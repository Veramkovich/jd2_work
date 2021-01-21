package it.academy.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.junit.FixMethodOrder;
import org.junit.Test;

import java.io.Serializable;
import java.sql.Date;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@FixMethodOrder
public class PersonTest {

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
        } finally {
            session.close();
        }

        //Then
        assertNotNull(id);
    }

    @Test
    public void delete() {
        //Given
        Session session = factory.openSession();
        Person person = session.get(Person.class, 1L);

        //When
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

        // Then
        assertNull(session.get(Person.class, 1L));
        session.close();
    }

    @org.junit.After
    public void tearDown() {
        factory.close();
    }
}