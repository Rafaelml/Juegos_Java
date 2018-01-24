package tp.pr5.logica.reglas;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class ReglasComplica extends ReglasComunes implements ReglasJuego {

	private final int ancho = 4;
	private final int alto = 7;

	public ReglasComplica() {

	}

	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		return comprobarCo(t);
	}

	public Tablero iniciaTablero() {
		return new Tablero(ancho, alto);
	}

	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return false;
	}
	
	/**
	 * M�todo que comprueba si existe 4 fichas en l�neas en el tablero para el
	 * juego complica.
	 * 
	 * @param x
	 *            posición horizontal de la ficha a analizar
	 * @param y
	 *            posición vertical de la ficha a analizar
	 * @param h
	 *            Analizando en dirección horizontal
	 * @param v
	 *            Analizando en dirección vertical
	 * @return Devuelve el color de la ficha si existen 4 fichas en l�neas.
	 */
	private Ficha recorrerCo(int x, int y, int h, int v, Tablero t) {
		Ficha ganador = Ficha.VACIA;
		int i = 0;

		int contNegras = 0;
		int contBlancas = 0;
		Ficha temp;
		while (!t.seSale(x + i * h, y + i * v)) {
			temp= t.getCasilla((x + i * h)+1, (y + i * v)+1);
			
			if (Ficha.NEGRA.equals(temp)){ /*(tablero[x + i * h][y + i * v])) {*/
				contNegras++;
				contBlancas = 0;

			} else if (Ficha.BLANCA.equals(temp)) {
				contBlancas++;
				contNegras = 0;
			}

			if (contNegras == 4)
				ganador = Ficha.NEGRA;
			if (contBlancas == 4)
				ganador = Ficha.BLANCA;

			i++;
		}
		return ganador;

	}
	
	/**
	 * M�todo que comprueba si existe 4 fichas en l�neas en el tablero
	 * Recorriendo todo el tablero y si hay dos fichas con el 4 en lineas y del
	 * mismo color retorna Ficha.VACIA.
	 * 
	 * @return Ficha color del ganador ,vacia si no hay.
	 */
	public Ficha comprobarCo(Tablero t) {
		Ficha acumulado = Ficha.VACIA;
		int contNegras = 0;
		int contBlancas = 0;
		int x = 0;
		int y = 0;
		while (y < alto) {
			acumulado = recorrerCo(0, y, 1, 0, t);
			if (acumulado.equals(Ficha.BLANCA))
				contBlancas++;
			if (acumulado.equals(Ficha.NEGRA))
				contNegras++;
			y++;
		}// vertical
		while (x < ancho) {
			acumulado = recorrerCo(x, 0, 0, 1, t);
			if (acumulado.equals(Ficha.BLANCA))
				contBlancas++;
			if (acumulado.equals(Ficha.NEGRA))
				contNegras++;
			x++;
		}// horizontal

		x = 0;
		y = 0;
		while (y < alto) {
			acumulado = recorrerCo(0, y, 1, 1, t);
			if (acumulado.equals(Ficha.BLANCA))
				contBlancas++;
			if (acumulado.equals(Ficha.NEGRA))
				contNegras++;
			y++;
		}// diagonal

		x = 0;
		y = 0;

		while (y < alto) {

			acumulado = recorrerCo(0, y, 1, -1, t);
			if (acumulado.equals(Ficha.BLANCA))
				contBlancas++;
			if (acumulado.equals(Ficha.NEGRA))
				contNegras++;
			y++;
		} // diagonal
		if (contBlancas > 0 && contNegras == 0)
			acumulado = Ficha.BLANCA;
		else if (contNegras > 0 && contBlancas == 0)
			acumulado = Ficha.NEGRA;
		else
			acumulado = Ficha.VACIA;
		return acumulado;
	}


}
