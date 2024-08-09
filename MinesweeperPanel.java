import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MinesweeperPanel extends JPanel {
	private MinesweeperModel model;
	private JButton[][] buttons;
	private int revealedCount = 0;
	
	public MinesweeperPanel(MinesweeperModel model) {
		this.model = model;
		int rows = model.getRows();
		int columns = model.getColumns();
		int mines = model.getMines();
		
		setLayout(new GridLayout(rows, columns));
		buttons = new JButton[rows][columns];
		
		for (int row = 0; row < rows; row++) {
			for(int column = 0; column < columns; column++) {
				buttons[row][column] = newButton(row, column);
				add(buttons[row][column]);
			}
		}
		
		updateGame();
	}
	
	private JButton newButton(int row, int column) {
		JButton button = new JButton();
		button.setPreferredSize(new Dimension(20, 20));
		
		button.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if(!model.isGameOver() && !model.isGameWon()) {
					if(SwingUtilities.isLeftMouseButton(e)) {
						model.revealed(row, column);
						model.adjacentMines();
						updateGame();
						gameStatus();
					} else if(SwingUtilities.isRightMouseButton(e)) {
						model.flagged(row, column);
						updateGame();
						gameStatus();
					}
				}
			}
		});
		return button;
	}
	
	public void updateGame() {
		revealedCount = 0;
		for(int row = 0; row < model.getRows(); row++) {
			for(int column = 0; column < model.getColumns(); column++) {
				JButton button = buttons[row][column];
				int spot = model.getSpot(row, column);
				
				if(model.isRevealed(row, column)) {
					if(spot == -1) {
						button.setText("Mine");
					} else if(spot > 0) {
						button.setText(Integer.toString(spot));
					} else {
						button.setText("");
					}
					button.setEnabled(false);
					revealedCount++;
				} else {
					//checking unrevealed 
					if(model.isFlagged(row, column)) {
						button.setText("F");
					} else {
						button.setText("?");
					}
					button.setEnabled(true);
				}
			}
			
			if(revealedCount == (model.getColumns() * model.getRows()) - model.getMines()) {
				JOptionPane.showMessageDialog(this, "You Won!");
			}
		}
	}
	
	private void gameStatus() {
		if(model.isGameOver()) {
			JOptionPane.showMessageDialog(this, "Game Over");
		//} else if(model.isGameWon()){
		//	JOptionPane.showMessageDialog(this, "You Won!");
		//}
		}
	}
}