package Iterator;

import java.util.Vector;

import domain.Event;

public class EventIterator implements ExtendedIterator<Event> {

	Vector<Event> eventuak;
	int posizioa;
	
	public EventIterator(Vector<Event> eventuak) {
		this.eventuak = eventuak;
		posizioa = eventuak.capacity()-1;
	}


	public Event previous() {
		// TODO Auto-generated method stub
		return eventuak.get(posizioa-1);
	}

	public boolean hasPrevious() {
		// TODO Auto-generated method stub
		return posizioa>0;
	}

	public void goFirst() {
		// TODO Auto-generated method stub
		posizioa = 0;
	}

	public void goLast() {
		// TODO Auto-generated method stub
		posizioa = eventuak.capacity()-1;
	}


	public boolean hasNext() {
		// TODO Auto-generated method stub
		return false;
	}


	public Object next() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
