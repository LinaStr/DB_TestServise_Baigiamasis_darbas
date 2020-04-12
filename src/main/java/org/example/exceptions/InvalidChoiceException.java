package org.example.exceptions;

public class InvalidChoiceException extends Exception {
    public InvalidChoiceException() {
        super("Netinkamas pasirinkimas");
    }

}
