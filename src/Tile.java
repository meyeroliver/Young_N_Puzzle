public class Tile extends Position
{
    private final int value;
    private Position position;

    public Tile (int value, Position position)
    {
        this.value = value;
        this.position = position;
    }

    public int getValue()
    {
        return value;
    }
}
