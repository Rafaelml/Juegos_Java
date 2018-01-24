package tp.pr5.gui;

import tp.pr5.control.Controlador;
import tp.pr5.gui.logica.Observable;
import tp.pr5.gui.logica.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;

/**
 * Clase de la Vista en consola del juego.
 * 
 */

public class Consola implements Observer {

	/**
	 * Constructor 
	 * Constructor que añade esta vista como observer.
	 */
	public Consola(Controlador control, Observable<Observer> game) {
		control.addObserver(this);
	}

	public void onReset(TableroInmutable tab) {
		System.out.println(tab);
	}

	public void onMove(TableroInmutable tab) {
		System.out.println(tab);
	}

	public void onError(String msg) {
		System.err.println(msg);
	}

	public void onGameOver(Ficha tab) {
		System.out.println("Partida terminada");
	}

	public void onUndo(TableroInmutable tab) {
	}

	public void onTurno(Ficha turno) {
	}

	public void onNotUndo(boolean vacia) {
	}
}
