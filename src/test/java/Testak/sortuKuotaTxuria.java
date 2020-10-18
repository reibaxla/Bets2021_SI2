package Testak;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;

public class sortuKuotaTxuria {

	static DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
	static TestFacadeImplementation testBL = new TestFacadeImplementation();

	private Event ev;

	@Test
	public void test1() { // IF1 TRUE
		try {

			String queryText = "proba galdera";
			Float betMinimum = new Float(2);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ev = testBL.addEvent(queryText, oneDate);
			Question q = sut.createQuestion(ev, queryText, betMinimum);

			sut.sortuKuota(q, queryText, 1.0); //Bi aldiz jartzen dut Erroreak salto egiteko.
			sut.sortuKuota(q, queryText, 1.0);
			
			fail();
		} catch (Exception e) {
			assertTrue(true);
		} finally {
			testBL.removeEvent(ev);
		}
	}

	

	@Test
	public void test2() {// IF1 FALSE
		try {
			String queryText = "proba galdera";
			Float betMinimum = new Float(2);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ev = testBL.addEvent(queryText, oneDate);
			Question q = sut.createQuestion(ev, queryText, betMinimum);

			sut.sortuKuota(q, queryText, 1.0);
			
			assertTrue(true);
			
		} catch (Exception e) {
			fail(); //Programa ona iristen bada esan nahi du Queryren bat errepikatua dagoela, beraz, gaizki egongo litzateke.
		} finally {
			testBL.removeEvent(ev);
		}
	}
}