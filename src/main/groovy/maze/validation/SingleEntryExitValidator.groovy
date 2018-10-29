package maze.validation

import groovy.util.logging.Slf4j
import groovy.transform.Immutable

@Slf4j
@Immutable
class SingleEntryExitValidator {
    static def isValid(maze) {
        log.debug("SingleEntryExitValidator.isValid(${maze})")

        containsOnlyOneEntryAndExit(maze)
    }

    private static def containsOnlyOneEntryAndExit(maze) {
        log.debug("SingleEntryExitValidator.containsOnlyOneEntryAndExit(${maze})")

        def entries = maze.findAll(MapSymbol.StartPoint.toString())
        def exits = maze.findAll(MapSymbol.EndPoint.toString())

        if (entries.size() != 1 || exits.size() != 1) {
            throw new IllegalArgumentException("The maze must have one and only one entry and exit point. It contains ${entries.size()} entries. It contains ${exits.size()} exits.")
        }
    }

}
