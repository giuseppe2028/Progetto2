package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import java.time.LocalDate;

public class Richiesta{
    private int id,ref_impiegato;
    private boolean stato;
    private LocalDate data_inizio,data_fine;

    public Richiesta(int id,int ref_impiegato,boolean stato,LocalDate data_inizio,LocalDate data_fine){
        this.id=id;
        this.ref_impiegato=ref_impiegato;
        this.stato=stato;
        this.data_inizio=data_inizio;
        this.data_fine=data_fine;
    }

    public int getID_richiesta() {
        return id;
    }
}
