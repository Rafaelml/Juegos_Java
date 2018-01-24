package tp.pr5.gui.logica;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;

/**
 * Interface Observer donde definimos los metodos del observable a implementar
 * en las clases observadas.
 */
public interface Observer {

	/**
	 * Metodo que realiza una accion determinada al detectar que se ha hecho
	 * reset.
	 * @param tablero inmutable que se modifica al resetear.
	 */
	public void onReset(TableroInmutable tab);

	/**
	 * Metodo que realiza una accion determinada al detectar que se ha ejecutado
	 * un movimiento.
	 * @param tablero inmutable que se modifica al realizar un movimiento.
	 */
	public void onMove(TableroInmutable tab);

	/**
	 * Metodo que realiza una accion determinada al detectar que se ha producido
	 * un error.
	 * @param String con el mensaje de error.
	 */
	public void onError(String msg);

	/**
	 * Metodo que realiza una accion determinada al detectar que se ha terminado
	 * la partida.
	 * @param Ficha del turno de la partida.
	 */
	public void onGameOver(Ficha fich);

	/**
	 * Metodo que realiza una accion determinada al detectar que se ha deshecho
	 * un movimiento.
	 *
	 */
	public void onUndo(TableroInmutable tab);

	/**
	 * Metodo que realiza una accion determinada al detectar que se ha cambiado
	 * de turno.
	 *  @param tablero inmutable que se modifica al deshacer un movimiento.
	 */
	public void onTurno(Ficha turno);

	/**
	 * Metodo que realiza una accion determinada al detectar que no es posible
	 * realizar un deshacer porque la pila de movimientos está vacia.
	 * @param boolean que se modifica si la pila de movimientos se vacia.
	 */
	public void onNotUndo(boolean vacia);
	
}
