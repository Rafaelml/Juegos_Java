package tp.pr5.control.jugadores;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.movimientos.MovimientoComplica;

public class JugadorHumanoComplica implements Jugador {
	private Scanner sc;
	private MovimientoComplica mov;

	public JugadorHumanoComplica(Scanner in) {
		this.sc = in;
	}

	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		System.out.print("Introduce la columna: ");
		int col = sc.nextInt();
		sc.nextLine();
		mov = new MovimientoComplica(col, color);
		return mov;
	}

}
