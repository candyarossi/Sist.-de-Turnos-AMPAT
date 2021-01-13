package Vistas;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;

import Json.ArchivosJson;
import Objetos.Sistema;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.SwingConstants;
import javax.swing.JButton;


public class Inicio extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Inicio frame = new Inicio();
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
	public Inicio() {
		setTitle("Sistema de Turnos SU TAXI - AMPAT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JSONArray contenido = ArchivosJson.leerTurnos();
		Sistema sistema = new Sistema();
		sistema.fromJson(contenido);
		sistema.eliminarMesesAnteriores();
		JSONArray json = sistema.toJson();
		ArchivosJson.grabarTurnos(json); 
		
		JLabel titulo = new JLabel("Sistema de Turnos SU TAXI - AMPAT");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titulo.setBounds(10, 100, 594, 83);
		contentPane.add(titulo);
		
		JButton ingresar = new JButton("Ingresar");
		ingresar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calendario calendar = new Calendario();
				calendar.setVisible(true);
			}
		});
		ingresar.setBounds(233, 239, 149, 42);
		contentPane.add(ingresar);
		
		JButton salir = new JButton("Salir");
		salir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		salir.setBounds(233, 311, 149, 42);
		contentPane.add(salir);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
