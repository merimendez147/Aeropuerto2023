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

    int capacidadTren;
    CyclicBarrier inicioTrayecto;
    CyclicBarrier finTrayecto;
    int terminalA = 0;
    int terminalB = 0;
    int terminalC = 0;
    Semaphore mutexContador, semTrenVacio;
    private final Semaphore[] turnoBajarTren = new Semaphore[3];

    public GestorTransporte(int capacidadT) {
        turnoBajarTren[0] = new Semaphore(0, true);
        turnoBajarTren[1] = new Semaphore(0, true);
        turnoBajarTren[2] = new Semaphore(0, true);
        this.capacidadTren = capacidadT;
        inicioTrayecto = new CyclicBarrier(capacidadTren);
        finTrayecto = new CyclicBarrier(capacidadTren);
        mutexContador = new Semaphore(1);
        semTrenVacio = new Semaphore(0);

    }

    public int capacidadTren() {
        return this.capacidadTren;
    }

    public void dejarSubirPasajeros() {
        semTrenVacio.release();
    }

    public boolean trenLleno() {
        boolean lleno=false;
        try {
            mutexContador.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        lleno = (terminalA + terminalB + terminalC) == capacidadTren;
        mutexContador.release();
        return lleno;
    }

    public void subirTren(char terminal) {
        try {
            semTrenVacio.acquire();
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
                //asignarTurnoA();
                System.out.println(terminalA + "  bajan en A, " + terminalB + " bajan en B y " + terminalC + " bajan en C");
                System.out.println("------------------------El tren sale completo---------------------------");
            }
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

//    private void asignarTurnoA() {
//        turnoBajarTren[0].release();
//    }
//    private void asignarTurno(char terminal) {
//        turnoBajarTren[i].release();
//    }
//    private void esperarTurno(char terminal) {
//        switch (terminal) {
//            case 'A' -> {
//                try {
//                    turnoBajarTren[0].acquire();
//                } catch (InterruptedException ex) {
//                    // TODO Auto-generated catch block
//                    Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            case 'B' -> {
//                try {
//                    turnoBajarTren[1].acquire();
//                } catch (InterruptedException ex) {
//                    // TODO Auto-generated catch block
//                    Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//            case 'C' -> {
//                try {
//                    turnoBajarTren[2].acquire();
//                } catch (InterruptedException ex) {
//                    // TODO Auto-generated catch block
//                    Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        }
//    }
    public boolean hayPasajero(char terminal) {
        boolean resultado = false;
        try {
            mutexContador.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (terminal) {
            case 'A' -> {
                // System.out.println("El valor del contador" + contador + " el valor de A " + terminalA);
                resultado = (0 == terminalA);
            }
            case 'B' -> {
                // System.out.println("El valor del contador" + contador + " el valor de B " + terminalB);
                resultado = (0 == terminalB);
            }
            case 'C' -> {
                //System.out.println("El valor del contador" + contador + " el valor de C " + terminalC);
                resultado = (0 == terminalC);
            }
        }
        mutexContador.release();
        return resultado;
    }

//    private boolean noHayPasajeros(char terminal) {
//        boolean resultado = false;
//        try {
//            mutexContador.acquire();
//        } catch (InterruptedException ex) {
//            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        switch (terminal) {
//            case 'A' -> {
//                // System.out.println("El valor del contador" + contador + " el valor de A " + terminalA);
//                resultado = (-1 == terminalA);
//            }
//            case 'B' -> {
//                // System.out.println("El valor del contador" + contador + " el valor de B " + terminalB);
//                resultado = (-1 == terminalB);
//            }
//            case 'C' -> {
//                //System.out.println("El valor del contador" + contador + " el valor de C " + terminalC);
//                resultado = (-1 == terminalC);
//            }
//        }
//        mutexContador.release();
//        return resultado;
//    }
    private void decrementarContador(char terminal) {
        try {
            mutexContador.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        switch (terminal) {
            case 'A' -> {
               System.out.println("El valor de A " + terminalA);
                terminalA--;
            }
            case 'B' -> {
               System.out.println("El valor de B " + terminalB);
                terminalB--;
            }
            case 'C' -> {
               System.out.println("El valor de C " + terminalC);
                terminalC--;
            }
        }
        mutexContador.release();
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

    public void asignarTurno(char terminal) {
        switch (terminal) {
            case 'A' -> {
                turnoBajarTren[0].release();
            }
            case 'B' -> {
                turnoBajarTren[1].release();
            }
            case 'C' -> {
                turnoBajarTren[2].release();
            }
        }
    }

    private void esperarTurno(char terminal) {
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

    public void bajarTren(char terminal) {
        switch (terminal) {
            case 'A' -> {
                esperarTurno('A');
                System.out.println("El " + Thread.currentThread().getName() + " bajo en la terminal " + terminal);
                decrementarContador('A');
            }
            case 'B' -> {
                esperarTurno('B');
                System.out.println("El " + Thread.currentThread().getName() + " bajo en la terminal  " + terminal);
                decrementarContador('B');
            }
            case 'C' -> {
                esperarTurno('C');
                System.out.println("El " + Thread.currentThread().getName() + " bajo en la terminal  " + terminal);
                decrementarContador('C');
            }
        }
        int trenVacio = 0;
        try {
            trenVacio = finTrayecto.await();
        } catch (InterruptedException | BrokenBarrierException ex) {
            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (trenVacio == 0) {
            System.out.println("------------------El tren vuelve vacio-------------------");
            //semTrenVacio.release(capacidadTren);
        }
    }
}

//    public void bajarTren(char terminal) {
//        esperarTurno(terminal);
//        decrementarContador(terminal);
//        boolean noQuedanPasajeros=utimoPasajero(terminal) || noHayPasajeros(terminal);
//        if (noQuedanPasajeros) {
//            System.out.println("Quedan pasajeros para bajar en la terminal "+terminal+ " "+noQuedanPasajeros);
//            char siguienteTerminal=terminal++;
//            System.out.println("La siguiente para es en la terminall "+siguienteTerminal);
//            asignarTurno(siguienteTerminal);
//        } else {
//            System.out.println("El " + Thread.currentThread().getName() + " bajo en la terminal " + terminal);
//            asignarTurno(terminal);
//        }
//        int trenVacio = 0;
//        try {
//            trenVacio = finTrayecto.await();
//        } catch (InterruptedException | BrokenBarrierException ex) {
//            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (trenVacio == 0) {
//            System.out.println("------------------El tren vuelve vacio-------------------");
//            semTrenVacio.release(capacidadTren);
//        }
//    }
//    public void bajarTren(char terminal) {
//        esperarTurno(terminal);
//        decrementarContador(terminal);
//        boolean noQuedanPasajeros = hayPasajero(terminal) || noHayPasajeros(terminal);
//        if (!noQuedanPasajeros) {
//            System.out.println("El " + Thread.currentThread().getName() + " bajo en la terminal " + terminal);
//            asignarTurno(terminal);
//
//        } else {
//            if (hayPasajero(terminal)) {
//                System.out.println("El " + Thread.currentThread().getName() + " bajo en la terminal " + terminal);
//            }
//            char siguienteTerminal = siguienteTerminal(terminal);
//            System.out.println("La siguiente parada es en la terminall " + siguienteTerminal);
//            asignarTurno(siguienteTerminal);
//        }
//        int trenVacio = 0;
//        try {
//            trenVacio = finTrayecto.await();
//        } catch (InterruptedException | BrokenBarrierException ex) {
//            Logger.getLogger(GestorTransporte.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        if (trenVacio == 0) {
//            System.out.println("------------------El tren vuelve vacio-------------------");
//            semTrenVacio.release(capacidadTren);
//        }
//    }
//}
