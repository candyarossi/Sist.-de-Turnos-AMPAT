package Vistas;

import java.awt.EventQueue;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import com.toedter.calendar.JCalendar;
import Objetos.DayOfWeekEvaluator;
import Objetos.Sistema;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;


public class Calendario extends JFrame implements ActionListener {

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
					Calendario frame = new Calendario();
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
	public Calendario() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("media\\logo.png"));
		setTitle("Sistema de Turnos SU TAXI - AMPAT");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 630, 520);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel titulo = new JLabel("Sistema de Turnos");
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setFont(new Font("Tahoma", Font.PLAIN, 25));
		titulo.setBounds(10, 25, 594, 83);
		contentPane.add(titulo);
		
		JCalendar calendar = new JCalendar();
		calendar.setBounds(101, 123, 409, 238);
		calendar.setWeekOfYearVisible(false);
		calendar.setMaxDayCharacters(2);
		calendar.setTodayButtonVisible(true);
		
		List<DayOfWeek> validDaysOfWeek = Arrays.asList(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
		calendar.getDayChooser().addDateEvaluator(new DayOfWeekEvaluator(validDaysOfWeek));
        calendar.setCalendar(Calendar.getInstance());
		
		contentPane.add(calendar);
		
		JButton mostrar = new JButton("Mostrar Turnos");
		mostrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		mostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Date fecha = calendar.getDate();
				LocalDate fechaN = Sistema.convertToLocalDateViaInstant(fecha);
				Turnos turnos = new Turnos(fechaN);
				turnos.setVisible(true);
				dispose();
			}
		});
		mostrar.setBounds(361, 389, 149, 42);
		contentPane.add(mostrar);
		
		JButton volver = new JButton("Volver");
		volver.setFont(new Font("Tahoma", Font.PLAIN, 15));
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
				dispose();
			}
		});
		volver.setBounds(103, 389, 149, 42);
		contentPane.add(volver);
		
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
