package tutorial.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.internal.jdbc.DriverDataSource;

import javax.sql.DataSource;
import java.util.Properties;

public class DataSourceHelper {
    private static final String DB_SCHEMA = "OLINGO";

    // private static final String HSQLDB_URL = "jdbc:hsqldb:mem:com.sample";
    private static final String HSQLDB_URL = "jdbc:hsqldb:file:~/IdeaProjects/Buildforce Digital 00/DemoOdataJPAWebContainer/GreentrakData/DevoGreentrak00";
    private static final String HSQLDB_DRIVER_CLASS_NAME = "org.hsqldb.jdbcDriver";

    private static final String DERBY_URL = "jdbc:derby:testdb;create=true;traceFile=derby_trace.log;trace_level=0xFFFFFFFF";
    private static final String DERBY_DRIVER_CLASS_NAME = "org.apache.derby.jdbc.EmbeddedDriver";

    private static final String REMOTE_URL = "jdbc:$DBNAME$:$Host$:$Port$";

    public static final int DB_HSQLDB = 2;
    public static final int DB_REMOTE = 3;
    public static final int DB_DERBY = 4;

    public static DataSource createDataSource(int database) {
        DriverDataSource ds = null;
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        switch (database) {
            case DB_HSQLDB:
                ds = new DriverDataSource(classLoader, HSQLDB_DRIVER_CLASS_NAME, HSQLDB_URL, "SA", null);
                break;
            case DB_DERBY:
                ds = new DriverDataSource(classLoader, DERBY_DRIVER_CLASS_NAME, DERBY_URL, null, null);
                break;

            case DB_REMOTE:
                String env = System.getenv().get("REMOTE_DB_LOGON");
                ObjectMapper mapper = new ObjectMapper();
                ObjectNode dbInfo;
                try {
                    dbInfo = (ObjectNode) mapper.readTree(env);
                } catch (JsonProcessingException e) {
                    return null;
                }
                String url = REMOTE_URL;
                url = url.replace("$Host$", dbInfo.get("hostname").asText());
                url = url.replace("$Port$", dbInfo.get("port").asText());
                url = url.replace("$DBNAME$", dbInfo.get("dbname").asText());
                String driver = dbInfo.get("driver").asText();
                ds = new DriverDataSource(classLoader, driver, url, dbInfo.get("username").asText(), dbInfo.get("password").asText());
                return ds;
            default:
                return null;
        }

        Flyway flyway = Flyway.configure().dataSource(ds).schemas(DB_SCHEMA).load();
        flyway.migrate();
        return ds;
    }
}