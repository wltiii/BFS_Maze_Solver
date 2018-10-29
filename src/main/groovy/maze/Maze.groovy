package maze

import maze.validation.MapSymbol

class Maze {
    def maze = []
    def visited
    Coordinate start

    Maze(String maze) {
        maze.eachLine { line, row ->
            this.maze << line.chars

            if (!this.start) {
                this.start = getCoordinateFor(line, row, MapSymbol.StartPoint.toString())
            }
         }

        this.visited = new Boolean[this.maze.size()][this.maze[0].size()]
    }

    private static getCoordinateFor(line, row, symbol) {
        if (line.indexOf(symbol) >= 1) {
            return new Coordinate(row: row, col: line.indexOf(symbol))
        }
        null
    }

    int getHeight() {
        this.maze.size()
    }

    int getWidth() {
        this.maze[0].size()
    }

    boolean isStart(Coordinate coordinate) {
        return this.maze[coordinate.row][coordinate.col] == MapSymbol.StartPoint.toString()
    }

    boolean isExit(Coordinate coordinate) {
        return this.maze[coordinate.row][coordinate.col] == MapSymbol.EndPoint.toString()
    }

    boolean isVisited(Coordinate coordinate) {
        return this.visited[coordinate.row][coordinate.col]
    }

    void setVisited(Coordinate coordinate) {
        this.visited[coordinate.row][coordinate.col] = true
    }

    boolean isWall(Coordinate coordinate) {
        return this.maze[coordinate.row][coordinate.col] == MapSymbol.Wall.toString()
    }

    boolean isValidLocation(Coordinate coordinate) {
        if (coordinate.row < 0 || coordinate.row >= this.height || coordinate.col < 0 || coordinate.col >= this.width) {
            return false
        }
        return true
    }

    boolean isInvalidLocation(Coordinate coordinate) {
        return !isValidLocation(coordinate)
    }

    String markPath(List<Coordinate> steps) {
        steps.each { step ->
            if (!isStart(step) && !isExit(step)) {
                this.maze[step.row][step.col] = '@'
            }
        }
        this.toString()
    }

    @Override
    String toString() {
        def lines = []
        this.maze.each { line ->
            lines << line.join()
        }
        lines.join('\n')
    }
}
