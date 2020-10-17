package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;

import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Locale;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.ButtonGroup;

public class index extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					index frame = new index();
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
	public index() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		final JRadioButton erregistratu = new JRadioButton("Erregistratu");
		buttonGroup.add(erregistratu);
		erregistratu.setBounds(115, 54, 199, 23);
		contentPane.add(erregistratu);
		
		final JRadioButton login = new JRadioButton("Login");
		buttonGroup.add(login);
		login.setBounds(115, 91, 128, 23);
		contentPane.add(login);
		
		final JRadioButton rdbtnGonbidatua = new JRadioButton("Gonbidatua");
		buttonGroup.add(rdbtnGonbidatua);
		rdbtnGonbidatua.setBounds(115, 129, 128, 23);
		contentPane.add(rdbtnGonbidatua);
		
		JButton sartuButton = new JButton("Sartu");
		sartuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (erregistratu.isSelected()) {
					erreg(arg0);
				}
				else if (login.isSelected()) {
					login(arg0);
				}
				
				else if (rdbtnGonbidatua.isSelected()) {
					gonbidatu(arg0);			
				}
			}
		});
		sartuButton.setBounds(137, 172, 106, 35);
		contentPane.add(sartuButton);
		
		JLabel lblAukeratuBat = new JLabel("AUKERATU BAT");
		lblAukeratuBat.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAukeratuBat.setBounds(115, 11, 199, 23);
		contentPane.add(lblAukeratuBat);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(96, 227, 199, 23);
		contentPane.add(lblNewLabel);
	}
	
	public void erreg(ActionEvent arg0) {
		ConfigXML c=ConfigXML.getInstance();
		
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		ErregistratuGUI a = new ErregistratuGUI();
		a.setVisible(true);
		this.setVisible(false);
		
		try {
			
			BLFacade appFacadeInterface;
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			if (c.isBusinessLogicLocal()) {
				
			 appFacadeInterface=new BLFacadeImplementation();
				
				
			}
			
			else { //If remote
				
				 String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
				 
				//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
				URL url = new URL(serviceName);

		 
		        //1st argument refers to wsdl document above
				//2nd argument is service name, refer to wsdl document above
//		        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
		        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
		 
		        Service service = Service.create(url, qname);
		 
		         appFacadeInterface = service.getPort(BLFacade.class);
			} 
			/*if (c.getDataBaseOpenMode().equals("initialize")) 
				appFacadeInterface.initializeBD();
				*/
			MainGUI.setBussinessLogic(appFacadeInterface);

		}catch (Exception e) {
			//a.jLabelSelectOption.setText("Error: "+e.toString());
			//a.jLabelSelectOption.setForeground(Color.RED);		
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
		//a.pack();
	}
	public void login(ActionEvent arg0) {

		ConfigXML c=ConfigXML.getInstance();
		
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		LoginGUI a = new LoginGUI();
		a.setVisible(true);
		this.setVisible(false);
		try {
			
			BLFacade appFacadeInterface;
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			if (c.isBusinessLogicLocal()) {
				
			 appFacadeInterface=new BLFacadeImplementation();
				
				
			}
			
			else { //If remote
				
				 String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
				 
				//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
				URL url = new URL(serviceName);

		 
		        //1st argument refers to wsdl document above
				//2nd argument is service name, refer to wsdl document above
//		        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
		        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
		 
		        Service service = Service.create(url, qname);
		 
		         appFacadeInterface = service.getPort(BLFacade.class);
			} 
			/*if (c.getDataBaseOpenMode().equals("initialize")) 
				appFacadeInterface.initializeBD();
				*/
			MainGUI.setBussinessLogic(appFacadeInterface);

		}catch (Exception e) {
			//a.jLabelSelectOption.setText("Error: "+e.toString());
			//a.jLabelSelectOption.setForeground(Color.RED);		
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
		//a.pack();
	}
	public void gonbidatu(ActionEvent arg0) {

		ConfigXML c=ConfigXML.getInstance();
		
		System.out.println(c.getLocale());
		
		Locale.setDefault(new Locale(c.getLocale()));
		
		System.out.println("Locale: "+Locale.getDefault());
		gonbidatuaGUI a = new gonbidatuaGUI();
		a.setVisible(true);
		this.setVisible(false);
		
		try {
			
			BLFacade appFacadeInterface;
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
			
			if (c.isBusinessLogicLocal()) {
				
			 appFacadeInterface=new BLFacadeImplementation();
				
				
			}
			
			else { //If remote
				
				 String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
				 
				//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
				URL url = new URL(serviceName);

		 
		        //1st argument refers to wsdl document above
				//2nd argument is service name, refer to wsdl document above
//		        QName qname = new QName("http://businessLogic/", "FacadeImplementationWSService");
		        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
		 
		        Service service = Service.create(url, qname);
		 
		         appFacadeInterface = service.getPort(BLFacade.class);
			} 
			/*if (c.getDataBaseOpenMode().equals("initialize")) 
				appFacadeInterface.initializeBD();
				*/
			MainGUI.setBussinessLogic(appFacadeInterface);

		}catch (Exception e) {
			a.jLabelSelectOption.setText("Error: "+e.toString());
			//a.jLabelSelectOption.setForeground(Color.RED);		
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
		//a.pack();
	}
}
