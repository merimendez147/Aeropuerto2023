/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aeropuerto;

/**
 *
 * @author Academica
 */
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class GestorCajas {

    private int cantidadPasajerosCaja1 = 0, cantidadPasajerosCaja2 = 0;
    private final int tamañoBufferCaja1 = 1, tamañoBufferCaja2 = 1;
    private final Lock caja1;
    private final Condition hacerFilaCaja1;
    private final Condition cobrarCaja1;
    private final Lock caja2;
    private final Condition hacerFilaCaja2;
    private final Condition cobrarCaja2;

    public GestorCajas(int M) {
        this.caja1 = new ReentrantLock();
        this.hacerFilaCaja1 = caja1.newCondition();
        this.cobrarCaja1 = caja1.newCondition();
        this.caja2 = new ReentrantLock();
        this.hacerFilaCaja2 = caja2.newCondition();
        this.cobrarCaja2 = caja2.newCondition();

    }

    public void hacerFilaCaja1() {
        try {
            caja1.lock();
            while (cantidadPasajerosCaja1 >= tamañoBufferCaja1) {
                hacerFilaCaja1.await();
            }
            cantidadPasajerosCaja1++;
            System.out.println(Thread.currentThread().getName() + " esperando en caja 1 ");
            cobrarCaja1.signalAll();

        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            caja1.unlock();
        }
    }

    public void hacerFilaCaja2() {
        try {
            caja2.lock();
            while (cantidadPasajerosCaja2 >= tamañoBufferCaja2) {
                hacerFilaCaja2.await();
            }
            cantidadPasajerosCaja2++;
            System.out.println(Thread.currentThread().getName() + " esperando en caja 2 ");
            cobrarCaja2.signalAll();

        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            caja2.unlock();
        }
    }

    public void cobrarCaja1() {
        try {
            caja1.lock();
            while (cantidadPasajerosCaja1 <= 0) {
                cobrarCaja1.await();
            }
            cantidadPasajerosCaja1--;
            System.out.println(Thread.currentThread().getName() + "esta pagando en la caja 1 ");
            hacerFilaCaja1.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            caja1.unlock();
        }
    }

    public void cobrarCaja2() {
        try {
            caja2.lock();
            while (cantidadPasajerosCaja2 <= 0) {
                cobrarCaja2.await();
            }
            cantidadPasajerosCaja2--;
            System.out.println(Thread.currentThread().getName() + "esta pagando en la caja 2 ");
            hacerFilaCaja2.signalAll();
        } catch (InterruptedException e) {
            System.out.println(e);
        } finally {
            caja2.unlock();
        }
    }
}
