package Testak;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Event;
import domain.Kuota;
import domain.Question;
import exceptions.KuotaAlreadyExist;

@RunWith(MockitoJUnitRunner.class)
public class FacadeMockSortuKuota {

	@Mock
	DataAccess dataAccess ;
	Question mockedQuestion = Mockito.mock(Question.class);

	@InjectMocks
	BLFacade sut = new BLFacadeImplementation();

	@Test
	public void test1() {// Dena zuzen joan da. Estalitakoak = (1,2,3,4)
		try {

			String questionText = "proba galdera";
			double apustua = 2.0;

			Kuota k =new Kuota(questionText, apustua, mockedQuestion); //Itzuliko duen Kuota.
			
			Mockito.doReturn(k).when(dataAccess).sortuKuota(mockedQuestion, questionText, apustua);

			Kuota q = sut.createKuota(mockedQuestion, questionText, apustua);

			assertEquals(k,q);
			
		} catch (Exception e) {
			System.out.println("HEMEN DAGO ERROREA =" + e);
			fail();
		}
	}
	
	@Test
	public void test2() {// Throws Kuota alredy exists, hau da, kuota existitzen da. estalitakoak =(9)
		try {

			String questionText = "proba galdera";
			Float betMinimun = new Float(2);

			
			Mockito.doThrow(new KuotaAlreadyExist()).when(dataAccess).sortuKuota(Mockito.any(Question.class), Mockito.any(String.class), Mockito.any(double.class));


			sut.createKuota(mockedQuestion, questionText, betMinimun);

			fail();
		} catch (KuotaAlreadyExist e) {
			assertTrue(true);
		}
	}
	
	
	@Test
	public void test3() {// Question null da., estalitakoak = (8)
		try {

			String questionText = "proba galdera";
			Float betMinimun = new Float(2);

			Mockito.when(dataAccess.sortuKuota(null, Mockito.any(String.class), Mockito.any(double.class))).thenThrow(new Exception());

			sut.createKuota(null, questionText, betMinimun);

			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void test4() {// Deskripzioa null da, estalitakoak = (5)
		try {
			Float betMinimun = new Float(2);

			Mockito.when(dataAccess.sortuKuota(null, Mockito.any(String.class), Mockito.any(double.class))).thenThrow(new Exception());

			sut.createKuota(mockedQuestion, null, betMinimun);

			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}

}
