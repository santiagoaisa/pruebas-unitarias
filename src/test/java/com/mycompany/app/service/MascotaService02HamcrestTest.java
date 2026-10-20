package com.mycompany.app.service;


import com.mycompany.app.model.Mascota;
import com.mycompany.app.model.Propietario;
import com.mycompany.app.repository.MascotaRepository;
import com.mycompany.app.repository.MascotaRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class MascotaService02HamcrestTest {

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

        //Assert(Afirmar) : Hamcrest - test Matchers
        assertThat(registrada.getNombre(),is("Garfield"));
        assertThat(registrada.getNombre(),containsString("fiel"));
        assertThat(registrada.getNombre(),startsWith("Ga"));
        assertThat(registrada.getNombre(),endsWith("ld"));
        assertThat(registrada.getNombre(),equalToIgnoringCase("garfield"));
        assertThat(registrada.getPropietario().getNombre(),not(blankString()));

        //Assert(Afirmar) : Hamcrest - number Matchers
        assertThat(registrada.getId(),greaterThan(3));
        assertThat(registrada.getId(),greaterThanOrEqualTo(4));
        assertThat(registrada.getId(),lessThan(10));
        assertThat(registrada.getId(),lessThanOrEqualTo(4));

        //Assert(Afirmar) : Hamcrest - Core Matchers
        assertThat(registrada.getNombre(), allOf(startsWith("Ga") ,containsString("fiel"),endsWith("ld")));

        //Assert(Afirmar) : Hamcrest - Bean Matchers
        assertThat(registrada, hasProperty("nombre",is("Garfield")) );
        assertThat(registrada, hasProperty("id",is(4)) );
        assertThat(registrada, hasProperty("propietario",is(notNullValue())) );


    }


}
