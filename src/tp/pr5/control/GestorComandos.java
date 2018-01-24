package tp.pr5.control;

import java.util.Scanner;

import tp.pr5.control.comandos.ComandoAyuda;
import tp.pr5.control.comandos.ComandoDeshacer;
import tp.pr5.control.comandos.ComandoJugador;
import tp.pr5.control.comandos.ComandoJugar;
import tp.pr5.control.comandos.ComandoPoner;
import tp.pr5.control.comandos.ComandoReiniciar;
import tp.pr5.control.comandos.ComandoSalir;
import tp.pr5.gui.logica.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;

/**
 * Clase que realiza la función de un patrón de comandos.
 */
public class GestorComandos implements Observer{
	private Comando[] comandos;
	private int numComandos = 7;
	private Scanner sc;
	private String ayuda = "AYUDA: muestra esta ayuda.\n";
	

	/**
	 * Método Constructor de la clase GestorComandos. Inicializando las
	 * variables a sus valores iniciales.Almacena todos los comandos posibles de
	 * la partida.
	 * 
	 * @param control
	 *            Recibe la información que se está pasando desde consola.
	 */
	public GestorComandos(ControladorConsola control)  {
		sc = control.getIn();
		comandos = new Comando[numComandos];
		comandos[0] = new ComandoDeshacer(control.getPartida());
		comandos[1] = new ComandoJugador(control);
		comandos[2] = new ComandoJugar(control);
		comandos[3] = new ComandoPoner(control);
		comandos[4] = new ComandoReiniciar(control);
		comandos[5] = new ComandoSalir();
		comandos[6] = new ComandoAyuda(todaayuda(ayuda));
		control.addObserver(this);
		
	}

	/**
	 * Metodo que comprueba si se ha pasado correctamente un comando. De ser así
	 * realiza la función correspondiente. De no ser así muestra un mensaje de
	 * error.
	 * 
	 * @return Si se a podida ejecutar el comando.
	 */
	public boolean ejecuta() {
		boolean valido = false;
		boolean terminar = false;
		int i = 0;
		String cmd = sc.nextLine();
		/*
		 * Recorre el array de comandos hasta encontrar el que lo puede
		 * ejecutar, lo ejecuta y comprueba si la partida ha de terminar. Si se
		 * ha cambiado de juego, se modifica el parametro Tipo que lo indica. Si
		 * no encuentra ninguno que lo ejecute, muestra un mensaje de error.
		 */

		while (i < comandos.length && !valido) {
			valido = comandos[i].analiza(cmd);
			i++;
		}
		if (!valido)
			System.err.println("No te entiendo.");
		else {
			comandos[i - 1].ejecuta();
			terminar = comandos[i - 1].terminar();
		}

		return terminar;
	}

	/**
	 * Muestra la ayuda Recorre el array de comandos llamando al metodo ayuda()
	 * 
	 * @param ayuda
	 *            String de cabecera
	 * @return String con todas las ayudas.
	 */

	public String todaayuda(String ayuda) {
		int i = 0;
		while (i < comandos.length - 1) {
			ayuda += comandos[i].ayuda();
			i++;
		}
		return ayuda;
	}

	@Override
	public void onReset(TableroInmutable tab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onMove(TableroInmutable tab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String msg) {
		System.err.println(msg+"\n");
	}

	@Override
	public void onGameOver(Ficha fich) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUndo(TableroInmutable tab) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onTurno(Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onNotUndo(boolean vacia) {
		// TODO Auto-generated method stub
		
	}
}
