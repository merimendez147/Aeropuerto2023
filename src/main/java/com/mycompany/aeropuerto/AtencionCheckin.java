/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Maria Elisa Mendez Cares
 * Legajo: 61921
 * Carrera: Profesorado de Informatica
 * Email: maria.mendez@est.fi.uncoma.edu.ar
 */

public class AtencionCheckin implements Runnable {

        GestorCheckin gestorCheckin;
        int nroPuesto;

        public AtencionCheckin(GestorCheckin gestorCheckin, int puestoCheckin) {
            this.gestorCheckin = gestorCheckin;
            this.nroPuesto = puestoCheckin;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(1000);
                    gestorCheckin.atenderPuestoCheckin(nroPuesto);
                } catch (InterruptedException ex) {
                    Logger.getLogger(AtencionCheckin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }