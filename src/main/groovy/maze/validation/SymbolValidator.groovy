package maze.validation

import groovy.util.logging.Slf4j
import groovy.transform.Immutable

@Slf4j
@Immutable
class SymbolValidator {
    static def isValid(maze) {
        log.debug("SymbolValidator.isValid(${maze})")

        containsValidSymbolsOnly(maze)
    }

    private static def containsValidSymbolsOnly(maze) {
        log.debug("SymbolValidator.isRectangular(${maze})")

        String stripped = maze.replaceAll(/[\r]?\n/, '')

        String invalidChar = stripped.chars.find { c ->
            MapSymbol.findBy(c) == null ? c : null
        }

        if (invalidChar) {
            throw new IllegalArgumentException("The maze can only contain the characters #.AB. Found ${invalidChar}.")
        }
    }

}
