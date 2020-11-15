package Iterator;

import java.text.SimpleDateFormat;
import java.util.Date;

import Factory.appFacadeFactory;
import businessLogic.BLFacade;
import configuration.ConfigXML;
import domain.Event;

public class Main {

	static ConfigXML c = ConfigXML.getInstance();
	static BLFacade facadeInterface = appFacadeFactory.createAppFacade(c);

	public static void main(String[] args) {
		boolean isLocal = true;

		try {
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date data;
			data = sdf.parse("30/05/1999");

			ExtendedIterator<Event> i = (ExtendedIterator<Event>) facadeInterface.getEvents(data);
			Event ev;
			i.goLast();
			while (i.hasPrevious()) {
				ev = i.previous();
				ev.toString();
			}
			// Nahiz eta suposatu hasierara ailegatu garela, eragiketa egiten dugu.
			i.goFirst();
			while (i.hasNext()) {
				ev = (Event) i.next();
				ev.toString();
			}
		} catch (Exception e) {
			System.out.println("Zerbait gaizki joan da");
		}

	}
}
