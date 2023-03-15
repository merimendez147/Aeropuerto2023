/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.Random;

/**
 *
 * @author Academica
 */
public class GestorSalasEmbarque {

    GestorFreeShop[] gestorFreeShop;
    GestorEmbarque[] gestorEmbarque;
    int cantTerminales;

   public GestorSalasEmbarque(int cantT) {
    this.cantTerminales = cantT;
    gestorFreeShop = new GestorFreeShop[cantTerminales];
    gestorEmbarque = new GestorEmbarque[cantTerminales];
    char terminal = 65;
    for (int i = 0; i < cantTerminales; i++) {
        gestorFreeShop[i] = new GestorFreeShop();
        Thread cajero1 = new Thread(new Cajero(gestorFreeShop[i], terminal, 1));
        cajero1.setName("Cajero1"+terminal);
        cajero1.start();
        Thread cajero2 = new Thread(new Cajero(gestorFreeShop[i], terminal, 2));
        cajero2.setName("Cajero2"+terminal);
        cajero2.start();
        gestorEmbarque[i] = new GestorEmbarque(terminal);
        terminal++;
    }
}

    public void ingresarFreeShop(char terminal) {
        switch (terminal) {
            case 'A' -> {
                gestorFreeShop[0].ingresarFreeShop();
//                System.out.println("El " + Thread.currentThread().getName() + " esta en el FreeShop de la terminal " + terminal);
            }
            case 'B' -> {
                gestorFreeShop[1].ingresarFreeShop();
//                System.out.println("El " + Thread.currentThread().getName() + " esta en el FreeShop de la terminal " + terminal);
            }
            default -> {
                gestorFreeShop[2].ingresarFreeShop();
//                System.out.println("El " + Thread.currentThread().getName() + " esta en el FreeShop de la terminal " + terminal);
            }
        }
    }

    public void salirFreeShop(char terminal) {
        switch (terminal) {
            case 'A' -> {
                gestorFreeShop[0].salirFreeShop();              
            }
            case 'B' -> {
                gestorFreeShop[1].salirFreeShop();              
            }
             case 'C' -> {
                gestorFreeShop[2].salirFreeShop();
            }
        }
    }

    public void pagarFreeShop(char terminal) {
        Random libre = new Random();
        boolean caja1Libre = libre.nextBoolean();
        switch (terminal) {
            case 'A' -> {
                if (caja1Libre) {
                    System.out.println(Thread.currentThread().getName() + " quiere pagar en la caja 1");
                    gestorFreeShop[0].pagarCaja1();
                } else {
                    System.out.println(Thread.currentThread().getName() + " quiere pagar en la caja 2");
                    gestorFreeShop[0].pagarCaja2();
                }
            }
            case 'B' -> {
                if (caja1Libre) {
                    System.out.println(Thread.currentThread().getName() + " quiere pagar en la caja 1");
                    gestorFreeShop[1].pagarCaja1();
                } else {
                    System.out.println(Thread.currentThread().getName() + " quiere pagar en la caja 2");
                    gestorFreeShop[1].pagarCaja2();
                }
            }
            case 'C' -> {
                if (caja1Libre) {
                    System.out.println(Thread.currentThread().getName() + " quiere pagar en la caja 1");
                    gestorFreeShop[2].pagarCaja1();
                } else {
                    System.out.println(Thread.currentThread().getName() + " quiere pagar en la caja 2");
                    gestorFreeShop[2].pagarCaja2();
                }
            }
        }
    }

    public void esperarEmbarque(char terminal) {

    }

    public void embarcar(char t) {

    }

}
