package maze.validation

import groovy.util.logging.Slf4j
import groovy.transform.Immutable

@Slf4j
@Immutable
class ShapeValidator {
    static def isValid(maze) {
        log.debug("ShapeValidator.isValid(${maze})")

        isRectangular(maze)
    }

    private static def isRectangular(maze) {
        log.debug("ShapeValidator.isRectangular(${maze})")

        List<String> rows = maze.split(/[\r]?\n/)

        def unconforming = rows.findAll { row ->
            return row.size() != rows[0].size()
        }

        if (unconforming.size() > 0) {
            def sizes = rows.collect { row ->
                row.size()
            }

            throw new IllegalArgumentException("The maze is not rectangular. The line lengths are ${sizes}. They should all be the same.")
        }
    }
}
