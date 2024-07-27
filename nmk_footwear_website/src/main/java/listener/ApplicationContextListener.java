package listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import utility.JPAUtil;

/**
 * Application Lifecycle Listener implementation class ApplicationContextListener
 *
 */
@WebListener
public class ApplicationContextListener implements ServletContextListener {

    public ApplicationContextListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	// Initialize the EntityManagerFactory from the JPA Utility class.
        JPAUtil.getEntityManagerFactory();
        System.out.println("EntityManagerFactory initialized.");
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
    	// Close the EntityManagerFactory from the JPA Utility class.
        JPAUtil.shutdown();
        System.out.println("EntityManagerFactory shutdown.");
    }
	
}
