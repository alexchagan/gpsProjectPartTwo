package GUI;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

/**
 * 
 * @author Alex Nour Ilya
 * This class represents our game and contains list of packmen and fruits.
 * also contains methods to read such objects from csv files and write to new csv files. 
 *
 */

public class game {

	private ArrayList<Fruit> fruits = new ArrayList<>();
	private ArrayList<Packman> pacs= new ArrayList<>();
	/**
	 * default constructor
	 */

	public game() {
		
	}
	/**
	 * Constructor by collection of packmen and fruits
	 * @param pacs
	 * @param fruits
	 */
	public game(ArrayList<Packman> pacs,ArrayList<Fruit> fruits) {
		this.pacs = pacs;
		this.fruits = fruits;
	}
	/**
	 * @return collection of packmen
	 */
	public ArrayList<Packman> getPacs() {
		return pacs;
	}
	/**
	 * Set a new collection of packmen for our game
	 * @param pacs
	 */
	public void setPacs(ArrayList<Packman> pacs) {
		this.pacs = pacs;
	}
	/**
	 * @return collection of fruits
	 */
	public ArrayList<Fruit> getFruits() {
		return fruits;
	}
	/**
	 * Set a new collection of fruits for our game
	 * @param fruits
	 */
	public void setFruits(ArrayList<Fruit> fruits) {
		this.fruits = fruits;
	}
	
	/**
	 * Read fruits and packmen from a csv file and put them in the collections
	 * @param csvPath - path of the file
	 */
	
	public void read_game_csv(String csvPath) {

		try {

			List<String> lines = Files.readAllLines(Paths.get(csvPath));
			Iterator<String> it = lines.iterator();
			it.next();

			while(it.hasNext()) {
				String line = it.next();
				String s[] = line.split(",");
				if(s[0].toLowerCase().equals("f"))
					this.fruits.add(new Fruit(line));
				if(s[0].toLowerCase().equals("p"))
					this.pacs.add(new Packman(line));			
			}

		} catch (IOException e) {
			System.err.println("err reading CSV");
			e.printStackTrace();
		}
	}
	/**
	 * Take the collections of packmen and fruits
	 * insert them into a new csv file
	 * @param filePath - path
	 */
	public void WriteToCSV(String filePath) {
		
		try (
				BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath));

				CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT
						.withHeader("Type", "id", "Lat","Lon","Alt","Speed/Weight","Radius"));
				) {

			Iterator<Packman> it = this.pacs.iterator();
			while(it.hasNext())
			{
				Packman p = it.next();
				csvPrinter.printRecord("P",String.valueOf(p.getId()),String.valueOf(p.getPlace().y())
						, String.valueOf(p.getPlace().x()), String.valueOf(p.getPlace().z())
						, String.valueOf(p.getSpeed()), String.valueOf(p.getRadius()));
			}

			Iterator<Fruit> it2 = this.fruits.iterator();
			while(it2.hasNext())
			{
				Fruit f = it2.next();
				csvPrinter.printRecord( "F", String.valueOf(f.getId()) , String.valueOf(f.getPlace().y())
						, String.valueOf(f.getPlace().x()), String.valueOf(f.getPlace().z())
						, String.valueOf(f.getWeight())); 

			}


			csvPrinter.flush();            
		} catch (IOException e) {
			System.err.println("err writing csv ..");
			e.printStackTrace();
		}
	}
	


}
