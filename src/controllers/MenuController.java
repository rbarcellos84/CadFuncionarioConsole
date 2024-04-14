package controllers;

import java.io.IOException;
import java.util.Scanner;

public class MenuController {
    private Scanner scanner = new Scanner(System.in);
    
    public void tituloPrograma() {
        System.out.println("\n    ***********************************");
        System.out.println("          Cadastro de funcionarios");
        System.out.println("    ***********************************\n");
    }
    
    public void opcoesInvalida() {
        System.out.println("\n    ***********************************");
        System.out.println("             Opcao invalida");
        System.out.println("    ***********************************\n");
    }
    
    public void pauser() {
    	try {
    		scanner.nextLine();
    		System.out.println("Press Enter to continue...");
    		@SuppressWarnings("unused")
			String linha = scanner.nextLine();
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
    }
    
    public void limparTela() throws IOException {
        //metodo que deveria limpar a tela, mas nao obtive sucesso. estou deixando o codigo para quem souber resolver o problema.
        //if (System.getProperty("os.name").contains("Windows")) {
        //    Runtime.getRuntime().exec("cls");
        //} else {
        //    Runtime.getRuntime().exec("clear");
        //}
        
        for (int i = 0; i < 100; i++) {
            System.out.println();
        }
    }
    
    public int opcoesPrograma() {
        int opcao = 0;
        System.out.println("\n    ***********************************");
        System.out.println("              Menu de opcoes");
        System.out.println("    ***********************************");
        System.out.println("      0 - Sair do programa");
        System.out.println("      1 - Cadastrar funcionario");
        System.out.println("      2 - Consultar funcionario");
        System.out.println("      3 - Atualizar funcionario");
        System.out.println("      4 - Listar todos os funcionario");
        System.out.println("      5 - Cadastrar endereco");
        System.out.println("      6 - Atualizar endereco");
        System.out.println("      7 - Listar endereco");
        System.out.println("      8 - Listar todos os enderecos");
        System.out.println("      9 - Repetir as opcaes do menu");
        System.out.println("    ***********************************\n");
        
        try {
            System.out.println("      Escolha uma opcao:");
            opcao = scanner.nextInt();
            return opcao;
        } catch (Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
        
        return 0;
    }
    
    public int opcoesImpressao() {
        int opcao = 0;
        System.out.println("\n    ***********************************");
        System.out.println("            Menu de impressao");
        System.out.println("    ***********************************");
        System.out.println("      0 - Voltar para menu inicial");
        System.out.println("      1 - Resumo");
        System.out.println("      2 - Detalhado");
        System.out.println("    ***********************************\n");
        
        try {
            System.out.println("      Escolha uma opcao:");
            opcao = scanner.nextInt();
            return opcao;
        } catch (Exception e) {
            System.out.println("\nErro: " + e.getMessage());
        }
        
        return 0;
    }
}
