package maze

import spock.lang.Specification

import static maze.Direction.*

class CoordinateSpec extends Specification {
    def "gets coordinate for neighbor"() {
        given:
        def coordinate = new Coordinate(row:4, col:6)

        when:
        def result = coordinate.getNeighborToThe(NORTH)

        then:
        result.row == 3
        result.col == 6
        result.parent == coordinate
    }
}
