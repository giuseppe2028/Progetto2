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
    public static ResultSet getNuoveNotifiche(int matricola){
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

}