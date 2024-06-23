package com.tcna.primeraweb.repository;

import com.tcna.primeraweb.entities.Animal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * JpaRepository: Siempre tenemos que pasarle el tipo de entidad y el tipo de ID de la entidad
 * JpaRepository nos brinda muchos metodos para realizar operaciones CRUD con los datos
 */
@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
