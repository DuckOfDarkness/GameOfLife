import javax.swing.*;
import java.awt.*;

public class BoardFieldsPanel extends JPanel {

    private int width, height;
    private Board board;

    private JCheckBox[][] boardFields;

    public BoardFieldsPanel(int width, int height, Board board) {
        this.width = width;
        this.height = height;
        this.board = board;
        boardFields = new JCheckBox[height][width];
        this.setLayout(new GridLayout(height, width));
        createBoard();
        updateBoardPanel(false);
    }

    private void createBoard() {
        for (int i = 0; i < boardFields.length; i++) {
            for (int j = 0; j < boardFields[i].length; j++) {
                boardFields[j][i] = new JCheckBox();
                boardFields[j][i].setUI(new CheckBoxUI());
                this.add(boardFields[j][i]);
            }
        }
    }

    public void updateBoardPanel(boolean clean) {
        char[][] boardTemp = board.getBoard();
        for (int i = 0; i < boardTemp.length; i++) {
            for (int j = 0; j < boardTemp[i].length; j++) {
                if(clean){
                    boardTemp[j][i] = ' ';
                    boardFields[j][i].setSelected(false);
                }else{
                    if (boardTemp[j][i] == 'X') {
                        boardFields[j][i].setSelected(true);
                    } else if (boardTemp[j][i] == ' ') {
                        boardFields[j][i].setSelected(false);
                    }
                }
            }
        }
        if(clean){
            board.setBoard(boardTemp);
            board.resetIterator();

        }
    }

    public void updateBoard() {
        char[][] boardTemp = board.getBoard();
        for (int i = 0; i < boardTemp.length; i++) {
            for (int j = 0; j < boardTemp[i].length; j++) {
                if (boardFields[j][i].isSelected()) {
                    boardTemp[j][i] = 'X';
                    System.out.println("selected: " + j + ", " + i);
                    System.out.println("amountOfAliveNeighbors:" + board.amountOfAliveNeighbors(j, i));
                } else if (!boardFields[j][i].isSelected()) {
                    boardTemp[j][i] = ' ';
                }
            }
        }
        board.setBoard(boardTemp);
    }

}
