import javax.swing.*;

public class MinesweeperFrame extends JFrame {
	private final int ROWS = 10; //final because these dont change
	private final int COLUMNS = 10;
	private final int MINES = 15;
	//private final int SPOTSREMAINING = (ROWS * COLUMNS) - MINES;
	
	private MinesweeperModel model;
	private MinesweeperPanel panel;
	
	public MinesweeperFrame() {
		model = new MinesweeperModel(ROWS, COLUMNS, MINES);
		
		setTitle("Minesweeper");
		setSize(800, 600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new MinesweeperPanel(model);
		add(panel);
		setVisible(true);
	}	
	
}