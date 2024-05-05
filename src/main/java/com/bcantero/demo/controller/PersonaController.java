package com.bcantero.demo.controller;

import com.bcantero.demo.entity.Persona;
import com.bcantero.demo.entity.PersonaLogin;
import com.bcantero.demo.service.PersonaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/persona")
@CrossOrigin(origins = "*")
public class PersonaController {

    @Autowired
    PersonaService personaService;

    @GetMapping("/personas")
    public ResponseEntity<List<Persona>> findAll() {
        try {
            List<Persona> personaList = personaService.findAll();
            if (!personaList.isEmpty()) {
                return ResponseEntity.ok(personaList);
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping("/registrar")
    public ResponseEntity<?> savePersona(@RequestBody Persona persona) {
        try {
            if (!(persona == null)) {
                return ResponseEntity.status(HttpStatus.OK).body(personaService.savePersona(persona));
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }
    }

    @PostMapping("/login")
    public ResponseEntity validarUsuario(@RequestBody PersonaLogin personaLogin) {

        try {
            if (!(personaLogin == null)) {
                String nombre = personaLogin.getNombre();
                String telefono = personaLogin.getTelefono();
                Persona persona = personaService.validarUsuario(nombre, telefono);
                if (!(persona == null)){
                    return ResponseEntity.status(HttpStatus.OK).body(persona);
                }else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
                }
            } else {
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(("{\"error\":\"" + e.getMessage() + "\"}"));
        }

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        Persona persona = personaService.findById(id);
        try {
            if (!(persona == null)){
                return ResponseEntity.ok(persona);
            }else {
                return ResponseEntity.notFound().build();
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> update(@PathVariable Long id, @RequestBody Persona personaUpdate){

        try {

            Persona persona = personaService.updateById(id, personaUpdate);

            if (persona != null) {
                return ResponseEntity.ok(persona);
            } else {
                return ResponseEntity.notFound().build();
            }

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public boolean delete (@PathVariable  Long id){
        try {
             return personaService.delete(id);
        }catch (Exception e){
            e.printStackTrace();

        }
        return false;
    }

    @GetMapping("/corre")
    public String llamarCorre(int valocidad){
        valocidad = 0;

        if (!(valocidad == 0)){
           return personaCorre(23) ;
        }

        return null;
    }

    public String personaCorre(){


        return "Esta corriendo";
    }

    public String personaCorre(int velocidad){

        return "Esta corriendo mas rapido";
    }

}
