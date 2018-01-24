package tp.pr5.control.factorias;

import java.util.Scanner;

import tp.pr5.control.jugadores.Jugador;
import tp.pr5.control.jugadores.JugadorAleatorioGravity;
import tp.pr5.control.jugadores.JugadorHumanoGravity;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.movimientos.MovimientoGravity;
import tp.pr5.logica.reglas.ReglasGravity;
import tp.pr5.logica.reglas.ReglasJuego;
/**
 * Implementaci�n de la factor�a para el juego del Gravity. 
 * Los m�todos devuelven los objetos capaces de jugar a ese juego.
 *x -columanas del tablero
 *y - filas del tablero
 */
public class FactoriaGravity implements FactoriaTipoJuego {
	private int x = 10;
	private int y = 10;

	public FactoriaGravity(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public FactoriaGravity() {
	}

	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
	}

	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoGravity(in);
	}

	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoGravity(col, fila, color);
	}

	public ReglasJuego creaReglas() {
		return new ReglasGravity(x, y);
	}

}
 