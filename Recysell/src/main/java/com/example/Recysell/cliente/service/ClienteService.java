package com.example.Recysell.cliente.service;

import com.example.Recysell.cliente.dto.CreateClienteRequest;
import com.example.Recysell.cliente.dto.EditClienteCmd;
import com.example.Recysell.cliente.dto.GetClienteDto;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.cliente.repo.ClienteRepository;
import com.example.Recysell.error.ClienteNotFoundException;
import com.example.Recysell.user.model.UserRole;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.hibernate.Session;
import org.hibernate.Filter;



import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final EntityManager entityManager;

    @Value("${spring.mail.username}")
    private String fromMail;


    //Listar Clientes
    public Page<GetClienteDto> findAll(Pageable pageable, boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedUserFilter");
        filter.setParameter("isDeleted", isDeleted);


        Page<GetClienteDto> result = clienteRepository.findAllClienteDto(pageable);
        session.disableFilter("deletedUserFilter");

        if (result.isEmpty()) {
            throw new ClienteNotFoundException();
        }

        return result;
    }

    //Filtrar Cliente por ID
    public Cliente findById(UUID id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));
    }

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

    //Editar Cliente
    public Cliente edit(EditClienteCmd editClienteCmd, UUID id){
        Optional<Cliente> clienteOptional = clienteRepository.findById(id);

        if(clienteOptional.isPresent()){
            return clienteOptional
                    .map(old -> {
                        old.setUsername(editClienteCmd.username());
                        old.setEmail(editClienteCmd.email());
                        old.setNombre(editClienteCmd.nombre());
                        old.setApellidos(editClienteCmd.apellidos());
                        old.setPassword(passwordEncoder.encode(editClienteCmd.password()));

                        return clienteRepository.save(old);
                    }).get();
        }else{
            throw new ClienteNotFoundException(id);
        }
    }

    //Eliminar Cliente
    public void deleteById(UUID id){
        clienteRepository.deleteById(id);
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
