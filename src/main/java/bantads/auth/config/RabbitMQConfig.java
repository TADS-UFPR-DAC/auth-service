package bantads.auth.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String MENSAGEM_EXCHANGE = "mensagem";
    public static final String FILA_MENSAGEM = "MensagemQueue";
    public static final String CHAVE_MENSAGEM = "mensagem";

    @Bean
    DirectExchange mensagemExchange() {
        return new DirectExchange(MENSAGEM_EXCHANGE);
    }

    @Bean
    Queue mensagemQueue() {
        return QueueBuilder.durable(FILA_MENSAGEM).build();
    }

    @Bean
    Binding mensagemBinding() {
        return BindingBuilder.bind(mensagemQueue()).to(mensagemExchange()).with(CHAVE_MENSAGEM);
    }

}