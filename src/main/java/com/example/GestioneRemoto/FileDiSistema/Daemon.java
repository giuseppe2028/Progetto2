package com.example.GestioneRemoto.FileDiSistema;

import java.sql.*;
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

                }
            }
        } catch (SQLException e) {
            System.out.println("Error in getDatiProfilo");
        }
        if (ritorno.isEmpty()) {
            System.out.println("no data found for matricola: ");
        }
        return ritorno;
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

    public static boolean modificaDati(Double recapito,String indirizzo, String iban, String mail) {
        int matricola= EntityUtente.getMatricola();

        try{
            String query= "UPDATE Utente SET indirizzo_residenza=?, recapito_telefonico=?, IBAN=?, mail_personale=?  WHERE matricola=?";
            PreparedStatement pstm1= conn.prepareStatement(query);
            pstm1.setString(1, indirizzo);
            pstm1.setDouble(2, recapito);
            pstm1.setString(3, iban);
            pstm1.setString(4, mail);
            pstm1.setInt(5, matricola);
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

    public static ResultSet getRichiesta() throws SQLException {
        ResultSet rs = null;


        try {

            Connection conn = DriverManager.getConnection(URL, username, passwordDBMS);
            int matricola= EntityUtente.getMatricola();
            String sql = "SELECT * FROM Richiesta WHERE ref_impiegato=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setInt(1, matricola);
            rs = pstm.executeQuery();
            return rs;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
    public static void delete(int ID_richiesta) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, username, passwordDBMS);
        String deleteSQL = "DELETE FROM Richiesta WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, ID_richiesta);

        preparedStatement.executeUpdate();

    }

}