package com.proyectointegrador.proyectointegrador.Services;

import com.proyectointegrador.proyectointegrador.Model.Empresa;
import com.proyectointegrador.proyectointegrador.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmpresaService {
    @Autowired
    EmpresaRepository empresaRepository;

    ArrayList<Empresa> empresas;
    public EmpresaService(ArrayList<Empresa> empresas){
        this.empresas = empresas;
    }
    public void crearEmpresa(Empresa empresa){

        empresaRepository.save(empresa);
    }

    public void editarEmpresa(Empresa empresa){
        empresaRepository.save(empresa);
    }


    public List<Empresa> verEmpresa(){
        List<Empresa> empresas = new ArrayList<Empresa>();
        empresas.addAll(empresaRepository.findAll());
        return empresas;
    }

    public Empresa verEmpresaPorId(Long id){

        return empresaRepository.findById(id).get();

    }

    public void eliminarEmpresa(Long id){
        empresaRepository.deleteById(id);
    }
}
