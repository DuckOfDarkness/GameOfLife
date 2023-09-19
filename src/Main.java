import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        int width = 25;
        int height = 25;
        Board board= new Board(width,height);

        SwingUtilities.invokeLater(()-> new Frame(width, height, board));
    }
}
