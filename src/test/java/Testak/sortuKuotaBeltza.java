package Testak;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import test.businessLogic.TestFacadeImplementation;

public class sortuKuotaBeltza {

	static DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));
	static TestFacadeImplementation testBL = new TestFacadeImplementation();

	private Event ev;

	@Test
	public void test1() {//BK egoki guztiak gordetzen dira, hau da, programa zuzen joan da. Estalitakoak = (1,2,3,4)
		try {

			String galdera = "proba galdera";
			Float apostua = new Float(2);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ev = testBL.addEvent(galdera, oneDate);
			Question q = sut.createQuestion(ev, galdera, apostua);

			sut.sortuKuota(q, galdera, 1.0);
			
			assertTrue(true);
		} catch (Exception e) {
			fail();
		} finally {
			testBL.removeEvent(ev);
		}
	}
	/*
	@Test
	public void test2() {//Kuotaren deskripzioa null. Estalitakoa = (5)
		try {

			String galdera = "proba galdera";
			Float apostua = new Float(2);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ev = testBL.addEvent(galdera, oneDate);
			Question q = sut.createQuestion(ev, galdera, apostua);

			sut.sortuKuota(q, null, 1.0);
			
			fail();
		} catch (Exception e) {
			assertTrue(true);
		} finally {
			testBL.removeEvent(ev);
		}
	}
*/
	/*
	@Test
	public void test4() { //Deskripzioa = float, Estalitakoa = (6)
		try {

			String galdera = "proba galdera";
			Float apostua = new Float(2);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ev = testBL.addEvent(galdera, oneDate);
			sut.createQuestion(ev, float, apostua);
	
			fail();
		} catch (Exception e) {
			assertTrue(true);
		} finally {
			testBL.removeEvent(ev);
		}
	}
	*/
	@Test
	public void test3() { //Question null. Estalitakoa = (8)
		try {

			String galdera = "proba galdera";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ev = testBL.addEvent(galdera, oneDate);

			sut.sortuKuota(null, galdera, 1.0);
			
			fail();
		} catch (Exception e) {
			assertTrue(true);
		} finally {
			testBL.removeEvent(ev);
		}
	}



	@Test
	public void test5() { // Kuota existitzen da estalitakoak = (9).
		try {

			String galdera = "proba galdera";
			Float apostua = new Float(2);
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate = null;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				e.printStackTrace();
			}
			ev = testBL.addEvent(galdera, oneDate);
			Question q = sut.createQuestion(ev, galdera, apostua);

			sut.sortuKuota(q, galdera, 1.0); //Bi aldiz jartzen dut Erroreak salto egiteko.
			sut.sortuKuota(q, galdera, 1.0);
			
			fail();
		} catch (Exception e) {
			assertTrue(true);
		} finally {
			testBL.removeEvent(ev);
		}
	}

	
}