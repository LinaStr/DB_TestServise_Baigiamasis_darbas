package org.example.Servise;


import org.example.abstracts.PrintingListsHelper;
import org.example.entities.Answer;
import org.example.entities.Question;
import org.example.entities.Test;

import java.util.List;

public class Print extends PrintingListsHelper {


    public void nameInput() {
        System.out.println("Įveskite vartotojo vardą");
    }

    public void testSelection(List<Test> tests) {
        System.out.println("Pasirinkite testą");
        int option = 1;
        for (Test t:
                tests) {
            System.out.println(option+". "+t.getTestName());
            option +=1;
        }
    }
    public void questionSelection(List<Question> questions) {
        System.out.println("Pasirinkite klausimą");
        int option = 1;
        for (Question q:
                questions) {
            System.out.println(option+". "+q.getQuestionText());
            option +=1;
        }
    }

    public void showQuestion(Question question) {
        System.out.println(question.getQuestionText());
    }

    public void showAnswers(List<Answer> answersList) {
        int letter = 0;
        for (Answer answer: answersList) {
            System.out.println(answersLetters[letter]+". "+answer.getAnswer());
            letter+=1;
        }

    }



    public void mainMenu() {
             System.out.println("\n" +
                    "1. Spręsti testą\n" +
                    "2. Kurti arba redaguoti esamus testus\n" +
                    "3. Peržiūrėti statistikas\n" +
                    "4. Išeiti");
    }

    public void exitinfo() {System.out.println("Ačiū, kad apsilankėt. ate ate"); }

    public void testsSolved(int totalTestsTooken) {
        System.out.println("Viso buvo spręsta "+ totalTestsTooken +" testų");
    }

    public void rightAnswers(int totalRightAnswers) {
        System.out.println("Teisingai atsakytų klausimų: "+totalRightAnswers);
    }

    public void rightlyAnsweredTo(String testName, String question, int rightAnwers) {
        System.out.println("Į Testo: "+testName+" klausimą "+ question+" buvo teisingai atsakyta kartų: "+rightAnwers);
    }

    public void answerByLetter(int i, int answersByLetter) {
        System.out.println("Pasirinkusių raidę "+answersLetters[i]+" buvo: "+answersByLetter);
    }

    public void editOrCreate() {
        System.out.println("\n" +
                "1. Kurti naują testą\n" +
                "2. Redaguoti esamus testus");
    }

    public void editOrDelete() {
        System.out.println("\n" +
                "1. Redaguoti testą\n" +
                "2. Trinti testą");
    }


    public void deletionSuccessful() {
        System.out.println("Testas ištrintas");
    }

    public void newTestName() {
        System.out.println("Įveskite naujo testo pavadinimą");
    }

    public void newQustionText() {
        System.out.println("Įveskite naują klausimo tekstą");
    }

    public void newAnswer() {
        System.out.println("Įveskite atsakymo tekstą");
    }

    public void isAnswerRight() {
        System.out.println("\n" +
                "1. Įvestas atsakymas yra teisingas\n" +
                "2. Įvestas atsakymas yra neteisingas");
    }

    public void addNewQuestion() {
        System.out.println("\n" +
                "1. Pridėti klausimą\n" +
                "2. Baigti testo kūrimą");
    }

    public void addNewAnswer() {
        System.out.println("\n" +
                "1. Pridėti atsakymą\n" +
                "2. Klausimui visi atsakymų varianai pridėti");
    }


}

