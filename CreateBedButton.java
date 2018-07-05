
public class CreateBedButton {
    
    private static final int WIDTH = 96; //instance fields of class CreateBedButton
    private static final int HEIGHT = 32;

    private PApplet processing;
    private float[] position;
    private String label;
    

    public CreateBedButton(float x, float y, PApplet processing) {
        this.processing = processing;
        
        position = new float[6];
        
        position[0] = x - WIDTH/2;
        position[1] = y - HEIGHT/2;
        position[2] = x + WIDTH/2;
        position[3] = y + HEIGHT/2;
        position[4] = x;
        position[5] = y;
        
        label = "Create Bed";
            
        processing.rect(position[0], position[1], position[2], position[3]);
        
    }
    public void update() { 
        
        if(isMouseOver()) {
            processing.fill(100);
        }
        else {
            processing.fill(200);
        }
        processing.rect(position[0], position[1], position[2], position[3]);
        
        processing.fill(0);
        
        processing.text(label, position[4], position[5]);
    }
    public Bed mouseDown() {
        
        if(isMouseOver()) {
            return new Bed(processing);
        }
        else {
            return null;
        }
    } 
    // After step 6, this method will instead return Furniture   
    public boolean isMouseOver() {
        
        if((processing.mouseX > position[0] && processing.mouseX < position[2])
            && (processing.mouseY > position[1] && processing.mouseY < position[3])) {
            return true;
        }
        else {
            return false;
        }
    }
}
