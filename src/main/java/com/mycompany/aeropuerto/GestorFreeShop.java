/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Elisa Mendez Cares Legajo: 61921 Carrera: Profesorado de
 * Informatica Email: maria.mendez@est.fi.uncoma.edu.ar
 */
public class GestorFreeShop {
    
    int capacidadFreeShop = 20;
    Semaphore ingresarFreeShop;//controla la cantidad de pasajeros que pueden ingresar al FreeShop
    Semaphore pagarCaja[]; //rendevous con esperarCaja
    Semaphore esperarCaja[];//rendevous con pagarCaja
    int cantCajas = 2;

    public GestorFreeShop() {
        ingresarFreeShop = new Semaphore(capacidadFreeShop, true);
        pagarCaja = new Semaphore[cantCajas];
        esperarCaja = new Semaphore[cantCajas];
        for (int i = 0; i < cantCajas; i++) {
            pagarCaja[i] = new Semaphore(1, true);
            esperarCaja[i] = new Semaphore(0, true);
        }
    }

    public void ingresarFreeShop() {
        try {
            ingresarFreeShop.acquire();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public int cajaLibre() {
        if (pagarCaja[0].tryAcquire()) {
            return 0;
        } else {
            return 1;
        }
    }

    public void pagarCaja(int nroCaja) {
        try {
            pagarCaja[nroCaja].acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorFreeShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        esperarCaja[nroCaja].release();
    }

    public void salirFreeShop() {
        ingresarFreeShop.release();
    }

    public void esperarCliente(int nroCaja) {
        try {
            esperarCaja[nroCaja].acquire();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void cobrarCaja(int nroCaja) {
        pagarCaja[nroCaja].release();
    }
}
