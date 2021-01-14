package Vistas;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.json.JSONArray;
import Json.ArchivosJson;
import Objetos.Sistema;
import Objetos.Tipo;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.UIManager;


public class Turnos extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField nombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Turnos frame = new Turnos(LocalDate.parse("01-01-2000"));
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Turnos(LocalDate fecha) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("media\\logo.png"));
		setTitle("Sistema de Turnos SU TAXI - AMPAT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titulo = new JLabel("Sistema de Turnos");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		titulo.setBounds(10, 25, 694, 83);
		contentPane.add(titulo);
		
		JLabel titulo2 = new JLabel("TURNOS DEL DIA " + fecha.getDayOfMonth() + "/" + fecha.getMonthValue());
		titulo2.setFont(new Font("Tahoma", Font.BOLD, 15));
		titulo2.setBounds(60, 99, 241, 35);
		contentPane.add(titulo2);
		
		JSONArray contenido = ArchivosJson.leerTurnos();
		Sistema sistema = new Sistema();
		sistema.fromJson(contenido);
		
		String estado = sistema.diaCompleto(fecha);
		LocalDate hoy = LocalDate.now();
		
		
		if(estado.compareTo("medio") == 0 || estado.compareTo("lleno") == 0) {
			StringBuilder turnosDelDia = sistema.getTurnosDelDia(fecha);
			JTextArea turnosDelDiaText = new JTextArea(String.valueOf(turnosDelDia));
			turnosDelDiaText.setFont(new Font("Tahoma", Font.PLAIN, 14));
			turnosDelDiaText.setBackground(UIManager.getColor("InternalFrame.borderColor"));
			turnosDelDiaText.setBounds(60, 139, 599, 155);
			turnosDelDiaText.setEditable(false);
			contentPane.add(turnosDelDiaText);
			
		}
		
		
		if(estado.compareTo("medio") == 0 || estado.compareTo("vacio") == 0 && fecha.compareTo(hoy) > 0) {
			LocalTime proximo = sistema.proximoTurnoDelDia(fecha);
			JLabel proximoTurno = new JLabel("Próximo turno: " + proximo.toString());
			proximoTurno.setFont(new Font("Tahoma", Font.BOLD, 14));
			proximoTurno.setBounds(60, 311, 177, 23);
			contentPane.add(proximoTurno);
			
			JLabel nombreI = new JLabel("A nombre de: ");
			nombreI.setFont(new Font("Tahoma", Font.PLAIN, 14));
			nombreI.setBounds(60, 345, 120, 23);
			contentPane.add(nombreI);
			
			ArrayList<Tipo> tiposDeTurno = ArchivosJson.leerTipos();
			
			JComboBox<String> tipos = new JComboBox<String>();
			tipos.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			for(Tipo aux : tiposDeTurno) {
				tipos.addItem(aux.getTipo());
			}
			
			tipos.setBounds(259, 311, 400, 22);
			contentPane.add(tipos);
			
			nombre = new JTextField();
			nombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
			nombre.setBounds(163, 345, 496, 23);
			contentPane.add(nombre);
			nombre.setColumns(10);
			
			JButton aceptar = new JButton("Aceptar");
			aceptar.setFont(new Font("Tahoma", Font.PLAIN, 15));
			aceptar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					String nombreIng = new String(nombre.getText());
					String tipoIng = new String(String.valueOf(tipos.getSelectedItem()));
					
					VerTurno turnoAGuardar = new VerTurno(fecha, proximo, tipoIng, nombreIng);
					turnoAGuardar.setVisible(true);
					dispose();
				}
			});
			aceptar.setBounds(510, 389, 149, 42);
			contentPane.add(aceptar);
			
			JButton volver = new JButton("Volver");
			volver.setFont(new Font("Tahoma", Font.PLAIN, 15));
			volver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Calendario calendar = new Calendario();
					calendar.setVisible(true);	
					dispose();
					}
			});
			volver.setBounds(60, 389, 149, 42);
			contentPane.add(volver);
			
			
		} else {
			JLabel sinTurnos = new JLabel("No hay más turnos disponibles.");
			sinTurnos.setFont(new Font("Tahoma", Font.BOLD, 15));
			sinTurnos.setBounds(60, 313, 259, 23);
			contentPane.add(sinTurnos);
			
			JButton volver2 = new JButton("Volver");
			volver2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			volver2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Calendario calendar = new Calendario();
					calendar.setVisible(true);
					dispose();
					}
			});
			volver2.setBounds(284, 389, 149, 42);
			contentPane.add(volver2);
			
	}
		
		JLabel creditos = new JLabel("2020 \u00A9 Candela Yarossi");
		creditos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		creditos.setBounds(594, 456, 110, 14);
		contentPane.add(creditos);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
