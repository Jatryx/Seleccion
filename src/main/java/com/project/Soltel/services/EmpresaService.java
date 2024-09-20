package com.project.Soltel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Soltel.models.EmpresaModel;
import com.project.Soltel.repositories.IEmpresaRepository;

@Service
public class EmpresaService {

    @Autowired
    private IEmpresaRepository empresaRepository;

    public List<EmpresaModel> consultarTodasEmpresas() {
        return empresaRepository.findAll();
    }

    public EmpresaModel guardarEmpresa(EmpresaModel empresa){
        return empresaRepository.save(empresa);
    }

    public EmpresaModel actualizarEmpresa(EmpresaModel empresa){
        return empresaRepository.save(empresa);
    }

    public Optional<EmpresaModel> consultarNombreEmpresa(String nombre){
        return empresaRepository.findEmpresaByNombre(nombre);
    }
    
}
