import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class SaveButton extends Button { //Save button allows you to save the contents of the dorm room into a file to be loaded

    public SaveButton(float x, float y, PApplet processing){  //creates the save button
        super(x, y, processing);
        this.processing = processing;
        super.label = "Save Room";
    }
    
    public void update() { //updates the save button
        super.update();
    }
    
    @Override
    public void mouseDown(Furniture[] furniture) { //overrides the superclass mouseDown to try saving the data
        if (isMouseOver()) {
        	    if (furniture != null) {
        	        try {
					saveRoom(furniture, "RoomData");
        	        } 
        	        catch (IOException e) {
        	            System.out.print(e.getMessage());
        	        }
        	    }
        }
    }
    public void mouseUp() {}
    
    public boolean isMouseOver() { //checks if the mouse is over
        return super.isMouseOver();
    }
    private void saveRoom(Furniture[] furniture, String filename)  throws IOException { //saveRoom method writes a new .ddd file containing the coordinates and types of furniture 
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
