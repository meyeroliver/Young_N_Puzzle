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

    private ArrayList<Board> bubbleSort(ArrayList<Board> sortBoard)
    {
        Board tempBoard;

        for (int i = 0; i < sortBoard.size(); i++)
        {
            for (int j = 0; j < sortBoard.size() - i - 1; j++)
            {
                if (sortBoard.get(j).getHeuristic() > sortBoard.get(j + 1).getHeuristic())
                {
                    tempBoard = sortBoard.get(j);
                    sortBoard.set(j, sortBoard.get(j + 1));
                    sortBoard.set(j + 1, tempBoard);
                }
            }
        }
        return sortBoard;
    }

    private void prioritiseOpenset()
    {
        ArrayList<Board> boardSort = new ArrayList<>();
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
                }
                else
                    misplaced++;
            }
        }
        return (misplaced);
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
                }
                else
                    misplaced++;
            }
        }
        return (misplaced);
    }

    private Queue<Board> createChildren(Board tempBoard)
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
        return rugrats;
    }

    private int userHeuristicChoice(int choice, Board youngOne)
    {
        Heuristics heuristics = new Heuristics();
        int number = 0;

        if (choice == 1)
        {
            number = heuristics.MisplacedTiles(youngOne.getBoard(), this.finalBoard.getBoard()) + youngOne.getDepth();
        }
        else if (choice == 2)
        {
            number = heuristics.ManhattanDistance(youngOne.getBoard(), this.finalBoard.getBoard()) + youngOne.getDepth();
        }
        else if (choice == 3)
        {
            number = heuristics.rowAndColumn(youngOne.getBoard(), this.finalBoard.getBoard()) + youngOne.getDepth();
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
        }
        return false;
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
        }
        return false;
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
        }
        return false;
    }

    public void solutionPath(int userHeuristic)
    {
        Board tempBoard;

        this.startBoard.setDepth(0);
        this.openSet.add(this.startBoard);
        while (!this.openSet.isEmpty())
        {
            tempBoard = this.openSet.remove();
            if (this.BoardCompare(tempBoard) == 0)
            {
                this.closedSet.addFirst(tempBoard);
                ListIterator<Board> listIterator = this.closedSet.listIterator();
                int i;

                while (listIterator.hasNext())
                {
                    Board solution = listIterator.next();
                    if (this.BoardCompare(solution) == 0)
                    {
                        this.solutionSet.addFirst(solution);
                        this.solutionSet.addFirst(listIterator.next());
                        listIterator.previous();
                        continue;
                    }
                    else
                    {
                        if (solution.getParentBoard() == null)
                        {
                            continue;
                        }
                        else
                        {
                            Board parentBoard = new Board(solution.getParentBoard(), 0, null);
                            Board nextStep;
                            while (this.BoardCompare2(parentBoard, nextStep = listIterator.next()) != 0) ;
                            this.solutionSet.addFirst(nextStep);
                            listIterator.previous();
                        }

                    }
                }
                i = 0;
                for (Board sol : this.solutionSet) {
                    sol.printBoard();
                    System.out.println("------------------------------------ " + i++);
                }
                break;
            }
            else
                {
                for (Board youngOne : this.createChildren(tempBoard))
                {
                    if (youngOne == null)
                    {
                        continue;
                    }
                    else if (this.isInOpenSet(youngOne) == false && this.isInClosedSet(youngOne) == false)
                    {
                        youngOne.setDepth(tempBoard.getDepth() + 1);
                        youngOne.setHeuristic(this.userHeuristicChoice(userHeuristic, youngOne));
                        youngOne.setParentBoard(tempBoard.getBoard());
                        this.openSet.add(youngOne);
                    }
                    else if (this.isInOpenSet(youngOne) == true)
                    {
                        youngOne.setDepth(tempBoard.getDepth() + 1);
                        youngOne.setHeuristic(this.userHeuristicChoice(userHeuristic, youngOne));

                        youngOne.setDepth(tempBoard.getDepth() + 1);
                        youngOne.setHeuristic(this.userHeuristicChoice(1, youngOne));

                        youngOne.setParentBoard(tempBoard.getBoard());

                        for (Board inOpen : this.openSet)
                        {
                            if (this.BoardCompare2(youngOne, inOpen) == 0)
                            {
                                if (youngOne.getHeuristic() < inOpen.getHeuristic())
                                {
                                    this.openSet.removeLast();
                                    this.openSet.add(youngOne);
                                    break;

                                }
                            }
                        }
                    }
                    else if (this.isInClosedSet(youngOne) == true)
                    {
                        youngOne.setDepth(tempBoard.getDepth() + 1);
                        youngOne.setHeuristic(this.userHeuristicChoice(userHeuristic, youngOne));
                        youngOne.setParentBoard(tempBoard.getBoard());

                        for (Board inClosed : this.closedSet)
                        {
                            if (this.BoardCompare2(youngOne, inClosed) == 0)
                            {
                                if (youngOne.getHeuristic() < inClosed.getHeuristic())
                                {
                                    this.openSet.add(youngOne);
                                    break;
                                }
                            }
                        }
                    }
                }
                this.closedSet.addFirst(tempBoard);
                this.prioritiseOpenset();
            }
        }
    }
}