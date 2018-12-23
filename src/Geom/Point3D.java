package Geom;

import java.io.Serializable;
public class Point3D implements Geom_element, Serializable 
{
	/**
	 * This class represents a 3D point in space.
	 */
	private static final long serialVersionUID = 1L;
	private double _x,_y,_z;
	private int compW = 1;
	private int comph = 1;
	public int getCompW() {
		return compW;
	}
	public void setCompW(int compW) {
		this.compW = compW;
	}
	public int getComph() {
		return comph;
	}
	public void setComph(int comph) {
		this.comph = comph;
	}
	/**
	 * Point3D constructor by 3 variables
	 * @param x
	 * @param y
	 * @param z
	 */
	public Point3D(double x,double y,double z) 
	{
		_x=x;
		_y=y;
		_z=z;
	}
/**
 * Copy constructor
 * @param p - Point3D
 */
	public Point3D(Point3D p) 
	{
		_x=p.x();
		_y=p.y();
		_z=p.z();
	}
	
	/**
	 * Constructor by 2 variables and _z=0;
	 * @param x
	 * @param y
	 */
	public Point3D(double x,double y) 
	{this(x,y,0);}
	
	/**
	 * Constructor by String with format "x,y,z"
	 * @param s - String
	 */
	public Point3D(String s) {
		String[] a = s.split(",");
		_x = Double.parseDouble(a[0]);
		_y = Double.parseDouble(a[1]);
		_z = Double.parseDouble(a[2]);
	}
	///////////////////////////////////////////////////////////////////////////
	////////////////////////////       methods        /////////////////////////
	///////////////////////////////////////////////////////////////////////////

	/////getters////
	
	public double x() {return _x;}
	public double y() {return _y;}
	public double z() {return _z;}
	
	////converters to integer///////
	
	public int ix() {return (int)_x;}
	public int iy() {return (int)_y;}
	public int iz() {return (int)_z;}
		
	/**
	 * Adds the 2 points together by same variables
	 * @param p - the other point
	 */
	public void add(Point3D p) { add(p._x,p._y,p._z);}
	
	/**
	 * Adds same variables
	 * @param dx
	 * @param dy
	 * @param dz
	 */
	public void add(double dx, double dy, double dz) {
			_x+=dx;_y+=dy;_z+=dz;
		}
	
	/**
	 * adds only the x and y variables
	 * @param x
	 * @param y
	 */
	public void add(double x, double y){this.add(x,y,0);}

	/**
	 * ToString method by format "x,y,z"
	 */
	public String toString() 
	{
		return ""+_x+","+_y+","+_z;
	}
	
	/**
	 * Calculates the 2D distance between two points
	 */
	public double distance2D(Point3D p2) { 
		return this.distance3D(p2.x(), p2.y(), this.z());
	}
	
	/**
	 * Calculates the 3D distance between two points
	 */
	public double distance3D(Point3D p2) {
		return this.distance3D(p2.x(), p2.y(), p2.z());}
	
	/**
	 * 3D distance calculation
	 * @param x
	 * @param y
	 * @param z
	 * @return 3D distance
	 */
	public double distance3D(double x, double y , double z)
	{
		double dx = _x-x;
		double dy = _y-y;
		double dz = _z-z;
		double t = dx*dx+dy*dy+dz*dz;
		return Math.sqrt(t);
	}

	/**
	 * Checks if 2 3d points are equal.
	 * @param p2
	 * @return true or false
	 */
	public boolean equals(Point3D p2)
	{
		return ( (this._x==p2._x) && (this._y==p2._y) && (this._z==p2._z) );
	}
	
	/**
	 * Checks if the distance between 2 points is smaller than a certain distance
	 * @param p2 - another 3D ponts
	 * @param dist - requested distance
	 * @return true or false
	 */
	public boolean close2equals(Point3D p2, double dist)
	{
		return ( this.distance3D(p2)< dist );
	}
	
	/**
	 * Checks if the X and Y of 2 points are equal.
	 * @param p
	 * @return true of false
	 */
	  public boolean equalsXY (Point3D p)
	    {return p._x == _x && p._y == _y;}
	    
	  /**
	     * Return a string of Point3D by format "x,y,z"
	     * @return
	     */
    public String toFile()  {return _x+","+_y+","+_z;}
    
    /**
     * Return a string of Point3D by format "Point3D x y z"
     * @return
     */
    public String toFile1()  {return "Point3D "+_x+" "+_y+" "+_z;}
    
    /**
     * Converts a point from ECEF (x,y,z) to LLA (lat,lon,alt)
     * @param point 
     * @return
     */
    public Point3D ecef2lla()
    {
    double a = 6378137;
    double b = 6.3568e6;
    double e = Math.sqrt((Math.pow(a, 2) - Math.pow(b, 2)) / Math.pow(a, 2));
    double e2 = Math.sqrt((Math.pow(a, 2) - Math.pow(b, 2)) / Math.pow(b, 2));
    
    double lat, lon, alt, N , theta, p;

    p = Math.sqrt(Math.pow(this._x, 2) + Math.pow(this._y, 2));

    theta = Math.atan((this._z * a) / (p * b));

    lon = Math.atan(this._y / this._x);

    lat = Math.atan(((this._z + Math.pow(e2, 2) * b * Math.pow(Math.sin(theta), 3)) / ((p - Math.pow(e, 2) * a * Math.pow(Math.cos(theta), 3)))));
    N = a / (Math.sqrt(1 - (Math.pow(e, 2) * Math.pow(Math.sin(lat), 2))));

    double m = (p / Math.cos(lat));
    alt = m - N;


    lon = lon * 180 / Math.PI;
    lat = lat * 180 / Math.PI; 

    return new Point3D(lat,lon,alt);
    }
    

    /**
     * Converts a point from LLA to ECEF
     * source:https://stackoverflow.com/questions/16614057/longitude-latitude-altitude-to-3d-cartesian-coordinate-systems
     * @return
     */
    public Point3D lla2ecef()
    {
    	
    	/*
    	 * WGS84 ellipsoid constants Radius
    	 */
    	 double a = 6378137;
    	/*
    	 * eccentricity
    	 */
    	 double e = 8.1819190842622e-2;

    	 double asq = Math.pow(a, 2);
    	 double esq = Math.pow(e, 2);

    	
    	   double lat = Math.toRadians(this._x);
    	   double lon = Math.toRadians(this._y);
    	   double alt = this._z;

    	   double N = a / Math.sqrt(1 - esq * Math.pow(Math.sin(lat), 2));

    	   double x = (N + alt) * Math.cos(lat) * Math.cos(lon);
    	   double y = (N + alt) * Math.cos(lat) * Math.sin(lon);
    	  double z = ((1 - esq) * N + alt) * Math.sin(lat);
    	return new Point3D(x,y,z);
    
    }
    
    
public final static int ONSEGMENT = 0,  LEFT = 1, RIGHT = 2, INFRONTOFA = 3, BEHINDB = 4, ERROR = 5;
public final static int DOWN = 6, UP = 7;

/** return up iff this point is above the SEGMENT (not the line) */
    public int pointLineTest2(Point3D a, Point3D b) {
    	int flag = this.pointLineTest(a,b);
    	if(a._x < b._x ) {
    		if(a._x<=_x && b._x>_x) {
    			if (flag == LEFT) return DOWN;
    			if (flag == RIGHT) return UP;
    		}
    	}
    	else 
    	if(a._x > b._x ) {
    		if(b._x<=_x && a._x>_x) {
    			if (flag == RIGHT) return DOWN;
    			if (flag == LEFT) return UP;
    		}
    	}	
    	return flag;
	}

 
    /** pointLineTest <br>
	test the following location of a point regards a line segment - all in 2D projection.<br><br>
   
	ONSEGMENT:  �����a----+----b������                              <br> <br>

	           +       +        +                              <br>
	LEFT:	 �����a---------b������                              <br> <br>


	RIGHT:	 �����a---------b������                              <br>
    		   +      +        +                              <br> <br>

	INFRONTOFA:  ��+��a---------b������                              <br>
        BEHINDB:  �����a---------b����+�                              <br>
	ERROR: a==b || a==null || b == null;                               <br>
    */

    public int pointLineTest(Point3D a, Point3D b) {

	if(a== null || b==null || a.equalsXY(b)) return ERROR;

	double dx = b._x-a._x;
	double dy = b._y-a._y;
	double res = dy*(_x-a._x)-dx*(_y-a._y);

	if (res < 0) return LEFT;
	if (res > 0) return RIGHT;
	
	if (dx > 0) {
	    if (_x < a._x) return INFRONTOFA;
	    if (b._x < _x) return BEHINDB;
	    return ONSEGMENT;
	}
	if (dx < 0) {
	    if (_x > a._x) return INFRONTOFA;
	    if (b._x > _x) return BEHINDB;
	    return ONSEGMENT;
	}
	if (dy > 0) {
	    if (_y < a._y) return INFRONTOFA;
	    if (b._y < _y) return BEHINDB;
	    return ONSEGMENT;
	}
	if (dy < 0) {
	    if (_y > a._y) return INFRONTOFA;
	    if (b._y > _y) return BEHINDB;
	    return ONSEGMENT;
	}
	return ERROR;
    }
	
	
	////////////////////////////////////////////////////////////////
	public void rescale(Point3D center, Point3D vec) {
		if(center!=null && vec != null)
			rescale(center,vec.x(),vec.y(),vec.z());
	}
	
	public void rescale(Point3D center, double size) {
		if(center!=null && size>0)
			rescale(center,size,size,size);
	}
	private void rescale(Point3D center, double sizeX,double sizeY, double sizeZ) {
		_x = center._x + ((_x - center._x) * sizeX);
		_y = center._y + ((_y - center._y) * sizeY);
		_z = center._z + ((_z - center._z) * sizeZ);
	} 
	
	public void rotate2D(Point3D center, double angle) {
 	// angle -1/2PI .. 1/2Piregular degrees. 
		_x = _x - center.x();
		_y = _y - center.y();
		double a = Math.atan2(_y,_x);
	//	System.out.println("Angle: "+a);
		double radius = Math.sqrt((_x*_x) + (_y*_y));
		_x = (center.x() +  radius * Math.cos(a+angle));
		_y = (center.y() +  radius * Math.sin(a+angle));
	}								
	/** computes the angleXY between p1 and p2 in RADIANS: <br><br>
	up:(PI/2)  , down (-PI/2) , right(0),  left(+- PI).   [-PI, +PI]	*/
	public double angleXY(Point3D p) {
		if(p==null) throw new RuntimeException("** Error: Point3D angle got null **");
		return Math.atan2((p._y-_y), (p._x-_x));
	}
	/** computes the angleXY between p1 and p2 in RADIANS: <br><br>
	up:(PI/2)  , down (1.5PI) , right(0),  left(PI).   [0,2PI].	*/
	public double angleXY_2PI(Point3D p) {
		if(p==null) throw new RuntimeException("** Error: Point3D angle got null **");
		double ans = Math.atan2((p._y-_y), (p._x-_x));
		if (ans<0) ans = 2*Math.PI+ans;
		return ans;
	}
	/** computes the angleZ between p1 and p2 in RADIANS */ 							
	public double angleZ(Point3D p) {
		if(p==null) throw new RuntimeException("** Error: Point3D angleZ got null **");
		return Math.atan2((p._z-_z), this.distance2D(p));
	}	
/** return the (planer angle of the vector between this --> p, in DEGREES, in a
 * compass order: north 0, east 90, south 180, west 270.
 * @param p is the end point of the vector (z value is ignored). 
 * @return angle in compass styye [0,360).
 */
	public double north_angle(Point3D p) {
		double ans = 0;
		double a_rad = Math.atan2((p._y-_y), (p._x-_x));
		double a_deg = Math.toDegrees(a_rad);
		if(a_deg<=90) ans = 90-a_deg;
		else ans = 450-a_deg;
		return ans;
	}
	/** return the vertical angles in DEGREES of the vector this-->p
	 * 
	 * */
	public double up_angle(Point3D p) {
		double ans = 0;
		ans = Math.atan2((p._z-_z), this.distance2D(p));
		return Math.toDegrees(ans);
	}
	/** return the vertical angles in DEGREES of the vector this-->p, 
	 *  @param h: is the extra height of the point p (used by GISElement).
	 * */
	public double up_angle(Point3D p, double h) {
		double ans = 0;
		ans = Math.atan2((p._z+h-_z), this.distance2D(p));
		return Math.toDegrees(ans);
	}
	/** transform from radians to angles */
	public static double r2d(double a) { return Math.toDegrees(a);}
	/** transform from radians to angles */
	public static double d2r(double a) { return Math.toRadians(a);}
	////////////////////////////////////////////////////////////////////////////////
}
