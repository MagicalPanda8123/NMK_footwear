package utility;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory factory;

	// Static initializer block to initialize the EntityManagerFactory
	static {
		// Create the EntityManagerFactory using the persistence unit name defined in
		// persistence.xml
		factory = Persistence.createEntityManagerFactory("MSSQL_Persistence_Unit");
	}

	// Public method to access the EntityManagerFactory
	public static EntityManagerFactory getEntityManagerFactory() {
		return factory;
	}

	// Public method to shut down the EntityManagerFactory
	public static void shutdown() {
		if (factory != null) {
			factory.close();
		}
	}
}
