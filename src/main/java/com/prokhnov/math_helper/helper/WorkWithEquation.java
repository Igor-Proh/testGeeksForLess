package com.prokhnov.math_helper.helper;

import com.prokhnov.math_helper.exception.IncorrectEquationException;
import org.nfunk.jep.JEP;

import java.util.Locale;

/**
 * The {@code WorkWithEquation} class.<br/>
 * Class that provide some methods to work with equations
 *
 * @author Ihor Prokhnov
 * @version 1.0
 */
public class WorkWithEquation {

    private WorkWithEquation() {
    }

    public static void equationSyntaxCheck(String equation) {

        if (equation.isBlank()) {
            throw new IllegalArgumentException("Empty input equation!");
        }

        String inputEquation = equation.replaceAll(" ", "").replaceAll(",",".").trim().toLowerCase(Locale.ROOT);

        int countOfBrackets = 0;
        int minimalEquationLength = 5;


        if (inputEquation.length() < minimalEquationLength || !inputEquation.contains("=") || !inputEquation.contains("x")) {
            throw new IncorrectEquationException("Incorrect equation!");
        }

        for (int i = 0; i < inputEquation.length(); i++) {

            String currentValue = String.valueOf(inputEquation.charAt(i));
            String nextValue;
            String previousValue;


            if (i == 0) {

                if (currentValue.matches("[(]")) {
                    countOfBrackets++;
                }

                if (!currentValue.matches("[0-9-(]")) {
                    throw new IncorrectEquationException();
                }

            } else if (i < inputEquation.length() - 1) {

                nextValue = String.valueOf(inputEquation.charAt(i + 1));
                previousValue = String.valueOf(inputEquation.charAt(i - 1));

                if (currentValue.matches("[1-9]")) {
                    if (!(previousValue.matches("[0-9+-/*(=]") && nextValue.matches("[0-9.+-/*)=]"))) {
                        throw new IncorrectEquationException("Incorrect character at index - " + i);
                    }
                }

                if (currentValue.matches("[0]")) {
                    if (!(previousValue.matches("[0-9+-/*(=]") && nextValue.matches("[0-9.+-/*)=]"))) {
                        throw new IncorrectEquationException("Incorrect character at index - " + i);
                    }
                }

                if (currentValue.matches("[.]")) {
                    if (!(previousValue.matches("[0-9]") && nextValue.matches("[0-9]"))) {
                        throw new IncorrectEquationException("Incorrect character at index - " + i);
                    }
                }

                if (currentValue.matches("[+]")) {
                    if (!(previousValue.matches("[0-9)x]") && nextValue.matches("[0-9-(x]"))) {
                        throw new IncorrectEquationException("Incorrect character at index - " + i);
                    }
                }

                if (currentValue.matches("[-]")) {

                    if (previousValue.matches("[-]") && nextValue.matches("[-]")) {
                        throw new IncorrectEquationException("Incorrect character at index - " + i);
                    } else if (!(previousValue.matches("[0-9)(x=+/*-]") && nextValue.matches("[0-9-(x]"))) {
                        throw new IncorrectEquationException("Incorrect character at index - " + i);
                    }
                }

                if (currentValue.matches("[(]")) {
                    countOfBrackets++;

                    if (!(previousValue.matches("[+-/*(]") && nextValue.matches("[0-9-x(]"))) {
                        throw new IncorrectEquationException("Incorrect character at index - " + i);
                    }
                }

                if (currentValue.matches("[)]")) {
                    countOfBrackets--;
                    if (!(previousValue.matches("[0-9x)]") && nextValue.matches("[+-/=*)]"))) {
                        throw new IncorrectEquationException("Incorrect character at index - " + i);
                    }
                }

                if (currentValue.matches("[x]")) {
                    if (!(previousValue.matches("[+-/*(]") && nextValue.matches("[+-/*)=]"))) {
                        throw new IncorrectEquationException("Incorrect character at index - " + i);
                    }
                }

                if (currentValue.matches("[=]")) {
                    if (!(previousValue.matches("[0-9x)]") && nextValue.matches("[0-9-]"))) {
                        throw new IncorrectEquationException("Incorrect character at index - " + i);
                    }
                }

                if (currentValue.matches("[*]")) {
                    if (!(previousValue.matches("[0-9x)]") && nextValue.matches("[0-9-x(]"))) {
                        throw new IncorrectEquationException("Incorrect character at index - " + i);
                    }
                }

            } else {

                if (!currentValue.matches("[0-9]")) {
                    throw new IncorrectEquationException("Incorrect character at index - " + i);
                }

                if (countOfBrackets != 0) {
                    throw new IncorrectEquationException("Incorrect count of brackets");
                }
            }
        }
    }

    public static boolean checkRootOfEquation(String equation, String root) {

        try {

            equationSyntaxCheck(equation);

            String[] split = equation.split("=");

            String eq = split[0].replace("x", root);

            JEP jep = new JEP();
            jep.addStandardConstants();
            jep.addStandardFunctions();
            jep.parseExpression(eq);

            if (jep.hasError()) {
                System.out.println("It is not a number! Try Again!!!");
            } else {
                double result = jep.getValue();
                if (result == Double.parseDouble(split[1])) {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

}

