package tutorial.service;

import com.sap.olingo.jpa.metadata.core.edm.mapper.exception.ODataJPAException;
import com.sap.olingo.jpa.processor.core.api.JPAODataCRUDContextAccess;
import com.sap.olingo.jpa.processor.core.api.JPAODataServiceContext;
import com.sap.olingo.jpa.processor.core.exception.ODataJPAFilterException;

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
            final JPAODataCRUDContextAccess serviceContext = JPAODataServiceContext.with()
                    .setPUnit(OdataServlet.PUNIT_NAME)
                    .setDataSource(ds)
                    .setTypePackage("tutorial.operations", "tutorial.model")
                    .build();
            sce.getServletContext().setAttribute("ServiceContext", serviceContext);
        } catch (RuntimeException | ODataJPAFilterException | ODataJPAException e) {
            log (e.getMessage());
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("ServiceContext", null);
    }
}