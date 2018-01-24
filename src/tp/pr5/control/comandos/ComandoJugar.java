package tp.pr5.control.comandos;

import java.util.Scanner;

import tp.pr5.control.Comando;
import tp.pr5.control.ControladorConsola;
import tp.pr5.control.factorias.FactoriaComplica;
import tp.pr5.control.factorias.FactoriaConecta4;
import tp.pr5.control.factorias.FactoriaGravity;
import tp.pr5.control.factorias.FactoriaReversi;
import tp.pr5.control.factorias.FactoriaTipoJuego;
import tp.pr5.logica.Partida;

public class ComandoJugar implements Comando {
	private String coman[];
	private FactoriaTipoJuego factoria;
	private Partida partida;
	private ControladorConsola control;
	private Scanner in;

	public ComandoJugar(ControladorConsola control) {
		factoria = control.getFactoria();
		partida = control.getPartida();
		this.control = control;
		in = control.getIn();
	}

	public boolean analiza(String cmd) {
		coman = cmd.split(" ");
		return (coman[0].equals("jugar") || coman[0].equals("Jugar"));
	}

	/**
	 * Metodo para cambiar de juego.
	 * 
	 * Se comparara el string para saber a que juego se quiere jugar Y se cambia
	 * a la factoria. En el caso de gravity tambien se solicita el alto y el
	 * ancho. Para eso se pasan a int los strings.
	 * 
	 */
	public void ejecuta() {
		boolean hecho = true;
		int ancho = 10;
		int alto = 10;
		if (coman.length > 1) {
			if (coman[1].equals("c4")) {
				factoria = new FactoriaConecta4();
			} else if (coman[1].equals("co")) {
				factoria = new FactoriaComplica();
			} else if (coman[1].equals("rv")) {
				factoria = new FactoriaReversi();
			} else if (coman[1].equals("gr")) {

				try {
					ancho = Integer.parseInt(coman[2]);
					alto = Integer.parseInt(coman[3]);
				} catch (ArrayIndexOutOfBoundsException e) {
					/*
					 * Si ancho y alto no son enteros, tira excepcion
					 */
				}

				if (ancho > 1 && alto > 1) {
					factoria = new FactoriaGravity(ancho, alto);
				}
			}
		} else
			hecho = false;
		/*
		 * Por defecto crea jugadores humanos en ambos casos
		 */
		control.setJugadorBlanca(factoria.creaJugadorHumanoConsola(in));
		control.setJugadorNegra(factoria.creaJugadorHumanoConsola(in));
		control.setFactoria(factoria);
		partida.reset(factoria.creaReglas());

		if (hecho)
			System.out.println("Partida reiniciada.");
	}

	public boolean terminar() {
		return false;
	}

	public String ayuda() {
		return "JUGAR [c4|co|gr|rv] [tamX tamY]: cambia el tipo de juego.\n";
	}

}
