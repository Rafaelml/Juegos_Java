package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.control.Controlador;
import tp.pr5.control.jugadores.JugadorSwing;
import tp.pr5.control.jugadores.JugadorSwingHumano;
import tp.pr5.gui.logica.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;

/**
 * Panel que contendra todos los demás subpaneles.
 */
@SuppressWarnings("serial")
public class Ventana extends JFrame implements Observer {

	private Controlador ctrl;
	private JugadorSwing blancas;
	private JugadorSwing negras;
	private JugadorSwing actual;
	PanelBotPartida panelPartida;
	
	
	/**
	 * Constructor que añade el controlador, hace visible el panel, y llama a
	 * init() para iniciar todo.
	 *  @param controlador
	 */
	public Ventana(Controlador control) throws HeadlessException {
		super("Práctica 5 - TP");
		this.ctrl = control;
		iniciarGUI();
		ctrl.addObserver(this);
		ctrl.reset();
	}

	/**
	 * Metodo para iniciar las acciones de todos los componentes de la clase,
	 * añadirlos y determinar su visibilidad.
	 */
	private void iniciarGUI() {

		JPanel panelPrincipal = new JPanel(new BorderLayout());
		this.setContentPane(panelPrincipal);
		
			
		// Subpaneles que componen la ventana
		JPanel panelTablero = new PanelTablero(ctrl);
		panelPartida = new PanelBotPartida(ctrl);
		JPanel panelBotInf = new PanelBotonesInf(ctrl,this);
		JPanel panelJuegos = new PanelCambiarJuego(ctrl,this);
		JPanel panelTurno = new PanelTurno(ctrl);
		JPanel derecha = new JPanel(new GridLayout(5, 1));
		JPanel panelJugadores = new PanelCambiarJugadores(ctrl, this);
	
		/*
		 * dividimos la ventana principal en dos grandes partes, el tablero, y
		 * todos los demas componentes a la derecha.
		 */
		panelPrincipal.add(panelTablero, BorderLayout.CENTER);
		panelPrincipal.add(derecha, BorderLayout.EAST);

		
		derecha.add(panelJuegos);
		derecha.add(panelPartida);
		derecha.add(panelJugadores);
		derecha.add(panelBotInf);
		derecha.add(panelTurno);
		
		/*
		 * se añade como accion al click en el boton de cerrar [X] que se llame
		 * a cerrar().
		 */
		this.addWindowListener(new WindowListener() {

			public void windowClosing(WindowEvent e) {
				cerrar();
			}

			public void windowOpened(WindowEvent e) {
			}

			public void windowIconified(WindowEvent e) {
			}

			public void windowDeiconified(WindowEvent e) {
			}

			public void windowDeactivated(WindowEvent e) {
			}

			public void windowClosed(WindowEvent e) {
			}

			public void windowActivated(WindowEvent e) {
			}
		});

		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setSize(970, 650);
		
		//this.setResizable(false);
		this.setVisible(true);
	
		blancas = new JugadorSwingHumano(ctrl, this);
		negras = new JugadorSwingHumano(ctrl, this);
		actual=negras;
	}

	/**
	 * Abrirá una nueva ventana de dialogo confirmando que se desea salir.
	 */
	private void cerrar() {
		int n = JOptionPane.showOptionDialog(this,
				"¿Estás seguro de que queires salir?", "Cerrar",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null,
				null, null);

		if (n == 0) {
			ctrl.salir();
			System.exit(0);
		}
	}
	/**
	 * Abrirá una nueva ventana de dialogo informando de un error
	 * @param title T�tulo del error
	 * @param msg mensaje del error
	 */
	private void reportError(String title, String msg) {
		JOptionPane.showMessageDialog(this, msg, title,
				JOptionPane.ERROR_MESSAGE);
	}
	/**
	 * Abrirá una nueva ventana de dialogo informando 
	 * que ha finalizado la partida
	 * @param ganador Tiene la forma en la que ha terminado 
	 * la partida; as� sea por victoria de blancas, negras o
	 *  tablas
	 */
	private void reportfinal(String ganador) {
		JOptionPane.showMessageDialog(this, ganador, "¡Partida Terminada!",
				JOptionPane.DEFAULT_OPTION);
	}

	public void onReset(TableroInmutable board) {
		actual.cederTurno();
	}

	public void onMove(TableroInmutable board) {
	}

	public void onError(String msg) {
		reportError("Error", msg);
	}

	public void onGameOver(Ficha turno) {
		actual.cederTurno();		
		String ganador="";
		if (turno.equals(Ficha.BLANCA))
			ganador = "Ganan las blancas";
		else if (turno.equals(Ficha.NEGRA))
			ganador = "Ganan las negras";
		else if(turno.equals(Ficha.VACIA))
			ganador = "Partida terminada en tablas";
		reportfinal(ganador);
	}

	public void onUndo(TableroInmutable tab) {
	}

	public void onTurno(Ficha turno) {
		if(turno.equals(Ficha.BLANCA)){
			actual=blancas;
		}
		else if (turno.equals(Ficha.NEGRA)){
			actual=negras;
		}
		if(!turno.equals(Ficha.VACIA)){
		 ejecutarHebra();
		 }
			
	}

	public void onNotUndo(boolean vacia) {
	}
	/**
	 * 	Método para cambiar el jugador actual a blancas
	 * @param jugador
	 */
	public void setBlancas(JugadorSwing jugador){
		blancas=jugador;
		if(!actual.equals(negras)){ actual=jugador;
		ejecutarHebra();}
		
		
	}
	/**
	 * 	Método para cambiar el jugador actual a negras
	 * @param jugador 
	 */
	public void setNegras(JugadorSwing jugador){
		negras=jugador;
		if(!actual.equals(blancas)){ actual=jugador;
		ejecutarHebra();}
	
	}
	/**
	 * Método para inciar la hebra tomando
	 * turno y movimiento del jugador actual
	 */
	private void ejecutarHebra(){
		actual.tomarTurno();
		actual.getMovimiento();
	}
	/**
	 * Método para desabilitar el boton 
	 * de poner aleatorio mietras juega 
	 * un jugador tipo máquina
	 * @param on
	 */
	public void setAleatorio(boolean on) {
		panelPartida.setEnabledAleatorio(on);
	}
	/**
	 * Método para ceder el turno 
	 * de todos los jugadores
	 */
	public void cederTurno(){
		blancas.cederTurno();
		negras.cederTurno();
		actual.cederTurno();
		actual=new JugadorSwingHumano(ctrl, this);
	}
}
