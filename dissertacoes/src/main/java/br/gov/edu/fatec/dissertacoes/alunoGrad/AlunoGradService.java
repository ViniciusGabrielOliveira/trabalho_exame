package br.gov.edu.fatec.dissertacoes.alunoGrad;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlunoGradService {
	
	@Autowired
	public AlunoGradRepository alunoGradRepository;
	
	@Transactional
	public AlunoGrad save(AlunoGrad alunoGrad) {
		
		return alunoGradRepository.existsByAlu_matricula(alunoGrad.getAlu_matricula())?alunoGradRepository.save(alunoGrad):null;
	}

	@Transactional
	public AlunoGrad remove(AlunoGrad alunoGrad) {
		alunoGradRepository.delete(alunoGrad);
		return alunoGrad;
	}
	
	@Transactional
	public AlunoGrad update(AlunoGrad alunoGrad, AlunoGrad alunoGradUpdate) {
		Optional<AlunoGrad> al = findByMatricula(alunoGrad.getAlu_matricula());
		if(al.isPresent()) {
			alunoGradUpdate.setAlu_matricula(al.get().getAlu_matricula());
			return save(alunoGradUpdate);
		}
		return alunoGrad;
	}
	
	@Transactional
	public Optional<AlunoGrad> findByMatricula(Integer matricula) {
		Optional<AlunoGrad> alunoGrad = alunoGradRepository.findByAlu_matricula(matricula);
		return alunoGrad.isPresent()?alunoGrad:null;
	}

	@Transactional
	public List<AlunoGrad> findAll() {
		return alunoGradRepository.findAll();
	}

}
