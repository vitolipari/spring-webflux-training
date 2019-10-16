package com.liparistudios.webSocketSpringFluxTraining.config;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.util.Collections;

@Configuration
public class ThymeleafConfig {

    private ApplicationContext applicationContext;

//    private static final String JAVA_MAIL_FILE = "classpath:email.properties";
    public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";


    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setCacheable(false);
        templateResolver.setPrefix("/");    // webapp di default preso da application.properties

        templateResolver.setApplicationContext(applicationContext);

        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setCheckExistence(true);

        templateResolver.setOrder(0);

        templateResolver.setSuffix(".html");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        // default
        springTemplateEngine.addTemplateResolver(templateResolver());

        // Resolver for TEXT emails
        springTemplateEngine.addTemplateResolver(textEMailTemplateResolver());

        // Resolver for HTML emails (except the editable one)
        springTemplateEngine.addTemplateResolver(htmlEMailTemplateResolver());

        // Resolver for HTML editable emails (which will be treated as a String)
        springTemplateEngine.addTemplateResolver(stringEMailTemplateResolver());

        // Message source, internationalization specific to emails
        springTemplateEngine.setTemplateEngineMessageSource(emailMessageSource());


        return springTemplateEngine;
    }
/*
*/

/*
*/



    @Bean
    public ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("mail/MailMessages");
        return messageSource;
    }



    @Bean
    public ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[] {".html", ".xhtml"});

        return viewResolver;
    }


    /**
     * Resolver per il contenuto in formato testo
     * @return
     */
    private ITemplateResolver textEMailTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(1));
        templateResolver.setResolvablePatterns(Collections.singleton("text/*"));
        templateResolver.setPrefix("/emailTemplates/");
        templateResolver.setSuffix(".txt");
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    /**
     * Resolver per il contenuto in formato HTML
     * @return
     */
    private ITemplateResolver htmlEMailTemplateResolver() {
        final ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(2));
        templateResolver.setResolvablePatterns(Collections.singleton("html/*"));
        templateResolver.setPrefix("/emailTemplates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    private ITemplateResolver stringEMailTemplateResolver() {
        final StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setOrder(Integer.valueOf(3));
        // No resolvable pattern, will simply process as a String template everything not previously matched
        templateResolver.setTemplateMode("HTML");
        templateResolver.setCacheable(false);
        return templateResolver;
    }



    // XML resolver

    // json resolver

    // csv resolver




/*

    @Bean
    @Scope("prototype")
    public ThymeleafView mainView() {
        ThymeleafView view = new ThymeleafView("main"); // templateName = 'main'
        //view.setStaticVariables(Collections.singletonMap("footer", "The ACME Fruit Company"));
        return view;
    }
*/


}
