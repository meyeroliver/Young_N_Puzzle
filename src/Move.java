public class Move
{
    Board puzzle;

    public Move (Board puzzle)
    {
        this.puzzle = puzzle;
    }

    private boolean validMove(int pos, String direction)
    {
        if (direction.equals("UP"))
        {
            if (pos == 0)
            return false;
        }
        else if (direction.equals("DOWN"))
        {
            if (pos == this.puzzle.getBoard().length - 1)
                return false;
        }
        else if (direction.equals("RIGHT"))
        {
            if (pos == this.puzzle.getBoard().length - 1)
                return false;
        }
        else if (direction.equals("LEFT"))
        {
            if (pos == 0)
                return false;
        }
        return true;
    }

    public Board moveUp (Board puzzle)
    {
        Board tempPuzzle = copyPuzzle(puzzle);
        int temp;

        for (int i = 0; i < puzzle.getBoard().length; i++)
        {
            for (int j = 0; j< puzzle.getBoard().length; j++)
            {
                if (tempPuzzle.getBoard()[i][j] == 0 && (validMove(i, "UP")))
                {
                    temp = tempPuzzle.getBoard()[i - 1][j];
                    tempPuzzle.getBoard()[i - 1][j] = tempPuzzle.getBoard()[i][j];
                    tempPuzzle.getBoard()[i][j] = temp;
                    return tempPuzzle;
                }
            }
        }
        return null;
    }

    public Board moveDown (Board puzzle)
    {
        Board tempPuzzle = copyPuzzle(puzzle);
        int temp;

        for (int i = 0; i < puzzle.getBoard().length; i++)
        {
            for (int j = 0; j< puzzle.getBoard().length; j++)
            {
                if (tempPuzzle.getBoard()[i][j] == 0 && (validMove(i, "DOWN")))
                {
                    temp = tempPuzzle.getBoard()[i + 1][j];
                    tempPuzzle.getBoard()[i + 1][j] = tempPuzzle.getBoard()[i][j];
                    tempPuzzle.getBoard()[i][j] = temp;
                    return tempPuzzle;
                }
            }
        }
         return null;
    }

    public Board moveRight (Board puzzle)
    {
        Board tempPuzzle = copyPuzzle(puzzle);
        int temp;

        for (int i = 0; i < puzzle.getBoard().length; i++)
        {
            for (int j = 0; j< puzzle.getBoard().length; j++)
            {
                if (tempPuzzle.getBoard()[i][j] == 0 && (validMove(j, "RIGHT")))
                {
                    temp = tempPuzzle.getBoard()[i][j + 1];
                    tempPuzzle.getBoard()[i][j + 1] = tempPuzzle.getBoard()[i][j];
                    tempPuzzle.getBoard()[i][j] = temp;
                    return tempPuzzle;
                }
            }
        }
        return null;
    }

    public Board moveLeft (Board puzzle)
    {
        Board tempPuzzle = copyPuzzle(puzzle);
        int temp;

        for (int i = 0; i < puzzle.getBoard().length; i++)
        {
            for (int j = 0; j< puzzle.getBoard().length; j++)
            {
                if (tempPuzzle.getBoard()[i][j] == 0 && (validMove(j, "LEFT")))
                {
                    temp = tempPuzzle.getBoard()[i][j - 1];
                    tempPuzzle.getBoard()[i][j - 1] = tempPuzzle.getBoard()[i][j];
                    tempPuzzle.getBoard()[i][j] = temp;
                    return tempPuzzle;
                }
            }
        }
        return null;
    }

    public Board copyPuzzle (Board puzzle)
    {
        int [][] copyPuzzle = new int[puzzle.getBoard().length][puzzle.getBoard().length];

        for (int i = 0; i < puzzle.getBoard().length; i++)
        {
            for (int j = 0; j < puzzle.getBoard().length; j++)
                copyPuzzle[i][j] = puzzle.getBoard()[i][j];
        }
        Board copyBoard = new Board(copyPuzzle, 0, null);
        return copyBoard;
    }

    public  void printPuzzle (Board puzzle)
    {
        for (int i = 0; i < puzzle.getBoard().length; i++)
        {
            for (int j = 0; j < puzzle.getBoard().length; j++)
            {
                System.out.print(puzzle.getBoard()[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main (String [] args)
    {
        int puzzle[][] = {{2,1,6}
                         ,{5,0,4}
                         ,{3,8,7}};

        Board puzzleBoard = new Board(puzzle, 0, null);

        Move mover = new Move(puzzleBoard);
        mover.printPuzzle(puzzleBoard);
        System.out.println();
        System.out.println("option 1");
        mover.printPuzzle(mover.moveRight(puzzleBoard));
        System.out.println();
        System.out.println("option 2");
        mover.printPuzzle(mover.moveLeft(puzzleBoard));
        System.out.println();
        System.out.println("option 3");
        mover.printPuzzle(mover.moveUp(puzzleBoard));
        System.out.println();
        System.out.println("option 4");
        mover.printPuzzle(mover.moveDown(puzzleBoard));
    }


}
