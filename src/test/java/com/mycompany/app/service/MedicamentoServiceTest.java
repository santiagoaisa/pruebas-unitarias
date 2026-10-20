package com.mycompany.app.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MedicamentoServiceTest {

    MedicamentoService medicamentoService = new MedicamentoService();

    @Test
    @DisplayName("Registrar medicamento correctamente")
    @Tag("one")
    @Order(2)
    void testResgistrarMedicamento(){
        String resultado = medicamentoService.registrarMedicamento("Antibiótico", 10);
        assertEquals("Medicamento registrado", resultado);
    }

    @Test
    @Disabled
    void testResgistrarMedicamento2(){
    }


    @Test
    @Tag("two")
    @Order(1)
    void testResgistrarMedicamentoVacio(){
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            medicamentoService.registrarMedicamento("", 10);
        });
        assertEquals("Nombre del medicamento no puede ser vacio", exception.getMessage());
    }

    @RepeatedTest(5)
    void testResgistrarMedicamentoNVeces(){
        String resultado = medicamentoService.registrarMedicamento("Antibiótico", 10);
        assertEquals("Medicamento registrado", resultado);
    }

    @ParameterizedTest
    @ValueSource(strings = {"Vacuna", "Vitamina"})
    void testResgistrarMedicamentoConParametrosDeNombres(String nombre){
        String resultado = medicamentoService.registrarMedicamento(nombre, 10);
        assertEquals("Medicamento registrado", resultado);
    }

    @ParameterizedTest
    @ValueSource(ints = {10,20,30})
    void testResgistrarMedicamentoConParametrosDeCantidad(Integer cantidad){
        String resultado = medicamentoService.registrarMedicamento("Suplemento", cantidad);
        assertEquals("Medicamento registrado", resultado);
    }

    @ParameterizedTest
    @CsvSource({ "101, 'Antibiótico'", "102, 'Analgésico'", "103, 'Antiinflamatorio'", "104, 'Vacuna'", "105, 'Desparasitante'" })
    void testConvertCodeToMedicamento(int codigo,String nombre){
        String medicamento = medicamentoService.convertCodeToMedicamento(codigo);
        assertEquals(nombre, medicamento);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/medicamentos.csv",numLinesToSkip = 1,delimiterString = "|")
    void testConvertCodeToMedicamentoFromResources(int codigo,String nombre){
        String medicamento = medicamentoService.convertCodeToMedicamento(codigo);
        assertEquals(nombre, medicamento);
    }

}
