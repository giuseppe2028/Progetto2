package com.example.GestioneRemoto.Contenitori;

import java.sql.*;

public class Stipendio {
    private int codice,ref_impiegato,anno;
    private String mese;
    private SQLXML resoconto;

    public Stipendio (int codice, int ref_impiegato,String mese,int anno, SQLXML resoconto){
        this.codice=codice;
        this.ref_impiegato=ref_impiegato;
        this.mese=mese;
        this.anno=anno;
        this.resoconto=resoconto;
    }
}
