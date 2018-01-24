package tp.pr5.logica;

/**
 * Interface que trabaja sobre 
 * el tablero de partida accediendo a su informaci�n
 * pero impidiendo trabajar sobre el tablero 
 * directamente
 */
public interface TableroInmutable {
	/**
	 * Get casilla; recibe la ficha 
	 * que se encuentra en la casilla
	 * @param int fil fila del tablero
	 * @param int col columna del tablero
	 * @return Ficha Color de la casilla
	 */
	public Ficha getCasilla(int fil, int col);
	/**
	 * Ancho del tablero
	 * @return int ancho del tablero
	 */
	public int getAncho();
	/**
	 * Alto del tablero
	 * @return int alto alto dle tablero
	 */
	public int getAlto();
}
