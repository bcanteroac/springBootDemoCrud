package com.bcantero.demo.service;

import com.bcantero.demo.entity.Persona;
import com.bcantero.demo.repository.PersonaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class PersonaService {

    @Autowired
    PersonaRepository personaRepository;

    public List<Persona> findAll() throws Exception{
        try {
            List<Persona> personaList = personaRepository.findAll();
            return personaList;
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public Persona savePersona(Persona persona){
        return personaRepository.save(persona);
    }

    public Persona findById(Long id){
        return personaRepository.findById(id).orElse(null);
    }

    public Persona updateById(Long id, Persona personaUpdate){

        Optional<Persona> existingPersona = personaRepository.findById(id);

        if (existingPersona.isPresent()) {
            Persona persona = existingPersona.get();

            if (personaUpdate.getNombre()!= null) {
                persona.setNombre(personaUpdate.getNombre());
            }

            if (personaUpdate.getApellido()!= null) {
                persona.setApellido(personaUpdate.getApellido());
            }

            if (personaUpdate.getDireccion()!= null) {
                persona.setDireccion(personaUpdate.getDireccion());
            }

            if (personaUpdate.getTelefono()!= null) {
                persona.setTelefono(personaUpdate.getTelefono());
            }

            return personaRepository.save(persona);
        } else {
            return null;
        }
    }

    public boolean delete(Long id){
        Persona persona = personaRepository.findById(id).orElse(null);

        if (!(persona == null)){
            personaRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Persona validarUsuario(String nombre, String telefono){

        try {
            Persona personaValidar = personaRepository.validarUsuario(nombre, telefono);
            if (!(personaValidar == null)){
                return personaValidar;
            }else {
                return null;
            }
        }catch (Exception e){
            log.error(e.getMessage());
            return null;
        }
    }
}
