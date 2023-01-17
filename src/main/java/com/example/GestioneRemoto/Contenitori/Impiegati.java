package com.example.GestioneRemoto.Contenitori;

import java.time.LocalDate;
import java.sql.*;

public class Impiegati {

    private int matricola,ref_servizio;
    private String nome,cognome,sesso,cf,indirizzo_residenza,mail_personale,iban,mail,password,ruolo;
    private LocalDate data_nascita,inizio_servizio,fine_servizio;
    private long recapito_telefonico;
    private boolean reperibile,disattivato;
    private Blob foto_profilo;

    public Impiegati(int matricola,String nome,String cognome,String sesso,String cf,LocalDate data_nascita,String indirizzo_residenza,long recapito_telefonico,String mail_personale,String iban,String mail,String password,String ruolo,boolean reperibile,int ref_servizio,LocalDate inizio_servizio,LocalDate fine_servizio,boolean disattivato,Blob foto_profilo){
        this.matricola=matricola;
        this.nome=nome;
        this.cognome=cognome;
        this.sesso=sesso;
        this.cf=cf;
        this.data_nascita=data_nascita;
        this.indirizzo_residenza=indirizzo_residenza;
        this.recapito_telefonico=recapito_telefonico;
        this.mail_personale=mail_personale;
        this.iban=iban;
        this.mail=mail;
        this.password=password;
        this.ruolo=ruolo;
        this.reperibile=reperibile;
        this.ref_servizio=ref_servizio;
        this.inizio_servizio=inizio_servizio;
        this.fine_servizio=fine_servizio;
        this.disattivato=disattivato;
        this.foto_profilo=foto_profilo;
    }
}
