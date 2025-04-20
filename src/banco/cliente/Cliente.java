package banco.cliente;

import banco.contas.Conta;

import java.util.ArrayList;
import java.util.List;

public class Cliente {
    private String nome;
    private List<Conta> conta;
    private String cpf;
    private String telefone;

    public Cliente(String nome, String cpf, String telefone) {
        conta = new ArrayList<>();
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Conta> getConta() {
        return conta;
    }


    public String getCpf() {
        return cpf;
    }


    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }


}
