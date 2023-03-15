/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.Random;

/**
 *
 * @author Maria Elisa Mendez Cares - Legajo 61921
 */
public class Pasajero implements Runnable {

    GestorInformes informes; // una instancia de la clase GestorInformes, que se utiliza para solicitar información y asignar un puesto de checking al pasajero.
    int puestoChecking;//una variable que almacena el número de puesto de checking asignado al pasajero.
    GestorChecking checking; //una instancia de la clase GestorChecking, que se utiliza para hacer cola en el puesto de checking, solicitar el proceso de checking y obtener la terminal asignada al pasajero.
    char terminal;//una variable que almacena la terminal asignada al pasajero.
    GestorTransporte gestorTransporte; //una instancia de la clase GestorTransporte, que se utiliza para subir y bajar del tren que lleva a los pasajeros a la terminal.
    GestorSalasEmbarque gestorSalaEmbarque; //una instancia de la clase GestorSalasEmbarque, que se utiliza para entrar y salir del FreeShop (en caso de que el pasajero decida visitarlo), esperar el embarque y embarcarse.

    public Pasajero(GestorInformes informes, GestorChecking colaEspera, GestorTransporte gestorT, GestorSalasEmbarque gSE) {
        this.informes = informes;
        this.checking = colaEspera;
        this.gestorTransporte = gestorT;
        gestorSalaEmbarque = gSE;
    }

    public String nombre() {
        return Thread.currentThread().getName();
    }

    @Override
    public void run() {
        System.out.println("El " + Thread.currentThread().getName() + " llega al Aeropuerto");
        informes.solicitarAtencionInformes();
        puestoChecking = informes.consultarPuestoChecking();
        System.out.println("El pasajero " + Thread.currentThread().getName() + " tiene que ir al puesto de Checking " + puestoChecking);
        checking.hacerColaChecking(this, puestoChecking); //inserta al pasajero en la cola del puesto de Checking
        checking.solicitarChecking(puestoChecking);
        terminal = checking.hacerChecking(puestoChecking);
        System.out.println("El " + Thread.currentThread().getName() + " hizo el Checking en el puesto " + puestoChecking + " tiene que ir a la Terminal " + terminal);
        gestorTransporte.subirTren(terminal);
        gestorTransporte.bajarTren(terminal);
        Random ingresarFreeShop = new Random();
        if (ingresarFreeShop.nextBoolean()) {
            gestorSalaEmbarque.ingresarFreeShop(terminal);       
            System.out.println("El " + Thread.currentThread().getName() + " ingresa al FreeShop  de la terminal  " + terminal);
            Random comprarFreeShop = new Random();
            if (comprarFreeShop.nextBoolean()) {
                System.out.println("El " + Thread.currentThread().getName() + " hizo compras en el FreeShop  de la terminal  " + terminal);
                gestorSalaEmbarque.pagarFreeShop(terminal);
            }
            gestorSalaEmbarque.salirFreeShop(terminal);
             System.out.println(Thread.currentThread().getName() + " salio del Free Shop de la Terminal " + terminal);
        }
        else {
            System.out.println("El " + Thread.currentThread().getName() + " no ingresa al FreeShop  de la terminal  " + terminal);
        }
        System.out.println("El " + Thread.currentThread().getName() + " proximo a embarcar en la terminal  " + terminal);
        gestorSalaEmbarque.esperarEmbarque(terminal);
        gestorSalaEmbarque.embarcar(terminal);
    }
}
