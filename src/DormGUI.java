/* DormGUI interface is the abstract of the program. Each object implementing DormGUI must
 * contain these method signatures to be called from the main class.
 */

public interface DormGUI {
	public void update();
	public void mouseDown(Furniture[] furniture) ;
	public void mouseUp();
	public boolean isMouseOver();
}
