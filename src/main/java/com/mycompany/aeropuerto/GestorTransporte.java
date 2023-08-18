/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.HashMap;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Maria Elisa Mendez Cares Legajo: 61921 Carrera: Profesorado de
 * Informatica Email: maria.mendez@est.fi.uncoma.edu.ar
 */
public class GestorTransporte {

    Semaphore semTrenParado;//Este semáforo controla la cantidad de pasajeros que pueden subir al tren. Se inicializa con la capacidad total del tren.
    Semaphore mutexTrenVacio; //Este semáforo asegura que la variable trenVacio sea actualizada de manera exclusiva.
    Semaphore[] mutexContador;//Un array de semáforos que protege el acceso concurrente a los contadores de pasajeros en cada terminal.
    CyclicBarrier inicioTrayecto;// Una barrera cíclica que se utiliza para sincronizar el inicio del trayecto del tren cuando se ha subido la cantidad completa de pasajeros.
    int capacidadTren; // La capacidad total del tren
    int trenVacio;//Un contador que controla cuántos pasajeros han bajado del tren
    HashMap<Character, Integer> terminales = new HashMap<>();// Un hashmap que asocia cada terminal con un índice numérico (0, 1 o 2).
    Semaphore[] turnoBajarTren;//Un array de semáforos que permite que los pasajeros bajen del tren en el orden correcto según su turno.
    int[] contadorPasjeros;//Un array que almacena el contador de pasajeros para cada terminal.

    public GestorTransporte(int capacidadT) {
        terminales.put('A', 0);
        terminales.put('B', 1);
        terminales.put('C', 2);
        int cantTerminales = 3;
        trenVacio = capacidadT;
        mutexTrenVacio = new Semaphore(1);
        turnoBajarTren = new Semaphore[cantTerminales];
        mutexContador = new Semaphore[cantTerminales];
        contadorPasjeros = new int[cantTerminales];
        this.capacidadTren = capacidadT;
        inicioTrayecto = new CyclicBarrier(capacidadTren);
        semTrenParado = new Semaphore(capacidadTren);
        for (int i = 0; i < cantTerminales; i++) {
            mutexContador[i] = new Semaphore(1);
            contadorPasjeros[i] = 0;
            turnoBajarTren[i] = new Semaphore(0, true);
        }
    }

    public void subirTren(char terminal) {
        try {
            semTrenParado.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        incrementarContador(terminales.get(terminal));
        System.out.println("El " + Thread.currentThread().getName() + " subio al tren y tiene que ir a la terminal " + terminal);
        try {
            if (inicioTrayecto.await() == 0) {
                System.out.println("------------------------El tren sale completo---------------------------");
                asignarPrimerTurno();
                inicioTrayecto.reset();
            }
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bajarTren(char terminal) {
        esTurno(terminales.get(terminal));
        System.out.println("El " + Thread.currentThread().getName() + " baja en la terminal " + terminal);
        decrementarContador(terminales.get(terminal));
        if (ultimoPasajero(terminales.get(terminal))) {
            avanzarSiguienteEstacion(terminal);
            asignarSiguienteTurno(terminales.get(terminal));
        }
        try {
            mutexTrenVacio.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        trenVacio--;
        if (trenVacio == 0) {
            System.out.println("------------------------El tren vuelve vacio---------------------------");
            semTrenParado.release(capacidadTren);
        }
        mutexTrenVacio.release();
    }

    private void incrementarContador(int indice) {
        try {
            mutexContador[indice].acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        contadorPasjeros[indice]++;
        mutexContador[indice].release();
    }

    private void avanzarSiguienteEstacion(char terminal) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("------------------Bajaron todos los pasajeros en la terminal " + terminal + " ----------------");
    }

    private void decrementarContador(int indice) {
        try {
            mutexContador[indice].acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        contadorPasjeros[indice]--;
        mutexContador[indice].release();
    }

    private void asignarPrimerTurno() {
        if (contadorPasjeros[0] > 0) {
            turnoBajarTren[0].release(contadorPasjeros[0]);
        } else if (contadorPasjeros[1] > 0) {
            turnoBajarTren[1].release(contadorPasjeros[1]);
        } else if (contadorPasjeros[2] > 0) {
            turnoBajarTren[2].release(contadorPasjeros[2]);
        }
    }

    private void asignarSiguienteTurno(int indice) {
        if (indice == 0) {
            try {
                mutexContador[1].acquire();
                mutexContador[2].acquire();
                if (contadorPasjeros[1] > 0) {
                    turnoBajarTren[1].release(contadorPasjeros[1]);
                } else {
                    turnoBajarTren[2].release(contadorPasjeros[2]);
                }
                mutexContador[1].release();
                mutexContador[2].release();
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (indice == 1) {
            try {
                mutexContador[2].acquire();
                turnoBajarTren[2].release(contadorPasjeros[2]);
                mutexContador[2].release();
            } catch (InterruptedException ex) {
                Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private boolean ultimoPasajero(int indice) {
        boolean ultimo;
        try {
            mutexContador[indice].acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
        ultimo = contadorPasjeros[indice] == 0;
        mutexContador[indice].release();
        return ultimo;
    }

    private void esTurno(int indice) {
        try {
            turnoBajarTren[indice].acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
    }
}
