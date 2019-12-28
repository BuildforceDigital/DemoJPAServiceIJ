package tutorial.service;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import org.apache.olingo.commons.api.ex.ODataException;

import com.sap.olingo.jpa.processor.core.api.JPAODataCRUDContextAccess;
import com.sap.olingo.jpa.processor.core.api.JPAODataServiceContext;

@WebListener
public class Listener implements ServletContextListener {

    // Create Service Context
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        final DataSource ds = DataSourceHelper.createDataSource(DataSourceHelper.DB_HSQLDB);
        try {
            final JPAODataCRUDContextAccess serviceContext = JPAODataServiceContext.with()
                    .setPUnit(Servlet.PUNIT_NAME)
                    .setDataSource(ds)
                    .setTypePackage("tutorial.operations", "tutorial.model")
                    .build();
            sce.getServletContext().setAttribute("ServiceContext", serviceContext);
        } catch (ODataException e) {
            // Log error
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("ServiceContext", null);
    }
}