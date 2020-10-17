package gui;

import java.text.DateFormat;
import java.util.*;

import javax.swing.*;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.Event;


public class CreateEventGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarMio = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonCreate = new JButton();
	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
	private JTextField ekipo2;
	private JTextField ekipo1;

	public CreateEventGUI() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(604, 370));
		this.setTitle("CreateEvent");

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonCreate.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		jButtonCreate.setBounds(new Rectangle(100, 275, 130, 30));
		jButtonCreate.setEnabled(true);

		jButtonCreate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});
		jButtonClose.setBounds(new Rectangle(275, 275, 130, 30));
		jButtonClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonClose_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);
		this.getContentPane().add(jButtonCreate, null);

		this.getContentPane().add(jCalendar, null);

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 16, 140, 25);
		getContentPane().add(jLabelEventDate);
		
		ekipo1 = new JTextField();
		ekipo1.setText((String) null);
		ekipo1.setColumns(10);
		ekipo1.setBounds(350, 80, 140, 20);
		getContentPane().add(ekipo1);
		
		ekipo2 = new JTextField();
		ekipo2.setText((String) null);
		ekipo2.setColumns(10);
		ekipo2.setBounds(350, 163, 140, 20);
		getContentPane().add(ekipo2);
		
		JLabel lblVs = new JLabel("Vs");
		lblVs.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		lblVs.setHorizontalAlignment(SwingConstants.CENTER);
		lblVs.setBounds(350, 116, 140, 20);
		getContentPane().add(lblVs);
		

		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//					public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					jCalendar.setCalendar(calendarMio);
					Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
				}
			}				
		});
	}

	
	private void jButtonCreate_actionPerformed(ActionEvent e) {
		/*private Integer eventNumber;
		private String description;
		private Date eventDate;
		private Vector<Question> questions=new Vector<Question>();*/
		
		String deskripzioa = ekipo1.getText() + "-" + ekipo2.getText();
		
		Date data = calendarMio.getTime();
		
		data.setHours(0);
	    data.setMinutes(0);
	    data.setSeconds(0);
	    data.setTime(data.getTime() - data.getTime() % 1000);

		
		BLFacade eventStore = MainGUI.getBusinessLogic();
		
		eventStore.storeEvent(deskripzioa, data);
	}

	private void jButtonClose_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}