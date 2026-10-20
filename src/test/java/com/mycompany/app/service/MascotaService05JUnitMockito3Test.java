package com.mycompany.app.service;


import com.mycompany.app.model.Mascota;
import com.mycompany.app.model.Propietario;
import com.mycompany.app.repository.MascotaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MascotaService05JUnitMockito3Test {

    @Mock
    ExternalService externalService;
    @Mock
    MascotaRepository repository;


    @Test
    void testRegistarMascotaCorrectamente(){
        //Arrange(Preparar)
        MascotaService service =new MascotaService(repository,externalService);
        Propietario propietario=new Propietario("Jhon","Lima","987654321");

        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);

        Mockito.when(externalService.validarVacunas(Mockito.any(Mascota.class))).thenReturn(true);
        Mockito.when(externalService.verificarRegistroMunicipal(Mockito.any(Mascota.class))).thenReturn(true);
        Mockito.when(repository.findByName(Mockito.anyString())).thenReturn(Optional.empty());
        Mockito.when(repository.guardar(Mockito.any(Mascota.class))).thenReturn(mascota);


        //Act (Actuar)
        Mascota registrada= service.registrarMascota(mascota);

        //Assert(Afirmar) : JUnit
        assertNotNull(registrada);
        assertEquals("Garfield",registrada.getNombre());
        assertNotNull(registrada.getPropietario());
        assertDoesNotThrow(()-> registrada);

    }
}
