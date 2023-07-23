/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;
/**
 *
 * @author Maria Elisa Mendez Cares
 * Legajo: 61921
 * Carrera: Profesorado de Informatica
 * Email: maria.mendez@est.fi.uncoma.edu.ar
 */
public class Aeropuerto {

    public static void main(String[] args) {

        int NUM_PASAJEROS = 10;
        int CAPACIDAD_PEOPLEMOVER = 5;
        int NUM_PUESTOS_INFORMES = 1;
        int NUM_AEROLINEAS = 3;
        Vuelos vuelos = new Vuelos(NUM_AEROLINEAS, NUM_PASAJEROS);
        vuelos.crearReservas();//crea las reservas aleatoriamente para cada pasajero
        vuelos.comenzarEmbarque();
        GestorInformes gestorInformes = new GestorInformes(NUM_PUESTOS_INFORMES);
        GestorCheckin gestorCheckin = new GestorCheckin(NUM_AEROLINEAS);
        GestorTransporte gestorTransporte = new GestorTransporte(CAPACIDAD_PEOPLEMOVER);
        GestorSalasEmbarque gestorSalaEmbarque = new GestorSalasEmbarque(vuelos);
        gestorSalaEmbarque.iniciarAtencion();
        Thread[] atencionCheckin = new Thread[NUM_AEROLINEAS];
        for (int i = 0; i < NUM_AEROLINEAS; i++) {
            atencionCheckin[i] = new Thread(new AtencionCheckin(gestorCheckin, i));
            atencionCheckin[i].setName("Atencion Puesto" + i);
            atencionCheckin[i].start();
        }
        Thread[] pasajeros = new Thread[NUM_PASAJEROS];
        for (int j = 0; j < NUM_PASAJEROS; j++) {
            Reserva reserva=vuelos.hacerReserva();
            pasajeros[j] = new Thread(new Pasajero(reserva, gestorInformes, gestorCheckin, gestorTransporte, gestorSalaEmbarque));
            pasajeros[j].setName("Pasajero" + (j + 1));
            pasajeros[j].start();
        }
        for (int i = 0; i < NUM_AEROLINEAS; i++) {
            gestorSalaEmbarque.cerrarEmbarque(i);
        }
    }
}
