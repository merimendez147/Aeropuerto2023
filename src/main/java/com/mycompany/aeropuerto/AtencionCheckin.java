/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Mendez
 * Legajo 61921
 * Profesorado en Informatica
 */
public class AtencionCheckin implements Runnable {

        GestorCheckin gestorCheckin;
        int puestoCheckin;

        public AtencionCheckin(GestorCheckin gestorCheckin, int puestoCheckin) {
            this.gestorCheckin = gestorCheckin;
            this.puestoCheckin = puestoCheckin;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    gestorCheckin.atenderPuestoCheckin(puestoCheckin);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AtencionCheckin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }