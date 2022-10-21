package com.proyectointegrador.proyectointegrador.Model;

import com.proyectointegrador.proyectointegrador.Enums.Enum_Rol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class UserClient {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "image")
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Enum_Rol rol;
    @Column(name="auth0Id", unique = true)
    private String auth0Id;

    public UserClient(String email, String image, String auth0Id, Enum_Rol rol) {
        this.email = email;
        this.image = image;
        this.auth0Id = auth0Id;
        this.rol = rol;
    }

}
