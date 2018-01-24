package tp.pr5.control.jugadores;

import tp.pr5.control.Controlador;
import tp.pr5.gui.Ventana;
import tp.pr5.logica.HebraJugador;

public class JugadorSwingAleatorio implements JugadorSwing {
private Controlador control;
private HebraJugador h;
private Ventana ventana;

	public JugadorSwingAleatorio(Controlador c, Ventana v){
		control=c;
		ventana=v;
	}
	
	
	public void tomarTurno() {
		h= new HebraJugador(control);
		ventana.setAleatorio(false);
	}

	
	public void cederTurno() {
		if(h!=null){
			h.interrupt();
			h=null;
		}
	}

	
	public void getMovimiento() {
		h.start();
	}
}
