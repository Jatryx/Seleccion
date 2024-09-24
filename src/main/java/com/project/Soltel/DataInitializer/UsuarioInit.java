package com.project.Soltel.DataInitializer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.Soltel.models.UsuarioModel;
import com.project.Soltel.repositories.IUsuarioRepository;

@Component
public class UsuarioInit implements CommandLineRunner {

    @Autowired
    private IUsuarioRepository usuarioRepository;

    @Override
    public void run(String... args) throws Exception {
        // Insertar usuarios si no existen
        if (usuarioRepository.count() == 0) {
            List<UsuarioModel> usuarios = Arrays.asList(
                new UsuarioModel("AMS","aaa" ,true),
                new UsuarioModel("ARB","aaa" ,true),
                new UsuarioModel("BAC","aaa" ,true),
                new UsuarioModel("RPP","aaa" ,true),
                new UsuarioModel("SMP","aaa" ,true),
                new UsuarioModel("FPG", "aaa",true),
                new UsuarioModel("PGN", "aaa",true),
                new UsuarioModel("MRGI","aaa" ,true),
                new UsuarioModel("IACA","aaa" ,true)
            );
            usuarioRepository.saveAll(usuarios);
        }
    }
}
