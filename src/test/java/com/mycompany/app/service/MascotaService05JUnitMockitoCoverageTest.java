package com.mycompany.app.service;


import com.mycompany.app.model.Mascota;
import com.mycompany.app.model.Propietario;
import com.mycompany.app.repository.MascotaRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class MascotaService05JUnitMockitoCoverageTest {

    @Mock
    ExternalService externalService;
    @Mock
    MascotaRepository repository;
    @InjectMocks
    MascotaService service;



    @Test
    void testRegistarMascotaCorrectamente(){
        //Arrange(Preparar)
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


    @DisplayName("Regla 01 - nombre null y vacio")
    @ParameterizedTest
    @NullAndEmptySource
    void testRegistarMascotaConNombreInvalido(String nombre){
        //Arrange(Preparar)
        Propietario propietario=new Propietario("Jhon","Lima","987654321");

        Mascota mascota = new Mascota();
        mascota.setPropietario(propietario);
        mascota.setNombre(nombre);
        //Act (Actuar)
        Exception exception =  assertThrows(IllegalArgumentException.class,()-> service.registrarMascota(mascota));

        //Assert(Afirmar) : JUnit
        assertEquals("El nombre de la mascota no puede estar vacío.", exception.getMessage());

    }

    @DisplayName("Regla 02 - propietario null")
    @Test
    void testRegistarMascotaSinPropietario(){
        //Arrange(Preparar)
        Propietario propietario=new Propietario("Jhon","Lima","987654321");

        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        //Act (Actuar)
        Exception exception =  assertThrows(IllegalArgumentException.class,()-> service.registrarMascota(mascota));

        //Assert(Afirmar) : JUnit
        assertEquals("La mascota debe tener un propietario.", exception.getMessage());

    }

    @DisplayName("Regla 03 - telefono null y vacio")
    @ParameterizedTest
    @NullAndEmptySource
    void testRegistarMascotaConPropietarioTelefonoInvalido(String telefono){
        //Arrange(Preparar)
        Propietario propietario=new  Propietario("Jhon","Lima",telefono);

        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);
        //Act (Actuar)
        Exception exception =  assertThrows(IllegalArgumentException.class,()-> service.registrarMascota(mascota));

        //Assert(Afirmar) : JUnit
        assertEquals("El propietario debe tener un teléfono registrado.", exception.getMessage());

    }

    @DisplayName("Regla 04 - Validar sin vacunas al dia")
    @Test
    void testRegistarMascotaSinVacunas(){
        //Arrange(Preparar)
        Propietario propietario=new Propietario("Jhon","Lima","987654321");
        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);
        Mockito.when(externalService.validarVacunas(Mockito.any(Mascota.class))).thenReturn(false);

        //Act (Actuar)
        Exception exception =  assertThrows(IllegalStateException.class,()-> service.registrarMascota(mascota));

        //Assert(Afirmar) : JUnit
        assertEquals("La mascota no tiene todas las vacunas al día.", exception.getMessage());

    }

    @DisplayName("Regla 05 - Validar registro en el municipio")
    @Test
    void testRegistarMascotaSinRegistroMunicipal(){
        //Arrange(Preparar)
        Propietario propietario=new Propietario("Jhon","Lima","987654321");
        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);
        Mockito.when(externalService.validarVacunas(Mockito.any(Mascota.class))).thenReturn(true);
        Mockito.when(externalService.verificarRegistroMunicipal(Mockito.any(Mascota.class))).thenReturn(false);

        //Act (Actuar)
        Exception exception =  assertThrows(IllegalStateException.class,()-> service.registrarMascota(mascota));

        //Assert(Afirmar) : JUnit
        assertEquals("La mascota no está registrada en el municipio.", exception.getMessage());

    }

    @DisplayName("Regla 06 - Validar mascota ya registrada")
    @Test
    void testRegistarMascotaRegistrada(){
        //Arrange(Preparar)
        Propietario propietario=new Propietario("Jhon","Lima","987654321");
        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setPropietario(propietario);

        Mockito.when(externalService.validarVacunas(Mockito.any(Mascota.class))).thenReturn(true);
        Mockito.when(externalService.verificarRegistroMunicipal(Mockito.any(Mascota.class))).thenReturn(true);
        Mockito.when(repository.findByName(Mockito.anyString())).thenReturn(Optional.of(mascota));

        //Act (Actuar)
        Exception exception =  assertThrows(IllegalStateException.class,()-> service.registrarMascota(mascota));

        //Assert(Afirmar) : JUnit
        assertEquals("Esta mascota ya está registrada.", exception.getMessage());

    }

    @DisplayName("eliminarMascotaPorId id si existe")
    @Test
    void testEliminarMascotaPorIdExiste(){
        //Arrange(Preparar)
        Integer id=1;
        Mascota mascota = new Mascota();
        mascota.setId(id);

        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.of(mascota));

        //Act (Actuar)
        service.eliminarMascotaPorId(id);

        //Assert(Afirmar) : JUnit
        // 1. Verificamos que se busco la mascota antes de borrarla
        Mockito.verify(repository).findById(id);
        // 2. Verificamos que efectivamente se llamo al metodo de borrado del repositorio
        Mockito.verify(repository).deleteById(id);

    }

    @DisplayName("eliminarMascotaPorId id no existe")
    @Test
    void testEliminarMascotaPorIdNoExiste(){
        //Arrange(Preparar)
        Integer id=9;
        Mockito.when(repository.findById(Mockito.anyInt())).thenReturn(Optional.empty());

        //Act (Actuar)
        Exception exception =  assertThrows(IllegalArgumentException.class,()-> service.eliminarMascotaPorId(id));

        //Assert(Afirmar) : JUnit
        assertEquals("No se puede eliminar: No se encontró mascota con el ID proporcionado.", exception.getMessage());
        Mockito.verify(repository, Mockito.never()).deleteById(Mockito.anyInt());
    }

    @DisplayName("buscarMascotaPorId id si existe")
    @Test
    void testBuscarMascotaPorIdExiste(){
        //Arrange(Preparar)
        Integer id=1;
        Mascota mascota = new Mascota();
        mascota.setNombre("Garfield");
        mascota.setId(id);

        Mockito.when(repository.findById(id)).thenReturn(Optional.of(mascota));

        //Act (Actuar)
        Optional<Mascota> resultado =service.buscarMascotaPorId(id);

        //Assert(Afirmar)
        // 1. Verificamos que el Optional contiene datos
        assertTrue(resultado.isPresent(), "La mascota debería ser encontrada");

        // 2. Verificamos que los datos del objeto retornado sean los esperados
        assertEquals("Garfield", resultado.get().getNombre());
        assertEquals(id, resultado.get().getId());

        // 3. Verificamos la interacción con el repositorio
        Mockito.verify(repository).findById(id);

    }

    @DisplayName("buscarMascotaPorId cuando id no existe")
    @Test
    void testBuscarMascotaPorIdNoExiste() {
        // Arrange (Preparar)
        Integer id = 99;

        // Simulamos que el repositorio no encuentra nada para ese ID
        Mockito.when(repository.findById(id)).thenReturn(Optional.empty());

        // Act (Actuar)
        Optional<Mascota> resultado = service.buscarMascotaPorId(id);

        // Assert (Afirmar)
        // 1. Verificamos que el Optional devuelto por el servicio esté vacío
        assertTrue(resultado.isEmpty(), "El resultado debería ser un Optional vacío");
        assertFalse(resultado.isPresent(), "No debería haber ninguna mascota presente");

        // 2. Verificamos que se consultó al repositorio exactamente 1 vez
        Mockito.verify(repository).findById(id);
    }


}
