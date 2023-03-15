/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
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

    @Override
    public void run() {
             while (true) {
            switch (nroCaja) {
                case 1 -> {
                    System.out.println("El " + Thread.currentThread().getName() + " de la caja " + nroCaja + " de la terminal " + terminal+" esta esperando a algun pasajero");
                    gestorFreeShop.cobrarFreeShopCaja1();
                    System.out.println("El " + Thread.currentThread().getName() + " esta cobrando en la caja " + nroCaja + " de la terminal " + terminal);
                }
                case 2 -> {
                    System.out.println("El " + Thread.currentThread().getName() + " de la caja " + nroCaja + " de la terminal " + terminal+" esta esperando a algun pasajero");
                    gestorFreeShop.cobrarFreeShopCaja2();
                    System.out.println("El " + Thread.currentThread().getName() + " esta cobrando en la caja " + nroCaja + " de la terminal " + terminal);
                }
            }
        }
    }
}
