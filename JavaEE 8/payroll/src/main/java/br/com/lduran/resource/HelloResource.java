package br.com.lduran.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

@Path("hello")
public class HelloResource
{
	@Path("{name}")
	@GET
	public Response sayHello(@PathParam("name") String name)
	{
		String greeting = "Hello " + name;
		return Response.ok(greeting).build();
	}

	@GET
	@Path("greet") //api/v1/hello/greet - GET Method
	public String greet()
	{
		return "Hello, World";
	}
}