package maze

import spock.lang.Specification

import static maze.Direction.NORTH

class MazeSpec extends Specification {
    def "properly initializes"() {
        given:
        def layout = this.exampleLayout

        when:
        def maze = new Maze(layout)

        then:
        maze.height == 8
        maze.width == 10
        maze.start == new Coordinate(row: 1, col:1)
        maze.visited == new Boolean[8][10]
    }

    def "identifies coordinate as exit"() {
        given:
        def layout = this.exampleLayout
        def endCoordinate = new Coordinate(row: 4, col:8)

        when:
        def maze = new Maze(layout)

        then:
        maze.isExit endCoordinate
    }

    def "identifies coordinate as exit ignores parent"() {
        given:
        def layout = this.exampleLayout
        def endCoordinate = new Coordinate(row: 4, col:8, parent: new Coordinate(row: 1, col:1))

        when:
        def maze = new Maze(layout)

        then:
        maze.isExit endCoordinate
    }

    def "identifies coordinate as wall"() {
        given:
        def layout = this.exampleLayout
        def wallCoordinate = new Coordinate(row: 0, col:0)

        when:
        def maze = new Maze(layout)

        then:
        maze.isWall wallCoordinate
    }

    def "identifies coordinate as wall ignores parent"() {
        given:
        def layout = this.exampleLayout
        def wallCoordinate = new Coordinate(row: 0, col:0, parent: new Coordinate(row: 1, col:1))

        when:
        def maze = new Maze(layout)

        then:
        maze.isWall wallCoordinate
    }

    def "correctly identifies coordinate validity"() {
        given:
        def maze = new Maze(this.exampleLayout)

        expect:
        maze.isValidLocation(new Coordinate(row: 0, col:0))
        maze.isValidLocation(new Coordinate(row: maze.height - 1, col:0))
        maze.isValidLocation(new Coordinate(row: 0, col:maze.width - 1))
        !maze.isValidLocation(new Coordinate(row: -1, col:0))
        !maze.isValidLocation(new Coordinate(row: 0, col:-1))
        !maze.isValidLocation(new Coordinate(row: maze.height, col:0))
        !maze.isValidLocation(new Coordinate(row: 0, col:maze.width))
    }

    def "marks coordinate as visited"() {
        given:
        def maze = new Maze(this.exampleLayout)

        when:
        maze.setVisited(maze.start)

        then:
        maze.visited[maze.start.row][maze.start.col] == true
        maze.isVisited(maze.start)
    }

    def "returns string representation of maze"() {
        when:
        def result = new Maze(this.exampleLayout).toString()

        then:
        result == this.exampleLayout.trim()
    }

    def "gets maze with path marked"() {
        given:
        def maze = new Maze(this.exampleLayout)
        def steps = [
                new Coordinate(row: 4, col:8, parent: new Coordinate(row: 5, col:8)),
                new Coordinate(row: 5, col:8, parent: new Coordinate(row: 6, col:8)),
                new Coordinate(row: 6, col:8, parent: new Coordinate(row: 6, col:7)),
                new Coordinate(row: 6, col:7, parent: new Coordinate(row: 6, col:6)),
                new Coordinate(row: 6, col:6, parent: new Coordinate(row: 5, col:6)),
                new Coordinate(row: 5, col:6, parent: new Coordinate(row: 4, col:6)),
                new Coordinate(row: 4, col:6, parent: new Coordinate(row: 4, col:5)),
                new Coordinate(row: 4, col:5, parent: new Coordinate(row: 4, col:4)),
                new Coordinate(row: 4, col:4, parent: new Coordinate(row: 4, col:3)),
                new Coordinate(row: 4, col:3, parent: new Coordinate(row: 3, col:3)),
                new Coordinate(row: 3, col:3, parent: new Coordinate(row: 2, col:3)),
                new Coordinate(row: 2, col:3, parent: new Coordinate(row: 1, col:3)),
                new Coordinate(row: 1, col:3, parent: new Coordinate(row: 1, col:2)),
                new Coordinate(row: 1, col:2, parent: new Coordinate(row: 1, col:1)),
                new Coordinate(row: 1, col:1, parent: null)
        ]

        when:
        def result = maze.markPath(steps)

        then:
        result == exampleWithPath.trim()
    }

    def getExampleLayout() {
        """##########
#A...#...#
#.#.##.#.#
#.#.##.#.#
#.#....#B#
#.#.##.#.#
#....#...#
##########
"""
    }

    def getExampleWithPath() {
        """##########
#A@@.#...#
#.#@##.#.#
#.#@##.#.#
#.#@@@@#B#
#.#.##@#@#
#....#@@@#
##########
"""

    }
}
