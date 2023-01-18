package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import java.time.LocalDate;

public class GiorniFerie extends Richiesta{

    private int id,ref_impiegato;

    private boolean stato;
    private LocalDate data_inizio,data_fine;

    public GiorniFerie (int id,int ref_impiegato,boolean stato,LocalDate data_inizio,LocalDate data_fine){
        super(id,ref_impiegato,stato);
        this.data_inizio=data_inizio;
        this.data_fine=data_fine;

    }
    @Override
    public String toString(){
        String a= Integer.toString(id)+stato+data_inizio+data_fine;
        return a;
    }
}
