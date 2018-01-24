package tp.pr5.control.jugadores;

import tp.pr5.control.Controlador;
import tp.pr5.gui.Ventana;

public class JugadorSwingHumano implements JugadorSwing {
	//private Controlador control;
	private Ventana ventana;
	
	public JugadorSwingHumano(Controlador c, Ventana v){
		//control=c;
		ventana=v;
	}

	public void tomarTurno() {
		ventana.setAleatorio(true);
	}

	public void cederTurno() {
	}

	
	public void getMovimiento() {
	}


}
