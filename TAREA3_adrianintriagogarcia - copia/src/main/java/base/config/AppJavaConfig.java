

package base.config;

import java.util.ResourceBundle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javafx.stage.Stage;

@Configuration
public class AppJavaConfig {

    @Bean
    public ResourceBundle resourceBundle() {
        return ResourceBundle.getBundle("messages");
    }

    @Bean
    @Lazy
    public StageManager stageManager(SpringFXMLLoader springFXMLLoader,
                                     Stage stage) {
        return new StageManager(springFXMLLoader, stage);
    }
}