package br.com.lduran.rest;

import br.com.lduran.entity.Todo;
import br.com.lduran.service.TodoService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.logging.Logger;

@Path("todo")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TodoRest
{
	private static final Logger LOGGER = Logger.getLogger(TodoRest.class.getName());

	private final TodoService todoService = new TodoService();

	@Path("new")
	@POST
	public Response createTodo(Todo todo)
	{
		System.out.println("task: " + todo.getTask());
		System.out.println("dueDate: " + todo.getDueDate());

		todoService.createTodo(todo);
		return Response.ok(todo).build();
	}

	@Path("update")
	@PUT
	public Response updateTodo(Todo todo)
	{
		todoService.updateTodo(todo);
		return Response.ok(todo).build();
	}

	@Path("{id}")
	@GET
	public Todo getTodo(@PathParam("id") Long id)
	{
		return todoService.findTodoById(id);
	}

	@Path("list")
	@GET
	public List<Todo> getTodos()
	{
		return todoService.getTodos();
	}

	@Path("status")
	@POST
	public Response markAsComplete(@QueryParam("id") Long id)
	{
		Todo todo = todoService.findTodoById(id);
		todo.setCompleted(true);
		todoService.updateTodo(todo);

		return Response.ok(todo).build();
	}
}
