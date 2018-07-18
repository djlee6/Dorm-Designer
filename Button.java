public class Button implements DormGUI {
    
    private static final int WIDTH = 96;
    private static final int HEIGHT = 32;
    protected PApplet processing;
    private float[] position;
    protected String label;

    public Button(String string, float x, float y, PApplet processing) {
          this.processing = processing;
            
            position = new float[6];
            
            label = string;
            position[0] = x - WIDTH/2; //array contains the sides of the rectangle and the center point
            position[1] = y - HEIGHT/2;
            position[2] = x + WIDTH/2;
            position[3] = y + HEIGHT/2;
            position[4] = x;
            position[5] = y;
                
            processing.rect(position[0], position[1], position[2], position[3]); //draws a rectangle
    }
    public void update() {
        if(isMouseOver()) { //if the mouse is over the button it is a darker grey
            processing.fill(100);
        }
        else { //lighter grey
            processing.fill(200);
        }
        processing.rect(position[0], position[1], position[2], position[3]);
        
        processing.fill(0); //color of the label
        
        processing.text(label, position[4], position[5]); //drawing of the label
    }
    
    public boolean isMouseOver() {
          if((processing.mouseX > position[0] && processing.mouseX < position[2]) //checks if the mouse is inside the parameters of the button
                    && (processing.mouseY > position[1] && processing.mouseY < position[3])) {
                    return true;
                }
                else {
                    return false;
                }
    }
    
    @Override
    public void mouseDown(Furniture[] furniture){
         if(isMouseOver())  {
                System.out.println("Room Cleared.");
         }
    }
    @Override
    public void mouseUp() {}
}
