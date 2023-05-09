package model;

public class Cliente {
	
	String CPF;
	String Nome;
	int Idade;
	double LimiteCredito;
	
	public Cliente(String CPF, String Nome, int Idade, double LimiteCredito) {
		this.CPF = CPF;
		this.Nome = Nome;
		this.Idade = Idade;
		this.LimiteCredito = LimiteCredito;
	}
	
	public String getCPF() {
		return CPF;
	}
	
	public void setCPF(String CPF) {
		this.CPF = CPF;
	}
	
	public String getNome() {
		return Nome;
	}
	
	public void setNome(String Nome) {
		this.Nome = Nome;
	}
	
	public int getIdade() {
		return Idade;
	}
	
	public void setIdade(int Idade) {
		this.Idade = Idade;
	}
	
	public double getLimiteCredito() {
		return LimiteCredito;
	}
	
	public void setLimiteCredito(double LimiteCredito) {
		this.LimiteCredito = LimiteCredito;
	}
}
