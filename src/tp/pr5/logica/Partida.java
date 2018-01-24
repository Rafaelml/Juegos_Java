package tp.pr5.logica;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;

import tp.pr5.gui.logica.Observable;
import tp.pr5.gui.logica.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.movimientos.MovimientoInvalido;
import tp.pr5.logica.movimientos.MovimientoReversi;
import tp.pr5.logica.reglas.ReglasJuego;

/**
 * Clase para representar la información de una partida. Así como la lista de
 * movimientos que se han ido realizando para poder deshacerlos. La partida
 * guarda al menos los 10 últimos movimientos.
 */

public class Partida implements Observable<Observer> {

	private Tablero tablero;
	private Ficha turno;
	private boolean terminada = false;
	private Ficha ganador = Ficha.VACIA;
	private Stack pila;
	private int tamanioPila = 10;
	private ReglasJuego reglas;
	private Collection<Observer> obs;

	/**
	 * Método Constructor de la clase Partida. Crea un tablero de ancho*alto
	 * casillas, una pila de tamanioPila Y resetea la partida, inicializando las
	 * variables a sus valores iniciales
	 */
	public Partida(ReglasJuego regla) {
		this.reglas = regla;
		pila = new Stack(tamanioPila);
		this.tablero = regla.iniciaTablero();

		turno = regla.jugadorInicial();
		this.obs = new ArrayList<Observer>();
		reset(regla);
	}

	/**
	 * Método que coloca la Ficha del color intdicado en el lugar indicado
	 * 
	 * @param mov
	 *            - Movimiento a ejecutar. Se asume que el movimiento es
	 *            compatible con las reglas de la partida que se est� jugando
	 *            (si se est� jugando a Conecta 4, el tipo de movimiento es
	 *            Conecta 4, etc.). En caso contrario, el comportamiento es
	 *            indeterminado.
	 * @return Devuelve true si se ha podido realizar el movimiento
	 */

	public void ejecutaMovimiento(Movimiento mov) throws MovimientoInvalido {

		try {
			if (!mov.getJugador().equals(turno)) {
				throw new MovimientoInvalido("No es tu turno!");
			} else

			{
				mov.ejecutaMovimiento(tablero);
				for (Observer o : obs) { // Informa a los observadore
					o.onMove(tablero); // que se ha realizado un movimiento
				}

				ganador = reglas.hayGanador(mov, tablero);
				pila.aniadirUndo(mov);
				for (Observer o : obs) { // Informa a los observadores
					o.onNotUndo(pila.vacia()); // que se ha realizado un
												// movimiento y guardado en
												// la pila de movimientos
				}
				if (reglas.tablas(turno, tablero)) {
					ganador = Ficha.VACIA;
					terminada = true;
				} else if (ganador.equals(Ficha.VACIA))
					turno = reglas.siguienteTurno(turno, tablero);
				else
					terminada = true;
			}

			for (Observer o : obs)
				o.onTurno(turno);// Informa a los observables
									// que se ha cambiado el turno

		} catch (MovimientoInvalido e) {
			for (Observer o : obs) {
				o.onError(e.getMessage());// Informa a los observables
			} // que se ha capturado un error
		}
		if (terminada) {
			for (Observer o : obs) {
				o.onGameOver(ganador);
			}
		}
	}
		
	

	/**
	 * Método informa si ha temrinado la partida
	 * 
	 * @return true si ha terminado.
	 */
	public boolean isTerminada() {

		return terminada;
	}

	/**
	 * Método que devuelve el turno.
	 * 
	 * @return el color de la ficha a la que le toca
	 */
	public Ficha getTurno() {
		return turno;
	}
	

	/**
	 * Método que devuelve el ganador.
	 * 
	 * @return el color de la ficha que ha ganado la partida
	 */
	public Ficha getGanador() {
		return ganador;
	}

	/**
	 * Método que deshace el ultimo movimiento realizado. Si el tablero no
	 * está vacio o no se han hecho ya 10 deshacer seguidos, colocará en la
	 * ultima casilla puesta una ficha Vacia y lo desapilará. Tambien cambiará
	 * el turno tras realizar un deshacer.
	 * 
	 * @returns si se ha podido deshacer el movimiento
	 */
	public boolean undo() {
		Movimiento mov;
		boolean hecho = false;

		mov = pila.deshacer();
		if (mov != null) {
			mov.undo(tablero);
			turno = reglas.siguienteTurno(turno, tablero);
			hecho = true;
		}
		for (Observer o : obs) {
			o.onUndo(tablero); // Informa a los observable
			o.onTurno(turno); // de que se ha desacido
								// un movimiento y se ha
			o.onNotUndo(pila.vacia());// desapilado
		}
		return hecho;
	}

	/**
	 * Método que reinicia la partida
	 *
	 */
	public void reset(ReglasJuego reglas) {
		terminada = false;
		tablero = reglas.iniciaTablero();
		turno = reglas.jugadorInicial();
		ganador = Ficha.VACIA;
		pila.reset();
		this.reglas = reglas;
		for (Observer o : obs) {
			o.onReset(tablero); // Informa a los observable
			o.onTurno(turno); // de que se ha reiniciado
								// la partida
		}
	}

	/**
	 * Método que llama al toString de tablero y lo devuelve. Se implementa
	 * para hacer mas corta la llamada a toString().
	 * 
	 * @return tablero
	 */
	public String toString() {
		return tablero.toString();
	}

	/**
	 * Método publico que devuelve el tablero que esta utilizando la partida
	 * 
	 * @return tablero
	 */

	public Tablero getTablero() {
		return tablero;
	}

	public void addObserver(Observer o) {
		obs.add(o);
	}

	public void removeObserver(Observer o) {
		obs.remove(o);

	}
    /**
     *Método que le cede el turno al jugador de color contrario
     */
	public void cedeTurno() {
		
		turno = reglas.siguienteTurno(turno, tablero);
				

		int ancho = tablero.getAncho();
		int alto = tablero.getAlto();
		MovimientoReversi mov;

		validasReversi.clear();
		for (int i = 1; i <= ancho; i++) {
			for (int j = 1; j <= alto; j++) {
				mov = new MovimientoReversi(i, j, turno);
				if (mov.comprobarCasilla(tablero,false))
					validasReversi.add(new Point(i, j));
			}
		}
		if(validasReversi.vacio()) turno = reglas.siguienteTurno(turno, tablero);
	
		
		for (Observer o : obs) { // Informa a los observadore
				o.onMove(tablero); // que se ha realizado un movimiento
		}
			
		for (Observer o : obs)
			o.onTurno(turno);// Informa a los observables
								// que se ha cambiado el turno
		
	}

}
