package be.kifaru.examples.servlet;

import java.io.InputStream;
import java.util.Properties;

import javax.servlet.ServletContext;

/**
 * @author Devid Verfaillie
 * @since 2016-08-16
 */
public class VersionFinder {

    public static String findJarVersion(ServletContext servletContext) {
        String groupId = "be.kifaru";
        String artifactId = "examples";
        String propertiesFileName = "META-INF/maven/" + groupId + "/" + artifactId + "/pom.properties";

        return findVersion(servletContext, propertiesFileName);
    }

    private static String findVersion(ServletContext servletContext, String propertiesFileName) {
        try (InputStream in = servletContext.getResourceAsStream(propertiesFileName)) {
            if (null == in) {
                System.err.printf("Could not find version in file [%s]%n", propertiesFileName);
                return "unknown";
            }

            Properties props = new Properties();
            props.load(in);

            return props.getProperty("version");
        } catch (Exception e) {
            System.err.printf("Could not find version in file [%s]%n", propertiesFileName);
            e.printStackTrace(System.err);

            return "unknown";
        }
    }

    public static String findWarVersion(ServletContext servletContext) {
        String groupId = "be.kifaru";
        String artifactId = "myWarModule";
        String propertiesFileName = "META-INF/maven/" + groupId + "/" + artifactId + "/pom.properties";

        return findVersion(servletContext, propertiesFileName);
    }
}
