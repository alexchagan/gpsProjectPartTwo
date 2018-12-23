/**
 * 
 */
package Coords;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Geom.Point3D;

/** JUnit tests for class: MyCoords
 * @author Alex,Ilya,Nour
 *
 */
class MyCoordsTest {

	MyCoords mc = new MyCoords();
	Point3D p0 = new Point3D("32.103315,35.209039,670");
	Point3D p1 = new Point3D("32.106352,35.205225,650");
	double distance = 493.45345296487307; //distance that takes altitude in calculation
	Point3D vector = new Point3D(337.6989920612881,-359.24920693881893,-20.0);
	double azimuth = 313.23042032651705;
	double pitch = 4.712198280389201;
	double[] aed = {azimuth,pitch,distance};
	


	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/** This method is not fully accurate, therefore it will fail 
	 * Test method for {@link Coords.MyCoords#add(Geom.Point3D, Geom.Point3D)}.
	 */
	@Test
	void testAdd() {
		
		Point3D expected = p1;
		Point3D actual = mc.add(p0,vector);
		assertTrue(expected.equals(actual));
			
	}

	
	/**
	 * Test method for {@link Coords.MyCoords#distance3d(Geom.Point3D, Geom.Point3D)}.
	 */
	@Test
	void testDistance3d() {
		double expected = distance;
		double actual = mc.distance3d(p0,p1);
		assertEquals(expected, actual);
		
	}

	/**
	 * Test method for {@link Coords.MyCoords#vector3D(Geom.Point3D, Geom.Point3D)}.
	 */
	@Test
	void testVector3D() {
		Point3D expected = vector;
		Point3D actual = mc.vector3D(p0,p1);
		System.out.println(actual.toString());
		System.out.println(vector.toString());
		assertTrue(expected.equals(actual));
	}

	/**
	 * Test method for {@link Coords.MyCoords#azimuth_elevation_dist(Geom.Point3D, Geom.Point3D)}.
	 */
	@Test
	void testAzimuth_elevation_dist() {
		double[] expected = aed;
		double[] actual = mc.azimuth_elevation_dist(p0, p1);
		assertTrue(Arrays.equals(expected, actual));
	}



}
