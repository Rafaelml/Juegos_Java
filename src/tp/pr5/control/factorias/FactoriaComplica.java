package tp.pr5.control.factorias;

import java.util.Scanner;

import tp.pr5.control.jugadores.Jugador;
import tp.pr5.control.jugadores.JugadorAleatorioComplica;
import tp.pr5.control.jugadores.JugadorHumanoComplica;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.movimientos.MovimientoComplica;
import tp.pr5.logica.reglas.ReglasComplica;
import tp.pr5.logica.reglas.ReglasJuego;
/**
 * Implementaci�n de la factor�a para el juego del Complica. 
 * Los m�todos devuelven los objetos capaces de jugar a ese juego.
 *
 */

public class FactoriaComplica implements FactoriaTipoJuego {

	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioComplica();
	}

	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoComplica(in);
	}

	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoComplica(col, color);
	}

	public ReglasJuego creaReglas() {
		return new ReglasComplica();
	}

}
