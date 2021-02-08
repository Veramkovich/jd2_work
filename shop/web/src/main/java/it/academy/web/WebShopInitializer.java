package it.academy.web;

import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.Set;

public class WebShopInitializer implements ServletContainerInitializer {


    @Override
    public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext context
                = new AnnotationConfigWebApplicationContext();
        context.register(WebShopConfiguration.class);

        DispatcherServlet servlet =
                new DispatcherServlet(context);

        final ServletRegistration.Dynamic dispatcher =
                servletContext.addServlet("dispatcher", servlet);
        dispatcher.setLoadOnStartup(1);
        dispatcher.addMapping("/");
    }
}
