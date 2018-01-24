package tp.pr5.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import tp.pr5.control.Controlador;
import tp.pr5.gui.logica.Observer;
import tp.pr5.logica.Ficha;
import tp.pr5.logica.TableroInmutable;
import tp.pr5.logica.movimientos.MovimientoInvalido;

/**
 * Panel que contendra en la ventana el array de botones que representan las
 * fichas.
 */

@SuppressWarnings("serial")
public class PanelTablero extends JPanel implements Observer {

	Controlador control;
	private JButton[][] botones;
	private GridBagConstraints c;
	private boolean activo;

	//logos para las fichas.
	private ImageIcon blancas = new ImageIcon("src/imgs/blancas.png");
	private ImageIcon negras = new ImageIcon("src/imgs/negras.png");
	private ImageIcon vacia = new ImageIcon("src/imgs/vacia.png");
	
	/**
	 * Constructor que añade el controlador, hace visible el panel, y llama a
	 * init() para iniciar todo.
	 *  @param controlador
	 */
	public PanelTablero(Controlador ctrl) {
		this.control = ctrl;
		control.addObserver(this);
		iniciarGUI();
	}

	/**
	 * Metodo para iniciar las acciones de todos los componentes de la clase,
	 * añadirlos y determinar su visibilidad.
	 */
	private void iniciarGUI() {
		this.setLayout(new GridBagLayout());
		c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 1;
		c.weighty = 1;
		this.setPreferredSize(new Dimension(400, 400));
	}
    /**
     * Metodo que tiene la funci�n de pintar constantemente.
     * El tablero
     * @param tab- Interfaz que tiene al forma del tablero en 
     * ese instante
     */
	public void onReset(TableroInmutable tab) {
		int fils = tab.getAlto();
		int cols = tab.getAncho();
		botones = new JButton[cols][fils];
		if(fils<70||cols<70){this.removeAll();}

		for (int x = 0; x < cols; x++) {
			for (int y = 0; y < fils; y++) {
				botones[x][y] = crearBoton(tab.getCasilla(x + 1, y + 1), x, y);
				c.gridy = y;
				c.gridx = x;
				this.add(botones[x][y], c);
			}
		this.revalidate();
			activo = true;
		}
	}

	/**
	 * Metodo que crea un boton (casilla del tablero) en la posicion indicada y
	 * del color indicado.
	 */
	private JButton crearBoton(Ficha color, final int x, final int y) {
		JButton bot = new JButton();

		/**
		 * Accion del boton.
		 * 
		 * En caso de que sea pulsado, se realizara un movimiento en la posicion
		 * correspondiente.
		 */
		bot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (activo)
					try {
						control.movimiento(x + 1, y + 1, control.getTurno());
					} catch (MovimientoInvalido e1) {
						e1.printStackTrace();
					}
			}
		});

		/**
		 * Según el color que le corresponda a la ficha en el array, se le
		 * meterá un icono u otro.
		 */
		if (color.equals(Ficha.BLANCA))
			bot.setIcon(blancas);
		else if (color.equals(Ficha.NEGRA))
			bot.setIcon(negras);
		else
			bot.setIcon(vacia);
		if(control.getValida(x,y)) 	bot.setBackground(Color.lightGray);
		else bot.setBackground(Color.darkGray);
		return bot;
	}

	public void onMove(TableroInmutable tab) {
		onReset(tab);
	}

	public void onError(String msg) {
	}

	public void onGameOver(Ficha board) {
		activo = false;
	}

	public void onUndo(TableroInmutable tab) {
		onReset(tab);
	}

	public void onTurno(Ficha turno) {
	}

	public void onNotUndo(boolean vacia) {
	}
}
