package org.example;

import org.example.entities.Answer;
import org.example.entities.Question;
import org.example.entities.Test;
import org.example.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class FillInnDb {


    public static void fill() {
        SessionFactory sessionFactory = new Configuration()
                .configure()
                .buildSessionFactory();

        Session session = sessionFactory.openSession();

        Transaction tx = null;
        try {

            tx = session.beginTransaction();
            User user = new User();
            user.setName("Jonukas");

            Test test1 = new Test("KET");
            Test test2 = new Test("Rašyba");
            Test test3 = new Test("Ar žinote, kaip anksčiau vadinosi šie Lietuvos miestai?");

            Question q1 = new Question(test1, "Ar, užsidegus žaliam šviesoforo signalui, galite apsisukti?");
            Question q2 = new Question(test2, "Kuris iš šių žodžių parašytas taisyklingai?");
            Question q3 = new Question(test2, "Kuris iš šių žodžių parašytas taisyklingai?");
            Question q4 = new Question(test2, "Kuris iš šių žodžių parašytas taisyklingai?");
            Question q5 = new Question(test3, "Koks Lietuvos miestas 1919 m. buvo trumpam pervadintas Ežerėnais?");
            Question q6 = new Question(test3, "Koks Lietuvos miestas iki 1923 m. vadinosi Šilokarčema?");

            Answer a1 = new Answer(q1,"Taip, jeigu važiuojate antra eismo juosta.", true);
            Answer a2 = new Answer(q1,"Ne.", false);

            Answer a3 = new Answer(q2,"vašelis", false);
            Answer a4 = new Answer(q2,"vąšelis", true);
            Answer a5 = new Answer(q2,"vašelys", false);

            Answer a6 = new Answer(q3,"Įspysti", false);
            Answer a7 = new Answer(q3,"Įspisti", false);
            Answer a8 = new Answer(q3,"Įspįsti", true);

            Answer a9 = new Answer(q4,"slianksčiai", false);
            Answer a10 = new Answer(q4,"slenksčiai", true);
            Answer a11 = new Answer(q4,"slenkščiai", false);

            Answer a12 = new Answer(q5,"Molėtai", false);
            Answer a13 = new Answer(q5,"Zarasai", true);
            Answer a14 = new Answer(q5,"Ignalina", false);

            Answer a15 = new Answer(q6,"Šilutė", true);
            Answer a16 = new Answer(q6,"Šiauliai", false);
            Answer a17 = new Answer(q6,"Šilalė", false);


            session.save(user);
            session.save(test1);
            session.save(test2);
            session.save(test3);
            session.save(q1);
            session.save(q2);
            session.save(q3);
            session.save(q4);
            session.save(q5);
            session.save(q6);

            session.save(a1);
            session.save(a2);
            session.save(a3);
            session.save(a4);
            session.save(a5);
            session.save(a6);
            session.save(a7);
            session.save(a8);
            session.save(a9);
            session.save(a10);
            session.save(a11);
            session.save(a12);
            session.save(a13);
            session.save(a14);
            session.save(a15);
            session.save(a16);
            session.save(a17);

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
}}
