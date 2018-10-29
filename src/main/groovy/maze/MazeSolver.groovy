package maze

import groovy.json.JsonBuilder

import static maze.Direction.EAST
import static maze.Direction.NORTH
import static maze.Direction.SOUTH
import static maze.Direction.WEST

class MazeSolver {

    String solve(Maze maze) {

        def start = System.currentTimeMillis()

        List steps = this.getPath(maze)
        def solvedMaze = maze.markPath(steps)

        JsonBuilder json = new JsonBuilder()
        json {
            solution solvedMaze
            stepsTaken steps.size() - 1
            solutionInMillis System.currentTimeMillis() - start
        }

        json.toString()
    }

    private List<Coordinate> getPath(Maze maze) {
        LinkedList<Coordinate> nextToVisit = new LinkedList<>()
        nextToVisit.add(maze.start)

        while (!nextToVisit.isEmpty()) {
            Coordinate cur = nextToVisit.remove()

            if (maze.isInvalidLocation(cur)) {
                continue
            }

            if (maze.isVisited(cur)) {
                continue
            }

            if (maze.isWall(cur)) {
                maze.setVisited(cur)
                continue
            }

            if (maze.isExit(cur)) {
                return backtrackPath(cur)
            }

            nextToVisit.add(cur.getNeighborToThe(NORTH))
            nextToVisit.add(cur.getNeighborToThe(SOUTH))
            nextToVisit.add(cur.getNeighborToThe(EAST))
            nextToVisit.add(cur.getNeighborToThe(WEST))

            maze.setVisited(cur)
        }
        Collections.emptyList()
    }

    private List<Coordinate> backtrackPath(Coordinate cur) {
        List<Coordinate> path = new ArrayList<>()
        Coordinate iter = cur

        while (iter != null) {
            path.add(iter)
            iter = iter.parent
        }

        path
    }
}
