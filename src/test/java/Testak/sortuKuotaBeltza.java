package Testak;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import test.businessLogic.TestFacadeImplementation;

public class sortuKuotaBeltza {

	static DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
	static TestFacadeImplementation testBL = new TestFacadeImplementation();

	private Event ev;

	@Test
	public void test1() {//BK egoki guztiak gordetzen dira, hau da, programa zuzen joan da.
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
			
			fail();
		} catch (Exception e) {
			assertTrue(true);
		} finally {
			testBL.removeEvent(ev);
		}
	}
	@Test
	public void test2() {//Kuotaren deskripzioa null.
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

			sut.sortuKuota(q, null, 1.0);
			
			fail();
		} catch (Exception e) {
			assertTrue(true);
		} finally {
			testBL.removeEvent(ev);
		}
	}

	@Test
	public void test3() { //Question null.
		try {

			String queryText = "proba galdera";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ev = testBL.addEvent(queryText, oneDate);

			sut.sortuKuota(null, queryText, 1.0);
			
			fail();
		} catch (Exception e) {
			assertTrue(true);
		} finally {
			testBL.removeEvent(ev);
		}
	}

	@Test
	public void test4() { 
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
	
			fail();
		} catch (Exception e) {
			assertTrue(true);
		} finally {
			testBL.removeEvent(ev);
		}
	}

	@Test
	public void test5() { // Deskripzioa existitzen da.
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

	
}