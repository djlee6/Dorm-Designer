/* CreateFurnitureButton extends the base class button and is generic to display a button
 * for all furniture types on the top of the screen.
 */

public class CreateFurnitureButton extends Button {
    private String type; //instance fields of class CreateFurnitureButton
    
    /* This constructor contains the position and type of furniture button to be created.
     * @param furniture type
     * @param x, y positions of button
     * @param processing
     */
    public CreateFurnitureButton(String type, float x, float y, PApplet processing) {
        super(x, y, processing);
        this.type = type;
        super.label = "Create" + type;
    }
    public void update() { 
        super.update();
    }
    public Furniture mouseDown() { //method checks if the button was clicked
        if(super.isMouseOver()) { //if the user clicks on the button a new bed is declared
            return new Furniture(type.toLowerCase(), processing);
        }
        else {
            return null;
        }
    }
}