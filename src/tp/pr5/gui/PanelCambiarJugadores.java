package tp.pr5.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tp.pr5.control.Controlador;
import tp.pr5.control.jugadores.JugadorSwing;
import tp.pr5.control.jugadores.JugadorSwingAleatorio;
import tp.pr5.control.jugadores.JugadorSwingHumano;
import tp.pr5.gui.logica.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;
/**
 * Panel que contiene a los Jugadores y permite cambiar entre 
 * automáticos o humanos
 */
@SuppressWarnings("serial")
public class PanelCambiarJugadores extends JPanel implements Observer{
	private Controlador control;
	private JComboBox<String> negras = new JComboBox<String>();
	private JComboBox<String> blancas = new JComboBox<String>();
	private JPanel neg = new JPanel();;
	private JPanel blan = new JPanel();
	private Ventana ventana;
	
    /**
     * Constructro de la clase
     * @param ctrl control del juego 
     * @param v ventana principal
     */
	public PanelCambiarJugadores(Controlador ctrl,Ventana v) {
		this.setLayout(new GridLayout(2, 1));
		this.control = ctrl;
		this.setBorder(new TitledBorder("Cambiar Jugadores"));
		ventana=v;
		this.setVisible(true);
		control.addObserver(this);
		init();
	}
	
		
	private void init() {

		negras.addItem("Humano");
		negras.addItem("Automatico");

		blancas.addItem("Humano");
		blancas.addItem("Automatico");
	
		/**
		 * Accion de la lista de juegos.
		 * 
		 * En caso de que la seleccion sea gravity, hará visible el panel
		 * tamGravity para que el usuario pueda introducir el tamaño del tablero
		 * que quiere.
		 */
		
		 
		negras.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JugadorSwing jug=new JugadorSwingHumano(control, ventana);
				
				if(negras.getSelectedItem().equals("Automatico")) jug=new JugadorSwingAleatorio(control,ventana);
				//else if(negras.getSelectedItem().equals("Humano")) jug=new JugadorSwingHumano(control, ventana);
				ventana.setNegras(jug);
			}
		});

		blancas.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				JugadorSwing jug=new JugadorSwingHumano(control, ventana);
				
			if(blancas.getSelectedItem().equals("Automatico")) jug=new JugadorSwingAleatorio(control,ventana);
			//else if(blancas.getSelectedItem().equals("Humano")) jug=new JugadorSwingHumano(control, ventana);
			ventana.setBlancas(jug);
			}
			
		});
		
		blan.add(new JLabel("Jugador Blancas:"));
		blan.add(blancas);
		neg.add(new JLabel("Jugador Negras:"));
		neg.add(negras);
		
		this.add(blan);
		this.add(neg);
	}

	public void onReset(TableroInmutable tab) {
		blancas.setEnabled(true);
		negras.setEnabled(true);
		blancas.setSelectedItem("Humano");
		negras.setSelectedItem("Humano");
	}

	public void onMove(TableroInmutable tab) {
	}

	public void onError(String msg) {
	}

	public void onGameOver(Ficha fich) {
		blancas.setEnabled(false);
		negras.setEnabled(false);
	}

	public void onUndo(TableroInmutable tab) {
	}

	public void onTurno(Ficha turno) {
	}

	public void onNotUndo(boolean vacia) {
	}
	
}
