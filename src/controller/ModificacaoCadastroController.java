package controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Writer;

import model.Cliente;
import model.Lista;

public class ModificacaoCadastroController implements ICadastroController{

	@Override
	public void novoCadastro(int idadeMin, int idadeMax, double limiteCredito) throws Exception {
		Lista clientes = new Lista();
		
		// Bloco para detectar o sistema operacional
		String temp;
		if(System.getProperty("os.name").contains("Windows")) {
			temp = "C:\\TEMP";
		}else {
			temp = "/tmp";
		}
		
		File arquivo = new File(temp, "cadastro.csv");
		Cliente cliente = new Cliente("","",0,0);
		
		//Inicio da leitura do arquivo cadastro.csv, e escrita em um novo arquivo
		//Escrita: realizada quando a idade de um cliente estiver entre idadeMin e idadeMax, e o seu limite for maior que o limite recebido
		if(arquivo.exists() && arquivo.isFile()) {
			FileInputStream fluxo = new FileInputStream(arquivo);
			InputStreamReader leitor = new InputStreamReader(fluxo);
			BufferedReader buffer = new BufferedReader(leitor);
			String linha = buffer.readLine();
			linha = buffer.readLine();
			while(linha!=null) {
				String[] lista = linha.split(";");
				cliente = new Cliente(lista[0], lista[1], Integer.parseInt(lista[2]), Double.parseDouble(lista[3]));
				
				if(cliente.getIdade() >= idadeMin && cliente.getIdade() <= idadeMax && cliente.getLimiteCredito() > limiteCredito) {
					try {
						if(clientes.isEmpty()) {
							clientes.addFirst(cliente);
						}else {
							clientes.addLast(cliente);
						}
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				linha = buffer.readLine();
			}
			buffer.close();
			fluxo.close();
			leitor.close();
			novoArquivo(clientes, "cadastro - Entre "+idadeMin+" a "+idadeMax+"anos.csv");
		}else {
			throw new Exception("Arquivo principal faltando");
		}
	}
	
	public void novoArquivo(Lista l,  String nomeArquivo) throws IOException {
		
		boolean exists = false;
		
		//Bloco para verificar o sistema operacional
		String temp;
		if(System.getProperty("os.name").contains("Windows")) {
			temp = "C:\\TEMP";
		}else {
			temp = "/tmp";
		}

		File dir = new File(temp);
		if(!dir.exists()) {
			dir.mkdirs();
		}
		File arquivo = new File(temp, nomeArquivo);
		if(arquivo.exists()) {
			exists = true;
		}
		int counter = 0;
		String buffer = "";
		FileWriter escrita = new FileWriter(arquivo, exists);
		PrintWriter print = new PrintWriter(escrita);
		Cliente aux = null;
		String header = "CPF;Nome;Idade;Limite de Credito\n";
		
		print.write(header);
		print.flush();
		
		while(counter!=l.size()) {
			try {
				aux = (Cliente) l.get(counter);
				buffer = (aux.getCPF()+";"+aux.getNome()+";"+aux.getIdade()+";"+aux.getLimiteCredito()+"\n");
				counter++;
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			print.write(buffer);
			print.flush();
		}
		escrita.close();
		print.close();
	}
	
	public void cadastroInit(Lista l) throws Exception {
		
		String temp;
		if(System.getProperty("os.name").contains("Windows")) {
			temp = "C:\\TEMP";
		}else {
			temp = "/tmp";
		}
		File arquivo = new File(temp, "cadastro.csv");
		if(!arquivo.exists()) {
			Cliente[] arquivoCadastro = {
					new Cliente("54707521304", "Cliente A", 45, 11108.00),
					new Cliente("19003628372", "Cliente B", 41, 9756.00),
					new Cliente("54446710134", "Cliente C", 33, 7217.00),
					new Cliente("93126907239", "Cliente D", 40, 14353.00),
					new Cliente("34935227995", "Cliente E", 30, 7342.00),
					new Cliente("26679245568", "Cliente F", 53, 9491.00),
					new Cliente("47817135372", "Cliente G", 31, 5244.00),
					new Cliente("88160205180", "Cliente H", 31, 12817.00),
					new Cliente("13667799861", "Cliente I", 28, 12686.00),
					new Cliente("27977151616", "Cliente J", 21, 11092.00),
					new Cliente("56634688310", "Cliente K", 18, 7597.00),
					new Cliente("77407950202", "Cliente L", 45, 8877.00),
					new Cliente("76113821167", "Cliente M", 26, 3865.00),
					new Cliente("60505933383", "Cliente N", 18, 3978.00),
					new Cliente("38134578281", "Cliente O", 26, 9574.00),
					new Cliente("39557597722", "Cliente P", 27, 6476.00),
					new Cliente("43535332258", "Cliente Q", 34, 3776.00),
					new Cliente("13897841035", "Cliente R", 32, 7964.00),
					new Cliente("22264929711", "Cliente S", 34, 4204.00),
					new Cliente("24246599452", "Cliente T", 31, 4869.00),
			        new Cliente("94461659861", "Cliente U", 22, 12770.00),
			        new Cliente("95029452114", "Cliente V", 49, 11877.00),
			        new Cliente("61587656636", "Cliente W", 43, 4802.00),
			        new Cliente("33660496913", "Cliente X", 46, 8208.00),
			        new Cliente("73014492090", "Cliente Y", 19, 6177.00),
			        new Cliente("46043531381", "Cliente Z", 20, 10641.00)
				};
			
			l.vectorInit(arquivoCadastro, l); // O vectorInit faz parte da biblioteca de listas, para inicializar uma lista a partir de um vetor
			novoArquivo(l, "cadastro.csv");
		}
	}
}
