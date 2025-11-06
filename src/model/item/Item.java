package model.item;

import model.personagem.Cacador;

public class Item {
    protected String nome;
    protected String tipo; // arma, armadura, especial
    protected int bonusForca;
    protected int bonusAgilidade;
    protected int bonusInteligencia;
    protected int nivelItem;
    protected boolean raro;

    public Item(String nome, String tipo, int bonusForca, int bonusAgilidade, int bonusInteligencia, int nivelItem, boolean raro) {
        this.nome = nome;
        this.tipo = tipo;
        this.bonusForca = bonusForca;
        this.bonusAgilidade = bonusAgilidade;
        this.bonusInteligencia = bonusInteligencia;
        this.nivelItem = nivelItem;
        this.raro = raro;
    }

    public Item(String essênciaSelvagem, String itemEspecial, boolean b) {
    }

    public void aplicarBonus(Cacador c) {
        c.aumentarAtributos(bonusForca, bonusAgilidade, bonusInteligencia);
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public boolean isRaro() {
        return raro;
    }

    @Override
    public String toString() {
        String raridade = raro ? "Épico" : "Comum";
        return "🔹 Item: " + nome + " [" + tipo + "] - " + raridade +
                "\n   + Força: " + bonusForca +
                " | + Agilidade: " + bonusAgilidade +
                " | + Inteligência: " + bonusInteligencia +
                " | Nível Item: " + nivelItem;
    }
}
