package org.hibernate.bugs;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.stream.Stream;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.sql.results.spi.LoadContexts;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * This template demonstrates how to develop a test case for Hibernate ORM, using the Java
 * Persistence API.
 */
public class JPAUnitTestCase {

  private EntityManagerFactory entityManagerFactory;

  @Before
  public void init() {
    entityManagerFactory = Persistence.createEntityManagerFactory("templatePU");
  }

  @After
  public void destroy() {
    entityManagerFactory.close();
  }

  // Entities are auto-discovered, so just add them anywhere on class-path
  // Add your tests, using standard JUnit.
  @Test
  public void hhh17078Test() throws Exception {
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    try (Stream<Event> events = entityManager.createQuery("from Event", Event.class)
        .getResultStream()) {
    }

    final LoadContexts loadContexts = entityManager.unwrap(SessionImplementor.class)
        .getPersistenceContext().getLoadContexts();
    Assert.assertTrue(loadContexts.isLoadingFinished());

    entityManager.getTransaction().commit();
    entityManager.close();
  }
}
