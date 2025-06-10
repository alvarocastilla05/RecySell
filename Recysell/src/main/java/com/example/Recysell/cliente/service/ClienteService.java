package com.example.Recysell.cliente.service;

import com.example.Recysell.cliente.dto.CreateClienteRequest;
import com.example.Recysell.cliente.dto.EditClienteCmd;
import com.example.Recysell.cliente.dto.GetClienteDto;
import com.example.Recysell.cliente.model.Cliente;
import com.example.Recysell.cliente.repo.ClienteRepository;
import com.example.Recysell.error.ClienteNotFoundException;
import com.example.Recysell.producto.model.Producto;
import com.example.Recysell.producto.repo.ProductoRepository;
import com.example.Recysell.user.model.UserRole;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
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
    private final ProductoRepository productoRepository;

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

        clienteRepository.save(cliente);

        sendActivationEmail(cliente.getEmail(), cliente.getActivationToken());

        return cliente;
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
    @Transactional
    public void deleteById(UUID id) {
        Cliente cliente = clienteRepository.findById(id)
                .orElseThrow(() -> new ClienteNotFoundException(id));

        cliente.getListProductosEnVenta().forEach(producto -> producto.setDeleted(true));

        cliente.setDeleted(true);

        clienteRepository.save(cliente);
    }

    private void sendActivationEmail(String to, String activationToken) {
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setFrom(fromMail);
            helper.setTo(to);
            helper.setSubject("Activación de cuenta");

            String html = "<p>Gracias por registrarse.</p><p>Su código de activación es: <strong>" + activationToken + "</strong></p>";
            helper.setText(html, true);

            mailSender.send(message);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error enviando email de activación", e);
        }
    }



    public String generateRandomActivationCode(){
        return UUID.randomUUID().toString();
    }


    //Añadir Producto a Favoritos
    public void addProductoFavorito(UUID clienteId, Long productoId){
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
        Optional<Producto> productoOptional = productoRepository.findById(productoId);

        if(clienteOptional.isPresent() && productoOptional.isPresent()){
            clienteOptional.get().addProductoFavorito(productoOptional.get());
            clienteRepository.save(clienteOptional.get());
    }else {
            throw new ClienteNotFoundException(clienteId);
        }
    }

    //Listar Productos Favoritos de un Usuario
    public Set<Producto> getProductoFavorito(UUID clienteId){
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);

        if(clienteOptional.isPresent()){
            if (clienteOptional.get().getListaFavoritos().isEmpty()){
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No hay productos favoritos");
            }
            return clienteOptional.get().getListaFavoritos();
        }else {
            throw new ClienteNotFoundException(clienteId);
        }
    }

    //Eliminar Producto de Favoritos
    public void deleteProductoFavorito(UUID clienteId, Long productoId){
        Optional<Cliente> clienteOptional = clienteRepository.findById(clienteId);
        Optional<Producto> productoOptional = productoRepository.findById(productoId);

        if(clienteOptional.isPresent() && productoOptional.isPresent()){
            clienteOptional.get().removeProductoFavorito(productoOptional.get());
            clienteRepository.save(clienteOptional.get());
        }else {
            throw new ClienteNotFoundException(clienteId);
        }
    }

}
