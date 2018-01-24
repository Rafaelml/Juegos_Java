package tp.pr5.logica;

import tp.pr5.logica.Ficha;

/**
 * Almacena la informaci�n de un tablero rectangular. El tama�o se fija en el
 * momento de la construcci�n, y contiene m�todos para acceder a la informaci�n
 * de cada celda y para colocar fichas.
 */
public class Tablero implements TableroInmutable {

	private int ancho;
	private int alto;
	private Ficha[][] tablero;

	/**
	 * Construye un tablero vac�o. De tamaño tx*ty, de no ser un tamaño valido,
	 * se contruiría de 1*1.
	 * 
	 * @param tx
	 *            - Tama�o en la coordenada x (o n�mero de columnas).
	 * @param ty
	 *            - Tama�o en la coordenada y y (o n�mero de filas).
	 */
	public Tablero(int tx, int ty) {
		if (tx < 1 || ty < 1) {
			tx = 1;
			ty = 1;
		}
		tablero = new Ficha[tx][ty];
		ancho = tx;
		alto = ty;
		reset();
	}

	/**
	 * M�todo para acceder a la informaci�n de una casilla o celda concreta.
	 * 
	 * @param x
	 *            - N�mero de columna (1..ancho)
	 * @param y
	 *            - N�mero de fila (1..alto)
	 * @return Informaci�n de la casilla. Si es inv�lida, devuelve Ficha.VACIA
	 */
	public Ficha getCasilla(int x, int y) {
		Ficha fich = Ficha.VACIA;

		if (x > 0 && x <= ancho && y > 0 && y <= alto) {
			fich = tablero[x - 1][y - 1];
		}
		return fich;

	}

	/**
	 * Permite especificar el nuevo contenido de una casilla. Tambi�n puede
	 * utilizarse para quitar una ficha
	 * 
	 * @param x
	 *            - N�mero de columna (1..ancho)
	 * @param y
	 *            - N�mero de fila (1..alto)
	 * @param color
	 *            - Color de la casilla.
	 */
	public void setCasilla(int x, int y, Ficha color) {
		if (1 <= x && x <= ancho && 0 <= y && y <= alto)
			tablero[x - 1][y - 1] = color;
	}

	/**
	 * M�todo que genera un tablero completado con Ficha.VACIA
	 */
	public void reset() {
		for (int x = 0; x < ancho; x++) {
			for (int y = 0; y < alto; y++) {
				tablero[x][y] = Ficha.VACIA;
			}
		}
	}

	/**
	 * M�todo que dibuja un tablero
	 * 
	 * @return cadena es un tablero dibujado
	 */
	public String toString() {
		String cadena = "";
		for (int y = 0; y <= alto - 1; y++) {
			cadena = cadena + "|";
			for (int x = 0; x < ancho; x++) {
				cadena = cadena + tablero[x][y].toString();

			}
			cadena = cadena + "|" + "\n";
		}
		cadena = cadena + pintarBase();

		return cadena;
	}

	/**
	 * M�todo para dibujar la base de un tablero
	 * 
	 * @return la base de un tablero
	 */
	/*
	 * private String pintarBase() { String base = ""; String nums = " "; base =
	 * base + "+"; for (int y = 0; y < ancho; y++) { base = base + "-"; nums =
	 * nums + (y + 1); } base = base + "+\n"; base = base + nums + "\n";
	 * 
	 * return base; }
	 */
	private String pintarBase() {
		String base = "";
		String nums = " ";
		base = base + "+";
		for (int y = 0; y < ancho; y++) {

			base = base + "-";
			if (y < 9)
				nums = nums + (y + 1);
			else if (y == 9)
				nums = nums + 0;
			else if (y == 10)
				nums = nums + 1;
			else
				nums = nums + (y % 10 + 1);

		}
		base = base + "+\n";
		base = base + nums + "\n";

		return base;
	}

	/**
	 * M�todo para obtener el ancho del tablero.
	 * 
	 * @return N�mero de columnas del tablero.
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * M�todo para obtener el alto del tablero.
	 * 
	 * @return N�mero de filas del tablero.
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * M�todo para encontrar donde terminar�a una ficha pasada por una columna
	 * dada.
	 * 
	 * @param x
	 *            - N�mero de columna (1..ancho)
	 * @return La posici�n donde quedar�a la ficha y. -1 si la columna esta
	 *         llena.
	 */
	public int buscarSuperior(int col) {
		int i = alto - 1;
		boolean encontrado = false;

		while (i >= 0 && 0 <= col && col < ancho && !encontrado) {
			if (tablero[col][i].equals(Ficha.VACIA))
				encontrado = true;
			else
				i--;
		}
		if (!encontrado)
			i = -1;
		return i;
	}


	/**
	 * M�todo que comprueba si existe 4 fichas en l�neas en el tablero
	 * 
	 * @param x
	 *            posición horizontal de la ficha a analizar
	 * @param y
	 *            posición vertical de la ficha a analizar
	 * @param h
	 *            Analizando en dirección horizontal
	 * @param v
	 *            Analizando en dirección vertical
	 * @return Devuelve si existen 4 fichas en l�neas
	 */
	public boolean comprobar(int x, int y, int h, int v) {
		boolean linea = false;
		int i = -3;
		int cont = 0;

		while (i < 4 && cont < 4) {
			if (!seSale(x + i * h, y + i * v)) {
				if (tablero[x][y].equals(tablero[x + i * h][y + i * v]))
					cont++;
				else
					cont = 0;
			}
			i++;
		}
		if (cont == 4)
			linea = true;

		return linea;
	}

	/**
	 * M�todo que comprueba si el tablero esta lleno
	 * 
	 * @return Devuelve si el tablero esta lleno o no
	 */
	public boolean comprobarLleno() {
		boolean lleno = true;
		int cont = 0;
		for (int i = 0; i < ancho; i++)
			cont += buscarSuperior(i);
		if (cont != -ancho)
			lleno = false;
		return lleno;
	}

	/**
	 * M�todo que comprueba si el tablero esta vac�o
	 * 
	 * @return Devuelve si el tablero esta vac�o o no
	 */
	public boolean comprobarVacio() {
		boolean vacio = true;
		int cont = 0;
		for (int i = 0; i < ancho; i++)
			cont += buscarSuperior(i);
		if (cont == ancho * (alto - 1))
			return vacio;
		else
			vacio = false;
		return vacio;
	}

	/**
	 * M�todo que comprueba si la posici�n que se pasa est� dentro o fuera del
	 * tablero
	 * 
	 * @param x
	 *            - N�mero de columna (1..ancho)
	 * @param y
	 *            - N�mero de fila (1..alto)
	 * @return Devuelve si la posici�n que se pasa est� dentro o fuera del
	 *         tablero
	 */
	public boolean seSale(int x, int y) {
		boolean sesale;
		if (x < 0 || x > ancho - 1 || y < 0 || y > alto - 1)
			sesale = true;

		else
			sesale = false;
		return sesale;
	}

	/**
	 * M�todo que desplaza el tablero para el juego complica
	 * 
	 * @param col
	 *            - N�mero de columna (1..ancho)
	 * @param op
	 *            -("-")Desplza hacia arriba la columna -("+")Desplaza hacia
	 *            abajo la columna
	 */

	public void desplazarArriba(int col) {
		for (int i = alto - 1; i > 0; i--)
			tablero[col][i] = tablero[col][i - 1];
	}

	public void desplazarabajo(int col) {
		for (int i = 0; i < alto - 1; i++)
			tablero[col][i] = tablero[col][i + 1];
	}
}
