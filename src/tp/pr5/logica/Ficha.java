package tp.pr5.logica;

/**
 * Clase de tipo enumerado Ficha con tres posibilidades, blanca, negra o vacia.
 */
public enum Ficha {
	VACIA, BLANCA, NEGRA;
	/**
	 * Metodo que transforma a cadena el tipo de ficha de la que se trata.
	 */
	public String toString() {
		String cad;
		if (this.equals(BLANCA))
			cad = "O";
		else if (this.equals(NEGRA))
			cad = "X";
		else
			cad = " ";
		return cad;
	}

	/**
	 * Cambia el color de la ficha
	 * 
	 * @return Ficha con color diferente
	 */
	public Ficha cambiar() {
		Ficha ficha = Ficha.BLANCA;

		if (this.equals(Ficha.BLANCA))
			ficha = Ficha.NEGRA;
		return ficha;
	}
}
