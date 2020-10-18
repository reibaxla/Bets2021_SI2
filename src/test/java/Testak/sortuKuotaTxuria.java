package Testak;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;

public class sortuKuotaTxuria {

	static DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));

	@Test
	public void test1() { // IF1 TRUE
		try {

			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);

			Event partida = new Event(1, "Real Sociedad-Athletic", UtilDate.newDate(year, month, 17)); // Partida sortu.
			Question q1 = partida.addQuestion("Nork irabaziko du partida?", 1);
			q1.addKuota("deskripzioa", 1);
			sut.sortuKuota(q1, "deskripzioa", 1);
			fail();

		} catch (Exception e) { // IF a betetzen bada exzepzioak salto egiten du.
			assertTrue(true);
		}
	}

	@Test
	public void test2() {// IF1 FALSE
		try {

			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);

			Event partida = new Event(1, "Real Sociedad-Athletic", UtilDate.newDate(year, month, 17)); // Partida sortu.
			Question q1 = partida.addQuestion("Nork irabaziko du partida?", 1);
			q1.addKuota("deskripzioa", 1);
			sut.sortuKuota(q1, "bestea", 2);
			assertTrue(true);
		} catch (Exception e) { // IF a betetzen ez bada exzepzioak salto egiten du.
			fail();
		}
	}
}