package com.proyectointegrador.proyectointegrador.Controller;

import com.proyectointegrador.proyectointegrador.Model.Empleado;
import com.proyectointegrador.proyectointegrador.Model.MovimientoDinero;
import com.proyectointegrador.proyectointegrador.Repository.MovimientoDineroRepository;
import com.proyectointegrador.proyectointegrador.Services.EmpleadoService;
import com.proyectointegrador.proyectointegrador.Services.MovimientoDineroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MovimientoDineroController {
    @Autowired
    EmpleadoService empleadoServ;

    @Autowired
    MovimientoDineroService movDineroServ;

    @Autowired
    MovimientoDineroRepository movDineroRepo;


    @RequestMapping("/VerMovimiento")
    public String viewMovimientoDinero(@RequestParam(value = "pagina", required=false, defaultValue = "1") int NumeroPagina,
                                       @RequestParam(value = "medida", required=false, defaultValue = "5") int medida,
                                       Model model, @ModelAttribute("mensaje") String mensaje        ){

        Page<MovimientoDinero> paginaMovimientos = movDineroRepo.findAll(PageRequest.of(NumeroPagina,medida));
        model.addAttribute("movlist",paginaMovimientos.getContent());
        model.addAttribute("paginas",new int[paginaMovimientos.getTotalPages()]);
        model.addAttribute("paginaActual", NumeroPagina);
        model.addAttribute("mensaje",mensaje);
        Long sumaMonto = movDineroServ.obtenerSumaMontos();
        model.addAttribute("SumaMontos",sumaMonto);
        return "verMovimientoDinero";
    }

    @GetMapping("/AgregarMovimientoDinero")
    public String AddMovimientoDinero(Model model, @ModelAttribute("mensaje") String mensaje) {
        MovimientoDinero movDin = new MovimientoDinero();
        model.addAttribute("movDin", movDin);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleado = empleadoServ.verEmpleado();
        model.addAttribute("emplList", listaEmpleado);
        return "agregarMovimientoDinero";
    }

    @PostMapping("/GuardarMovimientoDinero")
    public String SaveMovimientoDinero(MovimientoDinero mov, RedirectAttributes redirectAttributes) {
        if (movDineroServ.actualizarMovimientoDinero(mov)) {
            redirectAttributes.addFlashAttribute("mensaje", "saveOK");
            return "redirect:/VerMovimiento";
        }
        redirectAttributes.addFlashAttribute("mensaje", "saveError");
        return "redirect:/AgregarMovimientoDinero";
    }

    @GetMapping("/EditarMovimientoDinero/{id}")
    public String editarMovimentoDinero(Model model, @PathVariable Integer id, @ModelAttribute("mensaje") String mensaje) {
        MovimientoDinero mov = movDineroServ.getMovimientoDinero(id);
        model.addAttribute("mov", mov);
        model.addAttribute("mensaje", mensaje);
        List<Empleado> listaEmpleados = empleadoServ.verEmpleado();
        model.addAttribute("emplelist", listaEmpleados);
        return "editarMovimientoDinero";
    }

    @PostMapping("/ActualizarMovimientoDinero")
    public String updateMovimientoDinero(@ModelAttribute("movimientoDinero") MovimientoDinero movimientoDinero, RedirectAttributes redirectAttributes) {
        if (movDineroServ.actualizarMovimientoDinero(movimientoDinero)) {
            redirectAttributes.addFlashAttribute("mensaje", "updateOK");
            return "redirect:/VerMovimiento";
        }
        redirectAttributes.addFlashAttribute("mensaje", "updateError");
        return "redirect:/EditarMovimientoDinero/" + movimientoDinero.getId();

    }

    @GetMapping("/EliminarMovimientoDinero/{id}")
    public String eliminarMovimientoDinero(@PathVariable Integer id, RedirectAttributes redirectAttributes) {
        if (movDineroServ.eliminarMovimientoDinero(id)) {
            redirectAttributes.addFlashAttribute("mensaje", "deleteOK");
            return "redirect:/VerMovimiento";
        }
        redirectAttributes.addFlashAttribute("mensaje", "deleteError");
        return "redirect:/VerMovimiento";
    }

    //controlador para el boton Ver empleados y  ver montos
    @GetMapping("/Empleado/{id}/MovimientoDinero")
    public String movimientosPorEmpleado(@PathVariable("id") Integer id, Model model) {
        List<MovimientoDinero> movlist = movDineroServ.obtenerPorEmpleado(id);
        model.addAttribute("movlist", movlist);
        Long sumaMonto = movDineroServ.MontosPorEmpleado(id);
        model.addAttribute("SumaMontos", sumaMonto);
        return "verMovimientoDinero";
    }

    @GetMapping("/Empresa/{id}/MovimientoDinero")
    public String verMovimientosPorEmpresa(@PathVariable("id") Integer id, Model model) {
        List<MovimientoDinero> listaMovimientos = movDineroServ.obtenerPorEmpresa(id);
        model.addAttribute("movlist", listaMovimientos);
        Long sumaMonto = movDineroServ.MontosPorEmpresa(id);
        model.addAttribute("SumaMontos", sumaMonto);
        return "verMovimientoDinero";
    }
}
