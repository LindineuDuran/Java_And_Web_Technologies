package br.com.lduran.spring.springcore.innerbeans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test
{
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"br/com/lduran/spring/springcore/innerbeans/config.xml");

		Employee employee1 = (Employee) context.getBean("employee");
		System.out.println(employee1 + " - " + employee1.hashCode());

		Employee employee2 = (Employee) context.getBean("employee");
		System.out.println(employee2 + " - " + employee2.hashCode());

		Prescription prescription1 = (Prescription) context.getBean("prescription");
		System.out.println(prescription1 + " - " + prescription1.hashCode());

		Prescription prescription2 = (Prescription) context.getBean("prescription");
		System.out.println(prescription2 + " - " + prescription2.hashCode());
	}
}
