
public class LoadButton extends Button {
    private PApplet processing;//instance fields of class LoadButton
    
    public LoadButton(float x, float y, PApplet processing){ 
        super("Load Room", x, y, processing);
        this.processing = processing;
    }
    
    public void update() {
        super.update();
    }
    
    @Override
    public void mouseDown(Furniture[] furniture) {
        if (isMouseOver())
            System.out.println("Load Button pressed");
    }
}
