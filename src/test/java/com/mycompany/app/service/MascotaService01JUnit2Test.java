package com.mycompany.app.service;


import com.mycompany.app.model.Mascota;
import com.mycompany.app.model.Propietario;
import com.mycompany.app.repository.MascotaRepository;
import com.mycompany.app.repository.MascotaRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MascotaService01JUnit2Test {

    MascotaRepository repository=new MascotaRepositoryImpl();
    ExternalService externalService=new ExternalServiceImpl();
    MascotaService service =new MascotaService(repository,externalService);

    @Test
    void testRegistarMascotaCorrectamente(){
        //Arrange(Preparar)
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

    @Test
    @DisplayName("Test buscar mascota por ID existente")
    void testBuscarMascotaPorIdExiste(){
        //Arrange (Preparar)
        int id=1;
        //Act(Actuar)
        Optional<Mascota> mascota= service.buscarMascotaPorId(id);
        //Assert(Afirmar)
        assertEquals("Milo",mascota.get().getNombre());
    }

    @Test
    @DisplayName("Test buscar mascota por ID no existente")
    void testBuscarMascotaPorIdNoExiste(){
        //Arrange (Preparar)
        int id=1000;
        //Act(Actuar)
        Optional<Mascota> mascota= service.buscarMascotaPorId(id);
        //Assert(Afirmar)
        assertFalse(mascota.isPresent(),"La mascota no debe estar presente");
    }


    @Test
    @DisplayName("Test eliminar mascota por ID no existente")
    void testEliminarMascotaPorIdNoExiste(){
        //Arrange (Preparar)
        int idNoExiste=100;
        //Act(Actuar)


        Exception exception= assertThrows(IllegalArgumentException.class,()-> service.eliminarMascotaPorId(idNoExiste));

        //Assert(Afirmar)
        assertEquals("No se puede eliminar: No se encontró mascota con el ID proporcionado.",exception.getMessage() );

    }

}
