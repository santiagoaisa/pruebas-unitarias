package com.mycompany.app.service;


import com.mycompany.app.model.Mascota;
import com.mycompany.app.model.Propietario;
import com.mycompany.app.repository.MascotaRepository;
import com.mycompany.app.repository.MascotaRepositoryImpl;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MascotaService01JUnit1Test {

    @Test
    void testRegistrarMascota(){
        assertTrue(true);
    }

    @Test
    void testRegistarMascotaCorrectamente(){
        //Arrange(Preparar)
        MascotaRepository repository=new MascotaRepositoryImpl();
        ExternalService externalService=new ExternalServiceImpl();

        MascotaService service =new MascotaService(repository,externalService);
        Propietario propietario=new Propietario("Jhon","Lima","987654321");


        Mascota mascota = new Mascota();
        mascota.setNombre("Garflied");
        mascota.setPropietario(propietario);

        //Act (Actuar)
        Mascota registrada= service.registrarMascota(mascota);

        //Assert(Afirmar) : JUnit
        assertNotNull(registrada);
        assertEquals("Garflied",registrada.getNombre());
        assertNotNull(registrada.getPropietario());
        assertTrue( registrada.getId()>0 );
        assertDoesNotThrow(()-> registrada);

    }
}
