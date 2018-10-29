package maze.validation

import spock.lang.Specification

class SymbolValidatorSpec extends Specification {
    def "isValid when maze is contains valid symbols only"() {
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
        SymbolValidator.isValid(maze)

        then:
        noExceptionThrown()
    }

    def "isInvalid when maze contains invalid symbols"() {
        given:
        def maze = """##########
#A---#---#
#-#-##-#-#
#-#-##-#-#
#-#----#B#
#-#-##-#-#
#----#---#
##########
"""
        when:
        SymbolValidator.isValid(maze)

        then:
        IllegalArgumentException ex = thrown()
        ex.message == "The maze can only contain the characters #.AB. Found -."
    }

}
