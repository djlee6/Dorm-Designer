public class Furniture implements DormGUI {
    
    private PApplet processing; //instance fields of class Bed
    private PImage image;
    private float[] position;
    private boolean isDragging;
    private int rotations;

    // initializes the fields of a new bed object positioned in the center of the display
    public Furniture(String furnitureType, PApplet processing) {
        
        this.processing = processing;
        
        position = new float[2];
        
        position[0] = processing.width/2;
        position[1] = processing.height/2;
        
        image = processing.loadImage("images/" + furnitureType + ".png");
        
        rotations = 0;
    }
    // draws this bed at its current position
    public void update() {
        
        processing.image(image, position[0], position[1], rotations*PApplet.PI/2);
        
        if(isDragging) {
            position[0] = processing.mouseX;
            position[1] = processing.mouseY;//sets the position of the bed to update
        }
    }
    
    // used to start dragging the bed, when the mouse is over this bed when it is pressed
    public void mouseDown(Furniture[] furniture) {
        
        isDragging = isMouseOver();
        
        if(isDragging) {
            update();
        }
    }
    // used to indicate that the bed is no longer being dragged
    public void mouseUp() {
        
        isDragging = false;
    }
    // helper method to determine whether the mouse is currently over this bed
    public boolean isMouseOver() {
        
        if(rotations % 2 == 0) { //if the bed image is rotated an even number of times
            if((processing.mouseX > (position[0] - image.width/2) //if the mouse is within the bed in the horizontal orientation
                && processing.mouseX < (position[0] + image.width/2))
                && (processing.mouseY > (position[1] - image.height/2)
                && processing.mouseY < (position[1] + image.height/2))) {
                    return true; //mouse is over bed
            }
            else { //mouse is not over bed
                return false;
            }
        }
        if(rotations % 2 == 1) { //if the bed image is rotated an odd number of times
            if((processing.mouseX > (position[0] - image.height/2) //if the mouse is within the bed in the vertical orientation
                && processing.mouseX < (position[0] + image.height/2))
                && processing.mouseY > (position[1] - image.width/2)
                && processing.mouseY < (position[1] + image.width/2)) {
                    return true; //mouse is over bed
            }
            else { //mouse is not over bed
                return false;
            }
        }
        else {
            return false;
        }
    }
    //method to rotate the image 
    public void rotate() {
        rotations++;
        processing.image(image, position[0], position[1], rotations*PApplet.PI/2);
    }
}