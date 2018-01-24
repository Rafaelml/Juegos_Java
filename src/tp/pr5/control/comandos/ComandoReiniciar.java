package tp.pr5.control.comandos;

import tp.pr5.control.Comando;
import tp.pr5.control.ControladorConsola;
import tp.pr5.logica.Partida;

/**
 * Clase que se utiliza para reinicia la partida.
 */
public class ComandoReiniciar implements Comando {
	private ControladorConsola control;
	private Partida partida;

	public ComandoReiniciar(ControladorConsola control) {
		this.control = control;
		partida = control.getPartida();
	}

	public boolean analiza(String cmd) {
		return cmd.equals("reiniciar");
	}

	/**
	 * Metodo que reinicia la partida a la que se está jugando.
	 * 
	 * @param partida
	 *            Partida a la que se está jugando
	 * @param tipo
	 *            Identifica el tipo de juego al que se está jugando.
	 * @param sc
	 *            Recibe la información que se está pasando desde consola.
	 */
	public void ejecuta() {
		partida.reset(control.getFactoria().creaReglas());
		System.out.println("Partida reiniciada.");
	}

	public boolean terminar() {
		return false;
	}

	public String ayuda() {
		return "REINICIAR: reinicia la partida.\n";
	}
}
