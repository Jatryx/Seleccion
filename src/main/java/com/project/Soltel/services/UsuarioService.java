package com.project.Soltel.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.Soltel.models.UsuarioModel;
import com.project.Soltel.repositories.IUsuarioRepository;

@Service
public class UsuarioService {

    @Autowired
    private IUsuarioRepository iusuarioRepository;

    public List<UsuarioModel> consultarTodosUsuarios() {
        return iusuarioRepository.findAll();
    }

    public List<UsuarioModel> consultarUsuarioActivos(){
        return iusuarioRepository.findUsuarioActivo();
    }

    public List<UsuarioModel> consultarUsuarioInactivos(){
        return iusuarioRepository.findUsuarioInactivo();
    }

    public Optional<UsuarioModel> consultarUsuarioCodope(String codope){
        return iusuarioRepository.findUsuarioByCodope(codope);
    }

    public UsuarioModel guardarUsuario(UsuarioModel usuario){
        return iusuarioRepository.save(usuario);
    }

    public UsuarioModel actualizarUsuario(UsuarioModel usuario){
        return iusuarioRepository.save(usuario);
    }
    
}
