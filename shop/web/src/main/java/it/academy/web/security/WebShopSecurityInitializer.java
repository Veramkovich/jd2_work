package it.academy.web.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;
import org.springframework.stereotype.Component;

@Component
public class WebShopSecurityInitializer extends AbstractSecurityWebApplicationInitializer {

    public WebShopSecurityInitializer() {
        super(WebShopSecurityConfiguration.class);
    }
}
