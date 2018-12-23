package GIS;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import Geom.Point3D;

/**
 * This class implements Meta_data and contains all the possible data.
 * @author Alex,Ilya,Nour
 *
 */
public class MyMetaData implements Meta_data {

	private String UTC;
	private String dataDesc; //data representation
	private String name;
	private String color;

	public MyMetaData(String UTC, String dataDesc, String name, String color)
	{
		this.UTC = UTC;
		this.dataDesc = dataDesc;
		this.name = name;
		this.color = color;
	}

	public MyMetaData(String UTC)
	{
		this.UTC = UTC;
	}
	public MyMetaData(String UTC , String color)
	{
		this.UTC = UTC;
		this.color=color;
	}

	@Override
	/**
	 * Computes the UTC of current meta-data in long representation
	 */
	public long getUTC() {
		DateFormat format;
		
		if(this.name!=null) // if the meta data belongs to an element there is a special format 
		{
		 format = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.ENGLISH);
			try {
				Date date = format.parse(this.UTC);
				long utc = date.getTime();
				return utc;
			} catch (ParseException e) {
				e.printStackTrace();
				return 0;
			}

		} // if not, its the normal Date format
		else  
			format = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH);
		try {
			Date date = format.parse(this.UTC);

			long utc = date.getTime();
			return utc;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public Point3D get_Orientation() {

		return null;
	}

	@Override
	/** return a String representing this data */
	public String toString()
	{
		return this.dataDesc;

	}
	@Override
	public String getName() {
		return name;
	}
	@Override
	public String getColor() {
		return color;
	}

	public long getTime() {
		// TODO Auto-generated method stub
		return Long.parseLong(UTC);
	}






}
