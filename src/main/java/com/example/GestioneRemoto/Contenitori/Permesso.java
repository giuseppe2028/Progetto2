package com.example.GestioneRemoto.Contenitori;

import java.time.LocalDate;
import java.time.LocalTime;

public class Permesso {
    private int id,ref_impiegato;
    private boolean stato;
    private LocalDate data_inizio,data_fine;
    private LocalTime ora_inizio,ora_fine;

    public Permesso(int id,int ref_impiegato,boolean stato,LocalDate data_inizio,LocalDate data_fine,LocalTime ora_inizio,LocalTime ora_fine){
        this.id=id;
        this.ref_impiegato=ref_impiegato;
        this.stato=stato;
        this.data_inizio=data_inizio;
        this.data_fine=data_fine;
        this.ora_inizio=ora_inizio;
        this.ora_fine=ora_fine;
    }
}
