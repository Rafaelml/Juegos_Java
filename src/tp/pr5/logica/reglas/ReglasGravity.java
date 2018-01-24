package tp.pr5.logica.reglas;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class ReglasGravity extends ReglasComunes implements ReglasJuego {

	private int ancho = 10;
	private int alto = 10;

	public ReglasGravity(int x, int y) {
		ancho = x;
		alto = y;
	}

	public ReglasGravity() {
	}

	public Tablero iniciaTablero() {
		return new Tablero(ancho, alto);
	}

	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha ganador = Ficha.VACIA;
		ganador = comprobarGanadorGr(ultimoMovimiento.getCol(), 
				ultimoMovimiento.getFil(), t);

		return ganador;
	}
	
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return t.comprobarLleno();
	}
	
	private Ficha recorrerGr(int x, int y, int h, int v, Tablero t) {
		Ficha ganador = Ficha.VACIA;
		int i = -3;

		int contNegras = 0;
		int contBlancas = 0;
		Ficha pos;

		/*
		 * En un radio de 3 posiciones apartir de movimiento, se comprueba en
		 * diagonales, vertical y horizontal si hay 4 fichas consecutivas del
		 * mismo colorDe ser así, el metodo devolverá el color del ganador.
		 */

		while (ganador.equals(Ficha.VACIA) && (x + i * h) <= ancho + 3
				&& (y + i * v) <= alto + 3) {
			pos = t.getCasilla(x + i * h, y + i * v);

			if (Ficha.NEGRA.equals(pos)) {
				contNegras++;
				contBlancas = 0;

			} else if (Ficha.BLANCA.equals(pos)) {
				contBlancas++;
				contNegras = 0;
			} else if (Ficha.VACIA.equals(pos)) {
				contBlancas = 0;
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
	 * Comprueba si hay ganador del juego gravity
	 * 
	 * @param x
	 *            columna
	 * @param y
	 *            fila
	 * @return ficha ganadora o vacia si no hay ganador
	 */
	private Ficha comprobarGanadorGr(int x, int y, Tablero t) {
		Ficha gana = Ficha.VACIA;

		gana = recorrerGr(x, y, 0, 1, t);
		if (gana.equals(Ficha.VACIA))
			gana = recorrerGr(x, y, 1, 0, t);
		if (gana.equals(Ficha.VACIA))
			gana = recorrerGr(x, y, 1, 1, t);
		if (gana.equals(Ficha.VACIA))
			gana = recorrerGr(x, y, 1, -1, t);

		return gana;
	}
	
}
