package com.prokhnov.math_helper;

import com.prokhnov.math_helper.dao.impl.EquationDaoImpl;
import com.prokhnov.math_helper.dao.impl.RootDaoImpl;
import com.prokhnov.math_helper.entity.Equation;
import com.prokhnov.math_helper.entity.Root;
import com.prokhnov.math_helper.exception.IncorrectEquationException;
import com.prokhnov.math_helper.helper.WorkWithEquation;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.Locale;
import java.util.Scanner;

/**
 * The {@code Main} class.<br/>
 * Class that start main program
 *
 * @author Ihor Prokhnov
 * @version 1.0
 */
public class Main {

    /**
     * Initialize Logger (log4j)
     */
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void printMenu(String[] variants) {
        for (String variant : variants) {
            System.out.println(variant);
        }
        System.out.println("Please enter Your chose:");
    }

    public static void main(String[] args) {
        LOGGER.info("Program starting...");
        String[] mainMenu = {
                "This program can do next things:",
                "1. Input equation",
                "2. Search equations by root",
                "3. Exit",
        };

        Scanner scanner = new Scanner(System.in);

        int option = 0;

        while (option != 3) {
            printMenu(mainMenu);

            try {
                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Please enter equation:");
                        scanner = new Scanner(System.in);

                        String inputEquation = scanner.nextLine();

                        try {
                            WorkWithEquation.equationSyntaxCheck(inputEquation);

                            EquationDaoImpl equationDao = new EquationDaoImpl();
                            equationDao.create(new Equation(inputEquation));

                            System.out.println("Please enter root of equation -> " + inputEquation + ". Or BACK to return to Main menu!");

                            boolean rootFlag = true;
                            while (rootFlag) {

                                String root = scanner.nextLine();

                                if (root.toLowerCase(Locale.ROOT).equals("back")){
                                    break;
                                }

                                if (WorkWithEquation.checkRootOfEquation(inputEquation, root.replace(",","."))) {
                                    System.out.println("Root " + root + " is correct! It saved to DB!");
                                    RootDaoImpl rootDao = new RootDaoImpl();
                                    rootDao.create(new Root(root, equationDao.findByValue(inputEquation).getEquation_id()));
                                    rootFlag = false;
                                } else {
                                    System.out.println("Incorrect root for " + inputEquation + ". Try again! Or input BACK to return to Main menu!");
                                }
                            }


                        } catch (IncorrectEquationException exception) {
                            System.err.println(exception.getMessage());
                        }

                        break;

                    case 2:
                        EquationDaoImpl equationDao = new EquationDaoImpl();

                        System.out.println("Please enter root:");
                        scanner = new Scanner(System.in);
                        String inputRoot = scanner.nextLine();
                        System.out.println("Equations from DB by root -> " + inputRoot);

                        List<Equation> equationList = equationDao.findByRoot(inputRoot);
                        if (!equationList.isEmpty()) {
                            System.out.println(equationList);
                        } else {
                            System.out.println("No equations with root " + inputRoot);
                        }

                        break;

                    case 3:
                        LOGGER.info("End of program!");
                        break;

                    default:
                        System.out.println("Incorrect input!!");
                }

            } catch (Exception ex) {
                System.out.println("Please enter an integer value between 1 and " + mainMenu.length);
                scanner.next();
            }
        }
    }
}
