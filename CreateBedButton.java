
public class CreateBedButton {
    
    private static final int WIDTH = 96; //instance fields of class CreateBedButton
    private static final int HEIGHT = 32;

    private PApplet processing;
    private float[] position;
    private String label;

    public CreateBedButton(float x, float y, PApplet processing) {
        
    }
    public void update() { 
        
    }
    public Bed mouseDown() {
        boolean isOver = isMouseOver();
        if(isOver) {
            return new Bed(processing);
        }
        else {
            return null;
        }
    } 
    // After step 6, this method will instead return Furniture   
    public boolean isMouseOver() {
        
    }
}
