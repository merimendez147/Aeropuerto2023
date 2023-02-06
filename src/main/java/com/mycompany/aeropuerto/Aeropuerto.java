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
        int cantCajasFreeShop = 2;
        Guardia guardias[] = new Guardia[cantidadPuestosAtencion];//un guardia por cada puesto de atencion
        PuestoAtencion[] puestoAtencion = new PuestoAtencion[cantidadPuestosAtencion];
        for (int i = 0; i <= cantidadPuestosAtencion; i++) {
            puestoAtencion[i] = new PuestoAtencion(i);
            guardias[i] = new Guardia(puestoAtencion[i], i);
        }
        Thread[] pasajeros = new Thread[cantidadPasajeros];
        for (int i = 0; i <= cantidadPasajeros; i++) {
            int puestoAtencionAerolinea = (int)(Math.random()*cantidadPuestosAtencion);
            pasajeros[i] = new Thread(new Pasajero(puestoAtencionAerolinea));
            pasajeros[i].setName("Pasajero" + (i + 1));
            pasajeros[i].start();
        }
        System.out.println("Hello World!");
    }
}
