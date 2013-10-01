package launch;

import java.io.File;

import javax.naming.Context;
import javax.naming.InitialContext;

//import org.apache.catalina.Context;
import org.apache.catalina.startup.Tomcat;

//import com.mysql.jdbc.jdbc2.optional.MysqlConnectionPoolDataSource;

import org.postgresql.ds.PGConnectionPoolDataSource;

/**
 * Class to create an embedded Tomcat server and setup application JNDI environment bindings.
 */
public class Main {

    public static void main(String[] args) throws Exception {

        // Some of below code is from Heroku example
        String webappDirLocation = "src/main/webapp/";
        Tomcat tomcat = new Tomcat();

        //The port that we should run on can be set into an environment variable
        //Look for that variable and default to 8080 if it isn't there.
        String webPort = System.getenv("PORT");
        if(webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        tomcat.setPort(Integer.valueOf(webPort));
        
        // Added following to Heroku example to configure JNDI environmental entries
        // Not sure why this is needed in addition to enviroment entries in web.xml or META-INF/context.xml
        System.setProperty(javax.naming.Context.URL_PKG_PREFIXES, "org.apache.naming");
        System.setProperty(javax.naming.Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
        InitialContext initialContext = new InitialContext();
        initialContext.createSubcontext("java:");
        initialContext.createSubcontext("java:comp");
        
        // Below is the solution to bind multiple name value pairs to a subcontext
        Context subContext = initialContext.createSubcontext("java:comp/env");
        subContext.bind("IUserSvc", "com.travelplanner.model.service.usersvc.UserSvcImpl");
        subContext.bind("IFavoriteListSvc", "com.travelplanner.model.service.favoritelistsvc.FavoriteListSvcImpl");
        subContext.bind("ITravelPlanSvc", "com.travelplanner.model.service.travelplansvc.TravelPlanSvcImpl");
        
        initialContext.createSubcontext("java:comp/env/jdbc");

        // Database connection parameters
        PGConnectionPoolDataSource dataSource = new PGConnectionPoolDataSource();
        dataSource.setServerName("ec2-107-21-120-102.compute-1.amazonaws.com");
        dataSource.setPortNumber(5432);
        dataSource.setDatabaseName("dfhlo3ueqaut0m");
        dataSource.setUser("svdiufdculjrdb");
        dataSource.setPassword("2XiPTyo_CX0gVEYeBBoWPb_SCB");
        dataSource.setSsl(true);
        initialContext.bind("java:comp/env/jdbc/postgresqlconnector", dataSource);
        
        org.apache.catalina.Context context = tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
        File configFile = new File("src/main/webapp/META-INF/context.xml");
        context.setConfigFile(configFile.toURI().toURL());
        
        // Print out more diagnostic information
        System.out.println();
        System.out.println(configFile.toURI().toURL());
        System.out.println();
        System.out.println("configuring app with basedir: " + new File(webappDirLocation).getAbsolutePath());
        System.out.println();

        tomcat.start();
        
        // Print out more diagnostic information
        System.out.println();
        System.out.println(context.getDisplayName());  // Confirms tomcat is reading web.xml file
        System.out.println(InitialContext.doLookup("java:comp/env/IUserSvc"));  // Prints out mappings
        System.out.println(InitialContext.doLookup("java:comp/env/IFavoriteListSvc"));
        System.out.println(InitialContext.doLookup("java:comp/env/ITravelPlanSvc"));
        System.out.println();
        
        tomcat.getServer().await();  
    }
}