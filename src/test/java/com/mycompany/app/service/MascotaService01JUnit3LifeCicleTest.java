package com.mycompany.app.service;


import com.mycompany.app.model.Mascota;
import com.mycompany.app.model.Propietario;
import com.mycompany.app.repository.MascotaRepository;
import com.mycompany.app.repository.MascotaRepositoryImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class MascotaService01JUnit3LifeCicleTest {

    private int contadorDePrueba = 5;

    @Test
    @DisplayName("A")
    void testA(){
        contadorDePrueba++;
        System.out.println("A: "+ contadorDePrueba);
    }

    @Test
    @DisplayName("B")
    void testB(){
        contadorDePrueba++;
        System.out.println("B: "+ contadorDePrueba);
    }

    @Test
    @DisplayName("C")
    void testC(){
        contadorDePrueba++;
        System.out.println("C: "+ contadorDePrueba);
    }


}
