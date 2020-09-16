package com.example.demo.models.service.usuario;

import com.example.demo.models.dao.DIUsuario;
import com.example.demo.models.entity.Role;
import com.example.demo.models.entity.Usuario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class SUsuario implements UserDetailsService {

    @Autowired
    private DIUsuario usuarioDao;

    private Logger log = LoggerFactory.getLogger(getClass());

    public Usuario findByUsername(String username){
        return usuarioDao.findByUsername(username);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(s);

        if (usuario == null){
            log.error("No existe el usuario en la BD");
            throw new UsernameNotFoundException("Username " + s + " no existe en la BD");
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        for (Role role : usuario.getRoles()){
            authorities.add(new SimpleGrantedAuthority(role.getNombre()));
        }

        if(authorities.isEmpty()){
            log.error("No existen roles para el usuario" + s);
            throw new UsernameNotFoundException("Username " + s + " no tiene roles");
        }

        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, authorities);
    }
}
