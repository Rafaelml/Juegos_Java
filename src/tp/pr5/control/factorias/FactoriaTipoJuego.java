package tp.pr5.control.factorias;

import tp.pr5.control.jugadores.Jugador;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.reglas.ReglasJuego;

/**Interfaz que aglutina los m�todos de construcci�n de los distintos objetos 
 * involucrados en un juego concreto.
Habr� una implementaci�n de este interfaz por cada tipo de juego soportado.
*/
public interface FactoriaTipoJuego {

	/**
	 * Construye el objeto Jugador capaz de jugar al juego concreto de forma
	 * aleatoria.
	 * 
	 * @Returns:Objeto jugador que juega de forma aleatoria.
	 */
	Jugador creaJugadorAleatorio();

	/**
	 * Crea un jugador humano
	 * 
	 * @param in
	 *            scanner de teclado
	 * @return jugador
	 */
	public Jugador creaJugadorHumanoConsola(java.util.Scanner in);

	/**
	 * Crea un movimiento
	 * 
	 * @param col
	 *            coumna del movimiento
	 * @param fil
	 *            fila del movimiento
	 * @return movimiento
	 */
	public Movimiento creaMovimiento(int col, int fila, Ficha color);

	/**
	 * Construye las reglas del juego concreto. Returns:El objeto que implementa
	 * las reglas del juego al que representamos.
	 */
	public ReglasJuego creaReglas();

}
