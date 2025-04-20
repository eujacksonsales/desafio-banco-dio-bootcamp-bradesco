package banco.contas;

import banco.cliente.Cliente;

public class Conta {
    private static int SEQUENCIAL = 1;
    protected int agencia;
    protected int numero;
    protected double saldo;
    protected Cliente cliente;

    public Conta(Cliente cliente) {
        this.agencia = 1;
        this.numero = SEQUENCIAL++;
        this.cliente = cliente;
    }

    public double getSaldo() {
        return saldo;
    }

    public void sacar(double valor){
        this.saldo -= valor;
    }

    public void depositar(double valor){
        this.saldo += valor;
    }

    public void transferir(double valor, Conta conta){
        sacar(valor);
        conta.depositar(valor);
    }

    public void exibirExtrato(){
        System.out.println("=== Extrato Conta ===");
        System.out.printf("Titular: %s%n", cliente.getNome());
        System.out.printf("Agencia: %d%n", agencia);
        System.out.printf("Numero da Conta: %d%n", numero);
        System.out.printf("Saldo: %.2f%n", saldo);
    }
}
