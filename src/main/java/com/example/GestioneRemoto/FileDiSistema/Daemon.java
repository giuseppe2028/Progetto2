package com.example.GestioneRemoto.FileDiSistema;

import com.example.GestioneRemoto.Contenitori.Impiegati;
import com.example.GestioneRemoto.Contenitori.Periodi;
import com.example.GestioneRemoto.Contenitori.PropostaTurno;

import java.sql.*;
import java.time.LocalDate;
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

            //String sql = "SELECT matricola FROM Utente WHERE matricola = ? AND password = ?";
            String sql = "SELECT matricola,Impiegato.password,ruolo FROM Impiegato WHERE matricola = ? AND Impiegato.password = ? UNION SELECT matricola,Amministrativo.password,ruolo FROM amministrativo WHERE matricola = ? AND Amministrativo.password = ? UNION SELECT matricola,Datore.password,ruolo FROM datore WHERE matricola = ? AND Datore.password = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setString(2,password);
            preparedStatement.setInt(3,matricola);
            preparedStatement.setString(4,password);
            preparedStatement.setInt(5,matricola);
            preparedStatement.setString(6,password);
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
            System.out.println("ciao");
        }
        //return fittizzio
        return false;
    }
    public static List<Object> getDatiProfilo(int matricola){
        try {
            ritorno = new ArrayList<>();
            String sql = "SELECT matricola,nome,cognome,cf,data_nascita,foto_profilo,indirizzo_residenza,ruolo,mail,Impiegato.password,iban,recapito_telefonico,mail_personale FROM Impiegato WHERE matricola = ?  UNION SELECT matricola,nome,cognome,cf,data_nascita,foto_profilo,indirizzo_residenza,ruolo,mail,Datore.password,iban,recapito_telefonico,mail_personale FROM Datore WHERE matricola = ? UNION SELECT matricola,nome,cognome,cf,data_nascita,foto_profilo,indirizzo_residenza,ruolo,mail,Amministrativo.password,iban,recapito_telefonico,mail_personale FROM Amministrativo WHERE matricola = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setInt(2,matricola);
            preparedStatement.setInt(3,matricola);
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

            return ritorno;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }



    /*public static boolean verificaNotifiche(int matricola){
         ResultSet rs;
        try {

            conn  = DriverManager.getConnection(URL,username,passwordDBMS);
            String sql = "SELECT * FROM Notifiche WHERE presa_visione = false and ref_matricola = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, 1);
            rs =  pstm.executeQuery();
            if(rs.next()){
                return true;
            }
            else{
                return false;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }    */

   /* public static ResultSet getNuoveNotifiche(int matricola){
        ResultSet rs;
        try {

            conn  = DriverManager.getConnection(URL,username,passwordDBMS);
            String sql = "SELECT * FROM Notifica WHERE presa_visione = false and ref_matricola = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, matricola);
            rs =  pstm.executeQuery();
            return rs;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
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


    public static boolean verifyDati(String nome,String congome,int matricola){

        try {
            String sql = "Select * from Impiegato where nome =? and cognome = ? and matricola = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,nome);
            pstm.setString(2,congome);
            pstm.setInt(3,matricola);
            resultSet = pstm.executeQuery();
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
    //todo Giacomo: rivedere se appatta con quello scritto da te nel DBMS
    //todo Giacomo: inserire il tipo di ritorno del metodo
    public static Date getInizioturno(int matricola, Date date){


        try {
            String sql = "Select orario_Timbratura from Esegue where data_Timbratura = ? and ref_impiegato_timbratura = ?";
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
    }
    //TODO: rivedere nome cartella
    public static void insertTimbratura(Date date,Time time, int matricola){
        //siccome devo inserire i dati in due tabelle, allora devo fare due insert, una su esegue e una timbratura
       //ottengo prima l'ID del turno che voglio inserire così poi lo inseirsco
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
    //todo mettere dataTurno
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


public static List<Periodi> getPeriodi(){
   List<Periodi> lista = new ArrayList<>();
    try {
        String sql = "select data_Inizio,data_Fine,categoria from FestivitaFerie";
        preparedStatement = conn.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            lista.add(new Periodi(resultSet.getDate(1).toLocalDate(),resultSet.getDate(1).toLocalDate(),resultSet.getString(1)));
        }
        return lista;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}

public static List<Impiegati> getImpiegati(int servizio,String ruolo){
    List<Impiegati> listaDaRitornare = new ArrayList<>();
    try {
        String sql = "select matricola, nome,cognome,ref_servizio, ruolo from Impiegato where ref_servizio = ? and ruolo = ?";
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1,servizio);
        preparedStatement.setString(2,ruolo);
        resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            listaDaRitornare.add(new Impiegati(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5)));
        }
        return listaDaRitornare;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }

}
    public static List<Impiegati> getImpiegati(String ruolo){
        List<Impiegati> listaDaRitornare = new ArrayList<>();
        try {
            String sql = "select matricola, nome,cognome,ref_servizio, ruolo from Impiegato where ruolo = ? ORDER BY ref_servizio";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,ruolo);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listaDaRitornare.add(new Impiegati(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5)));
            }
            return listaDaRitornare;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public static List<Impiegati> getImpiegati(){
        List<Impiegati> listaDaRitornare = new ArrayList<>();
        try {
            String sql = "select matricola, nome,cognome,ref_servizio, ruolo from Impiegato ORDER BY ref_servizio";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                listaDaRitornare.add(new Impiegati(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getString(5)));
            }
            return listaDaRitornare;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
public static int getMaxMatricola(){
    //todo fare sia impiegato che amministrativo

    try {
        String sql = "select max(matricola) from Impiegato";
        preparedStatement = conn.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();
        resultSet.next();
        return resultSet.getInt(1);
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }


}
public static void updateImpiegato(int matricola, String nome, String cognome, char sesso, String cf, LocalDate dataNascita,String indirizzoResidenza,long recapitoTelefonico,String mailPersonale,String iban, String mail,String password,String ruolo, boolean reperibile,int servizio,LocalDate inizioServizio, LocalDate fineServizio,int giorniFerieRimanenti,double orePermesso,double oreStraordinario,boolean disattivato,Blob fotoProfilo){


}
}