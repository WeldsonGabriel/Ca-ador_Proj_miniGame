package model.personagem;

import java.util.ArrayList;
import java.util.List;
import model.item.Item;
import util.TextoFormatador;

/**
 * Representa o caçador controlado pelo jogador.
 * Possui atributos de combate, inventário e sistema de progressão.
 */
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

    // ===========================
    // ===== CONSTRUTOR =========
    // ===========================
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

        TextoFormatador.info("🏹 " + nome + " ganhou " + (int) xpGanho + " XP.");

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
            TextoFormatador.alerta("⚠️ Inventário cheio! Considere descartar ou usar um item.");
        }
    }

    public void equiparItem(Item item) {
        if (item.getTipo().equalsIgnoreCase("Arma")) {
            this.armaEquipada = item;
        } else if (item.getTipo().equalsIgnoreCase("Armadura")) {
            this.armaduraEquipada = item;
        } else if (item.getTipo().equalsIgnoreCase("Item Especial")) {
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

        TextoFormatador.linha();
        TextoFormatador.info("🐾 Enfrentando " + nomeAnimal + "...");
        TextoFormatador.info("⚔️ Poder do caçador: " + poderCacador + " | 🦴 Poder do animal: " + poderAnimal);

        boolean venceu = Math.random() < chanceVitoria;

        if (venceu) {
            int xpGanho = (int) (20 + Math.random() * (poderAnimal + nivel));
            TextoFormatador.sucesso("✅ Vitória! " + nome + " derrotou " + nomeAnimal + " e ganhou " + xpGanho + " XP!");
            ganharExperiencia(xpGanho);

            // Pequena chance de recuperar vida após vitória
            if (Math.random() < 0.25) {
                int cura = (int) (vidaMaxima * 0.15);
                vidaAtual = Math.min(vidaMaxima, vidaAtual + cura);
                TextoFormatador.info("❤️ Recuperou " + cura + " de vida após a batalha!");
            }

            return true;
        } else {
            int danoBase = 10 + (int) (Math.random() * poderAnimal);
            int danoFinal = Math.max(5, danoBase - (agilidade / 3)); // agilidade reduz dano
            vidaAtual -= danoFinal;

            if (vidaAtual > 0) {
                TextoFormatador.alerta("⚠️ Derrota! Sofreu " + danoFinal + " de dano. Vida atual: " + vidaAtual + "/" + vidaMaxima);
            } else {
                vidaAtual = 0;
                TextoFormatador.erro("💀 " + nome + " foi derrotado em batalha!");
                morrer();
            }

            return false;
        }
    }

    // ===========================
// ===== SISTEMA DE MORTE ====
// ===========================
    private void morrer() {
        TextoFormatador.linha();
        TextoFormatador.erro("☠️ GAME OVER — " + nome + " caiu em batalha!");
        TextoFormatador.alerta("💤 Revivendo...");

        try {
            Thread.sleep(2000); // pausa simbólica de 2 segundos
        } catch (InterruptedException ignored) {}

        reiniciarAposMorte();
    }

    // ===========================
// ===== REINICIAR ===========
    private void reiniciarAposMorte() {
        nivel = Math.max(1, nivel - 1); // perde 1 nível
        experiencia = 0;
        vidaAtual = vidaMaxima;
        TextoFormatador.sucesso("✨ " + nome + " renasceu no nível " + nivel + " com vida restaurada!");
    }


    // ===========================
    // ===== STATUS ==============
    // ===========================
    public void exibirStatus() {
        TextoFormatador.linha();
        System.out.println("🧍‍♂️ Caçador: " + nome);
        System.out.println("⭐ Nível: " + nivel + " | XP: " + (int) experiencia + "/" + (int) experienciaNecessaria);
        System.out.println("💪 Força: " + forca + " | ⚡ Agilidade: " + agilidade + " | 🧠 Inteligência: " + inteligencia);
        System.out.println("❤️ Vida: " + vidaAtual + "/" + vidaMaxima);
        System.out.println("⚔️ Arma: " + (armaEquipada != null ? armaEquipada.getNome() : "Nenhuma"));
        System.out.println("🛡️ Armadura: " + (armaduraEquipada != null ? armaduraEquipada.getNome() : "Nenhuma"));
        System.out.println("🔮 Item Especial: " + (itemEspecial != null ? itemEspecial.getNome() : "Nenhum"));
        TextoFormatador.linha();
    }

    // ===========================
    // ===== GETTERS ============
    // ===========================
    public String getNome() { return nome; }
    public int getNivel() { return nivel; }
    public int getVidaAtual() { return vidaAtual; }
    public int getVidaMaxima() { return vidaMaxima; }

    public int getForca() { return forca; }
    public int getAgilidade() { return agilidade; }
    public int getInteligencia() { return inteligencia; }

    public double getExperiencia() { return experiencia; }
    public double getExperienciaNecessaria() { return experienciaNecessaria; }

    public List<Item> getItens() { return inventario; }

    public Item getArmaEquipada() { return armaEquipada; }
    public Item getArmaduraEquipada() { return armaduraEquipada; }
    public Item getItemEspecial() { return itemEspecial; }

    public Object getXpAtual() {
        return experiencia;
    }

    public void receberDano(int danoAnimal) {
        this.vidaAtual -= danoAnimal;
    }

    public void reviver() {
        this.vidaAtual = this.vidaMaxima;
    }

    public void resetarStatus() {
        this.nivel = 1;
        this.experiencia = 0;
        this.experienciaNecessaria = 100;
        this.forca = 5;
        this.agilidade = 5;
        this.inteligencia = 5;
        this.vidaMaxima = 100;
        this.vidaAtual = vidaMaxima;
        this.inventario.clear();
        this.armaEquipada = null;
        this.armaduraEquipada = null;
        this.itemEspecial = null;
    }
}
