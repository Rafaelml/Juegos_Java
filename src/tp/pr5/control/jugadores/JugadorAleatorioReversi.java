package tp.pr5.control.jugadores;

import java.awt.Point;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.movimientos.MovimientoReversi;
import tp.pr5.logica.validasReversi;

/**
 *Jugador que juega de forma aleatoria a Reversi. 
 *Simplemente elige una posici�n aleatoria en el tablero, 
 *comprobando antes que esa casilla est� vac�a (se podr� poner).
 *y de que cumpla las leyes del juego
 */
public class JugadorAleatorioReversi implements Jugador {

	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		Point pos;
		MovimientoReversi mov=null;
		
		if(!validasReversi.vacio()){
			pos = validasReversi.getValida();
			mov= new MovimientoReversi(pos.x,pos.y, color);
		}
		
		return mov;
	}
}
