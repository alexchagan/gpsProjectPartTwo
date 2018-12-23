package GIS;

import Geom.Point3D;
/**
 * This interface contains all the relevant data about a specific object
 * @author Alex,Ilya,Nour
 *
 */
public interface Meta_data {
	/** returns the Universal Time Clock associated with this data; */
	public long getUTC();
	/** return a String representing this data */
	public String toString();
	/**
	 * @return the orientation: yaw, pitch and roll associated with this data;
	 */
	public Point3D get_Orientation();
	
	public String getName();
	
	public String getColor();
}
