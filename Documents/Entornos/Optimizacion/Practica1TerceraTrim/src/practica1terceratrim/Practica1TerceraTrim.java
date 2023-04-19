/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package practica1terceratrim;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author usuario
 */
public class Practica1TerceraTrim {

    /**
     * @param args the command line arguments
     */
    private static void crearMazo(LinkedList<Carta> mazo) {
        for (int i = 0; i < 8; i++) {
            mazo.add(new Carta("GASOLINA OK", true, i));
            mazo.add(new Carta("GASOLINA NO", true, i));
            mazo.add(new Carta("SEMÁFORO VERDE", true, i));
            mazo.add(new Carta("SEMÁFORO ROJO", true, i));
        }
        for (int i = 0; i < 16; i++) {
            mazo.add(new Carta("KM", false, i));
        }
    }

    private static void cogerCartasInicial(LinkedList<Carta> mazo, Jugador jugador1, Jugador jugador2) {
        for (int i = 0; i < 6; i++) {
            Collections.shuffle(mazo);
            jugador1.baraja.add(mazo.pollFirst());
            jugador2.baraja.add(mazo.pollFirst());
        }
    }

    public static void jugar(LinkedList<Carta> mazo, Jugador jugador1, Jugador jugador2, Mesa mesa1, Mesa mesa2) {

        boolean turno = false;
        Scanner leer = new Scanner(System.in);
        System.out.println("Turno del jugador 1");
        jugador1.baraja.add(mazo.poll());
        Collections.sort(jugador1.baraja);
        System.out.println(jugador1.baraja);
        System.out.println(mesa1.baraja);
        System.out.println(mesa2.baraja);
        System.out.println("¿Qué ficha quieres colocar?");
        int pos = leer.nextInt();
        if (jugador1.baraja.get(pos).getAccion().equals("SEMÁFORO VERDE")) {
            mesa1.baraja.add(jugador1.baraja.get(pos));
        }
        jugador1.baraja.remove(pos);
        turno = true;
        System.out.println("Turno del jugador 2");
        jugador2.baraja.add(mazo.poll());
        Collections.sort(jugador2.baraja);
        System.out.println(jugador2.baraja);
        System.out.println(mesa2.baraja);
        System.out.println(mesa1.baraja);
        System.out.println("¿Qué ficha quieres colocar?");
        pos = leer.nextInt();
        if (jugador2.baraja.get(pos).getAccion().equals("SEMÁFORO VERDE")) {
            mesa2.baraja.add(jugador2.baraja.get(pos));
        }
        jugador2.baraja.remove(pos);
        turno = false;
        do {
            if (turno == false) {
                System.out.println("Turno del jugador 1");
                jugador1.baraja.add(mazo.poll());
                Collections.sort(jugador1.baraja);
                System.out.println("Mesa del jugador 1: " + mesa1.baraja + "km totales:" + jugador1.kmtot);
                System.out.println("Mesa del jugador 2: " + mesa2.baraja + "km totales:" + jugador2.kmtot);
                System.out.println(jugador1.baraja);
                System.out.println("¿Qué ficha quieres colocar?");
                pos = leer.nextInt();
                turno(mesa1, jugador1, pos, mesa2);
                jugador1.baraja.remove(pos);
                turno = true;
            } else {
                System.out.println("Turno del jugador 2");
                jugador2.baraja.add(mazo.poll());
                Collections.sort(jugador2.baraja);
                System.out.println("Mesa del jugador 2: " + mesa2.baraja + "km totales:" + jugador1.kmtot);
                System.out.println("Mesa del jugador 1: " + mesa1.baraja + "km totales:" + jugador2.kmtot);
                System.out.println(jugador2.baraja);
                System.out.println("¿Qué ficha quieres colocar?");
                pos = leer.nextInt();
                turno(mesa2, jugador2, pos, mesa1);
                jugador2.baraja.remove(pos);
                turno = false;
            }

        } while (!mazo.isEmpty() || jugador1.kmtot >= 1000 || jugador2.kmtot >= 1000);
        if (jugador1.kmtot >= 1000) {
            System.out.println("Ha ganado el jugador 1");
        } else if (jugador2.kmtot >= 1000) {
            System.out.println("Ha ganado el jugador 2");
        } else {
            if (jugador1.kmtot > jugador2.kmtot) {
                System.out.println("Ha ganado el jugador 1");
            } else if (jugador2.kmtot > jugador1.kmtot) {
                System.out.println("Ha ganado el jugador 2");
            } else {
                System.out.println("Empate");
            }
        }
    }

    private static void turno(Mesa mesa1, Jugador jugador1, int pos, Mesa mesa2) {
        if (mesa1.baraja.isEmpty() || mesa1.baraja.get(0).getAccion().equals("SEMÁFORO ROJO")) {
            if (jugador1.baraja.get(pos).getAccion().equals("SEMÁFORO VERDE")) {
                mesa1.baraja.add(0, jugador1.baraja.get(pos));
            }

        } else {
            if (mesa1.baraja.get(0).getAccion().equals("SEMÁFORO VERDE")) {
                switch (jugador1.baraja.get(pos).getAccion()) {
                    case "KM":
                        if (mesa1.baraja.size() == 1) {
                            mesa1.baraja.add(1, jugador1.baraja.get(pos));
                            jugador1.setKmtot(jugador1.baraja.get(pos).getKm());
                        } else {
                            mesa1.baraja.set(1, jugador1.baraja.get(pos));
                            jugador1.setKmtot(jugador1.baraja.get(pos).getKm());
                            System.out.println(jugador1.kmtot);
                        }
                        break;
                    case "GASOLINA NO":
                        if (mesa2.baraja.get(0).getAccion().equals("SEMÁFORO VERDE")) {
                            mesa2.baraja.set(0, jugador1.baraja.get(pos));
                        } else {
                            System.out.println("No puedes colocar esa carta ahora mismo");
                        }
                        break;
                    case "SEMÁFORO ROJO":
                        if (mesa2.baraja.get(0).getAccion().equals("SEMÁFORO VERDE") && mesa1.baraja.get(0).getAccion().equals("SEMÁFORO VERDE")) {
                            mesa2.baraja.set(0, jugador1.baraja.get(pos));
                        }
                        break;
                    default:
                        break;
                }
            } else if (mesa1.baraja.get(0).getAccion().equals("GASOLINA NO") && jugador1.baraja.get(pos).getAccion().equals("GASOLINA OK")) {
                mesa1.baraja.set(0, jugador1.baraja.get(pos));
            } else if (mesa1.baraja.get(0).getAccion().equals("GASOLINA OK") && jugador1.baraja.get(pos).getAccion().equals("SEMÁFORO VERDE")) {
                mesa1.baraja.set(0, jugador1.baraja.get(pos));
            } else if (mesa1.baraja.get(0).getAccion().equals("SEMÁFORO ROJO") && jugador1.baraja.get(pos).getAccion().equals("SEMÁFORO VERDE")) {
                mesa1.baraja.set(0, jugador1.baraja.get(pos));
            }
        }

    }

    public static void main(String[] args) {
        LinkedList<Carta> mazo = new LinkedList();
        Jugador jugador1 = new Jugador();
        Jugador jugador2 = new Jugador();
        Mesa mesa1 = new Mesa();
        Mesa mesa2 = new Mesa();
        crearMazo(mazo);
        System.out.println(mazo);
        cogerCartasInicial(mazo, jugador1, jugador2);
        jugar(mazo, jugador1, jugador2, mesa1, mesa2);

    }

}
