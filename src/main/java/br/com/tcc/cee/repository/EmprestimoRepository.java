package br.com.tcc.cee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.tcc.cee.modelo.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long>{
	
	Emprestimo findByNumeroEmprestimo(String numero);

	@Query(nativeQuery = true, value = "SELECT EM.* FROM EMPRESTIMOS EM JOIN EMPRESTIMO_ITEM IT ON IT.EMPRESTIMO_ID = EM.ID JOIN EQUIPAMENTOS EQ ON EQ.ID = IT.EQUIPAMENTO_ID WHERE EQ.NUMERO_SERIE = :id")
	List<Emprestimo> findByItensEquipamentoNumeroSerie(@Param("id") String id);

}
