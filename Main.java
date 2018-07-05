//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DormDesigner2000
// Files:           DormDesigner.jar
// Course:          300, Summer, and 2018
//
// Author:          Daniel Lee
// Email:           djlee6@wisc.edu
// Lecturer's Name: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Jake Prine
// Partner Email:   jprine@wisc.edu
// Partner Lecturer's Name: Mouna Kacen
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   _X_ Write-up states that pair programming is allowed for this assignment.
//   _X_ We have both read and understand the course Pair Programming Policy.
//   _X_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates, 
// strangers, and others do.  If you received no outside help from either type
//  of source, then please explicitly indicate NONE.
//
// Persons:         (identify each person and describe their help in detail)
// Online Sources:  (identify each URL and describe their assistance in detail)
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class Main {
    
    private PApplet processing; //instance fields of class main
    private PImage backgroundImage;
    private PImage bedImage;
    private Bed beds[];
    private int dragBedIndex;
    
    public static void main(String[] args) {
        
        Utility.startApplication(); //begins program showing the dorm room
    }
    
    public Main(PApplet processing) {
        
        this.processing = processing;
        
        backgroundImage = processing.loadImage("images/background.png"); //loads the background image of the room
        
        bedImage = processing.loadImage("images/bed.png"); //sets bedImage reference to the bed picture
                
        dragBedIndex = -1;
        
        beds = new Bed[4];
    }
    
    public void update() {
        
        processing.background(95,158,160); //sets background color of room
        
        processing.image(backgroundImage, processing.width/2, processing.height/2); //places the background image in center of window
        
        for(int i = 0; i < beds.length; i++) { //updates every bed object to a new position
            if(dragBedIndex >= 0) {
                if(beds[dragBedIndex] != null) {
                    beds[dragBedIndex].update();
                }
            }
        }
        for(int i = 0; i < beds.length; i++) {
            if(beds[i] != null) {
                processing.image(bedImage, beds[i].getPosition(0), beds[i].getPosition(1) , beds[i].getRotations()*PApplet.PI/2);
            }
        }
    }
    
    public void mouseDown() {
        
        for(int i = 0; i < beds.length; i++) { //checks every bed object if the mouse is down
            if(beds[i] != null) {
                dragBedIndex = i;
                beds[i].mouseDown();
            }
        }
    }
    
    public void mouseUp() {
        
        for(int i = 0; i < beds.length; i++) { //checks every bed object if the mouse is down
            if(beds[i] != null) {
                dragBedIndex = -1;
                beds[i].mouseUp();
            }
        }
    }
    
    public void keyPressed() {
        
        if(processing.key == 'b' || processing.key == 'B') { //if a b is pressed it creates a new bed object set to the center of the screen
            for(int i = 0; i < beds.length; i++) {
                if(beds[i] == null) {
                    beds[i] = new Bed(processing);
                    return;
                }
            }
        }
        if(processing.key == 'd' || processing.key == 'D') { //if a d is pressed it checks all non null bed objects if the mouse if over it
            for(int i = 0; i < beds.length; i++) {
                if(beds[i] != null) {
                    boolean isOver = beds[i].isMouseOver();
                    if(isOver) { //if a mouse is over the bed object it sets the reference to null
                        beds[i] = null;
                        return;
                    }
                }
            }
        }
        if(processing.key == 'r' || processing.key == 'R') {
            for(int i = 0; i < beds.length; i++) {
                if(beds[i] != null) {
                    boolean isOver = beds[i].isMouseOver();
                    if(isOver) {
                        beds[i].rotate();
                        return;
                    }
                }
            }
        }
    }
}