package tp.pr5.control;

import tp.pr5.control.factorias.FactoriaTipoJuego;
import tp.pr5.gui.logica.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Partida;
import tp.pr5.logica.validasReversi;
import tp.pr5.logica.movimientos.MovimientoInvalido;

public abstract class Controlador {
	protected Partida partida;
	protected FactoriaTipoJuego factoria;

	public void salir() {
	}

	public void run() {
	}

	/**
	 * Método que crea un movimiento
	 * 
	 * @param col
	 *            - Columna del movimiento
	 * @param fil
	 *            - Fila del movimiento
	 * @param color
	 *            - Color de la ficha
	 * 
	 */
	public void movimiento(int x, int y, Ficha color) throws MovimientoInvalido {
	}

	/**
	 * Método que reinicia la partida.
	 */
	public void reset() {
	}

	/**
	 * Método que deshace un movimiento
	 */
	public boolean deshacer() {
		return partida.undo();
	}

	/**
	 * Método que permite cambiar la fáctoria
	 * 
	 * @param opc
	 *            - Tipo de juego
	 * @param cols
	 *            - Columnas del tablero para Gravity
	 * @param fils
	 *            - Filas del tablero para Gravity
	 */
	public void cambiarJuego(String opc, int x, int y) {
	}

	/**
	 * Get turno
	 * 
	 * @return devuelve a quien le toca jugar
	 */
	public Ficha getTurno() {
		return partida.getTurno();
	}

	/**
	 * Método que crea un movimiento aleatorio
	 */
	public void movAleatorio() {
	}

	/**
	 * Get terminada
	 * 
	 * @return Devuelve si la partida ha terminado
	 */
	public boolean getTerminada() {
		return partida.isTerminada();
	}

	/**
	 * Método que llama al addObserver de partida
	 */
	public void addObserver(Observer o) {
		partida.addObserver(o);
	}

	public boolean getValida(int x, int y) {
		return validasReversi.getValida(x,y); 
	}

	public void cederTruno() {
		partida.cedeTurno();
	}
	}
