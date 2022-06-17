// Tim Norwood & Nate Adam
// 2022-6-15
// CSE 142 AP CS A
//
// This class represents a ray sent out from the Camera class with a coordinate point and an angle.

public class Ray {

   private double x; //the x coordinate of the observer
   private double y; //the y coordinate of the observer
   private double angle; //the direction the observer is looking
   
   public Ray(double xi, double yi, double ai) {
      x = xi;
      y = yi;
      angle = ai;
   }
   
	//returns the slope of the ray
   public double getSlope() {
      /*if (((int) angle) % 90 == 0 && ((int) angle) < 360) {
         angle -= 0.001;
      }*/
      return Math.tan(Math.toRadians(angle));
   }
   
	//returns the angle of the ray
   public double getAngle() {
      return angle;
   }
   
	//returns the x coordinate of the observer
   public double getX() {
      return x;
   }
   
	//returns the x coordinate of the observer
   public double getY() {
      return y;
   }
}