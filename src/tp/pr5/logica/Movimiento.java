package tp.pr5.logica;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.movimientos.MovimientoInvalido;

/**
 * Clase que representa el movimiento de un jugador. Tiene un método para
 * ejecutar el movimiento sobre la partida, y otro para deshacerlo. Es una clase
 * abstracta; habrá una clase no abstracta por cada tipo de juego soportado.
 */

public abstract class Movimiento {
	public int getFil() {
		return fil;
	}

	protected int col;
	protected int fil;
	protected Ficha turno;

	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como par�metro. Se
	 * puede dar por cierto que tablero recibido sigue las reglas del tipo de
	 * juego al que pertenece el movimiento. En caso contrario, el
	 * comportamiento es indeterminado. Parameters: tab - Tablero sobre el que
	 * ejecutar el movimiento Returns: true si todo fue bien. Se devuelve false
	 * si el movimiento no puede ejecutarse sobre el tablero.
	 * 
	 * @param Tablero
	 *            en el que se está jugando.
	 */
	public abstract void ejecutaMovimiento(Tablero tab)
			throws MovimientoInvalido;

	/**
	 * Devuelve el color de la ficha del jugador que le corresponde jugar.
	 * 
	 * @return Devuelve la ficha correspondiente.
	 */
	public abstract Ficha getJugador();

	/**
	 * Devuelve la columna correspondiente.
	 * 
	 * @return Columna correspondiente
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Deshace el movimiento en el tablero recibido como par�metro. Se puede
	 * dar por cierto que el movimiento se ejecut� sobre ese tablero; en caso
	 * contrario, el comportamiento es indeterminado. Por lo tanto, es de
	 * suponer que el m�todo siempre funcionar� correctamente.
	 * 
	 * @param tablero
	 *            Tablero en el que se está jugando.
	 */

	public abstract void undo(Tablero tab);

	public void recorrerTablero(Tablero tablero) {
		// TODO Auto-generated method stub
		
	}

}
