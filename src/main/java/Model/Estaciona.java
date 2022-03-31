package Model;

import java.util.Date;

public class Estaciona {
	private int id;
	private Date entrada;
	private String placa;
	private Date saida;
	private int pagamento;
	private int livre;
	
	
	public Estaciona() {
		super();
	}
	
	public Estaciona(String placa) {
		this.entrada = new Date();
		this.placa = placa;
	}
	
	public Estaciona(int id) {
		this.id = id;
		this.saida = new Date();
		this.livre = 1;
	}
	
	public Estaciona(int id, int pagamento) {
		this.id = id;
		this.pagamento = pagamento;
	}

	public Estaciona(Integer id, Date entrada, String placa, Date saida, int pagamento, int livre) {
		this.id = id;
		this.entrada = entrada;
		this.placa = placa;
		this.saida = saida;
		this.pagamento = pagamento;
		this.livre = livre;
	}


	public Date getEntrada() {
		return entrada;
	}


	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public Date getSaida() {
		return saida;
	}


	public void setSaida(Date saida) {
		this.saida = saida;
	}


	public int getPagamento() {
		return pagamento;
	}


	public void setPagamento(int pagamento) {
		this.pagamento = pagamento;
	}


	public int getLivre() {
		return livre;
	}


	public void setLivre(int livre) {
		this.livre = livre;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
}
