package model;

import java.util.Random;

public class Ambiente {
    private String nome;
    private String efeito;
    private double bonusForca;
    private double bonusVelocidade;
    private double bonusInteligencia;

    private static final String[][] AMBIENTES = {
            {"Floresta", "Aumenta velocidade dos animais", "0.0", "0.15", "0.0"},
            {"Montanha", "Aumenta força dos caçadores", "0.15", "0.0", "0.0"},
            {"Deserto", "Favorece inteligência dos animais", "0.0", "0.0", "0.15"},
            {"Pântano", "Reduz velocidade de todos", "-0.10", "-0.10", "-0.10"}
    };

    public Ambiente() {
        Random random = new Random();
        int index = random.nextInt(AMBIENTES.length);
        this.nome = AMBIENTES[index][0];
        this.efeito = AMBIENTES[index][1];
        this.bonusForca = Double.parseDouble(AMBIENTES[index][2]);
        this.bonusVelocidade = Double.parseDouble(AMBIENTES[index][3]);
        this.bonusInteligencia = Double.parseDouble(AMBIENTES[index][4]);
    }

    public String getNome() { return nome; }
    public String getEfeito() { return efeito; }
    public double getBonusForca() { return bonusForca; }
    public double getBonusVelocidade() { return bonusVelocidade; }
    public double getBonusInteligencia() { return bonusInteligencia; }

    public void mostrarAmbiente() {
        System.out.printf("""
                \n🌍 Ambiente: %s
                Efeito: %s
                """, nome, efeito);
    }
}
