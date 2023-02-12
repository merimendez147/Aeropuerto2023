package com.mycompany.aeropuerto;

import java.util.concurrent.ArrayBlockingQueue;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Academica
 */
public class ColaEsperaPuestoAtencion {

    int max = 20;
    ArrayBlockingQueue<Pasajero> colaEspera;
    Guardia guardia;
    PuestoAtencion puestoAtencion;

    public ColaEsperaPuestoAtencion(Guardia g, PuestoAtencion pA) {
        colaEspera = new ArrayBlockingQueue<>(max);
        this.guardia = g;
        this.puestoAtencion = pA;
    }
}
