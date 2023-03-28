package com.manukhina.test4course.controllers;

import com.manukhina.test4course.Dto.EquationDto;
import com.manukhina.test4course.entity.Root;
import com.manukhina.test4course.services.EquationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equations")
public class EquationController {
    private EquationService equationService;

    @Autowired
    public EquationController(EquationService equationService) {
        this.equationService = equationService;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createEquation(@RequestBody EquationDto equationDto) {
        equationService.createEquation(equationDto.getEquation());
        return ResponseEntity.ok("Equation created successfully");
    }

    @PostMapping("/add-root")
    public ResponseEntity<String> addRootToEquation(@RequestParam("id") Long id, @RequestParam("root") Root root) {
        equationService.addRootToEquation(id, root);
        return ResponseEntity.ok("Root added to equation successfully");
    }

    @GetMapping("/search-by-root")
    public ResponseEntity<List<EquationDto>> searchEquationsByRoot(@RequestParam("root") Double root) {
        List<EquationDto> equations = equationService.searchEquationsByRoot(root);
        return ResponseEntity.ok(equations);
    }

    @GetMapping("/search-by-roots")
    public ResponseEntity<List<EquationDto>> searchEquationsByRoots(@RequestParam("roots") List<Double> roots) {
        List<EquationDto> equations = equationService.searchEquationsByRoots(roots);
        return ResponseEntity.ok(equations);
    }
}

//    @PostMapping
//    public ResponseEntity<Void> createEquation(@RequestBody String equation) {
//        if (!equationService.isValid(equation) || !equationService.checkRoots(equation)) {
//            return ResponseEntity.badRequest().build();
//        }
//        Equation parsedEquation = equationService.parse(equation);
//        equationService.saveEquation(parsedEquation);
//        return ResponseEntity.ok().build();
//    }
//
//    @GetMapping(params = "root")
//    public ResponseEntity<List<Equation>> findByRootsIn(@RequestParam List<Double> root) {
//        List<Equation> equations = equationService.searchByRoot(root);
//        if (equations.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(equations);
//    }
//
//    @GetMapping(params = "exactRoots")
//    public ResponseEntity<List<Equation>> searchByExactRoots(@RequestParam int exactRoots) {
//        List<Equation> equations = equationService.searchByExactRoots(exactRoots);
//        if (equations.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(equations);
//    }
//}
