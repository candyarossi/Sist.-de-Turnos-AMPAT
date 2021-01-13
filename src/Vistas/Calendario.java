package Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import org.json.JSONArray;

import com.toedter.calendar.JCalendar;

import Json.ArchivosJson;
import Objetos.DayOfWeekEvaluator;
import Objetos.Dia;
import Objetos.Horario;
import Objetos.Sistema;
import Objetos.Tipo;
import Objetos.Turno;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JDayChooser;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JButton;


public class Calendario extends JFrame implements ActionListener {

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
			}
		});
		mostrar.setBounds(361, 407, 149, 42);
		contentPane.add(mostrar);
		
		JButton volver = new JButton("Volver");
		volver.setFont(new Font("Tahoma", Font.PLAIN, 15));
		volver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Inicio inicio = new Inicio();
				inicio.setVisible(true);
			}
		});
		volver.setBounds(103, 407, 149, 42);
		contentPane.add(volver);
		
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	
}
