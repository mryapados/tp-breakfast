package fr.treeptik.local.run;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import fr.treeptik.conf.ApplicationConfiguration;

public class Run {

	public static ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
	//public static RefrigeratorService refrigeratorService = (RefrigeratorService) context.getBean("userHouseService");
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
