package com.example.GestioneRemoto.Contenitori;

import java.time.LocalDate;
import java.sql.*;

public class Congedo {
    private int id,ref_impiegato;
    private boolean stato;
    private LocalDate data_inizio,data_fine;
    private String tipo,motivazione;
    private Blob allegato;

    public Congedo(int id,int ref_impiegato,boolean stato,LocalDate data_inizio,LocalDate data_fine,String tipo,String motivazione,Blob allegato){
        this.id=id;
        this.ref_impiegato=ref_impiegato;
        this.stato=stato;
        this.data_inizio=data_inizio;
        this.data_fine=data_fine;
        this.tipo=tipo;
        this.motivazione=motivazione;
        this.allegato=allegato;
    }
}
