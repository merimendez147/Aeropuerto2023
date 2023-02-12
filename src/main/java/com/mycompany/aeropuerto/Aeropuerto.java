/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
 */
public class Aeropuerto {

    public static void main(String[] args) {
        int cantidadPuestosAtencion = 4;
        int cantidadPasajeros = 100;
        int cantTerminales = 4;
        int cantCajasFreeShop = 3;
        PuestoInformes puestoInformes= new PuestoInformes(cantidadPuestosAtencion);
        ZonaCheckIn zonaCheckIn = new ZonaCheckIn(cantidadPuestosAtencion);
        Tren tren = new Tren();
        FreeShop freeShop = new FreeShop(cantCajasFreeShop);
        PuestoEmbarque puestoEmbarque = new PuestoEmbarque();
      
        Thread[] pasajeros = new Thread[cantidadPasajeros];
        for (int i = 0; i <= cantidadPasajeros; i++) {
            pasajeros[i] = new Thread(new Pasajero(puestoInformes, zonaCheckIn, tren, freeShop, puestoEmbarque));
            pasajeros[i].setName("Pasajero" + (i + 1));
            pasajeros[i].start();
        }
        System.out.println("Hello World!");
    }
}
