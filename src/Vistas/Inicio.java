package Vistas;

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
import javax.swing.ImageIcon;
import java.awt.Toolkit;


public class Inicio extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("media\\logo.png"));
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
		
		JLabel titulo = new JLabel("Sistema de Turnos");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		titulo.setBounds(10, 145, 594, 83);
		contentPane.add(titulo);
		
		JButton ingresar = new JButton("Ingresar");
		ingresar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Calendario calendar = new Calendario();
				calendar.setVisible(true);
				dispose();
			}
		});
		ingresar.setBounds(233, 260, 149, 42);
		contentPane.add(ingresar);
		
		JButton salir = new JButton("Salir");
		salir.setFont(new Font("Tahoma", Font.PLAIN, 15));
		salir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		salir.setBounds(233, 332, 149, 42);
		contentPane.add(salir);
		
		JLabel imagen = new JLabel("");
		imagen.setIcon(new ImageIcon("media\\logo.png"));
		imagen.setBounds(216, 58, 184, 90);
		contentPane.add(imagen);
		
		JLabel creditos = new JLabel("2020 \u00A9 Candela Yarossi");
		creditos.setFont(new Font("Tahoma", Font.PLAIN, 10));
		creditos.setBounds(494, 456, 110, 14);
		contentPane.add(creditos);
		
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
