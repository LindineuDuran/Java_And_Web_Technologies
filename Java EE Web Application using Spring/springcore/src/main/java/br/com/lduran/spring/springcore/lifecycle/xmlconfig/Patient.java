package br.com.lduran.spring.springcore.lifecycle.xmlconfig;

public class Patient
{
	private int id;

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		System.out.println("Inside the setter method");
		this.id = id;
	}

	public void hi()
	{
		System.out.println("Inside Hi method");
	}

	public void bye()
	{
		System.out.println("Inside Bye method");
	}

	@Override
	public String toString()
	{
		return "Patient [id=" + id + "]";
	}
}
