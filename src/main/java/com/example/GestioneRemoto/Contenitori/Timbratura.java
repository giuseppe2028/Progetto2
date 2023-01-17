package com.example.GestioneRemoto.Contenitori;

public class Timbratura {
    private int ref_impiegato,ref_turno;
    private String ref_tipo_turno,tipo;
    private boolean timbratura_effettuata;

    public Timbratura(int ref_impiegato,int ref_turno,String ref_tipo_turno,boolean timbratura_effettuata,String tipo){
        this.ref_impiegato=ref_impiegato;
        this.ref_turno=ref_turno;
        this.ref_tipo_turno=ref_tipo_turno;
        this.timbratura_effettuata=timbratura_effettuata;
        this.tipo=tipo;
    }
}
