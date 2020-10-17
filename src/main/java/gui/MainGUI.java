package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import domain.Erabiltzaile;
import domain.Event;
import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Erabiltzaile logeatuta;

	private JPanel jContentPane = null;
	private JButton jButtonCreateQuery = null;
	private JButton jButtonQueryQueries = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton jButtonCreateEvent;
	private JButton btnMugIkus;

	private JButton btnKuotajarri;

	private JButton btnDiruaSartu;

	private JButton btnEmaitza;

	private JButton btnErreplikatu;
	
	/**
	 * This is the default constructor
	 */
	public MainGUI(Erabiltzaile logeatuta) {
		super();
		
		this.logeatuta=logeatuta;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					System.out.println("Error: "+e1.toString()+ResourceBundle.getBundle("Etiquetas").getString("Problems"));
				}
				System.exit(1);
			}
		});

		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(537, 345);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());
			jContentPane.add(getJButtonCreateEvent());
			jContentPane.add(getRdbtnNewRadioButton_1());
			jContentPane.add(getRdbtnNewRadioButton_2());
			jContentPane.add(getRdbtnNewRadioButton());
			
			btnEmaitza = new JButton();
			btnEmaitza.setText(ResourceBundle.getBundle("Etiquetas").getString("Result"));
			btnEmaitza.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new EmaitzakJarriGUI();
					a.setVisible(true);
				}
			});
			btnEmaitza.setBounds(31, 94, 211, 43);
			jContentPane.add(btnEmaitza);
			
			btnKuotajarri = new JButton(ResourceBundle.getBundle("Etiquetas").getString("PutFee"));
			btnKuotajarri.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new KuotakjarriGUI();
					a.setVisible(true);
				}
			});
			btnKuotajarri.setBounds(242, 137, 211, 50);
			jContentPane.add(btnKuotajarri);
			
			btnDiruaSartu = new JButton();
			btnDiruaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("MoneyBnt"));
			btnDiruaSartu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new diruaGUI(logeatuta);
					a.setVisible(true);
				}
			});
			btnDiruaSartu.setBounds(31, 137, 211, 50);
			jContentPane.add(btnDiruaSartu);
			jContentPane.add(getBtnMugIkus());
			
			btnErreplikatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Erreplikatu")); //$NON-NLS-1$ //$NON-NLS-2$
			btnErreplikatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new ErreplikatuGUI(logeatuta);
					a.setVisible(true);
				}
			});
			btnErreplikatu.setBounds(242, 186, 211, 45);
			jContentPane.add(btnErreplikatu);
		}
		return jContentPane;
	}


	/**
	 * This method initializes boton1
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (jButtonCreateQuery == null) {
			jButtonCreateQuery = new JButton();
			jButtonCreateQuery.setBounds(31, 52, 211, 43);
			jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
			jButtonCreateQuery.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CreateQuestionGUI(new Vector<Event>());
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateQuery;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(242, 52, 211, 43);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new ApustuEginGUI(logeatuta);

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(0, 0, 171, 34);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.setBounds(83, 252, 88, 25);
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.setBounds(178, 243, 87, 43);
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.setBounds(282, 250, 105, 34);
			rdbtnNewRadioButton_2.setVerticalAlignment(SwingConstants.TOP);
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		jButtonCreateQuery.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		jButtonCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		btnEmaitza.setText(ResourceBundle.getBundle("Etiquetas").getString("Result"));
		btnDiruaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("MoneyBnt"));
		btnKuotajarri.setText(ResourceBundle.getBundle("Etiquetas").getString("PutFee"));
		btnMugIkus.setText(ResourceBundle.getBundle("Etiquetas").getString("MoveBnt"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		btnErreplikatu.setText(ResourceBundle.getBundle("Etiquetas").getString("Erreplicatu"));
	}
	private JButton getJButtonCreateEvent() {
		if (jButtonCreateEvent == null) {
			jButtonCreateEvent = new JButton();
			jButtonCreateEvent.setBounds(242, 94, 211, 43);
			jButtonCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
			jButtonCreateEvent.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new CreateEventGUI();
					a.setVisible(true);
				}
			});
		}
		return jButtonCreateEvent;
	}
	
	private JButton getBtnMugIkus() {
		if (btnMugIkus == null) {
			btnMugIkus = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnMugIkus.setText(ResourceBundle.getBundle("Etiquetas").getString("MoveBnt"));
			btnMugIkus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new MugimenduakIkusiGUI(logeatuta);
					a.setVisible(true);
				}
			});
			btnMugIkus.setBounds(31, 186, 211, 45);
		}
		return btnMugIkus;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

