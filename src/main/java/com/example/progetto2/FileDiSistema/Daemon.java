package com.example.progetto2.FileDiSistema;

import com.example.progetto2.FileDiSistema.Contenitori.PropostaTurno;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Daemon {
    private static Connection conn;
    public Daemon(){
        try {
            conn  = DriverManager.getConnection(URL,username,passwordDBMS);
            System.out.println("Connessione Stabilita");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private static String URL = "jdbc:mysql://localhost:3306/Azienda";
    private static String username = "root";
    private static String passwordDBMS = "root1234";
    private static PreparedStatement preparedStatement;
    private static ResultSet resultSet;
    private static List<Object> ritorno;

    public static boolean verifyCredenziali(int matricola, String password){

        try{
            //faccio la query
            String sql = "SELECT matricola FROM Utente WHERE matricola = ? AND password = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setString(2,password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else {
                return false;
            }
        }
        catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        //return fittizzio
        return false;
    }
    public static List<Object> getDatiProfilo(int matricola){
        try {
            ritorno = new ArrayList<>();
            String sql = "SELECT * FROM Utente WHERE matricola = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet = preparedStatement.executeQuery();
            //ho messo tutto nella lista
            while(resultSet.next()){
                ritorno.add(resultSet.getInt("matricola"));
                ritorno.add(resultSet.getString("nome"));
                ritorno.add(resultSet.getString("cognome"));
                ritorno.add(resultSet.getString("cf"));
                ritorno.add(resultSet.getDate("data_nascita"));
                ritorno.add(resultSet.getBlob("foto_profilo"));
                ritorno.add(resultSet.getString("indirizzo_residenza"));
                ritorno.add(resultSet.getString("ruolo"));
                ritorno.add(resultSet.getString("mail"));
                ritorno.add(resultSet.getString("password"));
                ritorno.add(resultSet.getString("IBAN"));
                ritorno.add(resultSet.getLong("recapito_telefonico"));
                ritorno.add(resultSet.getString("mail_personale"));

            }
            System.out.println(ritorno.get(7));
            return ritorno;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }







    public static int getMatricola(String mail){
        ResultSet rs;
        try{
            String sql = "SELECT * FROM Utente WHERE mail = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,mail);
            rs = pstm.executeQuery();
            rs.next();
            return rs.getInt("matricola");
        }catch (SQLException a){
            return 0;
        }
    }
    /*public static void updatePresaVisione(int ID){
        try{
            String sql = "UPDATE Notifica SET presa_visione = true WHERE = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1,ID);
            pstm.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/


    public static boolean verifyDati(String nome,String cognome, int matricola){

        try {
            System.out.println("Verify: " + nome+cognome+matricola);
            String sql = "SELECT * FROM Impiegato WHERE nome=? AND cognome = ? AND matricola = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,nome);
            preparedStatement.setString(2,cognome);
            preparedStatement.setInt(3,matricola);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }
            else{
                return false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static LocalTime getInizioTurno(int matricola,LocalDate date) throws RuntimeException {
        System.out.println("INIIO" + matricola + date.toString());
    try {
        String sql = "select distinct t.inizio_turno,tr.data from Turno t join timbratura tr on t.id=tr.ref_turno and t.tipo_turno=tr.ref_tipo_turno where tr.ref_impiegato=? and tr.data=?";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, matricola);
        java.sql.Date sqlDate = java.sql.Date.valueOf(date);
        preparedStatement.setDate(2, sqlDate);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getTime(1).toLocalTime();
    } catch (SQLException e) {
        System.out.println("Errore Comunicazione DBMS");
        throw new RuntimeException(e);
    }

    }

    public static LocalTime getFineTurno(int matricola, LocalDate date) {
    try {
            String sql = "select distinct t.fine_turno as FineTurno,tr.data as DataTurno from Turno t join timbratura tr on t.id=tr.ref_turno and t.tipo_turno=tr.ref_tipo_turno where tr.ref_impiegato=? and tr.data=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, matricola);
            java.sql.Date sqlDate = java.sql.Date.valueOf(date);
            preparedStatement.setDate(2, sqlDate);
            resultSet = preparedStatement.executeQuery();
            resultSet.next();
            return resultSet.getTime(1).toLocalTime();
    } catch (SQLException e) {
            System.out.println("Errore Comunicazione DBMS");    }
            return null;}




    //TODO: rivedere nome cartella


    public static void insertTimbratura(LocalDate date,LocalTime time, int matricola){

    }
   /* public static Date getFineTurno(int matricola, Date date){


        try {
            String sql = "select "
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setDate(1,date);
            pstm.setInt(2,matricola);
            resultSet = pstm.executeQuery();
            if(resultSet.next()){
                return resultSet.getDate(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }*/

    public static  List<PropostaTurno> getPropostaTurni(){

        ArrayList<PropostaTurno> listaRitorno = new ArrayList<>();
        try {
            String sql = "Select id,tipo_turno, cognome, I.ref_servizio,I.ruolo,data_turno from PropostaTurno PT,Impiegato I where PT.ref_impiegato = I.matricola";
            PreparedStatement pstm = conn.prepareStatement(sql);
            resultSet = pstm.executeQuery();

            while (resultSet.next()){
                listaRitorno.add(new PropostaTurno(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5),resultSet.getDate(6).toLocalDate()));
            }
            return listaRitorno;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}