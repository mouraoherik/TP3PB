package br.com.infnet.tp3pb.service;


import br.com.infnet.tp3pb.model.Cliente;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteService {
    private final ArrayList<Cliente> clientes = new ArrayList<>();

    private Long nextId = 1L;

    public List<Cliente> listarClientes(){ return clientes;}

    public Cliente buscarPorId(Long id){
        for (Cliente cliente : clientes){
            if (cliente.getIdCliente().equals(id)){
                return cliente;
            }
        }
        return null;
    }

    public void cadastrarCliente(Cliente cliente){
        cliente.setIdCliente(nextId++);
        clientes.add(cliente);

    }

    public boolean remover(Long id){
        boolean removeu = false;
        for(int i = 0 ; i < clientes.size(); i++){
            if(clientes.get(i).getIdCliente().equals(id)){
                clientes.remove(i);
                removeu = true;
            }
        }
        return removeu;
    }
}
