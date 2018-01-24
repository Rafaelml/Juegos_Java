package tp.pr5.logica.movimientos;

import java.awt.Point;
import java.util.ArrayList;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.validasReversi;

/**
 * Clase que implementa el movimiento para el juego del Rebersi. Se deben
 * implementar los m�todos abstractos de la clase padre.
 * */
public class MovimientoReversi extends Movimiento {

	/**
	 * M�todo Constructor de la clase MovimientoComplica.
	 */
	private ArrayList<Point> pintadas = new ArrayList<Point>();

	public MovimientoReversi(int x, int y, Ficha color) {
		col = x;
		fil = y;
		turno = color;
	}

	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {

		if (!tab.getCasilla(col, fil).equals(Ficha.VACIA)) {
			throw new MovimientoInvalido("Casilla Ocupada.");
		} else if (validasReversi.getValida(this)) {
			comprobarCasilla(tab, true);
			recorrerTablero(tab);
		} else if (validasReversi.vacio()) {
			recorrerTablero(tab);
			// si no hay movimientos validos, pasará el turno al cotnrario
		}
		
		else {
			throw new MovimientoInvalido("Movimiento no valido.");
		}
	}

	public void undo(Tablero tab) {

		Point p;
		int index;
		Ficha opuesto = turno.cambiar();

		for (index = pintadas.size() - 1; index >= 0; index--) {

			p = pintadas.get(index);
			tab.setCasilla(p.x, p.y, opuesto);
			pintadas.remove(index);

		}
		tab.setCasilla(col, fil, Ficha.VACIA);
		validasReversi.clear();
		turno = turno.cambiar();
		recorrerTablero(tab);

	}

	/**
	 * Devuelve la columna correspondiente.
	 * 
	 * @return Columna correspondiente
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Devuelve el color de la ficha del jugador que le corresponde jugar.
	 * 
	 * @return Devuelve la ficha correspondiente.
	 */
	public Ficha getJugador() {
		return turno;
	}

	/**
	 * M�todo que cuenta cu�ntas fichas existen en el camino a encontrar una
	 * ficha opuesta
	 * 
	 * @param h
	 *            horizontal
	 * @param v
	 *            vertical
	 * @param t
	 *            tablero sobre el que se esta jugando
	 * @return cont contador de fichas
	 */
	private int comprobarDireccion(int h, int v, Tablero t) {
		int cont = 0;
		int x = 1, y = 1;
		Ficha opuesta = turno.cambiar();
		boolean fin = false;
		Ficha temp;

		if (h == 0 && v == 0) {
			fin = true;
			cont = -1;
		}
		do {
			temp = t.getCasilla(col + x * h, fil + y * v);

			if (temp.equals(opuesta)) {
				cont++;
				x++;
				y++;
			} else if (temp.equals(Ficha.VACIA)) {
				cont = 0;
				fin = true;
			} else
				fin = true;

		} while (!fin);
		return cont;
	}

	/**
	 * Comprueba las casillas que se pueden cambiar segun las reglas de reversi
	 * y las voltea en caso de ser positivo devulve true en caso de ser valido
	 * 
	 * @param t
	 *            tablero sobre el que se est� jugando
	 * @param pintar
	 *            booleano con la validacion de voltear
	 * @return true en caso de ser valido
	 */
	public boolean comprobarCasilla(Tablero t, boolean pintar) {
		int num;
		boolean valido = false;

		if (t.getCasilla(col, fil).equals(Ficha.VACIA)) {
			for (int h = -1; h < 2; h++) {
				for (int v = -1; v < 2; v++) {

					num = comprobarDireccion(h, v, t);

					if (num != 0) {
						valido = true;
						if (pintar)
							pintar(h, v, num, t);
					}
				}
			}
		}
		return valido;
	}

	/**
	 * M�todo que le da las vueltas a las casillas y pinta las nuevas;
	 * modificando de manera eficiente el arraylist.
	 * 
	 * @param h
	 *            horizontal
	 * @param v
	 *            vertical
	 * @param num
	 *            cantidad de fichas a voltear
	 * @param t
	 *            tablero sobre el que se esta jugando
	 */
	private void pintar(int h, int v, int num, Tablero t) {

		int cont = 0;
		int x = 1, y = 1;
		t.setCasilla(col, fil, turno);
		do {
			pintadas.add(new Point(col + x * h, fil + y * v));
			t.setCasilla(col + x * h, fil + y * v, turno);

			cont++;
			x++;
			y++;
		} while (cont < num);
	}

	/**
	 * M�todo que recorre el tablero entero modificando de manera eficiente el
	 * ArrayList de puntos.
	 */
	public void recorrerTablero(Tablero t) {
		int ancho = t.getAncho();
		int alto = t.getAlto();
		MovimientoReversi mov;
		Ficha opuesto = turno.cambiar();

		validasReversi.clear();
		for (int i = 1; i <= ancho; i++) {
			for (int j = 1; j <= alto; j++) {
				mov = new MovimientoReversi(i, j, opuesto);
				if (mov.comprobarCasilla(t, false))
					validasReversi.add(new Point(i, j));
			}
		}

	}

}

