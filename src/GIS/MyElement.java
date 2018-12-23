package GIS;


import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import Geom.Geom_element;
import Geom.Point3D;
/**
 * This class implements GIS_element that has geom and meta-data
 * @author Alex,Ilya,Nour
 *
 */
public class MyElement implements GIS_element {

	private Point3D geom;
	
	private MyMetaData data;
	
	
	
	public MyElement(Point3D geom,MyMetaData data)
	{
		this.geom = geom;
		this.data = data;
	}
	public MyElement(Point3D geom,String name, String color,MyMetaData data)
	{
		this.geom = geom;
		this.data = data;
	}
	public MyElement(Point3D geom)
	{
		Date date = new Date();
		this.geom = geom;
		this.data = new MyMetaData(date.toString());
	}
	
	@Override
	public Geom_element getGeom() {
		
		return this.geom;
	} 
	

	@Override
	public Meta_data getData() {
		
		return this.data;
	}

	@Override
	public void translate(Point3D vec) {
		
		setGeom(add(this.geom,vec));
		
	}

	/**
	 * computes a new point which is the geom transformed by a 3D vector (in meters)
	 * like add in MyCoords
	 * @param geom
	 * @param vec
	 * @return
	 */
	private Point3D add(Point3D geom, Point3D vec) {
		
		Point3D gpsCartesian = geom.lla2ecef(); //transform gps point from LLA to ECEF(cartesian)
		double x = gpsCartesian.x() + vec.x();
		double y = gpsCartesian.y() + vec.y();
		double z = gpsCartesian.z() + vec.z();
		
		Point3D point = new Point3D(x,y,z);
		return point.ecef2lla(); //transform back to LLA
	}

	
	public void setGeom(Point3D p)
	{
		this.geom = p; 
	}
	public Point3D getPoint()
	{
		return this.geom; 
	}
	public String getTime() {
		String time = "";
		
		LocalDateTime date = LocalDateTime.ofEpochSecond(this.data.getTime(),0,ZoneOffset.UTC);
		time = date.toString();
		
		return time;
	}



}
