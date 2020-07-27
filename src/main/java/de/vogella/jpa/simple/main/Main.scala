package de.vogella.jpa.simple.main

import nl.buildforce.sequoia.metadata.api.JPAEntityManagerFactory
import tutorial.model.TodoEntity
import tutorial.service.DataSourceHelper
import java.util
import java.util.List

import jakarta.persistence.EntityManager
import jakarta.persistence.EntityManagerFactory
import jakarta.persistence.Persistence
import jakarta.persistence.Query
import javax.sql.DataSource

import scala.jdk.CollectionConverters.ListHasAsScala


object Main {
  private val PERSISTENCE_UNIT_NAME = "GreenTrak00"
  private var factory: EntityManagerFactory = null

  def main(args: Array[String]): Unit = {
    val ds = DataSourceHelper.createDataSource(DataSourceHelper.DB_HSQLDB)
    factory = JPAEntityManagerFactory.getEntityManagerFactory(PERSISTENCE_UNIT_NAME, ds)
    val em = factory.createEntityManager
    // read the existing entries and write to console
    val q = em.createQuery("SELECT t FROM TodoEntity AS t")
    val todoList = q.getResultList.asScala
    for (todo <- todoList) {
      System.out.println(todo)
    }
    System.out.println("Size: " + todoList.size)

    val i: Int = if (todoList.isEmpty) -1 else
      todoList.map { case x: TodoEntity => x.getId() }.max
    // create new todo
    em.getTransaction.begin()
    val todo = new TodoEntity
    todo.setId( i+ 1)
    todo.setSummary("This is a test")
    todo.setDescription("This is a test")
    em.persist(todo)
    em.getTransaction.commit()
    em.close()
  }
}