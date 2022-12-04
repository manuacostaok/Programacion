package Grafos;


public class Arista {
	
	 private int peso,origen,destino;
	


	public Arista(int peso,int origen, int destino) {
		this.peso=peso;
		this.origen=origen;
		this.destino=destino;
	}

	public int getOrigen() {
		return origen;
	}

	public void setOrigen(int origen) {
		this.origen = origen;
	}

	public int getDestino() {
		return destino;
	}

	public void setDestino(int destino) {
		this.destino = destino;
	}
	public int getPeso() {
		
		return this.peso;
	}

}
