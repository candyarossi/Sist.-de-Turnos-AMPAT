package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;

import Json.ArchivosJson;
import Objetos.Sistema;
import Objetos.Turno;

import javax.swing.JLabel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class VerTurno extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VerTurno frame = new VerTurno(LocalDate.parse("01-01-2000"), LocalTime.parse("09:00"), "N/A", "N/A");
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
	public VerTurno(LocalDate fecha, LocalTime proximo, String tipoIng, String nombreIng) {
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
		
		Turno turno = new Turno();
		turno.setNombre(nombreIng);
		turno.setFecha(fecha);
		turno.setDia();
		turno.setTipo(tipoIng);
		turno.setDuracion(tipoIng);
		turno.setHora(proximo);
		
		JLabel turnoFinal = new JLabel("TURNO A AGENDAR:");
		turnoFinal.setFont(new Font("Tahoma", Font.BOLD, 15));
		turnoFinal.setBounds(103, 99, 241, 35);
		contentPane.add(turnoFinal);
		
		JTextArea turnoFinal1 = new JTextArea("Fecha: " + turno.getDia() + " " + turno.getFecha2() +
								"\nHora: " + turno.getHora() + "\nTipo de turno: " + turno.getTipo() + 
								"\nA nombre de: " + turno.getNombre());
		turnoFinal1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		turnoFinal1.setBackground(UIManager.getColor("InternalFrame.borderColor"));
		turnoFinal1.setBounds(103, 139, 430, 179);
		turnoFinal1.setEditable(false);
		contentPane.add(turnoFinal1);
		
		
		JButton volver = new JButton("Volver");
		volver.setFont(new Font("Tahoma", Font.PLAIN, 15));
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Turnos turnosP = new Turnos(turno.getFecha());
				turnosP.setVisible(true);       }
		});
		volver.setBounds(103, 407, 149, 42);
		contentPane.add(volver);
		
		JButton pdf = new JButton("Confirmar e Imprimir");
		pdf.setFont(new Font("Tahoma", Font.PLAIN, 15));
		pdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JSONArray contenido = ArchivosJson.leerTurnos();
				Sistema sistema = new Sistema();
				sistema.fromJson(contenido);
				sistema.agregar(nombreIng, fecha, tipoIng);
				JSONArray json = sistema.toJson();
				ArchivosJson.grabarTurnos(json); 
			
				ArchivosJson.grabarTXT("SISTEMA DE TURNOS - SU TAXI / AMPAT \nFecha: " + turno.getDia() + " " + turno.getFecha2() +
								"\nHora: " + turno.getHora() + "\nTipo de turno: " + turno.getTipo() + 
								"\nA nombre de: " + turno.getNombre());
				ArchivosJson.abrirTXT();
				
				Calendario calendar = new Calendario();
				calendar.setVisible(true);
			}
		});
		pdf.setBounds(328, 407, 182, 42);
		contentPane.add(pdf);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
