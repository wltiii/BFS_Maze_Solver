package maze.validation

enum MapSymbol {
    StartPoint('A'),
    EndPoint('B'),
    OpenRoad('.'),
    Wall('#')

    private final String symbol

    MapSymbol(String symbol) {
        this.symbol = symbol
    }

    /**
     * Returns the MapSymbol enum corresponding to the string value
     * @param symbol
     * @return MapSymbol or null if not found
     */
    static MapSymbol findBy(symbol) {
        return MapSymbol.values().find { e ->
            e.symbol == symbol
        }
    }

    String toString() {
        this.symbol
    }
}
