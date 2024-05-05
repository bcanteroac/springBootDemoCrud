package com.bcantero.demo.repository;

import com.bcantero.demo.entity.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {

    @Query(
//            value = "select * from persona where nombre = :nombre and telefono = :telefono",
            value = "SELECT * FROM persona WHERE LOWER(nombre) = LOWER(:nombre) AND telefono = :telefono",
            nativeQuery = true
    )
    Persona validarUsuario(@Param("nombre") String nombre, @Param("telefono") String telefono);

}
