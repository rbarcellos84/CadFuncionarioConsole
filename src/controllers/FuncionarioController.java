package controllers;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import entities.Funcionario;
import entities.Impressao;
import repositories.FuncionarioRepository;

public class FuncionarioController {
    
    private Scanner scanner = new Scanner(System.in);
    private DecimalFormat decimalFormat = new DecimalFormat("0.00");
    private FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
    private ImpressaoController impressaoController = new ImpressaoController();
    private MenuController menuController = new MenuController();
    
    public boolean cadastrarFuncionario() {
        try {
            System.out.println("\n    ***********************************");
            System.out.println("           Cadastrar funcionario");
            System.out.println("    ***********************************\n");
            
            Funcionario funcionario = new Funcionario();
            funcionario.setId(UUID.randomUUID());
            
            System.out.println("Digite o nome do funcionario: ");
            funcionario.setNome(scanner.nextLine());
            
            System.out.println("Digite o CPF do funcionario: ");
            funcionario.setCpf(scanner.nextLine());
            
            System.out.println("Digite a matricula do funcionario: ");
            funcionario.setMatricula(scanner.nextLine());
            
            System.out.println("Digite o salario do funcionario: ");
            funcionario.setSalario(new BigDecimal(scanner.nextLine()));
            
            Funcionario controle = new Funcionario();
            controle = funcionarioRepository.consultarPorMatricula(controle.getMatricula());
            if (controle.getId() != null) {
                String mensagem = "Matricula: " + controle.getMatricula() + " ja cadastrada.";
                impressaoController.imprimirMensagem(mensagem);
                menuController.pauser();
                return false;
            }
            
            funcionarioRepository.inserir(funcionario);
            return true;
        }
        catch(Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
        
        return false;
    }
    
    public boolean AtualizarFuncionario() {
        try {
            System.out.println("\n    ***********************************");
            System.out.println("           Atualizar funcionario");
            System.out.println("    ***********************************\n");
            
            Funcionario funcionario = new Funcionario();
            
            System.out.println("Digite a matricula que sera atualizado: ");
            String matricula = scanner.nextLine();
            funcionario = funcionarioRepository.consultarPorMatricula(matricula);
            
            if (funcionario.getId() == null) {
                String mensagem = "Matricula: " + matricula + " nao encontrada.";
                impressaoController.imprimirMensagem(mensagem);
                menuController.pauser();
                return false;
            }
            
            System.out.println("Digite o nome do funcionario: ");
            funcionario.setNome(scanner.nextLine());
            
            System.out.println("Digite a matricula do funcionario: ");
            funcionario.setMatricula(scanner.nextLine());
            
            System.out.println("Digite o CPF do funcionario: ");
            funcionario.setCpf(scanner.nextLine());
            
            System.out.println("Digite o salario do funcionario: ");
            funcionario.setSalario(new BigDecimal(scanner.nextLine()));
            
            funcionarioRepository.alterar(funcionario);
            return true;
        }
        catch(Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
        
        return false;
    }
    
    public boolean ConsultarFuncionario(int opcao) {
        try {
            System.out.println("\n    ***********************************");
            System.out.println("         Consulta de funcionario");
            System.out.println("    ***********************************\n");
            
            Funcionario funcionario = new Funcionario();
            
            System.out.println("Digite a matricula do funcionario: ");
            String matricula = scanner.nextLine();
            funcionario = funcionarioRepository.consultarPorMatricula(matricula);
            
            if (funcionario.getId() == null) {
                String mensagem = "Matricula: " + matricula + " nao encontrada.";
                impressaoController.imprimirMensagem(mensagem);
                menuController.pauser();
                return false;
            }
            
            Impressao titulo = new Impressao();
            titulo.setCampo1("Nome");
            titulo.setCampo2("Matricula");
            titulo.setCampo3("CPF");
            titulo.setCampo4("Salario");
            titulo.setCampo12("4");
            
            Impressao conteudo = new Impressao();
            conteudo.setCampo1(funcionario.getNome());
            conteudo.setCampo2(funcionario.getMatricula());
            conteudo.setCampo3(funcionario.getCpf());
            
            String salarioFormatado = decimalFormat.format(funcionario.getSalario());
            conteudo.setCampo4(salarioFormatado);
            conteudo.setCampo12("4");
            
            if (opcao == 1) {
                impressaoController.imprimirObjetoResumido(titulo);
                impressaoController.imprimirObjetoResumido(conteudo);
            }
            else {
                impressaoController.imprimirObjetoDetalhe(titulo, conteudo);
            }

            return true;
        }
        catch(Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
        
        return false;
    }
    
    public boolean consultarTodos(int opcao) throws Exception {
        try {
            System.out.println("\n    ***********************************");
            System.out.println("      Consultar todos os funcionarios");
            System.out.println("    ***********************************\n");
            
            List<Funcionario> funcionarios = new ArrayList<>();
            funcionarios = funcionarioRepository.consultarTodos();
            
            if (funcionarios.isEmpty()) {
                String mensagem = "Nao existe funcionario cadastrado no sistema.";
                impressaoController.imprimirMensagem(mensagem);
                menuController.pauser();
                return false;
            }
            
            Impressao titulo = new Impressao();
            titulo.setCampo1("Nome");
            titulo.setCampo2("Matricula");
            titulo.setCampo3("CPF");
            titulo.setCampo4("Salario");
            titulo.setCampo12("4");
            
            List<Impressao> impressao = new ArrayList<>();
            for (Funcionario funcionario : funcionarios) {
                Impressao item = new Impressao();
                item.setCampo1(funcionario.getNome());
                item.setCampo2(funcionario.getMatricula());
                item.setCampo3(funcionario.getCpf());
                
                String salarioFormatado = decimalFormat.format(funcionario.getSalario());
                item.setCampo4(salarioFormatado);
                
                item.setCampo12("4");
                impressao.add(item);
            }
            
            if (opcao == 1)
                impressaoController.imprimirListaResumido(titulo, impressao);
            else
                impressaoController.imprimirListaDetalhe(titulo, impressao);
            
            return true;
        }
        catch(Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
        
        return false;
    }
}
