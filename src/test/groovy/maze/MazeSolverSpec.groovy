package maze

import groovy.json.JsonSlurper
import spock.lang.Specification

class MazeSolverSpec extends Specification {
    def "solves maze one"() {
        given:
        def maze = new Maze(this.mazeOneSample)
        def oneMinuteRange = 1..60000

        when:
        def result = new MazeSolver().solve(maze)

        then:
        def json = new JsonSlurper().parseText(result)
        json.solution == mazeOneSolution.trim()
        json.stepsTaken == 14
        oneMinuteRange.containsWithinBounds(json.solutionInMillis)
    }

    def "solves maze two"() {
        given:
        def maze = new Maze(this.mazeTwoSample)
        def oneMinuteRange = 1..60000

        when:
        def result = new MazeSolver().solve(maze)

        then:
        def json = new JsonSlurper().parseText(result)
        json.solution == mazeTwoSolution.trim()
        json.stepsTaken == 219
        oneMinuteRange.containsWithinBounds(json.solutionInMillis)
    }

    def "solves maze three"() {
        given:
        def maze = new Maze(this.mazeThreeSample)
        def oneMinuteRange = 1..60000

        when:
        def result = new MazeSolver().solve(maze)

        then:
        def json = new JsonSlurper().parseText(result)
        json.solution == mazeThreeSolution.trim()
        json.stepsTaken == 302
        oneMinuteRange.containsWithinBounds(json.solutionInMillis)
    }

    def getMazeOneSample() {
        """##########
#A...#...#
#.#.##.#.#
#.#.##.#.#
#.#....#B#
#.#.##.#.#
#....#...#
##########
"""
    }

    def getMazeTwoSample() {
        """###############B#############################################
##.....########.#......................................#...##
##.###.#........####################################.#.#.#.##
##.###.#.#########..........#########.......########.#.#.#.##
##.#####...........########.#.......#.#####.########.#.#.#.##
##.########################.#.#####.#.#...#.########.#.#.#.##
##............................#####.#.##.##.########.#.#.#.##
##.###.############################.#.##.##.########.#.#.#.##
##.###.##...#...#...#...#...#.......#.##.##.########.#.#.#.##
##.###....#...#...#...#...#...#######.##.##.########.#.#.#.##
##.##################################.##.##.########.#.#.#.##
##.......................................##.########.#.#.#.##
###########################################.########.#.#.#.##
###...............................#########..........#.#.#.##
########################.###########################.#.#.#.##
#........................#...........................#.#.#.##
#.######################.#############################.#.#.##
#.#..........#.........................................#.#.##
#.#.########.#.#########################################.#.##
#.#........#.#.#.........................................#.##
#.##########.#.#.#########################################.##
#............#.#.##........................................##
##############.#.#############################.#####.########
#..............................................#####........#
########################A####################################
"""
    }

    def getMazeThreeSample() {
        """#######################################################################################################################################################################################
#A......#.....#.....#.#.....#.#...#.#.......#.....#.#...#.......#...........#...#.............#.....#.......#.........#.....#.....#...........#.#...................#.....#...........#
#.#######.#.#.#.###.#.#####.#.#.#.#.#####.#.#.###.#.#######.#.###.#.#.#.#.#####.#.#########.#.###.#.#.#.#.#.#.#.#.###.#.#####.#.#.#.#.#.#.#.#.#.#.#.###.#####.#.#.#.#.#.#.#.#.#.#.#.#.#
#.....#.#.....#.#...#.......#.......#...#...#.#.......#.....#.....#.........#...#...#.....#.........#.#...#.#.#.#.#...#.#...#.....#...#...#...#.#...#.#.#.#...........#.#.......#.#...#
#.#####.#.#.#.#.#.###.###.#.###.#.#.#.###.#.#.#.#.#.#.###.#.#.#######.#.#.#.###.#.#.#.#.#######.#.###.###.#.#.###.#####.#.#.#.#####.###.#.#.#########.#.#.#.###.#####.#.#.#####.#.#.###
#...#...#.#.#.....#...#.............#.#.......#.......#.#...#.#.........#.......#.#.....#.......#.......................#.#...#.#.#...#...#.#.#...#...#.......#.........#...#.#.....#.#
#.#.###.#.#.#.#.#.#.#.#.#.###.#.#.#.#.#.###.###.###.#.#.#.#.###.###.###.#.#.#####.#.#.#.#.#.#.#.#.###.#.###########.#.#.#.#.#.#.#.###.###.#.#.#.#.#.#.#.#.#.#.#####.###.#.###.#.#####.#
#...#...#...#...#.#.#.#...#...#...#...#.....#...................#...#.#.....#...........#.....#.#.#...#.#...#.........#.#.......#...........#...#.....#...#.#...............#.#.......#
#.#.#.#.###.#.#.###.#######.#.#.#.#.#.#.###.#.###.#.#.#.###.#.#.#.#.#.#.#####.#.#.###.#.#.###.#.#.#.#.#.###.###.#.#.#.#####.#.###########.#.#.#.#########.#.#.###.###.###.#.#.###.#.#.#
#.#.........#...#.....#.....#.#...#...#.....#...............#...#.......#.#.#.......#.#...#.....#.#.#.........#.#.........#...#...#.........#.#...#.#.#...........#.....#.......#.....#
#.#.#.#.#.###.#.#.#.#.#.#.###.#########.#.#.###.#.#.#####.#####.#.#####.#.#.###.#.#.#.#.#.#.#.#.#.#.#.#.#.###.###.###.#.#.###.###.#.#.#.#.#.###.#.#.#.#.#.#####.###.#.#.#######.#.###.#
#...#.........#...#.#...#.#.#...........#.#.#...........#.......#.#.#.....#...#.#.#.#...#.......#.........#...#.....#...#.....#.#.....#.#...#.....#.#...#.....#.#.#.#.............#...#
#.#.###########.###.#.###.#.#####.#.#.#.###.#.###.#.#.#.#########.#.#.#####.#.#.#.#.#.#.#####.#.#.#.###.#.#.#.#.###############.#.###.#.#.#.#.#.###.###.#.#.#.#.#.#.#####.#.#.###.#.###
#.....#.#...........#.#.............#.#...........#.....#.#.....#...#.#.#.#...........#.#.....#...#.....#...#.....#...........#.#.#...#.........#.#.....#...#.......#.....#...#.......#
#####.#.#.#.#.#.#####.#.#####.#.#.#.#.###.#.#.###.#######.#.#.#####.#.#.#.#.#.#####.###.#.###.#.#.#.#####.#.#.###.#.###.#.###.#.#.###.#.###.###.#.#.#.#.###.#.#####.#.#.#.#####.#.###.#
#.....#.#.....#.#.......#.......#.....#...#...#...............#.....#...........#.........#.....#.#.#.............#.#...#.#.....#.#...#...#...#.........#.....#...#...#...........#...#
#.#.#.###.#.###.#.#.#.###.#.###.#.#.#.###.###.#.###.#.#.#.#####.#.#.#.###.#####.#####.#.#.#.###.#.###.###.#####.#.#.#.#.#.###.###.#.###.#.#.#.#.#.#####.#####.#.#.#.###.#.#.#.#####.###
#...#.....#.#.......#.....#.#.....#...#...#.#.#.....#...#.#...#.#.....................#.....#.....#.....#.......#.........#...#...#...#.#.#.#...#.#.#...#...#.....#.#.......#...#.....#
#####.###.###.#.#.#.#.#.#.###.#.#.#.#####.#.#.#.#.#.#######.#.#.#.#.#.#######.###.#.###.#.#.###.#.#.#.#.#.#####.#.#.#.###.#.###.###.#.###.#.###.#.#.#.#.###.#.###.###.#####.###.#.#####
#.....#.....#...#.....#.#...#...#.........#...................#.....#...#.........#.....#...........#...#.#.......#.#...#...#...............#.......#...#.........#.............#.....#
#.#.#.#.#######.#.#.#########.#######.###.#####.###.#.###.#.#######.#.###.#.###.###.###.#####.#####.###.#.#.#.#.#.#.#.###.#.#.#######.#.#.#.#.#.#.#.#.#####.#.#.#.#.#.#.###.#.#.###.#.#
#.#.#.......#...#.#...#.......#.................#.....#...#.......#.#.....#.....#.#...#.#.....#...#.#.......#...#.....#.....#.....#.......#.....#...#.#...#.....#...#.#.#...#...#.....#
###.#.#####.#.###.###.#.#.###.#.###.#####.#.#.#######.#.#########.#####.#.#.#.###.#.#.#.###.#######.#.#########.#.#.#.#.#.#.#####.###.###.###.#.#.#.#.#.#.#.###.#.#.#.#.#####.#.#.###.#
#.#...#.....#.....#.#.....#...#...#.#...#...#.#.#.#.....#.....#.#.#.#.....#.................#.#.#...#...#...#.#.#.#.#.#...#...#.#.............#.#...#...#.#.#.......#.........#.#.....#
#.###.#.###.#.#.#.#.###.#.#.###.#####.#.###.#.#.#.#.#.###.#.#.#.#.#.#.#.#.#.#.#####.###.#.###.#.#.###.#.#.#.#.#.#.#.#.#.###.#.#.###.#.#.#.#######.#.###.#.#.#.###.#.#.#.###.###.#.#.#.#
#.#...#.....#.....#.#...#...#.......#...#.....#.#.#.....#.#...#...#...#.#.........#.....#...#.#...#...#...#.#.#.......#...#.#...#...#.#...#.....#.......#.......#.#.....#.#...#.#.....#
#.#.#.#.#.#.#.###.#.###.#.#.#.###.###.#.#.###.###.#.#.#.#.###.#.#.#.#.#.#.#########.#.#.#.#.#.#######.#.###.#.#.#.#.#.#.###.#.###.#######.#.#.#.#.###.#.#.#.#.#.#.#.#####.###.#.#.###.#
#.....#.....#.#...#.#.#...#.....#.....#.#...#.....#.....#.....#.#.#...#...#.#.....#.#.#...#...#.......#.....#...........#...#.#...#.......#.......#.#.#...#.#...#.#.#...........#.....#
#.#####.#####.#####.#.###.#########.#.#.#.#.###.#.#.#.#.#.#.#.#####.###.#.#.#.###.#.###.#.#####.###.#####.#####.#.###.###.#.#.#.#.#.###.#.#.#####.#.#.###.###.###.#.#.#.#.#.###.###.#.#
#.....#...#...#.#...#.........#.................#.#.....#.#.....#.........#...#.....#...#.#.....#.......#.......#.....#.#.#.......#.#...#.#...#.#.........#.........#...#.....#.....#.#
#.###.#####.#.#.#.#.#.#######.#.#####.#########.#.#.###.#.#####.#.#####.###.#.#.#.#.###.#.#.#.#.###.#.#####.#.###.###.#.#.#.###.#.#.#.#.#.#.#.#.#.#####.#.#.###.#.#.#.#.#.#.#.###.###.#
#.#...#.#.....#...#.#.#.......#.#.................#...#.....#...............#.........#...#.#.#...#.#...#...#.........#.....#...........#.#...........#.....#.#.#.#...#...#...#.#.#...#
#.#.#.#.#.###.#######.#.#.#.###.#.#.#.###.#.#.###.###.#.###.#.#####.#.#.#.#.#.###.#########.#.#.#.#.#.#.#######.###.#.#.###.#.#.###.#.###.#.#.###.#.#.#.#.#.#.#.#.#.###.#####.#.###.#.#
#.#.....#.....#.#.......#...#...#...#.#...#.....#.......#...#...#...#.#.#...#...#.....#.#...#...........#.........#...#.#...#...#...#...#...........#.#.#...#...........#.....#.#.....#
###.#.###.#####.#.#.#.#.#.###.#.#.#####.#.#.#.#.#.#.#.###.###.#.#.###.#.###.###.#####.#.#.###.#.#.###.#.#.#.###.#####.###.#####.#.#.###.#.#####.#.#.#.#####.###.###.###.#.#.#.#.#.#.###
#.........#.....#.....#...#...............#.#...#.#.#.....#.#...#...#.#...............#.......#.......#.#...#.#...........#.#.#...............#...#.........#...............#...#...#.#
#.#.#.###.#.#####.#.#.#.#.#.#.###.###.###.#####.#.#####.###.#.#.###.#.###.###.#####.#####.#####.#########.###.#.#.#.#.#.#.#.#.#.#.#.#.###.#.#.###.#####.###.#####.###.#.###.###.#.#.#.#
#.....#.#.............#...#.......#.#.............#.#...#.#...#...#.#.....#.#.#...#.#.....#...#...........#.....#.#.#...#...................#.....#.....#...............#.....#.#.....#
#.#####.#.###.#.#######.###.###.#.#.#.###.###.#.###.#.#.#.###.#.#.#.#####.#.###.###.#########.#.#####.#.#.#.#.#.#.#.#####.#########.#.#.#.#.###.###.#.#####.#.#.###.#####.#.#.###.###.#
#...#.....#.#.#.#...............#.........#.....#.......#.........#...........#...#.......#.#...#.......#.....#...#.#...#.#...#.#.....#...........#.................#...#.#.#.#...#.#.#
#.#.#.#####.#.###.###.#.#.#####.#.#####.###.#.###.#.#######.#.#.###.###.###.#.#.#.#.#.#.#.#.#.#.#.#.#######.#######.#.#.###.###.#.###.#.###.#.#.#.#.#.#######.#.#.###.#.###.#####.#.#.#
#.....#.#.....#...#...#.#.......#...#.............#...#.....#...#.#.......#.....#...#...#.#.....#.#...........#.......#.#...#...#.#...#.#.#.....#...............#...#.#.#...#...#.....#
#####.#.#.#.###.#.###.#.#####.#######.#############.#.#.#.#.#.###.#.#.###.#.#####.#.#.#.#####.#.#.###.###.#.#.#.###.#.#.###.#.#.#.#.#.#.#.#.#.###.###.#.#######.###.#.#####.#######.###
#.....#...........#.....#.....#.......#...#...#.......#.....#.#...#.#.#...#.#...#.....#.......#...#...#...#.#.#.#.....#.....#...#.#...#.......#...#...#.....#...#...#.........#.#....B#
#######################################################################################################################################################################################
"""
    }

    def getMazeOneSolution() {
        """##########
#A@@.#...#
#.#@##.#.#
#.#@##.#.#
#.#@@@@#B#
#.#.##@#@#
#....#@@@#
##########
"""
    }

    def getMazeTwoSolution() {
        """###############B#############################################
##.....########@#...................................@@@#...##
##.###.#@@@@@@@@####################################@#@#.#.##
##.###.#@#########@@@@@@@@@@#########@@@@@@@########@#@#.#.##
##.#####@@@@@@@@@@@########@#.......#@#####@########@#@#.#.##
##.########################@#.#####.#@#...#@########@#@#.#.##
##@@@@@@@@@@@@@@@@@@@@@@@@@@..#####.#@##.##@########@#@#.#.##
##@###.############################.#@##.##@########@#@#.#.##
##@###.##...#...#...#...#...#.......#@##.##@########@#@#.#.##
##@###....#...#...#...#...#...#######@##.##@########@#@#.#.##
##@##################################@##.##@########@#@#.#.##
##@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@...##@########@#@#.#.##
###########################################@########@#@#.#.##
###...............................#########@@@@@@@@@@#@#.#.##
########################.###########################.#@#.#.##
#........................#...........................#@#.#.##
#.######################.#############################@#.#.##
#.#..........#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#.#.##
#.#.########.#@#########################################.#.##
#.#........#.#@#.........................................#.##
#.##########.#@#.#########################################.##
#............#@#.##........................................##
##############@#.#############################.#####.########
#.............@@@@@@@@@@@......................#####........#
########################A####################################"""
    }

    def getMazeThreeSolution() {
        """#######################################################################################################################################################################################
#A......#.....#.....#.#.....#.#...#.#.......#.....#.#...#.......#...........#...#.............#.....#.......#.........#.....#.....#...........#.#...................#.....#...........#
#@#######.#.#.#.###.#.#####.#.#.#.#.#####.#.#.###.#.#######.#.###.#.#.#.#.#####.#.#########.#.###.#.#.#.#.#.#.#.#.###.#.#####.#.#.#.#.#.#.#.#.#.#.#.###.#####.#.#.#.#.#.#.#.#.#.#.#.#.#
#@....#.#.....#.#...#.......#.......#...#...#.#.......#.....#.....#.........#...#...#.....#.........#.#...#.#.#.#.#...#.#...#.....#...#...#...#.#...#.#.#.#...........#.#.......#.#...#
#@#####.#.#.#.#.#.###.###.#.###.#.#.#.###.#.#.#.#.#.#.###.#.#.#######.#.#.#.###.#.#.#.#.#######.#.###.###.#.#.###.#####.#.#.#.#####.###.#.#.#########.#.#.#.###.#####.#.#.#####.#.#.###
#@..#...#.#.#.....#...#.............#.#.......#.......#.#...#.#.........#.......#.#.....#.......#.......................#.#...#.#.#...#...#.#.#...#...#.......#.........#...#.#.....#.#
#@#.###.#.#.#.#.#.#.#.#.#.###.#.#.#.#.#.###.###.###.#.#.#.#.###.###.###.#.#.#####.#.#.#.#.#.#.#.#.###.#.###########.#.#.#.#.#.#.#.###.###.#.#.#.#.#.#.#.#.#.#.#####.###.#.###.#.#####.#
#@@@#...#...#...#.#.#.#...#...#...#...#.....#...................#...#.#.....#...........#.....#.#.#...#.#...#.........#.#.......#...........#...#.....#...#.#...............#.#.......#
#.#@#.#.###.#.#.###.#######.#.#.#.#.#.#.###.#.###.#.#.#.###.#.#.#.#.#.#.#####.#.#.###.#.#.###.#.#.#.#.#.###.###.#.#.#.#####.#.###########.#.#.#.#########.#.#.###.###.###.#.#.###.#.#.#
#.#@@@......#@@@#.....#.....#.#...#...#.....#...............#...#.......#.#.#.......#.#...#.....#.#.#.........#.#.........#...#...#.........#.#...#.#.#...........#.....#.......#.....#
#.#.#@#.#.###@#@#.#.#.#.#.###.#########.#.#.###.#.#.#####.#####.#.#####.#.#.###.#.#.#.#.#.#.#.#.#.#.#.#.#.###.###.###.#.#.###.###.#.#.#.#.#.###.#.#.#.#.#.#####.###.#.#.#######.#.###.#
#...#@@@@@@@@@#@..#.#...#.#.#...........#.#.#...........#.......#.#.#.....#...#.#.#.#...#.......#.........#...#.....#...#.....#.#.....#.#...#.....#.#...#.....#.#.#.#.............#...#
#.#.###########@###.#.###.#.#####.#.#.#.###.#.###.#.#.#.#########.#.#.#####.#.#.#.#.#.#.#####.#.#.#.###.#.#.#.#.###############.#.###.#.#.#.#.#.###.###.#.#.#.#.#.#.#####.#.#.###.#.###
#.....#.#......@....#.#.............#.#...........#.....#.#.....#...#.#.#.#...........#.#.....#...#.....#...#.....#...........#.#.#...#.........#.#.....#...#.......#.....#...#.......#
#####.#.#.#.#.#@#####.#.#####.#.#.#.#.###.#.#.###.#######.#.#.#####.#.#.#.#.#.#####.###.#.###.#.#.#.#####.#.#.###.#.###.#.###.#.#.###.#.###.###.#.#.#.#.###.#.#####.#.#.#.#####.#.###.#
#.....#.#.....#@#.......#.......#.....#...#...#...............#.....#...........#.........#.....#.#.#.............#.#...#.#.....#.#...#...#...#.........#.....#...#...#...........#...#
#.#.#.###.#.###@#.#.#.###.#.###.#.#.#.###.###.#.###.#.#.#.#####.#.#.#.###.#####.#####.#.#.#.###.#.###.###.#####.#.#.#.#.#.###.###.#.###.#.#.#.#.#.#####.#####.#.#.#.###.#.#.#.#####.###
#...#.....#.#..@@@..#.....#.#.....#...#...#.#.#.....#...#.#...#.#.....................#.....#.....#.....#.......#.........#...#...#...#.#.#.#...#.#.#...#...#.....#.#.......#...#.....#
#####.###.###.#.#@#.#.#.#.###.#.#.#.#####.#.#.#.#.#.#######.#.#.#.#.#.#######.###.#.###.#.#.###.#.#.#.#.#.#####.#.#.#.###.#.###.###.#.###.#.###.#.#.#.#.###.#.###.###.#####.###.#.#####
#.....#.....#...#@@@..#.#...#...#.........#...................#.....#...#.........#.....#...........#...#.#.......#.#...#...#...............#.......#...#.........#.............#.....#
#.#.#.#.#######.#.#@#########.#######.###.#####.###.#.###.#.#######.#.###.#.###.###.###.#####.#####.###.#.#.#.#.#.#.#.###.#.#.#######.#.#.#.#.#.#.#.#.#####.#.#.#.#.#.#.###.#.#.###.#.#
#.#.#.......#...#.#@@@#.......#.................#.....#...#.......#.#.....#.....#.#...#.#.....#...#.#.......#...#.....#.....#.....#.......#.....#...#.#...#.....#...#.#.#...#...#.....#
###.#.#####.#.###.###@#.#.###.#.###.#####.#.#.#######.#.#########.#####.#.#.#.###.#.#.#.###.#######.#.#########.#.#.#.#.#.#.#####.###.###.###.#.#.#.#.#.#.#.###.#.#.#.#.#####.#.#.###.#
#.#...#.....#.....#.#@@@@@#...#...#.#...#...#.#.#.#.....#.....#.#.#.#.....#.................#.#.#...#...#...#.#.#.#.#.#...#...#.#.............#.#...#...#.#.#.......#.........#.#.....#
#.###.#.###.#.#.#.#.###.#@#.###.#####.#.###.#.#.#.#.#.###.#.#.#.#.#.#.#.#.#.#.#####.###.#.###.#.#.###.#.#.#.#.#.#.#.#.#.###.#.#.###.#.#.#.#######.#.###.#.#.#.###.#.#.#.###.###.#.#.#.#
#.#...#.....#.....#.#...#@@@#@@@@@..#...#.....#.#.#.....#.#...#...#...#.#.........#.....#...#.#...#...#...#.#.#.......#...#.#...#...#.#...#.....#.......#.......#.#.....#.#...#.#.....#
#.#.#.#.#.#.#.###.#.###.#.#@#@###@###.#.#.###.###.#.#.#.#.###.#.#.#.#.#.#.#########.#.#.#.#.#.#######.#.###.#.#.#.#.#.#.###.#.###.#######.#.#.#.#.###.#.#.#.#.#.#.#.#####.###.#.#.###.#
#.....#.....#.#...#.#.#...#@@@..#@@@..#.#...#.....#.....#@@@..#.#.#...#...#.#.....#.#.#...#...#.......#.....#...........#...#.#...#.......#.......#.#.#...#.#...#.#.#@@@@@@@@@@@#..@@@#
#.#####.#####.#####.#.###.#########@#.#.#.#.###.#.#.#.#.#@#@#.#####.###.#.#.#.###.#.###.#.#####.###.#####.#####.#.###.###.#.#.#.#.#.###.#.#.#####.#.#.###.###.###.#.#@#.#.#.###@###@#@#
#.....#...#...#.#...#.........#....@@@..........#.#.....#@#@@@@@#.........#...#.....#...#.#.....#.......#.......#.....#.#.#.......#.#...#.#...#.#@@@@@@@..#@@@@@@@@@#@..#.....#@@@@@#@#
#.###.#####.#.#.#.#.#.#######.#.#####@#########.#.#.###.#@#####@#.#####.###.#.#.#.#.###.#.#.#.#.###.#.#####.#.###.###.#.#.#.###.#.#.#.#.#.#.#.#.#@#####@#.#@###.#.#@#@#.#.#.#.###.###@#
#.#...#.#.....#...#.#.#.......#.#....@@@@@@@@@@@@@#...#@@@..#..@@@@@@@@@....#.........#...#.#.#...#.#...#...#.........#.....#...........#.#@@@@@@@....#@@@@@#.#.#.#@@@#...#...#.#.#..@#
#.#.#.#.#.###.#######.#.#.#.###.#.#.#.###.#.#.###@###.#@###.#.#####.#.#@#.#.#.###.#########.#.#.#.#.#.#.#######.###.#.#.###.#.#.###.#.###.#@#.###.#.#.#.#.#.#.#.#.#.###.#####.#.###.#@#
#.#.....#.....#.#.......#...#...#...#.#...#.....#@@@@@@@#...#...#...#.#@#...#...#.....#.#...#...........#@@@@@@@..#...#.#...#...#...#...#@@@........#.#.#...#...........#.....#.#..@@@#
###.#.###.#####.#.#.#.#.#.###.#.#.#####.#.#.#.#.#.#.#.###.###.#.#.###.#@###.###.#####.#.#.###.#.#.###.#.#@#.###@#####.###.#####.#.#.###.#@#####.#.#.#.#####.###.###.###.#.#.#.#.#.#@###
#.........#.....#.....#...#...............#.#...#.#.#.....#.#...#...#.#@@@@@@@@@@@@@..#.......#.......#.#@..#.#@@@@@@@@@@@#.#.#@@@@@@@@@@@....#...#.........#...............#...#..@#.#
#.#.#.###.#.#####.#.#.#.#.#.#.###.###.###.#####.#.#####.###.#.#.###.#.###.###.#####@#####.#####.#########@###.#.#.#.#.#.#@#.#.#@#.#.#.###.#.#.###.#####.###.#####.###.#.###.###.#.#@#.#
#.....#.#.............#...#.......#.#.............#.#...#.#...#...#.#.....#.#.#...#@#.....#...#@@@@@@@@@@@#.....#.#.#...#@@@@@@@............#.....#.....#...............#.....#.#..@@@#
#.#####.#.###.#.#######.###.###.#.#.#.###.###.#.###.#.#.#.###.#.#.#.#####.#.###.###@#########.#@#####.#.#.#.#.#.#.#.#####.#########.#.#.#.#.###.###.#.#####.#.#.###.#####.#.#.###.###@#
#...#.....#.#.#.#...............#.........#.....#.......#.........#...........#...#@@@....#.#@@@#.......#.....#...#.#...#.#...#.#.....#...........#.................#...#.#.#.#...#.#@#
#.#.#.#####.#.###.###.#.#.#####.#.#####.###.#.###.#.#######.#.#.###.###.###.#.#.#.#.#@#.#.#.#@#.#.#.#######.#######.#.#.###.###.#.###.#.###.#.#.#.#.#.#######.#.#.###.#.###.#####.#.#@#
#.....#.#.....#...#...#.#.......#...#.............#...#.....#...#.#.......#.....#...#@@@#.#..@..#.#...........#.......#.#...#...#.#...#.#.#.....#...............#...#.#.#...#...#..@@@#
#####.#.#.#.###.#.###.#.#####.#######.#############.#.#.#.#.#.###.#.#.###.#.#####.#.#.#@#####@#.#.###.###.#.#.#.###.#.#.###.#.#.#.#.#.#.#.#.#.###.###.#.#######.###.#.#####.#######@###
#.....#...........#.....#.....#.......#...#...#.......#.....#.#...#.#.#...#.#...#.....#@@@@@@@#...#...#...#.#.#.#.....#.....#...#.#...#.......#...#...#.....#...#...#.........#.#..@@B#
#######################################################################################################################################################################################"""
    }
}
