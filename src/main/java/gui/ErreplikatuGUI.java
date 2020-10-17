package gui;

import javax.swing.JFrame;

import domain.Erabiltzaile;
import exceptions.ErabiltzaileNoExist;

import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

import businessLogic.BLFacade;

import javax.swing.JTextField;
import javax.swing.JLabel;

public class ErreplikatuGUI extends JFrame{

	private static final long serialVersionUID = 1L;
	private JTextField txtPosta;


	public ErreplikatuGUI(Erabiltzaile user) {
		initialize(user);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Erabiltzaile user) {
		this.setBounds(100, 100, 462, 261);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		
		JButton btnClose = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		btnClose.setBounds(263, 135, 128, 33);
		this.getContentPane().add(btnClose);
		btnClose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				btnClose(arg0);
			}
		});
		this.getContentPane().add(btnClose, BorderLayout.SOUTH);
		
		JButton btnErreplikatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Erreplikatu"));
		btnErreplikatu.setBounds(48, 132, 123, 38);
		this.getContentPane().add(btnErreplikatu);
		
		txtPosta = new JTextField();
		txtPosta.setBounds(138, 45, 271, 26);
		this.getContentPane().add(txtPosta);
		txtPosta.setColumns(10);
		
		JLabel lblpost = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PostaW"));
		lblpost.setBounds(15, 48, 96, 20);
		this.getContentPane().add(lblpost);
		
		final JLabel lblError = new JLabel("");
		lblError.setBounds(48, 96, 343, 20);
		this.getContentPane().add(lblError);
		
		btnErreplikatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String posta= txtPosta.getText();
				if(!posta.contains("@mail.com")||!posta.contains("@bets.com")) lblError.setText("Ez da posta");
				
				try {
					BLFacade a = MainGUI.getBusinessLogic();
					a.erreplikatu(user, posta);
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("Erreplikatuda"));
				} catch (ErabiltzaileNoExist e1) {
					lblError.setText(e1.getMessage());
				}
				
			}
		});
		
		
	}
	
	private void btnClose(ActionEvent e) {
		this.setVisible(false);
	}
}
