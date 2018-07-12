
public class ClearButton extends Button {
    private PApplet processing;//instance fields of class ClearButton
    
    public ClearButton(float x, float y, PApplet processing) { //initializes ClearButton
        super("Clear Room", x, y, processing);
        this.processing = processing;
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
