/* LoadButton class extends the button class with it's purpose being to create the button
 * as well as load the furniture objects from a saved file.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadButton extends Button { 
    private static Furniture[] loadButtonFurniture; //static instance field to return the furniture to Main
    
    /* This constructor creates the load room button using the button base class.
     * @param x, y positions
     * @param processing
     */
    public LoadButton(float x, float y, PApplet processing) {
        super(x, y, processing);
        this.processing = processing;
        super.label = "Load Room";
    }
    
    public void update() { //updates the superclass
        super.update();
    }
    
    /* If the load button is clicked it should load the dorm room from the save file. 
     * This method may catch errors when trying to load the furniture objects.
     * @param furniture objects
     */
    public void mouseDown(Furniture[] furniture) {
        if (isMouseOver()) {
            try { //try loading the furniture from the file if the button is clicked
                loadRoom(furniture, "RoomData");
            }
            catch (FileNotFoundException e) { //errors to catch
                System.out.println(e.getMessage());
            }
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
            catch (ArrayIndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
            }
        }
        update();
    }
    
    public void mouseUp() { }
    
    public boolean isMouseOver() { //calls button superclass to check if the mouse is over
        return super.isMouseOver();
    }
    
    /*    
    * loadRoom method is used when the load button is pressed. This method reads a file
    * and grabs the useful data out to place the saved furniture in the dorm room.
    * This method can throw exceptions that will be caught in mouseDown.
    * @param furniture objects
    * @param filename 
    */
    private void loadRoom(Furniture[] furniture, String filename) throws FileNotFoundException, IOException, ArrayIndexOutOfBoundsException {
        File file = new File(filename + ".ddd"); //creates the file with filename
        
        if(file.equals(null)) {
            throw new FileNotFoundException("WARNING: Could not load room contents from file RoomData.ddd.");
        }
        if(file.canRead() != true) {
            throw new IOException("WARNING: Could not load room contents from file RoomData.ddd.");
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        String type;
        String line;
        String[] numStorage = new String[3];
        float x;
        float y;
        int r;
        int j = 0;
        
        for(int i = 0; i < furniture.length; i++) {
            furniture[i] = null;
        }

        while((line = reader.readLine()) != null) { //when there is a line to read
            if (line != "") { //Checks if line is not an empty character
            line = line.trim(); //trim the line
            if(j < 100) { //keep array in bounds for the Main class
                if(line.length() > 0) { //checks if there's data in the line
                    type = line.split(":")[0].trim().toLowerCase(); //splits the type from the coordinates
                    String delete = line.split(":")[0];//Saves the beginning characters to be deleted later
                    line = line.replace(delete + ":", "");//Deletes the beginning characters
                    numStorage = line.split(",");
                    x = Float.parseFloat(numStorage[0].trim()); //grabs the coordinates
                    y = Float.parseFloat(numStorage[1].trim());
                    r = Integer.parseInt(numStorage[2].trim());
                    
                    furniture[j] = new Furniture(type, x, y, r, processing); //places a new furniture object
                    j++;
                }
                else {
                    continue;
                }
            }
            }
            else {
                throw new ArrayIndexOutOfBoundsException("WARNING: array index out of bounds in load room.");
            }
        }
        reader.close();
        loadButtonFurniture = furniture;
    }

    public static Furniture[] getFurniture() { //static getter method to return the furniture to the list of GUIObjects
          return loadButtonFurniture;
    }
}