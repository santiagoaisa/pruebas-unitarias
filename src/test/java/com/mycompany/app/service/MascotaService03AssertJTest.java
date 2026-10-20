package com.mycompany.app.service;


import com.mycompany.app.model.Mascota;
import com.mycompany.app.model.Propietario;
import com.mycompany.app.repository.MascotaRepository;
import com.mycompany.app.repository.MascotaRepositoryImpl;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class MascotaService03AssertJTest {

    MascotaRepository repository=new MascotaRepositoryImpl();
    ExternalService externalService=new ExternalServiceImpl();
    MascotaService service =new MascotaService(repository,externalService);



    @Test
    void testHamcrest(){
        //Arrange(Preparar)
        Propietario propietario=new Propietario("Jhon","Lima","987654321");

        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);

        //Act (Actuar)
        Mascota registrada= service.registrarMascota(mascota);

        //Assert(Afirmar) : AssertJ
        assertThat(registrada).isNotNull();
        assertThat(registrada.getId()).isNotNull();
        assertThat(registrada.getNombre()).isEqualTo("Garfield");
        assertThat(registrada.getNombre()).isInstanceOf(String.class);

        //Text Assertions
        assertThat(registrada.getNombre()).startsWithIgnoringCase("gar");
        assertThat(registrada.getNombre()).contains("fiel");
        assertThat(registrada.getNombre()).isNotEmpty();
        assertThat(registrada.getNombre()).hasSize(8);

        //Number Assertions
        assertThat(registrada.getId()).isLessThanOrEqualTo(4);


    }


}


