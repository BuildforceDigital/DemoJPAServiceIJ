package tutorial.service;

import com.sap.olingo.jpa.metadata.api.JPAEdmProvider;
import com.sap.olingo.jpa.metadata.api.JPAEntityManagerFactory;
import org.apache.olingo.commons.api.ex.ODataException;
import org.apache.olingo.server.api.OData;
import org.apache.olingo.server.api.ODataHttpHandler;
import org.apache.olingo.server.api.ServiceMetadata;

import javax.persistence.EntityManagerFactory;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;

@WebServlet(urlPatterns="/DemoJPA.svc/*")
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String PUNIT_NAME = "PersistenceUnit";

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
		try {
			EntityManagerFactory emf = JPAEntityManagerFactory.getEntityManagerFactory(PUNIT_NAME, new HashMap<>());
			JPAEdmProvider metadataProvider = new JPAEdmProvider(PUNIT_NAME, emf, null, new String[]{"tutorial.model"});

			OData odata = OData.newInstance();
			ServiceMetadata edm = odata.createServiceMetadata(metadataProvider, new ArrayList<>());
			ODataHttpHandler handler = odata.createHandler(edm);
			handler.process(req, resp);
		} catch (RuntimeException | ODataException e) {
			throw new ServletException(e);
		}

	}
}