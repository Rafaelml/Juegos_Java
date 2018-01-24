package tp.pr5.logica;

import javax.swing.SwingUtilities;

import tp.pr5.control.Controlador;
/**
 * Clase que permite crear una hebra en para poder
 * realizar varias operaciones en paralelo ; permite
 * que los jugadores realizar movimientos sin detener 
 * la aplicaci�n.
 */
public class HebraJugador extends Thread{
	private Controlador control;
	/**
	 * Método Constructor de la clase HebraJugador.
	 */
	public HebraJugador(Controlador control){
		this.control=control;
	}
	/**
	 * M�todo que permite que comienze la ejecuci�n 
	 * de la hebra
	 */
	public void run(){
	
		try {
		
			Thread.sleep(1000);	// pone a dormir
			SwingUtilities.invokeLater (new Runnable(){ /*Clase interna para realizar
                                            		el movimiento*/
	    
			public void run() {
				control.movAleatorio();
				}
			
			});
			
		} catch ( InterruptedException ex) {
		}   
		
		
	}


}
