package org.example.Servise;


import org.example.abstracts.PrintingListsHelper;
import org.example.entities.*;
import org.example.exceptions.InvalidChoiceException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class TestService extends PrintingListsHelper {
    private  Print print = new Print();
    private UserInputReader input = new UserInputReader();
    private DbManager db = new DbManager();
    private Result result = new Result();


    public void createNewTest() {
        print.newTestName();
        Test newTest = new Test(input.readString());
        db.saveNewTest(newTest);

        int choise;
        boolean isRight;
        try {
        do{
            print.newQustionText();
            Question newQuestion = new Question(newTest, input.readString());
            db.saveNewQuestion(newQuestion);

            do{
                print.newAnswer();
                String newAnswerString = input.readString();
                print.isAnswerRight();
                if(input.getNumericChoce(isRightChoices)==1){
                    isRight = true;
                }else {
                    isRight = false;
                }
                Answer newAnswer =new Answer(newQuestion, newAnswerString, isRight);
                db.saveNewAnswer(newAnswer);

                print.addNewAnswer();
                choise = input.getNumericChoce(questionCreationChoises);
            }while(choise!=2);



            print.addNewQuestion();
            choise = input.getNumericChoce(questionCreationChoises);
        }while(choise!=2);

        } catch (InvalidChoiceException e) {
            e.printStackTrace();
        }
    }

    public void doTest() {
        try {
            print.nameInput();
            String userName = input.readString();

            result.setUser(db.setUser(userName));
            takeTest();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Test testSelection() {
        List<Test> testList = db.getTests();
        print.testSelection(testList);
        int testChoice = 0;
        while (true) {
            try {
                testChoice = input.getNumericChoce(testList.size());
                break;
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            }
        }
        return testList.get(testChoice - 1);
    }
    public void editQuestionInTest() {
        Test editableTest = testSelection();
        Question editableQuestion = questionSelection(editableTest);
        print.newQustionText();
        String newQustionText = input.readString();
        db.updateQuestion(editableQuestion, newQustionText);


    }

    public void deleteQuestion() {
        Test choisenTest = testSelection();
        Question deletableQuestion = questionSelection(choisenTest);
        db.deleteQuestion(deletableQuestion);
        print.deletionSuccessful();
    }

    private Question questionSelection(Test test) {
        List<Question> questionsList = test.getQuestions();
        print.questionSelection(questionsList);
        int questionChoice = 0;
        while (true) {
            try {
                questionChoice = input.getNumericChoce(questionsList.size());
                break;
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            }
        }
        return questionsList.get(questionChoice - 1);
    }


    private void takeTest()  {
        Test choisenTest = testSelection();
        result.setTest(choisenTest);

        List<Question> questionsList = choisenTest.getQuestions();

        for (Question question : questionsList) {
            result.setQuestion(question);
            print.showQuestion(question);
            List<Answer> answersList = question.getAnswers();
            print.showAnswers(answersList);
            int chosenAnswer = 0;
            while (true) {
                try {
                    chosenAnswer = input.getStringChoice(answersList.size());
                    result.setAnswer(answersList.get(chosenAnswer));
                    result.setUserAnswer(answersLetters[chosenAnswer]);

                    db.setResult(this.result);
                    break;
                } catch (InvalidChoiceException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void exitProgram() {
        print.exitinfo();
        System.exit(0);
    }

    public void viewStatistics() throws SQLException {
        ResultSet rightAnswersFromEachTest = db.getRightAnswersInEachTest();
        int totalRightAnswers = 0;

        while (rightAnswersFromEachTest.next()) {
            totalRightAnswers+= rightAnswersFromEachTest.getInt(3);
        }

        print.testsSolved(db.getTotalTestsTooken());

        print.rightAnswers(totalRightAnswers);

        while (rightAnswersFromEachTest.next()) {
            print.rightlyAnsweredTo(rightAnswersFromEachTest.getString(1),
                    rightAnswersFromEachTest.getString(2),
                    rightAnswersFromEachTest.getInt(3));
        }

        for (int i = 0; i < answersLetters.length; i++) {
            print.answerByLetter(i, db.answersByLetter(answersLetters[i]));
        }

    }

}
