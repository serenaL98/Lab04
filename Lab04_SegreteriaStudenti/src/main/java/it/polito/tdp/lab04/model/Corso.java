package it.polito.tdp.lab04.model;

public class Corso {
	
	private String codins;
	private Integer numeroCrediti;
	private String nome;
	private Integer periodoDidattico;
	
	/**
	 * @param codins
	 * @param numeroCrediti
	 * @param nome
	 * @param periodoDidattico
	 */
	public Corso(String codins, Integer numeroCrediti, String nome, Integer periodoDidattico) {
		super();
		this.codins = codins;
		this.numeroCrediti = numeroCrediti;
		this.nome = nome;
		this.periodoDidattico = periodoDidattico;
	}
	
	public String getCodins() {
		return codins;
	}
	public void setCodins(String codins) {
		this.codins = codins;
	}
	public Integer getNumeroCrediti() {
		return numeroCrediti;
	}
	public void setNumeroCrediti(Integer numeroCrediti) {
		this.numeroCrediti = numeroCrediti;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getPeriodoDidattico() {
		return periodoDidattico;
	}
	public void setPeriodoDidattico(Integer periodoDidattico) {
		this.periodoDidattico = periodoDidattico;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codins == null) ? 0 : codins.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Corso other = (Corso) obj;
		if (codins == null) {
			if (other.codins != null)
				return false;
		} else if (!codins.equals(other.codins))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
		}

	public String toLongerString() {
		String s = String.format("%-15s %-10s %-50s %-15s", codins, numeroCrediti, nome, periodoDidattico);
		return s;
	}

}