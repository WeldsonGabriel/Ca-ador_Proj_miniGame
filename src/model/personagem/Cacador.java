package model.personagem;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
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

    private Scanner scanner = new Scanner(System.in);

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
    // ===== SISTEMA DE COMBATE ==
    // ===========================
    public void iniciarBatalha(String nomeAnimal, int poderAnimal) {
        TextoFormatador.linha();
        TextoFormatador.info("🐾 " + nome + " encontrou um " + nomeAnimal + "!");
        TextoFormatador.info("⚔️ Iniciando batalha...");

        while (vidaAtual > 0) {
            boolean venceu = atacar(nomeAnimal, poderAnimal);
            if (venceu) {
                TextoFormatador.sucesso("🏆 " + nome + " venceu a batalha contra " + nomeAnimal + "!");
                exibirBarraVida();
                perguntarNovaBatalha();
                break;
            } else if (vidaAtual <= 0) {
                TextoFormatador.erro("💀 " + nome + " foi derrotado pelo " + nomeAnimal + "...");
                morrer();
                break;
            }

            try { Thread.sleep(1200); } catch (InterruptedException ignored) {}
        }
    }

    public boolean atacar(String nomeAnimal, int poderAnimal) {
        int poderCacador = getPoderTotal();
        double chanceVitoria = (double) poderCacador / (poderCacador + poderAnimal);

        TextoFormatador.info("\n🗡️ " + nome + " ataca o " + nomeAnimal + "!");
        TextoFormatador.info("⚖️ Chance de vitória: " + (int) (chanceVitoria * 100) + "%");

        animarBatalha();
        exibirBarraVida();

        boolean venceu = Math.random() < chanceVitoria;

        if (venceu) {
            int xpGanho = (int) (20 + Math.random() * (poderAnimal + nivel));
            TextoFormatador.sucesso("✅ Vitória! " + nome + " derrotou " + nomeAnimal + " e ganhou " + xpGanho + " XP!");
            ganharExperiencia(xpGanho);

            if (Math.random() < 0.25) {
                int cura = (int) (vidaMaxima * 0.15);
                vidaAtual = Math.min(vidaMaxima, vidaAtual + cura);
                TextoFormatador.info("❤️ Recuperou " + cura + " de vida após a batalha!");
            }
            return true;
        } else {
            int danoBase = 10 + (int) (Math.random() * poderAnimal);
            int danoFinal = Math.max(5, danoBase - (agilidade / 3));
            vidaAtual -= danoFinal;
            vidaAtual = Math.max(0, vidaAtual);

            TextoFormatador.alerta("⚠️ Sofreu " + danoFinal + " de dano!");
            exibirBarraVida();
            return false;
        }
    }

    // ===========================
    // ===== ANIMAÇÃO ============
    // ===========================
    private void animarBatalha() {
        String[] frames = {"⚔️", "💥", "🩸", "🔥"};
        for (String f : frames) {
            System.out.print("\r" + f + " Lutando...");
            try { Thread.sleep(300); } catch (InterruptedException ignored) {}
        }
        System.out.println();
    }

    // ===========================
    // ===== BARRA DE VIDA =======
    // ===========================
    private void exibirBarraVida() {
        int totalBlocos = 20;
        int blocosCheios = (int) ((vidaAtual / (double) vidaMaxima) * totalBlocos);
        StringBuilder barra = new StringBuilder();

        for (int i = 0; i < totalBlocos; i++) {
            barra.append(i < blocosCheios ? "▰" : "▱");
        }

        System.out.println("❤️ Vida: [" + barra + "] " + vidaAtual + "/" + vidaMaxima);
    }

    // ===========================
    // ===== REINICIAR ===========
    // ===========================
    private void perguntarNovaBatalha() {
        System.out.print("\n⚔️ Deseja iniciar outra batalha? (s/n): ");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("s")) {
            System.out.print("🐗 Escolha um inimigo (nome): ");
            String nomeAnimal = scanner.nextLine();
            int poderAnimal = 20 + (int) (Math.random() * 60);
            iniciarBatalha(nomeAnimal, poderAnimal);
        } else {
            TextoFormatador.info("🌿 " + nome + " decide descansar por enquanto...");
        }
    }

    // ===========================
    // ===== MORTE / RENASCER ====
    // ===========================
    private void morrer() {
        TextoFormatador.linha();
        TextoFormatador.erro("☠️ GAME OVER — " + nome + " caiu em batalha!");
        TextoFormatador.alerta("💤 Revivendo...");

        try { Thread.sleep(2000); } catch (InterruptedException ignored) {}

        reiniciarAposMorte();
        exibirBarraVida();
        perguntarNovaBatalha();
    }

    private void reiniciarAposMorte() {
        nivel = Math.max(1, nivel - 1);
        experiencia = 0;
        vidaAtual = vidaMaxima;
        TextoFormatador.sucesso("✨ " + nome + " renasceu no nível " + nivel + " com vida restaurada!");
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
    // ===== UTILITÁRIOS ========
    // ===========================
    public int getPoderTotal() {
        int bonus = 0;
        if (armaEquipada != null) bonus += armaEquipada.isRaro() ? 5 : 2;
        if (armaduraEquipada != null) bonus += armaduraEquipada.isRaro() ? 3 : 1;
        return forca + agilidade + inteligencia + bonus;
    }

    public void exibirStatus() {
        TextoFormatador.linha();
        System.out.println("🧍‍♂️ Caçador: " + nome);
        System.out.println("⭐ Nível: " + nivel + " | XP: " + (int) experiencia + "/" + (int) experienciaNecessaria);
        System.out.println("💪 Força: " + forca + " | ⚡ Agilidade: " + agilidade + " | 🧠 Inteligência: " + inteligencia);
        exibirBarraVida();
        TextoFormatador.linha();
    }

    public void aumentarAtributos(int bonusForca, int bonusAgilidade, int bonusInteligencia) {
        this.forca += bonusForca;
        this.agilidade += bonusAgilidade;
        this.inteligencia += bonusInteligencia;
    }

    public int getVidaAtual() {
        return vidaAtual;
    }

    public Object getVidaMaxima() {
        return vidaMaxima;
    }

    public void receberDano(int danoAnimal) {
        vidaAtual -= danoAnimal;
        vidaAtual = Math.max(0, vidaAtual);
    }

    public void adicionarItem(Item drop) {
        inventario.add(drop);
    }

    public int getNivel() {
        return nivel;
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
    }

    public String getNome() {
        return nome;
    }

    public Object getExperiencia() {
        return experiencia;
    }

    public Object getExperienciaNecessaria() {
        return experienciaNecessaria;
    }

    public Object getItens() {
        return inventario;
    }

    public Object getForca() {
        return forca;
    }

    public Object getAgilidade() {
        return agilidade;
    }

    public Object getInteligencia() {
        return inteligencia;
    }
}
