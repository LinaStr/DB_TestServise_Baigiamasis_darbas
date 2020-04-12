package org.example.Servise;

import org.example.abstracts.PrintingListsHelper;
import org.example.exceptions.InvalidChoiceException;

import java.sql.SQLException;

public class Menu extends PrintingListsHelper {
    private  Print print = new Print();
    private UserInputReader input = new UserInputReader();
    private TestService servise = new TestService();

    public void run(){
        try {
            do {
                print.mainMenu();
                int choice = input.getNumericChoce(mainMenuChoices);
                switch (choice) {
                    case 1:
                        servise.doTest();
                        break;
                    case 2:
                        editOrCreateTest();
                        break;
                    case 3:
                        servise.viewStatistics();
                        break;
                    case 4:
                        servise.exitProgram();
                        break;
                }

            } while (true);
        } catch (InvalidChoiceException | SQLException e) {
            System.out.println(e.getMessage());
        }

    }



    private void editOrCreateTest() {
        print.editOrCreate();
        try {
            int choice = input.getNumericChoce(editingChoices);
            switch (choice){
                case 1:
                    servise.createNewTest();
                    break;
                case 2:
                    editOrDeleteQuesion();
                    break;
            }
        } catch (InvalidChoiceException e) {
            e.printStackTrace();
        }

    }

    private void editOrDeleteQuesion() {
        print.editOrDelete();
        try {
            int choice = input.getNumericChoce(editingChoices);
            switch (choice){
                case 1:
                    servise.editQuestionInTest();
                    break;
                case 2:
                    servise.deleteQuestion();
                    break;
            }
        } catch (InvalidChoiceException e) {
            e.printStackTrace();
        }
    }
}
