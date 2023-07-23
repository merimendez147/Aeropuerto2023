/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.Random;

/**
 *
 * @author Maria Elisa Mendez Cares Legajo: 61921 Carrera: Profesorado de
 * Informatica Email: maria.mendez@est.fi.uncoma.edu.ar
 */
public class Pasajero implements Runnable {

    Reserva reserva; //instancia de la clase reserva que almacena el nombre de la aerolinea, el puesto de checkin y la sala de embarque corresondiente 
    GestorInformes gestorinformes; //  instancia de la clase GestorInformes, que se utiliza para solicitar información y asignar un puesto de checking al pasajero.
    int nroPuesto;// variable que almacena el número de puesto de checking asignado al pasajero.
    GestorCheckin gestorCheckin; // instancia de la clase GestorChecking, que se utiliza para hacer cola en el puesto de checking, solicitar el proceso de checking y obtener la terminal asignada al pasajero.
    char terminal;// variable que almacena la terminal asignada al pasajero.
    GestorTransporte gestorTransporte; // instancia de la clase GestorTransporte, que se utiliza para subir y bajar del tren que lleva a los pasajeros a la terminal.
    GestorSalasEmbarque gestorSalaEmbarque; // instancia de la clase GestorSalasEmbarque, que se utiliza para entrar y salir del FreeShop (en caso de que el pasajero decida visitarlo), esperar el embarque y embarcarse.

    public Pasajero(Reserva reserva, GestorInformes gestorInformes, GestorCheckin gestorCheckin, GestorTransporte gestorTransporte, GestorSalasEmbarque gestorSalaEmb) {
        this.reserva = reserva;
        this.gestorinformes = gestorInformes;
        this.gestorCheckin = gestorCheckin;
        this.gestorTransporte = gestorTransporte;
        this.gestorSalaEmbarque = gestorSalaEmb;
    }

    public String nombre() {
        return Thread.currentThread().getName();
    }

    public Reserva reserva() {
        return this.reserva;
    }

    @Override
    public void run() {
        System.out.println("El " + Thread.currentThread().getName() + " llega al Aeropuerto");
        gestorinformes.solicitarAtencion();
        nroPuesto = gestorinformes.consultarPuesto(reserva);
        gestorCheckin.hacerChecking(nroPuesto);
        terminal = reserva.terminal();
        System.out.println("El " + Thread.currentThread().getName() + " hizo el Checkin en el puesto " + nroPuesto + " tiene que ir a la Terminal " + terminal);
        gestorTransporte.subirTren(terminal);
        gestorTransporte.bajarTren(terminal);
        Random ingresarFreeShop = new Random();
        boolean ingresaFreeShop = ingresarFreeShop.nextBoolean();
        int nroAerolinea = nroPuesto;
        if (ingresaFreeShop && !gestorSalaEmbarque.vueloEmbarcando(nroAerolinea)) {
            gestorSalaEmbarque.ingresarFreeShop(terminal);
            System.out.println("El " + Thread.currentThread().getName() + " ingresa al FreeShop  de la terminal  " + terminal);
            Random comprarFreeShop = new Random();
            if (comprarFreeShop.nextBoolean()) {
                System.out.println("El " + Thread.currentThread().getName() + " hizo compras en el FreeShop  de la terminal  " + terminal);
                gestorSalaEmbarque.pagarFreeShop(terminal);
            }
            gestorSalaEmbarque.salirFreeShop(terminal);
            System.out.println(Thread.currentThread().getName() + " salio del FreeShop de la Terminal " + terminal);
        } else {
            if (!ingresaFreeShop) {
                System.out.println("El " + Thread.currentThread().getName() + " no quiso ingresar al FreeShop");
            } else if (gestorSalaEmbarque.vueloEmbarcando(nroAerolinea)) {
                System.out.println("El " + Thread.currentThread().getName() + " no pudo ingresar al FreeShop  porque el vuelo de " + gestorSalaEmbarque.aerolinea(nroPuesto) + " esta embarcando");
            }
        }
        System.out.println("El " + Thread.currentThread().getName() + " proximo a embarcar en la terminal  " + terminal);
        gestorSalaEmbarque.embarcar(nroAerolinea);
        System.out.println("El " + Thread.currentThread().getName() + " subio al vuelo de la aerolinea  " + gestorSalaEmbarque.aerolinea(nroPuesto));
    }
}
