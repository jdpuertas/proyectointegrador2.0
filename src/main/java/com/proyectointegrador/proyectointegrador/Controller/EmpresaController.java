package com.proyectointegrador.proyectointegrador.Controller;

import com.proyectointegrador.proyectointegrador.Enums.Enum_Rol;
import com.proyectointegrador.proyectointegrador.Model.Empresa;
import com.proyectointegrador.proyectointegrador.Model.UserClient;
import com.proyectointegrador.proyectointegrador.Services.EmpresaService;
import com.proyectointegrador.proyectointegrador.Services.UserClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;

    @Autowired
    UserClientService userClientService;



    @GetMapping("/empresa")
    private String verEmpresa(Model model) {
        model.addAttribute("empresas", empresaService.verEmpresa());
        return "empresa";
    }

    @GetMapping("/crear-empresa")
    private String crearEmpresa(Empresa empresa,@AuthenticationPrincipal OidcUser principal) {
        UserClient userClient = this.userClientService.getOrCreateUser(principal.getClaims());
        if(userClient.getRol()!= Enum_Rol.valueOf("VISITANTE")) {
            return "crear-empresa";
        }
        return "redirect:empresa";
    }

    @PostMapping("/empresa")
    private String crear(Empresa empresa,@AuthenticationPrincipal OidcUser principal) {
        UserClient userClient = this.userClientService.getOrCreateUser(principal.getClaims());
        if(userClient.getRol()!= Enum_Rol.valueOf("VISITANTE"))
            empresaService.crearEmpresa(empresa);
        return "redirect:/empresa";
    }

    @GetMapping("empresa/eliminar/{id}")
    private String eliminarEmpresa(@PathVariable("id") Long id, @AuthenticationPrincipal OidcUser principal) {
        UserClient userClient = this.userClientService.getOrCreateUser(principal.getClaims());
        if(userClient.getRol()== Enum_Rol.valueOf("ADMIN"))
            empresaService.eliminarEmpresa(id);
        return "redirect:/empresa";

    }


    @GetMapping("/empresa/editar/{id}")
    private String verEmpresaPorId(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal OidcUser principal) {
        UserClient userClient = this.userClientService.getOrCreateUser(principal.getClaims());
        if(userClient.getRol()== Enum_Rol.valueOf("ADMIN")) {
            Empresa empresa = empresaService.verEmpresaPorId(id);
            model.addAttribute("empresa", empresa);
            return "actualizar-empresa";
        }
        return "redirect:/empresa";
    }

    @PostMapping("/empresa/actualizar/{id}")
    private String editarEmpresa(@PathVariable("id") Long id, Empresa empresa, @AuthenticationPrincipal OidcUser principal) {
        UserClient userClient = this.userClientService.getOrCreateUser(principal.getClaims());
        if(userClient.getRol()== Enum_Rol.valueOf("ADMIN"))
            empresaService.editarEmpresa(empresa);
        return "redirect:/empresa";
    }
}
