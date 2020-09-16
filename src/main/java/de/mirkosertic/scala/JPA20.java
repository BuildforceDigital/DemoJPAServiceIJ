package de.mirkosertic.scala;

import com.vladmihalcea.OneToMany.Post;
import com.vladmihalcea.OneToMany.PostComment;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaQuery;
import nl.buildforce.sequoia.metadata.api.JPAEntityManagerFactory;
import tutorial.service.DataSourceHelper;

import javax.sql.DataSource;

public class JPA20 {

    public static void main(String[] args) {
        DataSource ds = DataSourceHelper.createDataSource(DataSourceHelper.DB_HSQLDB);
        EntityManagerFactory theEMF = JPAEntityManagerFactory.getEntityManagerFactory(tutorial.service.OdataServlet.PUNIT_NAME, ds);
        //EntityManagerFactory theEMF = Persistence.createEntityManagerFactory("GreenTrak00");
        EntityManager theEntityManager = theEMF.createEntityManager();

        // Enter some test data
        theEntityManager.getTransaction().begin();
        Parent p0 = new Parent("name1","name2");
        theEntityManager.persist(p0);

        Parent p1 = new Parent("name3","name4");
        p1.addChild(new Child("child1"));
        theEntityManager.persist(p1);


        Post post = new Post("First post");

        post.addComment(new PostComment("My first review"));
        post.addComment(new PostComment("My second review"));
        post.addComment(new PostComment("My third review"));
        theEntityManager.persist(post);
        theEntityManager.getTransaction().commit();

        // Query it
        theEntityManager.getTransaction().begin();
        CriteriaQuery<Parent> theQuery = theEntityManager.getCriteriaBuilder().createQuery(Parent.class);
        theQuery.distinct(false).from(Parent.class);
        for (Parent theParent : theEntityManager.createQuery(theQuery).getResultList()) {
            System.out.println(theParent);
        }
    }
}