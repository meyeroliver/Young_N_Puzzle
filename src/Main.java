import java.util.Scanner;
import java.lang.String;
public class Main
{
    public static void main(String[] args)
    {
        Scanner userInput = new Scanner(System.in);
        System.out.println("Please enter the name of the input file");
        String[] arguments = userInput.nextLine().split(" ");

        if (arguments.length == 1)
        {
            if (arguments[0].length() == 0)
            {
                System.err.println("Error there are no arguments found");
            }
            else
            {
                ReadFile file = new ReadFile(arguments[0]);
                if (file.openFile() == true)
                {
                    int[][] initialPuzzle = file.getMatrix(arguments[0]);
                    if (initialPuzzle != null)
                    {
                        Scanner heuristicInput = new Scanner(System.in);
                        System.out.println("Please select: \n----------------\n 1: Misplaced tiles \n 2: Manhattan distance \n 3: Rows and columns");
                        String[] heuristics = heuristicInput.nextLine().split(" ");
                        Board puzzle = new Board(initialPuzzle, 0, null);
                        Solvable solvable = new Solvable(puzzle);
                        if (solvable.isSolvable() == true)
                        {
                            SnailSolution snailSolution =   new SnailSolution();
                            PathFinder pathFinder = new PathFinder(puzzle, snailSolution.get_snail_sol(initialPuzzle));
                            pathFinder.solutionPath(Integer.parseInt(heuristics[0]));
                        }
                        else
                        {
                            System.err.println("This puzzle has no solution");
                        }
                    }
                }
            }
        }
    }

}
