package GUI;

import java.util.ArrayList;
import java.util.Iterator;

import com.sun.xml.bind.v2.runtime.RuntimeUtil.ToStringAdapter;

import Geom.Point3D;
/**
 * This class represents the collection of coordinates(fruits) that each packman needs to go through
 *  
 * @author Alex Nour Ilya
 *
 */
public class Path {
	public  ArrayList<Point3D> path;
	/**
	 * Default constructor
	 */
	public Path() {

		this.path = new ArrayList<>();

	}
	/**
	 * 
	 * @return collection of coordinates(fruits)
	 */
	public ArrayList<Point3D> getpacPath(){

		return this.path;

	}
	/**
	 * add coordinates to collection
	 * @param nxtfrt
	 */
	
	public void addfruitPath(Point3D nxtfrt) {
		
		this.path.add(new Point3D(nxtfrt));
	}
	
	public String toString() {
		
		Iterator<Point3D> it = path.iterator();
		String s = "";

		while(it.hasNext()) {
		
			Point3D p = it.next();
			s +="\nx = "+p.x()+"   y = "+p.y();
		}
		return s;
	}



}
