import java.util.ArrayList;

//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           DormDesigner3000
// Files:           DormDesigner.jar
// Course:          300, Summer, and 2018
//
// Author:          Danny Lee
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
    private int dragBedIndex;
    private ArrayList<DormGUI> guiObjects;
    private CreateFurnitureButton button;
    /**
    * call back method called by the Utility class when the mouse button is pressed
    */
    
    public static void main(String[] args) {
        
        Utility.startApplication(); //begins program showing the dorm room
    }
    
    public Main(PApplet processing) { //initializes Main class instance fields
        
        this.processing = processing;
        
        backgroundImage = processing.loadImage("images/background.png");
                
        dragBedIndex = -1;
        
        guiObjects = new ArrayList<DormGUI>();
        
        guiObjects.add(new CreateFurnitureButton("Bed", 50, 24, processing)); //adds gui objects to the array list
        guiObjects.add(new CreateFurnitureButton("Sofa", 150, 24, processing));
        guiObjects.add(new CreateFurnitureButton("Dresser", 250, 24, processing));
        guiObjects.add(new CreateFurnitureButton("Desk", 350, 24, processing));
        guiObjects.add(new CreateFurnitureButton("Sink", 450, 24, processing));
        guiObjects.add(new ClearButton(550, 24, processing));
        guiObjects.add(new SaveButton(650, 24, processing));
        guiObjects.add(new LoadButton(750, 24, processing));
      
        
    }
    
    public void update() { //update method displays current furniture positions
        
        processing.background(95,158,160); //sets background color of room
        
        processing.image(backgroundImage, processing.width/2, processing.height/2); //places the background image in center of window
        
        for( int i = 0; i < guiObjects.size(); i++) { //updates every guiObject
            guiObjects.get(i).update();
        }
    }
    
    
    public void mouseDown() { //if the mouse is clicked this method determines which object the mouse is over and which mouseDown methods to call
        Furniture[] furniture = extractFurnitureFromGUIObjects();
        
        for(int i=0;i<guiObjects.size();i++) {
            if(guiObjects.get(i).isMouseOver()) {
                if(guiObjects.get(i) instanceof ClearButton) { //if the mouse is over ClearButton
                    guiObjects.get(i).mouseDown(furniture); 
                    for(int q = 0; q < furniture.length; q++) { //clears furniture array
                        furniture[q] = null;
                    }
                }
                if(guiObjects.get(i) instanceof LoadButton) { //if the mouse is over LoadButton
                    guiObjects.get(i).mouseDown(furniture);
                    furniture = LoadButton.getFurniture(); //loads and replaces furniture objects
                    replaceFurnitureInGUIObjects(furniture);
                    return;
                }
                if(guiObjects.get(i) instanceof SaveButton) { //if the mouse is over SaveButton
                    guiObjects.get(i).mouseDown(furniture); //dorm contents are saved
                    return;
                }
                if(guiObjects.get(i) instanceof CreateFurnitureButton) { //if the mouse is over a CreateFurnitureButton
                    button = (CreateFurnitureButton)guiObjects.get(i);
                    for(int h = 0; h < furniture.length; h++) { 
                        if(furniture[h] == null) { 
                            furniture[h] = button.mouseDown(); //creates new furniture of the type that was clicked
                            break;
                        }
                    }           
                }
                if(guiObjects.get(i) instanceof Furniture) { //if the mouse is over a furniture object it can move it
                    guiObjects.get(i).mouseDown(furniture);
                    return;
                }
            }
            else { // if the mouse isn't over anything the loop should continue to iterate
                continue;
            }
            replaceFurnitureInGUIObjects(furniture);
        }
    } 
    
    public void mouseUp() { //if the mouse is lifted up the furniture objects cannot be moved
        for(int i = 0; i < guiObjects.size(); i++) {
            guiObjects.get(i).mouseUp();
        }
    }
    
    public void keyPressed() { //allows for user key input to delete or rotate a furniture object
        Furniture[] furniture = extractFurnitureFromGUIObjects();

            for(int i = 0; i < furniture.length; i++) {
                if(furniture[i].isMouseOver()) { //if a mouse is over the furniture object it sets the reference to null
                    if(processing.key == 'd' || processing.key == 'D') { //if a d is pressed it checks all non null furniture objects if the mouse if over it
                        furniture[i] = null;
                        replaceFurnitureInGUIObjects(furniture);
                    }
                    if(processing.key == 'r' || processing.key == 'R') { //if a r is pressed it checks which furniture object it is over and rotates it
                        furniture[i].rotate();
                        replaceFurnitureInGUIObjects(furniture);
                    }
                }
                if(furniture[i + 1] == null) {
                	return;
                }
                else {
                    continue;
                }
            }
        }
 // Max number of furniture that LoadButton will be allowed to load at once.    
    private final static int MAX_LOAD_FURNITURE = 100;        
    /**
     * This method creates a new Furniture[] for the old mouseDown() methods
     * to make use of.  It does so by copying all Furniture references from
     * this.guiObjects into a temporary array of size MAX_LOAD_FURNITURE.
     * @return that array of Furniture references.
     */
    private Furniture[] extractFurnitureFromGUIObjects() {
        Furniture[] furniture = new Furniture[MAX_LOAD_FURNITURE];
        int nextFreeIndex = 0;
        for(int i=0;i<guiObjects.size() && nextFreeIndex < furniture.length;i++)
            if(guiObjects.get(i) instanceof Furniture)
                furniture[nextFreeIndex++] = (Furniture)guiObjects.get(i);        
        return furniture;        
    }    
    /**
     * This method first removes all Furniture references from this.guiObjects,
     * and then adds back in all of the non-null references from it's parameter.
     * @param furniture contains the only furniture that will be left in 
     *   this.guiObjects after this method is invoked (null references ignored).
     */
    private void replaceFurnitureInGUIObjects(Furniture[] furniture) {
        for(int i=0;i<guiObjects.size();i++)
            if(guiObjects.get(i) instanceof Furniture)
                guiObjects.remove(i--);
        for(int i=0;i<furniture.length;i++)
            if(furniture[i] != null)
                guiObjects.add(furniture[i]);
    }
}