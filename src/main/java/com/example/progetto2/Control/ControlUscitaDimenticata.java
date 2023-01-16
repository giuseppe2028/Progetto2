package com.example.progetto2.Control;

import com.example.progetto2.FileDiSistema.DatePicker;

import java.time.LocalDate;
import java.time.LocalTime;



public class ControlUscitaDimenticata {

    public ControlUscitaDimenticata(){
        LocalTime time = DatePicker.checkTime();
        LocalDate date = DatePicker.checkDate();
    }


}
