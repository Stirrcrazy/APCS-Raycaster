// Tim Norwood & Nate Adam
// 2022-6-13
// CSE 142 AP CS A
//
// This class assembles a level from a user created file listing the walls in the map as sets of
// two coordinate points.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
public class Map {

   private ArrayList<Walls> walls = new ArrayList<Walls>(); // The list of walls to send to the Camera
   
   public Map(File f) throws FileNotFoundException {
      Scanner mapReader = new Scanner(f);
      while (mapReader.hasNextInt()) {
         int x1 = mapReader.nextInt();
         int y1 = mapReader.nextInt();
         int x2 = mapReader.nextInt();
         int y2 = mapReader.nextInt();
         //if (x1 == x2) {
            Walls w = new Walls(x1, y1, x2, y2);
            walls.add(w);
         /*}
         else if (y1 == y2) {
            for (double i = Math.min(x1, x2); i < Math.max(x1, x2); i += 0.1) {
               Walls v = new Walls(i, y1, i, y1 + 1);
               walls.add(v);
            }
         }
         else {
            double slope = ((double) Math.max(y1, y2) - Math.min(y1, y2)) / ((double) Math.max(x1, x2) - Math.min(x1, x2));
            for (double i = Math.min(x1, x2); i < Math.max(x1, x2); i += 0.1) {
               Walls u = new Walls(i, Math.min(y1, y2) + (slope * i), i, Math.min(y1, y2) + 1 + (slope * i));
               walls.add(u);
            }
            */
         }
      }
   
   
   // Returns the list of walls generated when this object is constructed.
   public ArrayList<Walls> getWalls() {
      return walls;
   }
}