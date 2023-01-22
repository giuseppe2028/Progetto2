package com.example.GestioneRemoto.FileDiSistema;

import com.example.GestioneRemoto.Contenitori.Impiegati;
import com.example.GestioneRemoto.Contenitori.Richiesta;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class Daemon {
    public static long largeupdate;
    private static Connection conn;

    public Daemon() {
        try {
            conn = DriverManager.getConnection(URL, username, passwordDBMS);
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

    public static boolean verifyCredenziali(int matricola, String password) {

        try {
            //faccio la query
            String sql = "SELECT matricola FROM Utente WHERE matricola = ? AND password = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, matricola);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
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
                   /* Blob blob = resultSet.getBlob("foto_profilo");
                    byte[] data = blob.getBinaryStream().readAllBytes();
                    InputStream inputStream = new ByteArrayInputStream(data);
                    ritorno.add(inputStream);
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
    public static InputStream getFotoProfilo(int matricola) {
        String sql = "SELECT foto_profilo FROM Utente WHERE matricola = ?";
        try (PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, matricola);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Blob blob = resultSet.getBlob("foto_profilo");
                    return blob.getBinaryStream();
                } else {
                    throw new SQLException("Immagine non trovata");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }



    public static int getMatricola(String mail) {
        ResultSet rs;
        try {
            String sql = "SELECT * FROM Utente WHERE mail = ?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, mail);
            rs = pstm.executeQuery();
            rs.next();
            return rs.getInt("matricola");
        } catch (SQLException a) {
            return 0;
        }
    }

    public static boolean modificaDati(Double recapito, String indirizzo, String iban, String mail, InputStream path) {
        int matricola = EntityUtente.getMatricola();

        try {
            String query = "UPDATE Utente SET indirizzo_residenza=?, recapito_telefonico=?, IBAN=?, mail_personale=?, foto_profilo=?  WHERE matricola=?";
            PreparedStatement pstm1 = conn.prepareStatement(query);
            pstm1.setString(1, indirizzo);
            pstm1.setDouble(2, recapito);
            pstm1.setString(3, iban);
            pstm1.setString(4, mail);

            pstm1.setBlob(5, path);
            pstm1.setInt(6, matricola);

            pstm1.execute();
            return true;

        } catch (SQLException a) {
            return false;
        }
    }

    public static boolean modificaPassword(String vecpass, String nuovapass) {
        ResultSet rs;
        int matricola = EntityUtente.getMatricola();

        try {
            String sql = "SELECT password FROM Utente WHERE password=? AND matricola=?";
            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, vecpass);
            pstm.setInt(2, matricola);
            rs = pstm.executeQuery();


            if (rs.next()) {
                String query = "UPDATE Utente SET password=? WHERE matricola=?";
                PreparedStatement pstm1 = conn.prepareStatement(query);
                pstm1.setString(1, nuovapass);
                pstm1.setInt(2, matricola);
                pstm1.execute();
                return true;
            }
        } catch (SQLException a) {
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

    public static List<Richiesta> getRichieste(int matricola) {
        ArrayList<Richiesta> listaRitorno = new ArrayList();

        try {
            System.out.println(matricola);
            String sql = "select id, categoria, stato, data_inizio, data_fine, ora_inizio, ora_fine, svolgimento, motivazione, tipologia, matricola_destinazione, tipo_turno_origine,tipo_turno_destinazione, data_turno_origine, data_turno_destinazione from Richiesta where ref_impiegato=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, matricola);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listaRitorno.add(new Richiesta(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4).toLocalDate(), resultSet.getDate(5).toLocalDate(), resultSet.getTime(6).toLocalTime(), resultSet.getTime(7).toLocalTime(), resultSet.getString(8), resultSet.getString(9), resultSet.getString(10), resultSet.getInt(11), resultSet.getString(12), resultSet.getString(13), resultSet.getDate(14).toLocalDate(), resultSet.getDate(15).toLocalDate()));
            }
            return listaRitorno;
        } catch (SQLException e) {
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }


    public static List<Impiegati> getImpiegati(int servizio, String ruolo) {
        List<Impiegati> listaDaRitornare = new ArrayList<>();
        try {
            String sql = "select matricola, nome,cognome,ref_servizio, ruolo from Impiegato where ref_servizio = ? and ruolo = ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, servizio);
            preparedStatement.setString(2, ruolo);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listaDaRitornare.add(new Impiegati(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5)));
            }
            return listaDaRitornare;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Impiegati> getImpiegati(String ruolo) {
        List<Impiegati> listaDaRitornare = new ArrayList<>();
        try {
            String sql = "select matricola, nome,cognome,ref_servizio, ruolo from Impiegato where ruolo = ? ORDER BY ref_servizio";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, ruolo);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listaDaRitornare.add(new Impiegati(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5)));
            }
            return listaDaRitornare;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static List<Impiegati> getImpiegati() {
        List<Impiegati> listaDaRitornare = new ArrayList<>();
        try {
            String sql = "select matricola, nome,cognome,ref_servizio, ruolo from Impiegato ORDER BY ref_servizio";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listaDaRitornare.add(new Impiegati(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getInt(4), resultSet.getString(5)));
            }
            return listaDaRitornare;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<LocalDate> getGiorniProibiti() {
        List<LocalDate> listaRitorno = new ArrayList();
        try {
            String sql = "select data_inizio, data_fine from FestivitaFerie";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listaRitorno.add(resultSet.getDate("data_inizio").toLocalDate());
                listaRitorno.add(resultSet.getDate("data_fine").toLocalDate());
            }
            return listaRitorno;
        } catch (SQLException e) {
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }


    public static void insertMalattia(int matricola, LocalDate dataInizio, LocalDate dataFine, String motivazione, InputStream file) {

        try {
            String sql = "INSERT INTO Richiesta(ref_impiegato,categoria,stato,data_inizio,data_fine,ora_inizio,ora_fine,svolgimento,motivazione,tipologia,matricola_destinazione,tipo_turno_origine,tipo_turno_destinazione,data_turno_origine,data_turno_destinazione,allegato)values (?,'malattia','accettata',?,?,'','','',?,'',0,'','','1970-01-01','1970-01-01',?) ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, matricola);
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

    public static void insertLutto(int matricola, LocalDate dataInizio, LocalDate dataFine, InputStream file) {
        try {
            String sql = "INSERT INTO Richiesta(ref_impiegato,categoria,stato,data_inizio,data_fine,ora_inizio,ora_fine,svolgimento,motivazione,tipologia,matricola_destinazione,tipo_turno_origine,tipo_turno_destinazione,data_turno_origine,data_turno_destinazione,allegato)values (?,'congedo per lutto','accettata',?,?,'','','','','',0,'','','1970-01-01','1970-01-01',?) ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, matricola);
            preparedStatement.setDate(2, Date.valueOf(dataInizio));
            preparedStatement.setDate(3, Date.valueOf(dataFine));
            preparedStatement.setBlob(4, file);
            largeupdate = preparedStatement.executeLargeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertCongedoParentale(int matricola, LocalDate dataInizio, LocalDate dataFine, InputStream file) {
        try {
            String sql = "INSERT INTO Richiesta( ref_impiegato,categoria,stato,data_inizio,data_fine,ora_inizio,ora_fine,svolgimento,motivazione,tipologia,matricola_destinazione,tipo_turno_origine,tipo_turno_destinazione,data_turno_origine,data_turno_destinazione,allegato)values (?,'congedo parentale','accettata',?,?,'','','','','',0,'','','1970-01-01','1970-01-01',?) ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, matricola);
            preparedStatement.setDate(2, Date.valueOf(dataInizio));
            preparedStatement.setDate(3, Date.valueOf(dataFine));
            preparedStatement.setBlob(4, file);
            largeupdate = preparedStatement.executeLargeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void insertMaternita(int matricola, LocalDate dataInizio, LocalDate dataFine, InputStream file) {
        try {
            String sql = "INSERT INTO Richiesta(ref_impiegato,categoria,stato,data_inizio,data_fine,ora_inizio,ora_fine,svolgimento,motivazione,tipologia,matricola_destinazione,tipo_turno_origine,tipo_turno_destinazione,data_turno_origine,data_turno_destinazione,allegato)values (?,'maternità','accettata',?,?,'','','','','',0,'','','1970-01-01','1970-01-01',?) ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, matricola);
            preparedStatement.setDate(2, Date.valueOf(dataInizio));
            preparedStatement.setDate(3, Date.valueOf(dataFine));
            preparedStatement.setBlob(4, file);
            largeupdate = preparedStatement.executeLargeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertSciopero(int matricola, LocalDate data, String motivazione, String svolgimento) {
        try {
            String s = "SELECT matricola FROM Utente WHERE ruolo='Datore' ";
            preparedStatement = conn.prepareStatement(s);
            String sql = "INSERT INTO Richiesta(ref_impiegato,categoria,stato,data_inizio,data_fine,ora_inizio,ora_fine,svolgimento,motivazione,tipologia,matricola_destinazione,tipo_turno_origine,tipo_turno_destinazione,data_turno_origine,data_turno_destinazione,allegato)values (?,'sciopero','in sospeso',?,'','','',?,?,'',?,'','','1970-01-01','1970-01-01',null) ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, matricola);
            preparedStatement.setDate(2, Date.valueOf(data));
            preparedStatement.setString(3, motivazione);
            preparedStatement.setString(4, svolgimento);
            preparedStatement.setInt(5, Integer.parseInt(s));
            largeupdate = preparedStatement.executeLargeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<Object> getTurni(int matricola) {
        ArrayList<Object> listaRitorno = new ArrayList();
        try {
            String sql = "select distinct t.* from Turno t where ref_impiegato=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, matricola);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listaRitorno.add(resultSet.getString(1));
                listaRitorno.add(resultSet.getTime(2));
                listaRitorno.add(resultSet.getTime(3));
                listaRitorno.add(resultSet.getDate(4));
                listaRitorno.add(resultSet.getInt(5));
                listaRitorno.add(resultSet.getInt(6));
            }
            return listaRitorno;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getTurno(LocalDate turnoOrigine, int matricola) {
        try {
            String sql = "select tipo_turno from Turno where data_turno=? and ref_impiegato=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(turnoOrigine));
            preparedStatement.setInt(2, matricola);
            resultSet = preparedStatement.executeQuery();
            String a = null;
            while (resultSet.next()) {
                a = resultSet.getString(1);
            }

            return a;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static int getServizio(int matricola) {
        try {
            String sql = "select ref_servizio from Impiegato where matricola=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, matricola);

            resultSet = preparedStatement.executeQuery();
            int a = 0;
            while (resultSet.next()) {
                a = resultSet.getInt(1);
            }

            return a;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static List<Integer> getMatricoleDestinazione(LocalDate turnoDestinazione, String tipo_turno, int servizio) {
        ArrayList<Integer> listaRitorno = new ArrayList();
        try {
            int matricola = EntityUtente.getMatricola();
            String sql = "select ref_impiegato from Turno join Impiegato on matricola=ref_impiegato  where data_turno=? and tipo_turno=? and ref_servizio=? and ref_impiegato != ?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(turnoDestinazione));
            preparedStatement.setString(2, tipo_turno);
            preparedStatement.setInt(3, servizio);
            preparedStatement.setInt(4, matricola);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                listaRitorno.add(resultSet.getInt("ref_impiegato"));
            }

            return listaRitorno;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static void insertCambioTurno(int matricola, LocalDate turnoOrigine, LocalDate turnoDestinazione, String turnoDesiderato, String turnoPrecedente) {
        try {
            String sql = "INSERT INTO Richiesta(ref_impiegato,categoria,stato,data_inizio,data_fine,ora_inizio,ora_fine,svolgimento,motivazione,tipologia,matricola_destinazione,tipo_turno_origine,tipo_turno_destinazione,data_turno_origine,data_turno_destinazione,allegato)values (?,'cambio turno','in sospeso','1970-01-01','1970-01-01','','','','','',0,?,?,?,?,null) ";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, matricola);
            preparedStatement.setString(2, turnoPrecedente);
            preparedStatement.setString(3, turnoDesiderato);
            preparedStatement.setDate(4, Date.valueOf(turnoOrigine));
            preparedStatement.setDate(5, Date.valueOf(turnoDestinazione));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static List<Richiesta> getRichiesteRicevute(int matricola){
     /*   ArrayList<Richiesta> listaRitorno = new ArrayList();
        try{
            String sql="select id,categoria, tipo_turno_origine,tipo_turno_destinazione, data_turno_origine, data_turno_destinazione from Richiesta  where categoria='cambio turno' and stato='in sospeso'";
            preparedStatement = conn.prepareStatement(sql);
           // preparedStatement.setInt(1,matricola);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(resultSet.getInt(1));
                listaRitorno.add(resultSet.getInt(2));
                listaRitorno.add(resultSet.getString(3));
                listaRitorno.add(resultSet.getString(4));
                listaRitorno.add(resultSet.getDate(5).toLocalDate());
                listaRitorno.add(resultSet.getDate(6).toLocalDate());


            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }*/
        return null;


    }

    // TODO: 21/01/23 i prossimi due metodi si potrebbero utilizzare per acc e rif sciopero
    public static boolean accettaCambioTurno(int id) throws SQLException {
        ResultSet rs = null;


        if (rs.next()) {
            String query = "UPDATE Richiesta SET stato='accettata' WHERE id=?";
            PreparedStatement pstm1 = conn.prepareStatement(query);
            pstm1.setInt(1, id);
            pstm1.execute();
            return true;
        }

        return false;
    }
    public static void updateTurni(int matricola, int matricolaOrigine){
        //todo implementare
    }

    public static boolean rifiutaCambioTurno(int id) throws SQLException {
        ResultSet rs = null;
            if (rs.next()) {
                String query = "UPDATE Richiesta SET stato='in sospeso' WHERE id=?";
                PreparedStatement pstm1 = conn.prepareStatement(query);
                pstm1.setInt(1, id);
                pstm1.execute();
                return true;
            }

            return false;

        }

    public static List<Richiesta> getRichiesteSciopero(int matricola) {
        ArrayList<Richiesta> listaRitorno = new ArrayList();
        try{
            String sql="select ref_impiegato,  data_inizio, data_fine,stato,  svolgimento, motivazione, id from Richiesta  where categoria='sciopero' and matricola_destinazione=?";
            preparedStatement = conn.prepareStatement(sql);
             preparedStatement.setInt(1,matricola);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                listaRitorno.add(new Richiesta(resultSet.getInt(1), resultSet.getDate(2).toLocalDate(), resultSet.getDate(3).toLocalDate(), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getInt(7)));
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;


    }

    public static boolean accettaSciopero(int id) throws SQLException {

            String query = "UPDATE Richiesta SET stato='accettata' WHERE id=?";
            PreparedStatement pstm1 = conn.prepareStatement(query);
            pstm1.setInt(1, id);
            pstm1.execute();
            return resultSet.next();
    }
    public static boolean rifiutaSciopero(int id) throws SQLException {
        ResultSet rs = null;
        if (rs.next()) {
            String query = "UPDATE Richiesta SET stato='rifiutata' WHERE id=?";
            PreparedStatement pstm1 = conn.prepareStatement(query);
            pstm1.setInt(1, id);
            pstm1.execute();
            return true;
        }

        return false;
    }
}


