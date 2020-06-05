package tutorial.service;

import nl.buildforce.sequoia.jpa.metadata.core.edm.mapper.exception.ODataJPAException;
import nl.buildforce.sequoia.jpa.processor.core.api.JPAODataCRUDContextAccess;
import nl.buildforce.sequoia.jpa.processor.core.api.JPAODataServiceContext;
import nl.buildforce.sequoia.jpa.processor.core.exception.ODataJPAFilterException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebListener
public class OdataListener implements ServletContextListener {
    private Logger logger;

    private void log(String message) {
        String LOGGER_SUBSYSTEM = "tutorial";
        if (this.logger == null) {
            this.logger = Logger.getLogger(LOGGER_SUBSYSTEM);
        }
        this.logger.log(Level.SEVERE, LOGGER_SUBSYSTEM + "::" + message);
    }

    // Create Service Context
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final DataSource ds = DataSourceHelper.createDataSource(DataSourceHelper.DB_HSQLDB);
        try {
            final JPAODataCRUDContextAccess serviceContext =
                    new JPAODataServiceContext(OdataServlet.PUNIT_NAME, ds, "tutorial.operations", "tutorial.model");

            sce.getServletContext().setAttribute("ServiceContext", serviceContext);
        } catch (RuntimeException | ODataJPAFilterException | ODataJPAException e) {
            log(e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().removeAttribute("ServiceContext");
    }

}