package br.com.tcc.cee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.cee.modelo.Cargo;

@Repository
public interface CargoRepository extends JpaRepository<Cargo, Long>{
	
	List<Cargo> findByNomeContaining(String descricao);

}
