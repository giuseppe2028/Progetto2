package com.example.progetto2.FileDiSistema.Contenitori;

import java.time.LocalTime;

public class Turno {

    private int id;

    private String tipo_turno;

    private LocalTime inizio_turno,fine_turmo;

    public Turno (int ID,String tipo_turno,LocalTime inizio_turno,LocalTime fine_turno){
        this.id=ID;
        this.tipo_turno=tipo_turno;
        this.inizio_turno=inizio_turno;
        this.fine_turmo=fine_turno;
    }
}