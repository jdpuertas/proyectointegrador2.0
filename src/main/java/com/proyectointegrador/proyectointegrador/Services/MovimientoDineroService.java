package com.proyectointegrador.proyectointegrador.Services;

import com.proyectointegrador.proyectointegrador.Model.MovimientoDinero;
import com.proyectointegrador.proyectointegrador.Repository.MovimientoDineroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovimientoDineroService {
    @Autowired
    MovimientoDineroRepository movimientoDineroRepository;

    public List<MovimientoDinero> getAllMovimientoDinero() {
        List<MovimientoDinero> listaMovimientoDinero = new ArrayList<>();
        movimientoDineroRepository.findAll().forEach(movimientoDinero -> listaMovimientoDinero.add(movimientoDinero));
        return listaMovimientoDinero;
    }

    public MovimientoDinero getMovimientoDinero(Integer id) {
        return movimientoDineroRepository.findById(id).get();
    }

    public boolean actualizarMovimientoDinero(MovimientoDinero movDinero) {
        MovimientoDinero movimientoDinero = movimientoDineroRepository.save(movDinero);
        return movimientoDineroRepository.findById(movimientoDinero.getId()) != null;
    }

    public boolean eliminarMovimientoDinero(Integer id) {
        movimientoDineroRepository.deleteById(id);
        return movimientoDineroRepository.findById(id) != null;
    }

    public Long obtenerSumaMontos() {
        return movimientoDineroRepository.SumarMonto();
    }

    public Long MontosPorEmpleado(Integer id) {
        return movimientoDineroRepository.MontosPorEmpleado(id);
    }

    public Long MontosPorEmpresa(Integer id) {
        return movimientoDineroRepository.MontosPorEmpresa(id);
    }

    public ArrayList<MovimientoDinero> obtenerPorEmpleado(Integer id) {
        return movimientoDineroRepository.findByEmpleado(id);
    }

    public ArrayList<MovimientoDinero> obtenerPorEmpresa(Integer id) {
        return movimientoDineroRepository.findByEmpresa(id);
    }
}
