package tp.pr5.control.jugadores;


/**
 * Jugador con car�cter y pensado exclusivamente
 * para la vista
 */
public interface JugadorSwing {
	/**
	 * Toma el turno del que le toque jugar en la partida
	 */
	public void tomarTurno();
	/**
	 * Cede el turno 
	 */
	public void cederTurno();
	/**
	 *Realiza un movimiento
	 */
	public void getMovimiento();
}
