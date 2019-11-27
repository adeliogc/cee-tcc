package br.com.tcc.cee.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringMvcConfiguration implements ApplicationContextAware{
	
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * @Bean public ViewResolver jasperReportsViewResolver(DataSource dataSource){
	 * JasperReportsViewResolver resolver = new JasperReportsViewResolver();
	 * resolver.setPrefix("classpath:/relatorios/"); resolver.setSuffix(".jasper");
	 * resolver.setViewNames("relatorios_*");
	 * resolver.setViewClass(JasperReportsMultiFormatView.class);
	 * resolver.setJdbcDataSource(dataSource); return resolver; }
	 */
}
