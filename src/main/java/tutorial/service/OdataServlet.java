package tutorial.service;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.olingo.commons.api.ex.ODataException;

import com.sap.olingo.jpa.metadata.api.JPAEntityManagerFactory;
import com.sap.olingo.jpa.processor.core.api.JPAODataCRUDContextAccess;
import com.sap.olingo.jpa.processor.core.api.JPAODataCRUDHandler;
import tutorial.modify.CUDRequestHandler;

@WebServlet(urlPatterns="/DemoJPA.svc/*")
public class OdataServlet extends HttpServlet {
	protected static final String PUNIT_NAME = "TutorialPU";
	private final DataSource ds = DataSourceHelper.createDataSource(DataSourceHelper.DB_HSQLDB);
	private final EntityManagerFactory emf =JPAEntityManagerFactory.getEntityManagerFactory(PUNIT_NAME, ds);

	@Override
	protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException {
		final EntityManager em = emf.createEntityManager();
		final JPAODataCRUDContextAccess serviceContext =
				(JPAODataCRUDContextAccess) getServletContext().getAttribute("ServiceContext");
		final JPAODataCRUDHandler handler = new JPAODataCRUDHandler(serviceContext);

		try {
			handler.getJPAODataRequestContext().setEntityManager(em);
			handler.getJPAODataRequestContext().setCUDRequestHandler(new CUDRequestHandler());
			handler.process(req, resp);
		} catch (RuntimeException | ODataException e) {
			throw new ServletException(e);
		} finally {
			if (em != null) em.close();
		}
	}

}