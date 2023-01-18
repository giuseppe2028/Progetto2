package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import java.time.LocalDate;
import java.time.LocalTime;

public class Permesso extends Richiesta {
    private int id,ref_impiegato;
    private boolean stato;
    private LocalDate data_inizio,data_fine;
    private LocalTime ora_inizio,ora_fine;

    public Permesso(int id,int ref_impiegato,boolean stato,LocalDate data_inizio,LocalDate data_fine,LocalTime ora_inizio,LocalTime ora_fine){
        super(id,ref_impiegato,stato);
        this.data_inizio=data_inizio;
        this.data_fine=data_fine;
        this.ora_inizio=ora_inizio;
        this.ora_fine=ora_fine;
    }
    @Override
    public String toString(){
        String a= Integer.toString(id)+ stato+data_inizio+data_fine+ora_inizio+ora_fine;
        return a;
    }
}
