package com.manukhina.test4course.services;

import com.manukhina.test4course.Dto.EquationDto;
import com.manukhina.test4course.entity.Equation;
import com.manukhina.test4course.entity.Root;
import com.manukhina.test4course.repositories.EquationRepository;
import com.manukhina.test4course.repositories.RootRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class EquationService {

    private EquationRepository equationRepository;
    private RootRepository rootRepository;

    @Autowired
    public EquationService(EquationRepository equationRepository, RootRepository rootRepository) {
        this.equationRepository = equationRepository;
        this.rootRepository = rootRepository;
    }

    public ResponseEntity<Void> createEquation(String equationString) {
        Equation equation = new Equation();
        equation.setEquation(equationString);
        if (!checkEquationCorrectness(equationString))
            return ResponseEntity.badRequest().build();
        equationRepository.save(equation);
        return ResponseEntity.ok().build();
    }

    public void addRootToEquation(Long equationId, Root rootValue) {
        Optional<Equation> equationOptional = equationRepository.findById(equationId);
        if (equationOptional.isPresent()) {
            Equation equation = equationOptional.get();
            List<Root> roots = equation.getRoots();
            roots.add(rootValue);
            Root root = new Root();
            root.setValue(rootValue);
            root.setEquation(equation);
            rootRepository.save(root);
            equationRepository.save(equation);
        } else {
            throw new IllegalArgumentException("Equation not found with ID: " + equationId);
        }
    }

    public List<EquationDto> searchEquationsByRoot(Double root) {
        List<Equation> equations = equationRepository.findByRootsContaining(root);
        return mapEquationsToDtos(equations);
    }

    public List<EquationDto> searchEquationsByRoots(List<Double> roots) {
        List<Equation> equations = equationRepository.findByRootsIn(roots);
        return mapEquationsToDtos(equations);
    }

    private List<EquationDto> mapEquationsToDtos(List<Equation> equations) {
        return equations.stream()
                .map(equation -> new EquationDto(equation.getId(), equation.getEquation(), equation.getCorrect(), new ArrayList<>(equation.getRoots())))
                .collect(Collectors.toList());
    }

    //private static final String VALIDATION_PATTERN = "^-?(\\d+(\\.\\d+)?|x)(\\s*[+\\-*/](?!\\s*[+\\-*/])\\s*-?(\\d+(\\.\\d+)?|x)|\\s*|\\((\\s*-?(\\d+(\\.\\d+)?|x)(\\s*[+\\-*/](?!\\s*[+\\-*/])\\s*-?(\\d+(\\.\\d+)?|x))?\\s*\\))*(=\\s*-?(\\d+(\\.\\d+)?|x)|\\s*)\r?$";
    private static final String EQUATION_REGEX = "^\\s*(-?\\d*\\.?\\d*\\s*[\\+\\-\\*/\\(\\)]\\s*)*x\\s*[=]\\s*-?\\d*\\.?\\d*\\s*$";

    public boolean checkEquationCorrectness(String equation) {
        Pattern pattern = Pattern.compile(EQUATION_REGEX);
        Matcher matcher = pattern.matcher(equation);
        return matcher.matches() && areBracketsCorrect(equation) && isExpressionCorrect(equation);
    }

    // Checks if the brackets are correctly placed in the given equation:
    // the number of opening brackets is equal to number of closing ones
    private static boolean areBracketsCorrect(String equation) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < equation.length(); i++) {
            char c = equation.charAt(i);
            if (c == '(') {
                stack.push(c);
            } else if (c == ')') {
                if (stack.empty()) {
                    return false;
                }
                stack.pop();
            }
        }
        return stack.empty();
    }

    private static boolean isExpressionCorrect(String equation) {
        // Remove the "x =" part of the equation to get the expression
        String expression = equation.replaceAll("x\\s*=\\s*", "");
        // Check if there are two mathematical operators in a row
        if (expression.matches(".*[\\+\\-\\*/]{2,}.*")) {
            return false;
        }
        return true;
    }
}

//    private static final double TOLERANCE = 0.0001;
//    // Checks if the given roots are correct for the equation entered by the user.
//
//    public static boolean checkRoots(String left, String right, List<Double> roots) {
//        // Create a mathematical expression from the left and right parts of the equation
//        String expression = left + "-(" + right + ")";
//        Expression e = new Expression(expression);
//    }
//
