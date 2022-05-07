package com.TEAM4_C4.TIC_TAC_TOE;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JToggleButton;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JEditorPane;
import java.awt.SystemColor;

public class Juego extends JFrame {

	private JPanel contentPane;

	// Iniciar tablero
	static char[][] tablero = { { ' ', ' ', ' ' }, { ' ', ' ', ' ' }, { ' ', ' ', ' ' } };

	public static JLabel labelNombre1 = new JLabel("");
	public static JLabel labelNombre2 = new JLabel("");

	// este boolean determina quien empieza la partida:
	static boolean jugador = primerTurno();
	// creo una variable turno inicializada
	static int turno = 0;
	static boolean octirada = false;
	static final JLabel infoTurno = new JLabel("Es el turno de " + jugador);
	public  static 	Juego juego = new Juego();
	
	//esta variable nos indica una vez llegada la fase 2, si el usuario ya ha quitado una ficha que quiere mover
	public static boolean haCambiado = false;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//Juego frame = new Juego();
					juego.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Juego() {
		setTitle("Los treses en la línea [Team 4]");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 600, 800, 400);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setForeground(new Color(255, 255, 255));

		contentPane.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.BLACK, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNuevaPartida = new JButton("Nueva Partida");
		btnNuevaPartida.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
		btnNuevaPartida.setForeground(new Color(51, 204, 153));
		

		btnNuevaPartida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				limpiarTablero(tablero);
				juego.setVisible(false);
				App abrir = new App();
				abrir.setVisible(true);

			}
		});
		btnNuevaPartida.setBounds(543, 16, 201, 29);
		contentPane.add(btnNuevaPartida);
		infoTurno.setFont(new Font("Lucida Grande", Font.BOLD, 13));

		infoTurno.setBounds(489, 54, 358, 16);
		contentPane.add(infoTurno);
		labelNombre1.setFont(new Font("Kohinoor Gujarati", Font.BOLD | Font.ITALIC, 13));

		labelNombre1.setBounds(499, 137, 61, 16);
		contentPane.add(labelNombre1);
		labelNombre2.setFont(new Font("Kohinoor Gujarati", Font.BOLD | Font.ITALIC, 13));

		labelNombre2.setBounds(520, 137, 183, 16);
		contentPane.add(labelNombre2);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 0, 0));
		panel.setBounds(18, 16, 440, 335);
		panel.setBorder(new EmptyBorder(5,5,5,5));

		contentPane.add(panel);
		panel.setLayout(new GridLayout(3, 3, 0, 0));

		final JToggleButton btn11 = new JToggleButton(" ");
		panel.add(btn11);

		final JToggleButton btn12 = new JToggleButton(" ");
		panel.add(btn12);

		final JToggleButton btn13 = new JToggleButton(" ");
		panel.add(btn13);

		final JToggleButton btn21 = new JToggleButton(" ");
		panel.add(btn21);

		final JToggleButton btn22 = new JToggleButton(" ");
		panel.add(btn22);

		final JToggleButton btn23 = new JToggleButton(" ");
		panel.add(btn23);

		final JToggleButton btn31 = new JToggleButton(" ");
		panel.add(btn31);

		final JToggleButton btn32 = new JToggleButton(" ");
		panel.add(btn32);

		final JToggleButton btn33 = new JToggleButton(" ");
		panel.add(btn33);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 0));
		panel_1.setBounds(470, 0, 3, 384);
		contentPane.add(panel_1);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(Juego.class.getResource("/com/TEAM4_C4/TIC_TAC_TOE/img/fondo.png")));
		lblNewLabel.setBounds(0, -22, 471, 394);
		contentPane.add(lblNewLabel);

		// LISTENERS
		btn11.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// DONE COMENTAR
				if (jugador) {
					
					fase1(btn11);
						
					if(turno > 6){ //FASE 2 - PARTE 1 si el turno es mayor que 6 | fase2();
						
						if (btn11.getText().equals("X") && !haCambiado) { //tenemos que mirar que la ficha se pueda reemplazar y no se haya reemplazado otra antes
							btn11.setText(" "); //"reseteamos" la casilla
							tablero[0][0] = ' '; 
							
							//todavia estoy en mi turno
							
							//activamos el bool fase 2
							haCambiado = true;
							}else if (btn11.getText().equals("O")) {
								infoTurno.setText("Casilla no es tuya, marca otra casilla");
							}else {
								infoTurno.setText("Casilla esta vacia, marca otra casilla");
							}

					}
					
					if(turno > 6 && btn11.getText().equals(" ") && haCambiado) { //FASE 2 PARTE 2 si selecciona un espacio
						
						//pintar la marca en esa casilla
						btn11.setText("X");
						//pintamos tambien el tablero
						tablero[0][0] = 'X'; 
						//pasar turno
						turno();
						
					}else {
						infoTurno.setText("Selecciona un espacio");
					}

				} else {

					
						if (btn11.getText().equals("O") || btn11.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn11.setText("O");
							turno();
							tablero[0][0] = 'O';
						}
					 
					
					if(turno >= 4){
						// fasse2 >5
						if (btn11.getText().equals("O")) {
							btn11.setText("-");
							tablero[0][0] = '-';
							turno--;


						}

					}

				}
			}
		});
		btn12.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jugador) {
					

						if (btn12.getText().equals("O") || btn12.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn12.setText("X");
							turno();
							tablero[0][1] = 'X';
						}
					
					
					if(turno >= 4){
						// fasse2 >5
						if (btn12.getText().equals("X")) {
							btn12.setText("-");
							tablero[0][1] = '-';
							turno--;

						} 

					}
				} else {
					
						if (btn12.getText().equals("O") || btn12.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn12.setText("O");
							turno();
							tablero[0][1] = 'O';
						}
					 
					
					if(turno >= 4){
						// fasse2 >5
						if (btn12.getText().equals("O")) {
							btn12.setText("-");
							tablero[0][1] = '-';
							turno--;

						} 

					}
				}
			}
		});
		btn13.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jugador) {
					

						if (btn13.getText().equals("O") || btn13.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn13.setText("X");
							turno();
							tablero[0][2] = 'X';
						}
					
					
					if(turno >= 4){
						// fasse2 >5
						if (btn13.getText().equals("X")) {
							btn13.setText("-");
							tablero[0][2] = '-';
							turno--;


						} 

					}
				} else {
					
						if (btn13.getText().equals("O") || btn13.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn13.setText("O");
							turno();
							tablero[0][2] = 'O';
						}
					
					
					if(turno >= 4){
						// fasse2 >5
						if (btn13.getText().equals("O")) {
							btn13.setText("-");
							tablero[0][2] = '-';
							turno--;

						} 

					}
				}
			}
		});
		btn21.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jugador) {
					

						if (btn21.getText().equals("O") || btn21.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn21.setText("X");
							turno();
							tablero[1][0] = 'X';
						}
					
					
					if(turno >= 4){
						// fasse2 >5
						if (btn21.getText().equals("X")) {
							btn21.setText("-");
							tablero[1][0] = '-';
							turno--;

						}

					}
				} else {
					
						if (btn21.getText().equals("O") || btn21.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn21.setText("O");
							turno();
							tablero[1][0] = 'O';
						}
					 
					
					if(turno >= 4){
						// fasse2 >5
						if (btn21.getText().equals("O")) {
							btn21.setText("-");
							tablero[1][0] = '-';
							turno--;

						}

					}
				}
			}
		});
		btn22.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jugador) {
					

						if (btn22.getText().equals("O") || btn22.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn22.setText("X");
							turno();
							tablero[1][1] = 'X';
						}
					
					
					if(turno >= 4){
						// fasse2 >5
						if (btn22.getText().equals("X")) {
							btn22.setText("-");
							tablero[1][1] = '-';
							turno--;


						} 

					}

				} else {

					
						if (btn22.getText().equals("O") || btn22.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn22.setText("O");
							turno();
							tablero[1][1] = 'O';
						}
					
					
					if(turno >= 4){
						// fasse2 >5
						if (btn22.getText().equals("O")) {
							btn22.setText("-");
							tablero[1][1] = '-';
							turno--;

						} 

					}
				}
			}
		});
		btn23.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jugador) {
					

						if (btn23.getText().equals("O") || btn23.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn23.setText("X");
							turno();
							tablero[1][2] = 'X';
						}
					
					
					if(turno >= 4){
						// fasse2 >5
						if (btn23.getText().equals("X")) {
							btn23.setText("-");
							tablero[1][2] = '-';
							turno--;

						} 

					}
				} else {
					
						if (btn23.getText().equals("O") || btn23.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn23.setText("O");
							turno();
							tablero[1][2] = 'O';
						}
					
					
					if(turno >= 4){
						// fasse2 >5
						if (btn23.getText().equals("O")) {
							btn23.setText("-");
							tablero[1][2] = '-';
							turno--;

						}

					}
				}
			}
		});
		btn31.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jugador) {
					

						if (btn31.getText().equals("O") || btn31.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn31.setText("X");
							turno();
							tablero[2][0] = 'X';
						}
					 
					
					if(turno >= 4){
						// fasse2 >5
						if (btn31.getText().equals("X")) {
							btn31.setText("-");
							tablero[2][0] = '-';
							turno--;

						} 

					}
				} else {
					
						if (btn31.getText().equals("O") || btn31.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn31.setText("O");
							turno();
							tablero[2][0] = 'O';
						}
					
					
					if(turno >= 4){
						// fasse2 >5
						if (btn31.getText().equals("O")) {
							btn31.setText("-");
							tablero[2][0] = '-';
							turno--;

						} 
					}
				}
			}
		});
		btn32.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jugador) {
					

						if (btn32.getText().equals("O") || btn32.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn32.setText("X");
							turno();
							tablero[2][1] = 'X';
						}
					
					
					if(turno >= 4){
						// fasse2 >5
						if (btn32.getText().equals("X")) {
							btn32.setText("-");
							tablero[2][1] = '-';
							turno--;

						} 

					}
				} else {
					
						if (btn32.getText().equals("O") || btn32.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn32.setText("O");
							turno();
							tablero[2][1] = 'O';
						}
					
					
					if(turno >= 4){
						// fasse2 >5
						if (btn32.getText().equals("O")) {
							btn32.setText("-");
							tablero[2][1] = '-';
							turno--;

						} 

					}
				}
			}
		});
		btn33.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (jugador) {

				

						if (btn33.getText().equals("O") || btn33.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn33.setText("X");
							turno();
							tablero[2][2] = 'X';
						}
					
					
					if(turno >=4){
						// fasse2 >5
						if (btn33.getText().equals("X")) {
							btn33.setText("-");
							tablero[2][2] = '-';
							turno--;


						} 

					}
				} else {

					
						if (btn33.getText().equals("O") || btn33.getText().equals("X")) {
							infoTurno.setText("Casilla Ocupada, marca otra casilla");
						} else {
							btn33.setText("O");
							turno();
							tablero[2][2] = 'O';
						}
					 
					
					if(turno >= 4){
						// fasse2 >5
						if (btn33.getText().equals("O")) {
							btn33.setText("-");
							tablero[2][2] = '-';
							turno--;


						} 

					}
					

				}
			}
		});

	}
	// Establece el tablero en espacios vacíos
	static void limpiarTablero(char[][] tablero) {
		for (int y = 0; y < 3; y++) {
			for (int x = 0; x < 3; x++) {
				tablero[y][x] = ' ';
			}
		}
	}

	// random quien empieza (random boolean)
	static boolean primerTurno() {

		Random r = new Random();
		return r.nextBoolean();

	}
	
	static void mostrarArray() {
		for (int i = 0; i < tablero.length; i++) {
			for (int j = 0; j < tablero[i].length; j++) {
				System.out.print(tablero[i][j]);
			}
			System.out.println();
		}
	}

	static void turno() {
		
		mostrarArray();
		
		// la primera vez se decide por boolean random, true = primer jugador, false
		// segundo jugador

		
		if (jugador) {
			// implementar todas las funciones que se suceden durante el turno
			// se suma el turno
			turno++;
			// se cambia el valor del jugador para que le toque al siguiente
			jugador = false;
			infoTurno.setText("Es el turno de " + jugador);

		} else {
			// implementar todas las funciones que se suceden durante el turno
			// se suma un turno
			turno++;
			// se cambia el valor del jugador para que le toque al siguiente
			jugador = true;
			infoTurno.setText("Es el turno de " + jugador);
		}
		
		if(turno >=4) {
			infoTurno.setText("Tienes que quitar una casilla tuya");

		}

	}
	
	//una vez colocada se comprueba el tablero <- se gana o se sigue (mirar 9 comprobaciones) [@Borja]
	
	/* Array de 3x3
	 *  [ ] [ ] [ ]
	 *  [ ] [ ] [ ]
	 *  [ ] [ ] [ ]
	 */
	
	public void comprobarVictoria(boolean jugador) {
		// mientras no tengamos ganador, comprobaremos todas la combinaciones ganadoras:
		boolean ganaX = false;
		boolean ganaO = false;
		// ganaX = true;
		// ganaO = true;
		
		// comprobaremos el array con cada una de las combinaciones ganadoras
		
		//X o O
		if(jugador) {
			//turno X
			if(tablero[0][0] == 'X' && tablero[1][0] == 'X' && tablero[2][0] == 'X') {
				
				/*	  0   1   2
				 * 0 [1] [ ] [ ]
				 * 1 [1] [ ] [ ]
				 * 2 [1] [ ] [ ]
				 */
				ganaX = true;
				
			}
			if(tablero[0][1] == 'X' && tablero[1][1] == 'X' && tablero[2][1] == 'X') {
				/*	  0   1   2
				 * 0 [ ] [1] [ ]
				 * 1 [ ] [1] [ ]
				 * 2 [ ] [1] [ ]
				 */
				ganaX = true;
			}
			if(tablero[0][2] == 'X' && tablero[1][2] == 'X' && tablero[2][2] == 'X') {
				/*	  0   1   2
				 * 0 [ ] [ ] [1]
				 * 1 [ ] [ ] [1]
				 * 2 [ ] [ ] [1]
				 */
				ganaX = true;
			}
			if(tablero[0][0] == 'X' && tablero[0][1] == 'X' && tablero[0][2] == 'X') {
				/*	  0   1   2
				 * 0 [1] [1] [1]
				 * 1 [ ] [ ] [ ]
				 * 2 [ ] [ ] [ ]
				 */
				ganaX = true;
			}
			if(tablero[0][0] == 'X' && tablero[0][1] == 'X' && tablero[0][2] == 'X') {
				/*	  0   1   2
				 * 0 [1] [1] [1]
				 * 1 [ ] [ ] [ ]
				 * 2 [ ] [ ] [ ]
				 */
				ganaX = true;
			}
			if(tablero[1][0] == 'X' && tablero[1][1] == 'X' && tablero[1][2] == 'X') {
				/*	  0   1   2
				 * 0 [ ] [ ] [ ]
				 * 1 [1] [1] [1]
				 * 2 [ ] [ ] [ ]
				 */
				ganaX = true;
			}
			if(tablero[2][0] == 'X' && tablero[2][1] == 'X' && tablero[2][2] == 'X') {
				/*	  0   1   2
				 * 0 [ ] [ ] [ ]
				 * 1 [ ] [ ] [ ]
				 * 2 [1] [1] [1]
				 */
				ganaX = true;
			}
			
			// diagonal1
			if(tablero[0][0] == 'X' && tablero[1][1] == 'X' && tablero[2][2] == 'X') {
				/*	  0   1   2
				 * 0 [1] [ ] [ ]
				 * 1 [ ] [1] [ ]
				 * 2 [ ] [ ] [1]
				 */
				ganaX = true;
			}
			
			// diagonal2
			if(tablero[0][2] == 'X' && tablero[1][1] == 'X' && tablero[2][0] == 'X') {
				/*	  0   1   2
				 * 0 [ ] [ ] [1]
				 * 1 [ ] [1] [ ]
				 * 2 [1] [ ] [ ]
				 */
				ganaX = true;
			}
			
			if (ganaX == true) infoTurno.setText("El equipo X ha ganado");
			
		}else {
			//turno O
			if(tablero[0][0] == 'O' && tablero[1][0] == 'O' && tablero[2][0] == 'O') {
				/*	  0   1   2
				 * 0 [1] [ ] [ ]
				 * 1 [1] [ ] [ ]
				 * 2 [1] [ ] [ ]
				 */
				ganaO = true;
			}
			if(tablero[0][1] == 'O' && tablero[1][1] == 'O' && tablero[2][1] == 'O') {
				/*	  0   1   2
				 * 0 [ ] [1] [ ]
				 * 1 [ ] [1] [ ]
				 * 2 [ ] [1] [ ]
				 */
				ganaO = true;
			}
			if(tablero[0][2] == 'O' && tablero[1][2] == 'O' && tablero[2][2] == 'O') {
				/*	  0   1   2
				 * 0 [ ] [ ] [1]
				 * 1 [ ] [ ] [1]
				 * 2 [ ] [ ] [1]
				 */
				ganaO = true;
			}
			if(tablero[0][0] == 'O' && tablero[0][1] == 'O' && tablero[0][2] == 'O') {
				/*	  0   1   2
				 * 0 [1] [1] [1]
				 * 1 [ ] [ ] [ ]
				 * 2 [ ] [ ] [ ]
				 */
				ganaO = true;
			}
			if(tablero[0][0] == 'O' && tablero[0][1] == 'O' && tablero[0][2] == 'O') {
				/*	  0   1   2
				 * 0 [1] [1] [1]
				 * 1 [ ] [ ] [ ]
				 * 2 [ ] [ ] [ ]
				 */
				ganaO = true;
			}
			if(tablero[1][0] == 'O' && tablero[1][1] == 'O' && tablero[1][2] == 'O') {
				/*	  0   1   2
				 * 0 [ ] [ ] [ ]
				 * 1 [1] [1] [1]
				 * 2 [ ] [ ] [ ]
				 */
				ganaO = true;
			}
			if(tablero[2][0] == 'O' && tablero[2][1] == 'O' && tablero[2][2] == 'O') {
				/*	  0   1   2
				 * 0 [ ] [ ] [ ]
				 * 1 [ ] [ ] [ ]
				 * 2 [1] [1] [1]
				 */
				ganaO = true;
			}
			// diagonal1
			if(tablero[0][0] == 'O' && tablero[1][1] == 'O' && tablero[2][2] == 'O') {
				/*	  0   1   2
				 * 0 [1] [ ] [ ]
				 * 1 [ ] [1] [ ]
				 * 2 [ ] [ ] [1]
				 */
				ganaO = true;
			}
			// diagonal2
			if(tablero[0][2] == 'O' && tablero[1][1] == 'O' && tablero[2][0] == 'O') {
				/*	  0   1   2
				 * 0 [ ] [ ] [1]
				 * 1 [ ] [1] [ ]
				 * 2 [1] [ ] [ ]
				 */
				ganaO = true;
			}
			
			if (ganaO == true) infoTurno.setText("El equipo O ha ganado");
			
			}	  
		}
	
	//FASE 2  seleccionar una ficha, y moverla <- AL TURNO 6 [@Eloi]
	
	// comprobar ficha si estuya
	//este método comprueba unicamente si la ficha es tuya
	boolean moverFicha(String value) {
		// miramos a que jugador le pertenece
		if (jugador) {
			if (value.equals('X')) {
				return true;
			} else {
				return false;
			}
		} else {
			if (value.equals('O')) {
				return true;
			} else {
				return false;
			}
		}

	}
	
	static void fase1(JToggleButton btn) {
		
		if(turno <= 6 && btn.getText().equals("O") || btn.getText().equals("X")) { //SI ES TURNO 6 O LA CASILLA ESTA OCUPADA
			infoTurno.setText("Casilla Ocupada, marca otra casilla");  //INFORMA AL USUARIO
		} else { // SI NO ESTA OCUPADA y el turno es menor o igual a 6
			btn.setText("X"); //TE METE UNA X
			turno(); //PASA TURNO
			tablero[0][0] = 'X'; //LA METE EN TABLERO 
		}
		
	}
	
}
