package tp.pr5.logica.reglas;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Tablero;

public abstract class ReglasComunes implements ReglasJuego {

	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		return ultimoEnPoner.cambiar();
	}

	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}
}
