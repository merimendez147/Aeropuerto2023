/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;
/**
 *
 * @author Maria Elisa Mendez Cares
 * Legajo: 61921
 * Carrera: Profesorado de Informatica
 * Email: maria.mendez@est.fi.uncoma.edu.ar
 */
public class Reserva {

    String aerolinea;
    char terminal;
    int puestoCheckin;

    public Reserva(int numero, String aerolinea) {
        this.aerolinea = aerolinea;
        switch (numero) {
            case 0 ->
                this.terminal = 'A';
            case 1 ->
                this.terminal = 'B';
            case 2 ->
                this.terminal = 'C';
        }
        this.puestoCheckin = numero;
    }

    public String aerolinea() {
        return this.aerolinea;
    }

    public char terminal() {
        return this.terminal;
    }

    public int puestoChecking() {
        return this.puestoCheckin;
    }
}
