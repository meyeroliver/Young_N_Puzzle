import java.lang.Math.*;

public class Solvable
{
    public Solvable ()
    {

    }

    private int[] D2ToD1(int[][] puzzle){

        int[] newArray = new int[puzzle.length * puzzle[0].length];

        for (int i = 0; i < puzzle.length; ++i)
        {
            for (int j = 0; j < puzzle[i].length; ++j)
            {
                newArray[i * puzzle[0].length+j] = puzzle[i][j];
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

    private boolean blankOnOddFromBottom(int[][] puzzle)
    {
        for (int i = 0; i < puzzle.length; i++)
        {
            for (int j = 0; j< puzzle.length; j++)
            {
                if (puzzle[i][j] == 0)
                {
                    if ((puzzle.length - i) % 2 == 1)
                    {
                        return true;
                    }
                    else
                    {
                        return  false;
                    }
                }
            }
        }
        return false;
    }

    public boolean isSolvable(int[][] puzzle)
    {
        if (puzzle.length % 2 != 0)
        {
            if (this.getInvCount(this.D2ToD1(puzzle)) % 2 == 0)
            {
                return true;
            }
        }
        else
        {
            if (this.blankOnOddFromBottom(puzzle) && this.getInvCount(this.D2ToD1(puzzle)) % 2 == 0)
            {
                return true;
            }
        }
        return false;
    }

    public static void main (String [] args)
    {
        int puzzle[][] = {{1,2,3},{7,0,4},{8,5,6}};
        Solvable solvable = new Solvable();
        System.out.println(solvable.isSolvable(puzzle));
    }
}