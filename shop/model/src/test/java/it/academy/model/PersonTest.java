package it.academy.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.Test;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import static org.junit.Assert.*;

public class PersonTest extends BaseTest {

    private SessionFactory factory;

    @org.junit.Before
    public void setUp() {
        StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .configure("hibernate.cfg.test.xml")
                        .build();
        factory = new MetadataSources(registry)
                .buildMetadata()
                .buildSessionFactory();
    }

    @Test
    public void create() {
        //Given
        Person person = new Person();
        person.setId(1L);
        person.setName("Natalia");
        person.setSecondName("Ivanova");
        person.setDateOfBirth(Date.valueOf("1980-01-01"));

        //When
        Session session = factory.openSession();
        Transaction tx = null;
        Serializable id;
        try {
            tx = session.beginTransaction();
            //do some work
            id = session.save(person);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }

        //Then
        assertNotNull(id);
    }

    @Test
    public void delete() {
        //Given:
        cleanInsert("PersonTest.xml");

        //When:
        Session session = factory.openSession();
        Person person = session.get(Person.class, 3L);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            //do some work
            session.delete(person);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            throw e;
        }

        // Then:
        assertNull(session.get(Person.class, 3L));
        session.close();
        deleteDataset();
    }

    @Test
    public void query() {
        //Given:
        cleanInsert("PersonTest.xml");

        //When:
        Session session = factory.openSession();
        List<Person> persons = session
                .createQuery("from Person where secondName like 'Ivan%'", Person.class)
                .list();

        //Then:
        assertEquals(1, persons.size());
        deleteDataset();
    }

    @org.junit.After
    public void tearDown() {
        factory.close();
    }
}

