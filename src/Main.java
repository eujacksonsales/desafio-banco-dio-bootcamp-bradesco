import banco.cliente.Cliente;
import banco.contas.Conta;
import banco.contas.ContaCorrente;
import banco.contas.ContaPoupanca;
import banco.geral.Banco;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<Cliente> clientesCadastrados = new ArrayList<>();
        Banco banco = new Banco();
        Scanner scanner = new Scanner(System.in);
        int decisaoUser;

        do {
            System.out.println("Olá usuário, para iniciar sua operação, crie contas de clientes");
            System.out.println("Informe seu nome");
            String nome = scanner.next();
            System.out.println("Informe seu cpf");
            String cpf = scanner.next();
            System.out.println("Informe seu telefone");
            String telefone = scanner.next();
            Cliente cliente = new Cliente(nome, cpf, telefone);
            clientesCadastrados.add(cliente);
            banco.adicionarClientes(cliente);
        } while (clientesCadastrados.size() <= 3);



        do{
            System.out.println("Olá usuário, agora você é administrador do banco");

            System.out.println("====Escolha uma das opções====");
            System.out.println("1 - Criar conta de cliente");
            System.out.println("2 - Consultar saldo do banco");
            System.out.println("3 - Consultar quantidade de contas");
            System.out.println("4 - Consultar quantidade de clientes");
            System.out.println("0 - Sair");
            decisaoUser = scanner.nextInt();
            switch (decisaoUser){
                case 1 -> {
                    System.out.println("Informe: 1. Criar conta conta corrente\n 2. Conta poupança?");
                    decisaoUser = scanner.nextInt();
                    if (decisaoUser == 1){
                        for(Cliente cliente : clientesCadastrados){
                            banco.criarConta(cliente, new ContaCorrente(cliente));
                        }
                    } else if (decisaoUser == 2) {
                        for(Cliente cliente : clientesCadastrados){
                            banco.criarConta(cliente, new ContaPoupanca(cliente));
                        }
                    }

                    System.out.println("Contas criadas");
                }
                case 2 -> banco.obterMontanteSaldo();
                case 3 -> System.out.println("A quantidade de contas é: " + banco.getContasBancarias().size());
                case 4 -> System.out.println("A quantidade de clientes é: " + banco.getClientes().size());
                default -> {
                    if(decisaoUser !=0) {
                        System.out.println("Opção inválida");
                    }
                }
            }
        } while (decisaoUser != 0);


        System.out.println("Agora você ficará como cliente");
        System.out.println("Informe seu cpf para buscar sua conta cliente");
        String cpfInformado = scanner.next();
        Optional<Cliente> optionalCliente = clientesCadastrados.stream()
                .filter(cliente1 -> cliente1.getCpf().equals(cpfInformado))
                .findFirst();
        int option;
        if (optionalCliente.isPresent()) {
            Cliente cliente = optionalCliente.get();
            if(cliente.getConta().getFirst() instanceof ContaCorrente) {
                do {
                    System.out.println("====Escolha uma das opções====");
                    System.out.println("1 - Depositar dinheiro");
                    System.out.println("2 - Sacar dinheiro");
                    System.out.println("3 - Consultar saldo");
                    System.out.println("4 - Consultar cheque especial");
                    System.out.println("5 - Pagar boleto");
                    System.out.println("0 - Sair");
                    option = scanner.nextInt();
                    switch (option) {
                        case 1 -> ((ContaCorrente) cliente.getConta().getFirst()).DepositarDinheiroCC();
                        case 2 -> ((ContaCorrente) cliente.getConta().getFirst()).SacarDinheiro();
                        case 3 -> System.out.println("Saldo: " + cliente.getConta().getFirst().getSaldo());
                        case 4 -> ((ContaCorrente) cliente.getConta().getFirst()).ConsultarChequeEspecial();
                        case 5 -> ((ContaCorrente) cliente.getConta().getFirst()).PagarBoleto();
                        case 0 -> System.exit(0);
                        default -> System.out.println("Opção inválida");
                    }
                }
                while (true);
            } else if(cliente.getConta().getFirst() instanceof ContaPoupanca) {
                do {
                    System.out.println("====Escolha uma das opções====");
                    System.out.println("1 - Depositar dinheiro");
                    System.out.println("2 - Sacar dinheiro");
                    System.out.println("3 - Aplicar rendimento");
                    System.out.println("4 - Consultar saldo");
                    System.out.println("5 - Sacar rendimento");
                    System.out.println("0 - Sair");
                    option = scanner.nextInt();
                    switch (option) {
                        case 1 -> {
                            System.out.println("Informe o valor a ser depositado");
                            double valorParaDepositar = scanner.nextDouble();
                            cliente.getConta().getFirst().depositar(valorParaDepositar);
                        }
                        case 2 -> {
                            System.out.println("Informe o valor a ser sacado");
                            double valorSacar = scanner.nextDouble();
                            cliente.getConta().getFirst().sacar(valorSacar);
                        }
                        case 3 -> {
                            System.out.println("Saldo: " + cliente.getConta().getFirst().getSaldo());
                        }
                        case 4 -> {
                            System.out.println("Informe o valor a aplicado");
                            double valorAplicado = scanner.nextDouble();
                            ((ContaPoupanca) cliente.getConta().getFirst()).aplicarRendimentoMensal(valorAplicado);
                            System.out.println("Valor aplicado");
                        }
                        case 5 -> {
                            System.out.println("Informe o valor a ser sacado");
                            double valorSacado = scanner.nextDouble();
                            ((ContaPoupanca) cliente.getConta().getFirst()).sacarRendimento(cliente.getConta().getFirst(), valorSacado);
                            System.out.println("Valor do rendimento sacado");
                        }
                        case 0 -> System.exit(0);
                        default -> System.out.println("Opção inválida");
                    }
                }
                while (true);
            }
        }

    }

}
