package tp.pr5.logica.reglas;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

/**
 * Interfaz que representa las reglas de un juego concreto. La partida delega en
 * un objeto de Este interfaz para hacer avanzar la partida.
 */
public interface ReglasJuego {

	/**
	 * Construye el tablero que hay que utilizar para la partida, seg�n las
	 * reglas del juego. Returns: Tablero a utilizar. El estado del tablero ser�
	 * el de inicio de la partida.
	 */
	public Tablero iniciaTablero();

	/**
	 * Devuelve el color del jugador que comienza la partida. Returns: Color del
	 * primer jugador en colocar ficha.
	 */
	public Ficha jugadorInicial();

	/**
	 * Permite averiguar si en la partida ya tenemos un ganador o no. Devuelve
	 * el color del ganador (si lo hay). Parameters: ultimoMovimiento - Ultimo
	 * movimiento realizado. Las distintas implementaciones pueden utilizar este
	 * par�metro para optimizar la b�squeda del ganador. t - Estado del tablero.
	 * Returns: color del ganador, si lo hay. Si no lo hay, devuelve Ficha.VACIA
	 * (eso NO significa necesariamente que la partida haya terminado en
	 * tablas).
	 * 
	 * @param mov
	 *            - ultimo movimientoejecutado
	 * @param tablero
	 *            - tablero donde se ejecuto el movimiento
	 * @return Color de la ficha del ganador.
	 */
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t);

	/**
	 * Metodo que devuelve true si, con el estado del tablero dado, la partida
	 * ha terminado en tablas.
	 * 
	 * @return true si la partida ha terminado sin ganador.
	 */
	public boolean tablas(Ficha ultimoEnPoner, Tablero t);

	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * 
	 * @param Ficha
	 *            ultima ficha insertada en el tablero
	 * @param tablero
	 *            donde se ejecuto el movimiento
	 * @return Color de la ficha del jugador correspondiente.
	 */
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t);

}
