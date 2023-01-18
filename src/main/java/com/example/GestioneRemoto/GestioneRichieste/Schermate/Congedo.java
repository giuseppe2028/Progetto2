package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import java.time.LocalDate;
import java.sql.*;

public class Congedo extends Richiesta {
    private int id,ref_impiegato;
    private boolean stato;
    private LocalDate data_inizio,data_fine;
    private String tipo,motivazione;
    private Blob allegato;

    public Congedo(int id,int ref_impiegato,boolean stato,LocalDate data_inizio, LocalDate data_fine,String tipo,String motivazione){
        super(id, ref_impiegato, stato);
        this.data_inizio=data_inizio;
        this.data_fine=data_fine;


        this.tipo=tipo;
        this.motivazione=motivazione;

    }
    public String toString(){
        String a=Integer.toString(id)+stato+data_inizio+data_fine+tipo+motivazione;

        return  a;
    }
}
