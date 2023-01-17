package com.example.GestioneRemoto.Contenitori;

import java.time.LocalTime;
import java.time.LocalDate;
public class Turno {

    private int id,ref_impiegato;

    private String tipo_turno;

    private LocalTime inizio_turno,fine_turmo;

    private LocalDate data_turno;

    public Turno (int ID,int ref_impiegato,String tipo_turno,LocalTime inizio_turno,LocalTime fine_turno,LocalDate data_turno){
        this.id=ID;
        this.ref_impiegato=ref_impiegato;
        this.tipo_turno=tipo_turno;
        this.inizio_turno=inizio_turno;
        this.fine_turmo=fine_turno;
        this.data_turno=data_turno;
    }
}
