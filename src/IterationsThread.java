public class IterationsThread implements Runnable {

    private final int ITERATION_FREQUENCY = 500;
    private BoardFieldsPanel boardFieldsPanel;
    private Board board;
    private ControlPanel controlPanel;

    private volatile boolean isRunning = false;
    private volatile boolean isPaused = false;

    public IterationsThread(ControlPanel controlPanel, BoardFieldsPanel boardFieldsPanel, Board board) {
        this.controlPanel = controlPanel;
        this.boardFieldsPanel = boardFieldsPanel;
        this.board = board;
    }

    public void setRunning(boolean start) {
        isRunning = start;
        if (isRunning) {
            synchronized (this) {
                notify();
            }
        }
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public boolean isPaused() {
        return isPaused;
    }

    @Override
    public void run() {

        while (isRunning) {
            if (isPaused) {
                synchronized (this) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                boardFieldsPanel.updateBoard();
                board.nextOneIterationStep();
                boardFieldsPanel.updateBoardPanel(false);
                controlPanel.iterationStepOne();
            }
            try {
                Thread.sleep(ITERATION_FREQUENCY);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
