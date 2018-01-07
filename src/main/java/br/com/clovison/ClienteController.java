package br.com.clovison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import br.com.caelum.vraptor.Consumes;
import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Delete;
import br.com.caelum.vraptor.Get;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Put;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.view.Results;

@Controller
@Path("/cliente")
public class ClienteController {

	@Inject
	private Result result;
	
	private Map<Integer, Cliente> banco = new HashMap<Integer, Cliente>(); 
	
	@Post("/adiciona")
	@Consumes({"application/json", "application/xml"})
	public void adiciona(Cliente cliente) {
		
	System.out.println("Inserindo: "+cliente);
	result.use(Results.http()).setStatusCode(201);
	
	}
	
	@Get
	@Path("/")
	public List<Cliente> lista() {
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		clientes.add(new Cliente(1,"Clovison", 34, "Masculino") );
		clientes.add( new Cliente(2,"Gabriel", 6, "Masculino"));
		clientes.add( new Cliente(3,"Gustavo", 9, "Masculino"));
		clientes.add( new Cliente(4,"Gesy", 33, "Feminino"));
		
		result.use(Results.json()).from(clientes).serialize();
		
		return null;
	}

	@Get
	@Path("/{cliente.id}")
	public Cliente visualiza(Cliente cliente) {

		loadJson(banco.get(cliente.getId()));
		
		return null;
	}
	
	@Delete("/remove")
	@Consumes({"application/json", "application/xml"})
	
	public void remove(Cliente cliente) {
		System.out.println("Removendo: "+cliente);
		result.use(Results.http()).setStatusCode(204);
	}

	
	@Put("/atualiza")
	@Consumes({"application/json", "application/xml"})
	public void atualiza(Cliente cliente) {
		System.out.println("Atualizando: "+cliente);
		result.use(Results.http()).setStatusCode(204);
		
	}

	public void loadJson(Cliente cliente) {
		result.use(Results.json()).from(cliente).serialize();
	}

	public void loadXml(Cliente cliente) {
		result.use(Results.xml()).from(cliente).serialize();
	}
	
	@PostConstruct
	public void banco() {
		banco.put(1,new Cliente(1,"Clovison", 34, "Masculino") );
		banco.put(2, new Cliente(2,"Gabriel", 6, "Masculino"));
		banco.put(3, new Cliente(3,"Gustavo", 9, "Masculino"));
		banco.put(4, new Cliente(4,"Gesy", 33, "Feminino"));
	}
	
	
}
