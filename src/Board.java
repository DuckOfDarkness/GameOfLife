import java.util.Arrays;

public class Board {

    private int height, width, iterator;

    private char[][] board;
    private int[] aliveArray;
    private int[] deathArray;

    public Board(int width, int height) {
        iterator = 0;
        this.width = width;
        this.height = height;

        aliveArray = new int[9];
        deathArray = new int[9];

        board = createBoard();
    }

    private char[][] createBoard() {
        char[][] board = new char[height][width];
        for (char[] chars : board) {
            Arrays.fill(chars, ' ');
        }
        return board;
    }

    public int getIterator() {
        return iterator;
    }

    public void resetIterator() {
        iterator = 0;
    }

    public char[][] getBoard() {
        return board;
    }

    public void setAliveArray(int[] aliveArray) {
        this.aliveArray = aliveArray;
    }

    public void setDeathArray(int[] deathArray) {
        this.deathArray = deathArray;
    }

    public int amountOfAliveNeighbors(int x, int y) {
        int amountOfAlive = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }

                int neighborX = (x + i + width) % width;
                int neighborY = (y + j + height) % height;

                if (isFieldAlive(neighborX, neighborY)) {
                    amountOfAlive++;
                }
            }
        }
        return amountOfAlive;
    }

    public void nextOneIterationStep() {
        // Tworzenie tymczasowej kopii planszy
        char[][] newBoard = new char[height][width];

        // Przejście przez wszystkie komórki planszy
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int aliveNeighbors = amountOfAliveNeighbors(i, j);
                boolean isAlive = isFieldAlive(i, j);

                System.out.println(Arrays.toString(aliveArray));
                System.out.println(Arrays.toString(deathArray));
                // Sprawdzenie zasad gry i aktualizacja stanu komórki
                if (isAlive) {
                    if ((aliveArray[0] != 0 && aliveNeighbors == aliveArray[0]) || (aliveArray[1] != 0 && aliveNeighbors == aliveArray[1]) ||
                            (aliveArray[2] != 0 && aliveNeighbors == aliveArray[2]) || (aliveArray[3] != 0 && aliveNeighbors == aliveArray[3]) ||
                            (aliveArray[4] != 0 && aliveNeighbors == aliveArray[4]) || (aliveArray[5] != 0 && aliveNeighbors == aliveArray[5]) ||
                            (aliveArray[6] != 0 && aliveNeighbors == aliveArray[6]) || (aliveArray[7] != 0 && aliveNeighbors == aliveArray[7])) {
                        newBoard[j][i] = 'X'; // Komórka pozostaje żywa
                        System.out.println(j + ", " + i + " =  komorka pozostaje zywa");
                    } else {
                        newBoard[j][i] = ' '; // Komórka umiera
                        System.out.println(j + ", " + i + " =  Komórka umiera");
                    }
                } else {
                    if ((deathArray[0] != 0 && aliveNeighbors == deathArray[0]) || (deathArray[1] != 0 && aliveNeighbors == deathArray[1]) ||
                            (deathArray[2] != 0 && aliveNeighbors == deathArray[2]) || (deathArray[3] != 0 && aliveNeighbors == deathArray[3] ||
                            (deathArray[4] != 0 && aliveNeighbors == deathArray[4]) || (deathArray[5] != 0 && aliveNeighbors == deathArray[5]) ||
                            (deathArray[6] != 0 && aliveNeighbors == deathArray[6]) || (deathArray[7] != 0 && aliveNeighbors == deathArray[7]))
                    ) {
                        newBoard[j][i] = 'X'; // Komórka staje się żywa
                        System.out.println(j + ", " + i + " =  Komórka staje się żywa");
                    }
                }
            }
        }

        // Zaktualizowanie planszy
        board = newBoard;
        iterator++;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public boolean isFieldAlive(int x, int y) {
        return board[y][x] == 'X';
    }
}
