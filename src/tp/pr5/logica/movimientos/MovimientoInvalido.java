package tp.pr5.logica.movimientos;

@SuppressWarnings("serial")
public class MovimientoInvalido extends Exception {

	// private String mensaje;
	public MovimientoInvalido() {

	}

	// Constructor sin par�metros.

	public MovimientoInvalido(String msg) {
		super(msg);
		// mensaje=msg;

	}

	// Constructor con un par�metro para el mensaje.

	public MovimientoInvalido(String msg, Throwable arg) {
		super(msg);
		arg.printStackTrace();
	}

	// Constructor con un par�metro para el mensaje y otro para la causa.

	public MovimientoInvalido(Throwable arg) {
		arg.printStackTrace();
	}
	// Constructor con un par�metro para la causa inicial que provoc� la
	// excepci�n.

}
