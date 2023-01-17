package com.example.GestioneRemoto.Contenitori;

import java.time.LocalDate;

public class Periodi {
    private int id,ref_datore;
    private String categoria;
    private LocalDate data_inizio,data_fine;

    public Periodi(int id,int ref_datore,String categoria,LocalDate data_inizio,LocalDate data_fine){
        this.id=id;
        this.ref_datore=ref_datore;
        this.categoria=categoria;
        this.data_inizio=data_inizio;
        this.data_fine=data_fine;
    }
}
