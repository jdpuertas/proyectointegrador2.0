package com.proyectointegrador.proyectointegrador.Services;

import com.proyectointegrador.proyectointegrador.Enums.Enum_Rol;
import com.proyectointegrador.proyectointegrador.Model.UserClient;
import com.proyectointegrador.proyectointegrador.Repository.UserClientRepository;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserClientService {
    private UserClientRepository repository;

    public UserClientService(UserClientRepository repository){
        this.repository = repository;
    }

    public UserClient createUserClient (UserClient newUserClient){
        if(newUserClient.getRol()==null){
            newUserClient.setRol(Enum_Rol.valueOf("VISITANTE"));
        }
        return this.repository.save(newUserClient);
    }

    public UserClient findUserByEmail (String email){
        return this.repository.findByEmail(email);
    }
    public UserClient getOrCreateUser(Map<String, Object> userData){
        String email = (String) userData.get("email");

        UserClient userClient = findUserByEmail(email);
        if(userClient==null) {
            String name = (String) userData.get("nickname");
            String image = (String) userData.get("picture");
            String auth0Id = (String) userData.get("sub");
            Enum_Rol rol = (Enum_Rol) userData.get("rol");

            UserClient newUserClient = new UserClient(email = email, image = image, auth0Id = auth0Id, rol=rol);

            return createUserClient(newUserClient);
        }
        System.out.println(userClient);
        return userClient;
    }

}
