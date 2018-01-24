package tp.pr5.logica.movimientos;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class MovimientoConecta4 extends Movimiento {

	public MovimientoConecta4(int donde, Ficha color) {
		this.col = donde - 1;
		this.turno = color;
	}

	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		fil = tab.buscarSuperior(col);
		String mensaje;
		/*
		 * Se comprueba que sea un movimiento valido. Si lo es, se coloca la
		 * nueva ficha.
		 */
		if (col >= 0 && col < tab.getAncho() && fil >= 0 && fil < tab.getAlto())
			tab.setCasilla(col + 1, fil + 1, turno);
		else {
			if (col >= 0 && col < tab.getAncho())
				mensaje = "Columna llena.";
			else
				mensaje = "Columna incorrecta. Debe estar entre 1 y "
						+ tab.getAncho() + ".";
			throw new MovimientoInvalido(mensaje);
		}
	}

	public void undo(Tablero tab) {
		int y = tab.buscarSuperior(col) + 1;
		/*
		 * Se busca la fila donde se colocó el ultimo movimiento y se coloca una
		 * ficha vacia.
		 */
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
