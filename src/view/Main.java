package view;

import java.io.File;
import java.io.IOException;
import controller.ModificacaoCadastroController;
import model.Cliente;
import model.Lista;

public class Main {
	public static void main(String[] args) throws Exception {
		
		ModificacaoCadastroController cadastro = new ModificacaoCadastroController();
		Lista l = new Lista();
		
		cadastro.cadastroInit(l); //verifica se o arquivo original existe, se não existir, ele é criado

		try {
			cadastro.novoCadastro(41, 60, 8000.00);
			cadastro.novoCadastro(31, 40, 5000.00);
			cadastro.novoCadastro(21, 30, 3000.00);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
