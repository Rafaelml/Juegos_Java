package tp.pr5.control.comandos;

import tp.pr5.control.Comando;

public class ComandoAyuda implements Comando {
	private String ayuda;

	public ComandoAyuda(String ayuda) {
		this.ayuda = ayuda;
	}

	public boolean analiza(String cmd) {
		return cmd.equals("ayuda");
	}

	public void ejecuta() {
		System.out.println("Los comandos disponibles son: \n");
		System.out.println(ayuda);
	}

	public boolean terminar() {
		return false;
	}

	public String ayuda() {
		return "AYUDA: muestra esta ayuda.\n";
	}
}