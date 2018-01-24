package tp.pr5.logica.reglas;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class ReglasConecta4 extends ReglasComunes implements ReglasJuego {

	private final int ancho = 7;
	private final int alto = 6;

	public ReglasConecta4() {

	}

	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha gan = Ficha.VACIA;
		int x = ultimoMovimiento.getCol();

		if (comprobarGanador(x, t))
			gan = ultimoMovimiento.getJugador();
		return gan;
	}

	public Tablero iniciaTablero() {
		return new Tablero(ancho, alto);
	}
	
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		boolean tablas;
		if (ultimoEnPoner.equals(Ficha.BLANCA))
			tablas = false;
		else
			tablas = t.comprobarLleno();
		return tablas;
	}
	
	/**
	 * M�todo que comprueba si existe 4 fichas en l�neas en el tablero
	 * 
	 * @param x
	 *            - N�mero de columna (1..ancho)
	 * @param y
	 * @return Devuelve si existen 4 fichas en l�neas
	 */
	private boolean comprobarGanador(int x, Tablero t) {
		int y = t.buscarSuperior(x) + 1;

		return (t.comprobar(x, y, 0, 1) || t.comprobar(x, y, 1, 0)
				|| t.comprobar(x, y, 1, 1) || t.comprobar(x, y, -1, 1));
	}
	
}
