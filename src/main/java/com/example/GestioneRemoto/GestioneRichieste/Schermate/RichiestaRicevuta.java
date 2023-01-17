package com.example.GestioneRemoto.GestioneRichieste.Schermate;

import java.time.LocalDate;

public class RichiestaRicevuta {
    private int id,ref_impiegato,matricola_destinazione;
    private String categoria;
    private boolean stato;
    private String tipo_turno_origine,tipo_turno_destinazione;
    private LocalDate data_turno_origine,data_turno_destinazione;

    public RichiestaRicevuta(int id,int ref_impiegato,int matricola_destinazione,String categoria,boolean stato,String tipo_turno_origine,String tipo_turno_destinazione,LocalDate data_turno_origine,LocalDate data_turno_destinazione){
        this.id=id;
        this.ref_impiegato=ref_impiegato;
        this.matricola_destinazione=matricola_destinazione;
        this.categoria=categoria;
        this.stato=stato;
        this.tipo_turno_origine=tipo_turno_origine;
        this.tipo_turno_destinazione=tipo_turno_destinazione;
        this.data_turno_origine=data_turno_origine;
        this.data_turno_destinazione=data_turno_destinazione;
    }
}
