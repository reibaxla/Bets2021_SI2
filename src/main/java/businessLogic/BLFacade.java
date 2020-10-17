package businessLogic;

import java.util.Vector;
import java.util.Date;





//import domain.Booking;
import domain.Question;
import domain.Apustua;
import domain.Erabiltzaile;
import domain.Event;
import domain.Kuota;
import domain.Mugimendu;
import exceptions.DirurikEZ;
import exceptions.EmaitzaExist;
import exceptions.ErabiltzaileNoExist;
import exceptions.EventFinished;
import exceptions.KuotaAlreadyExist;
import exceptions.QuestionAlreadyExist;


import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	@WebMethod public Erabiltzaile isLogin(String posta, String pasahitza);
	
	@WebMethod public int storeUser(Erabiltzaile newUser);
	
	@WebMethod public int storeEvent(String deskripzioa, Date data);

	@WebMethod Kuota createKuota(Question question, String deskripzioa, double pronostikoa) throws KuotaAlreadyExist;
	
	@WebMethod Apustua sortuApustua(double zenbatekoa, Vector<Kuota> kuota, Erabiltzaile user, Date data, Date firstEventDate) throws DirurikEZ;
	
	@WebMethod public void updateQuestion(Integer ID, String result) throws EmaitzaExist;
	
	@WebMethod public void updateUser(Erabiltzaile user, double dirua, Date data);
	
	@WebMethod public boolean removeApustua (Mugimendu mu, Erabiltzaile user);
	
	@WebMethod public Erabiltzaile getUser(Erabiltzaile user);
	
	@WebMethod public void erreplikatu(Erabiltzaile user, String posta) throws ErabiltzaileNoExist;
	
}
