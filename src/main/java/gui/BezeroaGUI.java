package gui;

import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;

import businessLogic.BLFacade;
import domain.Erabiltzaile;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class BezeroaGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	private Erabiltzaile logeatuta;

	private JPanel jContentPane = null;
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
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton btnDiruaSartu;

	private JButton btnMugIk;

	private JButton btnErreplikatu;
	
	public BezeroaGUI(Erabiltzaile logeatuta) {
		super();
		
		this.logeatuta=logeatuta;
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
				} catch (Exception e1) {
					System.out.println("Error: "+e1.toString()+ResourceBundle.getBundle("Etiquetas").getString("Problems"));
				}
				System.exit(1);
			}
		});

		initialize();
	}
	

	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 403);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBoton3());
			jContentPane.add(getPanel());
			jContentPane.add(getBtnDiruaSartu());
			
			btnMugIk = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MoveBnt")); //$NON-NLS-1$ //$NON-NLS-2$
			btnMugIk.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new MugimenduakIkusiGUI(logeatuta);
					a.setVisible(true);
				}
			});
			btnMugIk.setBounds(0, 191, 481, 61);
			jContentPane.add(btnMugIk);
			
			btnErreplikatu = new JButton();
			btnErreplikatu.setText(ResourceBundle.getBundle("Etiquetas").getString("Erreplikatu"));
			btnErreplikatu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new ErreplikatuGUI(logeatuta);
					a.setVisible(true);
				}
			});
			btnErreplikatu.setBounds(0, 250, 473, 54);
			jContentPane.add(btnErreplikatu);
		}
		return jContentPane;
	}

	private JButton getBoton3() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(0, 61, 481, 68);
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
			jLabelSelectOption.setBounds(0, 0, 481, 63);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
//			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
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
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(0, 304, 481, 44);
			panel.add(getRdbtnNewRadioButton_1());
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
		}
		return panel;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
		btnMugIk.setText(ResourceBundle.getBundle("Etiquetas").getString("MoveBnt"));
		btnDiruaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("MoneyBnt"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		btnErreplikatu.setText(ResourceBundle.getBundle("Etiquetas").getString("Erreplikatu"));
	}
	
	private JButton getBtnDiruaSartu() {
		if (btnDiruaSartu == null) {
			btnDiruaSartu = new JButton();
			btnDiruaSartu.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new diruaGUI(logeatuta);
					a.setVisible(true);
					
				}
			});
			btnDiruaSartu.setText(ResourceBundle.getBundle("Etiquetas").getString("MoneyBnt")); //$NON-NLS-1$ //$NON-NLS-2$
			btnDiruaSartu.setBounds(0, 128, 481, 63);
		}
		return btnDiruaSartu;
	}
}