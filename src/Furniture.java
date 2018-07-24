/* Furniture class is a generic class that can represent any furniture object to be placed
 * in the dorm room. Each Furniture object is a DormGUI object.
 */

public class Furniture implements DormGUI {
    private PApplet processing; //instance fields of class Furniture
    private PImage image;
    private float[] position;
    private boolean isDragging;
    private int rotations;
    private String furnitureType;

    /* This constructor creates a new furniture object and centers it on the screen.
     * @param furnitureType
     * @param processing
     */
    public Furniture(String furnitureType, PApplet processing) {
        this.furnitureType = furnitureType;
        this.processing = processing;
        
        position = new float[2];
        
        position[0] = processing.width/2;
        position[1] = processing.height/2;
        
        image = processing.loadImage("images/" + furnitureType + ".png");
        
        rotations = 0;
    }
    // draws this furniture at its current position
    public void update() {
        processing.image(image, position[0], position[1], rotations*PApplet.PI/2);
        
        if(isDragging) {
            position[0] = processing.mouseX;
            position[1] = processing.mouseY;//sets the position of the furniture to update
        }
    }
    
    /* Method used to start dragging the furniture, when the mouse is over this furniture object 
     * and it is pressed.
     * @param furniture
     */
    public void mouseDown(Furniture[] furniture) {
        isDragging = isMouseOver();
        
        if(isDragging) {
            update();
        }
    }
    // used to indicate that the furniture is no longer being dragged
    public void mouseUp() {
        isDragging = false;
    }
    // helper method to determine whether the mouse is currently over this furniture object
    public boolean isMouseOver() {
        
        if(rotations % 2 == 0) { //if the furniture image is rotated an even number of times
            if((processing.mouseX > (position[0] - image.width/2) //if the mouse is within the furniture in the horizontal orientation
                && processing.mouseX < (position[0] + image.width/2))
                && (processing.mouseY > (position[1] - image.height/2)
                && processing.mouseY < (position[1] + image.height/2))) {
                    return true; //mouse is over furniture
            }
            else { //mouse is not over furniture
                return false;
            }
        }
        if(rotations % 2 == 1) { //if the furniture image is rotated an odd number of times
            if((processing.mouseX > (position[0] - image.height/2) //if the mouse is within the furniture in the vertical orientation
                && processing.mouseX < (position[0] + image.height/2))
                && processing.mouseY > (position[1] - image.width/2)
                && processing.mouseY < (position[1] + image.width/2)) {
                    return true; //mouse is over furniture
            }
            else { //mouse is not over furniture
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
    public String getVals() { //method returns the type and position of the furniture objects
    	String data;
    
    	data= furnitureType.toLowerCase() + ":" + String.valueOf(position[0]) + 
    			"," + String.valueOf(position[1]) + "," + String.valueOf(rotations);

    
    return data;
    }
    /*Constructor used when loading furniture from a saved file.
     * @param type
     * @param x, y positions
     * @param rotations
     * @param processing
     */
    public Furniture(String type, float x, float y,int r, PApplet processing) {
        this.processing = processing;
        this.furnitureType = type;
       
        position = new float[2];
        
        this.position[0] = x;
        this.position[1] = y;
        this.rotations = r;

        
        image = processing.loadImage("images/" + type + ".png");
        
        processing.image(image, x, y, r*PApplet.PI/2);
        
       
    }
}
    
