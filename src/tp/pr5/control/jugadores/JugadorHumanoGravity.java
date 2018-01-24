package tp.pr5.control.jugadores;

import java.util.Scanner;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.movimientos.MovimientoGravity;

public class JugadorHumanoGravity implements Jugador {
	private Scanner sc;
	private MovimientoGravity mov;

	public JugadorHumanoGravity(Scanner in) {
		this.sc = in;
	}

	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		System.out.print("Introduce la columna: ");
		int col = sc.nextInt();
		sc.nextLine();
		System.out.print("Introduce la fila: ");
		int fil = sc.nextInt();
		sc.nextLine();
		mov = new MovimientoGravity(col, fil, color);
		return mov;
	}

}
