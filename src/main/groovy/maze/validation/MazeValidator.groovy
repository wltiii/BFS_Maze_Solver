package maze.validation

import groovy.util.logging.Slf4j
import groovy.transform.Immutable

@Slf4j
@Immutable
class MazeValidator {
    static def isValid(maze) {
        log.debug("MazeValidator.isValid(${maze})")

        if (maze?.trim()) {
            ShapeValidator.isValid(maze)
            SingleEntryExitValidator.isValid(maze)
            SymbolValidator.isValid(maze)
        }
        else {
            throw new IllegalArgumentException("The maze is null or empty.")
        }
    }

}
