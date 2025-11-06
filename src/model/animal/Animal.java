package model.animal;

import model.item.Item;
import java.util.Random;

/**
 * Classe base para todos os animais.
 * Fornece atributos comuns e contratos para subclasses que representam
 * diferentes raridades ou comportamentos (Normal, Raro, Épico).
 */
public abstract class Animal {
    protected String nome;
    protected int nivel;
    protected int forca;
    protected int agilidade;
    protected int inteligencia;
    protected String idade; // Filhote, Adulto, Velho
    protected double raridadeMultiplicador;

    protected Random random = new Random();

    public Animal(String nome, int nivel, String idade) {
        this.nome = nome;
        this.nivel = nivel;
        this.idade = idade;
        gerarAtributosBase();
    }

    /**
     * Gera atributos iniciais com base no nível e na raridade.
     * Cada subclasse pode sobrescrever para modificar o comportamento.
     */
    protected void gerarAtributosBase() {
        int base = 5 * nivel;
        this.forca = base + random.nextInt(6);
        this.agilidade = base + random.nextInt(6);
        this.inteligencia = base + random.nextInt(6);
        this.raridadeMultiplicador = 1.0;
    }

    /**
     * Método de drop — deve ser implementado por subclasses (pode retornar null).
     */
    public abstract Item dropItem();

    /**
     * XP base concedido ao derrotar este animal.
     */
    public double getXpReward() {
        double mediaAtributos = (forca + agilidade + inteligencia) / 3.0;
        return mediaAtributos * raridadeMultiplicador;
    }

    // ============================
    // ==== GETTERS PADRÃO ========
    // ============================
    public String getNome() {
        return nome + " (" + idade + ")";
    }

    public int getNivel() {
        return nivel;
    }

    public int getForca() {
        return forca;
    }

    public int getAgilidade() {
        return agilidade;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public String getIdade() {
        return idade;
    }

    public double getRaridadeMultiplicador() {
        return raridadeMultiplicador;
    }

    @Override
    public String toString() {
        return String.format("%s [Nível %d | Força %d | Agilidade %d | Inteligência %d]",
                getNome(), nivel, forca, agilidade, inteligencia);
    }
}
