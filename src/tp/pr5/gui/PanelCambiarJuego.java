package tp.pr5.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr5.control.Controlador;
import tp.pr5.logica.validasReversi;

@SuppressWarnings("serial")
public class PanelCambiarJuego extends JPanel {
	private Controlador control;
	private Ventana v;
	// subpaneles
	private JPanel seleccion = new JPanel();;
	private JPanel tamGravity = new JPanel();

	// componentes del subpanel seleccion
	private JButton cambiar = new JButton("Cambiar");
	private JComboBox<String> list = new JComboBox<String>();
	
	

	// componentes del subpanel tamGravity
	private JLabel colum = new JLabel("Columnas:");
	private JLabel filas = new JLabel("Filas:");
	private JTextField cols = new JTextField("10");
	private JTextField fils = new JTextField("10");
	
	private ImageIcon cam = new ImageIcon("src/imgs/jugar.png");

	/**
	 * Constructor que añade el controlador, hace visible el panel, y llama a
	 * init() para iniciar todo.
	 *  @param controlador
	 */
	public PanelCambiarJuego(Controlador ctrl, Ventana ventana) {
		this.setLayout(new GridLayout(3, 1));
		this.control = ctrl;
		v=ventana;
		this.setBorder(new TitledBorder("Cambiar de Juego"));
		this.setVisible(true);
		init();
	}
	
	/**
	 * Metodo para iniciar las acciones de todos los componentes de la clase,
	 * añadirlos y determinar su visibilidad.
	 */
	private void init() {

		list.addItem("Conecta Cuatro");
		list.addItem("Complica");
		list.addItem("Gravity");
		list.addItem("Reversi");

		colum.setForeground(Color.BLUE);
		filas.setForeground(Color.BLUE);
		
		colum.setFont(new java.awt.Font("Impact", 0, 15));
		cols.setFont(new java.awt.Font("Impact", 0, 25));
		filas.setFont(new java.awt.Font("Impact", 0, 15));
		fils.setFont(new java.awt.Font("Impact", 0, 25));
		
		
		tamGravity.add(colum);
		tamGravity.add(cols);
		tamGravity.add(filas);
		tamGravity.add(fils);
		tamGravity.setVisible(false);

		seleccion.add(list);
		seleccion.add(tamGravity);

		/**
		 * Accion de la lista de juegos.
		 * 
		 * En caso de que la seleccion sea gravity, hará visible el panel
		 * tamGravity para que el usuario pueda introducir el tamaño del tablero
		 * que quiere.
		 */
		list.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (list.getSelectedItem().equals("Gravity")) {
					tamGravity.setVisible(true);
				} else
					tamGravity.setVisible(false);
			}
		});

		/**
		 * Accion del boton cambiar
		 * 
		 * En caso de ser pulsado, se pasa el string del juego seleccionado al
		 * metodo de control que cambia de juego, junto con el alto y el ancho
		 * de los textAreas. El alto y el ancho solo serán usados en Gravity.
		 */
		cambiar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String opc = (String) list.getSelectedItem();
				int x = Integer.parseInt(cols.getText());
				int y = Integer.parseInt(fils.getText());
				validasReversi.clear();
				v.cederTurno();
				control.cambiarJuego(opc, x, y);
				
			//	list.setSelectedItem("Conecta Cuatro");
			}
		});

		cambiar.setIcon(cam);
		
		this.add(list);
		this.add(tamGravity);
		this.add(cambiar);
	}
}