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
public class BLFacadeImplementation  implements BLFacade {

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
			dbManager.initializeDB();
			dbManager.close();
			}
		
	}
	

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
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
	    DataAccess dBManager=new DataAccess();
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dBManager.createQuestion(event,question,betMinimum);		

		dBManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		DataAccess dbManager=new DataAccess();
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		DataAccess dbManager=new DataAccess();
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
		DataAccess dBManager=new DataAccess();
		dBManager.initializeDB();
		dBManager.close();
	}
    
    @WebMethod
    public Erabiltzaile isLogin(String posta, String pasahitza) {
    	DataAccess dBManager = new DataAccess();
    	Erabiltzaile log = dBManager.isLogin(posta, pasahitza);
		dBManager.close();
		return log;
    }
    
    @WebMethod
    public int storeUser(Erabiltzaile newUser) {
    	DataAccess dBManager = new DataAccess();
    	int erreg = dBManager.storeUser(newUser);
		dBManager.close();
		return erreg;
    }
    
    @WebMethod
    public int storeEvent(String deskripzioa, Date data) {
    	DataAccess dBManager = new DataAccess();
    	int event = dBManager.storeEvent(deskripzioa, data);
		dBManager.close();
		return event;
    }
    
    @WebMethod 
    public Kuota createKuota(Question question, String deskripzioa, double pronostikoa) throws KuotaAlreadyExist{
    	 //The minimum bed must be greater than 0
	    DataAccess dBManager=new DataAccess();
		Kuota k=null;
		
		 k=dBManager.sortuKuota(question, deskripzioa, pronostikoa);		

		dBManager.close();
		
		return k;
    };
    @WebMethod
    public Apustua sortuApustua(double zenbatekoa, Vector<Kuota> kuota, Erabiltzaile user, Date data, Date firstEventDate) throws DirurikEZ{
    	
    	DataAccess dBManager=new DataAccess();
    	Apustua ap=null;
 		
 		 ap=dBManager.sortuApustua(zenbatekoa, kuota, user, data, firstEventDate);

 		dBManager.close();
 		
 		return ap;
    };
    @WebMethod
    public void updateQuestion(Integer ID, String result) throws EmaitzaExist {
    	DataAccess dBManager = new DataAccess();
    	dBManager.updateQuestion(ID, result);;
		dBManager.close();
    }
    @WebMethod
    public void updateUser(Erabiltzaile user, double dirua, Date data) {
    	DataAccess dBManager = new DataAccess();
    	dBManager.updateUser(user, dirua, data);
		dBManager.close();
    }

    @WebMethod
    public	boolean	removeApustua (Mugimendu mu, Erabiltzaile user) {
    	DataAccess dBManager = new DataAccess();
    	boolean em = dBManager.removeApustua(mu, user);
		dBManager.close();
		return em;
    }


	public Erabiltzaile getUser(Erabiltzaile user) {
		DataAccess dBManager = new DataAccess();
    	Erabiltzaile em = dBManager.getUser(user);
		dBManager.close();
		return em;
	}
	
	public void erreplikatu(Erabiltzaile user, String posta) throws ErabiltzaileNoExist {
		DataAccess dBManager = new DataAccess();
    	dBManager.erreplikatu(user, posta);
		dBManager.close();
	}
	
}

