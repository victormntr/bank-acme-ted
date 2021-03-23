/**
 * 
 */
package br.com.cbgomes.acme.client.resource;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.cbgomes.acme.client.domain.Client;
import br.com.cbgomes.acme.client.repository.ClientRepository;
import br.com.cbgomes.acme.client.service.exception.NotFoundException;

/**
 * @author cbgomes
 *
 */

@RestController
@RequestMapping("/api")
public class ClientResource {
	
	@Autowired
	private ClientRepository repository;
	
	//GET ALL
	@GetMapping("/clients")
	public List<Client> getAllClients(){
		List<Client> clients = this.repository.findAll();
		return clients;
	}
	//GET BY ID
	@GetMapping("/clients/{id}")
	public Client listClientsById(@PathVariable(value="id") long id) {
		return this.repository.findById(id).orElseThrow(() -> new NotFoundException("ID not found, search for an existing ID"));
	}
	//POST
	@PostMapping("/clients")
	public Client createClient(@RequestBody Client client){
		return this.repository.save(client);
		
	}
	//DELETE FOR ID
	@DeleteMapping("clients/{id}")
	public void deleteByIdClient(@PathVariable(value="id") long id) throws Exception {
		if(this.repository.existsById(id)) {
			this.repository.deleteById(id);
		}else {
			throw new NotFoundException("ID not found, please insert ID existing for deletion");
		}
	}
	//DELETE FOR BODY
	@DeleteMapping("/clients")
	public void deleteClient(@RequestBody Client client) {
		this.repository.delete(client);
	}
	
	//UPDATE FOR BODY
	@PutMapping("/clients/{id}")
	public void updateClient(@RequestBody Client client, 
			@PathVariable(value="id") Long id) throws Exception{
		if(repository.existsById(id)) {
			this.repository.save(client);		
		}else {
			throw new NotFoundException("ID does not existing, please insert ID existing for updating");
		}
	}
}
//DTO
//ModelMapper
//Status of return









