package com.manukhina.test4course;

import com.manukhina.test4course.repositories.EquationRepository;
import com.manukhina.test4course.repositories.RootRepository;
import com.manukhina.test4course.services.EquationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
@DataJpaTest
class TestProjectOfTheCandidateAnastasiiaManukhinaApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private EquationService equationService;

    @Autowired
    private RootRepository rootRepository;

    @Autowired
    private EquationRepository equationRepository;

    @Test
    public void testValidateEquation_valid() {
        String equationString = "2*x+5=17";
        Assertions.assertDoesNotThrow(() -> equationService.checkEquationCorrectness(equationString));
    }

    @Test
    public void testValidateEquation_invalid() {
        String equationString = "3+*4";
        Exception exception = Assertions.assertThrows(Exception.class, () -> equationService.checkEquationCorrectness(equationString));
        Assertions.assertTrue(exception.getMessage().contains("Invalid equation"));
    }

    @Test
    public void testCreateEquation_invalid() {
        String equationString = "3+*4";
        Exception exception = Assertions.assertThrows(Exception.class, () -> equationService.createEquation(equationString));
        Assertions.assertTrue(exception.getMessage().contains("Invalid equation"));
    }
}