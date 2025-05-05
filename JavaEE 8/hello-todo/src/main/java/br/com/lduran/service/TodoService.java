package br.com.lduran.service;

import br.com.lduran.entity.Todo;
import br.com.lduran.util.JpaUtil;
/*import jakarta.persistence.EntityManager;
import jakarta.validation.ConstraintViolationException;*/

import javax.persistence.EntityManager;
import javax.validation.ConstraintViolationException;
import java.util.List;

public class TodoService
{
	public Todo createTodo(Todo todo)
	{
		EntityManager em = JpaUtil.getEntityManager();
		try
		{
			em.getTransaction().begin();
			em.persist(todo);
			em.getTransaction().commit();
			return todo;
		}
		catch (ConstraintViolationException e)
		{
			e.getConstraintViolations().forEach(violation -> {
				System.err.println(">> Violação em: " + violation.getPropertyPath());
				System.err.println(">> Mensagem: " + violation.getMessage());
				System.err.println(">> Valor inválido: " + violation.getInvalidValue());
			});
			em.getTransaction().rollback();
			throw new RuntimeException("Erro ao criar TODO", e);
		}
		finally
		{
			em.close();
		}
	}

	public Todo updateTodo(Todo todo)
	{
		EntityManager em = JpaUtil.getEntityManager();
		try
		{
			em.getTransaction().begin();
			Todo updated = em.merge(todo);
			em.getTransaction().commit();
			return updated;
		}
		catch (Exception e)
		{
			em.getTransaction().rollback();
			throw new RuntimeException("Erro ao atualizar TODO", e);
		}
		finally
		{
			em.close();
		}
	}

	public Todo findTodoById(Long id)
	{
		EntityManager em = JpaUtil.getEntityManager();
		try
		{
			return em.find(Todo.class, id);
		}
		finally
		{
			em.close();
		}
	}

	public List<Todo> getTodos()
	{
		EntityManager em = JpaUtil.getEntityManager();
		try
		{
			return em.createQuery("SELECT t FROM Todo t", Todo.class).getResultList();
		}
		finally
		{
			em.close();
		}
	}
}
