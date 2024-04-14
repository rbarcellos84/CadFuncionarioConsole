package principal;

import controllers.EnderecoController;
import controllers.FuncionarioController;
import controllers.ImpressaoController;
import controllers.MenuController;

public class Main {
	public static void main(String[] args) {
		try {
			MenuController menuController = new MenuController();
			FuncionarioController funcionarioController = new FuncionarioController();
			EnderecoController enderecoController = new EnderecoController();
			ImpressaoController impressaoController = new ImpressaoController();
			
			String mensagem = "";
			
			menuController.tituloPrograma();
			int menu, impressao = 1;
			menu = menuController.opcoesPrograma();
			menuController.limparTela();
			while(menu > 0 && menu <= 9) {
				if ((menu < 0) && (menu > 9)) {
					menuController.opcoesInvalida();
					menuController.pauser();
					menuController.limparTela();
					menu = menuController.opcoesPrograma();
				}
				else {
					if ((menu == 2) || (menu == 4) || (menu == 7) || (menu == 8)) {
						menuController.limparTela();
						impressao = menuController.opcoesImpressao();
					}
					
					if(menu == 1) {
						menuController.limparTela();
						if (funcionarioController.cadastrarFuncionario() == false) {
							mensagem = "Não foi possivel cadastrar o funcionario, tente novamente.";
							impressaoController.imprimirMensagem(mensagem);
						}
						else {
							mensagem = "Funcionario cadastrado com sucesso!";
							impressaoController.imprimirMensagemSimples(mensagem);
						}
					}
					
					if(menu == 2) {
						menuController.limparTela();
						if (funcionarioController.ConsultarFuncionario(impressao) == false) {
							mensagem = "Não foi possivel consultar o funcionario, tente novamente.";
							impressaoController.imprimirMensagem(mensagem);
						}
					}
					
					if(menu == 3) {
						menuController.limparTela();
						if (funcionarioController.AtualizarFuncionario() == false) {
							mensagem = "Não foi possivel alterar o funcionario, tente novamente.";
							impressaoController.imprimirMensagem(mensagem);
						}
						else {
							mensagem = "Funcionario atualizado com sucesso!";
							impressaoController.imprimirMensagemSimples(mensagem);
						}
					}
					
					if(menu == 4) {
						menuController.limparTela();
						if (funcionarioController.consultarTodos(impressao) == false) {
							mensagem = "Não foi possivel listar os funcionarios, tente novamente.";
							impressaoController.imprimirMensagem(mensagem);
						}
					}
					
					if(menu == 5) {
						menuController.limparTela();
						if (enderecoController.cadastrarEndereco() == false) {
							mensagem = "Não foi possivel cadastrar o endereco, tente novamente.";
							impressaoController.imprimirMensagem(mensagem);
						}
						else {
							mensagem = "Endereco cadastrado com sucesso!";
							impressaoController.imprimirMensagemSimples(mensagem);
						}
					}
					
					if(menu == 6) {
						menuController.limparTela();
						if (enderecoController.atualizarEndereco() == false) {
							mensagem = "Não foi possivel atualizar o endereco, tente novamente.";
							impressaoController.imprimirMensagem(mensagem);
						}
						else {
							mensagem = "Endereco atualizado com sucesso!";
							impressaoController.imprimirMensagemSimples(mensagem);
						}
					}
					
					if(menu == 7) {
						menuController.limparTela();
						if (enderecoController.ConsultarEndereco(impressao) == false) {
							mensagem = "Não foi possivel liatar o endereco, tente novamente.";
							impressaoController.imprimirMensagem(mensagem);
						}
					}
					
					if(menu == 8) {
						menuController.limparTela();
						if (enderecoController.consultarTodos(impressao) == false) {
							mensagem = "Não foi possivel listar todos os enderecos, tente novamente.";
							impressaoController.imprimirMensagem(mensagem);
						}
					}
				}
				
				menuController.pauser();
				menuController.limparTela();

				menu = menuController.opcoesPrograma();
				menuController.limparTela();
			}
			
			mensagem = "Obrigado. Ate mais!";
			impressaoController.imprimirMensagemSimples(mensagem);
		} catch (Exception e) {
			System.out.println("\nErro: " + e.getMessage());
		}
	}
}
