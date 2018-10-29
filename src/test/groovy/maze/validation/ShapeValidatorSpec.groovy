package maze.validation

import spock.lang.Specification

class ShapeValidatorSpec extends Specification {

    def "isValid when maze is rectangular"() {
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
        ShapeValidator.isValid(maze)

        then:
        noExceptionThrown()
    }

    def "isInvalid when maze is not rectangular"() {
        given:
        def maze = """#
##########
"""
        when:
        ShapeValidator.isValid(maze)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "The maze is not rectangular. The line lengths are [1, 10]. They should all be the same."

    }
}
