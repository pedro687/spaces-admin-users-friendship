package com.spaces.user.svc.infrastructure;

import com.spaces.user.svc.infrastructure.configuration.WebServerConfig;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.swing.text.Element;
import java.lang.annotation.*;

@Target(ElementType.TYPE) // Sinaliza que só pode ser usado em Classes e Métodos
@Retention(RetentionPolicy.RUNTIME)
@Inherited // faz com que classes filhas herdem essa anotação e informações da anotação na classe pai
@ActiveProfiles("test")
@SpringBootTest(classes = WebServerConfig.class)
@ExtendWith(CleanUpExtension.class)
public @interface IntegrationTest {
}
