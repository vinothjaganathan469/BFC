package com.chatback.configuration;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer 
{

	@Override
	protected Class[] getRootConfigClasses()
	{
		System.out.println("ENTERING GETROOTCON");
		return new Class[] { AppConfig.class };
	}

	@Override
	protected Class[] getServletConfigClasses() 
	{
		return null;
	}

	@Override
	protected String[] getServletMappings() 
	{
		return new String[] { "/" };
	}
	
	
}
