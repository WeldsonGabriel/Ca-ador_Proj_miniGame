package model.personagem;

import java.util.ArrayList;
import java.util.List;
import model.item.Item;
import util.TextoFormatador;

public class Cacador {
    private String nome;
    private int nivel;
    private double experiencia;
    private double experienciaNecessaria;
    private int forca;
    private int agilidade;
    private int inteligencia;
    private int vidaMaxima;
    private int vidaAtual;

    private List<Item> inventario;
    private Item armaEquipada;
    private Item armaduraEquipada;
    private Item itemEspecial;

    public Cacador(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.experiencia = 0;
        this.experienciaNecessaria = 100;
        this.forca = 5;
        this.agilidade = 5;
        this.inteligencia = 5;
        this.vidaMaxima = 100;
        this.vidaAtual = vidaMaxima;
        this.inventario = new ArrayList<>();
    }

    // ===========================
    // ===== SISTEMA DE XP =======
    // ===========================
    public void ganharExperiencia(double xpGanho) {
        double multiplicador = (itemEspecial != null && itemEspecial.isRaro()) ? 1.25 : 1.0;
        experiencia += xpGanho * multiplicador;

        TextoFormatador.info("🏹 " + nome + " ganhou " + xpGanho + " XP.");

        while (experiencia >= experienciaNecessaria) {
            subirNivel();
        }
    }

    private void subirNivel() {
        experiencia -= experienciaNecessaria;
        nivel++;
        experienciaNecessaria *= 1.35;
        forca += 2;
        agilidade += 2;
        inteligencia += 1;
        vidaMaxima += 20;
        vidaAtual = vidaMaxima;

        TextoFormatador.sucesso("🎯 " + nome + " subiu para o nível " + nivel + "!");
    }

    // ===========================
    // ===== ATRIBUTOS ==========
    // ===========================
    public void aumentarAtributos(int f, int a, int i) {
        this.forca += f;
        this.agilidade += a;
        this.inteligencia += i;
    }

    public int getPoderTotal() {
        int bonus = 0;
        if (armaEquipada != null) bonus += armaEquipada.isRaro() ? 5 : 2;
        if (armaduraEquipada != null) bonus += armaduraEquipada.isRaro() ? 3 : 1;
        return forca + agilidade + inteligencia + bonus;
    }

    // ===========================
    // ===== INVENTÁRIO =========
    // ===========================
    public void adicionarItem(Item item) {
        if (inventario.size() < 15) {
            inventario.add(item);
            TextoFormatador.sucesso("💼 Item adicionado: " + item.getNome());
        } else {
            TextoFormatador.alerta("Inventário cheio! Considere descartar ou usar um item.");
        }
    }

    public void equiparItem(Item item) {
        if (item.getTipo().equals("Arma")) {
            this.armaEquipada = item;
        } else if (item.getTipo().equals("Armadura")) {
            this.armaduraEquipada = item;
        } else if (item.getTipo().equals("Item Especial")) {
            this.itemEspecial = item;
        }

        item.aplicarBonus(this);
        TextoFormatador.sucesso("⚙️ " + nome + " equipou: " + item.getNome());
    }

    // ===========================
    // ===== SISTEMA DE COMBATE ==
    // ===========================
    public boolean atacar(String nomeAnimal, int poderAnimal) {
        int poderCacador = getPoderTotal();
        double chanceVitoria = (double) poderCacador / (poderCacador + poderAnimal);

        TextoFormatador.info("🐾 Enfrentando " + nomeAnimal + "...");
        TextoFormatador.info("⚔️ Poder do caçador: " + poderCacador + " | 🦴 Poder do animal: " + poderAnimal);

        if (Math.random() < chanceVitoria) {
            TextoFormatador.sucesso("✅ Vitória contra " + nomeAnimal + "!");
            ganharExperiencia(30 + Math.random() * poderAnimal);
            return true;
        } else {
            int dano = (int) (10 + Math.random() * poderAnimal);
            vidaAtual -= dano;
            if (vidaAtual <= 0) {
                vidaAtual = vidaMaxima / 2; // revive com metade
                TextoFormatador.erro("💀 Você foi derrotado e recarregou com metade da vida!");
            } else {
                TextoFormatador.alerta("⚠️ Sofreu " + dano + " de dano. Vida atual: " + vidaAtual);
            }
            return false;
        }
    }

    // ===========================
    // ===== STATUS ==============
    // ===========================
    public void exibirStatus() {
        System.out.println("══════════════════════════════");
        System.out.println("🧍‍♂️ Caçador: " + nome);
        System.out.println("Nível: " + nivel + " | XP: " + (int) experiencia + "/" + (int) experienciaNecessaria);
        System.out.println("Força: " + forca + " | Agilidade: " + agilidade + " | Inteligência: " + inteligencia);
        System.out.println("Vida: " + vidaAtual + "/" + vidaMaxima);
        System.out.println("Arma: " + (armaEquipada != null ? armaEquipada.getNome() : "Nenhuma"));
        System.out.println("Armadura: " + (armaduraEquipada != null ? armaduraEquipada.getNome() : "Nenhuma"));
        System.out.println("Item Especial: " + (itemEspecial != null ? itemEspecial.getNome() : "Nenhum"));
        System.out.println("══════════════════════════════");
    }

    // ===========================
    // ===== GETTERS ============
    // ===========================
    public String getNome() { return nome; }
    public int getNivel() { return nivel; }
    public int getVidaAtual() { return vidaAtual; }
    public int getVidaMaxima() { return vidaMaxima; }
}
