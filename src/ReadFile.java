import java.io.BufferedReader;
import java.io.*;
import java.io.FileReader;
import java.io.IOException;

import java.util.*;
import java.util.stream.IntStream;

public class ReadFile {

    private static int dimensions;
    private Scanner scan;
    private String fileName;

    public ReadFile(String fileName)
    {
        this.fileName = fileName;
    }

    private int commentFound(String line)
    {
        char[] charArray = null;

        if (line != null)
        {
            charArray = line.toCharArray();
            if (charArray[0] == '#')
            {
                return (1);
            }
        }
        return (0);
    }

    private int emptyLine(String line)
    {
        if (line.isEmpty())
        {
            return (1);
        }
        return (0);
    }

    private int getDimensions(String line)
    {
        try
        {
            dimensions = Integer.parseInt(line);
            if (dimensions >= 2)
            {
                return (dimensions);
            }
        }
        catch (NumberFormatException e)
        {
            return (0);
        }
        return (0);
    }

    private int checkMatrix(int[][] matrix)
    {
        int rangeArr = dimensions * dimensions;
        int[] test = IntStream.range(0, rangeArr).toArray();
        int[] convertMatrix = new int [rangeArr];
        int index = 0;

        while (index < convertMatrix.length)
        {
            for (int row = 0; row < dimensions; row++)
            {
                for (int col = 0; col < dimensions; col++)
                {
                    convertMatrix[index] = matrix[row][col];
                    index++;
                }
            }
        }
        Arrays.sort(convertMatrix);
        for (int a = 0; a < convertMatrix.length; a++)
        {
            if (convertMatrix[a] == test[a])
            {
                continue;
            }
            else
            {
                System.out.println("Invalid matrix");
                return (0);
            }
        }
        return (1);
    }

    private String extractInfo(String line)
    {
        char[] charArray;
        String[] subInfo;
        String info;

        if (line != null)
        {
            if (line.contains("#"))
            {
                charArray = line.toCharArray();

                if (charArray[0] != '#')
                {
                    subInfo = line.split("#");
                    return subInfo[0];
                }
            }
            else
                return (line);
        }
        return (null);
    }

    public boolean openFile()
    {
        try
        {
            this.scan = new Scanner(new File(this.fileName));
            return true;
        }
        catch (Exception e)
        {
            System.err.println("\n \"" + fileName + "\" does not exist. File cannot be opened    ");
            return false;
        }
    }

    public int[][] getMatrix(String fileName)
    {
        int[][] matrix = null;

        try (BufferedReader br = new BufferedReader(new FileReader(fileName)))
        {
            String currentLine;
            String rawInput;
            String[] subStrings;
            int flag = 0;
            int row = 0;

            while ((rawInput = br.readLine()) != null)
            {
                currentLine = extractInfo(rawInput.trim());
                if ((getDimensions(currentLine) > 2) && flag == 0)
                {
                    dimensions = getDimensions(currentLine);
                    matrix = new int[dimensions][dimensions];
                    flag++;
                }
                else if (currentLine != null && flag != 0)
                {
                    int col = 0;

                    subStrings = currentLine.split("\\s+");
                    if (subStrings.length == dimensions)
                    {
                        for (String substring : subStrings)
                        {
                            matrix[row][col] = Integer.parseInt(substring);
                            col++;
                        }
                        row++;
                    }
                    else if (emptyLine(currentLine) == 1 || commentFound(currentLine) == 1)
                    {
                        continue;
                    }
                    else
                    {
                        System.out.println("Invalid line found");
                        return (null);
                    }
                }
            }
            if (flag == 0)
            {
                System.out.println("Dimensions not found");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return (matrix);
    }
}
