/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.Random;

/**
 *
 * @author Maria Mendez
 * Legajo 61921
 * Profesorado en Informatica
 */
public class GestorSalasEmbarque {

    GestorFreeShop[] gestorFreeShop;
    Thread[] cajero1;
    Thread[] cajero2;
    int cantTerminales;
    Vuelos vuelos;

    public GestorSalasEmbarque(Vuelos vuelos) {
        this.vuelos= vuelos;
        this.cantTerminales = vuelos.cantidadAerolineas;
        gestorFreeShop = new GestorFreeShop[cantTerminales];
        cajero1 = new Thread[cantTerminales];
        cajero2 = new Thread[cantTerminales];
    }

    public void iniciarAtencion() {
        char terminal = 65;
        for (int i = 0; i < cantTerminales; i++) {
            gestorFreeShop[i] = new GestorFreeShop();
            cajero1[i] = new Thread(new Cajero(gestorFreeShop[i], terminal, 1));
            cajero1[i].setName("Cajero1 de la terminal " + terminal);
            cajero1[i].start();
            cajero2[i] = new Thread(new Cajero(gestorFreeShop[i], terminal, 2));
            cajero2[i].setName("Cajero2 de la terminal " + terminal);
            cajero2[i].start();
            terminal++;
        }
    }

        public String aerolinea(int indice){
        return vuelos.aerolinea(indice);
    } 
        
    public boolean vueloEmbarcando(int indice){
        return vuelos.embarcando(indice);
    }
    
   public void cerrarEmbarque(int indice){
       vuelos.cerrarEmbarque(indice);
   }
   
   public void embarcar(int indice) {
       vuelos.embarcar(indice);
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
}
