package tp.pr5.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import tp.pr5.control.Controlador;
import tp.pr5.gui.logica.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;

/**
 * Panel que contendra el texto que indica a quien le toca poner ficha.
 */
@SuppressWarnings("serial")
public class PanelTurno extends JPanel implements Observer {
	private Controlador control;
	private JLabel txt = new JLabel("Juegan Blancas");
	private ImageIcon blancas = new ImageIcon("src/imgs/blancas.png");
	private ImageIcon negras = new ImageIcon("src/imgs/negras.png");
	private JLabel img = new JLabel();
	/**	
	 * Constructor que añade el controlador, hace visible el panel.
	 * 
	 * @param controlador
	 */
	public PanelTurno(Controlador ctrl) {
		control = ctrl;
		control.addObserver(this);
		init();
	}
	/**
	 * Metodo para iniciar las acciones de todos los componentes de la clase,
	 * añadirlos y determinar su visibilidad.
	 */
	private void init() {
		txt.setFont(new java.awt.Font("Impact", 0, 30));
		this.add(txt);
		this.add(img);
		img.setIcon(blancas);
	}

	public void onReset(TableroInmutable tab) {
	}

	public void onMove(TableroInmutable tab) {
	}

	public void onError(String msg) {
	}

	public void onGameOver(Ficha tab) {
        
		String ficha = "Blancas";
		img.setIcon(blancas);
		if (tab.equals(Ficha.NEGRA)){
			ficha = "Negras ";
			img.setIcon(negras);
		}
		if(!tab.equals(Ficha.VACIA)){
			txt.setText("Ganan " + ficha);
		}
		else txt.setText("Partida terminada en tablas ");
	}

	public void onUndo(TableroInmutable tab) {
	}

	public void onTurno(Ficha turno) {
		if (turno.equals(Ficha.BLANCA)) {
			txt.setText("Juegan Blancas");
			img.setIcon(blancas);
		} else if (turno.equals(Ficha.NEGRA)) {
			txt.setText("Juegan Negras ");
			img.setIcon(negras);
		} 
	}

	public void onNotUndo(boolean vacia) {
	}
}
