package br.com.tcc.cee.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/ordemServicos")
public class OrdemServicoResource {
	
//	@Autowired
//	private OrdemServicoRepository ordemServicoRepository;
//	
//	@GetMapping("porItem/{numeroSerie}")
//	public ResponseEntity<List<OrdemServicoBeanDTO>> listar(@PathVariable("numeroSerie") String numeroSerie){
//		List<OrdemServico> ordemServicos = ordemServicoRepository.findByItensEquipamentoNumeroSerie(numeroSerie);
//		List<OrdemServicoBeanDTO> beanDTO = new ArrayList<>();
//		
//		for (OrdemServico os : ordemServicos) {
//			OrdemServicoBeanDTO bean = new OrdemServicoBeanDTO();
//			bean.setDataAbertura(os.getDataAbertura().toLocalDate());
//			bean.setNumeroOrdemServico(os.getNumeroOrdemServico());
//			bean.setObservacao(os.getObservacao());
//			List<OrdemServicoItem> itens = os.getItens();
//			List<OrdemServicoItemBean> itemsBean = new ArrayList<>();
//			for(OrdemServicoItem item : itens) {
//				if (item.getEquipamento().getNumeroSerie().equals(numeroSerie)) {
//					OrdemServicoItemBean itemBean = new OrdemServicoItemBean();
//					itemBean.setEquipamento(item.getEquipamento().getNome());
//					itemBean.setId(item.getEquipamento().getNumeroSerie());
//					itemBean.setStatus(item.getStatus().getDescricao());
//					itemsBean.add(itemBean);					
//				}
//			}
//			bean.setItems(itemsBean);
//			beanDTO.add(bean);
//		}
//		return beanDTO.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(beanDTO, HttpStatus.OK);
//	}
//	
//	@GetMapping("porNumeroOs/{id}")
//	public ResponseEntity<OrdemServicoBeanDTO> listarPorId(@PathVariable("id") String id){
//		OrdemServico ordemServico = ordemServicoRepository.findByNumeroOrdemServico(id);
//		OrdemServicoBeanDTO beanDTO = new OrdemServicoBeanDTO();
//		beanDTO.setDataAbertura(ordemServico.getDataAbertura().toLocalDate());
//		beanDTO.setNumeroOrdemServico(ordemServico.getNumeroOrdemServico());
//		beanDTO.setObservacao(ordemServico.getObservacao());
//		for (OrdemServicoItem item : ordemServico.getItens()) {
//			OrdemServicoItemBean itemBean = new OrdemServicoItemBean();
//			itemBean.setEquipamento(item.getEquipamento().getNome());
//			itemBean.setId(item.getEquipamento().getNumeroSerie());
//			itemBean.setStatus(item.getStatus().getDescricao());
//			beanDTO.getItems().add(itemBean);					
//		}
//		return ordemServico == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(beanDTO, HttpStatus.OK);
//	}

}
