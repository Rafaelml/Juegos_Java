package tp.pr5.control.jugadores;

import java.util.Random;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.movimientos.MovimientoComplica;
/**
 *Jugador que juega de forma aleatoria a Complica. 
 *En este caso cualquier columna es v�lida, pues si est� llena, se har� hueco.
 */
public class JugadorAleatorioComplica implements Jugador {

	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		Random rnd = new Random();
		MovimientoComplica mov;
		int ancho = tab.getAncho() + 1;
		int col = rnd.nextInt(ancho);
		
		/*
		 * Mientras no sea valido el movimiento genera una nueva columna
		 */
		
		while (!comprobar(col, ancho)) {
			col = rnd.nextInt(ancho);
		}
		mov = new MovimientoComplica(col, color);
		return mov;
	}

	/**
	 * Comprueba que la columna sea correcta
	 * 
	 * @param col
	 * @param ancho
	 * @return true si es valido
	 */
	private boolean comprobar(int col, int ancho) {
		boolean comprobar;
		if (col <= 0 || col > ancho)
			comprobar = false;
		else
			comprobar = true;
		return comprobar;

	}
}
