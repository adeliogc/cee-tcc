package br.com.tcc.cee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.cee.modelo.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

	List<Usuario> findByNomeContaining(String descricao);

}
