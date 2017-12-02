public class Solvable
{
    int [][]puzzle;
    public Solvable (Board board)
    {
        this.puzzle = board.getBoard();
    }

    private int[] D2ToD1(){

        int[] newArray = new int[this.puzzle.length * this.puzzle[0].length];

        for (int i = 0; i < this.puzzle.length; ++i)
        {
            for (int j = 0; j < this.puzzle[i].length; ++j)
            {
                newArray[i * this.puzzle[0].length + j] = this.puzzle[i][j];
            }
        }
        return newArray;
    }

    private int getInvCount(int arr[])
    {
        int inv_count = 0;
        for (int i = 0; i < arr.length - 1; i++)
        {
            for (int j = i + 1; j < arr.length; j++)
            {
                if (arr[i] > arr[j] && arr[i] != 0 && arr[j] != 0)
                {
                    inv_count++;
                }
            }
        }
        return inv_count;
    }

    private boolean blankOnOddFromBottom()
    {
        for (int i = 0; i < this.puzzle.length; i++)
        {
            for (int j = 0; j< this.puzzle.length; j++)
            {
                if (this.puzzle[i][j] == 0)
                {
                    if ((this.puzzle.length - i) % 2 == 1)
                        return true;
                    else
                        return  false;
                }
            }
        }
        return false;
    }

    public boolean isSolvable()
    {
        if (this.puzzle.length % 2 == 1)
        {
            if (this.getInvCount(this.D2ToD1()) % 2 == 0)
                return false;
            else
                return true;
        }
        else
        {
            if (this.getInvCount(this.D2ToD1()) % 2 == 0)
            {
                if (this.blankOnOddFromBottom())
                    return true;
                else
                    return  false;
            }
            else
            {
                if (!this.blankOnOddFromBottom())
                    return true;
                else
                    return  false;
            }
        }
    }

    /*public static void main (String [] args)
    {
        int puzzle[][] = {{1,2,3,4,5}
                        , {6,7,8,9,10}
                        , {11,12,13,14,15}
                        , {16,17,18,19,0}
                        , {20,24,21,22,23}};
        Board board = new Board(puzzle, 0, null);
        Solvable solvable = new Solvable(board);
        System.out.println(solvable.isSolvable());
    }*/
}