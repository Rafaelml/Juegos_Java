package tp.pr5;

import java.text.ParseException;
import java.util.Scanner;

import tp.pr5.control.Controlador;
import tp.pr5.control.ControladorConsola;
import tp.pr5.control.ControladorVista;
import tp.pr5.control.factorias.FactoriaComplica;
import tp.pr5.control.factorias.FactoriaConecta4;
import tp.pr5.control.factorias.FactoriaGravity;
import tp.pr5.control.factorias.FactoriaReversi;
import tp.pr5.control.factorias.FactoriaTipoJuego;
import tp.pr5.gui.Consola;
import tp.pr5.gui.Ventana;
import tp.pr5.logica.*;
import tp.pr5.logica.reglas.ReglasComplica;
import tp.pr5.logica.reglas.ReglasConecta4;
import tp.pr5.logica.reglas.ReglasGravity;
import tp.pr5.logica.reglas.ReglasReversi;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

/**
 * Clase Main que inicia la ejecucion de la partida.
 * 
 * Crea una nueva partida, por defecto de Conecta4, y un controlador que la
 * ejecutará.
 */
public class Main {

	public static void main(String[] args) throws ParseException, Exception {
		Partida partida = new Partida(new ReglasConecta4());
		int x = 10;
		int y = 10;
		FactoriaTipoJuego f = new FactoriaConecta4();
		String mensaje = "";
		String game = "c4";
		boolean hecho = true;
		Scanner in = new Scanner(System.in);
		Controlador control = new ControladorConsola(f, partida, in);

		// Definimos las opciones de entradas por argumentos.

		try {

			Options options = new Options();
			options.addOption("g", "game", true,
					"Tipo de juego (c4, co, gr, rv). Por defecto, c4.");

			options.addOption("u", "ui", true,
					"Tipo de interfaz (console, window). Por defecto, console.");
			options.addOption("x", "tamX", true,
					"Número de columnas del tablero (sólo para Gravity). Por defecto, 10.");
			options.addOption("y", "tamY", true,
					"Número de filas del tablero (sólo para Gravity). Por defecto, 10.");
			options.addOption("h", "help", false, "Muestra esta ayuda.");

			options.getOption("g").setArgName("game");
			options.getOption("u").setArgName("tipo");
			options.getOption("x").setArgName("columnNumber");
			options.getOption("y").setArgName("rowNumber");

			// Analizar los argumentos

			CommandLineParser parser = new PosixParser();
			CommandLine cmd = parser.parse(options, args);

			// Mostrar ayuda

			if (cmd.hasOption("help")) {

				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp(Main.class.getName(), options, true);
				hecho = false;
			} else if (cmd.hasOption("g")) {

				game = cmd.getOptionValue("g");
				if (game.equals("c4")) {
					String[] a = cmd.getArgs();
					if (a.length != 0) {
						mensaje = "Uso incorrecto: Argumentos no entendidos: y otras cosas\n"
								+ "Use -h|--help para más detalles.";
						throw new ParseException(mensaje, 1);
					}
					// no se modifica nada porque c4 es el juego predefinido
				} else if (game.equals("co")) {

					String[] a = cmd.getArgs();
					if (a.length != 0) {
						mensaje = "Uso incorrecto: Argumentos no entendidos: y otras cosas\n"
								+ "Use -h|--help para más detalles.";
						throw new ParseException(mensaje, 1);
					} else
						partida = new Partida(new ReglasComplica());
					f = new FactoriaComplica();
				}

				else if (game.equals("gr")) {

					// Leer tamX y tamY

					x = Integer.parseInt(cmd.getOptionValue("tamX", "10"));
					y = Integer.parseInt(cmd.getOptionValue("tamY", "10"));

					partida = new Partida(new ReglasGravity(x, y));
					f = new FactoriaGravity(x, y);

				} 
				else if (game.equals("rv")) {
					partida = new Partida(new ReglasReversi());
					f = new FactoriaReversi();
				}
				
				else {
					mensaje = "Uso incorrecto: Juego 'noExiste' incorrecto.\n"
							+ "Use -h|--help para más detalles.";
					throw new ParseException(mensaje, 1);
				}
				control = new ControladorConsola(f, partida, in);
				
			}

			if (cmd.hasOption("ui")) {
				String consola = cmd.getOptionValue("ui");
				
				if (consola.equals("console")) {
					new Consola(control, partida);
					
				} else if (consola.equals("window")) {
					control = new ControladorVista(f, partida);
				
					new Ventana(control);
				}

			}

			// se crea controlador y se ejecuta.
			if (hecho) {

				control.run();
			}

		} catch (NumberFormatException e) {
			System.err.println("Error al leer un número");
			System.exit(1);
		} catch (ParseException e) {
			System.err.println(e.getMessage());
			System.exit(1);

		} catch (Exception e) {
			mensaje = "Uso incorrecto: Unrecognized option: -z\n"
					+ "Use -h|--help para más detalles.";
			System.err.println(mensaje);
			System.exit(1);
		} finally {
			in.close();
		}

	}

}
