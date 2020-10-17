package dataAccess;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Event;
import domain.Kuota;
import domain.Mugimendu;
import domain.Question;
import exceptions.DirurikEZ;
import exceptions.EmaitzaExist;
import exceptions.ErabiltzaileNoExist;
import exceptions.KuotaAlreadyExist;
import exceptions.QuestionAlreadyExist;
//import exceptions.UserNotExists;
import domain.Admin;
import domain.Apustua;
import domain.Bezero;
import domain.DiruMug;
import domain.Erabiltzaile;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;
	private Vector<Erabiltzaile>recurrent=new Vector<Erabiltzaile>();


	ConfigXML c;

	public DataAccess(boolean initializeMode)  {
		
		c=ConfigXML.getInstance();
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode)
			fileName=fileName+";drop";
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
	}

	public DataAccess()  {	
		 new DataAccess(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			Kuota k1, k2, k3;
			k1=q1.addKuota("Atlético", 1.30);
			k2=q1.addKuota("Enpate", 1.70);
			k3=q1.addKuota("Athletic", 1.80);
			
			db.persist(k1);
			db.persist(k2);
			db.persist(k3);
			
			Erabiltzaile newErab = new Bezero("Mariane","Menath",21,"mariane@mail.com","12345678");
			Erabiltzaile newErab2 = new Bezero("Iker","Ossa",21,"iker@mail.com","12345678");
			Erabiltzaile newErab3 = new Bezero("Xabier","Larrea",20,"larrea@mail.com","12345678");
			Erabiltzaile admin = new Admin("Administratzailea", "BereAbizena",95,"admin@bets.com","Admin1234");

			
			db.persist(admin);
			db.persist(newErab);
			db.persist(newErab2);
			db.persist(newErab3);

			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6);
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	
	public Erabiltzaile isLogin(String posta, String pasahitza) {
		Erabiltzaile us = db.find(Erabiltzaile.class, posta);
		if (us!=null) {
		if(us.getPosta().compareTo(posta)==0 && us.getPasahitza().compareTo(pasahitza)==0)
			return us;
		}
		return null;
	}

	public int storeUser(Erabiltzaile newUser) {
		
		TypedQuery<Bezero> query;
		
		query = db.createQuery("SELECT p from Bezero p WHERE p.posta=?1", Bezero.class);
		query.setParameter(1, newUser.getPosta());
		if(!query.getResultList().isEmpty()) {
				return 1;
		} else {
			System.out.println("User created");
			db.getTransaction().begin();
			db.persist(newUser);
			db.getTransaction().commit();
			return 0;
		}
		
	}
	
	public int storeEvent(String deskripzioa, Date data) {
		
		TypedQuery<Event> query;
		
		query = db.createQuery("SELECT e from Event e WHERE e.description=?1 AND e.eventDate=?2", Event.class);
        query.setParameter(1, deskripzioa);
        query.setParameter(2, data);
		if(!query.getResultList().isEmpty()) {
				return 1;
		}
		else {
			System.out.println("Event created");
			Event gertaera = new Event(deskripzioa, data); 
			db.getTransaction().begin();
			db.persist(gertaera);
			db.getTransaction().commit();
			return 0;
		}
		
	}
	
	public Kuota sortuKuota(Question question, String deskripzioa, double pronostikoa) throws KuotaAlreadyExist{
		System.out.println(">> DataAccess: createKuota=> question= "+question+" dezkripzioa= "+deskripzioa+" pronostikoa="+pronostikoa);
		
		Question q = db.find(Question.class, question.getQuestionNumber());
				
		if (q.DoesKuotaExists(deskripzioa)) throw new KuotaAlreadyExist("ErrorQueryAlreadyExist");
				
			db.getTransaction().begin();
			Kuota k = q.addKuota(deskripzioa, pronostikoa);
			db.getTransaction().commit();
			return k;
			
	}
	
	public Apustua sortuApustua(double zenbatekoa, Vector<Kuota> kuota, Erabiltzaile user, Date data, Date firstEventDate) throws DirurikEZ {
		
		Erabiltzaile us = db.find(Erabiltzaile.class, user.getPosta());
//		for (Question q:qN)
//		if (us.DoesApustuaExists(q)) throw new ApustuaAlreadyExist("ErrorApustuaAlreadyExist");
		
		if(us.getDiruZorroa()<zenbatekoa)throw new DirurikEZ("Ez duzu diru nahikorik");
		
		db.getTransaction().begin();
		Apustua ap = us.addApustu(zenbatekoa, kuota, firstEventDate, data);
		us.addMugimendu(ap);
		zenbatekoa=us.getDiruZorroa()-zenbatekoa;
		us.setDiruZorroa(zenbatekoa);
		this.recurrent.add(us);
		System.out.println("recursive");
		apustuErreplikatu(ap, us);
		System.out.println("irten");
		this.recurrent.removeAllElements();
		db.getTransaction().commit();
		return ap;
			
	}
	
	public void updateQuestion(Integer ID, String result) throws EmaitzaExist {
		
		db.getTransaction().begin();
		Question q = db.find(Question.class, ID);
		if(q.getResult().compareTo(result)==0)throw new EmaitzaExist("Emaitza gordeta dago jada");
		q.setResult(result);
		db.getTransaction().commit();
		System.out.println(ID + " galdera eguneratua izan da.");

	}
	
	public void updateUser(Erabiltzaile user, double dirua, Date data) {
		
		Erabiltzaile us = db.find(Erabiltzaile.class, user.getPosta());
		
		db.getTransaction().begin();
		us.setDiruZorroa(dirua+us.getDiruZorroa());
		DiruMug a = new DiruMug(dirua, data, user);
		us.addMugimendu(a);
		db.getTransaction().commit();
		System.out.println(user.getPosta() + " erabiltzailea eguneratua izan da."+dirua);

	}
	
	public	boolean	removeApustua (Mugimendu mu, Erabiltzaile user){
		boolean	em=true;
		try	{
			db.getTransaction().begin();
			Apustua	c=db.find(Apustua.class, mu);
			Erabiltzaile us = db.find(Erabiltzaile.class, user.getPosta());
			us.setDiruZorroa(c.getDirua()+us.getDiruZorroa());
			us.remApustu(c);
			us.kantzelatuMug(c);
			db.remove(c);
			db.getTransaction().commit();
			System.out.println("Apustua removed	"+ c.getApustuId());//apustu id
			}catch(Exception e){
				e.printStackTrace();
				em=false;
			}

		return	em;
		}	
	
	public Erabiltzaile getUser(Erabiltzaile user) {
		db.getTransaction().begin();
    	Erabiltzaile em = db.find(Erabiltzaile.class, user.getPosta());
    	db.getTransaction().commit();
		return em;
	}
	
	public void erreplikatu(Erabiltzaile user, String posta) throws ErabiltzaileNoExist {
		
		Erabiltzaile us =db.find(Erabiltzaile.class, user.getPosta());
		Erabiltzaile er =db.find(Erabiltzaile.class, posta);
		if(er==null)throw new ErabiltzaileNoExist(ResourceBundle.getBundle("Etiquetas").getString("ErNE"));
		db.getTransaction().begin();
		er.addReplikatu(us);
		db.getTransaction().commit();
				
	}
	public void apustuErreplikatu(Apustua ap, Erabiltzaile user) {
		boolean badago=false;
		Erabiltzaile us = db.find(Erabiltzaile.class, user.getPosta());
		
		for (Erabiltzaile er: us.getReplikatuak()) {
			for(Erabiltzaile era:this.recurrent) {
				if (era.getPosta().compareTo(er.getPosta())==0) {
					badago=true;
				}
			}
			if(!badago && er.getDiruZorroa()>=ap.getDirua()) {
				Erabiltzaile gor = db.find(Erabiltzaile.class, er.getPosta());
				gor.addApustu(ap.getDirua(), ap.getKuota(), ap.getFirstEventDate(), ap.getData());
				gor.setDiruZorroa(gor.getDiruZorroa()-ap.getDirua());
				gor.addMugimendu(ap);
				this.recurrent.add(er);
				apustuErreplikatu(ap, er);
			}
			badago=false;
		}
		
	}
		
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
}
