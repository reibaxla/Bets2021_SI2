package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.ParseException;
import com.toedter.calendar.JCalendar;

import domain.Kuota;
import domain.Question;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class EmaitzakJarriGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 

	private JButton jButtonClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarMio = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	private JScrollPane scrollPaneKuotak = new JScrollPane();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();
	private final JTable tableKuota = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;
	private DefaultTableModel tableModelKuotak;

	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	
	private String[] columnNamesKuotak = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("Description"), 
			ResourceBundle.getBundle("Etiquetas").getString("Fee")

	};
	private final JButton btnEmaitza = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Result"));

	public EmaitzakJarriGUI()
	{
		try
		{
			jbInit();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	
	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(778, 477));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(40, 222, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);

		jButtonClose.setBounds(new Rectangle(391, 375, 130, 30));

		jButtonClose.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		this.getContentPane().add(jButtonClose, null);


		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));


		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
					calendarMio = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
					jCalendar1.setCalendar(calendarMio);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));



					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarMio.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarMio.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
						btnEmaitza.setVisible(false);
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
				CreateQuestionGUI.paintDaysWithEvents(jCalendar1);
			} 
		});

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 252, 406, 116));
		scrollPaneKuotak.setBounds(new Rectangle(446, 252, 295, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);
				tableModelQueries.setColumnCount(3); // question obj aukeratu ahal izateko

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					row.add(q);
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
				tableQueries.getColumnModel().removeColumn(tableQueries.getColumnModel().getColumn(2)); // not shown in JTable
				btnEmaitza.setVisible(false);
			}
		});

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		tableQueries.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableQueries.getSelectedRow();
				
				domain.Question q=(domain.Question)tableModelQueries.getValueAt(i,2);
				Vector<Kuota> kuotak=q.getKuota();

				tableModelKuotak.setDataVector(null, columnNamesKuotak);

				if (kuotak.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoFee") + ": "+q.getQuestion());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedQuestion") +" "+q.getQuestion());

				for (domain.Kuota k:kuotak){
					Vector<Object> row = new Vector<Object>();

					row.add(k.getDeskripzioa());
					row.add(k.getPronostikoa());
					tableModelKuotak.addRow(row);	
				}
				tableKuota.getColumnModel().getColumn(0).setPreferredWidth(70);
				tableKuota.getColumnModel().getColumn(1).setPreferredWidth(25);
				btnEmaitza.setVisible(false);
			}
		});
		
		tableKuota.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				btnEmaitza.setVisible(true);
			}
		});
		
		scrollPaneKuotak.setViewportView(tableKuota);
		tableModelKuotak = new DefaultTableModel(null, columnNamesKuotak);

		tableKuota.setModel(tableModelKuotak);
		tableKuota.getColumnModel().getColumn(0).setPreferredWidth(70);
		tableKuota.getColumnModel().getColumn(1).setPreferredWidth(25);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		this.getContentPane().add(scrollPaneKuotak, null);
		btnEmaitza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int j=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event) tableModelEvents.getValueAt(j,2);
				if(!isExpire(ev.getEventDate())) {
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvent"));
				}
				else {
					try {
						int i=tableQueries.getSelectedRow();
						domain.Question question = (domain.Question)tableModelQueries.getValueAt(i,2);
						int y= tableKuota.getSelectedRow();
						
						String result=(String)tableModelKuotak.getValueAt(y,0);
						BLFacade b = MainGUI.getBusinessLogic();
						b.updateQuestion(question.getQuestionNumber(), result);
						jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("ResultSave"));
					}catch(Exception e4) {
						jLabelQueries.setText(e4.getMessage());
					}
				}
			}
		});
		btnEmaitza.setBounds(216, 376, 115, 29);
		btnEmaitza.setVisible(false);
		
		getContentPane().add(btnEmaitza);

	}

	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
	
	private boolean isExpire(Date date) {
	    
        SimpleDateFormat sdf =  new SimpleDateFormat("MMM-dd-yyyy hh:mm:ss a"); // Jan-20-2015 1:30:55 PM
           Date d=null;
           Date d1=null;
        String today=   getToday("MMM-dd-yyyy hh:mm:ss a");
        try {
            //System.out.println("expdate>> "+date);
            //System.out.println("today>> "+today+"\n\n");
            d=date;
            try {
				d1 = sdf.parse(today);
			} catch (java.text.ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if(d1.compareTo(d) <0){// not expired
                return false;
            }else if(d.compareTo(d1)==0){// both date are same
                        if(d.getTime() < d1.getTime()){// not expired
                            return false;
                        }else if(d.getTime() == d1.getTime()){//expired
                            return true;
                        }else{//expired
                            return true;
                        }
            }else{//expired
                return true;
            }
        } catch (ParseException e) {
            e.printStackTrace();                    
            return false;
        }

}


	public static String getToday(String format){
		Date date = new Date();
		return new SimpleDateFormat(format).format(date);
	}
}