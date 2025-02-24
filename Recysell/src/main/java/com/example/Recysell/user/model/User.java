package com.example.Recysell.user.model;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name="user_entity")
@Inheritance(strategy = InheritanceType.JOINED)
@SQLDelete(sql = "UPDATE user_entity SET deleted = true WHERE id = ?")
@FilterDef(name = "deletedUserFilter", parameters = @ParamDef(name = "isDeleted", type = Boolean.class))
@Filter(name = "deletedUserFilter", condition = "deleted = :isDeleted")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NaturalId
    @Column(unique = true, updatable = false)
    private String username;

    private String password;

    private String email;

    private String nombre;

    private String apellidos;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<UserRole> roles;

    private boolean deleted = Boolean.FALSE;

    @Builder.Default
    private boolean enabled = false;

    private String activationToken;

    @Builder.Default
    private Instant createdAt = Instant.now();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toSet());
    }
}
