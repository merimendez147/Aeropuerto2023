/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.HashMap;

/**
 *
 * @author Maria Elisa Mendez Cares Legajo: 61921 Carrera: Profesorado de
 * Informatica Email: maria.mendez@est.fi.uncoma.edu.ar
 */
public class GestorSalasEmbarque {

    GestorFreeShop[] gestorFreeShop;
     HashMap<Character, Integer> terminales = new HashMap<>();// Un hashmap que asocia cada terminal con un índice numérico (0, 1 o 2).
    Thread[] cajero1;
    Thread[] cajero2;
    int cantTerminales;
    Vuelos vuelos;

    public GestorSalasEmbarque(Vuelos vuelos) {
        this.vuelos = vuelos;
        this.cantTerminales = vuelos.cantidadAerolineas;
        gestorFreeShop = new GestorFreeShop[cantTerminales];
        cajero1 = new Thread[cantTerminales];
        cajero2 = new Thread[cantTerminales];
        terminales.put('A', 0);
        terminales.put('B', 1);
        terminales.put('C', 2);
    }

    public void iniciarAtencion() {
        char terminal = 65;
        for (int i = 0; i < cantTerminales; i++) {
            gestorFreeShop[i] = new GestorFreeShop();
            cajero1[i] = new Thread(new Cajero(gestorFreeShop[i], terminal, 0));
            cajero1[i].setName("Cajero1 terminal " + terminal);
            cajero1[i].start();
            cajero2[i] = new Thread(new Cajero(gestorFreeShop[i], terminal, 1));
            cajero2[i].setName("Cajero2 terminal " + terminal);
            cajero2[i].start();
            terminal++;
        }
    }

    public String aerolinea(int nroAerolinea) {
        return vuelos.aerolinea(nroAerolinea);
    }

    public boolean vueloEmbarcando(int nroAerolinea) {
        return vuelos.embarcando(nroAerolinea);
    }

    public void cerrarEmbarque(int nroAerolinea) {
        vuelos.cerrarEmbarque(nroAerolinea);
    }

    public void embarcar(int nroAerolinea) {
        vuelos.embarcar(nroAerolinea);
    }


    public void ingresarFreeShop(char terminal) {
        gestorFreeShop[terminales.get(terminal)].ingresarFreeShop();
    }

    public void salirFreeShop(char terminal) {
        gestorFreeShop[terminales.get(terminal)].salirFreeShop();
    }

    public void pagarFreeShop(char terminal) {
        int cajaLibre = (int) (Math.random() * 1);
        System.out.println(Thread.currentThread().getName() + " quiere pagar en la caja " + cajaLibre);
        gestorFreeShop[terminales.get(terminal)].pagarCaja(cajaLibre);
    }
}
