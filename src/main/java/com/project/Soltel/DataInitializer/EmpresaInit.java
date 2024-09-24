package com.project.Soltel.DataInitializer;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.project.Soltel.models.EmpresaModel;
import com.project.Soltel.repositories.IEmpresaRepository;

@Component
public class EmpresaInit implements CommandLineRunner {

    @Autowired
    private IEmpresaRepository empresaModelRepository;

    @Override
    public void run(String... args) throws Exception {
        // Insertar empresas si no existen
        if (empresaModelRepository.count() == 0) {
            List<EmpresaModel> empresas = Arrays.asList(
                new EmpresaModel("CLIENTES", true),
                new EmpresaModel("ACCENTURE", true),
                new EmpresaModel("AGENCIA CALIDAD SANITARIA", true),
                new EmpresaModel("ATLANTICA", true),
                new EmpresaModel("ATOS", true),
                new EmpresaModel("BABEL", true),
                new EmpresaModel("BIDAFARMA", true),
                new EmpresaModel("CAPGEMINI", true),
                new EmpresaModel("DINAMIC AREA", true),
                new EmpresaModel("DXC TECHNOLOGY", true),
                new EmpresaModel("ELASTIC.CO", true),
                new EmpresaModel("ERNST&YOUNG", true),
                new EmpresaModel("FDSA", true),
                new EmpresaModel("GESEIN", true),
                new EmpresaModel("INDRA", true),
                new EmpresaModel("INFORTEC", true),
                new EmpresaModel("INPRO", true),
                new EmpresaModel("ITURRI", true),
                new EmpresaModel("PROCESIA", true),
                new EmpresaModel("SEMS", true),
                new EmpresaModel("SOFTTEK", true),
                new EmpresaModel("SOLTEL", true),
                new EmpresaModel("SOLTEL (DIS)", true),
                new EmpresaModel("T-SYSTEMS", true),
                new EmpresaModel("TIER1", true),
                new EmpresaModel("VEIASA", true),
                new EmpresaModel("WEXLOG", true),
                new EmpresaModel("XMARTCS", true)
            );
            empresaModelRepository.saveAll(empresas);
        }
    }
}

