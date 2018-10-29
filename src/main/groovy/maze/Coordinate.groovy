package maze

import groovy.transform.Immutable

@Immutable
class Coordinate {
    int row
    int col
    Coordinate parent = null

    Coordinate getNeighborToThe(Direction direction) {
        def row = this.row +  direction.row
        def col = this.col +  direction.col
        new Coordinate(row, col, this)

    }
}
