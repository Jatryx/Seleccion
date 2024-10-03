package com.project.Soltel.DataInitializer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.Soltel.models.EstadoModel;
import com.project.Soltel.repositories.IEstadoRepository;

@Component
public class EstadoInit implements CommandLineRunner {

    @Autowired
    private IEstadoRepository estadoModelRepository;

    @Override
    public void run(String... args) throws Exception {
        // Insertar estados si no existen
        if (estadoModelRepository.count() == 0) {
            List<EstadoModel> estados = Arrays.asList(
                new EstadoModel("AUTODESCARTADO", true),
                new EstadoModel("ENTREVISTADO", true),
                new EstadoModel("PROPUESTO", true),
                new EstadoModel("RECHAZADO", true),
                new EstadoModel("SOLICITUD ENTREVISTA", true),
                new EstadoModel("SOLICITUD INCORPORACIÓN", true),
                new EstadoModel("INCORPORADO", true),
                new EstadoModel("PETICIÓN CANCELADA", true)            
            );
            estadoModelRepository.saveAll(estados);
        }
    }
}
