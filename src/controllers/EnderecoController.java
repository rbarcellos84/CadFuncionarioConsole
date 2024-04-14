package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;
import entities.Endereco;
import entities.FunEndereco;
import entities.Funcionario;
import entities.Impressao;
import repositories.EnderecoRepository;
import repositories.FunEnderecoRepository;
import repositories.FuncionarioRepository;

public class EnderecoController {
    private Scanner scanner = new Scanner(System.in);
    private FuncionarioRepository funcionarioRepository = new FuncionarioRepository();
    private FunEnderecoRepository funEnderecoRepository = new FunEnderecoRepository();
    private EnderecoRepository enderecoRepository = new EnderecoRepository();
    private ImpressaoController impressaoController = new ImpressaoController();
    private MenuController menuController = new MenuController();
    
    public boolean cadastrarEndereco() throws Exception {
        try {
            System.out.println("\n    ***********************************");
            System.out.println("           Cadastrar endereco");
            System.out.println("    ***********************************\n");
            
            Endereco endereco = new Endereco();
            endereco.setId(UUID.randomUUID());
            
            Funcionario funcionario = new Funcionario();
            System.out.println("Digite a matricula do funcionario: ");
            String matricula = scanner.nextLine();
            
            funcionario = funcionarioRepository.consultarPorMatricula(matricula);
            System.out.println("Nome do funcionario: " + funcionario.getNome() + "\n");
            
            endereco.setFuncionario(funcionario);
            
            System.out.println("Digite o logradouro: ");
            endereco.setLogradouro(scanner.nextLine());
            
            System.out.println("Digite o numero do endereco: ");
            endereco.setNumero(scanner.nextLine());
            
            System.out.println("Digite o bairro do endereco: ");
            endereco.setBairro(scanner.nextLine());
            
            System.out.println("Digite a cidade do endereco: ");
            endereco.setCidade(scanner.nextLine());
            
            System.out.println("Digite o estado do endereco (Sigla com 2 digitos): ");
            endereco.setEstado(scanner.nextLine());
            
            System.out.println("Digite o CEP do endereco: ");
            endereco.setCep(scanner.nextLine());
            
            System.out.println("Digite o complemento do endereco: ");
            endereco.setComplemento(scanner.nextLine());
            
            Endereco controle = new Endereco();
            controle = enderecoRepository.consultarEndereco(endereco.getCep(), endereco.getNumero());
            if (controle.getId() != null) {
                String mensagem = "Endereco ja cadastrado no sistema.";
                impressaoController.imprimirMensagem(mensagem);
                menuController.pauser();
                return false;
            }
            
            enderecoRepository.inserir(endereco);
            return true;
        } catch (Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
        return false;
    }
    
    public boolean atualizarEndereco() throws Exception {
        try {
            System.out.println("\n    ***********************************");
            System.out.println("           Cadastrar endereco");
            System.out.println("    ***********************************\n");
            
            Endereco endereco = new Endereco();
            
            Funcionario funcionario = new Funcionario();
            System.out.println("Digite a matricula do funcionario: ");
            String matricula = scanner.nextLine();
            
            funcionario = funcionarioRepository.consultarPorMatricula(matricula);
            System.out.println("Nome do funcionario: " + funcionario.getNome() + "\n");
            
            endereco.setFuncionario(funcionario);
            
            Endereco dados = new Endereco();
            System.out.println("Digite o CEP do endereco para atualizacao: ");
            String cep = scanner.nextLine();
            System.out.println("Digite o numero do endereco para atualizacao: ");
            String numero = scanner.nextLine();
            dados = enderecoRepository.consultarEndereco(cep, numero);
            if (dados.getId() == null) {
                String mensagem = "Endereco nao encontrado no sistema.";
                impressaoController.imprimirMensagem(mensagem);
                menuController.pauser();
                return false;
            }
            
            endereco.setId(dados.getId());
            
            System.out.println("Digite o logradouro: ");
            endereco.setLogradouro(scanner.nextLine());
            
            System.out.println("Digite o numero do endereco: ");
            endereco.setNumero(scanner.nextLine());
            
            System.out.println("Digite o bairro do endereco: ");
            endereco.setBairro(scanner.nextLine());
            
            System.out.println("Digite a cidade do endereco: ");
            endereco.setCidade(scanner.nextLine());
            
            System.out.println("Digite o estado do endereco (Sigla com 2 digitos): ");
            endereco.setEstado(scanner.nextLine());
            
            System.out.println("Digite o CEP do endereco: ");
            endereco.setCep(scanner.nextLine());
            
            System.out.println("Digite o complemento do endereco: ");
            endereco.setComplemento(scanner.nextLine());
            
            Endereco controle = new Endereco();
            controle = enderecoRepository.consultarEndereco(endereco.getCep(), endereco.getNumero());
            if ((controle.getId() != null) && (endereco.getFuncionario().getId().toString().equals(controle.getFuncionario().getId().toString()) == false)) {
                String mensagem = "Endereco ja cadastrado no sistema.";
                impressaoController.imprimirMensagem(mensagem);
                return false;
            }
            
            enderecoRepository.alterar(endereco);
            return true;
        } catch (Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
        return false;
    }
    
    public boolean ConsultarEndereco(int opcao) throws Exception {
        try {
            System.out.println("\n    ***********************************");
            System.out.println("           Consultar endereco");
            System.out.println("    ***********************************\n");
            
            Funcionario funcionario = new Funcionario();
            System.out.println("Digite a matricula do funcionario: ");
            String matricula = scanner.nextLine();
            
            funcionario = funcionarioRepository.consultarPorMatricula(matricula);
            System.out.println("Nome do funcionario: " + funcionario.getNome());
            
            List<Endereco> enderecos = new ArrayList<>();
            enderecos = enderecoRepository.consultarTodosId(funcionario.getId());
            
            Impressao titulo = new Impressao();
            if (opcao == 1) {
                titulo.setCampo1("cep");
                titulo.setCampo2("numero");
                titulo.setCampo3("bairro");
                titulo.setCampo4("cidade");
                titulo.setCampo5("estado");
                titulo.setCampo12("5");
                
                List<Impressao> impressao = new ArrayList<>();
                for (Endereco endereco : enderecos) {
                    Impressao item = new Impressao();
                    item.setCampo1(endereco.getCep());
                    item.setCampo2(endereco.getNumero());
                    item.setCampo3(endereco.getBairro());
                    item.setCampo4(endereco.getCidade());
                    item.setCampo5(endereco.getEstado());
                    item.setCampo12("5");
                    impressao.add(item);
                }
                
                impressaoController.imprimirListaResumido(titulo, impressao);
            }
            else {
                titulo.setCampo1("logradouro");
                titulo.setCampo2("numero");
                titulo.setCampo3("bairro");
                titulo.setCampo4("cidade");
                titulo.setCampo5("estado");
                titulo.setCampo6("complemento");
                titulo.setCampo7("cep");
                titulo.setCampo8("");
                titulo.setCampo9("");
                titulo.setCampo12("7");
                
                List<Impressao> impressao = new ArrayList<>();
                for (Endereco endereco : enderecos) {
                    Impressao item = new Impressao();
                    item.setCampo1(endereco.getLogradouro());
                    item.setCampo2(endereco.getNumero());
                    item.setCampo3(endereco.getBairro());
                    item.setCampo4(endereco.getCidade());
                    item.setCampo5(endereco.getEstado());
                    item.setCampo6(endereco.getComplemento());
                    item.setCampo7(endereco.getCep());
                    item.setCampo8("");
                    item.setCampo9("");
                    titulo.setCampo12("7");
                    impressao.add(item);
                }
                
                impressaoController.imprimirListaDetalhe(titulo, impressao);
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
        
        return false;
    }
    
    public boolean consultarTodos(int opcao) {
        try {
            System.out.println("\n    ***********************************");
            System.out.println("         Consultar todos endereco");
            System.out.println("    ***********************************");
            
            Impressao titulo = new Impressao();
            List<FunEndereco> funEnderecos = new ArrayList<>();
            funEnderecos = funEnderecoRepository.consultarTodos();
            
            if (opcao == 1) {
                titulo.setCampo1("matricula");
                titulo.setCampo2("nome");
                titulo.setCampo3("estado");
                titulo.setCampo4("cep");
                titulo.setCampo5("numero");
                titulo.setCampo12("5");
                
                List<Impressao> impressao = new ArrayList<>();
                for (FunEndereco funEndereco : funEnderecos) {
                    Impressao item = new Impressao();
                    item.setCampo1(funEndereco.getMatricula());
                    item.setCampo2(funEndereco.getNome());
                    item.setCampo3(funEndereco.getEstado());
                    item.setCampo4(funEndereco.getCep());
                    item.setCampo5(funEndereco.getNumero());
                    item.setCampo12("5");
                    impressao.add(item);
                }
                
                impressaoController.imprimirListaResumido(titulo, impressao);
            }
            else {
                titulo.setCampo1("matricula");
                titulo.setCampo2("nome");
                titulo.setCampo3("logradouro");
                titulo.setCampo4("numero");
                titulo.setCampo5("bairro");
                titulo.setCampo6("cidade");
                titulo.setCampo7("estado");
                titulo.setCampo8("cep");
                titulo.setCampo9("");
                titulo.setCampo10("");
                titulo.setCampo11("");
                titulo.setCampo12("8");
                
                List<Impressao> impressao = new ArrayList<>();
                for (FunEndereco funEndereco : funEnderecos) {
                    Impressao item = new Impressao();
                    item.setCampo1(funEndereco.getMatricula());
                    item.setCampo2(funEndereco.getNome());
                    item.setCampo3(funEndereco.getLogradouro());
                    item.setCampo4(funEndereco.getNumero());
                    item.setCampo5(funEndereco.getBairro());
                    item.setCampo6(funEndereco.getCidade());
                    item.setCampo7(funEndereco.getEstado());
                    item.setCampo8(funEndereco.getCep());
                    item.setCampo12("8");
                    impressao.add(item);
                }
                
                impressaoController.imprimirListaDetalhe(titulo, impressao);
            }
            
            return true;
        } catch (Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
        return false;
    }
}
