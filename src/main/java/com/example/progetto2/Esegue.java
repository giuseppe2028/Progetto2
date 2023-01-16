package com.example.progetto2;

import java.time.LocalDate;
import java.time.LocalTime;

public class Esegue {
    private int ref_impiegato_timbratura,ref_turno_timbratura;
    private String ref_tipo_turno_timbratura,tipologia;
    private LocalDate data_timbratura;
    private LocalTime orario_timbratura;

    public Esegue(int ref_impiegato_timbratura,int ref_turno_timbratura,String ref_tipo_turno_timbratura,String tipologia,LocalDate data_timbratura,LocalTime orario_timbratura){
        this.ref_impiegato_timbratura=ref_impiegato_timbratura;
        this.ref_turno_timbratura=ref_turno_timbratura;
        this.ref_tipo_turno_timbratura=ref_tipo_turno_timbratura;
        this.tipologia=tipologia;
        this.data_timbratura=data_timbratura;
        this.orario_timbratura=orario_timbratura;
    }
    public String toString(){
        return Integer.toString(this.ref_impiegato_timbratura);
    }
}
