package com.project.Soltel.DataInitializer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.Soltel.models.UbicacionModel;
import com.project.Soltel.repositories.IUbicacionRepository;

@Component
public class UbicacionInit implements CommandLineRunner{

	@Autowired
    private IUbicacionRepository ubicacionModelRepository;

    @Override
    public void run(String... args) throws Exception {
        // Insertar ubicaciones si no existen
        if (ubicacionModelRepository.count() == 0) {
            List<UbicacionModel> ubicaciones = Arrays.asList(
            		new UbicacionModel("Álava", true),
            		new UbicacionModel("Albacete", true),
            		new UbicacionModel("Alicante", true),
            		new UbicacionModel("Almería", true),
            		new UbicacionModel("Asturias", true),
            		new UbicacionModel("Ávila", true),
            	    new UbicacionModel("Badajoz", true),
            	    new UbicacionModel("Baleares", true),
            	    new UbicacionModel("Barcelona", true),
            	    new UbicacionModel("Burgos", true),
            	    new UbicacionModel("Cáceres", true),
            	    new UbicacionModel("Cádiz", true),
            	    new UbicacionModel("Cantabria", true),
            	    new UbicacionModel("Castellón", true),
            	    new UbicacionModel("Ceuta", true),
            	    new UbicacionModel("Ciudad Real", true),
            	    new UbicacionModel("Córdoba", true),
            	    new UbicacionModel("La Coruña", true),
            	    new UbicacionModel("Cuenca", true),
            	    new UbicacionModel("Gerona", true),
            	    new UbicacionModel("Granada", true),
            	    new UbicacionModel("Guadalajara", true),
            	    new UbicacionModel("Guipúzkoa", true),
            	    new UbicacionModel("Huelva", true),
            	    new UbicacionModel("Huesca", true),
            	    new UbicacionModel("Jaén", true),
            	    new UbicacionModel("La Rioja", true),
            	    new UbicacionModel("Las Palmas", true),
            	    new UbicacionModel("León", true),
            	    new UbicacionModel("Lérida", true),
            	    new UbicacionModel("Lugo", true),
            	    new UbicacionModel("Madrid", true),
            	    new UbicacionModel("Málaga", true),
            	    new UbicacionModel("Melilla", true),
            	    new UbicacionModel("Murcia", true),
            	    new UbicacionModel("Navarra", true),
            	    new UbicacionModel("Ourense", true),
            	    new UbicacionModel("Palencia", true),
            	    new UbicacionModel("Pontevedra", true),
            	    new UbicacionModel("Salamanca", true),
            	    new UbicacionModel("Santa Cruz de Tenerife", true),
            	    new UbicacionModel("Segovia", true),
            	    new UbicacionModel("Sevilla", true),
            	    new UbicacionModel("Soria", true),
            	    new UbicacionModel("Tarragona", true),
            	    new UbicacionModel("Teruel", true),
            	    new UbicacionModel("Toledo", true),
            	    new UbicacionModel("Valencia", true),
            	    new UbicacionModel("Valladolid", true),
            	    new UbicacionModel("Vizcaya", true),
            	    new UbicacionModel("Zamora", true),
            	    new UbicacionModel("Zaragoza", true)
                // Agrega más provincias según sea necesario
            );
            ubicacionModelRepository.saveAll(ubicaciones);
        }
    }

}
