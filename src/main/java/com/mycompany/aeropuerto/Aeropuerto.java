/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
 */
public class Aeropuerto {

    public static void main(String[] args) {
        int cantPasajeros = 10;
        int cantPuestosChecking = 4;
        GestorInformes informes = new GestorInformes(cantPuestosChecking, cantPasajeros);
        GestorColaEspera colaEspera = new GestorColaEspera(cantPuestosChecking);
        
        Thread atencionInformes = new Thread(new AtencionInformes(informes));
        atencionInformes.setName("Atencion Informes");
        atencionInformes.start();
        
        Thread[] pasajeros = new Thread[cantPasajeros];
        for (int j = 0; j < cantPasajeros; j++) {
            pasajeros[j] = new Thread(new Pasajero(informes, colaEspera));
            pasajeros[j].setName("Pasajero" + (j + 1));
            pasajeros[j].start();
        }
    }
}
