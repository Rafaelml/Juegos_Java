package tp.pr5.logica;

/**
 * Esta clase se encarga de almacenar la lista de movimientos que se han ido
 * realizando para poder deshacerlos.
 */

public class Stack {

	private Movimiento[] undoStack;
	private int numUndo;

	
	/**
	 * Constructor para la pila (array unidimensional).
	 * 
	 * @param tamanio
	 *            tamanio define el número de elementos que va a tener la pila
	 */
	public Stack(int tamanio) {
		undoStack = new Movimiento[tamanio];
		numUndo = -1;
	}

	/**
	 * Método que añade un movimiento a la pila Si el numero de elementos llega
	 * al maximo de la pila, se descarta el movimiento más viejo y se añade el
	 * nuevo.
	 */
	public void aniadirUndo(Movimiento mov) {
		if (numUndo == undoStack.length - 1)
			desplazarUndo();
		else
			numUndo++;

		undoStack[numUndo] = mov;
	}

	/**
	 * Método que devuelve el ultimo movimiento almacenado.
	 * 
	 * @return la columna donde se hizo el ultimo movimiento
	 */
	public Movimiento deshacer() {
		Movimiento mov = null;
		
		if (numUndo >= 0) {
			mov = undoStack[numUndo];
			undoStack[numUndo] = null;
			numUndo--;
		}
		return mov;
	}

	/**
	 * Método que desplaza los elementos de la pila descartando el primero.
	 */
	private void desplazarUndo() {
		for (int i = 0; i < undoStack.length - 1; i++) {
			undoStack[i] = undoStack[i + 1];
		}
	}

	/**
	 * Método que reinicia la pila. Pone a -1 el contador de movimientos
	 * almacenados, lo que indica que está vacia.
	 */
	public void reset() {

		while (numUndo > -1) {
			undoStack[numUndo] = null;
			numUndo--;
		}
	}
	
	/**
	 * Método que devuelve true si la pila está vacia.
	 */
	public boolean vacia(){
	return numUndo<0;
	}
}
