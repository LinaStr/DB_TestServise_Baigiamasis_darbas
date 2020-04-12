package org.example.Servise;

import org.example.entities.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.sql.*;
import java.util.List;

public class DbManager {
    SessionFactory sessionFactory = new Configuration()
            .configure()
            .buildSessionFactory();

    public User setUser(String userName) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            User user = new User();
            user.setName(userName);
            session.save(user);
            tx.commit();
            return user;
        }catch (Exception e){
            if (tx!=null){
                tx.rollback();
            }
            throw e;

        }finally {
            session.close();
        }
    }

    public List<Test> getTests() {
        Session session = sessionFactory.openSession();
        Query<Test> query = session.createQuery("FROM Test", Test.class);
        List<Test>  tests = query.list();
        session.close();
        return  tests;

    }

    public void setResult(Result result) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try{
            tx = session.beginTransaction();
            session.save(result);
            tx.commit();
        }catch (Exception e){
            if (tx!=null){
                tx.rollback();
            }
            throw e;
        }finally {
            session.close();
        }
    }

    public int getTotalTestsTooken() {
        Session session = sessionFactory.openSession();
        Query<Result> query = session.createQuery("FROM Result", Result.class);
        List<Result>  tests = query.list();
        int solvedTests = tests.size();
        session.close();
        return solvedTests;
    }

    public int answersByLetter(String answersLetter) {
        Session session = sessionFactory.openSession();
        Query<Result> query = session.createQuery("from Result where user_answer = :user_answer");
        query.setParameter("user_answer", answersLetter);
        List<Result> lettersByUsers = query.list();
        session.close();
        return lettersByUsers.size();
    }

    public void deleteQuestion(Question deletableQuestion) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            Question question = new Question();
            question.setQuestionId(deletableQuestion.getQuestionId());
            tx = session.beginTransaction();
            session.delete(deletableQuestion);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void saveNewTest(Test newTest) {
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(newTest);
            tx.commit();
        }
        catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

    }

    public void saveNewQuestion(Question newQuestion) {
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(newQuestion);
            tx.commit();
        }
        catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void saveNewAnswer(Answer newAnswer) {
        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(newAnswer);
            tx.commit();
        }
        catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    public void updateQuestion(Question editableQuestion, String newQustionText) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query<Question> query= session.createQuery("UPDATE Question SET question_text = :newQuestion WHERE question_id = :question_id");
            query.setParameter("newQuestion", newQustionText);
            query.setParameter("question_id", editableQuestion.getQuestionId());
            query.executeUpdate();
//
//            session.save(newAnswer);
            tx.commit();
        }
        catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    //šis metodas tik dėl to kad nepavyko normaliai hibernatu išimti info aka sujungti 2 lenteles
    public ResultSet getRightAnswersInEachTest() {

        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT test_Name, question_text, count(*) " +
                    "FROM RESULT JOIN TEST ON test.TEST_ID = RESULT .TEST_ID " +
                    "join QUESTION on QUESTION.QUESTION_ID  = RESULT.QUESTION_ID  " +
                    "JOIN ANSWER ON ANSWER.ANSWER_ID    = RESULT.ANSWER_ID    " +
                    "WHERE IS_RIGHT = 'true' group by TEST_NAME, question_text;");

            ResultSet rightAnswers = statement.executeQuery();

            return rightAnswers;


        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
    private Connection createConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(
                "jdbc:h2:tcp://localhost/~/test", "sa", "");
    }

}