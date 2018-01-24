package tp.pr5.logica.movimientos;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

/**
 * Clase que implementa el movimiento para el juego del Complica. Se deben
 * implementar los métodos abstractos de la clase padre.
 * */
public class MovimientoComplica extends Movimiento {

	private Ficha desp = Ficha.VACIA;

	/**
	 * Método Constructor de la clase MovimientoComplica.
	 */
	public MovimientoComplica(int donde, Ficha color) {
		this.col = donde - 1;
		this.turno = color;
	}

	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		fil = tab.buscarSuperior(col);

		/*
		 * Se comprueba que sea un movimiento valido. Si lo es, y la columna
		 * está completa, se guarda en el parametro desp de movimiento la ficha
		 * que se va a desplazar del tablero. Y se desplazan las fichas hacia
		 * abajo con el metodo desplazar indicando la columna y el char '+' que
		 * indica la direccion de desplazamiento. Una vez desplazado, se coloca
		 * la nueva ficha.
		 */

		if (col >= 0 && col < tab.getAncho() && fil < tab.getAlto()) {
			if (fil == -1) {
				fil = 0;
				desp = tab.getCasilla(col + 1, fil + 1);

				tab.desplazarArriba(col);
			}
			tab.setCasilla(col + 1, fil + 1, turno);
		} else {
			throw new MovimientoInvalido(
					"Columna incorrecta. Debe estar entre 1 y "
							+ tab.getAncho() + ".");
		}

	}

	public void undo(Tablero tab) {
		int y = tab.buscarSuperior(col) + 1;
		/*
		 * Si la columna está completa y se haya desplazado una ficha, se
		 * desplaza hacia arriba y se coloca en la fila mas baja de la columna
		 * la ficha que habia sido desplazada al realizarse el movimiento que se
		 * está deshaciendo. Si la columna no está llena, simplemente se vacia
		 * la ultima ficha colocada.
		 */
		if (y == 0) {

			if (!desp.equals(Ficha.VACIA)) {
				tab.desplazarabajo(col);
				tab.setCasilla(col + 1, tab.getAlto(), desp);
			} else
				tab.setCasilla(col + 1, y + 1, Ficha.VACIA);

		} else
			tab.setCasilla(col + 1, y + 1, Ficha.VACIA);
	}

	/**
	 * Devuelve la columna correspondiente.
	 * 
	 * @return Columna correspondiente
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Devuelve el color de la ficha del jugador que le corresponde jugar.
	 * 
	 * @return Devuelve la ficha correspondiente.
	 */
	public Ficha getJugador() {
		return turno;
	}

}
