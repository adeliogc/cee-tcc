package br.com.tcc.cee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.cee.modelo.Setor;

@Repository
public interface SetorRepository extends JpaRepository<Setor, Long>{
	
	List<Setor> findByNomeContaining(String descricao);


}
