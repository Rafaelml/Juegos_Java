package tp.pr5.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import tp.pr5.control.Controlador;
import tp.pr5.gui.logica.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;

/**
 * Subpanel de la ventana que está compuesto por el boton de Reiniciar y el
 * boton de Salir.
 */

@SuppressWarnings("serial")
public class PanelBotonesInf extends JPanel implements Observer{

	private JButton reset = new JButton("Reiniciar");
	private JButton salir = new JButton("Salir");
	private Controlador control;
	private Ventana v;
	private ImageIcon sal = new ImageIcon("src/imgs/salir.png");
	private ImageIcon rei = new ImageIcon("src/imgs/reiniciar.png");
	/**
	 * Constructor que añade el controlador, hace visible el panel, y llama a
	 * init() para iniciar todo.
	 * @param ventana 
	 *  @param controlador
	 */
	public PanelBotonesInf(Controlador control, Ventana ventana) {
		this.control = control;
		this.setVisible(true);
		v=ventana;
		init();
	}

	/**
	 * Metodo para iniciar las acciones de todos los componentes de la clase,
	 * añadirlos y determinar su visibilidad.
	 */
	private void init() {
		/**
		 * Accion del boton reset 
		 * 
		 * En caso de ser pulsado, se llamará al metodo
		 * de reset para que se resetee la partida.
		 */
		reset.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				v.cederTurno();
				control.reset();
			}
		});
		
		/**
		 * Accion del boton salir 
		 * 
		 * En caso de ser pulsado, se abriría una
		 * nueva ventana de dialogo confirmando que se desea salir.
		 */
		
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int n = JOptionPane.showOptionDialog(salir,
						"¿Estás seguro de que queires salir??", "Cerrar",
						JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE, null, null, null);

				if (n == 0) {
					System.exit(0);
				}
			}
		});
		
		reset.setIcon(rei);
		salir.setIcon(sal);
		this.add(reset);
		this.add(salir);
	
	}

	
	public void onReset(TableroInmutable tab) {
		reset.setEnabled(false);
	}

	public void onMove(TableroInmutable tab) {
	}


	public void onError(String msg) {
	}


	public void onGameOver(Ficha fich) {
	}


	public void onUndo(TableroInmutable tab) {

	}


	public void onTurno(Ficha turno) {
	}


	public void onNotUndo(boolean vacia) {
	}
}
