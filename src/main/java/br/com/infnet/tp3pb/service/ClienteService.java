package br.com.infnet.tp3pb.service;


import br.com.infnet.tp3pb.model.Cliente;
import br.com.infnet.tp3pb.repository.ClienteRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {
    private final ClienteRepository repository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.repository = clienteRepository;
    }

    public List<Cliente> listarClientes(){ return repository.listar();}

    public Cliente buscarPorId(Long id) {
        return repository.buscarPorId(id)
                .orElseThrow();
    }

    public Cliente cadastrarCliente(String nome, String telefone, String email, String endereco) {
        return repository.adicionar(id ->
                new Cliente(id, nome.trim(), telefone.trim(), email.trim(), endereco.trim()));
    }

    public Cliente atualizar(Long id, String nome, String telefone, String email, String endereco) {
        buscarPorId(id);
        Cliente atualizado = new Cliente(id, nome.trim(), telefone.trim(),
                email.trim(), endereco.trim());
        repository.atualizar(atualizado);
        return atualizado;
    }

    public void  remover(Long id) {
        buscarPorId(id);
        repository.remover(id);
    }

}
