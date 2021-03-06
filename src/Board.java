public class Board
{
    private final int[][] board;
    private int     heuristic;
    private int     depth;
    private int[][] parentBoard;

    public Board (int [][]board, int heuristic, int [][]parentBoard)
    {
        this.board = board;
        this.heuristic = heuristic;
        this.parentBoard = parentBoard;
    }

    public int getHeuristic()
    {
        return heuristic;
    }

    public int[][] getBoard()
    {
        return board;
    }

    public int[][] getParentBoard()
    {
        return parentBoard;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    public int getDepth()
    {
        return depth;
    }

    public void setHeuristic(int heuristic) {
        this.heuristic = heuristic;
    }

    public void setParentBoard(int[][] parentBoard)
    {
        this.parentBoard = parentBoard;
    }

    public  void printBoard ()
    {
        System.out.println("Child Board\t\t\tParent Board\n");
        for (int i = 0; i < this.board.length; i++)
        {
            for (int j = 0; j < this.board.length; j++)
            {
                if (this.parentBoard != null)
                {
                    System.out.print(this.board[i][j] + " " + this.board[i][j + 1] + " " + this.board[i][j + 2] + "\t\t\t\t");
                    System.out.print(this.parentBoard[i][j] + " " + this.parentBoard[i][j + 1] + " " + this.parentBoard[i][j + 2] + " ");
                }
                else
                {
                    System.out.print(this.board[i][j] + " " + this.board[i][j + 1] + " " + this.board[i][j + 2] + "\t\t\t\t");
                }
                break;
            }

            System.out.println();
        }
    }
}
