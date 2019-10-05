package br.com.tcc.cee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.tcc.cee.modelo.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long>{

	List<Funcionario> findByNomeContaining(String upperCase);

}
