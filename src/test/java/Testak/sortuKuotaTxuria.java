package Testak;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Locale;

import javax.persistence.EntityManager;

import org.junit.Test;

import businessLogic.BLFacade;
import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Admin;
import domain.Bezero;
import domain.Erabiltzaile;
import domain.Event;
import domain.Kuota;
import domain.Question;

public class sortuKuotaTxuria {

	static DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
	protected static EntityManager  db;

	@Test
	public void test1() { // IF1 TRUE
		try {
			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);
			Event partida = new Event(1, "Real Sociedad-Athletic", UtilDate.newDate(year, month, 17));
			sut.storeEvent("Real Sociedad-Athletic", UtilDate.newDate(year, month, 17));
			Question q = sut.createQuestion(partida, "Zeinek irabaziko du partidua?", 1);
			sut.sortuKuota(q, "Zeinek irabaziko du partidua?", 1);
			fail();
		} catch (Exception e) { // IF a betetzen bada exzepzioak salto egiten du.
			assertTrue(true); // Partida isistitzen da
		}finally {
			
		}
	}

	@Test
	public void test2() {// IF1 FALSE
		try {
			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);
			Event partida = new Event(1, "Real Sociedad-Athletic", UtilDate.newDate(year, month, 17));
			sut.storeEvent("Real Sociedad-Athletic", UtilDate.newDate(year, month, 17));
			Question q = sut.createQuestion(partida, "Nor izango da jokalari hoberena?", 1);
			Question q1 = new Question(1,  "Nor izango da jokalari hoberena?", 1, partida);
			sut.removeQuestion(q1, partida);
			sut.sortuKuota(q, "Nor izango da jokalari hoberena?", 1);
			fail();
		} catch (Exception e) { // IF a betetzen bada exzepzioak salto egiten du.
			System.out.println(e);
			fail();
		}finally {
		}
	}
}