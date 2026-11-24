package br.com.infnet.tp3pb.repository;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;
import java.util.function.LongFunction;

public abstract class CsvRepository<T> {
    private static final char separador = ';';

    private final Path arquivo;
    private final String cabecalho;

    protected CsvRepository(Path arquivo, String cabecalho) {
        this.arquivo = arquivo;
        this.cabecalho = cabecalho;
    }

    protected abstract Long id(T item);

    protected abstract String[] paraCampos(T item);

    protected abstract T deCampos(String[] campos);


    public synchronized List<T> listar() {
        List<T> itens = new ArrayList<>();
        if (!Files.exists(arquivo)) return itens;
        int colunas = cabecalho.split(String.valueOf(separador)).length;
        try {
            List<String> linhas = Files.readAllLines(arquivo);
            for (int i = 1; i < linhas.size(); i++) {
                if (linhas.get(i).isBlank()) continue;
                String[] campos = dividir(linhas.get(i));
                if (campos.length != colunas) {
                    throw new IllegalStateException(arquivo + ", linha " + (i + 1)
                            + ": esperados " + colunas + " campos, encontrados " + campos.length);
                }
                itens.add(deCampos(campos));
            }
        } catch (IOException e) {
            throw new UncheckedIOException("Erro ao ler " + arquivo, e);
        }
        return itens;
    }


    public synchronized Optional<T> buscarPorId(Long id) {
        return listar().stream().filter(i -> id(i).equals(id)).findFirst();
    }


    public synchronized T adicionar(LongFunction<T> fabrica) {
        List<T> itens = listar();
        long novoId = itens.stream().mapToLong(this::id).max().orElse(0L) + 1;
        T novo = fabrica.apply(novoId);
        itens.add(novo);
        salvarTodos(itens);
        return novo;
    }

    public synchronized boolean atualizar(T atualizado) {
        List<T> itens = listar();
        for (int i = 0; i < itens.size(); i++) {
            if (id(itens.get(i)).equals(id(atualizado))) {
                itens.set(i, atualizado);
                salvarTodos(itens);
                return true;
            }
        }
        return false;
    }

    public synchronized boolean remover(long id) {
        List<T> itens = listar();
        boolean removeu = itens.removeIf(i -> id(i) == id);
        if (removeu) salvarTodos(itens);
        return removeu;
    }

    private void salvarTodos(List<T> itens) {
        List<String> linhas = new ArrayList<>();
        linhas.add(cabecalho);
        for (T item : itens) {
            String[] campos = paraCampos(item);
            StringJoiner linha = new StringJoiner(String.valueOf(separador));
            for (String campo : campos) linha.add(escapar(campo));
            linhas.add(linha.toString());
        }
        try {
            if (arquivo.getParent() != null) Files.createDirectories(arquivo.getParent());
            Path temp = arquivo.resolveSibling(arquivo.getFileName() + ".tmp");
            Files.write(temp, linhas);
            Files.move(temp, arquivo, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new UncheckedIOException("Erro ao gravar " + arquivo, e);
        }
    }

    private String escapar(String valor) {
        if (valor == null) return "";
        valor = valor.replace("\r", " ").replace("\n", " ");
        if (valor.indexOf(separador) >= 0 || valor.indexOf('"') >= 0) {
            valor = "\"" + valor.replace("\"", "\"\"") + "\"";
        }
        return valor;
    }

    private String[] dividir(String linha) {
        List<String> campos = new ArrayList<>();
        StringBuilder atual = new StringBuilder();
        boolean entreAspas = false;
        for (int i = 0; i < linha.length(); i++) {
            char c = linha.charAt(i);
            if (entreAspas) {
                if (c == '"' && i + 1 < linha.length() && linha.charAt(i + 1) == '"') {
                    atual.append('"');
                    i++;
                } else if (c == '"') {
                    entreAspas = false;
                } else {
                    atual.append(c);
                }
            } else if (c == '"') {
                entreAspas = true;
            } else if (c == separador) {
                campos.add(atual.toString());
                atual.setLength(0);
            } else {
                atual.append(c);
            }
        }
        campos.add(atual.toString());
        return campos.toArray(new String[0]);
    }
}
