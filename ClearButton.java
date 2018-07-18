
public class ClearButton extends Button {
    
    public ClearButton(float x, float y, PApplet processing) { //initializes ClearButton
        super("Clear Room", x, y, processing);
        this.processing = processing;
    }
    
    public void update() { //updates ClearButton
        super.update();
    }
    
    public boolean mouseDown() { //method checks if the button was clicked
        if(isMouseOver()) {
            return true;
        }
        else {
            return false;
        }
    }
    public void mouseUp() {
        
    }
    public boolean isMouseOver() {
        return super.isMouseOver();
    }
}
