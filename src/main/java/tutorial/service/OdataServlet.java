package tutorial.service;

import com.sap.olingo.jpa.metadata.core.edm.mapper.exception.ODataJPAModelException;
import com.sap.olingo.jpa.processor.core.api.JPAODataCRUDContextAccess;
import com.sap.olingo.jpa.processor.core.api.JPAODataCRUDHandler;
import tutorial.persistence.ExampleCUDRequestHandler;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/ServletPath.svc/*")
public class OdataServlet extends HttpServlet {
	protected static final String PUNIT_NAME = "TutorialPU";

	@Override
	protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException {
		final JPAODataCRUDContextAccess serviceContext =
				(JPAODataCRUDContextAccess) getServletContext().getAttribute("ServiceContext");
		final JPAODataCRUDHandler handler = new JPAODataCRUDHandler(serviceContext);

		handler.getJPAODataRequestContext().setCUDRequestHandler(new ExampleCUDRequestHandler());
		try { handler.process(req, resp); }
		catch ( ODataJPAModelException e) { throw new ServletException(e); }
	}

}