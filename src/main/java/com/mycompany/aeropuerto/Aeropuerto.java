/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Maria Mendez
 * Legajo 61921
 * Profesorado en Informatica
 */
public class Aeropuerto {

    public static void main(String[] args) {

        int NUM_PASAJEROS = 10;
        int CAPACIDAD_PEOPLEMOVER = 5;
        int NUM_PUESTOS_INFORMES = 1;
        int NUM_AEROLINEAS = 3;
        Vuelos vuelos = new Vuelos(NUM_AEROLINEAS);
        GestorInformes gestorInformes = new GestorInformes(NUM_PUESTOS_INFORMES);
        GestorCheckin gestorCheckin = new GestorCheckin(NUM_AEROLINEAS);
        gestorCheckin.iniciarAtencion();
        GestorTransporte gestorTransporte = new GestorTransporte(CAPACIDAD_PEOPLEMOVER);
        GestorSalasEmbarque gestorSalaEmbarque = new GestorSalasEmbarque(vuelos);
        gestorSalaEmbarque.iniciarAtencion();
        Thread[] pasajeros = new Thread[NUM_PASAJEROS];
        for (int j = 0; j < NUM_PASAJEROS; j++) {
            vuelos.hacerReserva();
            pasajeros[j] = new Thread(new Pasajero(vuelos.reserva(), gestorInformes, gestorCheckin, gestorTransporte, gestorSalaEmbarque));
            pasajeros[j].setName("Pasajero" + (j + 1));
            pasajeros[j].start();
        }
        vuelos.comenzarEmbarque();
    }
}
