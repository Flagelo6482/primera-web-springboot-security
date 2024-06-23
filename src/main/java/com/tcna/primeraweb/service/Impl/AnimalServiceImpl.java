package com.tcna.primeraweb.service.Impl;

import com.tcna.primeraweb.entities.Animal;
import com.tcna.primeraweb.repository.AnimalRepository;
import com.tcna.primeraweb.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Autowired
    AnimalRepository animalRepository;

    @Override
    public List<Animal> obtenerTodas() {
        //Para obtener todos los datos con "findAll"
        return animalRepository.findAll();
    }

    @Override
    public Animal obtenerPorId(Long id) {
        //Para obtener un dato según el "id", en caso no la encuentre retornara "null"
        return animalRepository.findById(id).orElse(null);
    }

    @Override
    public Animal crearAnimal(Animal animal) {
        //Con "save" guardamos el dato "animal" pasado por parametro
        return animalRepository.save(animal);
    }

    @Override
    public Animal actualizarAnimal(Long id, Animal animal) {
        Animal animalBD = animalRepository.findById(id).orElse(null);

        if(animalBD != null){
            animalBD.setNombre((animal.getNombre()));
            animalBD.setSexo(animal.getSexo());
            return animalRepository.save(animalBD);
        }
        return null;
    }

    @Override
    public void eliminarAnimal(Long id) {
        animalRepository.deleteById(id);
    }

    @Override
    public long contarAnimales() {
        return animalRepository.count();
    }
}
