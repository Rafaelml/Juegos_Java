package tp.pr5.control.comandos;

import tp.pr5.control.Comando;
import tp.pr5.logica.Partida;

/**
 * Clase que realiza las acciones para deshacer el ultimo movimiento.
 */
public class ComandoDeshacer implements Comando {

	private Partida partida;

	public ComandoDeshacer(Partida p) {
		partida = p;
	}

	public boolean analiza(String cmd) {
		return cmd.equals("deshacer");
	}

	/**
	 * Deshace el ultimo movimiento, si no se peude deshacer muestra un mensaje
	 * de error.
	 * 
	 * @param partida
	 *            partida a la que se está jugando
	 * @param tipo
	 *            Identifica el tipo de juego al que se está jugando.
	 * @param sc
	 *            Recibe la información que se está pasando desde consola.
	 */

	public void ejecuta() {
		if (!partida.undo())
			System.err.println("Imposible deshacer.");

	}

	public boolean terminar() {
		return false;
	}

	public String ayuda() {

		return "DESHACER: deshace el último movimiento hecho en la partida.\n";
	}

}
