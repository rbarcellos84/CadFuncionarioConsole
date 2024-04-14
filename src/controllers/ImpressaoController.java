package controllers;

import java.util.List;
import java.util.Objects;
import entities.Impressao;
import interfaces.GenericImpressao;

public class ImpressaoController implements GenericImpressao<Impressao> {
	
    private static String formataString(String str, int tamanhoMaximo) {
        String valor = Objects.requireNonNullElse(str, "");
        
        if (valor.length() > tamanhoMaximo) {
            valor = valor.substring(0, tamanhoMaximo - 3) + "...";
        }
        
        valor = String.format("%-" + tamanhoMaximo + "s", valor);
        
        return valor;
    }
    
    @Override
    public void imprimirMensagem(String mensagem) {
        System.out.println("\nAVISO!");
        System.out.println("    " + mensagem + "\n");
    }
    
    @Override
    public void imprimirMensagemSimples(String mensagem) {
        System.out.println("    " + mensagem + "\n");
    }
    
    @Override
    public void imprimirObjetoDetalhe(Impressao titulo, Impressao objeto) throws Exception {
        if (titulo.getCampo1() != null && !titulo.getCampo1().isEmpty()) {
            System.out.println("    " + titulo.getCampo1() + ": " + objeto.getCampo1());
        }
        
        if (titulo.getCampo2() != null && !titulo.getCampo2().isEmpty()) {
            System.out.println("    " + titulo.getCampo2() + ": " + objeto.getCampo2());
        }
        
        if (titulo.getCampo3() != null && !titulo.getCampo3().isEmpty()) {
            System.out.println("    " + titulo.getCampo3() + ": " + objeto.getCampo3());
        }
        
        if (titulo.getCampo4() != null && !titulo.getCampo4().isEmpty()) {
            System.out.println("    " + titulo.getCampo4() + ": " + objeto.getCampo4());
        }
        
        if (titulo.getCampo5() != null && !titulo.getCampo5().isEmpty()) {
            System.out.println("    " + titulo.getCampo5() + ": " + objeto.getCampo5());
        }
        
        if (titulo.getCampo6() != null && !titulo.getCampo6().isEmpty()) {
            System.out.println("    " + titulo.getCampo6() + ": " + objeto.getCampo6());
        }
        
        if (titulo.getCampo7() != null && !titulo.getCampo7().isEmpty()) {
            System.out.println("    " + titulo.getCampo7() + ": " + objeto.getCampo7());
        }
        
        if (titulo.getCampo8() != null && !titulo.getCampo8().isEmpty()) {
            System.out.println("    " + titulo.getCampo8() + ": " + objeto.getCampo8());
        }
        
        if (titulo.getCampo9() != null && !titulo.getCampo9().isEmpty()) {
            System.out.println("    " + titulo.getCampo9() + ": " + objeto.getCampo9());
        }
    }

    @Override
    public void imprimirListaDetalhe(Impressao titulo, List<Impressao> lista) throws Exception {
    	System.out.println("\nDados da impressao ******************************************");
        for (Impressao impressao : lista) {
            imprimirObjetoDetalhe(titulo, impressao);
            System.out.println("*************************************************************");
        }
        System.out.println(" ");
    }

    @Override
    public void imprimirObjetoResumido(Impressao objeto) throws Exception {
        int tamanho = 0;
        int limite = 125;
        int quantidade = Integer.parseInt(objeto.getCampo12());
        String impressao = "";
        
        if (quantidade > 5)
            quantidade = 5;
        
        tamanho = limite / quantidade;
        
        if (quantidade == 1) {
        	impressao = impressao + formataString(objeto.getCampo1(), tamanho);
        }
        else if (quantidade == 2) {
        	impressao = impressao + formataString(objeto.getCampo1(), tamanho)
                                  + formataString(objeto.getCampo2(), tamanho);
        }
        else if (quantidade == 3) {  
        	impressao = impressao + formataString(objeto.getCampo1(), tamanho)
                                  + formataString(objeto.getCampo2(), tamanho)
                                  + formataString(objeto.getCampo3(), tamanho);
        }
        else if (quantidade == 4) {
        	impressao = impressao + formataString(objeto.getCampo1(), tamanho)
                                  + formataString(objeto.getCampo2(), tamanho)
                                  + formataString(objeto.getCampo3(), tamanho)
                                  + formataString(objeto.getCampo4(), tamanho);
        }
        else {
        	impressao = impressao + formataString(objeto.getCampo1(), tamanho)
                                  + formataString(objeto.getCampo2(), tamanho)
                                  + formataString(objeto.getCampo3(), tamanho)
                                  + formataString(objeto.getCampo4(), tamanho)
                                  + formataString(objeto.getCampo5(), tamanho);
        }
        System.out.println(impressao);
    }

    @Override
    public void imprimirListaResumido(Impressao titulo, List<Impressao> lista) throws Exception {
    	System.out.println("\nDados da impressao *********************************************************************************************************");
        imprimirObjetoResumido(titulo);
        System.out.println("****************************************************************************************************************************");
        for (Impressao impressao : lista) {
            imprimirObjetoResumido(impressao);
        }
        System.out.println("****************************************************************************************************************************");
    }
}
