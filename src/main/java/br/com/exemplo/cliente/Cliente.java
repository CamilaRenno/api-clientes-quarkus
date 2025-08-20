package br.com.exemplo.cliente;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "clientes")
public class Cliente extends PanacheEntity {

    @NotBlank
    @Column(nullable = false, length = 120)
    public String nome;

    @Email
    @Column(length = 160)
    public String email;

    @Column(length = 20)
    public String telefone;
}