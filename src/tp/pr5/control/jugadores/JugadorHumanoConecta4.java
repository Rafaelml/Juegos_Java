package tp.pr5.control.jugadores;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.movimientos.MovimientoConecta4;

public class JugadorHumanoConecta4 implements Jugador {
	private Scanner sc;
	private MovimientoConecta4 mov;

	public JugadorHumanoConecta4(Scanner in) {
		this.sc = in;
	}

	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		System.out.print("Introduce la columna: ");
		int col = sc.nextInt();
		sc.nextLine();
		mov = new MovimientoConecta4(col, color);
		return mov;
	}
}
