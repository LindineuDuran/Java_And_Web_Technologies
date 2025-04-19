package br.com.lduran.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil
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
