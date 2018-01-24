package tp.pr5.control.comandos;

import java.util.Scanner;

import tp.pr5.control.Comando;
import tp.pr5.control.ControladorConsola;
import tp.pr5.control.factorias.FactoriaTipoJuego;
import tp.pr5.control.jugadores.Jugador;

public class ComandoJugador implements Comando {
	private String coman[];
	private FactoriaTipoJuego factoria;
	private Scanner sc;
	private ControladorConsola control;

	public ComandoJugador(ControladorConsola control) {
		sc = control.getIn();
		this.control = control;
	}

	public boolean analiza(String cmd) {
		coman = cmd.split(" ");
		return coman[0].equals("jugador");
	}

	/**
	 * Metodo para cambiar a aleatorio o a humano un jugador.
	 * 
	 * Se comparara el string para saber si se quiere un jugador aleatorio o uno
	 * humano y en consecuencia se crea uno u otro.
	 * 
	 */
	public void ejecuta() {

		Jugador jugador = null;
		factoria = control.getFactoria();

		if (coman[2].equals("aleatorio")) {
			jugador = factoria.creaJugadorAleatorio();
		} else if (coman[2].equals("humano")) {
			jugador = factoria.creaJugadorHumanoConsola(sc);
		}

		if (coman[1].equals("blancas")) {
			control.setJugadorBlanca(jugador);
		} else if (coman[1].equals("negras")) {
			control.setJugadorNegra(jugador);
		}

	}

	public boolean terminar() {
		return false;
	}

	public String ayuda() {
		return "JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.\n";
	}

}
