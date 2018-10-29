package maze

enum Direction {
    EAST(0, 1),
    WEST(0, -1),
    NORTH(-1, 0),
    SOUTH (1, 0)

    private final int row
    private final int col

    Direction(int row, int col) {
        this.row = row
        this.col = col
    }
}
