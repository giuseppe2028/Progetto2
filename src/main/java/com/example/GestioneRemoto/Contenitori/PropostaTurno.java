package com.example.GestioneRemoto.Contenitori;

import java.time.LocalDate;
import java.time.LocalTime;

public class PropostaTurno {


    private int id;
    private String cognome;
    private String tipoTurno;
    private int servizio;
    private String ruolo;
    private LocalDate dataTurno;

    public PropostaTurno(int id,String tipoTurno, String cognome,int refServizio,String ruolo ,LocalDate dataTurno){

        this.id = id;
        this.dataTurno = dataTurno;
        this.cognome = cognome;
        this.servizio = refServizio;
        this.ruolo = ruolo;
        this.tipoTurno = tipoTurno;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getTipoTurno() {
        return tipoTurno;
    }

    public void setTipoTurno(String tipoTurno) {
        this.tipoTurno = tipoTurno;
    }



    public LocalDate getDataTurno() {
        return dataTurno;
    }

    public void setDataTurno(LocalDate dataTurno) {
        this.dataTurno = dataTurno;
    }

    public int getServizio() {
        return servizio;
    }

    public void setServizio(int servizio) {
        this.servizio = servizio;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }
    @Override
    public String toString(){
        return this.ruolo + this.cognome;
    }
}
