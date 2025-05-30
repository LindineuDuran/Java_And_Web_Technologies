package br.com.lduran.spring.springcore.coursetask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test
{
	public static void main(String[] args)
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(
				"br/com/lduran/spring/springcore/coursetask/config.xml");

		University university1 = (University) context.getBean("university");
		System.out.println(university1 + " - " + university1.hashCode());

		University university2 = (University) context.getBean("university");
		System.out.println(university2 + " - " + university2.hashCode());
	}
}
