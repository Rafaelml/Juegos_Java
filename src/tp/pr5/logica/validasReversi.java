package tp.pr5.logica;

import java.awt.Point;
import java.util.ArrayList;

import tp.pr5.logica.movimientos.MovimientoReversi;

/**
 * Clase final que permite saber si existen movimientos validos en un tablero
 * sobre el que se juega reversi
 */
public final class validasReversi {
	private static ArrayList<Point> validas = new ArrayList<Point>();

	/**
	 * M�todo que permite saber si un movimiento es valido para reversi
	 * 
	 * @return Punto donde el movimiento es valido
	 */
	public static Point getValida() {
		int pos = (int) (Math.random() * validas.size());
		Point cas = validas.get(pos);
		return cas;
	}

	/**
	 * M�todo para agregar un punto al arraylist de puntos
	 * 
	 * @param p
	 */
	public static void add(Point p) {
		validas.add(p);
	}

	/**
	 * M�todo que valida movimientos retornando true de ser posible , falso en
	 * caso contrario
	 * 
	 * @param mov
	 *            Movimiento a ejecutar
	 * @return Devuelve true si el movimiento es v�lido
	 */
	public static boolean getValida(MovimientoReversi mov) {
		Point temp;
		Boolean valida = false;
		int cont = 0;
		int max = validas.size();

		while (!valida && cont < max) {
			temp = validas.get(cont);
			if (temp.x == mov.col && temp.y == mov.fil)
				valida = true;
			cont++;
		}
		return valida;

	}

	/**
	 * Rellena el arrayList con los primeros movimientos validos del reversi
	 */
	public static void init() {
		validas.clear();
		validas.add(new Point(4, 3));
		validas.add(new Point(3, 4));
		validas.add(new Point(6, 5));
		validas.add(new Point(5, 6));
	}

	/**
	 * Vac�a el arrayList
	 */
	public static void clear() {
		validas.clear();
	}

	/**
	 * M�todo que comprueba si un movimiento es valido en una posici�n dada
	 * 
	 * @param x
	 *            columna
	 * @param y
	 *            fila
	 * @return true en caso de ser valido
	 */
	public static boolean getValida(int x, int y) {
		Point temp;
		Boolean valida = false;
		int cont = 0;
		int max = validas.size();

		while (!valida && cont < max) {
			temp = validas.get(cont);
			if (temp.x == x + 1 && temp.y == y + 1)
				valida = true;
			cont++;
		}
		return valida;
	}

	/**
	 * M�todo que permite saber si el ArrayList se encuentra vac�o
	 * 
	 * @return true en caso de ser verdadero
	 */
	public static boolean vacio() {
		return validas.size() == 0;
	}
	
	public static boolean comprobarMov(Tablero t, Ficha turno, int i, int j){
		boolean valido =false;
		int ancho = t.getAncho();
		int alto = t.getAlto();
		MovimientoReversi mov= new MovimientoReversi(i,j,turno);
		
		for (int x = 1; x <= ancho; x++) {
			for (int y = 1; y <= alto; y++) {
				if (mov.comprobarCasilla(t, false))	valido=true;
			}
		}
		return valido;
	}
}
