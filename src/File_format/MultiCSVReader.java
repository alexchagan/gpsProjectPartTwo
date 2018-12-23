package File_format;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import GIS.MyLayer;
import GIS.MyMetaData;
import GIS.MyProject;

/**
 * This class handles reading all csv files from a certain path
 * @author Alex,Ilya,Nour
 *
 */
public class MultiCSVReader 
{

	/**
	 * This method reads from all the csv files in the path (including all the folders inside)
	 * and puts each csv file in a layer and each layer in a project.
	 * source: https://stackoverflow.com/questions/28236098/how-to-read-multiple-csv-files-from-a-folder-in-java
	 * @param path
	 * @return
	 */
	public MyProject readFromMultCSV(String path) 
	{
		
		Date date = new Date();
		ArrayList<MyLayer> arr = new ArrayList<MyLayer>();
		CSVReader csv = new CSVReader();


		File folder = new File(path);


		listFilesForFolder(folder,arr,csv);


		MyMetaData data = new MyMetaData(date.toString(),"This is a Project of "+arr.size()+ " layers, was created in: "+date.toString(),null,null);
		MyProject project = new MyProject(data);
		project.addAll(arr);
		return project;

	}
/**
 * This method goes over all the documents in a folder and reads the csv files
 * source: https://stackoverflow.com/questions/28236098/how-to-read-multiple-csv-files-from-a-folder-in-java
 * @param folder
 * @param arr
 * @param csv
 */
	private void listFilesForFolder(final File folder,ArrayList<MyLayer> arr,CSVReader csv) {

		ArrayList<MyLayer> arr2 = arr;
		CSVReader csv2 = csv;
		for (final File fileEntry : folder.listFiles()) //go over all the files in our folder
		{
			if (fileEntry.isDirectory()) //if the file is a folder, we use the method in recursion
			{
				listFilesForFolder(fileEntry,arr2 , csv2);
			} 
			else  //if the file is csv, create a layer of it
			{
				if(fileEntry.getName().contains(".csv"))
				{
					
					MyLayer layer = csv.readFromCSV(fileEntry.getAbsolutePath());
					arr.add(layer);
				}
			}
		}

	}


}
