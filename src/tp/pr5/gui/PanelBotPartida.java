package tp.pr5.gui;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import tp.pr5.control.Controlador;
import tp.pr5.gui.logica.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;

/**
 * Subpanel de la ventana que está compuesto por el boton de Deshacer y el boton
 * de Poner Aleatorio.
 */
@SuppressWarnings("serial")
public class PanelBotPartida extends JPanel implements Observer {

	private Controlador control;
	private JButton deshacer = new JButton("Deshacer");
	private JButton aleatorio = new JButton("Poner Aleatorio");

	private ImageIcon des = new ImageIcon("src/imgs/deshacer.png");
	private ImageIcon rand = new ImageIcon("src/imgs/random.png");
	/**
	 * Constructor que añade el controlador, hace visible el panel, y llama a
	 * init() para iniciar todo.
	 *  @param controlador
	 */
	public PanelBotPartida(Controlador cntrl) {
		control = cntrl;
		control.addObserver(this);
		this.setBorder(new TitledBorder("Opciones de Juego"));
		//this.setLayout(new GridLayout(2,1));
		initPanel();
	}

	/**
	 * Metodo para iniciar las acciones de todos los componentes de la clase,
	 * añadirlos y determinar su visibilidad.
	 */
	private void initPanel() {
		deshacer.setEnabled(false);

		/**
		 * Accion del boton deshacer 
		 * 
		 * En caso de ser pulsado, se abriría una
		 * nueva ventana de dialogo confirmando que se desea salir.
		 */
		deshacer.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				if (!control.deshacer()) {
					JOptionPane.showMessageDialog(deshacer,
							"¡Imposible Deshacer!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		/**
		 * Accion del boton aleatorio 
		 * 
		 * En caso de ser pulsado, se llamara al
		 * metodo de control para que se genere un movimiento aleatorio sobre el
		 * tablero
		 */
		aleatorio.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				control.movAleatorio();
			}
		});

		deshacer.setIcon(des);
		aleatorio.setIcon(rand);
		this.add(deshacer);
		this.add(aleatorio);
		this.setVisible(true);
	}

	public void onReset(TableroInmutable tab) {
		deshacer.setEnabled(false);
		aleatorio.setEnabled(true);
	}

	public void onMove(TableroInmutable tab) {
		deshacer.setEnabled(true);
	}

	public void onError(String msg) {
	}

	public void onGameOver(Ficha tab) {
		deshacer.setEnabled(false);
		aleatorio.setEnabled(false);
	}

	public void onUndo(TableroInmutable tab) {
	}

	public void onTurno(Ficha turno) {
	}

	public void onNotUndo(boolean vacia) {
		deshacer.setEnabled(!vacia);
	}
	
	public void setEnabledAleatorio(boolean on) {
		aleatorio.setEnabled(on);
	}
	
}
