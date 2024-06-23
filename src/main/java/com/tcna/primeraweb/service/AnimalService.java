package com.tcna.primeraweb.service;

import com.tcna.primeraweb.entities.Animal;

import java.util.List;

public interface AnimalService {

    List<Animal> obtenerTodas();

    Animal obtenerPorId(Long id);

    Animal crearAnimal(Animal animal);

    Animal actualizarAnimal(Long id, Animal animal);

    void eliminarAnimal(Long id);

    long contarAnimales();

    //Estos metodos los implementamos a la clase de la carpeta "Impl"
}
