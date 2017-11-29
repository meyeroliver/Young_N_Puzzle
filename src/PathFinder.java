import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.util.*;

public class PathFinder
{
    private Queue<Board> openSet;
    private Queue<Board> closedSet;
    private final Board startBoard;
    private final Board finalBoard;


    public PathFinder(Board startBoard, Board finalBoard)
    {
        this.finalBoard = finalBoard;
        this.startBoard = startBoard;
        openSet = new LinkedList<>();
        closedSet = new LinkedList<>();
    }

    private ArrayList <Board> bubbleSort(ArrayList <Board> sortBoard)
    {
        Board tempBoard;

        for(int i = 0; i < sortBoard.size(); i++)
        {
            for(int j = 0; j < sortBoard.size() - i - 1; j++)
            {
                if (sortBoard.get(j).getHeuristic() > sortBoard.get(j + 1).getHeuristic())
                {
                    tempBoard = sortBoard.get(j);
                    sortBoard.set(j, sortBoard.get(j+ 1));
                    sortBoard.set(j + 1, tempBoard);
                }
            }
        }
        return sortBoard;
    }

    public Queue<Board> getClosedSet()
    {
        return closedSet;
    }

    public Queue<Board> getOpenSet()
    {
        return openSet;
    }

    public int BoardCompare(Board tempBoard)
    {
        int misplaced = 0;

        for (int rows = 0; rows < tempBoard.getBoard().length; rows++)
        {
            for (int cols = 0; cols < tempBoard.getBoard()[rows].length; cols++)
            {
                if (tempBoard.getBoard()[rows][cols] == this.finalBoard.getBoard()[rows][cols])
                {
                    continue;
                } else
                    misplaced++;
            }
        }
        return (misplaced);
    }

    private Queue<Board> createChildren (Board tempBoard)
    {
        Queue<Board> rugrats = new LinkedList<>();
        Move childMaker = new Move(tempBoard);
        if (childMaker.moveRight(tempBoard) != null)
        {
           rugrats.add(childMaker.moveRight(tempBoard));
        }
        if (childMaker.moveLeft(tempBoard) != null)
        {
            rugrats.add(childMaker.moveLeft(tempBoard));
        }
        if (childMaker.moveUp(tempBoard) != null)
        {
           rugrats.add(childMaker.moveUp(tempBoard));
        }
        if (childMaker.moveDown(tempBoard) != null)
        {
            rugrats.add(childMaker.moveDown(tempBoard));
        }
        return  rugrats;
    }

    private int userHeuristicChoice (int choice, Board youngOne)
    {
        Heuristics heuristics = new Heuristics();
        int number = 0;

        if (choice == 1)
        {
            number = heuristics.MisplacedTiles(youngOne.getBoard(), this.finalBoard.getBoard());//the heuristeic class needs to use board
        }
        else if (choice == 2)
        {

        }
        else if (choice == 3)
        {

        }
        return number;
    }

    public void solutionPath(/*need to pass a variable that will allow the user to choose the type of heuristics*/)
    {
        Board tempBoard = null;
        int deapthOfSearch = 0;

        this.openSet.add(this.startBoard);
        while (!this.openSet.isEmpty())
        {
            tempBoard = this.openSet.remove();
            if (this.BoardCompare(tempBoard) == 0)
            {
                //go through the closedset to arrange the path
                break ;
            }
            else
            {
                for (Board youngOne : this.createChildren(tempBoard))
                {
                   // this.openSet.add(youngOne);
                    if (youngOne == null)
                    {
                        continue;
                    }

                    if (!this.openSet.contains(youngOne) && !this.closedSet.contains(youngOne))
                    {
                        youngOne.setHeuristic(this.userHeuristicChoice(1, youngOne));
                        youngOne.setParentBoard(tempBoard.getBoard());
                        youngOne.setDepth(deapthOfSearch + 1);
                        this.openSet.add(youngOne);
                    }

                    /*else if (this.openSet.contains(youngOne))
                    {

                    }

                    else if (this.closedSet.contains(youngOne))
                    {
                        System.out.println("Child already on closed");
                    }*/
                }
                // make this a function
                this.closedSet.add(tempBoard);
                ArrayList <Board> boardSort = new ArrayList<>();
                while (!this.openSet.isEmpty())
                {
                    boardSort.add(this.openSet.remove());
                }

                boardSort = this.bubbleSort(boardSort);
                for (Board sorted : boardSort)
                {
                    sorted.printBoard();
                    System.out.println("------------------------------------");
                    this.openSet.add(sorted);
                }
            }
            deapthOfSearch++;
        }
        //tempBoard.printBoard();
    }

    public static void main (String [] args)
    {
        int puzzle[][] = {{2, 8, 3}
                        , {1, 6, 4}
                        , {7, 0, 5}};

        Board board = new Board(puzzle, 0, null);

        SnailSolution snailSolution =   new SnailSolution();
        PathFinder pathFinder = new PathFinder(board, snailSolution.get_snail_sol(puzzle));
        pathFinder.solutionPath();
    }
}
