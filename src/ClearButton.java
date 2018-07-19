public class ClearButton extends Button {
    
    public ClearButton(float x, float y, PApplet processing) { //initializes ClearButton
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