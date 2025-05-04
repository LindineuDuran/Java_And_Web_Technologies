package br.com.lduran.config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaConfig
{
	private static EntityManagerFactory emf;

	static
	{
		try
		{
			// Cria a fábrica de EntityManagers
			emf = Persistence.createEntityManagerFactory("myPU");
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new ExceptionInInitializerError("Erro ao inicializar o EntityManagerFactory: " + e);
		}
	}

	public static EntityManager getEntityManager()
	{
		return emf.createEntityManager();
	}
}
