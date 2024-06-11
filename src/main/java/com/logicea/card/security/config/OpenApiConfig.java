package com.logicea.card.security.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.security.SecuritySchemes;
import jakarta.validation.constraints.Email;
import jdk.jfr.Name;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "CARD TASK REST API",
        version = "1.0",
        termsOfService = "#",
        contact = @Contact(
            name = "Kennedy Ikatanyi",
            url = "https://worldnettps.com/contact/",
            email = "support@worldnettps.com"),
        license = @License(
            name = "Use and Distribution License",
            url = "#"),
        description = "This is a simple Spring boot application focused on the interaction with a Spring REST server.\n"
            + "\n"
            + "The data-centric application offers a basic user-management section, with the possibility to sign-in new users.\n"
            + "Each user can manage a list of task cards, each one associated to a name, color, status date, and a description.\n"
            + "There are three different roles:\n"
            + "* Admin: has full permissions on the whole data\n"
            + "* Member manager: has permissions on user data\n"
            + "* User: has full permissions on owned Card data.")
)

@SecuritySchemes({
        @SecurityScheme(name = "basicAuth", type = SecuritySchemeType.HTTP, scheme = "basic"),
        @SecurityScheme(name = "bearerToken", type = SecuritySchemeType.HTTP, scheme = "bearer", bearerFormat = "JWT")
})
public class OpenApiConfig {
}
