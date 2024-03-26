import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leitores")
public class LeitorController {

	@Autowired
	private LeitorRepository leitorRepository;

	@PostMapping
	public ResponseEntity<Leitor> criarLeitor(@RequestBody Leitor leitor) {
		Leitor novoLeitor = leitorRepository.save(leitor);
		return new ResponseEntity<>(novoLeitor, HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Leitor> atualizarLeitor(@PathVariable Long id, @RequestBody Leitor leitorAtualizado) {
		Leitor leitor = leitorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Leitor não encontrado com o ID: " + id));
		leitor.setNome(leitorAtualizado.getNome());
		leitor.setCpfOuCnpj(leitorAtualizado.getCpfOuCnpj());
		leitor.setEnderecoCompleto(leitorAtualizado.getEnderecoCompleto());
		leitor.setContatos(leitorAtualizado.getContatos());
		Leitor leitorAtualizadoDB = leitorRepository.save(leitor);
		return ResponseEntity.ok(leitorAtualizadoDB);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Leitor> buscarLeitor(@PathVariable Long id) {
		Leitor leitor = leitorRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Leitor não encontrado com o ID: " + id));
		return ResponseEntity.ok(leitor);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletarLeitor(@PathVariable Long id) {
		leitorRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}
}
