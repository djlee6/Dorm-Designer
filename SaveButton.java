
public class SaveButton extends Button {
    private PApplet processing;//instance fields of class SaveButton

    public SaveButton(float x, float y, PApplet processing){ 
        super("Save Room", x, y, processing);
        this.processing = processing;
    }
    
    public void update() { 
        super.update();
    }
    
    @Override
    public void mouseDown(Furniture[] furniture) {
        if (isMouseOver())
            System.out.println("Save Button pressed");
    }
}
