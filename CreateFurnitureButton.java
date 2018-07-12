
public class CreateFurnitureButton extends Button {

    private PApplet processing;//instance fields of class CreateFurnitureButton
    private String type;

    public CreateFurnitureButton(String type, float x, float y, PApplet processing) { //CreateBedButton constructor to draw the box
        
        super("Create " + type, x, y, processing);
        
        this.processing = processing;
        this.type = type;
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
