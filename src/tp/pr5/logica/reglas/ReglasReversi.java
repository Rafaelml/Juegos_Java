package tp.pr5.logica.reglas;

import tp.pr5.logica.Ficha;
import tp.pr5.logica.Movimiento;
import tp.pr5.logica.Tablero;
import tp.pr5.logica.validasReversi;



public class ReglasReversi extends ReglasComunes implements ReglasJuego {

	private final int ancho = 8;
	private final int alto = 8;
	private boolean tablas = false;
		
	public ReglasReversi() {
	}

	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {
		Ficha temp=Ficha.VACIA;
		boolean vacia=false;
		int i=1, j=1;
		int contB=0, contN=0;
		boolean terminada=false;
		
		do{
			do{
				temp=t.getCasilla(i, j);
				if(temp.equals(Ficha.BLANCA)) contB++;
				else if(temp.equals(Ficha.NEGRA)) contN++;
				else{
					
					if(!validasReversi.comprobarMov(t, Ficha.NEGRA, i, j) && !validasReversi.comprobarMov(t, Ficha.BLANCA, i, j))
						terminada=true;
					else{
						vacia=true;
						terminada=false;
					}
				}
				i++;
				
			}while(i<=ancho && !vacia );
			i=1;
			j++;
		}while(j<=alto && !vacia );
			
		if(terminada){
			if(vacia) temp=Ficha.VACIA;
			else if(contB>contN) temp=Ficha.BLANCA;
			else if(contB<contN) temp=Ficha.NEGRA;
			else tablas=true;
		}
		return temp;
		
	}

	public Tablero iniciaTablero() {
	   Tablero tab= new Tablero(ancho, alto);
	   tab.setCasilla(4, 4, Ficha.BLANCA);
	   tab.setCasilla(5, 4, Ficha.NEGRA);
	   tab.setCasilla(4, 5, Ficha.NEGRA);
	   tab.setCasilla(5, 5, Ficha.BLANCA);
	   validasReversi.init();
	  return tab;
	}

	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {
		return tablas;
	}
	public Ficha jugadorInicial() {
		return Ficha.NEGRA;
	}
	
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
			return ultimoEnPoner.cambiar();
	}

}
	