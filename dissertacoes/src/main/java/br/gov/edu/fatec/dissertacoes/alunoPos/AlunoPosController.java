package br.gov.edu.fatec.dissertacoes.alunoPos;

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
@RequestMapping("/AlunoPos")
public class AlunoPosController {
	
	@Autowired
	private AlunoPosService alunoPosService;
	
	@PostMapping(path = "/new")
	public ResponseEntity<Response<AlunoPos>> cadastrar(@Valid @RequestBody AlunoPos alunoPos, BindingResult result) {
		Response<AlunoPos> response = new Response<AlunoPos>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		AlunoPos alunoSalvo = this.alunoPosService.save(alunoPos);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alunoPos.getAlu_matricula())
				.toUri();
		response.setData(alunoSalvo);
		return ResponseEntity.created(location).body(response);
	}
	
	@PutMapping("/put/{matricula}")
	public ResponseEntity<Response<AlunoPos>> atualizar(@Valid @RequestBody AlunoPos alunoPos, BindingResult result, @PathVariable("matricula") Integer alu_matricula){
		Response<AlunoPos> response = new Response<AlunoPos>();
		

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		
		Optional<AlunoPos> alunoPosUpdate = alunoPosService.findByAlu_matricula(alu_matricula);

		AlunoPos alunoSalvo = alunoPosService.update(alunoPos, alunoPosUpdate.get());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(alunoPos.getAlu_matricula()).toUri();
		response.setData(alunoSalvo);
		return ResponseEntity.created(location).body(response);
	 }
	
	@GetMapping
	public ResponseEntity<List<AlunoPos>> listar() {
		List<AlunoPos> alunos = alunoPosService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(alunos);
	}

	@GetMapping(path = "/{alu_matricula}")
	public ResponseEntity<Response<AlunoPos>> buscar(@PathVariable("alu_matricula") Integer alu_matricula) {
  
		Optional<AlunoPos> aluno = alunoPosService.findByAlu_matricula(alu_matricula);
		Response<AlunoPos> response = new Response<AlunoPos>();
		response.setData(aluno.get());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/delete/{alu_matricula}")
    public ResponseEntity<Response<AlunoPos>> deletePersona(@PathVariable("alu_matricula") Integer alu_matricula) {

		if (alunoPosService.alunoPosRepository.existsByAlu_matricula(alu_matricula)) {
			alunoPosService.remove(alunoPosService.findByAlu_matricula(alu_matricula).get());
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
    }
	
}
