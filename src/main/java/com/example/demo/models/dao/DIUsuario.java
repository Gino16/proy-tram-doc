package com.example.demo.models.dao;

import com.example.demo.models.entity.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface DIUsuario extends CrudRepository<Usuario, Long> {

    public Usuario findByUsername(String username);
}
