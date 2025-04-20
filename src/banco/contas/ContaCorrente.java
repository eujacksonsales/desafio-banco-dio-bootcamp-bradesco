package banco.contas;

import banco.cliente.Cliente;

import java.util.Scanner;

public class ContaCorrente extends Conta {
    private double valorChequeEspecial;
    private boolean usarChequeEspecial;
    private double valorDivida;

    public ContaCorrente(Cliente cliente) {
        super(cliente);
        valorChequeEspecial = 100;
    }


    Scanner scanner = new Scanner(System.in);


    public void ConsultarChequeEspecial(){
        System.out.println("O valor do seu saldo é: " + valorChequeEspecial);
    }


    public void DepositarDinheiroCC(){
        System.out.println("Qual valor você deseja depositar?");
        double valorDepositado = scanner.nextDouble();


        if (usarChequeEspecial && valorDepositado >= valorDivida){
            System.out.println("Conforme políticas de Cheque Especial, estamos tirando o valor de R$ "+ valorDivida +" da sua dívida do cheque especial");
            usarChequeEspecial = false;
            depositar(valorDepositado - valorDivida);
            valorDivida = 0;
        }else{
            depositar(valorDepositado);
        }

        System.out.println("Deposito concluído");
    }

    public void SacarDinheiro(){
        System.out.println("Qual valor você deseja sacar?");
        double valorPretendido= scanner.nextDouble();

        if(valorPretendido > saldo && VerificarChequeEspecial(valorPretendido)){
            System.out.println("Você não tem esse saldo disponível, deseja pegar do seu cheque especial? \n 1. Sim \n 2. Não");
            int desejaUsarCheque = scanner.nextInt();
            DesejaUsarChequeEspecial(desejaUsarCheque,(valorPretendido - saldo));
            saldo = 0;
            System.out.println("Saque concluído");
        } else if(valorPretendido <= saldo){
            sacar(valorPretendido);
            System.out.println("Saque concluído");
        }

    }

    public void PagarBoleto(){
        System.out.println("Qual valor do boleto?");
        double valorBoleto = scanner.nextDouble();

        if(valorBoleto > saldo && VerificarChequeEspecial(valorBoleto)){
            System.out.println("Você não tem esse saldo disponível, deseja pegar do seu cheque especial? \n 1. Sim \n 2. Não");
            int desejaUsarCheque = scanner.nextInt();
            DesejaUsarChequeEspecial(desejaUsarCheque,(valorBoleto - saldo));
            saldo = 0;
            System.out.println("Boleto pago");

        } else if(valorBoleto <= saldo){
            saldo -= valorBoleto;
            System.out.println("Boleto Pago");
        }

    }

    private void DesejaUsarChequeEspecial(int decisao, double valorPedido){
        if(decisao == 1){
            UsarChequeEspecial(valorPedido);
        }
        else if(decisao == 2){
            System.out.println("Então infelizmente você não conseguirá pagar o boleto");
        }
    }

    private boolean VerificarChequeEspecial(double valor){
        return valor <= (saldo + valorChequeEspecial);
    }

    public void UsarChequeEspecial(double valor){
        usarChequeEspecial = true;
        valorDivida = valor + valor*0.2;
        valorChequeEspecial -= valor;
        System.out.println("Você acabou de usar o Cheque especial no valor de: " + valor + " e por isso no seu próximo deposito cobraremos o valor de: " + valorDivida);
    }
}
