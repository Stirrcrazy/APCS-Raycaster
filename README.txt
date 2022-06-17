README:

To run this program, you will need an IDE that can compile java code. Here is a link explaining how to use the one we used:
https://courses.cs.washington.edu/courses/cse14x/software2-openJDK/

Download the five java files from this project, place them all in a single folder on your computer. Use the IDE, jGrasp,
to open all five files and compile them with the green plus button in the upper left. (Counting DrawingPanel).

Place a text document in your C:/ drive titled "maptest" or copy the one in this project into your C:/ drive.

If you choose to make your own map, or wish to edit ours, you can create a new wall by listing four numbers. The first two
numbers represent the coordinate point on the map the wall starts at, the last two numbers represent the point the wall
ends at. You can make as many walls as you want, just make sure at the end that your total number of numbers in the file
is divisible by four. Note that the Camera starts at the point (50,50).

When you're ready to see the map, open the Camera.java file and press the Run button (the little red running man in jgrasp)
and click on the console in jGrasp. To move the Camera, press W for forward, A for left, S for backward, and D for right.
To rotate the camera, press Z to turn left, X to turn around, and C to turn right. Hit enter after every input to see
the view update.

If you wish to edit the resolution of the window, edit the HR variable in the Camera class to change the horizontal
resolution, or the VR variable for vertical resolution. If you wish to increase the resolution of the non-vertical walls,
go to lines 34 and 27 of the Map class. Change the number 0.1 in both lines to whatever you want. Higher numbers decrease
resolution, lower numbers increase it. The commented out section in Camera governs the walls darkening at a distance,
and is intended to stop far away walls from drawing in front of closer ones, but currently isn't functional and slows down
the program far too much.