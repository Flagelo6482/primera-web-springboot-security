package com.tcna.primeraweb.controller;

import com.tcna.primeraweb.entities.Animal;
import com.tcna.primeraweb.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/*
* Agregamos el estereotipo "CONTROLLER"
* Para acceder al CONTROLLER tenemos que escribir "/animales"
* */
@Controller
@RequestMapping("/animales")
public class AnimalController {

    @Autowired
    private AnimalService animalService;


    /**
     * En esta ruta mostraremos el html "listar", mostrando una lista de datos que obtuvimos de la base de datos
     * Para mostrar algo en un HTML tenemos que pasarlo como MODELO
     * Model model: Se utiliza para transferir datos y objetos, desde el controlador a la vista
     * GetMapping: Notacion del metodo HTTP
     * @param model
     * @return
     */
    @GetMapping
    public String listarPersonas(Model model){
        List<Animal> animales = animalService.obtenerTodas();
        /**
         * animalesLista: es la llave
         * animales: es el valor de la llave, osea que contiene todos los datos
         */
        model.addAttribute("animalesLista", animales);
        return "listar";
    }



    /**
     * Esta ruta nos mostrara el html "crear", para rellenar un formulario y guardar los datos del "animal" nuevo
     * Vamos a pasar al formulario el objeto "animal", con "model.addAttribute"
     * @return
     */
    @GetMapping("/nueva")
    public String mostrarFormulario(Model model){
        model.addAttribute("animal", new Animal());
        model.addAttribute("accion", "/animales/nueva");
        return "formulario";
    }

    /**
     * Cuando seleccionemos el boton de "GUARDAR" en el html "formulario" entraremos a este POST, que se
     * encarga de guardar el "animal" en la base de datos, luego nos redirigimos a la ruta "personas" cop
     * "redirect:/animales"
     * @ModelAttribute: Recibe un objeto(animal), obtiene cada dato del objeto de cada campo del formlario, con el
     * "th:field"
     * @param animal
     * @return
     */
    @PostMapping("/nueva")
    public String guardarNuevoAnimal(@ModelAttribute Animal animal){
        animalService.crearAnimal(animal);
        return "redirect:/animales";
    }



    /**
     * Para mostrar el formulario para editar un objeto "animal" tenemos que escribir la URL de "editar" con el "id"
     * y retornar a "formulario" pero no pasaremos un nuevo objeto, si no uno que ya EXISTE para editarlo con
     * "ModelAttribute"
     * PathVariable: Es una notacion para pasar al URL variables "id" y con esto obtener una entidad en particular
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarAnimal(@PathVariable Long id, Model model,@ModelAttribute Animal animal){
        model.addAttribute("animal", animal);
        model.addAttribute("accion", "/animales/editar/"+id);
        return "formulario";
    }

    @PostMapping("/editar/{id}")
    public String actualizarAnimal(@PathVariable Long id,@ModelAttribute Animal animal){
        animalService.actualizarAnimal(id, animal);
        return "redirect:/animales";
    }



    @GetMapping("/eliminar/{id}")
    public String eliminarAnimal(@PathVariable Long id){
        animalService.eliminarAnimal(id);
        return "redirect:/animales";
    }
}
