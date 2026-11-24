package br.com.infnet.tp3pb.repository;

import br.com.infnet.tp3pb.model.Cliente;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import java.nio.file.Path;

@Repository
public class ClienteRepository extends CsvRepository<Cliente> {

    public ClienteRepository(@Value("${app.dados}") String pasta) {
        super(Path.of(pasta, "clientes.csv"),
                "idCliente;nome;telefone;email;endereco");
    }

    @Override protected Long id(Cliente c) { return c.getIdCliente(); }

    @Override protected String[] paraCampos(Cliente c) {
        return new String[] {
                c.getIdCliente().toString(), c.getNome(), c.getTelefone(),
                c.getEmail(), c.getEndereco()
        };
    }

    @Override protected Cliente deCampos(String[] f) {
        return new Cliente(Long.parseLong(f[0]), f[1], f[2],
                f[3].isEmpty() ? null : f[3],
                f[4].isEmpty() ? null : f[4]);
    }
}