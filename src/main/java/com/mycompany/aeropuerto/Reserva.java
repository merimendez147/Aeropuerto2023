/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Academica
 */
public class Reserva {

    int NUM_AEROLINEAS, NUM_PUESTOSCHECKING, NUM_TERMINALES = 3;
    String aerolinea;
    char terminal;
    int puestoChecking;

    public void hacerReserva() {
        List<String> aerolineas = new ArrayList<>();
        aerolineas.add("Aerolíneas Argentinas");
        aerolineas.add("LAN");
        aerolineas.add("American Airlines");
        int indice = new Random().nextInt(NUM_AEROLINEAS);
        aerolinea = aerolineas.get(indice);
        switch (indice) {
            case 1 ->
                terminal = 'A';
            case 2 ->
                terminal = 'B';
            case 3 ->
                terminal = 'C';
        }
        puestoChecking = indice;
    }

    public String aerolinea() {
        return this.aerolinea;
    }

    public char terminal() {
        return this.terminal;
    }

    public int puestoChecking() {
        return this.puestoChecking;
    }
}
