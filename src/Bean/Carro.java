package Bean;

public class Carro {

    private String cor;
    private String ano;
    private String modelo;

    public Carro(String ano, String cor, String modelo) {

        this.ano = ano;
        this.cor = cor;
        this.modelo = modelo;

    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}