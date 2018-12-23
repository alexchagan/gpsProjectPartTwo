package GUI;

import java.util.Date;

import GIS.MyElement;
import GIS.MyMetaData;
import Geom.Point3D;

public class Fruit {
	/**
	 * This class represents a fruit in the game with id, weight, coordinates
	 * and indication if it was eaten or not.
	 * @author Alex Nour Ilya
	 *
	 */
	private int id;
	private int weight;
	private MyElement furit;
	private boolean eaten = false;
	/**
	 * Constructor
	 * @param place
	 * @param id
	 * @param weight
	 */
	public Fruit(Point3D place,int id,int weight)
	{
		this.furit = new MyElement(place);
		this.id = id;
		this.weight = weight;
	}
	/**
	 * Constructor by csv
	 * @param csv
	 */

	public Fruit(String csv) {
	
		String s[] = csv.split(","); 
		this.furit = new MyElement(new Point3D(Double.parseDouble(s[3]),Double.parseDouble(s[2]),Double.parseDouble(s[4])));
		this.id = Integer.parseInt(s[1]);
		this.weight = Integer.parseInt(s[5]);


	}
	/**
	 * Constructor by point
	 * @param point3d
	 */

	public Fruit(Point3D point3d) {
		this.furit = new MyElement(point3d);
	}


/**
 * @return coordinates of fruit
 */
	public Point3D getPlace() {
		return (Point3D) this.furit.getGeom();
	}
	/**
	 * @return creation date of fruit
	 */
	public MyMetaData getData() {
		return (MyMetaData) this.furit.getData();
	}

	/**
	 * @return id of the fruit
	 */
	public int getId() {
		return id;
	}


	/**
	 * @return weight of fruit
	 */
	public int getWeight() {
		return weight;
	}
	/**
	 * sets the fruit with different coordinates
	 * @param p - coordinates
	 */
	
	public void setPlace(Point3D p) {
		this.furit.setGeom(p);
	
	}
	/**
	 * sets fruit to eaten/not eaten
	 * @param true/false
	 */
	
	public void eaten(boolean b) {
		this.eaten = b;
	}
	/**
	 * @return if fruit is eaten or not
	 */
	public boolean eaten() {
		return this.eaten;
	}



}
