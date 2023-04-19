/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package practica1terceratrim;

import java.util.LinkedList;

/**
 *
 * @author usuario
 */
public class Jugador {
//atributos
    protected LinkedList<Carta> baraja = new LinkedList();
    protected Integer kmtot;
//métodos
        public Integer getKmtot() {
        return kmtot;
    }

    public void setKmtot(Integer kmtot) {
        this.kmtot += kmtot;
    }
//constructores

    public Jugador() {
        this.kmtot = 0;
    }


}
