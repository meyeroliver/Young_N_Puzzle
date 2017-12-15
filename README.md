# Young_N_Puzzle

The goal of this project is to solve a N-puzzle "sliding tile puzzle". To solve this problem I learnt about the A*
search algorithm. This is an efficient search algorithm that finds the best possible path between two points, but this
also depends on the heuristics use. This was a team project and the heuristics we decided to use were:
1) Misplaced tiles
2) Manhattan Distance
3) Rows and Columns

How to run Program.

1) cd into src.
2) To compile enter the following in the command line "javac -g *.java".
3) To run the progrm enter in the following in the command line "java Main".
4) Next, user will be prompted to enter in the a file. Please enter in the absolute path.
  * There are given files in the root.
  * Also, there is a python file "npuzzle-gen.py" that te user can use to generate a puzzle.
    -> How to run "npuzzle-gen.py"
    4.1) Enter following into the terminal "python npuzzle-gen.py -h", this will list all the commands the user can execute.
    *  Python npuzzle-gen.py 3 -s "creates solvable 3x3 puzzle"
    *  Python npuzzle-gen.py 3 -u "creates unsolvable 3x3 puzzle"
    4.2) Copy output generated and paste into textfile.
    4.3) Go back to 3). 
5) Next, user will be prompted to choose a heuristic.
6) Depending on whether the puzzle is solvable. It will generate the solution in steps.

Disclaimer.

The npuzzle-gen.py is not of my creation.
Also its best to run with 3x3 puzzle. Anything bigger will require you to run over lunch.
