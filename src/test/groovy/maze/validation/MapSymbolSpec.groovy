package maze.validation

import spock.lang.Specification

import static maze.validation.MapSymbol.Wall
import static maze.validation.MapSymbol.EndPoint
import static maze.validation.MapSymbol.OpenRoad
import static maze.validation.MapSymbol.StartPoint

class MapSymbolSpec extends Specification {
    def "returns map symbol enums from valid string symbol"() {
        expect: '`A` represents the starting point'
        MapSymbol.findBy('A') == StartPoint
        MapSymbol.findBy('B') == EndPoint
        MapSymbol.findBy('.') == OpenRoad
        MapSymbol.findBy('#') == Wall

    }
    def "returns null if string symbol is invalid"() {
        expect: 'null to be null'
        MapSymbol.findBy(null) == null
        MapSymbol.findBy('') == null
        MapSymbol.findBy('X') == null
    }

}
