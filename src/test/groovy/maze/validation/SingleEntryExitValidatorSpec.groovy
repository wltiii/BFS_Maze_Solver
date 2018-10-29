package maze.validation

import spock.lang.Specification

class SingleEntryExitValidatorSpec extends Specification {
    def "is valid when maze contains one entry and one exit"() {
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
        SingleEntryExitValidator.isValid(maze)

        then:
        noExceptionThrown()
    }

    def "is invalid when maze contains multiple entries"() {
        given:
        def maze = """##########
#A...#...#
#.#.##.#.#
#.#.##.#.#
#.#....#B#
#.#.##.#.#
#.A..#...#
##########
"""
        when:
        SingleEntryExitValidator.isValid(maze)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "The maze must have one and only one entry and exit point. It contains 2 entries. It contains 1 exits."
    }

    def "is invalid when maze contains multiple exits"() {
        given:
        def maze = """##########
#A...#...#
#.#.##.#.#
#.#.##.#.#
#.#....#B#
#.#.##.#.#
#.B..#...#
##########
"""
        when:
        SingleEntryExitValidator.isValid(maze)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "The maze must have one and only one entry and exit point. It contains 1 entries. It contains 2 exits."
    }

    def "is invalid when maze contains no entry"() {
        given:
        def maze = """##########
#....#...#
#.#.##.#.#
#.#.##.#.#
#.#....#B#
#.#.##.#.#
#....#...#
##########
"""
        when:
        SingleEntryExitValidator.isValid(maze)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "The maze must have one and only one entry and exit point. It contains 0 entries. It contains 1 exits."
    }

    def "is invalid when maze contains no exit"() {
        given:
        def maze = """##########
#A...#...#
#.#.##.#.#
#.#.##.#.#
#.#....#.#
#.#.##.#.#
#....#...#
##########
"""
        when:
        SingleEntryExitValidator.isValid(maze)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "The maze must have one and only one entry and exit point. It contains 1 entries. It contains 0 exits."
    }
}
