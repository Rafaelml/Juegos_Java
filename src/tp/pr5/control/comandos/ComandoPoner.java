package tp.pr5.control.comandos;

import tp.pr5.control.Comando;
import tp.pr5.control.ControladorConsola;
import tp.pr5.control.jugadores.Jugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Partida;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.movimientos.MovimientoInvalido;

/**
 * Clase que se utiliza para realizar un movimiento.
 */
public class ComandoPoner implements Comando  {
	private Movimiento mov;
	private Partida partida;
	private Tablero tab;
	private ControladorConsola control;

	public ComandoPoner(ControladorConsola control) {
		this.control = control;
	}

	public boolean analiza(String cmd) {
		return cmd.equals("poner");
	}


	/**
	 * Metodo que realiza un movimiento en una posicion idicada. De no poder
	 * hacerse enviara el mensaje correspondiente.
	 * 
	 * @param partida
	 *            Partida a la que se está jugando
	 * @param tipo
	 *            Identifica el tipo de juego al que se está jugando.
	 * @param sc
	 *            Recibe la información que se está pasando desde consola.
	 */
	public void ejecuta() {
		Jugador jugadorBlancas = control.getJugadorBlanca();
		Jugador jugadorNegras = control.getJugadorNegra();
		boolean terminada = false;
		Ficha ganador;
		partida = control.getPartida();
		tab = partida.getTablero();

		try {
			if (partida.getTurno().equals(Ficha.BLANCA))
				mov = jugadorBlancas.getMovimiento(tab, Ficha.BLANCA);
			else
				mov = jugadorNegras.getMovimiento(tab, Ficha.NEGRA);
			
			if(mov!=null)partida.ejecutaMovimiento(mov);
			
		} catch (MovimientoInvalido e) {

			System.err.println(e.getMessage());
		}
		terminada = partida.isTerminada();
		ganador = partida.getGanador();
		if (terminada)
			System.out.print(partida.toString());

		if (terminada && ganador.equals(Ficha.VACIA))
			System.out.println("\nPartida terminada en tablas.");
		else if (terminada && ganador.equals(Ficha.NEGRA))
			System.out.println("\nGanan las negras");
		else if (terminada && ganador.equals(Ficha.BLANCA))
			System.out.println("\nGanan las blancas");

	}

	public boolean terminar() {
		return false;
	}

	public String ayuda() {
		return "PONER: utilízalo para poner la siguiente ficha.\n";
	}
}
