package juego;

import java.awt.Color;
import java.awt.Image;
import entorno.Entorno;


public class Control {

	private Exterminador Jugador1;
	private int puntaje;
	private int minasjug1;
	private int minasjug2;
	private Potenciador potenciador;
	private Ciudad ciudad;
	private Enjambre enjambreJugador_1;
	private Campominado minasJugador_1;
	private Enjambre matanboss;
	private Redtelas telaranias;
	private ArasGigantes bosses;
	private ArasGigantes bossesjug2;
	private Disparar disparojug1;
	private Disparar disparojug2;
	private Exterminador Jugador2;
	private Enjambre enjambreJugador_2;
	private Campominado minas2;
	private boolean menu;
	private boolean menuniveles;
	private Startmenu players;
	private Startmenu niveles;

	private int bossmuertos;



	public Control(){

		this.menu = true;
		this.menuniveles=false;
		this.bossmuertos=0;
		this.potenciador=new Potenciador(6,7,0,false);
		this.puntaje = 0;
		this.minasjug1 = 0;
		this.ciudad = new Ciudad();		
		this.telaranias = new Redtelas(10);
		this.players= new Startmenu(3);
		this.niveles= new Startmenu(5);

	}

	//esta funcion detecta las colisiones entre los objetos entre si, recorriendo el arreglo "arrayarañas" del objeto "Spider"
	private void interaccionMinasAranasyJugador(Exterminador one, Enjambre en, int tiempo, Ciudad ciudad, Campominado campo){

		if(one!=null&& en!=null){
			for(int i=0; i<en.get_arrayArañas().length;i++) {

				//int aparicionarañas = generador.nextInt(6000);
				//System.out.println(arañastodas.array()[1].arrayarañas()[i]);
				if(en.get_arrayArañas()[i]!=null) {

					if(campo.minapisadaindice(en.get_arrayArañas()[i])<1000) {
						//puntaje=en.arañascercaradioexp(campo.arrayminas()[campo.minapisadaindice(en.arrayarañas()[i])])*10;
						puntaje++;
						en.arañaCercaRadioExpNullSolo(en.get_arrayArañas()[i]);
						en.arañaPisoMinaIndice(campo);
						if( campo.exterminadorcercademinapisada(one, en.get_arrayArañas()[i])) {
							one.restarvida();
							one.spawn(ciudad);
						}
						if(campo.minapisadaindice(en.get_arrayArañas()[i])<1000) {
							if(campo.matarConMinasucesivas(campo.minacercana(campo.arrayminas()[campo.minapisadaindice(en.get_arrayArañas()[i])])
									,en.get_arrayArañas()[i])) {


								en.HacerNullAraña(i);
								puntaje++;
							}
						}
						if(campo.minapisadaindice(en.get_arrayArañas()[i])<1000) {
							if(campo.arrayminas()[campo.minapisadaindice(en.get_arrayArañas()[i])]!=null) {
								campo.minahacernull(campo.minapisadaindice(en.get_arrayArañas()[i]));
								puntaje++;
							}
						}

						en.HacerNullAraña(i); 

					}
				}
				if(one.perdiste(en.get_arrayArañas()[i])) {
					one.restarvida();
					one.spawn(ciudad);

				}

			}
		}


	}
	//esta funcion toma un array de "minas" y lo compara posicionalmente con un "disparo", un "exterminador" y un "enjambre" es decir los diferentes objetos 
	private void interaccionDisparoMinas(Campominado c, Disparar d, Exterminador e, Enjambre en) {
		if(c!=null && d!=null && e!=null && en!=null) {	
			if(c.indiceMinaRecibeDisparo(d)>=0) {
				if(en!=null){

					if(en.arañaCercaRadioExp(c.arrayminas()[c.indiceMinaRecibeDisparo(d)])<en.get_arrayArañas().length) {
						en.HacerNullAraña(en.arañaCercaRadioExp(c.arrayminas()[c.indiceMinaRecibeDisparo(d)]));
					}
					if(c.MinaCerca(c.arrayminas()[c.indiceMinaRecibeDisparo(d)])<c.arrayminas().length) {	
						if(en.arañaCercaRadioExp(c.arrayminas()[c.MinaCerca(c.arrayminas()[c.indiceMinaRecibeDisparo(d)])])<en.get_arrayArañas().length) {
							en.HacerNullAraña(en.arañaCercaRadioExp(c.arrayminas()[c.MinaCerca(c.arrayminas()[c.indiceMinaRecibeDisparo(d)])]));
							c.minahacernull(c.MinaCerca(c.arrayminas()[c.indiceMinaRecibeDisparo(d)]));
						}
						if(c.indiceMinaRecibeDisparo(d)>=0) {
							if(en.arañaCercaRadioExp(c.arrayminas()[c.indiceMinaRecibeDisparo(d)])>=0){

							}
						}
						c.minahacernull(c.MinaCerca(c.arrayminas()[c.indiceMinaRecibeDisparo(d)]));
					}
				}
				if(c.indiceMinaRecibeDisparo(d)>=0) {
					c.minahacernull(c.indiceMinaRecibeDisparo(d));	
				}
				d.hacernull();
			}
		}
	}
	//esta funcion toma un jugador "Exterminador" y lo saca del borde de la pantalla llamando a la funcion "salirdelborde" de la clase "Exterminador"
	private void limitarpantallaJugador(Exterminador e, Entorno entorno) {
		if(e!=null) {
			e.salirdelborde(entorno);
		}
	}

	// esta funcion devuelve un booleano tomando un "Exterminador" y un arreglo de "Telaraña" si el objeto "Exterminador" 
	//no esta vacío y si alguna "Telaraña" colisiona con el "Exterminador". Devuelve verdadero y realentiza el jugador

	private boolean comprobarchoquescontelas(Exterminador one, Redtelas telaranias) {
		if(one!=null) {
			if(telaranias.tocajugador(one)) {
				one.realentizar();	
				return true;
			}
		}
		return false;
	}
	//Esta funcion dibuja un arreglo del objeto "Edificio" tomando su imagen. 
	public void dibujaredificios(Entorno e, Image edificios) {
		ciudad.dibujar(e, edificios);
	}

	//Esta funcion vuelve a aparecer objetos del tipo "Spider" si estan en null. 
	private void reaparecerarañasmuertas(Enjambre en, Entorno entorno, int tiempo) {
		if(en!=null) {
			en.spawnArañasnull(en, entorno,tiempo);
		}
	}
	//la siguiente controla el tiempo de aparicion del objeto"Telaraña", y las crea y dibuja llamando diferentes funciones. 
	//
	public void telarañasaparecer(int tiempo, Entorno entorno, Image tela) {
		telaranias.comprobartiempo(tiempo);
		telaranias.dibujartelas(entorno, tela);
		if(tiempo % 500 ==0) {
			telaranias.creartela(tiempo,enjambreJugador_1);
		}
	}
	//dibuja el objeto "Spider" controlado por la clase "Enjambre", los mueve y los hace chocar a travez de las funciones
	private void araniasDibujarYMover(Enjambre en, Entorno entorno, Ciudad ciudad, Exterminador one, Campominado campo,Image aranas) {
		if(en!=null) {
			en.dibujar(entorno,aranas);
			en.chocanedificios(ciudad,one);
			en.mover(one, entorno,aranas);
		}

	}

	//Sube el puntaje del jugador si el objeto "Enjambre" recibe un disparo.
	private void araniasrecibirdisaparo(Enjambre en, Disparar bala) {
		if(bala!=null&& en !=null) {
			if(en.recibirdisparo( bala)){
				bala=null;
				puntaje++;
			}
		}
	}

	//Devuelve el entero puntaje
	public int  GetPuntaje() {
		return puntaje;
	}

	//Setea los controles del objeto "Exterminador", es decir con que teclas se moverá.
	public void Set_controljugador(Exterminador one, char avanzar, char retroceder, char girarderecha, char girarizq, Entorno entorno) {
		if(one!=null) {
			if (entorno.estaPresionada(girarizq)) {
				if (one.get_x() > 0)
					one.moveranguloizq();
			}
			if (entorno.estaPresionada(girarderecha)) {
				if (one.get_x() < entorno.ancho())
					one.moveranguloder();
			}
			if (entorno.estaPresionada(avanzar)) {
				if(one!=null) {
					if (one.get_x() > 0 && one.get_x() < entorno.ancho()
							&& one.get_y() > 0 && one.get_y() < entorno.alto()) {
						one.avanzar();
					}
				}
			}
			if (entorno.estaPresionada(retroceder))

				if (one.get_x() > 0 && one.get_x() < entorno.ancho()) {
					one.retroceder();

				}
		}
	}

	//Setea un jugador a controlar con las flechas del teclado
	public void Set_controljugadorFlechas(Exterminador one, Entorno entorno) {
		if(one!=null) {
			if (entorno.estaPresionada(entorno.TECLA_IZQUIERDA)) {
				if (one.get_x() > 0)
					one.moveranguloizq();
			}
			if (entorno.estaPresionada(entorno.TECLA_DERECHA)) {
				if (one.get_x() < entorno.ancho())
					one.moveranguloder();
			}
			if (entorno.estaPresionada(entorno.TECLA_ARRIBA)) {
				if (one.get_x() > 0 && one.get_x() < entorno.ancho()
						&& one.get_y() > 0 && one.get_y() < entorno.alto()) {
					one.avanzar();
				}
			}
			/*if (one.chocasteCon(entorno))
			one.rebotar();*/
			if (entorno.estaPresionada(entorno.TECLA_ABAJO))
				if (one.get_x() > 0 && one.get_x() < entorno.ancho()) {
					one.retroceder();

				}
		}
	}


	//Resta vida del objeto "Exterminador" si el objeto de tipo "Spider" colisiona con el jugador. Y lo reaparece
	private void colisionExterminadorconBosses(ArasGigantes bosses, Exterminador e, Ciudad ciudad){
		if(e!=null && bosses!=null) {
			if(bosses.arañaTocoJugador(e)) {
				e.restarvida();
				e.spawn(ciudad);
			}
		}
	}
	//Devuelve un booleano como verdadero si se presiona una tecla y agrega un objeto "Mine" al arreglo controlado por "CampoMinado"
	//
	public boolean ponerminas(Exterminador e, Campominado c, char tecla, Entorno entorno, int minastotales){
		if (entorno.sePresiono(tecla)) {
			if (minastotales < c.arrayminas().length) {
				c.agregarmina(minastotales,e);
				return true;
			}
		}
		return false;
	}
	//establece como disparo para un "Exterminador" la tecla espacio, y si es distinto de null lo crea y mueve;
	public Disparar setDispararConTeclaEspacio(Entorno entorno,Exterminador e, Disparar d, Image disparo){
		if (entorno.sePresiono(entorno.TECLA_ESPACIO)) {

			d = new Disparar(e);
		}
		if(d!=null) {
			d.moverdisparo(entorno, disparo);
		}
		return d;
	}

	//Setea la tecla con la que el "Exterminador" va a disparar 
	public Disparar disparar(Entorno entorno, Exterminador e, Disparar d, char tecla, Image disparo){	
		if (entorno.sePresiono(tecla)) {
			d = new Disparar(e);
		}	
		if(d!=null) {
			d.moverdisparo(entorno, disparo);
		}
		return d;
	}

	//Si el "Exterminador" recibe una colision del objeto "Disparar", le quita una vida, lo vuelve a aparecer si corresponde, y hace el "Disparo" null.
	public void jugadorrecibedisparo(Exterminador e, Disparar bala,Ciudad ciudad) {
		if(bala!=null) {	
			if(bala.bala()!=null && e!=null) {

				if(e.recibedisparo(bala)) {
					e.restarvida();
					e.spawn(ciudad);
					bala.hacernull();
				}
			}
		}

	}

	//Crea el "Potenciador" en un determinado tiempo y devuelve el booleano verdadero si el "Exterminador" colisiona con el objeto "Potenciador" 
	private boolean pot(Exterminador one, Potenciador potenciador, int tiempo, Entorno entorno){	
		if(one!=null){
			if (tiempo%1000==0 && potenciador.getSe_activo()==false){ //crea el potenciador x un det tiempo;
				potenciador.setActivo(true);
			}
			if (tiempo>2000 && potenciador.getActivo()==true){ //Da el efecto potenciador x un det tiempo;
				potenciador.setActivo(false);
			}		
			if (potenciador.getActivo()==true){ 
				potenciador.dibujar(entorno); 
			}

			else if (tiempo>2000&&potenciador.getActivo()==true){ //si no lo llego a agarrar que borre el potenciador
				potenciador.setActivo(false);
			}
			if(potenciador!=null){
				if (one.chocasteConPotenciador(potenciador) && potenciador.getActivo()==true){					
					potenciador.setActivo(false);
					return true;
				}
			}
		}
		return false;
	}
	//Aumenta la velocidad del "Exterminador" por un determinado tiempo y vuelve a establecer su velocidad normal

	private void Potenciador(Exterminador e, Potenciador potenciador, int tiempo, Entorno entorno, Redtelas telaranias){

		if(e!=null){
			if(pot(e, potenciador, tiempo, entorno)){
				potenciador.setAux(tiempo+1000);
				e.Acelerar();
			}
			else
				if(!comprobarchoquescontelas(e, telaranias) && potenciador.getAux()<tiempo){
					e.velocidadnormal();
				}
		}
	}

	//C
	public void interaccionjugador1ConMundo (Entorno entorno, int tiempo,Image exter) {
		if(Jugador1!=null) {
			if(Jugador1.comprobarvidas()) {  //si no tiene vidas devuelve True 
				Jugador1=null;  
			}
			if(Jugador1!=null) {
				ciudad.chocar(Jugador1);  //Hace que el exterminador rebote contra la Ciudad
				Jugador1.dibujar(entorno,exter);
			}
		}
		//Controla la colision de la mina con las arañas, los disparos y minas de los distintos jugadores.
		interaccionDisparoMinas(minasJugador_1,disparojug1,Jugador1,enjambreJugador_1); 
		interaccionDisparoMinas(minas2,disparojug1,Jugador1,enjambreJugador_1);
		interaccionDisparoMinas(minasJugador_1,disparojug1,Jugador1,enjambreJugador_2);
		interaccionDisparoMinas(minas2,disparojug1,Jugador1,enjambreJugador_2);

		Potenciador(Jugador1, potenciador, tiempo, entorno, telaranias);//
		if(disparojug1!=null) {
			disparojug1.choquedisparo(entorno, ciudad); //Hace que el "Disparo" colisione con el array de "Edificio" de la clase "Ciudad" 
		}
		//Llamado a las funciones ya descritas anteriormente
		bossmuertos = bosses.contarBossesMuertos(puntaje, bossmuertos, 50); //
		comprobarchoquescontelas(Jugador1, telaranias);//
		interaccionMinasAranasyJugador(Jugador1, enjambreJugador_1, tiempo, ciudad, minasJugador_1);
		interaccionMinasAranasyJugador(Jugador1, matanboss, tiempo, ciudad,	minasJugador_1);
		interaccionMinasAranasyJugador(Jugador1, enjambreJugador_2, tiempo, ciudad, minasJugador_1);
		araniasrecibirdisaparo(enjambreJugador_1, disparojug1);
		araniasrecibirdisaparo(enjambreJugador_1, disparojug2);
		araniasrecibirdisaparo(matanboss,disparojug1);
		colisionExterminadorconBosses(bosses, Jugador1, ciudad);
		bosses.pisarMinas(Jugador1, minasJugador_1);
		limitarpantallaJugador(Jugador1, entorno);
		jugadorrecibedisparo(Jugador1, disparojug2, ciudad);
		reaparecerarañasmuertas(enjambreJugador_1, entorno, tiempo);
		bosses.recibirDisparo(disparojug1);
		bosses.chocanEdificios(ciudad);
		//Hace que las minas del Jugador 1 interactuen con la clase "Arasgigantes" 
		if(bossesjug2!=null) {
			bossesjug2.pisarMinas(Jugador1, minasJugador_1);
			bossesjug2.recibirDisparo(disparojug1);
		}
		/*if (bosses.controlarvida() != null) {
			matanboss.Agregararañaboss(entorno, bosses.controlarvida());
			matanboss.Agregararañaboss(entorno, bosses.controlarvida());
			puntaje += 10;

		}*/
		//Representa el entero vidas del Jugador 1 en la pantalla
		if(Jugador1!=null) {
			entorno.cambiarFont("arial", 20, Color.CYAN);
			entorno.escribirTexto(Integer.toString(Jugador1.get_vidas()), 120, 50);
		}

	}

	//Interactua igual que el anterior pero con Jugador 2 
	public Exterminador interaccionjugador2( Entorno entorno, int tiempo, Image exter) {
		if(Jugador2!=null) {
			if(Jugador2.comprobarvidas()) {
				Jugador2=null;
			}
		}
		if(Jugador2!=null) {
			Jugador2.dibujar(entorno,exter);
		}
		if(Jugador1!=null) {
			ciudad.chocar(Jugador2);
		}
		int bossmuertos=0;
		bossmuertos = bosses.contarBossesMuertos(puntaje, bossmuertos, 50);

		Potenciador(Jugador2, potenciador, tiempo, entorno, telaranias);

		comprobarchoquescontelas(Jugador2, telaranias);
		interaccionMinasAranasyJugador(Jugador2, enjambreJugador_2, tiempo, ciudad, minas2);
		interaccionMinasAranasyJugador(Jugador2, matanboss, tiempo, ciudad,	minas2);
		interaccionMinasAranasyJugador(Jugador2, enjambreJugador_1, tiempo, ciudad, minas2);
		//hacemos que el disparo interaccione con los distintos enjambres
		araniasrecibirdisaparo(enjambreJugador_2, disparojug1);
		araniasrecibirdisaparo(enjambreJugador_2, disparojug2);
		araniasrecibirdisaparo(matanboss,disparojug2);
		//hacemos que el array de bosses siga al jugador 2 y choque con la ciudad
		colisionExterminadorconBosses(bossesjug2, Jugador2 , ciudad);

		if (disparojug2 != null) {
			disparojug2.choquedisparo(entorno, ciudad);
		}


		if(bossesjug2!=null) {
			bossesjug2.pisarMinas(Jugador2, minas2);
		}
		limitarpantallaJugador(Jugador2, entorno);
		jugadorrecibedisparo(Jugador2, disparojug1, ciudad);
		bosses.pisarMinas(Jugador2, minas2);
		bosses.recibirDisparo(disparojug2);
		reaparecerarañasmuertas(enjambreJugador_2, entorno, tiempo);	
		if(bossesjug2!=null){

			bossesjug2.chocanEdificios(ciudad);
			bossesjug2.recibirDisparo(disparojug1);
			bossesjug2.recibirDisparo(disparojug2);
			bossesjug2.pisarMinas(Jugador2, minas2);
			bossesjug2.pisarMinas(Jugador1, minasJugador_1 );
		
		}
		if(Jugador2!=null) {
			entorno.cambiarFont("arial", 20, Color.CYAN);
			entorno.escribirTexto("vidas", 400, 50);
			entorno.escribirTexto(Integer.toString(Jugador2.get_vidas()), 500, 50);
		}
		return Jugador2;
	}

	//dibuja las minas del "Exterminador" 
	public void dibujarMinas(Entorno entorno) {
		minasJugador_1.dibujarminas(entorno);
		if(minas2!=null) {
			minas2.dibujarminas(entorno);
		}
	}

	//dibuja las arañas y con sus respectivas funciones hace que sigan a los jugadores y si muere uno comienzan a seguir al que queda vivo.
	public void dibujarYMoverAranias(Entorno entorno,Image arasgrandes, Image aranas) {
		if(bossesjug2!=null) {	
			bossesjug2.aparecerBosses(entorno, ciudad, Jugador2, puntaje,30 ,bossmuertos, arasgrandes);
		}
		bosses.aparecerBosses(entorno, ciudad, Jugador1, puntaje,1 ,bossmuertos, arasgrandes);
		if(Jugador1!=null) {
			araniasDibujarYMover(enjambreJugador_1, entorno, ciudad, Jugador1, minasJugador_1, aranas);
		}
		else {
			araniasDibujarYMover(enjambreJugador_1, entorno, ciudad, Jugador2, minas2, aranas);
		}
		if(Jugador2!=null) {
			araniasDibujarYMover(enjambreJugador_2, entorno, ciudad, Jugador2, minas2, aranas);
		}
		else {
			araniasDibujarYMover(enjambreJugador_2, entorno, ciudad, Jugador1, minasJugador_1, aranas);

		}


		if(matanboss!=null) {
			araniasDibujarYMover(matanboss,entorno,ciudad,Jugador1,minasJugador_1,aranas);
		}
	}

	//Controla la vida de la clase "ArasGigantes" y crear dos objetos "Spider" cuando muere.
	private void controlarvidabosses( Entorno entorno,ArasGigantes bosseslista){
		Punto temp = bosseslista.controlarVida();
		matanboss.agregarArañaBoss(entorno,temp);
		matanboss.agregarArañaBoss(entorno,temp);	
	}
	//controla la vida de los bosses.
	public void controlarVidaDeBosses(Entorno e) {
		controlarvidabosses(e, bosses);
		controlarvidabosses(e,bossesjug2);
	}
	//Pone las minas de los jugadores y aumenta la variable de instancia que cuenta la cantidad de "Mine" puestas.
	public void jugadoresPonerMinas(Entorno entorno, char jugador1, char jugador2) {
		if (ponerminas(Jugador1, minasJugador_1, jugador1, entorno, minasjug1)) {
			minasjug1++;
		}
		if (ponerminas(Jugador2, minas2, jugador2, entorno, minasjug2)) {
			minasjug2++;
		}
	}
	//Si la vida de los jugadores es igual a 0, vuelve al menú inicial.
	public void controlarVidaJugadores(int tiempo, Entorno e) {

		if (Jugador1==null && Jugador2==null) {
			e.cambiarFont("Arial", 100, Color.ORANGE);
			e.escribirTexto("GAME OVER", 100, 300);
			e.cambiarFont("Arial", 40, Color.ORANGE);
			e.escribirTexto("Enter para volver", 300, 500);
			if(e.sePresiono(e.TECLA_ENTER)) {
				menu=true;
				menuniveles=false;
				players.volvermenuaoriginal();
				niveles.volvermenuaoriginal();
			}
		}
	}

	public boolean getMenu() {

		return menu;
	}
	public void setmenu(boolean b) {
		menu=b;

	}
	public void getMenuCantidadJugadores() {
		players.volvermenuaoriginal();

	}
	public int getMinasJug1() {

		return minasjug1;
	}
	public int getMinasJug2() {
		return minasjug2;

	}

	//Setea los controles de los jugadores pidiendo las teclas
	public void setJugadorControles(Entorno entorno, char adelante, char atras, char derecha, char izquierda, int numerojugador) {

		if(numerojugador==1) {
			Set_controljugador(Jugador1, adelante, atras,derecha, izquierda,entorno);
		}
		if(numerojugador==2) {
			Set_controljugador(Jugador2, adelante, atras,derecha, izquierda,entorno);
		}
	}
	//Setea las flechas del teclado como control del jugador
	public void setJugadorControlFlechas(Entorno entorno, int numerojugador) {
		if(numerojugador==1) {
			Set_controljugadorFlechas(Jugador1,entorno);
		}
		if(numerojugador==2) {
			Set_controljugadorFlechas(Jugador2,entorno);
		}
	}

	//setea la tecla con el que se dispara
	public void setTeclaDisparo(int numerojugador, char tecla, Entorno entorno, Image disparo) {
		if(numerojugador==1) {
			disparojug1=disparar(entorno, Jugador1, disparojug1, tecla, disparo);
		}
		if(numerojugador==2) {
			disparojug2=disparar(entorno, Jugador2, disparojug2, tecla, disparo);
		}
	}
	// Establecemos el menu de eleccion de jugadores.
	public int menuCantjugadores(int tiempo,Entorno entorno)	{
		if(!menuniveles) {
			niveles.volvermenuaoriginal();

			entorno.cambiarFont("comic sans MS", 100, Color.BLUE);
			entorno.escribirTexto("UNGS OF WAR", 50, 100);
			players.agregarString("un jugador");
			players.agregarString("dos jugadores");
			players.agregarString("exit");
			players.dibujar(entorno,200,250);
			players.mover(entorno);
			players.seleccion(entorno);
			players.iniciarmenu(entorno);
		}
		if(players.seleccion(entorno)==2) {
			System.exit(0);
		}
		if (players.seleccion(entorno)==1) {	
			this.Jugador1 = new Exterminador(400, 300, 0, 1, 4);
			this.Jugador2=new Exterminador(400, 300, 0, 1, 4);
			this.enjambreJugador_2 = new Enjambre(entorno, 10);
			Jugador1.spawn(ciudad);
			Jugador2.spawn(ciudad);
			menuniveles=true;

			return 2;


		}
		if(players.seleccion(entorno)==0) {
			this.Jugador1 = new Exterminador(400, 300, 0, 1, 4);
			Jugador1.spawn(ciudad);
			menuniveles=true;

			return 1;


		}
		return 10000;
	}
	// Establecemos el menu de eleccion de nivel.

	public void niveles(int cantidadjugadores, Entorno entorno) {

		if(menuniveles) {
			potenciador=new Potenciador(6,7,0,false);
			this.minasJugador_1 = new Campominado();
			this.minas2 = new Campominado();

			niveles.agregarString("nivel 1");
			niveles.agregarString("nivel 2");
			niveles.agregarString("nivel 3");
			niveles.agregarString("nivel 4");
			niveles.agregarString("volver");
			niveles.dibujar(entorno,250, 200);
			niveles.mover(entorno);
			niveles.seleccion(entorno);
			niveles.iniciarmenu(entorno);
			if(niveles.seleccion(entorno)==0){
				setAraniasIniciales(10,5,cantidadjugadores,entorno);
				menu=false;
			}
			if(niveles.seleccion(entorno)==1){
				setAraniasIniciales(15,6,cantidadjugadores,entorno);
				menu=false;
			}
			if(niveles.seleccion(entorno)==2){
				setAraniasIniciales(20,8,cantidadjugadores,entorno);
				menu=false;
			}
			if(niveles.seleccion(entorno)==3){
				setAraniasIniciales(30,10,cantidadjugadores,entorno);
				menu=false;
			}
			if (niveles.seleccion(entorno)==4) {
				menuniveles=false;	
			}
		}
	}	
	//establecemos el constructor de las arañas, para que vaya cambiando segun el nivel
	private void setAraniasIniciales(int arañas, int bosses,int cantidadjugadores, Entorno entorno) {
		if(cantidadjugadores==1 || cantidadjugadores==2) {
			this.bosses = new ArasGigantes(bosses, entorno);
			this.enjambreJugador_1 = new Enjambre(entorno, arañas);
			this.matanboss = new Enjambre(entorno, arañas, "BOSSS");
			this.bossmuertos=0;
			puntaje=0;

		}
		if (cantidadjugadores==2) {
			this.bossesjug2= new ArasGigantes(bosses, entorno);
			this.enjambreJugador_2= new Enjambre(entorno, arañas);

			puntaje=0;

		}
	}

	//Setea la barra espaciadora como tecla para disparar
	public void setDispararConTeclaEspacio(Entorno e, Image disparo, int numerojug) {
		if(numerojug==1) {
			disparojug1=disparar(e, Jugador1, disparojug1, e.TECLA_ESPACIO, disparo);
		}
		if(numerojug==2) {
			disparojug2=disparar(e, Jugador2, disparojug2, e.TECLA_ESPACIO, disparo);
		}
	}
}


