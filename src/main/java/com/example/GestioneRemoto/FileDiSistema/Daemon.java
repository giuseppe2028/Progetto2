package com.example.GestioneRemoto.FileDiSistema;

import com.example.GestioneRemoto.Contenitori.*;

import java.io.CharArrayReader;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
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

    private static int update;

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
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return 0;
    }

    //FUNZIONA
    public static boolean verifyMailPersonale (String mailPersonale)
    {
        try {
            String sql = "SELECT mailPersonale FROM Utente WHERE mailPersonale=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1, mailPersonale);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
        System.out.println("Errore Comunicazione DBMS");
        }

        return false;
    }

    //FUNZIONA
    public static void updatePassword(String nuovaPassword,String mailPersonale)
    {
        try
        {
            String sql = "UPDATE Utente SET password=? WHERE mailPersonale=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setString(1,nuovaPassword);
            preparedStatement.setString(2,mailPersonale);
            update = preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static boolean verifyPassword(int matricola,String vecchia)
    {
        try
        {
            String sql="SELECT matricola and vecchia FROM Utente WHERE matricola=? and vecchia=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setString(2,vecchia);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }

        return false;
    }

    //FUNZIONA
    public static void updatePassword1(int matricola,String nuova){
        try{
            String sql="UPDATE Utente SET password=? WHERE matricola=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setString(2,nuova);
            update=preparedStatement.executeUpdate();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static List<Turno> getTurni (int matricola){
        ArrayList<Turno> listaRitorno=new ArrayList();
        try{
           String sql="SELECT T.* FROM Turno T,Impiegato I Turno WHERE I.matricola=? T.ref_impiegato=I.matricola";
           preparedStatement = conn.prepareStatement(sql);
           preparedStatement.setInt(1,matricola);
           resultSet=preparedStatement.executeQuery();
           while (resultSet.next()){
               listaRitorno.add(new Turno(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getTime(4).toLocalTime(),resultSet.getTime(5).toLocalTime(),resultSet.getDate(6).toLocalDate()));
           }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    //FUNZIONA
    public static void insertTimbraturaRemoto (int matricola,Date data,Time ora,String motivazione){
       ArrayList<Object> listaRitorno=new ArrayList();
       listaRitorno.add(getTimbraturaRemoto(matricola,motivazione));
       listaRitorno.add(getEsegue(data,ora));
    }

    private static List<TimbraturaRemoto> getTimbraturaRemoto(int matricola,String motivazione){
        ArrayList<TimbraturaRemoto> listaRitorno=new ArrayList();
        try{
            String sql="insert into TimbraturaRemoto set matricola=?,ref_turno=(select ref_turno),ref_tipo_turno=(select ref_tipo_turno),timbratura_effettuata=1,motivazione=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setString(2,motivazione);
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                listaRitorno.add(new TimbraturaRemoto(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getBoolean(4),resultSet.getString(5)));
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    private static List<Esegue> getEsegue(Date data,Time ora){
        ArrayList<Esegue> listaRitorno=new ArrayList();
        try{
            String sql="insert into Esegue set ref_impiegato_timbratura=(select ref_impiegato_timbratura),ref_turno_timbratura=(select ref_turno_timbratura),ref_tipo_turno_timbratura=(select ref_tipo_turno_timbratura),tipologia=(select tipologia),data=?,ora=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setDate(1,data);
            preparedStatement.setTime(2,ora);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(new Esegue(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getString(4),resultSet.getDate(5).toLocalDate(),resultSet.getTime(6).toLocalTime()));
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }


    //FUNZIONA
    public static List<GiorniProibiti> getGiorniProibiti(){
       ArrayList<GiorniProibiti> listaRitorno= new ArrayList();
        try {
            String sql = "SELECT * FROM GiorniProibiti";
            preparedStatement = conn.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                listaRitorno.add(new GiorniProibiti(resultSet.getInt(1), resultSet.getInt(2), resultSet.getString(3), resultSet.getDate(4).toLocalDate(), resultSet.getDate(5).toLocalDate()));
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    //FUNZIONA
    public static List<GiorniFerie> getGiorniFerie (int matricola){
        ArrayList<GiorniFerie> listaRitorno=new ArrayList();
        try{
            String sql= "SELECT * FROM GiorniFerie WHERE matricola=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                listaRitorno.add (new GiorniFerie(resultSet.getInt(1),resultSet.getInt(2),resultSet.getBoolean(3),resultSet.getDate(4).toLocalDate(),resultSet.getDate(5).toLocalDate()));
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    //FUNZIONA
    public static void insertRichiestaFerie (int matricola,Date dataInizio,Date dataFine){
        try{
            String sql="insert into Ferie set id=(select id),matricola=?,stato=(select stato),dataInizio=?,dataFine=?)";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setDate(2,dataInizio);
            preparedStatement.setDate(3,dataFine);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static void updateGiorniFerie(int matricola, Date dataInizio,Date dataFine){
        try{
            String sql="select (200 - ((sum(datediff(dataFine,dataInizio)))))as GiorniFerieRinamenti from Ferie f join Impiegato i on f.ref_impiegato=i.matricola where i.matricola=? and f.dataInizio=? and f.dataFine=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setDate(2,dataInizio);
            preparedStatement.setDate(3,dataFine);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static double getOrePermesso(int matricola){
        try{
            String sql="select round(((sum(timediff(ora_fine,ora_inizio))))/10000,2) as OrePermesso from Permesso p join Impiegato i on p.ref_impiegato=i.matricola where matricola=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet=preparedStatement.executeQuery();
            return resultSet.getDouble(1);
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return 0.0;
    }

    //FUNZIONA
    public static void insertOrePermesso(int matricola,Date data,Time oraInizio,Time oraFine){
        try{
            String sql="insert into Permesso set id=(select id),matricola=?,stato=(select stato),data=?,data_fine=data,oraInizio=?,oraFine=?;";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt (1,matricola);
            preparedStatement.setDate (2,data);
            preparedStatement.setTime (3,oraInizio);
            preparedStatement.setTime (4,oraFine);
            resultSet= preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static void updateOrePermesso (int matricola,Time oraInizio,Time oraFine){
        try{
            String sql="select (700 - round(((sum(timediff(oraFine,oraInizio))))/10000,2)) as OrePermessoRimanenti from Permesso p join Impiegato i on p.ref_impiegato=i.matricola where i.matricola=? and p.oraInizio=? and p.oraFine=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setTime(2,oraInizio);
            preparedStatement.setTime(3,oraFine);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static void insertRichiesteSciopero(int matricola,Date dataInizio,Date dataFine,String motivazione,String svolgimento){
        try{
            String sql="insert into Sciopero set id=(select id),matricola=?,stato=(select stato),dataInizio=?,dataFine=?,motivazione=?,svolgimento=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setDate(2,dataInizio);
            preparedStatement.setDate(3,dataFine);
            preparedStatement.setString(4,motivazione);
            preparedStatement.setString(5,svolgimento);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static int getMatricolaDatore(){
        try{
            String sql="Select matricola from Datore";
            preparedStatement = conn.prepareStatement(sql);
            resultSet= preparedStatement.executeQuery();
            resultSet.getInt(1);
        }catch (SQLException e){
            System.out.println("ErroreComunicazione DBMS");
        }
        return 0;
    }

    //FUNZIONA
    public static void insertCongedoParentale(int matricola,Date dataInizio,Date dataFine,Blob allegato){
        try{
            String sql="insert into congedo set id=(select id),matricola=?,stato=1,dataIinizio=?,dataFine=?,tipo='congedo parentale',motivazione=(select motivazione),allegato=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setDate(2,dataInizio);
            preparedStatement.setDate(3,dataFine);
            preparedStatement.setBlob(4,allegato);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static void insertCongedoLutto(int matricola,Date dataInizio,Date dataFine,Blob allegato){
        try{
            String sql="insert into congedo set id=(select id),matricola=?,stato=1,dataIinizio=?,dataFine=?,tipo='congedo per lutto',motivazione=(select motivazione),allegato=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setDate(2,dataInizio);
            preparedStatement.setDate(3,dataFine);
            preparedStatement.setBlob(4,allegato);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static void insertMaternita(int matricola,Date dataInizio,Date dataFine,Blob allegato){
        try{
            String sql="insert into congedo set id=(select id),matricola=?,stato=1,dataIinizio=?,dataFine=?,tipo='congedo maternità',motivazione=(select motivazione),allegato=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setDate(2,dataInizio);
            preparedStatement.setDate(3,dataFine);
            preparedStatement.setBlob(4,allegato);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static void insertMalattia(int matricola,Date dataInizio,Date dataFine,Blob allegato){
        try{
            String sql="insert into congedo set id=(select id),matricola=?,stato=1,dataIinizio=?,dataFine=?,tipo='congedo malattia',motivazione=(select motivazione),allegato=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setDate(2,dataInizio);
            preparedStatement.setDate(3,dataFine);
            preparedStatement.setBlob(4,allegato);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static void insertTimbraturaMattina(int matricola,Date data,Time ora){
        ArrayList<Object> listaRitorno=new ArrayList();
        listaRitorno.add(getTimbraturaMattina(matricola));
        listaRitorno.add(getEsegue(data,ora));
    }

    private static List<Timbratura> getTimbraturaMattina(int matricola){
        ArrayList<Timbratura> listaRitorno=new ArrayList();
        try{
            String sql="insert into Timbratura set matricola=?,ref_turno=(select ref_turno),ref_tipo_turno='mattina',timbratura_effettuata=(select timbratura_effettuata),tipo=(select tipo)";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(new Timbratura(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getBoolean(4),resultSet.getString(5)));
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    //FUNZIONA
    public static void insertTimbraturaPomeriggio(int matricola,Date data,Time ora){
        ArrayList<Object> listaRitorno=new ArrayList();
        listaRitorno.add(getTimbraturaPomeriggio(matricola));
        listaRitorno.add(getEsegue(data,ora));
    }
    private static List<Timbratura> getTimbraturaPomeriggio(int matricola){
        ArrayList<Timbratura> listaRitorno=new ArrayList();
        try{
            String sql="insert into Timbratura set matricola=?,ref_turno=(select ref_turno),ref_tipo_turno='pomeriggio',timbratura_effettuata=(select timbratura_effettuata),tipo=(select tipo)";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(new Timbratura(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getBoolean(4),resultSet.getString(5)));
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    //FUNZIONA
    public static  List<Impiegati> getMatricoleImpiegati(Date data,Time orario){
        ArrayList<Impiegati> listaRitorno=new ArrayList();
        try{
            String sql="select i.matricola from Impiegati i join Turno t on i.matricola=t.ref_impiegato where t.fine_turno=orario and t.data_turno=data and data=? and orario=?";
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setDate(1,data);
            preparedStatement.setTime(2,orario);
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                listaRitorno.add (new Impiegati(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getDate(6).toLocalDate(),resultSet.getString(7),resultSet.getLong(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getString(12),resultSet.getString(13),resultSet.getBoolean(14),resultSet.getInt(15),resultSet.getDate(16).toLocalDate(),resultSet.getDate(17).toLocalDate(),resultSet.getBoolean(18),resultSet.getBlob(19)));
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    //FUNZIONA
    private static Time getFineTurno (int matricola){
        try{
            String sql="select fine_turno as FineTurno from Turno where matricola=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet=preparedStatement.executeQuery();
            return resultSet.getTime(1);
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    //FUNZIONA
    public static boolean verifyUscita(int matricola,Time orariFineTurno){
        try{
            String sql="select ti.timbratura_effettuata as TimbraturaEffettuata from Turno t join timbratura ti on t.id=ti.ref_turno where matricola=? and orariFineTurno=?";
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setTime(2,orariFineTurno);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()) {
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return false;
    }

    //FUNZIONA
    public static void insertTimbraturaUscitaDimenticata(int matricola){
        try{
            String sql="update Timbratura set timbratura_effettuata=1 where matricola=?";
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static List<Periodi> getPeriodi(){
        ArrayList<Periodi> listaRitorno=new ArrayList();
        try{
            String sql="select categoria,data_inizio as DataInizio,data_fine as DataFine from Periodi";
            preparedStatement=conn.prepareStatement(sql);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                listaRitorno.add(new Periodi(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getDate(4).toLocalDate(),resultSet.getDate(5).toLocalDate()));
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    //FUNZIONA
    public static boolean verifyDateProibite(Date dataInizio,Date dataFine){
        try{
            String sql="select dataInizio,dataFine from FestivitaFerie where dataInizio=? and dataFine=?";
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setDate(1,dataInizio);
            preparedStatement.setDate(2,dataFine);
            resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return false;
    }

    //FUNZIONA
    public static void insertDateProibite(String cateogoria,Date dataInizio,Date dataFine){
        try{
            String sql="insert into FestivitaFerie set id=(select id),ref_datore=1001,categoria=?,data_inizio=?,data_fine=?";
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,cateogoria);
            preparedStatement.setDate(2,dataInizio);
            preparedStatement.setDate(3,dataFine);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore comunicazine DBMS");
        }
    }

    //FUNZIONA
    public static List<Impiegati> getImpiegati(int servizio,String ruolo){
        ArrayList<Impiegati> listaRitorno=new ArrayList();
        try{
            String sql="select matricola,servizio,ruolo from Impiegati where servizio=? and ruolo=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,servizio);
            preparedStatement.setString(2,ruolo);
            resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                listaRitorno.add (new Impiegati(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getDate(6).toLocalDate(),resultSet.getString(7),resultSet.getLong(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getString(12),resultSet.getString(13),resultSet.getBoolean(14),resultSet.getInt(15),resultSet.getDate(16).toLocalDate(),resultSet.getDate(17).toLocalDate(),resultSet.getBoolean(18),resultSet.getBlob(19)));
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    //FUNZIONA
    public static boolean verifyTimbratura(int matricola){
        try{
            String sql="select timbratura_effettuata from Timbratura where matricola=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet= preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }else {
                return false;
            }
        }catch(SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return false;
    }

    //FUNZIONA
    public static void insertTimbraturaImpiegato(int matricola,Time oraArrivo,Date dataArrivo){
        try{
            String sql="insert into TimbraturaImpiegato set ref_matricola=(select matricola from Amministrativo),matricola=?,oraArrivo=?,dataArrivo=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            preparedStatement.setTime(2,oraArrivo);
            preparedStatement.setDate(3,dataArrivo);
            resultSet= preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static void insertImpiegato(String nome,String cognome,String sesso,String cf,Date dataNascita,String indirizzoResidenza,long recapitoTelefonico,String mailPersonale,String iban,String ruolo,boolean reperibile,int refServizio,Blob fotoProfilo){
        try{
            String sql="insert into Impiegato set matricola=(select matricola),nome=?,cognome=?,sesso=?,cf=?,dataNascita=?,indirizzoResidenza=?,recapitoTelefonico=?,mailPersonale=?,iban=?,mail=(select mail),password=(select password),ruolo=?,reperibile=?,refServizio=?,inizio_servizio=(select inizio_servizio),fine_servizio=(select fine_servizio),disattivato=0,fotoProfilo=?";
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setString(1,nome);
            preparedStatement.setString(2,cognome);
            preparedStatement.setString(3,sesso);
            preparedStatement.setString(4,cf);
            preparedStatement.setDate(5,dataNascita);
            preparedStatement.setString(6,indirizzoResidenza);
            preparedStatement.setLong(7,recapitoTelefonico);
            preparedStatement.setString(8,mailPersonale);
            preparedStatement.setString(9,iban);
            preparedStatement.setString(10,ruolo);
            preparedStatement.setBoolean(11,reperibile);
            preparedStatement.setInt(12,refServizio);
            preparedStatement.setBlob(13,fotoProfilo);
            resultSet=preparedStatement.executeQuery();
        }catch(SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static boolean verifyPassword(String password){
        try{
            String sql="select password from Amministrativo where password=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setString(1,password);
            resultSet=preparedStatement.executeQuery();
            if (resultSet.next()){
                return true;
            }else {
                return false;
            }
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return false;
    }

    //FUNZIONA
    public static void updateImpiegato(int matricola){
        try{
            String sql="update Impiegato set disattivato=1 where matricola=?";
            preparedStatement=conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet=preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static List<Impiegati> getMatricole(){
        ArrayList<Impiegati> listaRitorno=new ArrayList();
        try{
            String sql="select matricola from Impiegati";
            preparedStatement=conn.prepareStatement(sql);
            resultSet= preparedStatement.executeQuery();
            while (resultSet.next()){
                listaRitorno.add (new Impiegati(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4),resultSet.getString(5),resultSet.getDate(6).toLocalDate(),resultSet.getString(7),resultSet.getLong(8),resultSet.getString(9),resultSet.getString(10),resultSet.getString(11),resultSet.getString(12),resultSet.getString(13),resultSet.getBoolean(14),resultSet.getInt(15),resultSet.getDate(16).toLocalDate(),resultSet.getDate(17).toLocalDate(),resultSet.getBoolean(18),resultSet.getBlob(19)));
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    //FUNZIONA
    public static void insertStipendio(int codice,int matricola,String mese,int anno,SQLXML resoconto){
        try{
            String sql="insert into Stipendio set codice=?,matricola=?,mese=?,anno=?,resoconto=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,codice);
            preparedStatement.setInt(2,matricola);
            preparedStatement.setString(3,mese);
            preparedStatement.setInt(4,anno);
            preparedStatement.setSQLXML(5,resoconto);
            resultSet= preparedStatement.executeQuery();
        }catch(SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static List<Stipendio> getStipendio(int matricola){
        ArrayList<Stipendio> listaRitorno=new ArrayList();
        try{
            String sql="select * from Stipendio where matricola=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(new Stipendio(resultSet.getInt(1),resultSet.getInt(2),resultSet.getString(3),resultSet.getInt(4),resultSet.getSQLXML(5)));
            }
            return listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    //FUNZIONA
    public static List<Sciopero> getRichiesteSciopero(int matricola){
        ArrayList<Sciopero> listaRitorno = new ArrayList();
        try{
            String sql="select * from Sciopero where matricola=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(new Sciopero(resultSet.getInt(1),resultSet.getInt(2),resultSet.getBoolean(3),resultSet.getDate(4).toLocalDate(),resultSet.getDate(5).toLocalDate(),resultSet.getString(6),resultSet.getString(7)));
            }
            return  listaRitorno;
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    //FUNZIONA
    public static void updateRichiesteScioperoAccettata(int richiesta){
        try{
            String sql="update Sciopero set stato=1 where richiesta=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,richiesta);
            resultSet= preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static void updateRichiesteScioperoRifiutata(int richiesta){
        try{
            String sql="update Sciopero set stato=0 where richiesta=?";
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1,richiesta);
            resultSet= preparedStatement.executeQuery();
        }catch (SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
    }

    //FUNZIONA
    public static List<Object> getRichieste(int matricola){
        ArrayList<Object> listaRitorno=new ArrayList();
        listaRitorno.add(getRichiesta(matricola));
        listaRitorno.add(getCongedi(matricola));
        listaRitorno.add(getFerie(matricola));
        listaRitorno.add(getPermessi(matricola));
        listaRitorno.add(getRichiesteRicevute(matricola));
        listaRitorno.add(getScioperi(matricola));
        return listaRitorno;
    }

    private static List<Richiesta> getRichiesta(int matricola){
        ArrayList<Richiesta> listaRitorno=new ArrayList();
        try{
            String sql="select id as ID,ref_impiegato as Matricola,stato as Stato,data_inizio as DataInizio,data_fine as DataFine from Richiesta where matricola=?";
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
    }

    private static List<Congedo> getCongedi(int matricola){
        ArrayList<Congedo> listaRitorno=new ArrayList();
        try{
            String sql="select id as ID,ref_impiegato as Matricola,stato as Stato,data_inizio as DataInizio,data_fine as DataFine,tipo as Tipo,motivazione as Motivazione,allegato as Allegato from Congedo where matricola=?";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(new Congedo(resultSet.getInt(1),resultSet.getInt(2),resultSet.getBoolean(3),resultSet.getDate(4).toLocalDate(),resultSet.getDate(5).toLocalDate(),resultSet.getString(6),resultSet.getString(7),resultSet.getBlob(8)));
            }
            return listaRitorno;
        }catch(SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    private static List<GiorniFerie> getFerie(int matricola){
        ArrayList<GiorniFerie> listaRitorno=new ArrayList();
        try{
            String sql="select id as ID,ref_impiegato as Matricola,stato as Stato,data_inizio as DataInizio,data_fine as DataFine from GiorniFerie where matricola=?";
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

    private static List<Permesso> getPermessi(int matricola){
        ArrayList<Permesso> listaRitorno=new ArrayList();
        try{
            String sql="select id as ID,ref_impiegato as Matricola,stato as Stato,data_inizio as DataInizio,data_fine as DataFine,ora_inizio as OraInizio,ora_fine as OraFine from Permesso where matricola=?";
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

    private static List<RichiestaRicevuta> getRichiesteRicevute(int matricola){
        ArrayList<RichiestaRicevuta> listaRitorno=new ArrayList();
        try{
            String sql="select id as ID,ref_impiegato as Matricola,matricola_destinazione as MatricolaDestinazione,categoria as Categoria,stato as Stato,tipo_turno_origine as TipoTurnoOrigine,tipo_turno_destinazione as TipoTurnoDestinazione,data_turno_origine as DataTurnoOrigine,data_turno_destinazione as DataTurnoDestinazione from RichiestaRicevuta where matricola=?;";
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

    private static List<Sciopero> getScioperi(int matricola){
        ArrayList<Sciopero> listaRitorno=new ArrayList();
        try{
            String sql="select id as ID,ref_impiegato as Matricola,stato as Stato,data_inizio as DataInizio,data_fine as DataFine,motivazione as Motivazione,svolgimento as Svolgimento from Sciopero where matricola=?";
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

    //FUNZIONA
    public static List<Object> getRichiesteAstensione(int matricola){
        ArrayList<Object> listaRitorno=new ArrayList();
        listaRitorno.add(getRichiesta1(matricola));
        listaRitorno.add(getCongedi1(matricola));
        listaRitorno.add(getFerie1(matricola));
        listaRitorno.add(getPermessi1(matricola));
        listaRitorno.add(getScioperi1(matricola));
        return listaRitorno;
    }

    private static List<Richiesta> getRichiesta1(int matricola){
        ArrayList<Richiesta> listaRitorno=new ArrayList();
        try{
            String sql="select id as ID,ref_impiegato as Matricola,stato as Stato,data_inizio as DataInizio,data_fine as DataFine from Richiesta where matricola=? and stato=1";
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
    }

    private static List<Congedo> getCongedi1(int matricola){
        ArrayList<Congedo> listaRitorno=new ArrayList();
        try{
            String sql="select id as ID,ref_impiegato as Matricola,stato as Stato,data_inizio as DataInizio,data_fine as DataFine,tipo as Tipo,motivazione as Motivazione,allegato as Allegato from Congedo where matricola=? stato=1";
            preparedStatement= conn.prepareStatement(sql);
            preparedStatement.setInt(1,matricola);
            resultSet= preparedStatement.executeQuery();
            while(resultSet.next()){
                listaRitorno.add(new Congedo(resultSet.getInt(1),resultSet.getInt(2),resultSet.getBoolean(3),resultSet.getDate(4).toLocalDate(),resultSet.getDate(5).toLocalDate(),resultSet.getString(6),resultSet.getString(7),resultSet.getBlob(8)));
            }
            return listaRitorno;
        }catch(SQLException e){
            System.out.println("Errore Comunicazione DBMS");
        }
        return null;
    }

    private static List<GiorniFerie> getFerie1(int matricola){
        ArrayList<GiorniFerie> listaRitorno=new ArrayList();
        try{
            String sql="select id as ID,ref_impiegato as Matricola,stato as Stato,data_inizio as DataInizio,data_fine as DataFine from GiorniFerie where matricola=? and stato=1";
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

    private static List<Permesso> getPermessi1(int matricola){
        ArrayList<Permesso> listaRitorno=new ArrayList();
        try{
            String sql="select id as ID,ref_impiegato as Matricola,stato as Stato,data_inizio as DataInizio,data_fine as DataFine,ora_inizio as OraInizio,ora_fine as OraFine from Permesso where matricola=? and stato=1";
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

    private static List<Sciopero> getScioperi1(int matricola){
        ArrayList<Sciopero> listaRitorno=new ArrayList();
        try{
            String sql="select id as ID,ref_impiegato as Matricola,stato as Stato,data_inizio as DataInizio,data_fine as DataFine,motivazione as Motivazione,svolgimento as Svolgimento from Sciopero where matricola=? and stato=1";
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