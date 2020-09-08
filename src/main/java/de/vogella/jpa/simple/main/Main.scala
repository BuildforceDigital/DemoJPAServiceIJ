package de.vogella.jpa.simple.main

import nl.buildforce.sequoia.metadata.api.JPAEntityManagerFactory
import tutorial.model.TodoEntity
import tutorial.service.DataSourceHelper

import scala.jdk.CollectionConverters.ListHasAsScala


object Main {

  def main(args: Array[String]): Unit = {
    val ds = DataSourceHelper.createDataSource(DataSourceHelper.DB_HSQLDB)
    val factory = JPAEntityManagerFactory.getEntityManagerFactory(tutorial.service.OdataServlet.PUNIT_NAME, ds)
    val em = factory.createEntityManager
    // read the existing entries and write to console
    val q = em.createQuery("SELECT t FROM TodoEntity AS t")
    val todoList = q.getResultList.asScala
    println("Size: " + todoList.size)
    todoList.foreach(println)

    val i: Int = if (todoList.isEmpty) -1 else todoList.map { case x: TodoEntity => x.getId }.max
    // create new todo
    em.getTransaction.begin()
    val todo = new TodoEntity(i + 1)
    todo.setSummary("This is a test")
    todo.setDescription("This is a test")
    em.persist(todo)
    em.getTransaction.commit()
    em.close()
  }
}