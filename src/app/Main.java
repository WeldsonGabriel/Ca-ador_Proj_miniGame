package app;

import model.personagem.Cacador;
import model.animal.Animal;
import model.ambiente.Ambiente;
import model.sistema.Batalha;
import model.sistema.GeradorRandom;
import util.TextoFormatador;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        TextoFormatador.linha();
        System.out.println("🏹 Bem-vindo ao Mundo da Caça Selvagem!");
        TextoFormatador.linha();

        System.out.print("Digite o nome do seu caçador: ");
        String nome = scanner.nextLine();

        Cacador cacador = new Cacador(nome);
        GeradorRandom gerador = new GeradorRandom();

        TextoFormatador.sucesso("\nCaçador criado com sucesso!");
        System.out.println(cacador);

        boolean continuar = true;
        while (continuar) {
            TextoFormatador.linha();
            System.out.println("🌍 Gerando ambiente...");
            Ambiente ambiente = gerador.gerarAmbienteAleatorio();

            System.out.println("🐾 Procurando por animais...");
            Animal animal = gerador.gerarAnimalAleatorio(ambiente);

            TextoFormatador.info("Você está em: " + ambiente.getNome());
            TextoFormatador.info("Um " + animal.getNome() + " apareceu!");

            TextoFormatador.linha();
            System.out.println("Escolha uma ação:");
            System.out.println("[1] Atacar");
            System.out.println("[2] Fugir");
            System.out.println("[3] Sair do jogo");

            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> {
                    Batalha batalha = new Batalha();
                    boolean venceu = batalha.iniciar(cacador, animal, ambiente);

                    if (venceu) {
                        TextoFormatador.sucesso("🎉 Você venceu o confronto!");
                    } else {
                        TextoFormatador.erro("💀 O caçador foi derrotado!");
                        continuar = false;
                    }
                }
                case "2" -> TextoFormatador.info("Você fugiu para outro ambiente...");
                case "3" -> {
                    TextoFormatador.erro("Encerrando o jogo...");
                    continuar = false;
                }
                default -> TextoFormatador.erro("Opção inválida!");
            }
        }

        TextoFormatador.linha();
        System.out.println("🏁 Fim da caçada, " + cacador.getNome() + "!");
        scanner.close();
    }
}
