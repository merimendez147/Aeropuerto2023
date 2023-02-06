/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
 */
public class PuestoInformes {
    
    int cantidadPuestosAtencion;

    public PuestoInformes(int cpa) {
        cantidadPuestosAtencion = cpa;
    }

    public void solicitarDatosVuelo() {

    }

    public void derivarPuestoAtencion() {
        int puestoAtencionAerolinea = (int) (Math.random() * cantidadPuestosAtencion);

    }

}
