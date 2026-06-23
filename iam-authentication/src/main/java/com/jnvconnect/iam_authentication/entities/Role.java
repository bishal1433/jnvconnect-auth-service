package com.jnvconnect.iam_authentication.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_roles")
public class Role {
    @Id
    private UUID id =  UUID.randomUUID();
    @Column(name = "role_name", unique = true, nullable = false, length = 100)
    private String name;
}
