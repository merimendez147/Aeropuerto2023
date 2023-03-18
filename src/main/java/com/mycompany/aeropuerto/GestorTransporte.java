/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Academica
 */
public class GestorTransporte {

    Semaphore semTrenParado, mutexContador;
    CyclicBarrier inicioTrayecto, finTrayecto;
    int capacidadTren, terminalA, terminalB, terminalC;
    private final Semaphore[] turnoBajarTren = new Semaphore[3];

    public GestorTransporte(int capacidadT) {
        this.capacidadTren = capacidadT;
        inicioTrayecto = new CyclicBarrier(capacidadTren);
        finTrayecto = new CyclicBarrier(capacidadTren);
        semTrenParado = new Semaphore(capacidadTren);
        mutexContador = new Semaphore(1);
        terminalA = 0;
        terminalB = 0;
        terminalC = 0;
        turnoBajarTren[0] = new Semaphore(0, true);
        turnoBajarTren[1] = new Semaphore(0, true);
        turnoBajarTren[2] = new Semaphore(0, true);
    }

    public void subirTren(char terminal) {
        try {
            semTrenParado.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            switch (terminal) {
                case 'A' -> {
                    incrementarContador('A');
                    System.out.println("El " + Thread.currentThread().getName() + " subio al tren y tiene que ir a la terminal " + terminal);
                }
                case 'B' -> {
                    incrementarContador('B');
                    System.out.println("El " + Thread.currentThread().getName() + " subio al tren y tiene que ir a la terminal " + terminal);
                }
                case 'C' -> {
                    incrementarContador('C');
                    System.out.println("El " + Thread.currentThread().getName() + " subio al tren y tiene que ir a la terminal " + terminal);
                }

            }
            int trenCompleto = inicioTrayecto.await();
            if (trenCompleto == 0) {
                System.out.println("------------------------El tren sale completo---------------------------");
                asignarPrimerTurno();
                inicioTrayecto.reset();
            }
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void bajarTren(char terminal) {
        switch (terminal) {
            case 'A' -> {
                esTurno('A');
                System.out.println("El " + Thread.currentThread().getName() + " baja en la terminal " + terminal);
                decrementarContador('A');
                if (ultimoPasajero('A')) {
                    asignarSiguienteTurno('A');
                }
            }
            case 'B' -> {
                esTurno('B');
                System.out.println("El " + Thread.currentThread().getName() + " baja en la terminal " + terminal);
                decrementarContador('B');
                if (ultimoPasajero('B')) {
                    asignarSiguienteTurno('B');
                }
            }
            case 'C' -> {
                esTurno('C');
                System.out.println("El " + Thread.currentThread().getName() + " baja en la terminal " + terminal);
                decrementarContador('C');
                if (ultimoPasajero('C')) {
                    asignarSiguienteTurno('C');
                }
            }
        }
        int trenCompleto = 0;
        try {
            trenCompleto = finTrayecto.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (trenCompleto == 0) {
            System.out.println("------------------------El tren vuelve vacio---------------------------");
            semTrenParado.release(capacidadTren);
            finTrayecto.reset();
        }
    }

    private void incrementarContador(char terminal) {
        try {
            mutexContador.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (terminal) {
            case 'A' -> {
                terminalA++;
            }
            case 'B' -> {
                terminalB++;
            }
            case 'C' -> {
                terminalC++;
            }
        }
        mutexContador.release();
    }

    private void decrementarContador(char terminal) {
        try {
            mutexContador.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (terminal) {
            case 'A' -> {
                terminalA--;
            }
            case 'B' -> {
                terminalB--;
            }
            case 'C' -> {
                terminalC--;
            }
        }
        mutexContador.release();
    }

    private void asignarPrimerTurno() {
        try {
            mutexContador.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (terminalA > 0) {
            turnoBajarTren[0].release(terminalA);
        } else if (terminalB > 0) {
            turnoBajarTren[1].release(terminalB);
        } else if (terminalC > 0) {
            turnoBajarTren[2].release(terminalC);
        }
        mutexContador.release();
    }

    private void asignarSiguienteTurno(char terminal) {
        switch (terminal) {
            case 'A' -> {
                if (terminalB > 0) {
                    turnoBajarTren[1].release(terminalB);
                } else {
                    turnoBajarTren[2].release(terminalC);
                }
            }
            case 'B' -> {
                turnoBajarTren[2].release(terminalC);
            }
            case 'C' -> {
                turnoBajarTren[0].release(terminalA);
            }
        }
    }

    private boolean ultimoPasajero(char terminal) {
        boolean ultimo = false;
        try {
            mutexContador.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (terminal) {
            case 'A' -> {
                ultimo = terminalA == 0;
            }
            case 'B' -> {
                ultimo = terminalB == 0;
            }
            case 'C' -> {
                ultimo = terminalC == 0;
            }
        }
        mutexContador.release();
        return ultimo;
    }

    private void esTurno(char terminal) {
        switch (terminal) {
            case 'A' -> {
                try {
                    turnoBajarTren[0].acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case 'B' -> {
                try {
                    turnoBajarTren[1].acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            case 'C' -> {
                try {
                    turnoBajarTren[2].acquire();
                } catch (InterruptedException ex) {
                    Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
