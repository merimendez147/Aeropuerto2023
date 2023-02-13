/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
 */
public class AtencionInformes implements Runnable {
    
    GestorInformes informes;
    
    public AtencionInformes(GestorInformes i){
        this.informes=i;
    }
    
    @Override
    public void run(){
        informes.esperarPasajero();
        informes.atenderPasajero();
    }
}
