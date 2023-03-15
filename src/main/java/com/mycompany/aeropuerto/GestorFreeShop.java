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
 * @author Academica
 */
public class GestorFreeShop {

    int capacidadFreeShop = 20;
    Semaphore ingresarFreeShop;
    Semaphore pagarCaja1;
    Semaphore esperarCaja1;
    Semaphore pagarCaja2;
    Semaphore esperarCaja2;

    public GestorFreeShop() {
        ingresarFreeShop = new Semaphore(capacidadFreeShop, true);
        pagarCaja1 = new Semaphore(1, true);
        esperarCaja1 = new Semaphore(0, true);
        pagarCaja2 = new Semaphore(1, true);
        esperarCaja2 = new Semaphore(0, true);
    }

    public void ingresarFreeShop() {
        try {
            ingresarFreeShop.acquire();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void pagarCaja1() {
        try {
            pagarCaja1.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorFreeShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        esperarCaja1.release();
    }

    public void pagarCaja2() {
        try {
            pagarCaja2.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorFreeShop.class.getName()).log(Level.SEVERE, null, ex);
        }
        esperarCaja2.release();
    }

    public void salirFreeShop() {
        ingresarFreeShop.release();
    }

    public void cobrarFreeShopCaja1() {
        try {
            esperarCaja1.acquire();
            pagarCaja1.release();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    public void cobrarFreeShopCaja2() {
        try {
            esperarCaja2.acquire();
            pagarCaja2.release();
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
