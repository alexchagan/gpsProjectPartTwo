package GUI;

import javax.swing.JFrame;

import Geom.Point3D;
/**
 * this class represents our "map" and support the cordinates convertision from GIS to pixels
 * 
 * @author nour ilya alex
 *
 */
public class Map {

   	   	double PixelsWidthX=1433;
		 double rangeYpixels=-642;
		 double rangeXpolar=0.0100;
		 double rangeYpolar=0.0038;
		 double PolarX0=35.2023;
		 double PolarY0=32.1057;
/**
 * 
 * @param point with pixel cordinates 
 * @return point with GIS cornitases (lat lon alt)
 */
		public  Point3D XY2latlon(Point3D point) {
			double newX = rangeXpolar*(point.x()/PixelsWidthX)+PolarX0;
			double newY = rangeYpolar*(point.y()/rangeYpixels)+PolarY0;
			return new Point3D(newX,newY,0);
		}
		/**
		 * 
		 * @param point with GIS cornitases (lat lon alt) 
		 * @return point with pixel cordinates
		 */
		public  Point3D latlon2XY(Point3D point) {
			double newX = Math.round(((point.x()-PolarX0)/rangeXpolar)*PixelsWidthX);
			double newY = Math.round(((point.y()-PolarY0)/rangeYpolar)*rangeYpixels);
			return new Point3D(newX,newY,0);
		}	
}


