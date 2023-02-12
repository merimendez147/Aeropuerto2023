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
public class Pasajero implements Runnable {

    PuestoInformes puestoInformes;
    ZonaCheckIn zonaCheckIn;
    Tren tren;
    FreeShop freeShop;
    PuestoEmbarque puestoEmbarque;

    public Pasajero(PuestoInformes pInformes, ZonaCheckIn zci, Tren t, FreeShop fShop, PuestoEmbarque pEmbarque) {
        this.puestoInformes = pInformes;
        this.zonaCheckIn = zci;
        this.tren = t;
        this.freeShop = fShop;
        this.puestoEmbarque = pEmbarque;
    }

    @Override
    public void run() {
        puestoInformes.irPuestoInformes();
        guardia.irPuestoAtencion();
        puestoAtencion.hacerChecking();
        tren.irTeminal();
        Random rd = new Random();
        boolean quieroIrFreeShop = rd.nextBoolean();
        if (quieroIrFreeShop) {
            freeShop.irFreeShop();
            boolean quieroComprar = rd.nextBoolean();
            if (quieroComprar) {
                freeShop.pagarFreeShop();
            }
        }
        puestoEmbarque.embarcar();
    }
}
