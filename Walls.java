// Tim Norwood & Nate Adam
// 2022-6-15
// CSE 142 AP CS A
//
// This class represents a wall from two coordinate points, it only does perfectly vertical walls.
// The Map class represents slanted or horizontal walls as many vertical walls close together.

public class Walls {
	private double x1;// the point (x1, y1) is the coordinate of the beggining of the wall
	private double y1;
	private double x2;// the point (x2, y2) is the coordinate of the beggining of the wall
	private double y2;
	private double longerX; //longer x is equielent to whichever x value is larger
	private double shorterX; //shorter x is equivelent to whichever x value is shorter
	private double longerY; //longer y is equielent to whichever y value is larger
	private double shorterY; //shorter y is equivelent to whichever y value is shorter
	
	public Walls(double xx1, double yy1, double xx2, double yy2) {
		x1 = xx1;
		x2 = xx2;
		y1 = yy1;
		y2 = yy2;
		if(xx1 > xx2) {
			longerX = xx1;
			shorterX = xx2;
		} else {
			longerX = xx2;
			shorterX = xx1;
		}
		if(yy1 > yy2) {
			longerY = yy1;
			shorterY = yy2;
		} else {
			longerY = yy2;
			shorterY = yy1;
		}
	}
	
	// Collide method to figure out if a ray collides with a wall
	// takes a ray as a paramater
	// returns -1 if wall and ray do not collide
	// returns the distance to the collision from the beginning of the ray if the ray does collide with the wall
	public double collide(Ray jj) {
		if((x1 == x2) && (y1 == y2)) {
			return -1;
		}
		if((jj.getAngle() == 90.0)) {
			//if the ray is vertical upwards and the wall collides with the ray between
			//the wall's endpoints then return the distance from the observer to the collision
			//if not then return -1
			if(((((y1 - y2)/(x1 - x2)) * (jj.getX() - x1) + y1) > jj.getY()) && (shorterX < jj.getX()) && (longerX > jj.getX())) {
				return Math.sqrt((((y1 - y2)/(x1 - x2)) * (jj.getX() - x2) + y2 - jj.getY()) * (((y1 - y2)/(x1 - x2)) * (jj.getX() - x2) + y2 - jj.getY()));
			}
			return -1;
		}
		if((jj.getAngle() == 270.0)) {
			//if the ray is vertical downwards and the wall collides with the ray between
			//the wall's endpoints then return the distance from the observer to the collision
			//if not then return -1
			if(((((y1 - y2)/(x1 - x2)) * (jj.getX() - x1) + y1) < jj.getY()) && (shorterX < jj.getX()) && (longerX > jj.getX())) {
				return Math.sqrt((((y1 - y2)/(x1 - x2)) * (jj.getX() - x2) + y2 - jj.getY()) * (((y1 - y2)/(x1 - x2)) * (jj.getX() - x2) + y2 - jj.getY()));
			}
			return -1;
		}
		
		// Everything below this calculates if the ray hits a wall when cast at an
      // angle that isn't a multiple of 90.
		
		
		if((0 <= jj.getAngle() && jj.getAngle() < 90) || (270 < jj.getAngle() && jj.getAngle() < 360)) {
			if(x1 == x2) {
				if(x1 > jj.getX()) {
					if((((jj.getSlope() * (x1 - jj.getX())) + jj.getY()) > shorterY) && (((jj.getSlope() * (x1 - jj.getX())) + jj.getY()) < longerY)) {
						return Math.sqrt(((jj.getX() - x1) * (jj.getX() - x1)) + (((jj.getSlope() * (x1 - jj.getX())) + jj.getY() - jj.getY()) * ((jj.getSlope() * (x1 - jj.getX())) + jj.getY() - jj.getY())));
					}
					return -1;
				}
			}
			boolean continuity = (((y1 - y2)/(x1 - x2)) * (x1 - jj.getX()) - y1 + jj.getY() > 0);
			for(double x = jj.getX(); x < longerX + 1; x += 0.01) {
				if(!((((jj.getSlope() * (x - jj.getX())) + jj.getY()) - ((((y1 - y2)/(x1 - x2)) * (x - x1)) + y1) > 0) == continuity)) {
					if(((0 <= jj.getAngle() && jj.getAngle() < 90) || (270 < jj.getAngle() && jj.getAngle() < 360)) && x > jj.getX() && x > shorterX && x < longerX + 0.1) {
						return (Math.sqrt((jj.getX() - x) * (jj.getX() - x) + (((y1 - y2)/(x1 - x2)) * (x - x2) + y2 - jj.getY()) * (((y1 - y2)/(x1 - x2)) * (x - x2) + y2 - jj.getY()))); //(jj.getX() - x) * (jj.getX() - x) + (((y1 - y2)/(x1 - x2)) * (x - x1) + y1 - (jj.getSlope() * (x - jj.getX()) + jj.getY())) * (((y1 - y2)/(x1 - x2)) * (x - x1) + y1 - (jj.getSlope() * (x - jj.getX()) + jj.getY())))); //(jj.getX()) * (jj.getX()) + (jj.getSlope() * (x - jj.getX()) + jj.getY()) * (jj.getSlope() * (x - jj.getX()) + jj.getY())));
					}
				}
			}
		} else {
			if(x1 == x2) {
				if(x1 < jj.getX()) {
					if((((jj.getSlope() * (x2 - jj.getX())) + jj.getY()) > shorterY) && (((jj.getSlope() * (x2 - jj.getX())) + jj.getY()) < longerY)) {
						return Math.sqrt(((jj.getX() - x1) * (jj.getX() - x1)) + (((jj.getSlope() * (x1 - jj.getX())) + jj.getY() - jj.getY()) * ((jj.getSlope() * (x1 - jj.getX())) + jj.getY() - jj.getY())));
					}
					return -1;
				}
			}
			boolean continuity = (((y1 - y2)/(x1 - x2)) * (jj.getX() - x2) - y2 + jj.getY() > 0);
			for(double x = jj.getX(); x > shorterX - 1; x -= 0.01) {
				if(!((((jj.getSlope() * (x - jj.getX())) + jj.getY()) - ((((y1 - y2)/(x1 - x2)) * (x - x2)) + y2) > 0) == continuity)) {
					if((90 < jj.getAngle() && jj.getAngle() < 270) && x < jj.getX() && x > shorterX - 0.1 && x < longerX) {
						return (Math.sqrt((jj.getX() - x) * (jj.getX() - x) + (((y1 - y2)/(x1 - x2)) * (x - x2) + y2 - jj.getY()) * (((y1 - y2)/(x1 - x2)) * (x - x2) + y2 - jj.getY()))); //(jj.getX() - x) * (jj.getX() - x) + (((y1 - y2)/(x1 - x2)) * (x - x1) + y1 - (jj.getSlope() * (x - jj.getX()) + jj.getY())) * (((y1 - y2)/(x1 - x2)) * (x - x1) + y1 - (jj.getSlope() * (x - jj.getX()) + jj.getY())))); //(jj.getX()) * (jj.getX()) + (jj.getSlope() * (x - jj.getX()) + jj.getY()) * (jj.getSlope() * (x - jj.getX()) + jj.getY())));
					}
				}
			}
		}
		return -1;
	}
}