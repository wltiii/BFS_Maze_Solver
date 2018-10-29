package maze

import spock.lang.Specification

import static maze.Direction.EAST
import static maze.Direction.NORTH
import static maze.Direction.SOUTH
import static maze.Direction.WEST

class DirectionSpec extends Specification {
    def "properly defines possible movements"() {
        expect:
        NORTH.row == -1
        NORTH.col == 0

        SOUTH.row == 1
        SOUTH.col == 0

        EAST.row == 0
        EAST.col == 1

        WEST.row == 0
        WEST.col == -1
    }
}
