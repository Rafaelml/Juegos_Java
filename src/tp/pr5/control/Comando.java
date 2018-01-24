package tp.pr5.control;

/**
 * Interface que define los metodos que se implementan en el patrón de comandos.
 */
public interface Comando {

	/**
	 * Metodo que compara el String cmd con la accion a realizar.
	 * 
	 * @param cmd
	 *            comando que se le pasa
	 * @return True de ser correcto el comando; False en caso contrario.
	 */
	public boolean analiza(String cmd);

	/**
	 * Metodo que realiza la accion pertinente en la partida que se está
	 * jugando.
	 * 
	 */
	public void ejecuta();

	/**
	 * Metodo que devuelve si la partida ha de terminase.
	 * 
	 * Es false en todos los casos, escepto en el ComandoSalir.
	 * 
	 * @return Si se ha terminado la partida.
	 */
	public boolean terminar();

	/**
	 * Metodo que devuelve la informacion del comando.
	 * 
	 * @return El String de ayuda correspondiente al comando
	 */
	public String ayuda();
}
