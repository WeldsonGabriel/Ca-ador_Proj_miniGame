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

    // **campo para life-steal**
    private int vidaRouboPercent = 0; // soma de bônus de itens

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
            // calcula dano causado pelo caçador (baseado no poder)
            int danoCausado = (int) (poderCacador * (0.4 + Math.random() * 0.6));
            int xpGanho = (int) (20 + Math.random() * (poderAnimal + nivel));
            TextoFormatador.sucesso("✅ Vitória! " + nome + " derrotou " + nomeAnimal + " e ganhou " + xpGanho + " XP!");
            ganharExperiencia(xpGanho);

            // **aplica roubo de vida (life-steal)**
            if (vidaRouboPercent > 0) {
                int cura = (int) Math.round(danoCausado * (vidaRouboPercent / 100.0));
                int antes = vidaAtual;
                vidaAtual = Math.min(vidaMaxima, vidaAtual + cura);
                TextoFormatador.info("吸 " + " (Roubo de vida) Recuperou " + cura + " HP (" + antes + " -> " + vidaAtual + ")");
            }

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

        System.out.println("❤️ Vida: [" + barra + "] " + vidaAtual + "/" + vidaMaxima +
                (vidaRouboPercent > 0 ? " | LifeSteal: " + vidaRouboPercent + "%" : ""));
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
        vidaRouboPercent = 0; // zera o life-steal ao renascer (se preferir manter, remova esta linha)
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
    // ===== ITENS / INVENTÁRIO ==
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

        // aplica bônus de atributos
        item.aplicarBonus(this);

        // aplica vidaRoubo (acumula)
        try {
            this.vidaRouboPercent += item.getVidaRouboPercent();
            if (item.getVidaRouboPercent() > 0) {
                TextoFormatador.info("✨ LifeSteal adquirido: +" + item.getVidaRouboPercent() + "% (total: " + vidaRouboPercent + "%)");
            }
        } catch (Exception ignored) {}

        TextoFormatador.sucesso("⚙️ " + nome + " equipou: " + item.getNome());
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

    // getters necessários pelo StatusExibidor etc.
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

    // método que o Item.aplicarBonus utiliza
    public void aumentarAtributos(int bonusForca, int bonusAgilidade, int bonusInteligencia) {
        this.forca += bonusForca;
        this.agilidade += bonusAgilidade;
        this.inteligencia += bonusInteligencia;
        TextoFormatador.sucesso("💎 Atributos aumentados: +"
                + bonusForca + " Força, +" + bonusAgilidade + " Agilidade, +" + bonusInteligencia + " Inteligência!");
    }

    // método de exibição do caçador (mantive simples)
    public void exibirStatus() {
        TextoFormatador.linha();
        System.out.println("🧍‍♂️ Caçador: " + nome);
        System.out.println("⭐ Nível: " + nivel + " | XP: " + (int) experiencia + "/" + (int) experienciaNecessaria);
        System.out.println("💪 Força: " + forca + " | ⚡ Agilidade: " + agilidade + " | 🧠 Inteligência: " + inteligencia);
        exibirBarraVida();
        TextoFormatador.linha();
    }

    public void receberDano(int danoAnimal) {
        vidaAtual -= danoAnimal;
        vidaAtual = Math.max(0, vidaAtual);
    }

    public void reviver() {
        vidaAtual = vidaMaxima;
        TextoFormatador.sucesso("✨ " + nome + " foi revivido com vida total!");
    }

    public void resetarStatus() {
        nivel = 1;
        experiencia = 0;
        experienciaNecessaria = 100;
        forca = 5;
        agilidade = 5;
        inteligencia = 5;
        vidaMaxima = 100;
        vidaAtual = vidaMaxima;
        inventario.clear();
        armaEquipada = null;
        armaduraEquipada = null;
        itemEspecial = null;
        vidaRouboPercent = 0;
        TextoFormatador.sucesso("🔄 " + nome + " teve seu status resetado!");
    }
}
