#gpsProjectPartTwo
========
This project is a continuation project to gpsProject https://github.com/alexchagan/gpsProject.  
It focuses on converting gps (lat,lon,alt) points to pixelated points, from a google earth display to a 2D image display of the same area.  
We turn these pixelated points on a 2D image into a "Packman game", where such points represent either "packmen" or "fruits".     
The objective of the game is for the packmen to "eat" all the fruits on the image in the fastest time possible, concidering their
speed and radius of "eating".  
After a certain game was played, the project creates a KML file which lets us see all the routes of the packmen on a google earth display.

#Relevant Packages & Classes
========

#GUI
=======
- MyFrame: This Class represents the GUI of our game.
 Contain functions such as: opening a game, saving a game, editing a new game, clearing everything.
 and playing the game(making the packmen move and eat the fruits).  
 Also has the abillity to change the GUI resolutions without changing to proportions of the objects.  
 Note: still contains some minor bugs  
   
![Alt text](guirunmod.jpeg?raw=true "")
![Alt text](guirunmod2.jpeg?raw=true "")
   
   
 - Packman: This class represents a packman in the game with id, movement speed, radius of eating,
 score(the sum of weights of all fruits it ate), path(collection of fruits that it needs to eat)
 time(the time it took for it to finish it's path).
 
- Fruit: This class represents a fruit in the game with id, weight, coordinates
 and indication if it was eaten or not.
 
 - Game: This class represents our game and contains list of packmen and fruits.
 also contains methods to read such objects from csv files and write to new csv files. 
 
 - Path: This class represents the collection of coordinates(fruits) that each packman needs to go through.
 
 - Map: This class contains all the attributes of our image including the file itself and it's graphical qualities. Also gives the option to make transitions from lat-lon to pixels and pixels to lat-lon.
 
 - PathToKML: This class converts all the points of our paths into a KML file and showcases them on google earth.  
   
![Alt text](google1.jpeg?raw=true "")
     
 
 #Algo
=======
-ShortestPathAlgo: This class is responsible for calculating the best path for each packman in the game
 so that the fruits will be eaten in the fastest time possible.
 
 -PixelMovement: This class is responsible for calculating the direction of the packman to its current target and moving it towards the target.
 
 


