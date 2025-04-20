package banco.contas;

import banco.cliente.Cliente;

public class ContaPoupanca extends Conta {
    private double rendimentoTotal;
    public ContaPoupanca(Cliente cliente) {
        super(cliente);
    }

    public void aplicarRendimentoMensal(double valor){
        rendimentoTotal = (rendimentoTotal+valor)*0.14;
    }

    public void sacarRendimento(Conta conta, double valor){
        rendimentoTotal -= valor;
        conta.depositar(valor);
    }



}
