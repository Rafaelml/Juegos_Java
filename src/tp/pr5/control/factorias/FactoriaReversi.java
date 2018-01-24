package tp.pr5.control.factorias;

import java.util.Scanner;

import tp.pr5.control.jugadores.Jugador;
import tp.pr5.control.jugadores.JugadorAleatorioReversi;
import tp.pr5.control.jugadores.JugadorHumanoReversi;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.movimientos.MovimientoReversi;
import tp.pr5.logica.reglas.ReglasJuego;
import tp.pr5.logica.reglas.ReglasReversi;
/**
 * Implementaci�n de la factor�a para el juego del Conecta 4. 
 * Los m�todos devuelven los objetos capaces de jugar a ese juego.
 * 
 */
public class FactoriaReversi implements FactoriaTipoJuego {

	
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioReversi();
	}

	
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoReversi(in);
	}

	
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoReversi(col,fila,color);
	}

	
	public ReglasJuego creaReglas() {
		return new ReglasReversi();
	}

}
