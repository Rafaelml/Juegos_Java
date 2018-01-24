package tp.pr5.control.jugadores;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.movimientos.MovimientoConecta4;
/**
 * Jugador que juega de forma aleatoria a Conecta 4.
 *  Simplemente elige una columna aleatoria en el tablero, 
 *  comprobando antes que se podr� colocar en ella (no est� llena).
 */
public class JugadorAleatorioConecta4 implements Jugador {

	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		MovimientoConecta4 mov;
		int ancho = tab.getAncho() + 1;
		
		/*
		 * Mientras no sea valido el movimiento genera una nueva columna y una
		 * nueva fila
		 */

		int col = (int) (Math.random() * ancho);
		int fil = tab.buscarSuperior(col - 1);

		while (!comprobar(col, fil, ancho)) {
			col = (int) (Math.random() * ancho);
			fil = tab.buscarSuperior(col - 1);
		}
		mov = new MovimientoConecta4(col, color);
		return mov;
	}

	/**
	 * Comprueba que la columna y la fila sea correcta
	 * 
	 * @param col
	 * @param fil
	 * @param ancho
	 * @return true si es valido
	 */
	private boolean comprobar(int col, int fil, int ancho) {
		boolean comprobar = true;
		if (fil == -1 || col <= 0 || col > ancho)
			comprobar = false;
		return comprobar;
	}
}
