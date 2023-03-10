/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
 */
public class GestorSalasEmbarque {

    int cantTerminales;
    GestorFreeShop[] gestorFreeShop;
    GestorEmbarque[] gestorEmbarque;

    public GestorSalasEmbarque(int cantT) {
        this.cantTerminales = cantT;
        gestorFreeShop = new GestorFreeShop[cantTerminales];
        gestorEmbarque = new GestorEmbarque[cantTerminales];
        char terminal = 65;
        for (int i = 0; i < cantTerminales; i++) {
            this.gestorFreeShop[i] = new GestorFreeShop(terminal);
            this.gestorEmbarque[i] = new GestorEmbarque(terminal);
            terminal++;
        }
    }

    public void irFreeShop(char terminal) {
        switch (terminal) {
            case 'A' -> {

                System.out.println("El " + Thread.currentThread().getName() + " esta en el FreeShop de la terminal " + terminal);
            }
            case 'B' -> {

                System.out.println("El " + Thread.currentThread().getName() + " esta en el FreeShop de la terminal " + terminal);
            }
            default -> {

                System.out.println("El " + Thread.currentThread().getName() + " esta en el FreeShop de la terminal " + terminal);
            }
        }
    }

    public void salirFreeShop(char terminal) {

    }

    public void esperarEmbarque(char terminal) {

    }

    public void embarcar(char t) {

    }

}
