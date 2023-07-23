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
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Maria Elisa Mendez Cares
 * Legajo: 61921
 * Carrera: Profesorado de Informatica
 * Email: maria.mendez@est.fi.uncoma.edu.ar
 */
public class Vuelos {

    CountDownLatch[] partidas;
    int[] cantReservasVuelo; //cada vuelo tiene una x cantidad de reservas
    boolean[] embarcando;
    Semaphore vueloEmbarcando[]; //los pasajeros esperan este semaforo para embarcar
    int cantidadAerolineas, cantidadPasajeros;
    List<String> aerolineas = new ArrayList<>();
    Reserva reserva;
    Reservas reservas;//pila para las reservas 
    Timer timer = new Timer();

    public Vuelos(int cantAerolineas, int cantPasajeros) {
        this.cantidadAerolineas = cantAerolineas;
        this.cantidadPasajeros = cantPasajeros;
        this.partidas = new CountDownLatch[cantidadAerolineas];
        this.cantReservasVuelo = new int[cantidadAerolineas];
        this.embarcando = new boolean[cantidadAerolineas];
        this.vueloEmbarcando = new Semaphore[cantidadAerolineas];
        this.reservas = new Reservas(cantidadPasajeros);
        aerolineas.add("Aerolíneas Argentinas");
        aerolineas.add("LAN");
        aerolineas.add("American Airlines");
        for (int i = 0; i < cantidadAerolineas; i++) {
            cantReservasVuelo[i] = 0;
            embarcando[i] = false;
            vueloEmbarcando[i] = new Semaphore(0);
        }
    }

    public String aerolinea(int nroAerolinea) {
        return aerolineas.get(nroAerolinea);
    }

    public int cantAerolineas() {
        return this.cantidadAerolineas;
    }

    public boolean embarcando(int nroAerolinea) {
        return embarcando[nroAerolinea];
    }

    public Reserva reserva() {
        return this.reserva;
    }

    public void crearReservas() {
        for (int i = 1; i <= cantidadPasajeros; i++) {
            int nroAerolinea = new Random().nextInt(cantidadAerolineas);
            reserva = new Reserva(nroAerolinea, aerolineas.get(nroAerolinea));
            reservas.push(reserva);
            cantReservasVuelo[nroAerolinea]++;
        }
    }

    public Reserva hacerReserva() {//un pasajero hace una reserva
        return reservas.pop();
    }

    private int cantReservas(int aerolinea) {
        return this.cantReservasVuelo[aerolinea];
    }

    public void comenzarEmbarque() {
        for (int i = 0; i < cantidadAerolineas; i++) {
            partidas[i] = new CountDownLatch(cantReservas(i));
        }
        TimerTask vuelo1 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("El vuelo de " + aerolinea(0) + " llama a embarcar a los " + cantReservas(0) + " pasajeros");
                embarcando[0] = true;
                vueloEmbarcando[0].release(cantReservas(0));
            }
        };
        TimerTask vuelo2 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("El vuelo de " + aerolinea(1) + " llama a embarcar a los " + cantReservas(1) + " pasajeros");
                embarcando[1] = true;
                vueloEmbarcando[1].release(cantReservas(1));
            }
        };
        TimerTask vuelo3 = new TimerTask() {
            @Override
            public void run() {
                System.out.println("El vuelo de " + aerolinea(2) + " llama a embarcar a los " + cantReservas(2) + " pasajeros");
                embarcando[2] = true;
                vueloEmbarcando[2].release(cantReservas(2));
                timer.cancel();
            }
        };
        // Partida de los vuelos en los tiempos específicos
        timer.schedule(vuelo1, 15000);
        timer.schedule(vuelo2, 20000);
        timer.schedule(vuelo3, 30000);
    }

    public void embarcar(int nroAerolinea) { //el pasajero esta en la sala de embarque
        CountDownLatch partida = partidas[nroAerolinea];
        synchronized (partida) {
            try {
                vueloEmbarcando[nroAerolinea].acquire();
            } catch (InterruptedException ex) {
                Logger.getLogger(Vuelos.class.getName()).log(Level.SEVERE, null, ex);
            }
            partida.countDown();
        }
    }

    public void cerrarEmbarque(int nroAerolinea) {//gestorSalaEmbarque cierra el embarque
        try {
            partidas[nroAerolinea].await();
        } catch (InterruptedException ex) {
            Logger.getLogger(Vuelos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("El vuelo de " + aerolinea(nroAerolinea) + " termino de embarcar ");
    }

}
