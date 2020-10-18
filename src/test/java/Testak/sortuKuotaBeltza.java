package Testak;

import static org.junit.Assert.*;

import java.util.Calendar;

import org.junit.Test;

import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;

public class sortuKuotaBeltza {

	static DataAccess sut = new DataAccess(ConfigXML.getInstance().getDataBaseOpenMode().equals("initialize"));

	@Test
	public void test1() {
		try {

			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);

			Event partida = new Event(1, "Real Sociedad-Athletic", UtilDate.newDate(year, month, 17));
			Question q1 = partida.addQuestion("Nork irabaziko du partida?", 1);
			sut.sortuKuota(q1, "deskripzioa", 1);
			fail();

		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void test2() {
		try {

			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);

			Event partida = new Event(1, "Real Sociedad-Athletic", UtilDate.newDate(year, month, 17));
			Question q1 = partida.addQuestion("Nork irabaziko du partida?", 1);
			sut.sortuKuota(q1, null, 1);
			fail();

		} catch (Exception e) {
			assertTrue(true);
		}
	}

	@Test
	public void test3() {
		try {

			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);

			Event partida = new Event(1, "Real Sociedad-Athletic", UtilDate.newDate(year, month, 17));
			Question q1 = partida.addQuestion("Nork irabaziko du partida?", 1);
			sut.sortuKuota(q1, "deskripzioa", "bat");
			fail();

		} catch (Exception e) {
			assertTrue(true);
		}

	}

	@Test
	public void test4() {
		try {

			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);

			Event partida = new Event(1, "Real Sociedad-Athletic", UtilDate.newDate(year, month, 17)); // Partida sortu
			Question q1 = partida.addQuestion("Nork irabaziko du partida?", 1);
			sut.sortuKuota(q1, "deskripzioa", 1);
			fail();

		} catch (Exception e) { // IF a betetzen bada exzepzioak salto egiten du.
			assertTrue(true);
		}

	}

	@Test
	public void test5() {
		try {

			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);

			Event partida = new Event(1, "Real Sociedad-Athletic", UtilDate.newDate(year, month, 17)); // Partida sortu
			Question q1 = partida.addQuestion("Nork irabaziko du partida?", 1);
			q1.addKuota("deskripzioa", 1);
			sut.sortuKuota(q1, "deskripzioa", 1);
			fail();

		} catch (Exception e) { // IF a betetzen bada exzepzioak salto egiten du.
			assertTrue(true);
		}

	}

	@Test
	public void test6() {
		try {

			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);

			Event partida = new Event(1, "Real Sociedad-Athletic", UtilDate.newDate(year, month, 17)); // Partida sortu
			Question q1 = partida.addQuestion("Nork irabaziko du partida?", 1);
			q1.addKuota("deskripzioa", 1);
			sut.sortuKuota(q1, "deskripzioa", 1);
			fail();

		} catch (Exception e) { // IF a betetzen bada exzepzioak salto egiten du.
			assertTrue(true);
		}

	}

	@Test
	public void test7() {
		try {

			Calendar today = Calendar.getInstance();
			int month = today.get(Calendar.MONTH);
			int year = today.get(Calendar.YEAR);

			Event partida = new Event(1, "Real Sociedad-Athletic", UtilDate.newDate(year, month, 17)); // Partida sortu
			Question q1 = partida.addQuestion("Nork irabaziko du partida?", 1);
			q1.addKuota("deskripzioa", 1);
			sut.sortuKuota(q1, "deskripzioa", 1);
			fail();

		} catch (Exception e) { // IF a betetzen bada exzepzioak salto egiten du.
			assertTrue(true);
		}
	}

}
