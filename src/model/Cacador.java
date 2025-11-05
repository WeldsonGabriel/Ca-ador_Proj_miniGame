package model;

import java.util.Random;
import java.util.Scanner;

public class Cacador {
    private String nome;
    private int forca;
    private int velocidade;
    private int inteligencia;
    private int nivel;
    private int xp;

    public Cacador(String nome) {
        this.nome = nome;
        this.nivel = 1;
        this.xp = 0;
        gerarAtributosAleatorios();
    }

    private void gerarAtributosAleatorios() {
        Random random = new Random();
        this.forca = random.nextInt(41) + 40;       // 40–80
        this.velocidade = random.nextInt(41) + 40;
        this.inteligencia = random.nextInt(41) + 40;
    }

    public void mostrarStatus() {
        System.out.printf("""
                
                🧍‍♂️ Status do Caçador: %s
                Nível: %d | XP: %d
                Força: %d
                Velocidade: %d
                Inteligência: %d
                """, nome, nivel, xp, forca, velocidade, inteligencia);
    }

    public void ganharXP(int quantidade) {
        this.xp += quantidade;
        if (this.xp >= 100) {
            subirNivel();
            this.xp -= 100;
        }
    }

    private void subirNivel() {
        nivel++;
        System.out.printf("✨ Parabéns! %s subiu para o nível %d!%n", nome, nivel);
        Scanner sc = new Scanner(System.in);
        System.out.println("Escolha um atributo para melhorar:");
        System.out.println("1️⃣ Força");
        System.out.println("2️⃣ Velocidade");
        System.out.println("3️⃣ Inteligência");
        System.out.print("Escolha: ");
        int opcao = sc.nextInt();

        switch (opcao) {
            case 1 -> { forca += 10; System.out.println("💪 Força aumentada!"); }
            case 2 -> { velocidade += 10; System.out.println("⚡ Velocidade aumentada!"); }
            case 3 -> { inteligencia += 10; System.out.println("🧠 Inteligência aumentada!"); }
            default -> System.out.println("Opção inválida, nenhum atributo foi alterado.");
        }
    }

    public int getForca() { return forca; }
    public int getVelocidade() { return velocidade; }
    public int getInteligencia() { return inteligencia; }
    public String getNome() { return nome; }
}
