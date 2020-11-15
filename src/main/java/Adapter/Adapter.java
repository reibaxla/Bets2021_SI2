package Adapter;

import java.util.List;

import javax.swing.table.AbstractTableModel;
import domain.Apustua;

public class Adapter extends AbstractTableModel {

	private String[] apostuak= new String[] {"Event","Galdera","Data","€"};
	private List<Apustua> apustuak;
	
	public String getColumnName(int col) {
		return apostuak[col];
	}
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return apustuak.size();
	}

	public int getColumnCount() {
		// TODO Auto-generated method stub
		return apostuak.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		
		switch(columnIndex) {
		case 0: return apustuak.get(rowIndex).getKuota().get(0).getQuestion().getEvent();
		case 1: return apustuak.get(rowIndex).getKuota().get(0).getQuestion();
		case 2: return apustuak.get(rowIndex).getData();
		case 3: return apustuak.get(rowIndex).getDirua();
		}
		
		return null;
	}
}
