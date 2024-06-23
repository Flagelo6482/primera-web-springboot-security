package com.tcna.primeraweb.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//Toda clase que se quiera mapear como una tabla en una base de datos tiene que tener "Entity"
/**/

@Entity
@Table(name = "tbl_animales")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String sexo;
}
