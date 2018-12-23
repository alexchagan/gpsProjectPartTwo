package Algo;

import java.util.ArrayList;
import Coords.MyCoords;
import java.util.Iterator;

import Coords.MyCoords;
import GUI.Fruit;
import GUI.Map;
import GUI.Packman;
import GUI.Path;
import Geom.Point3D;
import GUI.game;
/**
 * This class is responsible for getting the best path for each packman in the game
 * so that the fruits will be eaten in the fastest time possible.
 * 
 * 
 * @author Alex Nour Ilya
 *
 */

public class ShortestPathAlgo {

	private game game;
	private MyCoords mc = new MyCoords();
	private double _time = 0 ;
	private Packman pac;
	private Fruit frt;
	private ArrayList<Packman> newpacs = new  ArrayList<>();
	private ArrayList<Fruit> newfruits = new ArrayList<>();
	/**
	 * Builder that asks for a game
	 * @param game - our current game
	 */
	public ShortestPathAlgo(game game)
	{
		this.game = game;
		copy(game);
	}
	/**
	 * Method that does path calculations depending on speed and radius of each packman. 
	 * @return a list of packmen with updated paths
	 */

	public ArrayList<Packman> shortestpath(){
		while(newfruits.size() > 0) {
		int time = Integer.MAX_VALUE;
		for (int i = 0; i < newpacs.size(); i++) {
			for (int j = 0; j < newfruits.size(); j++) {
				double dist = Math.abs(mc.distance3d(newpacs.get(i).getPlace(),
						newfruits.get(j).getPlace())-newpacs.get(i).getRadius()); 
				double temp = dist / newpacs.get(i).getSpeed();

				if(pac !=null &&temp < time && temp + newpacs.get(i).getTime()
						< _time + pac.getTime() && !newfruits.get(j).eaten()) {
					time = (int) temp ;
					pac = newpacs.get(i);
					_time = temp;
					frt =newfruits.get(j);

				}
				if(pac == null && temp < time) {
					time = (int) temp ;
					pac = newpacs.get(i);
					_time = temp;
					frt =newfruits.get(j);
				}
			}
		}
		for (int i = 0; i < newpacs.size(); i++) {
			if(newpacs.get(i).getId() == pac.getId()) {
				newpacs.get(i).getPath().addfruitPath(frt.getPlace());
				newpacs.get(i).setPlace(frt.getPlace());
				newpacs.get(i).setTime(pac.getTime() + _time);
			}
		}
		for (int i = 0; i < newfruits.size(); i++) {
			if(newfruits.get(i).getId() == frt.getId()) {
				frt.eaten(true);
				newfruits.remove(newfruits.get(i));
			}
		}

		//if(newfruits.size() > 0) {
			pac = null ;
			_time = 0;
			frt = null;
		//	shortestpath();


		}
		
		return newpacs;

	}
	/**
	 * A method that copies the fruits and packmen of our game because we don't want to 
	 * make changes to out actual game
	 * @param game - our game
	 */
	private void copy(game game) {
		newpacs = game.getPacs();
		newfruits =game.getFruits();


	}




}
