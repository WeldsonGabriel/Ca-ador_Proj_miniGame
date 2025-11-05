package app;

import model.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Digite o nome do seu caçador: ");
        String nome = sc.nextLine();
        Cacador cacador = new Cacador(nome);

        boolean jogando = true;

        while (jogando) {
            System.out.println("\n===== 🌲 CAÇADA SELVAGEM =====");
            System.out.println("1️⃣ Procurar Animal");
            System.out.println("2️⃣ Mostrar Status do Caçador");
            System.out.println("3️⃣ Sair");
            System.out.print("Escolha: ");
            int opcao = sc.nextInt();

            switch (opcao) {
                case 1 -> {
                    Ambiente ambiente = new Ambiente();
                    ambiente.mostrarAmbiente();

                    Animal animal = new Animal(ambiente);
                    animal.mostrarStatus();

                    System.out.println("\nVocê deseja:");
                    System.out.println("1️⃣ Enfrentar");
                    System.out.println("2️⃣ Fugir");
                    int acao = sc.nextInt();

                    if (acao == 1) {
                        enfrentarAnimal(cacador, animal, ambiente);
                    } else {
                        System.out.println("🏃‍♂️ Você fugiu com segurança!");
                    }
                }

                case 2 -> cacador.mostrarStatus();

                case 3 -> {
                    System.out.println("👋 Fim da caçada. Até a próxima!");
                    jogando = false;
                }

                default -> System.out.println("⚠️ Opção inválida!");
            }
        }

        sc.close();
    }

    private static void enfrentarAnimal(Cacador cacador, Animal animal, Ambiente ambiente) {
        int vantagem = 0;

        if (cacador.getForca() > animal.getForca()) vantagem++;
        if (cacador.getVelocidade() > animal.getVelocidade()) vantagem++;
        if (cacador.getInteligencia() > animal.getInteligencia()) vantagem++;

        if (vantagem >= 2) {
            System.out.printf("🏆 Você derrotou o %s em %s!%n", animal.getEspecie(), ambiente.getNome());
            cacador.ganharXP(50);
        } else {
            System.out.printf("💀 O %s era forte demais no ambiente %s... Você perdeu!%n", animal.getEspecie(), ambiente.getNome());
        }
    }
}
