package bantads.auth.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {
    public static final String AUTH_EXCHANGE = "auth";
    public static final String MENSAGEM_EXCHANGE = "mensagem";
    public static final String FILA_MENSAGEM = "MensagemQueue";
    public static final String FILA_DELETAR_USUARIO = "DeletarUsuarioQueue";
    public static final String CHAVE_MENSAGEM = "mensagem";
    public static final String CHAVE_DELETAR_USUARIO = "deletarUsuario";

    @Bean
    DirectExchange mensagemExchange() {
        return new DirectExchange(MENSAGEM_EXCHANGE);
    }

    @Bean
    DirectExchange authExchange() {
        return new DirectExchange(AUTH_EXCHANGE);
    }

    @Bean
    Queue mensagemQueue() {
        return QueueBuilder.durable(FILA_MENSAGEM).build();
    }

    @Bean
    Queue deletarContaQueue() {
        return QueueBuilder.durable(FILA_DELETAR_USUARIO).build();
    }

    @Bean
    Binding mensagemBinding() {
        return BindingBuilder.bind(mensagemQueue()).to(mensagemExchange()).with(CHAVE_MENSAGEM);
    }

    @Bean
    Binding deletarUsuarioBinding() {
        return BindingBuilder.bind(deletarContaQueue()).to(authExchange()).with(CHAVE_DELETAR_USUARIO);
    }

}