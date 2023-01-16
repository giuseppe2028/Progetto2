package com.example.GestioneRemoto.GestioneTurni.Schermate;

import com.example.GestioneRemoto.Contenitori.PropostaTurno;
import com.example.GestioneRemoto.FileDiSistema.Daemon;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.*;

public class SchermataPianificazioneTurni {
    private TableColumn<String,String> tipoTurno;
    private TableColumn<String, String> dipendete;
    private TableColumn<String,Integer> servizio;
    private TableColumn<String, String> ruolo;
    @FXML
    private TableView<PropostaTurno> tabella;
    LocalDate dataAttuale;
    @FXML
    private Label ciao;
    @FXML
    private Button bottoneVedi;

    @FXML
    private Button b1;

    @FXML
    private Button b10;

    @FXML
    private Button b11;

    @FXML
    private Button b12;

    @FXML
    private Button b13;

    @FXML
    private Button b14;

    @FXML
    private Button b15;

    @FXML
    private Button b16;

    @FXML
    private Button b17;

    @FXML
    private Button b18;

    @FXML
    private Button b19;

    @FXML
    private Button b2;

    @FXML
    private Button b20;

    @FXML
    private Button b21;

    @FXML
    private Button b22;

    @FXML
    private Button b23;

    @FXML
    private Button b24;

    @FXML
    private Button b25;

    @FXML
    private Button b26;

    @FXML
    private Button b27;

    @FXML
    private Button b28;

    @FXML
    private Button b29;

    @FXML
    private Button b3;

    @FXML
    private Button b30;

    @FXML
    private Button b31;

    @FXML
    private Button b32;

    @FXML
    private Button b33;

    @FXML
    private Button b34;

    @FXML
    private Button b4;

    @FXML
    private Button b5;

    @FXML
    private Button b6;

    @FXML
    private Button b7;

    @FXML
    private Button b8;

    @FXML
    private Button b9;
    @FXML
    private Button b35;
    @FXML
    private Button b36;
    @FXML
    private Button b37;
    @FXML
    private Button b38;
    @FXML
    private Button b39;
    @FXML
    private Button b40;
    @FXML
    private Button b41;
    @FXML
    private Button b42;
    @FXML
    private Button b43;
    @FXML
    private ChoiceBox<String> selezionaMese;
    private String[] giorniSettimana = {"Gen", "Feb", "Mar", "Apr", "Mag", "Giu", "Lug", "Ago", "Set", "Ott", "Nov", "Dic"};
    private ObservableList<PropostaTurno> propostaTurniTabella;
    private Date first;
    /*TurnoImpiegato turnoImpiegato;

    @FXML
    private TableView<TurnoImpiegato> tabella;
    @FXML
    private TableColumn<TurnoImpiegato, Integer> turno;
    @FXML
    private TableColumn<TurnoImpiegato, String> dipendente;
    @FXML
    private TableColumn<TurnoImpiegato, Integer> servizio;
    @FXML
    private TableColumn<TurnoImpiegato, String> ruolo;
    ObservableList<TurnoImpiegato> turnoImpiegatoes;
*/

    private List<PropostaTurno> proposta;
    public SchermataPianificazioneTurni(List<PropostaTurno> proposta){
        this.proposta = proposta;
    }
    @FXML
    public void initialize() {
        //prendo la data corrente
        dataAttuale = LocalDate.now();

        //lavoro sul choiche box
        selezionaMese.getItems().add(giorniSettimana[dataAttuale.getMonthValue()-1]);
        selezionaMese.getItems().add(giorniSettimana[dataAttuale.getMonthValue()]);
        selezionaMese.getItems().add(giorniSettimana[dataAttuale.getMonthValue()+1]);





        //fare gli algoritmi
        /*
         Ho impostato su FXML tutti i bottoni, uno per ogni cella del calendario
         successivamente prendo il primo giorno da calendario con la classe calendar

         */
        //imposto i valori di default
        selezionaMese.setValue(giorniSettimana[dataAttuale.getMonthValue()-1]);
        //imposto il calendario di default
        visualizzaCalendarioBase(dataAttuale.getMonthValue()-1,dataAttuale.getYear());
        //setto la scelta dell'utente
        System.out.println("ciaooooooooooooooo");
        selezionaMese.setOnAction(this::visualizzaCalendario);
/*
        turnoImpiegatoes = FXCollections.observableArrayList();
        turnoImpiegatoes.add(new TurnoImpiegato(1,"Isabella Greco",1,"Impiegato"));
        turnoImpiegatoes.add(new TurnoImpiegato(1,"Marco Rossi", 2, "Amministrativo"));
        turnoImpiegatoes.add(new TurnoImpiegato(1,"Giacomo Neri", 2, "Amministrativo"));
        turnoImpiegatoes.add(new TurnoImpiegato(1,"Alice Verdi", 2, "Amministrativo"));
        turnoImpiegatoes.add(new TurnoImpiegato(2,"Gabriele Ciano", 2, "Amministrativo"));
        turnoImpiegatoes.add(new TurnoImpiegato(2,"Marco Russo", 2, "Amministrativo"));
        turnoImpiegatoes.add(new TurnoImpiegato(2,"Aldo di Pasquale", 2, "Amministrativo"));
        turnoImpiegatoes.add(new TurnoImpiegato(2,"Luisa Trapani", 2, "Amministrativo"));
        turno.setCellValueFactory(new PropertyValueFactory<>("Turno"));
        dipendente.setCellValueFactory(new PropertyValueFactory<>("Dipendente"));
        servizio.setCellValueFactory(new PropertyValueFactory<>("Servizio"));
        ruolo.setCellValueFactory(new PropertyValueFactory<>("Ruolo"));
        tabella.setItems(turnoImpiegatoes);

*/

    }

    private void visualizzaCalendario(ActionEvent actionEvent) {

        visualizzaCalendarioBase(valoreMese(selezionaMese.getValue())-1,dataAttuale.getYear());
        selezionaMese.setOnAction(this::visualizzaCalendario);
    }


    private int ottieniInizio(Date first){
        String caso  = first.toString().substring(0,3);
        switch (caso){
            case "Mon":
                return 0;
            case "Tue":
                return 1;
            case "Wed":
                return 2;
            case "Thu":
                return 3;
            case "Fri":
                return 4;
            case "Sat":
                return 5;
            case "Sun":
                return 6;
        }
        return 0;
    }

private void visualizzaCalendarioBase(int mese, int anno){

    ArrayList<Button> giorno = new ArrayList<>();
    giorno.add(b1);
    giorno.add(b2);
    giorno.add(b3);
    giorno.add(b4);
    giorno.add(b5);
    giorno.add(b6);
    giorno.add(b7);
    giorno.add(b8);
    giorno.add(b9);
    giorno.add(b10);
    giorno.add(b11);
    giorno.add(b12);
    giorno.add(b13);
    giorno.add(b14);
    giorno.add(b15);
    giorno.add(b16);
    giorno.add(b17);
    giorno.add(b18);
    giorno.add(b19);
    giorno.add(b20);
    giorno.add(b21);
    giorno.add(b22);
    giorno.add(b23);
    giorno.add(b24);
    giorno.add(b25);
    giorno.add(b26);
    giorno.add(b27);
    giorno.add(b28);
    giorno.add(b29);
    giorno.add(b30);
    giorno.add(b31);
    giorno.add(b32);
    giorno.add(b33);
    giorno.add(b34);
    giorno.add(b35);
    giorno.add(b36);
    giorno.add(b37);
    giorno.add(b38);
    giorno.add(b39);
    giorno.add(b40);
    giorno.add(b41);
    giorno.add(b42);
    giorno.add(b43);

    //è giusto che dia il mese corrente
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.MONTH, mese);
    calendar.set(Calendar.YEAR, anno);
    calendar.set(Calendar.DAY_OF_MONTH, 1);
     first = calendar.getTime();
    int daysInMonth = calendar.getActualMaximum(Calendar.DATE);
    System.out.println(first);


    //prendo il primo giorno del mese, passando l'il giorno, successivamente mi faccio tornare il numero di questo giorno
    //in modo tale da avere inizializzata la prima riga
    //successivamente da setto tutte le label con: giorno.get(ottieniInizio(first)+j-1).setText(String.valueOf(j));
    //adesso prendo l'inizio:
    for(int j = 1; j<=daysInMonth; j++){
        //ottieniInizio(first)+j
        giorno.get(ottieniInizio(first)+j-1).setText(String.valueOf(j));
        //setto le label precedneti e successive


    }
    for(int x = ottieniInizio(first)-1; x>=0; x--){
        giorno.get(x).setText("");
    }
    //se il mese è di 30 giorni faccio una cosa, altrimenti facciamo altro
    //quindi dal giorno di inizio, fino a quando finisce, allora io aggiungo la lunghezza del mese
    //todo non so perchè ma mi da errore
       /* for(int y = giorno.size()-1; y>daysInMonth;y--){
            giorno.get(daysInMonth).setText("");
        }*/


    //fixxare il fatto di eliminare i button dalla fine del calendario
    System.out.println(first.toString().substring(0,3));


}

    private int valoreMese(String mese) {
        switch (mese) {
            case "Gen":
                return 1;
            case "Feb":
                return 2;
            case "Mar":
                return 3;
            case "Apr":
                return 4;
            case "Mag":
                return 5;
            case "Giu":
                return 6;
            case "Lug":
                return 7;
            case "Ago":
                return 8;
            case "Set":
                return 9;
            case "Ott":
                return 10;
            case "Nov":
                return 11;
            case "Dic":
                return 12;

        }
        return 0;
    }
    @FXML
    //todo magari mettere l'alert??
    public void clickButtonb1(ActionEvent e){
visualizzaTabella(0,Integer.parseInt(b1.getText()));




    }
    @FXML
    public void clickButtonb2(ActionEvent e){
        visualizzaTabella(0,Integer.parseInt(b2.getText()));
    }
    @FXML
    public void clickButtonb3(ActionEvent e){
        visualizzaTabella(0,Integer.parseInt(b3.getText()));
    }
    @FXML
    public void clickButtonb4(ActionEvent e){
        visualizzaTabella(0,Integer.parseInt(b4.getText()));
    }
    @FXML
    public void clickButtonb5(ActionEvent e){
        visualizzaTabella(0,Integer.parseInt(b5.getText()));
    }
    @FXML
    public void clickButtonb6(ActionEvent e){
        visualizzaTabella(0,Integer.parseInt(b6.getText()));
    }
    @FXML
    public void clickButtonb7(ActionEvent e){
        visualizzaTabella(0,Integer.parseInt(b7.getText()));
    }
    @FXML
    public void clickButtonb8(ActionEvent e){
        visualizzaTabella(0,Integer.parseInt(b8.getText()));
    }
    @FXML
    public void clickButtonb9(ActionEvent e){
        visualizzaTabella(0,Integer.parseInt(b1.getText()));
    }
    @FXML
    public void clickButtonb10(ActionEvent e){
        visualizzaTabella(0,Integer.parseInt(b1.getText()));
    }
    @FXML
    public void clickButtonb11(ActionEvent e){
        visualizzaTabella(0,Integer.parseInt(b1.getText()));
    }
    @FXML
    public void clickButtonb12(ActionEvent e){
        // visualizzaTabella(11);
    }
    @FXML
    public void clickButtonb13(ActionEvent e){
        // visualizzaTabella(12);
    }
    @FXML
    public void clickButtonb14(ActionEvent e){
        //visualizzaTabella(13);
    }
    @FXML
    public void clickButtonb15(ActionEvent e){
        //visualizzaTabella(14);
    }
    @FXML
    public void clickButtonb16(ActionEvent e){
        //visualizzaTabella(15);
    }
    @FXML
    public void clickButtonb17(ActionEvent e){

    }
    @FXML
    public void clickButtonb18(ActionEvent e){

    }
    @FXML
    public void clickButtonb19(ActionEvent e){

    }
    @FXML
    public void clickButtonb20(ActionEvent e){

    }
    @FXML
    public void clickButtonb21(ActionEvent e){

    }
    @FXML
    public void clickButtonb22(ActionEvent e){

    }
    @FXML
    public void clickButtonb23(ActionEvent e){

    }
    @FXML
    public void clickButtonb24(ActionEvent e){

    }
    @FXML
    public void clickButtonb25(ActionEvent e){

    }
    @FXML
    public void clickButtonb26(ActionEvent e){

    }
    @FXML
    public void clickButtonb27(ActionEvent e){

    }
    @FXML
    public void clickButtonb28(ActionEvent e){

    }
    @FXML
    public void clickButtonb29(ActionEvent e){

    }
    @FXML
    public void clickButtonb30(ActionEvent e){

    }
    @FXML
    public void clickButtonb31(ActionEvent e){

    }
    @FXML
    public void clickButtonb32(ActionEvent e){

    }
    @FXML
    public void clickButtonb33(ActionEvent e){

    }
    @FXML
    public void clickButtonb34(ActionEvent e){

    }
    @FXML
    public void clickButtonb35(ActionEvent e){

    }
    @FXML
    public void clickButtonb36(ActionEvent e){

    }
    @FXML
    public void clickButtonb37(ActionEvent e){

    }
    @FXML
    public void clickButtonb38(ActionEvent e){

    }
    @FXML
    public void clickButtonb39(ActionEvent e){

    }
    @FXML
    public void clickButtonb40(ActionEvent e){

    }
    @FXML
    public void clickButtonb41(ActionEvent e){

    }
    @FXML
    public void clickButtonb42(ActionEvent e){

    }
    @FXML
    public void clickButtonb43(ActionEvent e){

    }

private void visualizzaTabella(int offset,int giornoconsiderato){
    System.out.println("GiornoConsiderato "+giornoconsiderato);
    propostaTurniTabella = FXCollections.observableArrayList();
    //scorro la lista e vedo quale oggetto ha il giorno selezionato
    for(int i = 0; i< proposta.size(); i++){
        System.out.println("giorno query "+ proposta.get(i).getDataTurno().getDayOfMonth());;
        if(proposta.get(i).getDataTurno().getDayOfMonth() == giornoconsiderato){
            System.out.println(proposta.get(i).getDataTurno().getDayOfMonth());
            propostaTurniTabella.add(proposta.get(i));
        }
    }

    tipoTurno.setCellValueFactory(new PropertyValueFactory<>("turno"));
    dipendete.setCellValueFactory(new PropertyValueFactory<>("Dipendente"));
    servizio.setCellValueFactory(new PropertyValueFactory<>("Servizio"));
    ruolo.setCellValueFactory(new PropertyValueFactory<>("Ruolo"));
    tabella.setItems(propostaTurniTabella);



}
}

