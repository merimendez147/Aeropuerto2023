/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Mendez
 * Legajo 61921
 * Profesorado en Informatica
 */
public class Vuelos {

    Semaphore[] semaforoReservasVuelos; //semaforo mutex para sumar cantidad de reservas por vuelo
    int[] cantReservasVuelo;
    boolean[] embarcando;
    int cantidadAerolineas;
    List<String> aerolineas = new ArrayList<>();
    Reserva reserva;
    Timer timer = new Timer();

    public Vuelos(int numAerolineas) {
        this.cantidadAerolineas = numAerolineas;
        this.semaforoReservasVuelos = new Semaphore[cantidadAerolineas];
        this.cantReservasVuelo = new int[cantidadAerolineas];
        this.embarcando = new boolean[cantidadAerolineas];
        aerolineas.add("Aerolíneas Argentinas");
        aerolineas.add("LAN");
        aerolineas.add("American Airlines");
        for (int i = 0; i < cantidadAerolineas; i++) {
            semaforoReservasVuelos[i] = new Semaphore(1);
            cantReservasVuelo[i] = 0;
            embarcando[i] = false;
        }
    }

    public String aerolinea(int indice) {
        return aerolineas.get(indice);
    }

    public int cantAerolineas(){
        return this.cantidadAerolineas;
    }
    
    public boolean embarcando(int indice){
        return embarcando[indice];
    }
    public Reserva reserva() {
        return this.reserva;
    }

    public void hacerReserva() {
        int indice = new Random().nextInt(cantidadAerolineas);
        try {
            semaforoReservasVuelos[indice].acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Vuelos.class.getName()).log(Level.SEVERE, null, ex);
        }
        reserva = new Reserva(indice, aerolineas.get(indice));
        cantReservasVuelo[indice]++;
        semaforoReservasVuelos[indice].release();
    }

    public int cantReservas(int vuelo) {
        int r = 0;
        try {
            semaforoReservasVuelos[vuelo].acquire();
            r = this.cantReservasVuelo[vuelo];
        } catch (InterruptedException ex) {
            Logger.getLogger(Vuelos.class.getName()).log(Level.SEVERE, null, ex);
        }
        semaforoReservasVuelos[vuelo].release();
        return r;
    }

    public void comenzarEmbarque() {
        TimerTask vuelo1 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Embarcando vuelo de " +aerolinea(0) + " con " + cantReservas(0) + " pasajeros");
                 embarcando[0] = true;
            }
        };

        TimerTask vuelo2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Embarcando vuelo de " + aerolinea(1) + " con " + cantReservas(1) + " pasajeros");
                embarcando[1] = true;
            }
        };

        TimerTask vuelo3 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Embarcando vuelo de " + aerolinea(2) + " con " + cantReservas(2) + " pasajeros");
                embarcando[2] = true;
                timer.cancel();
            }
        };

        // Partida de los vuelos en los tiempos específicos
        timer.schedule(vuelo1, 15000);
        timer.schedule(vuelo2, 20000);
        timer.schedule(vuelo3, 30000);
    }
}
