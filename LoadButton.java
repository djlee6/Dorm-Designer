import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadButton extends Button {
    
    private static Furniture[] loadButtonFurniture;
    
    public LoadButton(float x, float y, PApplet processing){ 
        super("Load Room", x, y, processing);
        this.processing = processing;
    }
    
    public void update() {
        super.update();
    }
    
    @Override
    public void mouseDown(Furniture[] furniture) {
        if (isMouseOver()) {
            try {
                loadRoom(furniture, "RoomData");
            }
            catch (FileNotFoundException e) { 
                System.err.print(e);
            }
            catch (IOException e) {
                System.err.print(e);
            }
        }
        update();
    }
    
    public void mouseUp() {
        
    }
    public boolean isMouseOver() {
        return super.isMouseOver();
    }
    private void loadRoom(Furniture[] furniture, String filename) throws FileNotFoundException, IOException {
        File file = new File(filename + ".ddd");
        
        if(file.equals(null)) {
            throw new FileNotFoundException("FileNotFoundException when loading room");
        }
        if(file.canRead() != true) {
            throw new IOException("IOException: file cannot be read when loading room");
        }
        BufferedReader reader = new BufferedReader(new FileReader(file));
        
        String type;
        String line;
        String[] numStorage;
        float x;
        float y;
        int r;
        int j = 0;
        
        for(int i = 0; i < furniture.length; i++) {
            furniture[i] = null;
        }

        while((line = reader.readLine()) != null) {
            type = line.split(":")[0].trim().toLowerCase();
            line = line.replace(type + ":", "");
            numStorage = line.split(",");
            x = Float.parseFloat(numStorage[0]);
            y = Float.parseFloat(numStorage[1]);
            r = Integer.parseInt(numStorage[2]);
                    System.out.println("PostStorage" + type + x + y + r);
            furniture[j] = new Furniture(type, x, y, r, processing);
            j++;
            }
        loadButtonFurniture = furniture;
        reader.close();
    }
    public static Furniture[] getFurniture() {
        return loadButtonFurniture;
    }
}