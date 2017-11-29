public class Heuristics
{
    public Heuristics()
    {

    }

    private Position finalStatePosition(int value, int[][] puzzleState) {
        Position position;
        for (int i = 0; i < puzzleState.length; i++) {
            for (int j = 0; j < puzzleState.length; j++) {
                if (value == puzzleState[i][j]) {
                    position = new Position(i, j);
                    return position;
                }
            }
        }
        return null;
    }

    public int ManhattanDistance(int[][] puzzleState, int[][] solvedState) {
        int manhattanDistance = 0;

        for (int i = 0; i < solvedState.length; i++) {
            for (int j = 0; j < solvedState.length; j++) {
                if (solvedState[i][j] != 0) {
                    Position finalStatePos = this.finalStatePosition(solvedState[i][j], puzzleState);
                    manhattanDistance = manhattanDistance + Math.abs(j - finalStatePos.getJ()) + Math.abs(i - finalStatePos.getI());
                }
            }
        }
        return manhattanDistance;
    }

    public int MisplacedTiles(int initial_state[][], int[][] solved_state) {

        int misplaced = 0;

        for (int rows = 0; rows < initial_state.length; rows++) {
            for (int cols = 0; cols < initial_state[rows].length; cols++) {
                if (initial_state[rows][cols] == solved_state[rows][cols]) {
                    continue;
                } else
                    misplaced++;
            }
        }
        return (misplaced);
    }

    public static void main (String [] args)
    {
        int puzzle[][] = {{0,8,3}
                        ,{1,2,4}
                        ,{7,6,5}};

        int solved[][] = {{1,2,3}
                        ,{8,0,4}
                        ,{7,6,5}};

        Heuristics heuristics = new Heuristics();
        System.out.println(heuristics.ManhattanDistance(puzzle, solved));
    }
}
