package com.project.Soltel.DataInitializer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.Soltel.models.PuestoModel;
import com.project.Soltel.repositories.IPuestoRepository;

@Component
public class PuestoInit implements CommandLineRunner {

    @Autowired
    private IPuestoRepository puestoRepository;

    @Override
    public void run(String... args) throws Exception {
        // Insertar puestos si no existen
        if (puestoRepository.count() == 0) {
            List<PuestoModel> puestos = Arrays.asList(
                new PuestoModel("FULLSTACK", true),
                new PuestoModel("JEFE DE PROYECTO", true),
                new PuestoModel("DESARROLLADOR BACKEND", true),
                new PuestoModel("DESARROLLADOR FRONTEND", true),
                new PuestoModel("ANALISTA PROGRAMADOR", true),
                new PuestoModel("ADMINISTRADOR DE BASE DE DATOS", true),
                new PuestoModel("INGENIERO DE SOFTWARE", true),
                new PuestoModel("DEVOPS", true),
                new PuestoModel("TESTER", true),
                new PuestoModel("ARQUITECTO DE SOFTWARE", true)
            );
            puestoRepository.saveAll(puestos);
        }
    }
}
