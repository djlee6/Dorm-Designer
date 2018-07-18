import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class SaveButton extends Button {

    public SaveButton(float x, float y, PApplet processing){ 
        super("Save Room", x, y, processing);
    }
    
    public void update() { 
        super.update();
    }
    
    @Override
    public void mouseDown(Furniture[] furniture) {
        if (isMouseOver()) {
            if (furniture != null) {
                try {
                    saveRoom(furniture, "RoomData");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public void mouseUp() {}
    
    public boolean isMouseOver() {
        return super.isMouseOver();
    }
    private void saveRoom(Furniture[] furniture, String filename)  throws IOException {
        FileOutputStream outputDorm = new FileOutputStream(filename + ".ddd");
        PrintStream dormWriter = new PrintStream(outputDorm);
        
        for(int i= 0; i < furniture.length; i++) {//Out prints the furniture data
            if (furniture[i] != null) {
            
                dormWriter.println(furniture[i].getVals());
            }           
        }
        dormWriter.close();
        System.out.println("Dorm Saved!");
        }
}