import javax.swing.*;
import javax.swing.plaf.metal.MetalLookAndFeel;
import java.awt.*;

public class Frame extends JFrame {

    public Frame(int width, int height, Board board) {
        super("Game of Life");

        try{
            UIManager.setLookAndFeel(new MetalLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }

        this.setSize(new Dimension(400, 800));
        this.setResizable(false);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel topPanel = new JPanel();

        BoardFieldsPanel boardFieldsPanel = new BoardFieldsPanel(width, height, board);
        boardFieldsPanel.setPreferredSize(new Dimension(300, 300));
        topPanel.add(boardFieldsPanel);
        this.add(topPanel, BorderLayout.NORTH);

        JSeparator jSeparator = new JSeparator(SwingConstants.HORIZONTAL);
        this.add(jSeparator, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        ControlPanel controlPanel = new ControlPanel(board, boardFieldsPanel);
        bottomPanel.add(controlPanel, BorderLayout.NORTH);

        this.add(bottomPanel, BorderLayout.SOUTH);
        
        this.setVisible(true);
        this.pack();
    }
}


