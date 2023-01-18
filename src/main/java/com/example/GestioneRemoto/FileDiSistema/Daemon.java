package com.example.GestioneRemoto.FileDiSistema;

import com.example.GestioneRemoto.Contenitori.Richieste;
import com.example.GestioneRemoto.GestioneRichieste.Schermate.*;

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


    public static void delete(int ID_richiesta) throws SQLException {
        Connection conn = DriverManager.getConnection(URL, username, passwordDBMS);
        String deleteSQL = "DELETE FROM Richiesta WHERE id = ?";
        PreparedStatement preparedStatement = conn.prepareStatement(deleteSQL);
        preparedStatement.setInt(1, ID_richiesta);

        preparedStatement.executeUpdate();

    }

    public static List<Richiesta> getRichieste(int matricola){
        ArrayList<Richiesta> listaRitorno=new ArrayList<>();

       // listaRitorno.add(getRichiesta(matricola));
        listaRitorno.add((Richiesta) getCongedi(matricola));
        listaRitorno.add((Richiesta) getFerie(matricola));
        listaRitorno.add((Richiesta) getPermessi(matricola));
        listaRitorno.add((Richiesta) getRichiesteRicevute(matricola));
        listaRitorno.add((Richiesta) getScioperi(matricola));
        System.out.println(getCongedi(matricola).toString());
        return listaRitorno;
    }

   /* private static List<Richiesta> getRichiesta(int matricola){
        ArrayList<Richiesta> listaRitorno=new ArrayList();
        try{
            String sql="select id as ID,ref_impiegato as Matricola,stato as Stato,data_inizio as DataInizio,data_fine as DataFine from Richiesta where ref_impiegato=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(new Richiesta(resultSet.getInt(1),resultSet.getInt(2),resultSet.getBoolean(3),resultSet.getDate(4).toLocalDate(),resultSet.getDate(5).toLocalDate()));
            }
            return listaRitorno;
        }catch(SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }*/
    private static List<Richiesta> getCongedi(int matricola){
        ArrayList<Richiesta> listaRitorno=new ArrayList();


        try{

            String sql="select id as ID, ref_impiegato,stato as Stato,data_inizio as DataInizio,data_fine as DataFine,tipo as Tipo,motivazione as Motivazione from Congedo where ref_impiegato=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
               listaRitorno.add(new Congedo(resultSet.getInt(1),resultSet.getInt(2),resultSet.getBoolean(3),resultSet.getDate(4).toLocalDate(),resultSet.getDate(5).toLocalDate(),resultSet.getString(6),resultSet.getString(7)));
            }
            return listaRitorno;
        }catch(SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }
    private static List<Richiesta> getFerie(int matricola){
        ArrayList<Richiesta> listaRitorno=new ArrayList();
        try{
            //String sql="select id as ID,ref_impiegato as Matricola,stato as Stato,data_inizio as DataInizio,data_fine as DataFine from giorniFerie where ref_impiegato=?";
            String sql = "select id, ref_impiegato, stato, data_inizio, data_fine from Ferie where ref_impiegato = ?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(new GiorniFerie(resultSet.getInt(1),resultSet.getInt(2),resultSet.getBoolean(3),resultSet.getDate(4).toLocalDate(),resultSet.getDate(5).toLocalDate()));
            }
            return listaRitorno;
        }catch(SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }
    private static List<Richiesta> getPermessi(int matricola){
        ArrayList<Richiesta> listaRitorno=new ArrayList<>();
        try{
            String sql="select id as ID,ref_impiegato,stato as Stato,data_inizio as DataInizio,data_fine as DataFine,ora_inizio as OraInizio,ora_fine as OraFine from permesso where ref_impiegato=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(new Permesso(resultSet.getInt(1),resultSet.getInt(2),resultSet.getBoolean(3),resultSet.getDate(4).toLocalDate(),resultSet.getDate(5).toLocalDate(),resultSet.getTime(6).toLocalTime(),resultSet.getTime(7).toLocalTime()));
            }
            return listaRitorno;
        }catch(SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }
    private static List<Richiesta> getRichiesteRicevute(int matricola){
        ArrayList<Richiesta> listaRitorno=new ArrayList<>();
        try{
            String sql="select id as ID,ref_impiegato,matricola_destinazione as MatricolaDestinazione,categoria as Categoria,stato as Stato,tipo_turno_origine as TipoTurnoOrigine,tipo_turno_destinazione as TipoTurnoDestinazione,data_turno_origine as DataTurnoOrigine,data_turno_destinazione as DataTurnoDestinazione from richiestaRicevuta where ref_impiegato=?;";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(new RichiestaRicevuta(resultSet.getInt(1),resultSet.getInt(2),resultSet.getInt(3),resultSet.getString(4),resultSet.getBoolean(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8).toLocalDate(),resultSet.getDate(9).toLocalDate()));
            }
            return listaRitorno;
        }catch(SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }
    private static List<Richiesta> getScioperi(int matricola){
        ArrayList<Richiesta> listaRitorno=new ArrayList<>();
        try{
            String sql="select id as ID,ref_impiegato,stato as Stato,data_inizio as DataInizio,data_fine as DataFine,motivazione as Motivazione,svolgimento as Svolgimento from sciopero where ref_impiegato=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(new Sciopero(resultSet.getInt(1),resultSet.getInt(2),resultSet.getBoolean(3),resultSet.getDate(4).toLocalDate(),resultSet.getDate(5).toLocalDate(),resultSet.getString(6),resultSet.getString(7)));
            }
            return listaRitorno;
        }catch(SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }



}