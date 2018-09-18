# CEG 4110_HW1

HW1 is a program that allows the user to do 2 basic functions.  The first is to enter some text and change the color of the text to anything at random.  The second function is a free canvas to draw anything on using any color.  The user can also clear and save the canvas.


### Tech

HW1 uses one open source project to work properly:

* [Chroma] - Color Picker for the drawing functionality.

And of course Hw1 itself is open source with a [public repository]
 on GitHub.

### Installation

Hw1 can be either be run through the APK on any system or download the source code
and opened in an IDE.  I used Android Studio and the programs minimum OS requirement is 

For running in an IDE do the following

- Install the dependencies
    - In your build.gradle file insert the following code
        - implementation 'me.priyesh:chroma:1.0.2'

    - Then in your mainActivity.java file insert the following code
        - new ChromaDialog.Builder()
    .initialColor(Color.GREEN)
    .colorMode(ColorMode.RGB) // There's also ARGB and HSV
    .onColorSelected(color -> /* do your stuff */)
    .create()
    .show(getSupportFragmentManager(), "ChromaDialog");

# Structure

DrawingActivity:
This class will do multiple things.  For starters if the user presses the color putton it will 
load Chromas open source code color picker.  This will allow the user to change the color of their drawing.  
Another functionality is if the user presses the erase button it will completely clear the canvas creating a 
blank canvas for the user.  If the user presses the save button this will take the bitmap of the canvas and 
save the file to local storage on the phone as a JPEG.  Another functionality of this program is it will need to 
ask the user permission to access local storage.  

ColorChanger:
This class will allow the user to type in the text space provided and then press the change color button to randomize the
color of the text.  The color is generated at random by the program and will display the necessary RGB values of the color and its hex values.

Canvas2:
This class is the canvas that the user will draw on using thhe DrawingActivity class.  This has multiple methods in it 
that pertain to the overall functionality of drawing.  There is a method that will simply set the color for the user and this is what
is used when the user uses Chroma to change the color. The onSizeChanged method will create a bitmap or a maping of bits for the user to draw on and puts the bitmap
into the canvas.  The methond onDraw will take in the users canvas and drawpaths and change the color or set the color. 
There are 3 methods that dictate the moving function for the users finger and path.  The startTouch,moveTouch, and upTouch.
startTouch will take the current path and move it to the coordinates x and y.  The upTouch method will take a path and draw a line to positions of
the member variables x and y.  The onTouchEvent method will take thhe event and get the action and determine if it is a down, move or up action.  If this
is a down action then you will call startTouch and invalidate which calls the onDraw method also.  The move action will call the moveTouch method and invalidate.  
The up action will call the uptouch and invalidate.  The final method is  a getter for the bitmap.
 
 # Examples
 ![1](https://github.com/Bhulbert2096/Ceg4110_HW1/blob/master/HW1_Hulbert/1.PNG)
 ![1](https://github.com/Bhulbert2096/Ceg4110_HW1/blob/master/HW1_Hulbert/2.PNG)
 ![1](https://github.com/Bhulbert2096/Ceg4110_HW1/blob/master/HW1_Hulbert/3.PNG)
 
 
# License
This project and all its dependencies are licensed under MIT
