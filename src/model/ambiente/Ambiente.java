package model.ambiente;

public class Ambiente {
    private String nome;
    private String tipo; // Ex: floresta, deserto, montanha
    private double multiplicadorXp;
    private double penalidade; // afeta atributos do caçador

    public Ambiente(String nome, String tipo, double multiplicadorXp, double penalidade) {
        this.nome = nome;
        this.tipo = tipo;
        this.multiplicadorXp = multiplicadorXp;
        this.penalidade = penalidade;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMultiplicadorXp() {
        return multiplicadorXp;
    }

    public double getPenalidade() {
        return penalidade;
    }

    @Override
    public String toString() {
        return "🌍 Ambiente: " + nome +
                " (" + tipo + ") | XP x" + multiplicadorXp +
                " | Penalidade: " + (int)(penalidade * 100) + "%";
    }
}
