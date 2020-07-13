package tutorial.service;

import nl.buildforce.sequoia.processor.core.api.JPAODataCRUDContextAccess;
import nl.buildforce.olingo.commons.api.ex.ODataException;
import nl.buildforce.sequoia.processor.core.api.JPAODataGetHandler;
import nl.buildforce.sequoia.processor.core.processor.JPAODataRequestContextImpl;
import tutorial.persistence.ExampleCUDRequestHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns="/ServletPath.svc/*")
public class OdataServlet extends HttpServlet {
	protected static final String PUNIT_NAME = "GreenTrak00";

	@Override
	protected void service(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException {
		final JPAODataCRUDContextAccess serviceContext =
				(JPAODataCRUDContextAccess) getServletContext().getAttribute("ServiceContext");
		final JPAODataGetHandler handler = new JPAODataGetHandler(serviceContext); // JPAODataCRUDHandler(serviceContext);

		((JPAODataRequestContextImpl) handler.getJPAODataRequestContext()).setCUDRequestHandler(new ExampleCUDRequestHandler());
		try { handler.process(req, resp); }
		catch ( ODataException e) { throw new ServletException(e); }
	}

}