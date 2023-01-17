package com.example.GestioneRemoto.Contenitori;

public class TimbraturaRemoto {
    private int ref_impiegato,ref_turno;
    private String ref_tipo_turno,motivazione;
    private boolean timbratura_effettuata;

    public TimbraturaRemoto(int ref_impiegato,int ref_turno,String ref_tipo_turno,boolean timbratura_effettuata,String motivazione){
        this.ref_impiegato=ref_impiegato;
        this.ref_turno=ref_turno;
        this.ref_tipo_turno=ref_tipo_turno;
        this.timbratura_effettuata=timbratura_effettuata;
        this.motivazione=motivazione;
    }
}
