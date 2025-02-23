package com.example.Recysell.cliente.service;

import com.example.Recysell.cliente.dto.CreateClienteRequest;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.cliente.repo.ClienteRepository;
import com.example.Recysell.user.model.UserRole;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromMail;

    //Crear User Cliente
    public Cliente createCliente(CreateClienteRequest createClienteRequest){
        Cliente cliente = Cliente.builder()
                .username(createClienteRequest.username())
                .email(createClienteRequest.email())
                .nombre(createClienteRequest.nombre())
                .apellidos(createClienteRequest.apellidos())
                .password(passwordEncoder.encode(createClienteRequest.password()))
                .roles(Set.of(UserRole.CLIENTE))
                .activationToken(generateRandomActivationCode())
                .build();

        try {
            sendActivationEmail(cliente.getEmail(), cliente.getActivationToken());
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al enviar el correo de activación", e);
        }

        return clienteRepository.save(cliente);
    }

    private void sendActivationEmail(String to, String activationToken) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom(fromMail);
        helper.setTo(to);
        helper.setSubject("Activación de cuenta");
        helper.setText("Su código de activación es: " + activationToken, true);

        mailSender.send(message);
    }

    public String generateRandomActivationCode(){
        return UUID.randomUUID().toString();
    }

}
