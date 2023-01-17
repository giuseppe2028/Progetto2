package com.example.GestioneRemoto.Contenitori;

import java.sql.Blob;
import java.time.LocalDate;
import java.time.LocalTime;



    public class Richieste {

        private int id,ref_impiegato,matricola_destinazione;
        private boolean stato;
        private LocalDate data_inizio,data_fine,data_turno_origine,data_turno_destinazione;
        private LocalTime ora_inizio,ora_fine;
        private String svolgimento,motivazione,tipo,categoria,tipo_turno_origine,tipo_turno_destinazione;
        private Blob allegato;

        public Richieste (int id,int ref_impiegato,int matricola_destinazione,boolean stato,LocalDate data_inizio,LocalDate data_fine,LocalDate data_turno_origine,LocalDate data_turno_destinazione,LocalTime ora_inizio,LocalTime ora_fine,String svolgimento,String motivazione,String tipo,String categoria,String tipo_turno_origine,String tipo_turno_destinazione,Blob allegato){
            this.id=id;
            this.ref_impiegato=ref_impiegato;
            this.matricola_destinazione=matricola_destinazione;
            this.stato=stato;
            this.data_inizio=data_inizio;
            this.data_fine=data_fine;
            this.data_turno_origine=data_turno_origine;
            this.data_turno_destinazione=data_turno_destinazione;
            this.ora_inizio=ora_inizio;
            this.ora_fine=ora_fine;
            this.svolgimento=svolgimento;
            this.motivazione=motivazione;
            this.tipo=tipo;
            this.categoria=categoria;
            this.tipo_turno_origine=tipo_turno_origine;
            this.tipo_turno_destinazione=tipo_turno_destinazione;
            this.allegato=allegato;
        }
    }

