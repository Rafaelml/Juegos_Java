package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.control.factorias.FactoriaTipoJuego;
import tp.pr5.control.jugadores.Jugador;
import tp.pr5.gui.logica.Observer;
import tp.pr5.logica.*;

/**
 * Clase que controla la ejecuci�n de la partida, pidiendo al usuario que quiere
 * ir haciendo, hasta que la partida termina.
 */
public class ControladorConsola extends Controlador {
	private Scanner in;
	private Jugador jugadorBlanca = null;
	private Jugador jugadorNegra = null;

	/**
	 * Constructor de la clase.
	 * 
	 * @param factoria
	 *            - Factoria de Tipo juego
	 * @param partida
	 *            - Partida a la que se jugar�.
	 * @param in
	 *            - Scanner recibir la informaci�n pedida al usuario.
	 */
	public ControladorConsola(FactoriaTipoJuego f, Partida partida,
			java.util.Scanner in) {
		this.in = in;
		this.partida = partida;
		this.factoria = f;
		jugadorBlanca = factoria.creaJugadorHumanoConsola(in);
		jugadorNegra = factoria.creaJugadorHumanoConsola(in);
	}

	/**
	 * Get factoria
	 * 
	 * @return devuelve la factoria actual
	 */
	public FactoriaTipoJuego getFactoria() {
		return factoria;
	}

	/**
	 * Get partida
	 * 
	 * @return devuelve la partida actual
	 */
	public Partida getPartida() {
		return partida;
	}

	/**
	 * M�todo que ejecuta la partida hasta que termina. Muestra el tablero y a
	 * quien le toca jugar. Y pide comando.
	 */
	public void run() {
		GestorComandos comandos = new GestorComandos(this);
		boolean salir = false;

		while (!partida.isTerminada() && !salir) {
			System.out.println(partida.toString());
			getJuega();
			System.out.print("Qué quieres hacer? ");

			salir = comandos.ejecuta();
		}

	}

	/**
	 * Get jugador blancas
	 * 
	 * @return devuelve el jugador blanco
	 */
	public Jugador getJugadorBlanca() {
		return jugadorBlanca;
	}

	/**
	 * Get Jugador negra
	 * 
	 * @returndevuelve el jugador negro
	 */
	public Jugador getJugadorNegra() {
		return jugadorNegra;
	}

	/**
	 * Get partida
	 * 
	 * @return devuelve el scanner por el que interactua el usuario
	 */
	public Scanner getIn() {
		return in;
	}

	/**
	 * Método que muestra el color del jugador al que le corresponde jugar.
	 */

	private void getJuega() {
		Ficha a = partida.getTurno();
		if (a.equals(Ficha.BLANCA))
			System.out.println("Juegan blancas");
		else if (a.equals(Ficha.NEGRA))
			System.out.println("Juegan negras");
	}

	/**
	 * Set factoria Metodo que cambia la factoria actual
	 */
	public void setFactoria(FactoriaTipoJuego factoria) {
		this.factoria = factoria;
	}

	/**
	 * Set jugador Metodo que cambia el jugador blanco actual
	 */
	public void setJugadorBlanca(Jugador jugadorBlanca) {
		this.jugadorBlanca = jugadorBlanca;
	}

	/**
	 * Set jugador negra Metodo que cambia el jugador blanco actual
	 */
	public void setJugadorNegra(Jugador jugadorNegra) {
		this.jugadorNegra = jugadorNegra;
	}
	public void addObserver(Observer o) {
		partida.addObserver(o);
	}

}
