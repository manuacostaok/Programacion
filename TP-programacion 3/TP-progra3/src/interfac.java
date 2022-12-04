

import java.awt.EventQueue;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.ShutdownChannelGroupException;

import javax.sql.rowset.spi.TransactionalWriter;
import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;




public class interfac implements ActionListener {

	private JFrame frame;
	private JTextField textField;
	private JuegoDeLuces Juego;
	
	
	private JButton boton0;
	private JButton boton1;
	private JButton boton2;
	private JButton boton3;
	private JButton boton4;
	private JButton boton5;
	private JButton boton6;
	private JButton boton7;
	private JButton boton8;
	private JButton boton9;
	private JButton boton10;
	private JButton boton11;
	private JButton boton12;
	private JButton boton13;
	private JButton boton14;
	private JButton boton15;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					interfac window = new interfac();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public interfac() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(10, 21, 385, 32);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		Juego=new JuegoDeLuces(4);
		Juego.mezclarLuces();
		
		
		//aqui creamos los botones de la interface 4x4
		//-------------------------------------------------------------------------
		boton0 = new JButton("0");
		boton0.addActionListener(this);
		boton0.setBounds(10, 119, 89, 23);
		frame.getContentPane().add(boton0);
		
		 boton1 = new JButton("1");
		 boton1.addActionListener(this);
		 boton1.setBounds(109, 119, 89, 23);
		frame.getContentPane().add( boton1);
		
		boton2 = new JButton("2");
		boton2.addActionListener(this);
		boton2.setBounds(208, 119, 89, 23);
		frame.getContentPane().add(boton2);
		
		boton4 = new JButton("4");
		boton4.addActionListener(this);
		boton4.setBounds(10, 153, 89, 23);
		frame.getContentPane().add(boton4);
		
		boton3 = new JButton("3");
		boton3.addActionListener(this);
		boton3.setBounds(306, 119, 89, 23);
		frame.getContentPane().add(boton3);
		
		
		boton5 = new JButton("5");
		boton5.addActionListener(this);
		boton5.setBounds(109, 153, 89, 23);
		frame.getContentPane().add(boton5);
		
		boton6 = new JButton("6");
		boton6.addActionListener(this);
		boton6.setBounds(208, 153, 89, 23);
		frame.getContentPane().add(boton6);
		
		boton7 = new JButton("7");
		boton7.addActionListener(this);
		boton7.setBounds(306, 153, 89, 23);
		frame.getContentPane().add(boton7);
		
		boton8 = new JButton("8");
		boton8.addActionListener(this);
		boton8.setBounds(10, 187, 89, 23);
		frame.getContentPane().add(boton8);
		
		 boton9 = new JButton("9");
		boton9.addActionListener(this);
		boton9.setBounds(109, 187, 89, 23);
		frame.getContentPane().add(boton9);
		
		boton11 = new JButton("11");
		boton11.addActionListener(this);
		boton11.setBounds(306, 187, 89, 23);
		frame.getContentPane().add(boton11);
		
		boton10= new JButton("10");
		boton10.addActionListener(this);
		boton10.setBounds(208, 187, 89, 23);
		frame.getContentPane().add(boton10);
		
		
		boton12 = new JButton("12");
		boton12.addActionListener(this);
		boton12.setBounds(10, 221, 89, 23);
		frame.getContentPane().add(boton12);
		
	    boton13 = new JButton("13");
	    boton13.addActionListener(this);
	    boton13.setBounds(109, 221, 89, 23);
		frame.getContentPane().add( boton13);
		
		boton14 = new JButton("14");
		boton14.addActionListener(this);
		boton14.setBounds(208, 221, 89, 23);
		frame.getContentPane().add(boton14);
		
		boton15 = new JButton("15");
		boton15.addActionListener(this);
		boton15.setBounds(307, 221, 89, 23);
		frame.getContentPane().add(boton15);
		
	
		
		mostrarTablero();
		System.out.println(Juego.toString());
		VerificarGanador();
	}
	
		
		

	@Override
	public void actionPerformed(ActionEvent e) {
		
		//SI PRECIONO UN NUMERO
		if(((JButton)e.getSource()).equals(boton0)){
			System.out.println(boton0.getText());
			Juego.EncenderLuz(0, 0);
			mostrarTablero();
			textField.setText(""+textField.getText()+"0");	
		}
		if(((JButton)e.getSource()).equals(boton1)){
			System.out.println(boton1.getText());
			Juego.EncenderLuz(0, 1);
			mostrarTablero();
			textField.setText(""+textField.getText()+"1");	
		}
		if(((JButton)e.getSource()).equals(boton2)){
			System.out.println(boton2.getText());
			Juego.EncenderLuz(0, 2);
			mostrarTablero();
			textField.setText(""+textField.getText()+"2");	
		}
		if(((JButton)e.getSource()).equals(boton3)){
			System.out.println(boton3.getText());
			Juego.EncenderLuz(0, 3);
			mostrarTablero();
			textField.setText(""+textField.getText()+"3");	
		}
		if(((JButton)e.getSource()).equals(boton4)){
			System.out.println(boton4.getText());
			Juego.EncenderLuz(1, 0);
			mostrarTablero();
			textField.setText(""+textField.getText()+"4");	
		}
		if(((JButton)e.getSource()).equals(boton5)){
			System.out.println(boton5.getText());
			Juego.EncenderLuz(1, 1);
			mostrarTablero();
			textField.setText(""+textField.getText()+"5");	
		}
		if(((JButton)e.getSource()).equals(boton6)){
			System.out.println(boton6.getText());
			Juego.EncenderLuz(1, 2);
			mostrarTablero();
			textField.setText(""+textField.getText()+"6");	
		}
		if(((JButton)e.getSource()).equals(boton7)){
			System.out.println(boton7.getText());
			Juego.EncenderLuz(1, 3);
			mostrarTablero();
			textField.setText(""+textField.getText()+"7");	
		}
		if(((JButton)e.getSource()).equals(boton8)){
			System.out.println(boton8.getText());
			Juego.EncenderLuz(2, 0);
			mostrarTablero();
			textField.setText(""+textField.getText()+"8");	
		}
		if(((JButton)e.getSource()).equals(boton9)){
			System.out.println(boton9.getText());
			Juego.EncenderLuz(2, 1);
			mostrarTablero();
			textField.setText(""+textField.getText()+"9");	
		}
		
		
		if(((JButton)e.getSource()).equals(boton10)){
			System.out.println(boton10.getText());
			Juego.EncenderLuz(2, 2);
			mostrarTablero();
			textField.setText(""+textField.getText()+"10");
		}
				
		if(((JButton)e.getSource()).equals(boton11)){
			System.out.println(boton11.getText());
			Juego.EncenderLuz(2, 3);
			mostrarTablero();
			textField.setText(""+textField.getText()+"11");
				
		}
		
				
		if(((JButton)e.getSource()).equals(boton12)){
			System.out.println(boton12.getText());
			Juego.EncenderLuz(3, 0);
			mostrarTablero();
			textField.setText(""+textField.getText()+"12");
		}
		
				
		if(((JButton)e.getSource()).equals(boton13)){
			System.out.println(boton13.getText());
			Juego.EncenderLuz(3, 1);
			mostrarTablero();
			textField.setText(""+textField.getText()+"13");
		}
		
		
		
		if(((JButton)e.getSource()).equals(boton14)){
			System.out.println(boton14.getText());
			Juego.EncenderLuz(3, 2);
			mostrarTablero();
			textField.setText(""+textField.getText()+"14");
			
		}
		
			 
		if(((JButton)e.getSource()).equals(boton15)){
			System.out.println(boton15.getText());
			Juego.EncenderLuz(3, 3);
			mostrarTablero();
			textField.setText(""+textField.getText()+"15");
			
		}
	}
	
	
	private void mostrarTablero() {
		
		if (this.Juego.getPosDelTablero(0,0))
			boton0.setText("1");
		else
			boton0.setText("0");
		
		if (this.Juego.getPosDelTablero(0,1))
			boton1.setText("1");
		else
			boton1.setText("0");
		
		if (this.Juego.getPosDelTablero(0,2))
			boton2.setText("1");
		else
			boton2.setText("0");
		
		if (this.Juego.getPosDelTablero(0,3))
			boton3.setText("1");
		else
			boton3.setText("0");
		
		if (this.Juego.getPosDelTablero(1,0))
			boton4.setText("1");
		else
			boton4.setText("0");
		
		if (this.Juego.getPosDelTablero(1,1))
			boton5.setText("1");
		else
			boton5.setText("0");
		
		if (this.Juego.getPosDelTablero(1,2))
			boton6.setText("1");
		else
			boton6.setText("0");
		
		if (this.Juego.getPosDelTablero(1,3))
			boton7.setText("1");
		else
			boton7.setText("0");
		
		if (this.Juego.getPosDelTablero(2,0))
			boton8.setText("1");
		else
			boton8.setText("0");
		
		if (this.Juego.getPosDelTablero(2,1))
			boton9.setText("1");
		else
			boton9.setText("0");
		
		if (this.Juego.getPosDelTablero(2,2))
			boton10.setText("1");
		else
			boton10.setText("0");
		
		if (this.Juego.getPosDelTablero(2,3))
			boton11.setText("1");
		else
			boton11.setText("0");
		
		if (this.Juego.getPosDelTablero(3,0))
			boton12.setText("1");
		else
			boton12.setText("0");
		
		if (this.Juego.getPosDelTablero(3,1))
			boton13.setText("1");
		else
			boton13.setText("0");
		
		if (this.Juego.getPosDelTablero(3,2))
			boton14.setText("1");
		else
			boton14.setText("0");
		
		if (this.Juego.getPosDelTablero(3,3))
			boton15.setText("1");
		else
			boton15.setText("0");		
	}

	
	private void VerificarGanador() {
		if (Juego.VerificarGanador()) {
			JOptionPane.showInputDialog(boton15, this,"Termino el Juego", 0, null, null, "Game Over");
		System.exit(0);}
	}
}