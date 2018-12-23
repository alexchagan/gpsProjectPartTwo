package GIS;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;

import javax.lang.model.element.Element;

/**
 * This class implements GIS_layer and uses an arraylist to contain elements.
 * @author Alex,Ilya,Nour
 *
 */
public class MyLayer  implements GIS_layer {
	
	private ArrayList<GIS_element> elements;
	private MyMetaData data;
	
	
	public MyLayer()
	{
		this.elements = new ArrayList<GIS_element>();
	}
	
	public MyLayer(MyMetaData data)
	{
		this.elements = new ArrayList<GIS_element>();
		this.data = data;
	}

	@Override
	public boolean add(GIS_element e) {
		
		return elements.add(e);
	}

	@Override
	public boolean addAll(Collection<? extends GIS_element> c) {
		
		return elements.addAll(c);
	}

	@Override
	public void clear() {
		elements.clear();
		
	}

	@Override
	public boolean contains(Object o) {
		return elements.contains(o);
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		return elements.containsAll(c);
	}

	@Override
	public boolean isEmpty() {
		return elements.isEmpty();
	}

	@Override
	public Iterator<GIS_element> iterator() {
		return elements.iterator();
	}

	@Override
	public boolean remove(Object o) {
		return elements.remove(o);
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		
		return elements.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		return elements.retainAll(c);
	}

	@Override
	public int size() {
		return elements.size();
	}

	@Override
	public Object[] toArray() {
		return elements.toArray();
	}

	@Override
	public <T> T[] toArray(T[] a) {
		return elements.toArray(a);
	}

	@Override
	public Meta_data get_Meta_data() {
		
		return this.data;
	}

	

	

	
	
	

}
