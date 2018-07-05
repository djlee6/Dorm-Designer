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
    private Furniture furniture[];
    private int dragBedIndex;
    private CreateBedButton bedButton;
    private CreateSofaButton sofaButton;
    
    public static void main(String[] args) {
        
        Utility.startApplication(); //begins program showing the dorm room
    }
    
    public Main(PApplet processing) {
        
        this.processing = processing;
        
        backgroundImage = processing.loadImage("images/background.png"); //loads the background image of the room
        
        bedImage = processing.loadImage("images/bed.png"); //sets bedImage reference to the bed picture
                
        dragBedIndex = -1;
        
        furniture = new Furniture[6];
        
        bedButton = new CreateBedButton(50, 24, processing);
        
        sofaButton = new CreateSofaButton(150, 24, processing);
    }
    
    public void update() {
        
        processing.background(95,158,160); //sets background color of room
        
        processing.image(backgroundImage, processing.width/2, processing.height/2); //places the background image in center of window
        
        bedButton.update();
        
        sofaButton.update();
        
        for(int i = 0; i < furniture.length; i++) { //updates every bed object to a new position
            if(dragBedIndex >= 0) {
                if(furniture[dragBedIndex] != null) {
                    furniture[dragBedIndex].update();
                }
            }
            if(furniture[i] != null) {
                furniture[i].update();
            }
        }
    }
    
    public void mouseDown() {
        
        for(int i = 0; i < furniture.length; i++) { //checks every bed object if the mouse is down
            if(furniture[i] == null) {
                if(bedButton.isMouseOver()) {
                    furniture[i] = bedButton.mouseDown();
                    return;
                }
                if(sofaButton.isMouseOver()) {
                    furniture[i] = sofaButton.mouseDown();
                    return;
                }
            }
            if(furniture[i] != null) {
                furniture[i].mouseDown();
                dragBedIndex = i;
            }
        }
    }
    
    public void mouseUp() {
        
        for(int i = 0; i < furniture.length; i++) { //checks every bed object if the mouse is down
            if(furniture[i] != null) {
                dragBedIndex = -1;
                furniture[i].mouseUp();
            }
        }
    }
    
    public void keyPressed() {
        
       /* if(processing.key == 'b' || processing.key == 'B') { //if a b is pressed it creates a new bed object set to the center of the screen
            for(int i = 0; i < beds.length; i++) {
                if(beds[i] == null) {
                    beds[i] = new Bed(processing);
                    return;
                }
            }
        }*/
        if(processing.key == 'd' || processing.key == 'D') { //if a d is pressed it checks all non null bed objects if the mouse if over it
            for(int i = 0; i < furniture.length; i++) {
                if(furniture[i] != null) {
                    if(furniture[i].isMouseOver()) { //if a mouse is over the bed object it sets the reference to null
                        furniture[i] = null;
                        return;
                    }
                }
            }
        }
        if(processing.key == 'r' || processing.key == 'R') {
            for(int i = 0; i < furniture.length; i++) {
                if(furniture[i] != null) {
                    if(furniture[i].isMouseOver()) {
                        furniture[i].rotate();
                        return;
                    }
                }
            }
        }
    }
}