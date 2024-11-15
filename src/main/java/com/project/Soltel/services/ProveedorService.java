package com.project.Soltel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Soltel.models.ProveedorModel;
import com.project.Soltel.repositories.IProveedoresRepository;

@Service
public class ProveedorService {

    @Autowired
    private IProveedoresRepository proveedorRepository;

    // Método para consultar todos los proveedores
    public List<ProveedorModel> consultarTodosProveedores() {
        return proveedorRepository.findAll();
    }

    // Método para guardar un nuevo proveedor
    public ProveedorModel guardarProveedor(ProveedorModel proveedor) {
        return proveedorRepository.save(proveedor);
    }

    // Método para actualizar un proveedor existente
    public ProveedorModel actualizarProveedor(ProveedorModel proveedor) {
        return proveedorRepository.save(proveedor);
    }

    // Método para consultar un proveedor por su nombre
    public Optional<ProveedorModel> consultarNombreProveedor(String nombre) {
        return proveedorRepository.findProveedorByNombre(nombre);
    }
}
