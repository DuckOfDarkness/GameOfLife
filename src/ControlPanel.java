import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class ControlPanel extends JPanel {

    private Board board;
    private JButton startButton = new JButton("Start");
    private JButton stopButton = new JButton("Stop");
    private JButton clearButton = new JButton("Clear");
    private JLabel iterations, iterationsLabel, settingLabel;
    private JTextField aliveTextField, deathTextField, separatorTextField;
    private JTextField iterationsInput = new JTextField();

    private BoardFieldsPanel boardFieldsPanel;
    public ControlPanel(Board board, BoardFieldsPanel boardFieldsPanel){
        this.board = board;

        this.setLayout(new GridLayout(2, 1));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));


        this.boardFieldsPanel = boardFieldsPanel;



        iterationsInput.setPreferredSize(new Dimension(iterationsInput.getPreferredSize().width,
                iterationsInput.getFontMetrics(iterationsInput.getFont()).getHeight()));


        iterations = new JLabel(String.valueOf(board.getIterator()));
        iterationsLabel = new JLabel("Iterations: ");



        IterationsThread iterationsThread = new IterationsThread(this, boardFieldsPanel, board);

        Thread thread = new Thread(iterationsThread);


        topPanel.add(iterationsLabel);
        topPanel.add(iterations);

        topPanel.add(startButton);
        topPanel.add(stopButton);
        stopButton.setVisible(false);

        topPanel.add(clearButton);

        this.add(topPanel);

        settingLabel = new JLabel("Settings: ");
        bottomPanel.add(settingLabel);

        aliveTextField = new JTextField(6);
        bottomPanel.add(aliveTextField);

        separatorTextField = new JTextField(1);
        separatorTextField.setText("\\");
        separatorTextField.setEditable(false);
        bottomPanel.add(separatorTextField);

        deathTextField = new JTextField(6);
        bottomPanel.add(deathTextField);

        this.add(bottomPanel);
        ActionListener actionListener = e -> {
            if(e.getSource() == startButton){
                try{
                    board.setAliveArray(getAliveSetting(true));
                    board.setDeathArray(getAliveSetting(false));

                    startButton.setVisible(false);
                    stopButton.setVisible(true);
                    if (!iterationsThread.isRunning()) {
                        iterationsThread.setRunning(true);
                        thread.start();
                    } else if (iterationsThread.isPaused()) {
                        iterationsThread.setPaused(false);
                        iterationsThread.setRunning(true);
                    }
                    aliveTextField.setEditable(false);
                    deathTextField.setEditable(false);
                } catch (Exception ex) {
                }

            }
            if(e.getSource() == clearButton){
                startButton.setVisible(true);
                stopButton.setVisible(false);
                aliveTextField.setEditable(true);
                deathTextField.setEditable(true);
                boardFieldsPanel.updateBoardPanel(true);
                iterationsThread.setPaused(true);
                iterationStepOne();
            }
            if(e.getSource() == stopButton){
                startButton.setVisible(true);
                stopButton.setVisible(false);
                aliveTextField.setEditable(true);
                deathTextField.setEditable(true);
                iterationsThread.setPaused(true);
            }
        };
        startButton.addActionListener(actionListener);
        clearButton.addActionListener(actionListener);
        stopButton.addActionListener(actionListener);
    }

    public int[] getAliveSetting(boolean isAlive) throws Exception {
        int[] truth = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
        String string;
        if(isAlive){
            string = aliveTextField.getText();
        }else{
            string = deathTextField.getText();
        }
        if(string.isEmpty()){
            JOptionPane.showMessageDialog(null, "Setting fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            throw new Exception("Empty number");
        }else{
            char[] charArray = string.toCharArray();

            for(int i = 0 ; i < string.length() ; i++){
                if((int) charArray[i] > 48 && (int)charArray[i] < 57){

                    truth[i] = charArray[i]-48;
                }
                else{
                    JOptionPane.showMessageDialog(null, "The entered value must consist of 1 to 8 numbers. Each number can be from 1 to 8.", "Error", JOptionPane.ERROR_MESSAGE);
                    throw new Exception("Invalid number");
                }
            }
            return truth;
        }
    }

    public void iterationStepOne(){
        iterations.setText(String.valueOf(board.getIterator()));
    }
}
