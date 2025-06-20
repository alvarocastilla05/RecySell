package com.example.Recysell.trabajador.service;

import com.example.Recysell.error.TrabajadorNotFoundException;
import com.example.Recysell.trabajador.dto.CreateTrabajadorRequest;
import com.example.Recysell.trabajador.dto.EditTrabajadorCmd;
import com.example.Recysell.trabajador.dto.GetTrabajadorDto;
import com.example.Recysell.trabajador.model.Trabajador;
import com.example.Recysell.trabajador.repo.TrabajadorRepository;
import com.example.Recysell.user.model.User;
import com.example.Recysell.user.model.UserRole;
import com.example.Recysell.user.repo.UserRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TrabajadorService {

    private final TrabajadorRepository trabajadorRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JavaMailSender mailSender;
    private final EntityManager entityManager;


    @Value("${spring.mail.username}")
    private String fromMail;

    //ListarTrabajadores
    public Page<GetTrabajadorDto> findAll(Pageable pageable, boolean isDeleted){
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedUserFilter");
        filter.setParameter("isDeleted", isDeleted);

        Page<GetTrabajadorDto> result = trabajadorRepository.findAllTrabajadorDto(pageable);
        session.disableFilter("deletedUserFilter");

        if(result.isEmpty()){
            throw new TrabajadorNotFoundException();
        }

        return result;
    }

    //Obtener Trabajador por ID
    public Trabajador findById(UUID id){
        return trabajadorRepository.findById(id)
                .orElseThrow(() -> new TrabajadorNotFoundException(id));
    }

    //Crear User Trabajador
    public Trabajador createTrabajador(CreateTrabajadorRequest createTrabajadorRequest){
        Trabajador trabajador = Trabajador.builder()
                .username(createTrabajadorRequest.username())
                .password(passwordEncoder.encode(createTrabajadorRequest.password()))
                .email(createTrabajadorRequest.email())
                .nombre(createTrabajadorRequest.nombre())
                .apellidos(createTrabajadorRequest.apellido())
                .roles(Set.of(UserRole.TRABAJADOR))
                .puesto(createTrabajadorRequest.puesto())
                .activationToken(generateRandomActivationCode())
                .build();

        try{
            sendActivationEmail(trabajador.getEmail(), trabajador.getActivationToken());
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error al enviar el correo de activación", e);
        }


        return trabajadorRepository.save(trabajador);
    }

    //Editar Trabajador
    public Trabajador edit(EditTrabajadorCmd editTrabajadorCmd, UUID id){
        Optional<Trabajador> trabajador = trabajadorRepository.findById(id);

        if(trabajador.isPresent()){
            return trabajador
                    .map(old -> {
                        old.setUsername(editTrabajadorCmd.username());
                        old.setPassword(passwordEncoder.encode(editTrabajadorCmd.password()));
                        old.setEmail(editTrabajadorCmd.email());
                        old.setNombre(editTrabajadorCmd.nombre());
                        old.setApellidos(editTrabajadorCmd.apellidos());

                        return trabajadorRepository.save(old);
                    }).get();
        }else{
            throw new TrabajadorNotFoundException(id);
        }
    }

    //Eliminar Trabajador
    public void deleteById(UUID id){
        trabajadorRepository.deleteById(id);
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
