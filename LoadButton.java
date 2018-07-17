import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LoadButton extends Button {
    
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
        float x;
        float y;
        int r;
        int j = 0;
        
        for(int i = 0; i < furniture.length; i++) {
            furniture[i] = null;
        }
        
        while(reader.readLine() != null) {
            line = reader.readLine();
            type = line.split(":")[0].trim().toLowerCase();
            x = Float.parseFloat(line);
            y = Float.parseFloat(line);
            r = Integer.parseInt(line);
            
            furniture[j] = new Furniture(type, x, y, r, processing);
            j++;
        }
        reader.close();
    }
}
