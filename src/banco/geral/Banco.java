package banco.geral;

import banco.cliente.Cliente;
import banco.contas.Conta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Banco {
    private List<Cliente> clientes;
    private List<Conta> contasBancarias;
    private double montanteSaldo;

    public Banco() {
        this.contasBancarias = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.montanteSaldo = 0;
    }

    public void obterMontanteSaldo(){
        double saldoTotal;
        List<Conta> listaContasCliente = clientes.stream().flatMap(cliente -> cliente.getConta().stream()).toList();
        saldoTotal = listaContasCliente.stream().mapToDouble(Conta::getSaldo).sum();
        System.out.println("O saldo total é: " + saldoTotal);
    }

    public void criarConta(Cliente cliente, Conta conta){
        Optional<Cliente> clienteOptional = clientes.stream()
                .filter(cliente1 -> cliente1.getCpf().equals(cliente.getCpf()))
                .findFirst();

        if (clienteOptional.isPresent()) {
            Cliente clienteEncontrado = clienteOptional.get();
            clienteEncontrado.getConta().add(conta); // adiciona diretamente na lista do cliente
            contasBancarias.add(conta);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

    public void adicionarClientes(Cliente cliente) {
        clientes.add(cliente);
    }

    public List<Conta> getContasBancarias() {
        return contasBancarias;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }
}
