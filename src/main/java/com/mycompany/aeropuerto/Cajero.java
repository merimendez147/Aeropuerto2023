/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Elisa Mendez Cares 
 * Legajo: 61921 
 * Carrera: Profesorado de Informatica 
 * Email: maria.mendez@est.fi.uncoma.edu.ar
 */
public class Cajero implements Runnable {

    GestorFreeShop gestorFreeShop;
    char terminal;
    int nroCaja;

    public Cajero(GestorFreeShop gestorFreeShop, char terminal, int nroCaja) {
        this.terminal = terminal;
        this.nroCaja = nroCaja;
        this.gestorFreeShop = gestorFreeShop;
    }

    private void cobrar() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cajero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("El " + Thread.currentThread().getName() + " de la caja " + nroCaja + " de la terminal " + terminal + " esta esperando a algun pasajero");
            gestorFreeShop.esperarCliente(nroCaja);
            System.out.println("El " + Thread.currentThread().getName() + " esta cobrando en la caja " + nroCaja + " de la terminal " + terminal);
            cobrar();
            gestorFreeShop.cobrarCaja(nroCaja);
        }
    }
}
