package tp.pr5.gui.logica;

/**
 * 
 * Interfaz generica para el patrón observer.
 *
 * @param <T>
 */
public interface Observable<T> {
	/**
	 * Añade un observador
	 * 
	 * @param o
	 *            un observador
	 */
	public void addObserver(T o);

	/**
	 * Elimina un observador
	 * 
	 * @param o
	 *            un observador
	 */
	public void removeObserver(T o);
}
