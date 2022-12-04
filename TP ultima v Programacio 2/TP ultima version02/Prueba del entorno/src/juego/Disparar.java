package juego;

import java.awt.Image;

import entorno.Entorno;

public class Disparar {
	private Disparo bala;
	
	Disparar(Exterminador e){
		if(bala==null && e!=null){
		bala = new Disparo(e.get_x(), e.get_y(), 8, e.get_angulo());
		}
		else
			return;
	}
	public void moverdisparo(Entorno entorno, Image disparo) {
		if (bala != null) {
			bala.dibujardisparo(entorno, disparo);
			bala.moverdisparo();
		}
	}
	public void hacernull() {
		bala=null;
	}
	
	
	public void choquedisparo( Entorno entorno, Ciudad ciudad) {
		
		if (bala != null) {
			if (bala.get_y() < entorno.alto() || bala.get_y() > entorno.alto()) {
				if (bala.get_y() < 0 || bala.get_y() > entorno.alto()) {
					bala=null;
				}
			}
		if(bala!=null){
			if( (bala.get_x() < entorno.ancho() || bala.get_x() > entorno
							.ancho())) {
				if (bala.get_x() < 0 || bala.get_x() > entorno.ancho()) {
					
					bala=null;
					}
				}
			}
		if (bala != null){
			if ( ciudad.choqueedificios(bala)!=false) {
					bala=null;
				}
			}
			
			}
		
		}
	public Disparo bala() {
		return this.bala;
	}
}
