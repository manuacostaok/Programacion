package juego;

public class Punto {
		
		double x;
		double y;
		
  Punto(){
	  this.x=0;
	  this.y=0;
	  
  }
  Punto(double x, double y){
	  this.x=x;
	  this.y=y;
  }
  public void imprimir() {
	  System.out.println("x:"+this.x+" "+"y:"+this.y);
  }
  void desplazar(double desp_x, double desp_y) {
	  this.x=desp_x;
	  this.y=desp_y;
  }
 public static double distancia(Punto p1, Punto p2) {
	  double distancia=0;
		return  distancia= Math.sqrt(Math.pow(p1.x-p2.x, 2)+Math.pow(p1.y-p2.y, 2));
  }
}

