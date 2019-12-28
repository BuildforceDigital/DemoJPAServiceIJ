package tutorial.service;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.olingo.commons.api.ex.ODataException;

import com.sap.olingo.jpa.processor.core.api.JPAODataCRUDContextAccess;
import com.sap.olingo.jpa.processor.core.api.JPAODataCRUDHandler;

@WebServlet(urlPatterns="/DemoJPA.svc/*")
public class Servlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void service(final HttpServletRequest req, final HttpServletResponse resp)
			throws ServletException {

		try {
			final JPAODataCRUDContextAccess serviceContext =
					(JPAODataCRUDContextAccess) getServletContext().getAttribute("ServiceContext");
			new JPAODataCRUDHandler(serviceContext).process(req, resp);
		} catch (RuntimeException | ODataException e) {
			throw new ServletException(e);
		}
	}
}