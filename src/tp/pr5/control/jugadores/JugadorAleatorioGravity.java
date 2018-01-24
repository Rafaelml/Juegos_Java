package tp.pr5.control.jugadores;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.movimientos.MovimientoGravity;
/**
 *Jugador que juega de forma aleatoria a Gravity. 
 *Simplemente elige una posici�n aleatoria en el tablero, 
 *comprobando antes que esa casilla est� vac�a (se podr� poner).
 */
public class JugadorAleatorioGravity implements Jugador {

	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		MovimientoGravity mov;
		int ancho = tab.getAncho() + 1;
		int alto = tab.getAlto() + 1;
		int fil = (int) (Math.random() * alto + 1);
		int col = (int) (Math.random() * ancho + 1);
		
		while (comprobar(col, fil, ancho, alto, tab)) {
			col = (int) (Math.random() * ancho + 1);
			fil = (int) (Math.random() * alto + 1);
		}
		mov = new MovimientoGravity(col, fil, color);
		return mov;
	}

	/**
	 * Comprueba que la columna y fila sea correcta y la posicion no este
	 * ocupada
	 * 
	 * @param col
	 * @param fil
	 * @param ancho
	 * @param tablero
	 *            donde se ejecutaria
	 * @return true si es valido
	 */
	private boolean comprobar(int col, int fil, int ancho, int alto, Tablero tab) {
		boolean comprobar = true;
		if (col > 0 && col < ancho && fil > 0 && fil < alto
				&& tab.getCasilla(col, fil).equals(Ficha.VACIA)) {
			comprobar = false;
		}
		return comprobar;
	}

}
