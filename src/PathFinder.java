import java.util.*;

public class PathFinder
{
    private LinkedList<Board> openSet;
    private LinkedList<Board> closedSet;
    private LinkedList<Board> solutionSet;
    private final Board startBoard;
    private final Board finalBoard;


    public PathFinder(Board startBoard, Board finalBoard)
    {
        this.finalBoard = finalBoard;
        this.startBoard = startBoard;
        this.openSet = new LinkedList<>();
        this.closedSet = new LinkedList<>();
        this.solutionSet = new LinkedList<>();

    }

    public LinkedList<Board> getClosedSet()
    {
        return closedSet;
    }

    public LinkedList<Board> getOpenSet()
    {
        return openSet;
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

    private void prioritiseOpenset ()
    {
        ArrayList <Board> boardSort = new ArrayList<>();
        while (!this.openSet.isEmpty())
        {
            boardSort.add(this.openSet.remove());
        }

        boardSort = this.bubbleSort(boardSort);
        for (Board sorted : boardSort)
        {
            this.openSet.add(sorted);
        }
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

    private boolean isInOpenSet(Board youngOne)
    {
        if (this.openSet.isEmpty())
        {
            return false;
        }

        for (Board compare : this.openSet)
        {
            if (this.BoardCompare2(youngOne, compare) == 0)
            {
                return true;
            }
            /*for (int i = 0; i < compare.getBoard().length; i++)
            {
                for (int j = 0; j < compare.getBoard().length; j++)
                {
                    if (youngOne.getBoard()[i][j] != compare.getBoard()[i][j])
                    {
                        return false;
                    }
                }
            }*/
        }
        return  false;
    }

    public int BoardCompare2(Board youngOne, Board compare)
    {
        int misplaced = 0;

        for (int rows = 0; rows < youngOne.getBoard().length; rows++)
        {
            for (int cols = 0; cols < youngOne.getBoard()[rows].length; cols++)
            {
                if (youngOne.getBoard()[rows][cols] == compare.getBoard()[rows][cols])
                {
                    continue;
                } else
                    misplaced++;
            }
        }
        return (misplaced);
    }

    private boolean isInClosedSet(Board youngOne)
    {
        boolean skip = false;
        if (this.closedSet.isEmpty())
        {
            return false;
        }

        for (Board compare : this.closedSet)
        {
            if (this.BoardCompare2(youngOne, compare) == 0)
            {
                return true;
            }
            /*for (int i = 0; i < compare.getBoard().length; i++)
            {
                for (int j = 0; j < compare.getBoard().length; j++)
                {
                    if (youngOne.getBoard()[i][j] != compare.getBoard()[i][j])
                    {
                        skip = true;
                        break;
                    }
                }
                if (skip == true)
                {
                    skip = false;
                    break;
                }
            }*/
        }
        return  false;
    }

    private boolean isInSolutionSet(Board youngOne)
    {
        if (this.solutionSet.isEmpty())
        {
            return false;
        }

        for (Board compare : this.solutionSet)
        {
            if (this.BoardCompare2(youngOne, compare) == 0)
            {
                return true;
            }
            /*
            for (int i = 0; i < compare.getBoard().length; i++)
            {
                for (int j = 0; j < compare.getBoard().length; j++)
                {
                    if (youngOne.getBoard()[i][j] != compare.getBoard()[i][j])
                    {
                        return false;
                    }
                }
            }*/
        }
        return false;
    }

    public void solutionPath(/*need to pass a variable that will allow the user to choose the type of heuristics*/)
    {
        Board tempBoard = null;
        int depthOfSearch = 0;

        this.openSet.add(this.startBoard);
        while (!this.openSet.isEmpty())
        {
            tempBoard = this.openSet.remove();
            if (this.BoardCompare(tempBoard) == 0)
            {


                //go through the closedset to arrange the path
                this.closedSet.addFirst(tempBoard);
                /////////////////////////////////////////////////////////////
                int flag = 0;
                for (Board solution : this.closedSet)
                {
                   /* Board parentBoard = new Board(solution.getParentBoard(), 0 , solution.getBoard());

                    if (flag == 0)
                    {
                        this.solutionSet.addFirst(solution);
                        this.solutionSet.addFirst(parentBoard);
                        flag = 1;
                    }
                    else
                    {
                        if (solution.getParentBoard() == null)
                        {
                            this.solutionSet.addFirst(solution);
                            continue;
                        }
                        if (this.isInSolutionSet(parentBoard))
                        {
                            continue;
                        }
                        this.solutionSet.addFirst(parentBoard);
                    }*/


                    Board parentBoard = new Board(solution.getParentBoard(), 0 , null);
                    if (flag == 0)
                    {
                        this.solutionSet.addFirst(solution);
                        this.solutionSet.addFirst(parentBoard);
                        flag = 1;
                        continue;

                    }
                    if (this.BoardCompare(parentBoard) == 0)
                    {
                        this.solutionSet.addFirst(parentBoard);
                        break;
                    }
                    else
                    {
                        if (this.isInSolutionSet(solution) || this.isInSolutionSet(parentBoard))
                        {
                            continue;
                        }
                        else
                            this.solutionSet.addFirst(solution);
                        if (solution.getParentBoard() == null)
                        {
                            this.solutionSet.addFirst(solution);
                            continue;
                        }
                        /*if (this.isInSolutionSet(parentBoard))
                        {
                            continue;
                        }*/

                    }

                }
                ////////////////////////////////////////////////////////////
                for (Board sol : this.closedSet)
                {
                    sol.printBoard();
                    System.out.println("------------------------------------");
                }
                break ;
            }
            else
            {
                for (Board youngOne : this.createChildren(tempBoard))
                {
                    if (youngOne == null)
                    {
                        continue;
                    }

                    else if (this.isInOpenSet(youngOne) == false && this.isInClosedSet(youngOne) == false /*!this.openSet.contains(youngOne) && !this.closedSet.contains(youngOne)*/)
                    {
                        youngOne.setHeuristic(this.userHeuristicChoice(1, youngOne));
                        youngOne.setParentBoard(tempBoard.getBoard());//on that specific case
                        youngOne.setDepth(depthOfSearch);
                        this.openSet.add(youngOne);
                    }

                    else if (this.isInOpenSet(youngOne) == true)
                    {
                        System.out.println("Child already on open\n depth : " + depthOfSearch);
                        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                        youngOne.printBoard();
                        System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    }

                    else if (this.isInClosedSet(youngOne) == true)
                    {

                      //  System.out.println("Child already on closed");
                    }
                }
                this.closedSet.addFirst(tempBoard);
                this.prioritiseOpenset();
            }
            depthOfSearch++;
        }
        //misplaced tiles is out by one
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















