package util;

public class Caminhao extends Veiculo {

	public String getCarroceria() {
		return carroceria;
	}
	public void setCarroceria(String carroceria) {
		this.carroceria = carroceria;
	}
	public String getEixo() {
		return eixo;
	}
	public void setEixo(String eixo) {
		this.eixo = eixo;
	}
	public String getMarcaMotor() {
		return marcaMotor;
	}
	public void setMarcaMotor(String marcaMotor) {
		this.marcaMotor = marcaMotor;
	}
	private String carroceria;
	private String eixo;
	private String marcaMotor;

}