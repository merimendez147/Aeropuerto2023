/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;


/**
 *
 * @author Academica
 */
public class ZonaCheckIn {

    int cantidadPuestosAtencion;
    ColaEsperaPuestoAtencion [] puestos;
    Guardia guardias[] = new Guardia[cantidadPuestosAtencion];//un guardia por cada puesto de atencion
    PuestoAtencion[] puestoAtencion = new PuestoAtencion[cantidadPuestosAtencion];

 
    public ZonaCheckIn(int cantPuestos){
        this.cantidadPuestosAtencion=cantPuestos;
        for(int i = 0; i<=this.cantidadPuestosAtencion;i++){
            guardias[i] = new Guardia(i);
            puestoAtencion[i] = new PuestoAtencion(i);
            puestos[i]=new ColaEsperaPuestoAtencion(guardias[i], puestoAtencion[i]);
            

        }
    }
}
