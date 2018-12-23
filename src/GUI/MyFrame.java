package GUI;

import java.awt.Color;
import java.awt.FileDialog;
import java.awt.Graphics;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.omg.PortableInterceptor.Current;

//import Algo.Algo;
import Geom.Geom_element;
import Geom.Point3D;
import de.micromata.opengis.kml.v_2_2_0.Point;
import Algo.ShortestPathAlgo;
import Coords.MyCoords;
import File_format.ToKMLv2;
import GIS.GIS_layer;
import GIS.MyElement;
import GIS.MyMetaData;

/**
 * This Class represents the GUI of our game
 * Contain functions such as: opening a game, saving a game, editing a new game, clearing everything
 * and playing the game(making the packmen move and eat the fruits)
 * @author Alex Nour Ilya
 *
 */
public class MyFrame extends JFrame implements MouseListener
{

	private BufferedImage arielimg;
	private BufferedImage pac1;
	private BufferedImage pac0;
	private Map m = new Map();
	private ArrayList<Fruit> xyfruits = new ArrayList<>();
	private ArrayList<Packman> xypacs = new ArrayList<>(); 
	static boolean fruit = false;
	static boolean pacf = false;
	static boolean clik = false;
	static boolean mou = false;
	private Point3D point;
	private ShortestPathAlgo algo;
	private game game ;
	private MyCoords mc =new MyCoords();
	private ArrayList<Point3D> lines = new ArrayList<>();
	private Random rand = new Random();
	private ArrayList<MyElement> solution = new ArrayList<>();
	private static int pathc = 0;

/**
 * Frame constructor
 */
	public MyFrame() {
		initGUI();		
		this.addMouseListener(this); 
		this.setVisible(true);
		this.setSize(this.arielimg.getWidth(),this.arielimg.getHeight());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	}
	/**
	 * Gui initialization
	 */
	private void initGUI() 
	{
		MenuBar menuBar = new MenuBar();
		Menu input = new Menu("input");
		Menu game = new Menu("Game"); 
		MenuItem open = new MenuItem("Open");
		MenuItem save = new MenuItem("Save");
		MenuItem frt = new MenuItem("Fruits");
		MenuItem pac = new MenuItem("Pacmans");
		Menu playM = new Menu("play");

		MenuItem play = new MenuItem("play");
		MenuItem path2KML = new MenuItem("path to KML");
		MenuItem clr = new MenuItem("Clear");


		menuBar.add(game);
		game.add(open);
		game.add(save);
		menuBar.add(input);
		input.add(frt);
		input.add(pac);
		input.add(clr);

		menuBar.add(playM);
		playM.add(play);
		playM.add(path2KML);

		this.setMenuBar(menuBar);
		open.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				readFileDialog();			}
		});
		frt.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				fruit = true;
				pacf = false;


			}
		});
		pac.addActionListener(new ActionListener() {


			@Override
			public void actionPerformed(ActionEvent e) {
				pacf = true;
				fruit = false;

			}
		});

		clr.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				xypacs.removeAll(xypacs);
				xyfruits.removeAll(xyfruits);
				lines.removeAll(lines);

				repaint();

			}
		});
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					writeFileDialog();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		play.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				runpac();
			}
		});
		path2KML.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ToKMLv2.layout1(solution, ("game "+ ++pathc));
			}
		});

		try {
			arielimg = ImageIO.read(new File("Ariel1.png"));
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("reading image err");
		}		
		try {
			pac1 = ImageIO.read(new File("pac1.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			pac0 = ImageIO.read(new File("pac0.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
/**
 * performs the back scene mechanical "repainting" of main window graphics
 */
	public void paint(Graphics g)

	{
		g.drawImage(arielimg, 0, 0, getWidth()-10, getHeight()-10,null);
		//		if(xyfruits != null) {

		Iterator<Fruit> fit = xyfruits.iterator();
		Iterator<Packman> pit = xypacs.iterator();
		Iterator<Point3D> line = lines.iterator();

		while(fit.hasNext() ) {

			Fruit fruit = fit.next();				
			int r = 20,x = 0,y = 0;
			if(clik) {
				if(this.fruit || pacf) {
					x = (int) (fruit.getPlace().x()/fruit.getPlace().getCompW() * getWidth());
					y = (int) (fruit.getPlace().y()/fruit.getPlace().getComph() * getHeight());

				}}
			else {
				x = (int) (fruit.getPlace().x()/1433 * getWidth());
				y = (int) (fruit.getPlace().y()/642 * getHeight());
			}if(!fruit.eaten())
				g.setColor(Color.green);//////////////////////////////////////////////////////////////////////////////////////////
			else g.setColor(Color.red);
			//	g.drawImage(zbr, x-10, y-8,70 ,70, null);
			g.fillOval(x-10 , y-8 , r, r);
		}

		while (pit.hasNext()) {

			Packman pac = pit.next();
	
			int r = 30,x = 0,y = 0;
			if(clik) {
				if(fruit || pacf) {
					x = (int) (pac.getPlace().x()/pac.getPlace().getCompW() * getWidth());
					y = (int) (pac.getPlace().y()/pac.getPlace().getComph() * getHeight());

				}
			}
			else {
				x = (int) (pac.getPlace().x()/1433  * getWidth());
				y = (int) (pac.getPlace().y()/642 * getHeight());
			}
			//g.setColor(Color.yellow);
			if(mou) {
				g.drawImage(pac1, x-10, y-8,50 ,50, null);
				mou=false;
			}
			else {
				g.drawImage(pac0, x-10, y-8,50 ,50, null);
				mou=true;
			}
			//g.drawLine(pac.getPlace().ix(), pac.getPlace().iy(), pac.getPath().getpacPath().get(0).ix(), pac.getPath().getpacPath().get(0).iy());
			//g.fillOval(x-10 , y-8 , r, r);
		}


		while(line.hasNext()) {
			int r = 30,x = 0,y = 0;

			Point3D p1 = line.next();
			x = (int) (p1.x()/1433 * getWidth());////////////////////////////////////////////////////////
			y = (int) (p1.y()/642 * getHeight());///////////////////////////////////////////////////////////////me3odkan///////////
			//Point3D p2 = line.next();
			//	g.drawLine(p1.ix(), p1.iy(), p2.ix(), p2.iy());

			g.setColor(Color.white);
			g.fillOval(x-10, y-8,5 ,5);

		}







	}
	/**
	 * supports the "run" function of the game, visualizing the actual "game" action 
	 */
	private void runpac(){
		lines.removeAll(lines);
		solution.removeAll(solution);

		if(xyfruits.isEmpty())
			return;
		
		Iterator<Packman> packs = xypacs.iterator();
		LocalDateTime date = LocalDateTime.now().withNano(0);

		while(packs.hasNext()) {
			solution.add(new MyElement(m.XY2latlon(new Point3D(packs.next().getPlace())),
					new MyMetaData(""+date.toEpochSecond(ZoneOffset.UTC),"red")));
		}
		Iterator<Fruit> fruts = xyfruits.iterator();
		while(fruts.hasNext()) {
			solution.add(new MyElement(m.XY2latlon(new Point3D(fruts.next().getPlace())),
					new MyMetaData(""+date.toEpochSecond(ZoneOffset.UTC),"green")));
		}




		//		for (int i = 0; i < soulution.size(); i++) {
		//			System.out.println(soulution.get(i).getGeom());
		//
		//		}
		//		System.out.println(soulution.size());


		//	System.out.println(m.latlon2XY(xypacs.get(0).getPath().getpacPath().get(0)));
		//	System.out.println(xypacs.get(1).getPath());
		//		int count = xyfruits.size()+2;
		double jj = 0;
		boolean done = false;
		double max = xypacs.get(0).getTime();
		
		while(!done) {
			jj++;
			done = true;
			for (int i = 0; i < xyfruits.size(); i++) {

				if(!xyfruits.get(i).eaten())
					done = false;
			}

			for (int i = 0; i < xypacs.size(); i++) {

				Packman pac = xypacs.get(i);

				if(max < pac.getTime()) {
					max = pac.getTime(); 
				}
				ArrayList<Point3D> pacpath = pac.getPath().getpacPath();
				Point3D pix = null;
				/////////////////////titkadem lakevon hpre/////////////////////////////////////////////////////////////////////
				if(pacpath.size() > 0) {


					pix = directionPixels(pac.getPlace(),
							m.latlon2XY(pacpath.get(0)));
					lines.add(pix);
					if(jj %25 == 0) {
					date = LocalDateTime.now().withNano(0);
					solution.add(new MyElement(m.XY2latlon(new Point3D(pix)),
							new MyMetaData(""+date.toEpochSecond(ZoneOffset.UTC),"red")));
					//	pac.setPlace(m.latlon2XY(xypacs.get(i).getPath().getpacPath().get(0)));
					}
					
					pac.setPlace(pix);



				}
				/////////////////////////////////////////////////////////////////////////////////////////////////////////////
				if(!pacpath.isEmpty()) {//yesh lo lean lalehet

					if(distP(pac.getPlace(), m.latlon2XY(pacpath.get(0))) <= pac.getRadius()) {//////em y5ol l25ol 20t5a

						for (int j = 0; j < xyfruits.size(); j++) {////////me7apes it hpre
							Fruit frt = xyfruits.get(j);
							//			System.out.println(distP(frt.getPlace(),directionPixels( pac.getPlace(),frt.getPlace())) +"    r>"+pac.getRadius()+"     count"+);

							//System.out.println(xyfruits.get(j).getPlace()+"\n"+m.latlon2XY(xypacs.get(i).getPath().getpacPath().get(0)));
							//	if(frt.getPlace().equals(pix)) {
							if(distP(frt.getPlace(),pix) <= pac.getRadius() || distP(frt.getPlace(),pix) == 0) {//////em y5ol l25ol 20t5a

								//								count--;
								frt.eaten(true);

							}
						}
						pacpath.remove(0);

					}


				}

			}
			//			try {
			//				Thread.sleep(0);
			//			} catch (InterruptedException e) {
			//				e.printStackTrace();
			//			}
			paint(getGraphics());
		}
		System.out.println("all the fruits eaten in "+max);
		System.out.println("done");
	}
	

	private double distP(Point3D start,Point3D dest) {

		return Math.sqrt(Math.pow(start.x()-dest.x(),2) + Math.pow(start.y()-dest.y(),2) + Math.pow(start.z()-dest.z(),2) );
	}
	public Point3D directionPixels(Point3D start,Point3D dest)
	{
		double currentX = start.x();
		double currentY = start.y();

		double deltaX = dest.x() - start.x();
		double deltaY = dest.y() - start.y();
		double angle = Math.atan2( deltaY, deltaX );
		currentX +=  Math.cos( angle );
		currentY +=  Math.sin( angle );

		return new Point3D (currentX,currentY,0);
	}
	@Override
	public void mouseClicked(MouseEvent arg) {
		clik = true;
		//System.out.println("("+ arg.getX() + "," + arg.getY() +")");
		if(fruit) {
			Fruit clikp = new Fruit(new Point3D((double) arg.getX(),(double) arg.getY()));
			clikp.getPlace().setCompW(getWidth()); clikp.getPlace().setComph(getHeight());
			this.xyfruits.add(clikp);
			repaint();


		}

		if(pacf) {
			Packman clikp = (new Packman(new Point3D((double) arg.getX(),(double) arg.getY())));
			clikp.getPlace().setCompW(getWidth()); clikp.getPlace().setComph(getHeight());
			this.xypacs.add(clikp);
			//(new Point3D((double) arg.getX()/getWidth(),(double) arg.getY()/getHeight()) ));
			repaint();
		}

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	/**
	 * supports the "open" function to read a csv file and initialize the game
	 */
	public void readFileDialog() {
		this.xypacs.removeAll(xypacs);
		this.xyfruits.removeAll(xyfruits);
		clik=false;
		//		try read from the file
		FileDialog fd = new FileDialog(this, "Open csv file", FileDialog.LOAD);
		fd.setFile("*.csv");
		fd.setDirectory("C:\\Users\\nour\\Desktop\\game");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();

		System.out.println(folder+fileName);
		game = new game();
		game.read_game_csv(folder+fileName);
		game gametoalgo = new game();
		gametoalgo.read_game_csv(folder+fileName);
		/////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////
		ShortestPathAlgo algo = new ShortestPathAlgo(gametoalgo);
		ArrayList<Packman> alfteralgo = algo.shortestpath();


		for (int i = 0; i < game.getPacs().size() ; i++) {
			for (int j = 0; j < alfteralgo.size(); j++) {
				if(game.getPacs().get(i).getId() == alfteralgo.get(j).getId()) {
					game.getPacs().get(i).setPath(alfteralgo.get(j).getPath());
					game.getPacs().get(i).setTime(alfteralgo.get(j).getTime());
				}
			}
		}


		//////////////////////////////////////////////////////////////
		/////////////////////////////////////////////////////////////

		Iterator<Fruit> fit = game.getFruits().iterator();
		Iterator<Packman> pit = game.getPacs().iterator();

		while(fit.hasNext()) {
			Fruit f = fit.next();
			Point3D llp = new Point3D(f.getPlace().x(),f.getPlace().y(),f.getPlace().z());
			Point3D xyp =m.latlon2XY(llp);
			xyfruits.add(new Fruit(new Point3D(xyp)));
		}
		while(pit.hasNext()) {
			Packman csvp =pit.next();
			Point3D xyp =m.latlon2XY(csvp.getPlace());
			csvp.setPlace(xyp);
			xypacs.add(csvp);

		}
		repaint();
	}
	/**
	 * this function supports the "save" option 
	 * @throws IOException
	 */
	public void writeFileDialog() throws IOException {
		//		 try write to the file
		FileDialog fd = new FileDialog(this, "Save the csv file", FileDialog.SAVE);
		fd.setFile("*.csv");
		fd.setFilenameFilter(new FilenameFilter() {
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(".csv");
			}
		});
		fd.setVisible(true);
		String folder = fd.getDirectory();
		String fileName = fd.getFile();
		saveGame(folder + fileName);
	}
	/**
	 * 
	 * @param path the actual "path" where the filename.csv will be created
	 * @throws IOException
	 */
	public void saveGame(String path) throws IOException
	{
		int pCounter=-1;
		int fCounter=-1;
		ArrayList<Packman> packs = new ArrayList<Packman>();
		ArrayList<Fruit> fruits = new ArrayList<Fruit>();

		Iterator<Packman> it = xypacs.iterator();
		while(it.hasNext())
		{
			++pCounter;
			Point3D packPixel = it.next().getPlace();
			Packman packman = new Packman(m.XY2latlon(packPixel),pCounter,1,1);
			packs.add(packman);
		}

		Iterator<Fruit> it2 = xyfruits.iterator();
		while(it2.hasNext())
		{
			++fCounter;
			Point3D fruitPixel = it2.next().getPlace();
			Fruit fruit = new Fruit(m.XY2latlon(fruitPixel),fCounter,1);
			fruits.add(fruit);
		}

		game newGame = new game(packs,fruits);
		newGame.WriteToCSV(path);

	}

}





