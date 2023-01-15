package com.example.progetto2.FileDiSistema;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.Date;

public class EntityUtente {
    private int matricola;
    private String nome;
    private String cognome;
    private String cf;
    private Date data_nascita;

    private Blob foto_profilo;
    private String indirizzo_residenza;
    private String ruolo;
    private String mail;
    private String password;
    private String IBAN;
    private long recapito_telefonico;
    private String mail_personale;


    public EntityUtente(ArrayList<Object> arrayList){
        this.matricola = (Integer) arrayList.get(0);
        this.nome = (String) arrayList.get(1);
        this.cognome = (String) arrayList.get(2);
        this.cf = (String) arrayList.get(3);
        this.data_nascita = (Date) arrayList.get(4);
        this.foto_profilo = (Blob) arrayList.get(5);
        this.indirizzo_residenza = (String) arrayList.get(6);
        this.ruolo = (String) arrayList.get(7);
        this.mail = (String) arrayList.get(8);
        this.password = (String) arrayList.get(9);
        this.IBAN = (String) arrayList.get(10);
        this.recapito_telefonico = (long) arrayList.get(11);
        this.mail_personale = (String) arrayList.get(12);

    }
    public  String toString(){
        String a = matricola + nome + cognome + cf + data_nascita + foto_profilo + indirizzo_residenza + ruolo + mail + password + IBAN + recapito_telefonico + mail_personale;
        return a;
    }
}
