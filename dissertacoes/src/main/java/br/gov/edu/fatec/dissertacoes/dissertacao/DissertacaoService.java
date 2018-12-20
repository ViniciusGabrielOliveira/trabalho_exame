package br.gov.edu.fatec.dissertacoes.dissertacao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DissertacaoService {
	
	@Autowired
	public DissertacaoRepository dissertacaoRepository;

	public Dissertacao save(Dissertacao dissertacao) {
		
		return dissertacaoRepository.existsById(dissertacao.getId())?dissertacaoRepository.save(dissertacao):null;
	}

	public Dissertacao remove(Dissertacao dissertacao) {
		dissertacaoRepository.delete(dissertacao);
		return dissertacao;
	}

	public Dissertacao update(Dissertacao dissertacao, Dissertacao dicertacaoUpdate) {
		Optional<Dissertacao> dicer = findById(dissertacao.getId());
		if(dicer.isPresent()) {
			dicertacaoUpdate.setId(dicer.get().getId());
			return save(dicertacaoUpdate);
		}
		return dissertacao;
	}

	public Optional<Dissertacao> findById(Integer id) {
		Optional<Dissertacao> dissertacao = dissertacaoRepository.findById(id);
		return dissertacao.isPresent()?dissertacao:null;
	}

	public List<Dissertacao> findAll() {
		return dissertacaoRepository.findAll();
	}
	

}
