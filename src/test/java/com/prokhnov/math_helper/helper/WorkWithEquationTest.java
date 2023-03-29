package com.prokhnov.math_helper.helper;

import com.prokhnov.math_helper.exception.IncorrectEquationException;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;


public class WorkWithEquationTest {


    @Test
    public void s(){
        Assertions.assertDoesNotThrow(() -> {
            WorkWithEquation.equationSyntaxCheck("1+x=2");
        });

        Assertions.assertThrows(IncorrectEquationException.class, () -> {
            WorkWithEquation.equationSyntaxCheck("111+*");
        });

    }
    @Test
    public void testShouldCheckRootOfCorrectEquation() {

        Assertions.assertTrue(WorkWithEquation.checkRootOfEquation("1+x=2", "1"));
        Assertions.assertTrue(WorkWithEquation.checkRootOfEquation("-(1+x)=2", "-3"));

        Assertions.assertFalse(WorkWithEquation.checkRootOfEquation("-(1+x)=2", "0"));
        Assertions.assertFalse(WorkWithEquation.checkRootOfEquation("-(1+x)=2", "15515"));


    }
}