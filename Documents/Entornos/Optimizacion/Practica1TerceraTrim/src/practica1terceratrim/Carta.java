/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package practica1terceratrim;

/**
 *
 * @author usuario
 */
public class Carta implements Comparable {
//atributos
    protected String accion; 
    protected Integer km;
    protected Integer peso;
    protected boolean tipo;
//métodos

    @Override
    public String toString() {
        String cartamos="";
        if(tipo==true){
            cartamos= accion;
        }else{
            cartamos=accion+":"+km;
        }
        return cartamos;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public Integer getKm() {
        return km;
    }

    public void setKm(Integer km) {
        this.km = km;
    }
    
//constructores

    public Carta(String accion, boolean tipo, int i) {
        this.accion = accion;
        this.tipo = tipo;
        if(tipo==true){
            km=0;
        }else{
            if(i<2){
                km=25;
            }else if(i<4){
                km=50;
            }else if(i<6){
                km=75;
            }else if(i<12){
                km=100;
            }else if(i<14){
                km=150;
            }else{
                km=200;
            }
        }
    }

    @Override
    public int compareTo(Object o) {
        return km.compareTo(((Carta) o).km);
    }



   
}
