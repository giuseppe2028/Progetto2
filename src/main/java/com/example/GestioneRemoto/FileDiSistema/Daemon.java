package com.example.GestioneRemoto.FileDiSistema;

import com.example.GestioneRemoto.Contenitori.Impiegati;
import com.example.GestioneRemoto.Contenitori.Richiesta;

import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class Daemon {
    public static long largeupdate;
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
    public static List<Object> getDatiProfilo(int matricola) {
        List<Object> ritorno = new ArrayList<>();
        String sql = "SELECT * FROM Utente WHERE matricola = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            preparedStatement.setInt(1, matricola);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    ritorno.add(resultSet.getInt("matricola"));
                    ritorno.add(resultSet.getString("nome"));
                    ritorno.add(resultSet.getString("cognome"));
                    ritorno.add(resultSet.getString("cf"));
                    ritorno.add(resultSet.getDate("data_nascita"));
                    // ritorno.add(resultSet.getBlob("foto_profilo"));
                    ritorno.add(resultSet.getString("indirizzo_residenza"));
                    ritorno.add(resultSet.getString("ruolo"));
                    ritorno.add(resultSet.getString("mail"));
                    // ritorno.add(resultSet.getString("password"));
                    ritorno.add(resultSet.getString("IBAN"));
                    ritorno.add(resultSet.getLong("recapito_telefonico"));
                    ritorno.add(resultSet.getString("mail_personale"));
                   /* Blob clob = resultSet.getBlob("foto_profilo");
                    byte[] byteArr = clob.getBytes(1, (int) clob.length());
                    InputStream inputStream = new ByteArrayInputStream(byteArr);
                    ritorno.add(inputStream);*/
ritorno.add(resultSet.getString("sesso"));


                }


                return ritorno;

            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
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

    public static boolean modificaDati(Double recapito,String indirizzo, String iban, String mail, InputStream path) {
        int matricola= EntityUtente.getMatricola();

        try{
            String query= "UPDATE Utente SET indirizzo_residenza=?, recapito_telefonico=?, IBAN=?, mail_personale=?, foto_profilo=?  WHERE matricola=?";
            PreparedStatement pstm1= conn.prepareStatement(query);
            pstm1.setString(1, indirizzo);
            pstm1.setDouble(2, recapito);
            pstm1.setString(3, iban);
            pstm1.setString(4, mail);

            pstm1.setBlob(5, path);
            pstm1.setInt(6, matricola);

            pstm1.execute();
            return true;

        } catch (SQLException a){
            return false;
        }
    }

    public static boolean modificaPassword(String vecpass, String nuovapass) {
        ResultSet rs;
        int matricola= EntityUtente.getMatricola();

        try{
            String sql = "SELECT password FROM Utente WHERE password=? AND matricola=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1,vecpass);
            pstm.setInt(2, matricola);
            rs = pstm.executeQuery();


            if (rs.next()){
                String query= "UPDATE Utente SET password=? WHERE matricola=?";
                PreparedStatement pstm1= conn.prepareStatement(query);
                pstm1.setString(1, nuovapass);
                pstm1.setInt(2,matricola);
                pstm1.execute();
                return true;
            }
        } catch (SQLException a){
            return false;
        }
        return false;
    }


    public static void delete(int ID_richiesta) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, username, passwordDBMS);
        String deleteSQL = "DELETE FROM Richiesta WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, ID_richiesta);

        preparedStatement.executeUpdate();

    }

    public static List<Richiesta> getRichieste(int matricola){
        ArrayList<Richiesta> listaRitorno=new ArrayList();

        try{
            System.out.println(matricola);
            String sql="select id, categoria, stato, data_inizio, data_fine, ora_inizio, ora_fine, svolgimento, motivazione, tipologia, matricola_destinazione, tipo_turno_origine,tipo_turno_destinazione, data_turno_origine, data_turno_destinazione from Richiesta where ref_impiegato=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet = preparedStatement.executeQuery();

           while(resultSet.next()){
                listaRitorno.add(new Richiesta(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDate(4).toLocalDate(),resultSet.getDate(5).toLocalDate(),resultSet.getTime(6).toLocalTime(),resultSet.getTime(7).toLocalTime(),resultSet.getString(8),resultSet.getString(9),resultSet.getString(10),resultSet.getInt(11),resultSet.getString(12),resultSet.getString(13),resultSet.getDate(14).toLocalDate(),resultSet.getDate(15).toLocalDate()));
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
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
    public static List<LocalDate> getGiorniProibiti(){
        List<LocalDate> listaRitorno = new ArrayList();
        try{
            String sql="select data_inizio, data_fine from FestivitaFerie";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(resultSet.getDate("data_inizio").toLocalDate());
                listaRitorno.add(resultSet.getDate("data_fine").toLocalDate());
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }


    public static void insertMalattia(int matricola, LocalDate dataInizio, LocalDate dataFine, String motivazione, InputStream file) {

        try {
            String sql= "INSERT INTO Richiesta(ref_impiegato,categoria,stato,data_inizio,data_fine,ora_inizio,ora_fine,svolgimento,motivazione,tipologia,matricola_destinazione,tipo_turno_origine,tipo_turno_destinazione,data_turno_origine,data_turno_destinazione,allegato)values (?,'malattia','accettata',?,?,'','','',?,'',0,'','','1970-01-01','1970-01-01',?) ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setDate(2, Date.valueOf(dataInizio));
            preparedStatement.setDate(3, Date.valueOf(dataFine));
            preparedStatement.setBlob(5, file);
            preparedStatement.setString(4, motivazione);
           // preparedStatement.executeLargeUpdate();
            largeupdate = preparedStatement.executeLargeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static void insertMaternita(int matricola, LocalDate dataInizio, LocalDate dataFine, InputStream file) {
        try {
            String sql= "INSERT INTO Richiesta(ref_impiegato,categoria,stato,data_inizio,data_fine,ora_inizio,ora_fine,svolgimento,motivazione,tipologia,matricola_destinazione,tipo_turno_origine,tipo_turno_destinazione,data_turno_origine,data_turno_destinazione,allegato)values (?,'maternità','accettata',?,?,'','','','','',0,'','','1970-01-01','1970-01-01',?) ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setDate(2, Date.valueOf(dataInizio));
            preparedStatement.setDate(3, Date.valueOf(dataFine));
            preparedStatement.setBlob(4, file);
            largeupdate = preparedStatement.executeLargeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}