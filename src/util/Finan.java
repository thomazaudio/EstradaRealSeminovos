package util;

/**
 * Classe para opera��es financeiras
 */
public class Finan {

	private double saldo;

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	/**
	 * 
	 * @param valor
	 */
	public boolean depositar(double valor) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param valor
	 */
	public boolean pagar(double valor) {
		throw new UnsupportedOperationException();
	}

}