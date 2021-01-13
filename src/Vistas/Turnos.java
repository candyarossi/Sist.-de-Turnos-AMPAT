package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;

import Json.ArchivosJson;
import Objetos.Sistema;
import Objetos.Tipo;
import Objetos.Turno;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class Turnos extends JFrame implements ActionListener {

	private JPanel contentPane;
	private final JButton aceptar = new JButton("New button");
	private JTextField nombre;
	private JTable table;

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
		setTitle("Sistema de Turnos SU TAXI - AMPAT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titulo = new JLabel("Sistema de Turnos SU TAXI - AMPAT");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		titulo.setBounds(10, 25, 594, 83);
		contentPane.add(titulo);
		
		JLabel titulo2 = new JLabel("TURNOS DEL DIA " + fecha.getDayOfMonth() + "/" + fecha.getMonthValue());
		titulo2.setFont(new Font("Tahoma", Font.BOLD, 15));
		titulo2.setBounds(103, 99, 241, 35);
		contentPane.add(titulo2);
		
		JSONArray contenido = ArchivosJson.leerTurnos();
		Sistema sistema = new Sistema();
		sistema.fromJson(contenido);
		
		String estado = sistema.diaCompleto(fecha);
		
		if(estado.compareTo("medio") == 0 || estado.compareTo("lleno") == 0) {
			StringBuilder turnosDelDia = sistema.getTurnosDelDia(fecha);
			JTextArea turnosDelDiaText = new JTextArea(String.valueOf(turnosDelDia));
			turnosDelDiaText.setFont(new Font("Tahoma", Font.PLAIN, 14));
			turnosDelDiaText.setBackground(UIManager.getColor("InternalFrame.borderColor"));
			turnosDelDiaText.setBounds(103, 139, 430, 179);
			turnosDelDiaText.setEditable(false);
			contentPane.add(turnosDelDiaText);
			
		}
		
		if(estado.compareTo("medio") == 0 || estado.compareTo("vacio") == 0) {
			LocalTime proximo = sistema.proximoTurnoDelDia(fecha);
			JLabel proximoTurno = new JLabel("Próximo turno: " + proximo.toString());
			proximoTurno.setFont(new Font("Tahoma", Font.BOLD, 14));
			proximoTurno.setBounds(103, 329, 177, 23);
			contentPane.add(proximoTurno);
			
			JLabel nombreI = new JLabel("A nombre de: ");
			nombreI.setFont(new Font("Tahoma", Font.PLAIN, 14));
			nombreI.setBounds(103, 363, 120, 23);
			contentPane.add(nombreI);
			
			ArrayList<Tipo> tiposDeTurno = ArchivosJson.leerTipos();
			
			JComboBox tipos = new JComboBox();
			tipos.setFont(new Font("Tahoma", Font.PLAIN, 15));
			
			for(Tipo aux : tiposDeTurno) {
				tipos.addItem(aux.getTipo());
			}
			
			tipos.setBounds(302, 329, 208, 22);
			contentPane.add(tipos);
			
			nombre = new JTextField();
			nombre.setFont(new Font("Tahoma", Font.PLAIN, 15));
			nombre.setBounds(206, 363, 304, 23);
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
				}
			});
			aceptar.setBounds(361, 407, 149, 42);
			contentPane.add(aceptar);
			
			JButton volver = new JButton("Volver");
			volver.setFont(new Font("Tahoma", Font.PLAIN, 15));
			volver.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Calendario calendar = new Calendario();
					calendar.setVisible(true);			}
			});
			volver.setBounds(103, 407, 149, 42);
			contentPane.add(volver);
			
			
		} else {
			
			JLabel sinTurnos = new JLabel("No hay más turnos disponibles.");
			sinTurnos.setFont(new Font("Tahoma", Font.BOLD, 15));
			sinTurnos.setBounds(103, 331, 259, 23);
			contentPane.add(sinTurnos);
			
			JButton volver2 = new JButton("Volver");
			volver2.setFont(new Font("Tahoma", Font.PLAIN, 15));
			volver2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Calendario calendar = new Calendario();
					calendar.setVisible(true);			}
			});
			volver2.setBounds(230, 407, 149, 42);
			contentPane.add(volver2);
			
	}
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
