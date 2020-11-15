package Iterator;

import java.util.Iterator;

public interface ExtendedIterator<Event> extends Iterator<Object> {

	//uneko elementua itzultzen du eta aurrekora pasatzen da
	public Event previous();
	
	//true aurreko elementua existitzen bada.
	public boolean hasPrevious();
	
	//Lehendabiziko elementuan kokatzen da.
	public void goFirst();
	
	//Azkeneko elementuan kokatzen da.
	public void goLast();
	
}