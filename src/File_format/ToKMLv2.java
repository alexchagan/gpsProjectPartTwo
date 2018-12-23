package File_format;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import GIS.MyElement;


public class ToKMLv2 {
	/**
	 * creates kml file with name path. this layer present the points , Transparent green wall with yellow outlines
	 * @param layer GISlayer of elements 
	 * @param path path of the file to creates end's by his name 
	 * "D:\\\\eclipse-workspace\\\\matala2\\\\CSVFiles\\\\testresult"
	 */
	public static void layout2(ArrayList<MyElement> toKml,String path) {
		String header = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n" + 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\">\r\n" + 
				"  <Document>\r\n" + 
				"    <name>Paths</name>\r\n" + 
				"    <description>Examples of paths. Note that the tessellate tag is by default\r\n" + 
				"      set to 0. If you want to create tessellated lines, they must be authored\r\n" + 
				"      (or edited) directly in KML.</description>\r\n" + 
				"    <Style id=\"yellowLineGreenPoly\">\r\n" + 
				"      <LineStyle>\r\n" + 
				"        <color>7f00ffff</color>\r\n" + 
				"        <width>4</width>\r\n" + 
				"      </LineStyle>\r\n" + 
				"      <PolyStyle>\r\n" + 
				"        <color>7f00ff00</color>\r\n" + 
				"      </PolyStyle>\r\n" + 
				"    </Style>\r\n" + 
				"    <Placemark>\r\n" + 
				"      <name>Absolute Extruded</name>\r\n" + 
				"      <description>Transparent green wall with yellow outlines</description>\r\n" + 
				"      <styleUrl>#yellowLineGreenPoly</styleUrl>\r\n" + 
				"      <LineString>\r\n" + 
				"        <extrude>1</extrude>\r\n" + 
				"        <tessellate>1</tessellate>\r\n" + 
				"        <altitudeMode>absolute</altitudeMode>\r\n" + 
				"        <coordinates>";
		String tail = "        </coordinates>\r\n" + 
				"      </LineString>\r\n" + 
				"    </Placemark>\r\n" + 
				"  </Document>\r\n" + 
				"</kml>\r\n";
		String result = "" + header;
		Iterator<MyElement> iter = toKml.iterator();
		while (iter.hasNext()) {
			MyElement elem = iter.next();

			result += elem.getPoint().x()+","+elem.getPoint().y()+","+elem.getPoint().z()+"\r\n";
		} 
		result +=tail;
		writeToKMLFile(result,path);

	}


	/**
	 * creates kml file with name path. this layer present the points as pointmark with colors red 1 or green 2
	 * @param layer GISlayer of elements 
	 * @param path path of the file to creates end's by his name 
	 * "D:\\\\eclipse-workspace\\\\matala2\\\\CSVFiles\\\\testresult"
	 */
	public static void layout1(ArrayList<MyElement> toKml,String path) {
		String header = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\r\n" + 
				"<kml xmlns=\"http://www.opengis.net/kml/2.2\" xmlns:gx=\"http://www.google.com/kml/ext/2.2\" xmlns:atom=\"http://www.w3.org/2005/Atom\" xmlns:xal=\"urn:oasis:names:tc:ciq:xsdschema:xAL:2.0\">\r\n" + 
				"    <Document>\r\n" + 
				"        <Style id=\"style_red\">\r\n" + 
				"            <IconStyle>\r\n" + 
				"                <scale>1.0</scale>\r\n" + 
				"                <heading>0.0</heading>\r\n" + 
				"                <Icon>\r\n" + 
				"                    <href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>\r\n" + 
				"                    <refreshInterval>0.0</refreshInterval>\r\n" + 
				"                    <viewRefreshTime>0.0</viewRefreshTime>\r\n" + 
				"                    <viewBoundScale>0.0</viewBoundScale>\r\n" + 
				"                </Icon>\r\n" + 
				"            </IconStyle>\r\n" + 
				"            <LabelStyle>\r\n" + 
				"                <color>ff43b3ff</color>\r\n" + 
				"                <scale>0.5</scale>\r\n" + 
				"            </LabelStyle>\r\n" + 
				"        </Style>\r\n" + 
				"        <Style id=\"style_green\">\r\n" + 
				"            <IconStyle>\r\n" + 
				"                <scale>1.0</scale>\r\n" + 
				"                <heading>0.0</heading>\r\n" + 
				"                <Icon>\r\n" + 
				"                    <href>http://maps.google.com/mapfiles/kml/paddle/grn-blank.png</href>\r\n" + 
				"                    <refreshInterval>0.0</refreshInterval>\r\n" + 
				"                    <viewRefreshTime>0.0</viewRefreshTime>\r\n" + 
				"                    <viewBoundScale>0.0</viewBoundScale>\r\n" + 
				"                </Icon>\r\n" + 
				"            </IconStyle>\r\n" + 
				"            <LabelStyle>\r\n" + 
				"                <color>37FF33</color>\r\n" + 
				"                <scale>0.5</scale>\r\n" + 
				"            </LabelStyle>\r\n" + 
				"        </Style>\r\n";  
		String end = "    </Document>\r\n" + 
				"</kml>\r\n" + 
				"";
		String Green ="green";
		String Red ="red";
		String color;

		String result = "" + header;

		Iterator<MyElement> iter = toKml.iterator();
		while (iter.hasNext()) {
			MyElement elem = iter.next();
			if(elem.getData().getColor().equalsIgnoreCase("red")) {
				color = Red;
			}
			else {
				color = Green;
			}
			result += "        <Placemark>\r\n" + 
					"            <name> </name>\r\n" + 
					"            <open>1</open>\r\n" + 
					"            <description></description>\r\n" + 
					"            <TimeStamp>\r\n" + 
					"                <when>"+elem.getTime()+"</when>\r\n" + 
					"            </TimeStamp>\r\n" + 
					"            <styleUrl>#style_"+color+"</styleUrl>\r\n" + 
					"            <Point>\r\n" + 
					"                <coordinates>"+elem.getPoint().x()+","+elem.getPoint().y()+","+elem.getPoint().z()+"</coordinates>\r\n" + 
					"            </Point>\r\n" + 
					"        </Placemark>\r\n"; 
		} 
		result +=end;
		writeToKMLFile(result,path);
	}
	/**
	 * Writes String Data to a file 
	 * @param data String data to write into file
	 * @param path path of the file to creates end's by his name 
	 * "D:\\\\eclipse-workspace\\\\matala2\\\\CSVFiles\\\\testresult"
	 * and writes a file name.kml
	 */
	public static void writeToKMLFile(String data,String path) {
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter( new FileWriter(path+".kml"));
			writer.write(data);
		}
		catch ( IOException e)
		{
			System.out.println("nayobka");
		}
		finally
		{
			try
			{
				if ( writer != null)
					writer.close( );
			}
			catch ( IOException e)
			{

			}
		}
	}

}



