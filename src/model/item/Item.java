package model.item;

import model.personagem.Cacador;

/**
 * Representa um item que pode aplicar bônus ao Cacador.
 * Inclui agora vidaRouboPercent (life steal) para armas/amuletos.
 */
public class Item {
    protected String nome;
    protected String tipo; // "Arma", "Armadura", "Item Especial", etc.
    protected int bonusForca;
    protected int bonusAgilidade;
    protected int bonusInteligencia;
    protected int nivelItem;
    protected boolean raro;
    protected int vidaRouboPercent; // novo: porcentagem de life-steal (0 = nenhum)

    public Item(String nome, String tipo, int bonusForca, int bonusAgilidade, int bonusInteligencia,
                int nivelItem, boolean raro) {
        this.nome = nome;
        this.tipo = tipo;
        this.bonusForca = bonusForca;
        this.bonusAgilidade = bonusAgilidade;
        this.bonusInteligencia = bonusInteligencia;
        this.nivelItem = nivelItem;
        this.raro = raro;
        this.vidaRouboPercent = vidaRouboPercent;
    }

    // compatibilidade: construtor simples (mantém existentes)
    public Item(String nome, String tipo, boolean raro) {
        this(nome, tipo, 0, 0, 0, 1, raro);
    }

    // Aplica bônus diretos ao caçador (atributos)
    public void aplicarBonus(Cacador c) {
        // o aumento de atributos fica no Cacador (método aumentarAtributos já deve existir)
        c.aumentarAtributos(bonusForca, bonusAgilidade, bonusInteligencia);
        // vidaRouboPercent será lido pelo Cacador quando equipar o item (ver Cacador.equiparItem)
    }

    public String getNome() { return nome; }
    public String getTipo() { return tipo; }
    public boolean isRaro() { return raro; }
    public int getVidaRouboPercent() { return vidaRouboPercent; }

    @Override
    public String toString() {
        String raridade = raro ? "Épico" : "Comum";
        String steal = vidaRouboPercent > 0 ? " | LifeSteal: " + vidaRouboPercent + "%" : "";
        return "🔹 Item: " + nome + " [" + tipo + "] - " + raridade +
                "\n   + Força: " + bonusForca +
                " | + Agilidade: " + bonusAgilidade +
                " | + Inteligência: " + bonusInteligencia +
                " | Nível Item: " + nivelItem + steal;
    }
}
