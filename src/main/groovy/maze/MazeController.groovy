package maze

import maze.validation.MazeValidator
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping

@RestController
class MazeController {

    @RequestMapping(value = "/solve", method = RequestMethod.PUT, produces = "application/json; charset=UTF-8")
    @CrossOrigin
    @ResponseBody
    String solve(@RequestBody Layout layout){
        MazeValidator.isValid(layout.text)

        def solution = new MazeSolver().solve(new Maze(layout.text))

        return solution
    }
}

