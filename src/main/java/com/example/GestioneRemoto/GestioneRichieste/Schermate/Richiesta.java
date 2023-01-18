package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import java.time.LocalDate;
import java.time.LocalTime;

public class Richiesta{
    private  int ref_impiegato;
    private  int anInt1;
    private  String string2;
    private  LocalTime toLocalTime1;
    private  LocalTime toLocalTime;
    private  String string1;
    private String string;
    private  LocalDate toLocalDate1;
    private  int anInt;
    private boolean aBoolean;
    private LocalDate toLocalDate;
    private LocalDate data_inizio;
    private  LocalDate data_fine;
    private  String  tipo;
    private  String motivazione;
    private  String allegato;
    private  String svolgimento;
    private  LocalTime ora_inizio;
    private  LocalTime ora_fine;
    private  int matricola;
    private String turno_origine;
    private  String turno_destinazione;
    private  LocalDate data_turno_origine;
    private  LocalDate data_turno_destinazione;
    private int id;
    private boolean stato;



    public LocalDate getData_inizio() {
        return data_inizio;
    }

    public void setData_inizio(LocalDate data_inizio) {
        this.data_inizio = data_inizio;
    }

    public LocalDate getData_fine() {
        return data_fine;
    }

    public void setData_fine(LocalDate data_fine) {
        this.data_fine = data_fine;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMotivazione() {
        return motivazione;
    }

    public void setMotivazione(String motivazione) {
        this.motivazione = motivazione;
    }

    public String getAllegato() {
        return allegato;
    }

    public void setAllegato(String allegato) {
        this.allegato = allegato;
    }

    public String getSvolgimento() {
        return svolgimento;
    }

    public void setSvolgimento(String svolgimento) {
        this.svolgimento = svolgimento;
    }

    public LocalTime getOra_inizio() {
        return ora_inizio;
    }

    public void setOra_inizio(LocalTime ora_inizio) {
        this.ora_inizio = ora_inizio;
    }

    public LocalTime getOra_fine() {
        return ora_fine;
    }

    public void setOra_fine(LocalTime ora_fine) {
        this.ora_fine = ora_fine;
    }

    public int getMatricola() {
        return matricola;
    }

    public void setMatricola(int matricola) {
        this.matricola = matricola;
    }

    public String getTurno_origine() {
        return turno_origine;
    }

    public void setTurno_origine(String turno_origine) {
        this.turno_origine = turno_origine;
    }

    public String getTurno_destinazione() {
        return turno_destinazione;
    }

    public void setTurno_destinazione(String turno_destinazione) {
        this.turno_destinazione = turno_destinazione;
    }

    public LocalDate getData_turno_origine() {
        return data_turno_origine;
    }

    public void setData_turno_origine(LocalDate data_turno_origine) {
        this.data_turno_origine = data_turno_origine;
    }

    public LocalDate getData_turno_destinazione() {
        return data_turno_destinazione;
    }

    public void setData_turno_destinazione(LocalDate data_turno_destinazione) {
        this.data_turno_destinazione = data_turno_destinazione;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isStato() {
        return stato;
    }

    public void setStato(boolean stato) {
        this.stato = stato;
    }
    public Richiesta(int id,int ref_impiegato,boolean stato){
        this.id=id;
        this.ref_impiegato=ref_impiegato;
        this.stato=stato;
        this.data_inizio=data_inizio;
        this.data_fine=data_fine;
    }

  /*  public Richiesta(int id, LocalDate data_inizio, LocalDate data_fine, String tipo, Boolean stato, String motivazione, String allegato,
                     String svolgimento, LocalTime ora_inizio, LocalTime ora_fine, int matricola, String turno_origine, String turno_destinazione,
                     LocalDate data_turno_origine, LocalDate data_turno_destinazione){
        this.id=id;
this.data_inizio=data_inizio;
this.data_fine=data_fine;
this.tipo=tipo;
this.stato=stato;
this.motivazione=motivazione;
this.allegato=allegato;
this.svolgimento=svolgimento;
this.ora_inizio=ora_inizio;
this.ora_fine=ora_fine;
this.matricola=matricola;
this.turno_origine=turno_origine;
        this.turno_destinazione=turno_destinazione;
        this.data_turno_origine=data_turno_origine;
        this.data_turno_destinazione=data_turno_destinazione;
        
        

        
    }

    public Richiesta(Object richiesta) {
        this.id=id;
        this.data_inizio=data_inizio;
        this.data_fine=data_fine;
        this.tipo=tipo;
        this.stato=stato;
        this.motivazione=motivazione;
        this.allegato=allegato;
        this.svolgimento=svolgimento;
        this.ora_inizio=ora_inizio;
        this.ora_fine=ora_fine;
        this.matricola=matricola;
        this.turno_origine=turno_origine;
        this.turno_destinazione=turno_destinazione;
        this.data_turno_origine=data_turno_origine;
        this.data_turno_destinazione=data_turno_destinazione;


    }

    public Richiesta(int anInt, boolean aBoolean, LocalDate toLocalDate, LocalDate toLocalDate1, String string, String string1) {
this.anInt=anInt;
this.aBoolean=aBoolean;
this.toLocalDate=toLocalDate;
this.toLocalDate1= toLocalDate1;
this.string=string;
this.string1=string1;
    }
    public String toString(){
        String a=Integer.toString(id)+stato+data_inizio+data_fine+tipo+motivazione+ stato+allegato +svolgimento+ora_inizio+ ora_fine+matricola+turno_origine+turno_destinazione+
                data_turno_origine+ data_turno_destinazione;

        return  a;
    }

    public Richiesta(int anInt, boolean aBoolean, LocalDate toLocalDate, LocalDate toLocalDate1) {
        this.anInt=anInt;
        this.aBoolean=aBoolean;
        this.toLocalDate=toLocalDate;
        this.toLocalDate1= toLocalDate1;
    }

    public Richiesta(int anInt, boolean aBoolean, LocalDate toLocalDate, LocalDate toLocalDate1, LocalTime toLocalTime, LocalTime toLocalTime1) {
        this.anInt=anInt;
        this.aBoolean=aBoolean;
        this.toLocalDate=toLocalDate;
        this.toLocalDate1= toLocalDate1;
        this.toLocalTime=toLocalTime;
        this.toLocalTime1=toLocalTime1;
    }

    public Richiesta(int anInt, int anInt1, String string, boolean aBoolean, String string1, String string2, LocalDate toLocalDate, LocalDate toLocalDate1) {
        this.anInt=anInt;
        this.anInt1=anInt1;
        this.aBoolean=aBoolean;
        this.string=string;
        this.string1=string1;
        this.string2=string2;
        this.toLocalDate=toLocalDate;
        this.toLocalDate1= toLocalDate1;
    }

    public Richiesta() {

    }


    public int getID_richiesta() {
        return id;
    }*/

}
