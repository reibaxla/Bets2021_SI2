package businessLogic;

import java.util.Date;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
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

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation() {
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c = ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager = new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
		}

	}

	/**
	 * This method creates a question for an event, with a question text and the
	 * minimum bet
	 * 
	 * @param event      to which question is added
	 * @param question   text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished        if current data is after data of the event
	 * @throws QuestionAlreadyExist if the same question already exists for the
	 *                              event
	 */

	public BLFacadeImplementation(DataAccess da) {
		System.out.println("Creating BLFacadeImplementation  instance with DataAccess parameter");
		ConfigXML c = ConfigXML.getInstance();
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();
		}
		dbManager = da;
	}

	@WebMethod
	public Question createQuestion(Event event, String question, float betMinimum)
			throws EventFinished, QuestionAlreadyExist {

		// The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry = null;

		if (new Date().compareTo(event.getEventDate()) > 0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));

		qry = dbManager.createQuestion(event, question, betMinimum);

		dbManager.close();

		return qry;
	};

	/**
	 * This method invokes the data access to retrieve the events of a given date
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod
	public Vector<Event> getEvents(Date date) {
		dbManager.open(false);
		Vector<Event> events = dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

	/**
	 * This method invokes the data access to retrieve the dates a month for which
	 * there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved
	 * @return collection of dates
	 */
	@WebMethod
	public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date> dates = dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}

	/**
	 * This method invokes the data access to initialize the database with some
	 * events and questions. It is invoked only when the option "initialize" is
	 * declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	public void initializeBD() {
		dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	@WebMethod
	public Erabiltzaile isLogin(String posta, String pasahitza) {
		dbManager.open(false);
		Erabiltzaile log = dbManager.isLogin(posta, pasahitza);
		dbManager.close();
		return log;
	}

	@WebMethod
	public int storeUser(Erabiltzaile newUser) {
		dbManager.open(false);
		int erreg = dbManager.storeUser(newUser);
		dbManager.close();
		return erreg;
	}

	@WebMethod
	public int storeEvent(String deskripzioa, Date data) {
		dbManager.open(false);
		int event = dbManager.storeEvent(deskripzioa, data);
		dbManager.close();
		return event;
	}

	@WebMethod
	public Kuota createKuota(Question question, String deskripzioa, double pronostikoa) throws KuotaAlreadyExist {
		// The minimum bed must be greater than 0
		dbManager.open(false);
		Kuota k = null;

		k = dbManager.sortuKuota(question, deskripzioa, pronostikoa);

		dbManager.close();

		return k;
	};

	@WebMethod
	public Apustua sortuApustua(double zenbatekoa, Vector<Kuota> kuota, Erabiltzaile user, Date data,
			Date firstEventDate) throws DirurikEZ {

		dbManager.open(false);
		Apustua ap = null;

		ap = dbManager.sortuApustua(zenbatekoa, kuota, user, data, firstEventDate);

		dbManager.close();

		return ap;
	};

	@WebMethod
	public void updateQuestion(Integer ID, String result) throws EmaitzaExist {
		dbManager.open(false);
		dbManager.updateQuestion(ID, result);
		;
		dbManager.close();
	}

	@WebMethod
	public void updateUser(Erabiltzaile user, double dirua, Date data) {
		dbManager.open(false);
		dbManager.updateUser(user, dirua, data);
		dbManager.close();
	}

	@WebMethod
	public boolean removeApustua(Mugimendu mu, Erabiltzaile user) {
		dbManager.open(false);
		boolean em = dbManager.removeApustua(mu, user);
		dbManager.close();
		return em;
	}

	public Erabiltzaile getUser(Erabiltzaile user) {
		dbManager.open(false);
		Erabiltzaile em = dbManager.getUser(user);
		dbManager.close();
		return em;
	}

	public void erreplikatu(Erabiltzaile user, String posta) throws ErabiltzaileNoExist {
		dbManager.open(false);
		dbManager.erreplikatu(user, posta);
		dbManager.close();
	}

}
