package proyeto1.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    /**
     * URL base del servidor obtenida desde las propiedades.
     */
    @Value("${server.servlet.context-path}")
    private String contextPath;

    /**
     * Configura la información principal de la API para la documentación.
     *
     * @return Objeto OpenAPI configurado con la información de la API
     */
    @Bean
    public OpenAPI catalogServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Catálogo de Productos")
                        .description("API RESTful para la gestión de un catálogo de productos. " +
                                "Este microservicio es un material educativo que muestra buenas prácticas " +
                                "en el desarrollo de APIs con Spring Boot.")
                        .version("v1.0")
                        .contact(new Contact()
                                .name("Equipo de Desarrollo")
                                .email("desarrollo@example.com")
                                .url("https://www.example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0")))
                .servers(List.of(
                        new Server().url(contextPath).description("Servidor por defecto")  // Usando el contexto configurado
                ))
                .components(new Components());
    }
}

