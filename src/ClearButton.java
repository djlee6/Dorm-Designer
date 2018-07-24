/*ClearButton class extends the button class, specifically displaying a button to clear the
 * room. The class will return a boolean variable declaring if the button was pressed or not.
 * The place where the button is updated is where the furniture is cleared from the room.
 */
public class ClearButton extends Button {
    
    /*This constructor of the ClearButton class declares the position of the button and 
     * draws the button with label using the base class Button.
     * @param x, y position of button
     * @param procesing
     */
    public ClearButton(float x, float y, PApplet processing) {
        super(x, y, processing);
        this.processing = processing;
        super.label = "Clear Room";
    }
    
    public void update() { //updates ClearButton
        super.update();
    }
    
    public boolean mouseDown() { //method checks if the button was clicked
        if(super.isMouseOver()) {
            return true;
        }
        else {
            return false;
        }
    } 
}