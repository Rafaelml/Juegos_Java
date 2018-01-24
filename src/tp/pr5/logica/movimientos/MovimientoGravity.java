package tp.pr5.logica.movimientos;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;

public class MovimientoGravity extends Movimiento {

	public MovimientoGravity(int col, int fil, Ficha color) {
		this.col = col;
		this.fil = fil;
		this.turno = color;
	}

	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		int lado = 0, colR = col;
		boolean fin = false, libre = false;
		int i = 0, x = 0, y = 0;
		int ancho = tab.getAncho();
		int alto = tab.getAlto();
		int filR = fil;
		
		/*
		 * Se comprueba si la casilla elegida ya está ocupada, y si es así se lanza una excepcion.
		 */
		if (!tab.getCasilla(colR, filR).equals(Ficha.VACIA)) {

			throw new MovimientoInvalido("Casilla ocupada.");
		}

		if (col > 0 && col <= ancho && fil > 0 && fil <= alto
				/*&& tab.getCasilla(colR, filR).equals(Ficha.VACIA)*/) {

			
			lado = distancia(alto, ancho);
			/*
			 * según el lado que devuelva, se va a desplazar la ficha en una u otra direccion del tablero.
			 */
			switch (lado) {
			case 1:
				x = -1;
				break;
			case 2:
				y = -1;
				break;
			case 3:
				x = 1;
				break;
			case 4:
				y = 1;
				break;
			case 5:
				x = -1;
				y = -1;
				break;
			case 6:
				x = 1;
				y = -1;
				break;
			case 7:
				x = 1;
				y = 1;
				break;
			case 8:
				x = -1;
				y = 1;
				break;
			default:
				fin = true;
			}
			

			
			while (!fin && !libre) {
			
				colR = (col + i * x);
				filR = (fil + i * y);
			/*
			 *Si se encuentra una posicion opcuipada, fin se vuelve true y se deja de desplazar.
			 *Si se llega a los extremos del tablero, libre se vuelve true 
			 */
				
				if (!Ficha.VACIA.equals(tab.getCasilla(colR, filR)))
					fin = true;
				else if (colR == ancho || colR == 1 || filR == alto
						|| filR == 1)
					libre = true;
				i++;

			}
			
			/*
			 * se actualiazan fil y col con la posicion definitiva del movimiento desplazado
			 */
			
			if (!libre) {
				col = (colR - 1 * x);
				fil = (filR - 1 * y);
				tab.setCasilla(col, fil, turno);
			}

			else {
				tab.setCasilla(colR, filR, turno);
				col = colR;
				fil = filR;
			}

		} else
			throw new MovimientoInvalido("Posición incorrecta.");

	}

	public int getFil() {
		return fil;
	}

	public Ficha getJugador() {
		return turno;
	}

	public int getCol() {
		return col;
	}

	public void undo(Tablero tab) {
		tab.setCasilla(col, fil, Ficha.VACIA);
	}

	private int distancia(int alto, int ancho) {
		int pos = 0;
		int dis1, dis2, dis3, dis4;
		dis1 = fil - 1;
		dis2 = alto - fil;
		dis3 = col - 1;
		dis4 = ancho - col;
		
		/*
		 * Apartir de las cuatro distancias a los lados, calculamos 
		 * la posicion mas cercana a la cual debe desplazarse. 
		 */
		
		
		/* 		 5 ______2______ 6
		 *		 |       ^     | 
		 * 		 |  dis1 I     |
		 * 		 |       I dis4|
		 * 		1|<----->X<--->|3
		 * 		 | dis3  ^     |
		 *	 	 |		 I     |
		 *	 	 |       Idis2 |
		 *	 	 |_______I_____|
		 * 		8   	4       7
		 */

		if (dis3 < dis1 && dis3 < dis2 && dis3 < dis4)
			pos = 1;
		
		else if (dis1 < dis2 && dis1 < dis3 && dis1 < dis4)
			pos = 2;
		
		else if (dis4 < dis1 && dis4 < dis2 && dis4 < dis3)
			pos = 3;
		
		else if (dis2 < dis1 && dis2 < dis3 && dis2 < dis4)
			pos = 4;

		
		else if (dis1 == dis3 && (dis1 < dis2 || dis3 < dis4))
			pos = 5;
		
		else if (dis2 == dis4 && (dis2 < dis1 || dis4 < dis3))
			pos = 7;

		else if (dis1 == dis4 && (dis4 < dis3 || dis1 < dis2))
			pos = 6;
		
		else if (dis2 == dis3 && (dis3 < dis4 || dis2 < dis1))
			pos = 8;

		
		if ((dis1 == dis3 && (dis1 < dis2 || dis3 < dis4))
				&& (dis2 == dis3 && (dis3 < dis4 || dis2 < dis1)))
			pos = 1;
		
		else if ((dis1 == dis4 && (dis4 < dis3 || dis1 < dis2))
				&& (dis2 == dis4 && (dis2 < dis1 || dis4 < dis3)))
			pos = 3;
		
		else if ((dis2 == dis3 && (dis3 < dis4 || dis2 < dis1))
				&& (dis2 == dis4 && (dis2 < dis1 || dis4 < dis3)))
			pos = 4;
		
		else if ((dis1 == dis3 && (dis1 < dis2 || dis3 < dis4))
				&& (dis1 == dis4 && (dis4 < dis3 || dis1 < dis2)))
			pos = 2;

		return pos;
	}

}
