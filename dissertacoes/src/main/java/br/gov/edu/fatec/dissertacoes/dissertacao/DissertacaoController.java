package br.gov.edu.fatec.dissertacoes.dissertacao;

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
@RequestMapping("/Dissertacao")
public class DissertacaoController {
	
	@Autowired
	private DissertacaoService dissertacaoService;
	
	@PostMapping(path = "/new")
	public ResponseEntity<Response<Dissertacao>> cadastrar(@Valid @RequestBody Dissertacao dissertacao, BindingResult result) {
		Response<Dissertacao> response = new Response<Dissertacao>();

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		Dissertacao dissertacaoSalvo = this.dissertacaoService.save(dissertacao);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dissertacao.getId()).toUri();
		response.setData(dissertacaoSalvo);
		return ResponseEntity.created(location).body(response);
	}
	
	@PutMapping("/put/{id}")
	public ResponseEntity<Response<Dissertacao>> atualizar(@Valid @RequestBody Dissertacao dissertacao, BindingResult result, @PathVariable("id") Integer id){
		Response<Dissertacao> response = new Response<Dissertacao>();
		

		if (result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		
		Optional<Dissertacao> dissertacaoUpdate = dissertacaoService.findById(id);

		Dissertacao dissertacaoSalvo = dissertacaoService.update(dissertacao, dissertacaoUpdate.get());
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dissertacao.getId()).toUri();
		response.setData(dissertacaoSalvo);
		return ResponseEntity.created(location).body(response);
	 }
	
	@GetMapping
	public ResponseEntity<List<Dissertacao>> listar() {
		List<Dissertacao> dissertacoes = dissertacaoService.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(dissertacoes);
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Dissertacao>> buscar(@PathVariable("id") Integer id) {
  
		Optional<Dissertacao> dissertacao = dissertacaoService.findById(id);
		Response<Dissertacao> response = new Response<Dissertacao>();
		response.setData(dissertacao.get());
		return ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
	@DeleteMapping("/delete/{id}")
    public ResponseEntity<Response<Dissertacao>> deletePersona(@PathVariable("id") Integer id) {

		if (dissertacaoService.dissertacaoRepository.existsById(id)) {
			dissertacaoService.remove(dissertacaoService.findById(id).get());
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
    }
	
}
