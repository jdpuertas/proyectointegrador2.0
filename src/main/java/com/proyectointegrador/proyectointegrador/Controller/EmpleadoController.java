package com.proyectointegrador.proyectointegrador.Controller;

import com.proyectointegrador.proyectointegrador.Enums.Enum_Rol;
import com.proyectointegrador.proyectointegrador.Model.Empleado;
import com.proyectointegrador.proyectointegrador.Model.Empresa;
import com.proyectointegrador.proyectointegrador.Model.UserClient;
import com.proyectointegrador.proyectointegrador.Services.EmpleadoService;
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

import java.util.List;

@Controller
public class EmpleadoController {
    @Autowired
    EmpleadoService empleadoService;

    @Autowired
    EmpresaService empresaService;
    @Autowired
    UserClientService userClientService;


    @GetMapping("/empleado")
    private String verEmpleado(Model model) {
        model.addAttribute("empleado", empleadoService.verEmpleado());
        return "empleado";
    }

    @GetMapping("/crear-empleado")
    private String crearEmpleado(Empleado empleado, @AuthenticationPrincipal OidcUser principal, Model model) {
        UserClient userClient = this.userClientService.getOrCreateUser(principal.getClaims());
        if (userClient.getRol() != Enum_Rol.valueOf("VISITANTE")) {
            List<Empresa> listaEmpresa = empresaService.verEmpresa();
            model.addAttribute("empresaList", listaEmpresa);
            return "crear-empleado";
        }

        return "redirect:/empleado";
    }

    @PostMapping("/empleado")
    private String crear(Empleado empleado, @AuthenticationPrincipal OidcUser principal) {
        UserClient userClient = this.userClientService.getOrCreateUser(principal.getClaims());
        if (userClient.getRol() != Enum_Rol.valueOf("VISITANTE"))
            empleadoService.crearEmpleado(empleado);
        return "redirect:/empleado";
    }

    @GetMapping("empleado/eliminar/{id}")
    private String eliminarEmpleado(@PathVariable("id") Long id, @AuthenticationPrincipal OidcUser principal) {
        UserClient userClient = this.userClientService.getOrCreateUser(principal.getClaims());
        if (userClient.getRol() == Enum_Rol.valueOf("ADMIN"))
            empleadoService.eliminarEmpleado(id);
        return "redirect:/empleado";
    }


    @GetMapping("/empleado/editar/{id}")
    private String verEmpleadoPorId(@PathVariable("id") Long id, Model model, @AuthenticationPrincipal OidcUser principal) {
        UserClient userClient = this.userClientService.getOrCreateUser(principal.getClaims());
        if (userClient.getRol() == Enum_Rol.valueOf("ADMIN")) {
            List<Empresa> listaEmpresa = empresaService.verEmpresa();
            model.addAttribute("empresaList", listaEmpresa);
            Empleado empleado = empleadoService.verEmpleadoPorId(id);
            model.addAttribute("empleado", empleado);
            return "actualizar-empleado";
        }
        return "redirect:/empleado";
    }

    @PostMapping("/empleado/actualizar/{id}")
    private String editarEmpleado(@PathVariable("id") Long id, Empleado empleado, @AuthenticationPrincipal OidcUser principal, Model model) {
        UserClient userClient = this.userClientService.getOrCreateUser(principal.getClaims());
        if (userClient.getRol() == Enum_Rol.valueOf("ADMIN")) {
            List<Empresa> listaEmpresa = empresaService.verEmpresa();
            model.addAttribute("empresaList", listaEmpresa);
            empleadoService.editarEmpleado(empleado);
            return "redirect:/empleado";
        }
        return "redirect:/empleado";
    }

}
