// Tim Norwood & Nate Adam
// 2022-6-13
// AP CS A
//
// This class makes walls from a class, takes input from a player, and draws a frame on the screen
// every time the player makes input it redraws the frame.

import java.awt.*;
import javax.swing.JFrame;
import java.lang.Math.*;
import java.util.*;
import java.io.*;

public class Camera {

   public static final int HR = 600; // horizontal resolution of screen
   public static final int VR = 600; // vartical resolution of screen
   private static ArrayList<Walls> w; // an arraylist 
   private static int darken = 0;
	
   public static void main(String[] args) throws FileNotFoundException {
      File f = new File("maptest.txt");
      Map m = new Map(f);
      w = m.getWalls();
      System.out.println(w);
      DrawingPanel p = new DrawingPanel(HR, VR);
      Graphics g = p.getGraphics();
      Scanner console = new Scanner(System.in);
      double x = 50;
      double y = 50;
      double cameraAngle = 0;
      while (true) {
         g.setColor(Color.WHITE);
         g.fillRect(0, 0, HR, VR);
         //g.setColor(Color.RED); PASS DARKEN TO MAIN METHOD TO SET COLOR HERE OR MOVE SETCOLOR OUTSIDE IF STATEMENT IN DRAWFRAME
         drawFrame(x, y, cameraAngle, g);
         double[] newPos = move(x, y, cameraAngle, console);
         x = newPos[0];
         y = newPos[1];
         cameraAngle = newPos[2];
         cameraAngle = cameraAngle % 360;
      }
   }
   
	// Call move method to move the character
	// input the x and y coordinate of the player, the camera angle, and the player input
	// checks for player input and adjusts the coordinates and angle accordingly
	// returns a double array of the x coordinate, y coordinate, and angle of the player
   public static double[] move(double x, double y, double cameraAngle, Scanner console) {
      char direction = console.next().charAt(0);
		double oldCam = cameraAngle;
      if (direction == 'w') {
         y += 10 * Math.sin(Math.toRadians(cameraAngle));
         x += 10 * Math.cos(Math.toRadians(cameraAngle));
      }
      if (direction == 's') {
         y -= 10 * Math.sin(Math.toRadians(cameraAngle));
         x -= 10 * Math.cos(Math.toRadians(cameraAngle));
      }
      if (direction == 'a') {
         cameraAngle += 90;
         if (cameraAngle > 360) {
            cameraAngle -= 360;
         }
         y += 10 * Math.sin(Math.toRadians(cameraAngle));
         x += 10 * Math.cos(Math.toRadians(cameraAngle));
      }
      if (direction == 'd') {
         cameraAngle -= 90;
         if (cameraAngle < 0) {
            cameraAngle += 360;
         }
         y += 10 * Math.sin(Math.toRadians(cameraAngle));
         x += 10 * Math.cos(Math.toRadians(cameraAngle));
      }
		cameraAngle = oldCam;
		if (direction == 'c') {
			cameraAngle = (cameraAngle + 315) % 360;
		}
		if (direction == 'x') {
			cameraAngle = (cameraAngle + 180) % 360;
		}
		if (direction == 'z') {
			cameraAngle = (cameraAngle + 45) % 360;
		}
      double[] arr = {x, y, cameraAngle};
      return arr;
   }
   
	// call drawframe to draw the frame on the screen
	// takes paramaters x and y coordinates of the player, the camera angle, and the drawingpanel graphics
	// draws a frame from the perspective of the player
   public static void drawFrame(double x, double y, double cameraAngle, Graphics g) {
      int closestwall = 0;
      double shortestdist;
      int count = 50;
      Color c = new Color(255 - (darken), 0, 0);
      g.setColor(c);
      for (int i = 0; i <= HR; i += 5) {
         double angle = cameraAngle + 45;
         angle = angle - (i * (90.0 / HR));
         if (angle >= 360) {
            angle -= 360;
         }
         if (angle <= 0) {
            angle += 360;
         }
         Ray r = new Ray(x, y, angle);
         //for (int j = 0; j < w.size(); j++) {
            //if (w.get(j).collide(r) != -1) {
               //for (int k = i; k < i + 20; k++) {
                  //g.drawLine(k, (int) ((VR / 2) + (Math.toDegrees(Math.atan(50 / w.get(j).collide(r))) / 360) * VR), k, (int) ((VR / 2) - (Math.toDegrees(Math.atan(50 / w.get(j).collide(r))) / 360) * VR));
               //}
            //}
         //}
         /* Unused code that darkens the walls based on their distance from the camera. Far too performance intensive.*/
         if (count == 50) {
            for (int j = 0; j < w.size(); j++) {
               shortestdist = w.get(0).collide(r);
               int k = 0;
               while (shortestdist == -1 && k < (w.size() - 1)) {
                  k++;
                  shortestdist = w.get(k).collide(r);
               }
               closestwall = k;
               if (w.get(j).collide(r) < shortestdist && w.get(j).collide(r) != -1) {
                  shortestdist = w.get(j).collide(r);
                  closestwall = j;
               }
            }
         }
         if (closestwall < w.size()) { 
            if (w.get(closestwall).collide(r) != -1 && count == 50) {
               darken = (int) w.get(closestwall).collide(r);
               if (darken > 255) {
                  darken = 255;
               }
               count -= 50;
               c = new Color(255 - (darken), 0, 0);
               g.setColor(c);
            }
            for (int j = 0; j < w.size(); j++) {
               if (w.get(j).collide(r) != -1) {
                  for (int k = i; k < i + 20; k++) {
                     g.drawLine(k, (int) ((VR / 2) + (Math.toDegrees(Math.atan(50 / w.get(j).collide(r))) / 360) * VR), k, (int) ((VR / 2) - (Math.toDegrees(Math.atan(50 / w.get(j).collide(r))) / 360) * VR));
                  }
               }
            }
            count += 5;
            //g.drawLine(i, (int) ((VR / 2) + (Math.toDegrees(Math.atan(50 / w.get(closestwall).collide(r))) / 360) * VR), i, (int) ((VR / 2) - (Math.toDegrees(Math.atan(50 / w.get(closestwall).collide(r))) / 360) * VR));
         }
      }
   }
   
}