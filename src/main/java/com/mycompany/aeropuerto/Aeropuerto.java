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
        int cantPasajeros = 100;
        int cantPuestosChecking = 4;
        GestorInformes informes;
        GestorColaEspera colaEspera;
        GestorChecking checking;
        Thread atencionInformes = new Thread(new AtencionInformes(informes));
        Thread[] atencionChecking = new Thread[cantPuestosChecking];
        Thread[] guardias = new Thread[cantPuestosChecking];
        for (int i = 0; i <= cantPuestosChecking; i++) {
            guardias[i] = new Thread(new Guardia(colaEspera));
            atencionChecking[i] = new Thread(new AtencionChecking(colaEspera));
        }
        Thread[] pasajeros = new Thread[cantPasajeros];
        for (int i = 0; i <= cantPuestosChecking; i++) {
            pasajeros[i] = new Thread(new Pasajero(informes, colaEspera, checking));
        }
    }
}
