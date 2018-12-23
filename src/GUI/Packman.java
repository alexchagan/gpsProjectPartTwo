package GUI;

import java.util.ArrayList;
import java.util.Date;

import GIS.MyElement;
import GIS.MyMetaData;
import Geom.Point3D;
/**
 * This class represents a packman in the game with id, movement speed, radius of eating,
 * score(the sum of weights of all fruits it ate), path(collection of fruits that it needs to eat)
 * time(the time it took for it to finish it's path).
 * @author Alex Nour Ilya
 *
 */
public class Packman {

	private MyElement packman;
	private int id;
	private int speed = 1;
	private int radius;
	double Orientation;
	private int score = 0;
	private Path path ;
	private double time = 0;


	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}
	/**
	 * Constructor
	 * @param place
	 * @param id
	 * @param speed
	 * @param radius
	 */

	public Packman(Point3D place, int id, int speed, int radius)
	{
		time = 0;
		this.packman = new MyElement(place);
		this.id = id;
		this.speed = speed;
		this.radius = radius;
		this.path = new Path();

	}
	/**
	 * Copy constructor
	 * @param pac
	 */
	public Packman(Packman pac)
	{	
		this.time = pac.getTime();
		this.packman = new MyElement(pac.getPlace());
		this.id = pac.id;
		this.speed = pac.speed;
		this.radius = pac.radius;
		this.path = pac.getPath();
	}
	/**
	 * Constructor by csv
	 * @param csv
	 */

	public Packman(String csv) {
		time = 0;
		String s[] = csv.split(",");
		this.id = Integer.parseInt(s[1]);
		this.packman = new MyElement(new Point3D(Double.parseDouble(s[3]),Double.parseDouble(s[2]),Double.parseDouble(s[4])));
		//pac = new MyElement(this.point);
		this.speed = Integer.parseInt(s[5]);
		this.radius = Integer.parseInt(s[6]);
		this.path = new Path();

	}
	public Packman(Point3D p) {

		time = 0;
		this.packman = new MyElement(p);
		this.path = new Path();

	}

	/**
	 * @return coordinates of packman
	 */

	public Point3D getPlace() {
		return (Point3D) this.packman.getGeom();
	}
	/**
	 * set new coordinates to packman
	 * @param point
	 */
	public void setPlace(Point3D point) {
		this.packman.setGeom(point);
	}
	/**
	 * get meta data of packman
	 * @return
	 */

	public MyMetaData getData() {
		return (MyMetaData) this.packman.getData();
	}
	/**
	 * @return id of packman
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return speed of packman
	 */
	public int getSpeed() {
		return speed;
	}

	/**
	 * @return radius of packman
	 */

	public int getRadius() {
		return radius;
	}
	/**
	 * @return orientation of packman
	 */

	public double getOrientation() {
		return this.Orientation;
	}
	/**
	 * 
	 * @return score of packman
	 */

	public int getScore() {
		return score;
	}
	/**
	 * 
	 * @return path of packman
	 */
	public Path getPath() {
		return this.path;
	}
	/**
	 * set new path to the packman
	 * @param path
	 */
	
	public void setPath(Path path) {
		this.path = path;
	}
	/**
	 * set new orientation to the packman
	 * @param orientation
	 */
	public void setOrientation(double orientation) {
		Orientation = orientation;
	}
	/**
	 * set new score to the packman
	 * @param score
	 */
	public void setScore(int score) {
		this.score = score;
	}
	/**
	 * 
	 * @return time of eating all fruits in path
	 */

	public void setRadius(int radius2) {
		this.radius = radius2;		
	}
	/**
	 * set new time of eating all fruits in path
	 * @param time
	 */
	public void setSpeed(int speed2) {
		this.speed = speed2;		
	}






}
