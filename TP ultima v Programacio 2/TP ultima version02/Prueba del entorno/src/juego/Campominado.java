package juego;

import entorno.Entorno;

public class Campominado {
	private Mine[] minas;
	
	public Campominado() {
		minas= new Mine[30];
		}
	
	public void agregarmina(int m, Exterminador e) {
		minas[m] = new Mine(e.get_x(), e.get_y());
	}
	
	public void dibujarminas(Entorno entorno) {
	if(this!=null) {
		for(int i =0; i<minas.length;i++) {
			if(minas[i]!=null) {
			minas[i].dibujar(entorno);
			}
		}
		}
	}
	public Mine[] arrayminas() {
		return minas;
	}
	public int minapisadaindice(Spider s) {
		for (int i = 0; i < minas.length ;i++) {
			if(s!=null && minas[i]!=null) 
			if(s.pisamina(minas[i])) {
				return i;
			}
		}
		return 10000;
	}
	public boolean exterminadorcercademinapisada(Exterminador e,Spider s) {
		if(minapisadaindice(s)<1000) {
			if(e.distancia(minas[minapisadaindice(s)])<minas[minapisadaindice(s)].get_radio()) {
				return true;
			}
			
		}
		return false;
	}
	public Punto[] minacercana(Mine m) {
		Punto[] minasexp = new Punto[30];
		for (int i = 0; i < minas.length ;i++) {
			if(minas[i]!=null && m!=null) {
			if(m.distancia(minas[i])<=1) {
				minasexp[i]= new Punto(minas[i].get_x(),minas[i].get_y());  
				minas[i]=null;
			}
			}
		}
		return minasexp;
	}
	public void minahacernull(int mina) {
		minas[mina]=null;
	}
	public boolean matarConMinasucesivas(Punto[] puntos, Spider s) {
		for(int i =0; i<puntos.length;i++) {
			if(puntos[i]!=null && s!=null) {
				Mine m = new Mine(puntos[i].x,puntos[i].y);
				if (s.distancia(m)<=1) {
					return true;
					}
				}
		}
		return false;
	}
	public int indiceminapisada(Spider s) {
		for(int i = 0; i<minas.length;i++) {
			if(s!=null && minas[i]!=null) {
				if(s.distancia(minas[i])<1) {
					minas[i]=null;
					return i;
		}
	}
	}
	return 100000;
	}
	public int indiceMinaRecibeDisparo(Disparar disparo) {
		for(int i = 0; i<minas.length;i++) {
			if(disparo!=null && minas[i]!=null) {
				if(minas[i].recibedisparo(disparo)) {
					return i;
				}
			}

		}
		return -1;
		}
	public int MinaCerca(Mine m) {
		for (int i = 0; i < minas.length ;i++) {
			if(minas[i]!=null && m!=null) {
				if(m.distancia(minas[i])<=1) {
					
					return i;
			}
			}
		}
		return -1;
	}
}
