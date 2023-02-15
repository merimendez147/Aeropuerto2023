/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.concurrent.ArrayBlockingQueue;

/**
 *
 * @author Academica
 */
public class GestorChecking {
    int max = 20;
    ArrayBlockingQueue<Pasajero> colaEspera;
    
    public GestorChecking() {
        colaEspera = new ArrayBlockingQueue<>(max);
    }
}
