package br.gov.edu.fatec.dissertacoes.alunoGrad;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.gov.edu.fatec.dissertacoes.Response;

@RestController
@RequestMapping("/AlunoGrad")
public class AlunoGradController {
	
	@Autowired
	private AlunoGradService alunoGradService;
	
	@PostMapping(path = "/new")
	public ResponseEntity<Response<AlunoGrad>> cadastrar(@Valid @RequestBody AlunoGrad alunoGrad, BindingResult result) {
		Response<AlunoGrad> response = new Response<AlunoGrad>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		AlunoGrad alunoSalvo = this.alunoGradService.save(alunoGrad);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alunoGrad.getAlu_matricula())
				.toUri();
		response.setData(alunoSalvo);
		return ResponseEntity.created(location).body(response);
	}
	
	@PutMapping("/put/{matricula}")
	public ResponseEntity<Response<AlunoGrad>> atualizar(@Valid @RequestBody AlunoGrad alunoGrad, BindingResult result, @PathVariable("matricula") Integer matricula){
		Response<AlunoGrad> response = new Response<AlunoGrad>();
		

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		
		Optional<AlunoGrad> alunoGradUpdate = alunoGradService.findByMatricula(matricula);
		

		AlunoGrad alunoSalvo = alunoGradService.update(alunoGrad, alunoGradUpdate.get());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alunoGrad.getAlu_matricula()).toUri();
		response.setData(alunoSalvo);
		return ResponseEntity.created(location).body(response);
	 }
	
	@GetMapping
	public ResponseEntity<List<AlunoGrad>> listar() {
		List<AlunoGrad> alunos = alunoGradService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(alunos);
	}

	@GetMapping(path = "/{matricula}")
	public ResponseEntity<Response<AlunoGrad>> buscar(@PathVariable("matricula") Integer matricula) {
  
		Optional<AlunoGrad> aluno = alunoGradService.findByMatricula(matricula);
		Response<AlunoGrad> response = new Response<AlunoGrad>();
		response.setData(aluno.get());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/delete/{matricula}")
    public ResponseEntity<Response<AlunoGrad>> deletePersona(@PathVariable("matricula") Integer matricula) {

		if (alunoGradService.alunoGradRepository.existsByMatricula(matricula)) {
			alunoGradService.remove(alunoGradService.findByMatricula(matricula).get());
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
    }
	
}
