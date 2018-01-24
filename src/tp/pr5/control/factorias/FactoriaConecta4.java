package tp.pr5.control.factorias;

import java.util.Scanner;

import tp.pr5.control.jugadores.Jugador;
import tp.pr5.control.jugadores.JugadorAleatorioConecta4;
import tp.pr5.control.jugadores.JugadorHumanoConecta4;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.movimientos.MovimientoConecta4;
import tp.pr5.logica.reglas.ReglasConecta4;
import tp.pr5.logica.reglas.ReglasJuego;
/**
 * Implementaci�n de la factor�a para el juego del Conecta 4. 
 * Los m�todos devuelven los objetos capaces de jugar a ese juego.
 * 
 */
public class FactoriaConecta4 implements FactoriaTipoJuego {

	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioConecta4();
	}

	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoConecta4(in);
	}

	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoConecta4(col, color);
	}

	public ReglasJuego creaReglas() {
		return new ReglasConecta4();
	}

}
