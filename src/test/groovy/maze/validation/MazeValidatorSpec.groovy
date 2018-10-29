package maze.validation

import spock.lang.Specification

class MazeValidatorSpec extends Specification {
    def "is valid maze"() {
        given:
        def maze = """##########
#A...#...#
#.#.##.#.#
#.#.##.#.#
#.#....#B#
#.#.##.#.#
#....#...#
##########
"""
        when:
        MazeValidator.isValid(maze)

        then:
        ShapeValidatorSpec
    }


    def "isInvalid when maze is null"() {
        given:
        def maze = null

        when:
        MazeValidator.isValid(maze)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "The maze is null or empty."
    }

    def "isInvalid when maze is empty"() {
        given:
        def maze = ""

        when:
        MazeValidator.isValid(maze)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "The maze is null or empty."
    }
}
