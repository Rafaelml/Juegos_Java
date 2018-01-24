package tp.pr5.control;

import tp.pr5.control.factorias.FactoriaComplica;
import tp.pr5.control.factorias.FactoriaConecta4;
import tp.pr5.control.factorias.FactoriaGravity;
import tp.pr5.control.factorias.FactoriaReversi;
import tp.pr5.control.factorias.FactoriaTipoJuego;
import tp.pr5.control.jugadores.Jugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Partida;
import tp.pr5.logica.movimientos.MovimientoInvalido;

/**
 * Clase que controla la ejecuci�n de la partida.
 */
public class ControladorVista extends Controlador {
	/**
	 * Constructor de la clase.
	 * 
	 * @param factoria
	 *            - Factoria de Tipo juego
	 * @param partida
	 *            - Partida a la que se jugar�.
	 */
	public ControladorVista(FactoriaTipoJuego f, Partida partida) {
		this.partida = partida;
		this.factoria = f;
	}

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
	public void movimiento(int col, int fil, Ficha color)
			throws MovimientoInvalido {
		try {
			Movimiento mov = factoria.creaMovimiento(col, fil, color);

			 partida.ejecutaMovimiento(mov);
		} catch (MovimientoInvalido e1) {
			e1.printStackTrace();
		}

	}

	/**
	 * Método que reinicia la partida.
	 */
	public void reset() {
		partida.reset(factoria.creaReglas());
	}

	/**
	 * Método que crea un movimiento aleatorio
	 */
	public void movAleatorio() {
		
				
			Jugador jugador = factoria.creaJugadorAleatorio();
			try {
				Movimiento mov = jugador.getMovimiento(partida.getTablero(),
						partida.getTurno());
				
				if(mov!=null) partida.ejecutaMovimiento(mov);
				else partida.cedeTurno();
				
				
			} catch (MovimientoInvalido e) {
				e.printStackTrace();
			}
		
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
	public void cambiarJuego(String opc, int cols, int fils) {

		if (opc.equals("Conecta Cuatro")) {
			this.factoria = new FactoriaConecta4();
		} else if (opc.equals("Complica")) {
			this.factoria = new FactoriaComplica();
		} else if (opc.equals("Gravity")) {
			this.factoria = new FactoriaGravity(cols, fils);
		}
		else if (opc.equals("Reversi")) {
		this.factoria = new FactoriaReversi();
		}
				
		reset();
	}
			
	}
		

