
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextComponent;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.ShutdownChannelGroupException;
import java.text.BreakIterator;

import javax.sql.rowset.spi.TransactionalWriter;
import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;




public class Interface implements ActionListener {

	private JFrame frame;
	private JTextField textField;
	private JuegoDeLuces Juego;
	private JPanel pBotonesTablero;
	private JButton [] tableroBotones;
	private int niveles;
	int intentos;
	private JButton lvl1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Interface window = new Interface();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Interface() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 579, 484);
		frame.setTitle("Fuera Luces - UNGS TP");
		frame.getContentPane().setBackground(new Color(210, 180, 140));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		
		//AREA DEL TABLERO
		//-------------------------------------------------------------------------
		JPanel panelParaTablero = new JPanel();
		panelParaTablero.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		panelParaTablero.setBounds(168, 55, 407, 368);
		frame.getContentPane().add(panelParaTablero);
		panelParaTablero.setLayout(null);
		
		//--------------------------------------------------------------------------
		
		//PANEL LATERAL IZQUIERDO
		//-----------------------------------------------------------------------
		JPanel panelnteractivo = new JPanel();
		panelnteractivo.setBackground(new Color(224, 255, 255));
		panelnteractivo.setBounds(0, 0, 163, 455);
		frame.getContentPane().add(panelnteractivo);
		panelnteractivo.setLayout(null);
		//-----------------
		JPanel head = new JPanel();
		head.setBackground(new Color(240, 230, 140));
		head.setToolTipText("FUERA LUCES ");
		head.setBounds(10, 11, 143, 48);
		panelnteractivo.add(head);
		
		JLabel TEXTOHEAD = new JLabel("FUERA LUCES");
		TEXTOHEAD.setBackground(new Color(205, 133, 63));
		TEXTOHEAD.setFont(new Font("Permanent Marker", Font.PLAIN, 18));
		head.add(TEXTOHEAD);
		
		//-----------------------------------------------------------
		//muestra los intentas realizados por el jugador
		JPanel panelNombres = new JPanel();
		panelNombres.setBackground(new Color(119, 136, 153));
		panelNombres.setBounds(20, 66, 121, 48);
		panelnteractivo.add(panelNombres);
		
		JLabel lblNewLabel = new JLabel(" nº de intentos:");
		lblNewLabel.setForeground(new Color(240, 248, 255));
		lblNewLabel.setFont(new Font("Sitka Text", Font.BOLD, 11));
		panelNombres.add(lblNewLabel);
		
		textField = new JTextField();
		panelNombres.add(textField);
		textField.setColumns(10);
		
		//--------------------------
		
		//PANEL DE ELECCION DE NIVELES
		JPanel PanelNiveles = new JPanel();
		PanelNiveles.setBackground(new Color(205, 133, 63));
		PanelNiveles.setBounds(10, 125, 143, 188);
		panelnteractivo.add(PanelNiveles);
		PanelNiveles.setLayout(null);
		
		
		
		//BOTONES DE LV1 LV2 Y LV3
		//--------------------------------------------------------------------------------------
		JButton lvl1 = new JButton("CAMPESINO");
		lvl1.setBounds(10, 35, 123, 40);
		PanelNiveles.add(lvl1);
		lvl1.addActionListener(this);
		lvl1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Juego=new JuegoDeLuces(4);
				Juego.mezclarLuces();
				CrearBotones(4,4);
				
			}
		});
		
		JButton lvl2 = new JButton("GERRERO");
		lvl2.setBounds(10, 86, 123, 40);
		PanelNiveles.add(lvl2);
		lvl2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Juego=new JuegoDeLuces(6);
				Juego.mezclarLuces();
				CrearBotones(6,6);
				;
			}
		});
		
		JButton lvl3 = new JButton("DIOS");
		lvl3.setBounds(10, 137, 123, 40);
		PanelNiveles.add(lvl3);
		lvl3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Juego=new JuegoDeLuces(8);
				Juego.mezclarLuces();
				CrearBotones(8,8);
				
			}
		});
		//----------------------------------------------------------------------------------------------------------		
		//panel para los botones del tablero
		this.pBotonesTablero=new JPanel();
		
		
		//------------------------------------------------------------------------------

		
	}
	
			

	@Override
	public void actionPerformed(ActionEvent e) {
	
		int cont=0;
		for(int i=0;i<this.Juego.getTamaño();i++) {
			for (int j=0;j<this.Juego.getTamaño();j++) {
				if(((JButton)e.getSource()).equals(tableroBotones[cont])){
					intentos++;
					System.out.println("boton "+cont+" presionado :)");
					textField.setText(""+intentos);
					Juego.EncenderLuz(i, j);
					mostrarTablero();
				}
				cont++;
				System.out.println("el contador es de :"+cont);
			}
		}
		cont=0;
	
		VerificarGanador();
	}
	
	
	private void mostrarTablero() {
		int cont=0;
		for(int i=0;i<this.Juego.getTamaño();i++) {
			for (int j=0;j<this.Juego.getTamaño();j++) {
				if (this.Juego.getPosDelTablero(i,j))
					tableroBotones[cont].setText("1");
				else
					tableroBotones[cont].setText("0");
				
				cont++;
				System.out.println("el contador es de :"+cont);
			}
		}
		cont=0;
	}
	

	private void VerificarGanador() {
		if (Juego.VerificarGanador()) {
			JOptionPane.showInputDialog(lvl1,"Fuera Luces","Termino el Juego", 0, null, null, "¡¡¡Ganaste, Bien echo!!!");
		System.exit(0);}
	}



	//metodo para generar los botones a un GridLayout, puede recibir renglon y columna y dar un efecto de tablero 
		public void CrearBotones(int fila, int columna) {
			pBotonesTablero.setBackground(new Color(224, 255, 255));
			pBotonesTablero.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
			pBotonesTablero.setBounds(173, 69, 390, 375);
			frame.getContentPane().add(pBotonesTablero);
			pBotonesTablero.removeAll();
			pBotonesTablero.setLayout(new GridLayout(fila,columna,0,0));
			tableroBotones = new JButton[fila*columna];
			for(int i=0;i<(fila*columna);i++) {
				tableroBotones[i]=new JButton();
//				tableroBotones[i].setRolloverIcon(new ImageIcon("C:\\Users\\pc\\Downloads\\botonPresionado.png"));
//				tableroBotones[i].setPressedIcon(new ImageIcon("C:\\Users\\pc\\Downloads\\botonPresionado.png"));
//				tableroBotones[i].setSelectedIcon(new ImageIcon("C:\\Users\\pc\\Downloads\\BotonNormal.png"));
//				tableroBotones[i].setIcon(new ImageIcon("C:\\Users\\pc\\Downloads\\BotonNormal.png"));
				tableroBotones[i].addActionListener(this);
				pBotonesTablero.add(tableroBotones[i]);
				
			}
			pBotonesTablero.updateUI(); //ACT panel
			mostrarTablero();
		}
}






