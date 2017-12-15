import java.lang.String;

public class SnailSolution
{
    public SnailSolution ()
    {

    }

    public Board get_snail_sol(int[][] matrix)
    {
        Board snailSolution;
        int n = matrix.length;
        int[][] spiral_sol = new int[n][n];
        int val = 1;
        int min_col = 0;
        int max_col = n - 1;
        int min_row = 0;
        int max_row = n - 1;

        while (val < (n * n))
        {
            for (int i = min_col; i <= max_col; i++)
            {
                spiral_sol[min_row][i] = val;
                val++;
            }
            for (int i = min_row + 1; i <= max_row; i++)
            {
                spiral_sol[i][max_col] = val;
                val++;
            }
            for (int i = max_col - 1; i >= min_col; i--)
            {
                if (val == (n * n))
                {
                    val = 0;
                    n = 0;
                    spiral_sol[max_row][i] = val;
                }
                spiral_sol[max_row][i] = val;
                val++;
            }
            for (int i = max_row - 1; i >= min_row + 1; i--)
            {
                spiral_sol[i][min_col] = val;
                val++;
            }
            min_col++;
            min_row++;
            max_col--;
            max_row--;
        }
        snailSolution = new Board(spiral_sol, 0, null);
        return (snailSolution);
    }
}
