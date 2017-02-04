package be.kifaru.examples.servlet;

import javax.servlet.ServletContextEvent;

/**
 * Also see: <ul><li><a href="https://wiki.apache.org/tomcat/MemoryLeakProtection">https://wiki.apache.org/tomcat/MemoryLeakProtection</a></li>
 * <li><a href="https://www.eclipse.org/jetty/documentation/current/preventing-memory-leaks.html">https://www.eclipse.org/jetty/documentation/current/preventing-memory-leaks.html</a></li>
 * </ul>
 *
 * @author Devid Verfaillie
 * @since 2016-08-16
 */
public class ServletContextListener implements javax.servlet.ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        String warVersion = VersionFinder.findWarVersion(sce.getServletContext());

        System.out.printf("========================================================================================%n");
        System.out.printf("Start ServletContextListener.contextInitialized - version [%s]%n", warVersion);
        System.out.printf("========================================================================================%n");

        // do some set-up...

        System.out.printf("========================================================================================%n");
        System.out.printf("End ServletContextListener.contextInitialized - version [%s]%n", warVersion);
        System.out.printf("========================================================================================%n");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        String warVersion = VersionFinder.findWarVersion(sce.getServletContext());

        System.out.printf("========================================================================================%n");
        System.out.printf("Start ServletContextListener.contextDestroyed - version [%s]%n", warVersion);
        System.out.printf("========================================================================================%n");

        // do some tear-down...

        System.out.printf("========================================================================================%n");
        System.out.printf("End ServletContextListener.contextDestroyed - version [%s]%n", warVersion);
        System.out.printf("========================================================================================%n");
    }
}
