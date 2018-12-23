package File_format;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import GIS.MyElement;
import GIS.MyLayer;
import GIS.MyMetaData;
import Geom.Point3D;

/**
 * This class reads from a single csv file and creates a layer
 * @author Alex,Ilya,Nour
 *
 */
public class CSVReader {
	/**
	 * This method reads from a CSV file and creates a layer object that contains an array of elements.
	 * each element is 1 row in the CSV file.
	 * relevant info:
	 * userInfo[1] = name, userInfo[3] = utc, userInfo[6] = lat, userInfo[7] = lon, userInfo[8] = alt, userInfo[10] = type.
	 * source: yael landau moodle
	 * @param path - CSV file path
	 * @return layer object
	 */
	public MyLayer readFromCSV(String path)

	{
		Date date = new Date();
		int counter = 0;
		ArrayList<MyElement> arr = new ArrayList<MyElement>(); //temporary array to contain elements

		String csvFile = path;
		String line = "";
		String cvsSplitBy = ",";

		try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) 
		{
			br.readLine();
			br.readLine();

			while ((line = br.readLine()) != null) 
			{
				++counter; //we count the amount of elements in the layer
				String[] userInfo = line.split(cvsSplitBy);
				double lat = Double.parseDouble(userInfo[6]);
				double lon = Double.parseDouble(userInfo[7]);
				double alt = Double.parseDouble(userInfo[8]);

				String color; //determine the color based on altitude
				if(alt>600)
					color = "green";
				else
					color = "blue";

				//meta-data of element creation
				MyMetaData data = new MyMetaData(userInfo[3],"\n This is a placemark named: "+userInfo[1]+
						".\n lat:"+lat+" lon:"+lon+" alt:"+alt+"\n Type: "+userInfo[10]+"\n Was recorded in: "+userInfo[3]+"\n"
						,userInfo[1],color);
				//create an element and put it in the array
				MyElement element = new MyElement(new Point3D(lat,lon,alt),userInfo[1],color,data);
				arr.add(element);


			}

		} catch (IOException e) 
		{
			e.printStackTrace();
		}
		//meta-data of layer
		MyMetaData data = new MyMetaData(date.toString(),"\n This is a layer of "+counter+ " elements, was created in: "+date.toString(),null,null);
		MyLayer layer = new MyLayer(data);
		layer.addAll(arr); //we add all the elements to the layer from the array
		return layer;
	}

}
