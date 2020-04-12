package org.example.Servise;

import org.example.abstracts.PrintingListsHelper;
import org.example.exceptions.InvalidChoiceException;

import java.util.Scanner;

public class UserInputReader extends PrintingListsHelper {
    Scanner sc = new Scanner(System.in);

    public String readString() {
        return sc.next();
    }

    public int getNumericChoce(int choices) throws InvalidChoiceException {
        String choiceString = sc.next();
        try {
            int choice = Integer.parseInt(choiceString);
            if (choice > 0 && choice <= choices) {
                return choice;
            }
        } catch (Exception e) {
            throw new InvalidChoiceException();

        }

        throw new InvalidChoiceException();
    }


    public int getStringChoice(int size) throws InvalidChoiceException {
        String choiceString = sc.next();
        try {
            for (int i = 0; i < size; i++) {
                if (choiceString.equals(answersLetters[i])) {
                    return i;
                }
            }
        } catch (Exception e) {
            throw new InvalidChoiceException();

        }
        throw new InvalidChoiceException();

    }
}
