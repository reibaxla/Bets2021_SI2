package gui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Apustua;
import domain.DiruMug;
import domain.Erabiltzaile;
import domain.Mugimendu;

public class MugimenduakIkusiGUI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableMug= new JTable();
	private JScrollPane scrollPaneMug = new JScrollPane();
	private DefaultTableModel tableModelMug;
	
	private String[] columnNamesMug = new String[] {
			//pensar que tiene que poner en la caja, tipo data, que tipo de mugimendu...
			ResourceBundle.getBundle("Etiquetas").getString("Username"),
			ResourceBundle.getBundle("Etiquetas").getString("Date"), 
			ResourceBundle.getBundle("Etiquetas").getString("Type"),
			ResourceBundle.getBundle("Etiquetas").getString("Quantity"),
			

	};
	

	/**
	 * Create the application.
	 */
	public MugimenduakIkusiGUI(Erabiltzaile user) {
		BLFacade b= MainGUI.getBusinessLogic();
		Erabiltzaile us =b.getUser(user);
		initialize(us);	
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(final Erabiltzaile user) {
		
		getContentPane().setLayout(null);
		this.setSize(new Dimension(608, 441));
		
		JButton CloseButton = new JButton("Close");
		CloseButton.setBounds(109, 332, 106, 37);
		getContentPane().add(CloseButton);
		
		final JLabel JlabelError = new JLabel("");
		JlabelError.setBounds(53, 296, 450, 20);
		getContentPane().add(JlabelError);
		
		JButton btnApustuaEzabatu = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteBet"));
		btnApustuaEzabatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i=tableMug.getSelectedRow();
				domain.Mugimendu ev=(domain.Mugimendu)tableModelMug.getValueAt(i,4);
				if(ev instanceof Apustua) {
//					Apustua a = (Apustua)ev;
//					if(a.getFirstEventDate().compareTo(data)<=0) JlabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("GertIrag"));
//					else {
						BLFacade b = MainGUI.getBusinessLogic();
						b.removeApustua(ev, user);
						JlabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuEzab"));
						
						tableModelMug.setDataVector(null, columnNamesMug);
						tableModelMug.setColumnCount(5); // question obj aukeratu ahal izateko
//						probatu eguneratzen tabla
						Erabiltzaile us =b.getUser(user);
						for (Mugimendu mu:us.getMugimeduak()){
							Vector<Object> row = new Vector<Object>();
							//mu.get y lo que quiera meter
							row.add(us.getPosta());
							row.add(mu.getData());
							if(mu instanceof Apustua) row.add(ResourceBundle.getBundle("Etiquetas").getString("Bets"));
							else if(mu instanceof DiruMug) row.add(ResourceBundle.getBundle("Etiquetas").getString("DiruMug"));
							else row.add(ResourceBundle.getBundle("Etiquetas").getString("Kantz"));
							
							row.add(mu.getDirua());
							row.add(mu);
							row.add(mu);
							tableModelMug.addRow(row);	
						}
						tableMug.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableMug.getColumnModel().getColumn(1).setPreferredWidth(60);
						tableMug.getColumnModel().getColumn(2).setPreferredWidth(70);
						tableMug.getColumnModel().getColumn(3).setPreferredWidth(80);
						
						tableMug.getColumnModel().removeColumn(tableMug.getColumnModel().getColumn(4)); // not shown in JTable
						
//					}
				}
				else {
					JlabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("BetNone"));
				}
			}
		});
		btnApustuaEzabatu.setBounds(351, 332, 165, 37);
		getContentPane().add(btnApustuaEzabatu);
		
		JLabel MugLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("MoveBnt"));
		MugLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		MugLabel.setBounds(33, 28, 196, 20);
		getContentPane().add(MugLabel);
		
		scrollPaneMug.setBounds(new Rectangle(43, 64, 532, 213));
		
		
		CloseButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				jButton2_actionPerformed(e);
			}
		});

		scrollPaneMug.setViewportView(tableMug);
		tableModelMug = new DefaultTableModel(null, columnNamesMug);

		tableMug.setModel(tableModelMug);
		this.getContentPane().add(scrollPaneMug, null);
		
//		
		tableModelMug.setDataVector(null, columnNamesMug);
		tableModelMug.setColumnCount(5); // question obj aukeratu ahal izateko

		for (Mugimendu mu:user.getMugimeduak()){
			Vector<Object> row = new Vector<Object>();
			row.add(user.getPosta());
			row.add(mu.getData());
			if(mu instanceof Apustua) row.add(ResourceBundle.getBundle("Etiquetas").getString("Bets"));
			else if(mu instanceof DiruMug) row.add(ResourceBundle.getBundle("Etiquetas").getString("DiruMug"));
			else row.add(ResourceBundle.getBundle("Etiquetas").getString("Kantz"));
			
			row.add(mu.getDirua());
			row.add(mu);
			tableModelMug.addRow(row);	
		}
		tableMug.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableMug.getColumnModel().getColumn(1).setPreferredWidth(60);
		tableMug.getColumnModel().getColumn(2).setPreferredWidth(70);
		tableMug.getColumnModel().getColumn(3).setPreferredWidth(80);
		
		tableMug.getColumnModel().removeColumn(tableMug.getColumnModel().getColumn(4)); // not shown in JTable
//		
	}
	
	private void jButton2_actionPerformed(ActionEvent e) {
		this.setVisible(false);
	}
}
