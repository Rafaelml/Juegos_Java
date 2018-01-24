package tp.pr5.control.comandos;

import tp.pr5.control.Comando;

/**
 * Clase que realiza las acciones para finalizar la partida y salir.
 * 
 * No ejecuta nada, pero terminar() devuelve TRUE, lo que indica que la partida
 * finaliza
 */
public class ComandoSalir implements Comando {
	public boolean analiza(String cmd) {
		return cmd.equals("salir");
	}

	public void ejecuta() {

	}

	public boolean terminar() {
		return true;
	}

	public String ayuda() {
		return "SALIR: termina la aplicación.\n";
	}
}
