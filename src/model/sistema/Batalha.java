package model.sistema;

import model.animal.Animal;
import model.ambiente.Ambiente;
import model.personagem.Cacador;
import model.item.Item;
import util.TextoFormatador;
import util.StatusExibidor;
import java.util.Scanner;

public class Batalha {

    private final Scanner scanner = new Scanner(System.in);

    /**
     * Inicia uma batalha completa entre o caçador e o animal.
     */
    public boolean iniciar(Cacador cacador, Animal animal, Ambiente ambiente) {
        TextoFormatador.linha();
        TextoFormatador.info("⚔️  BATALHA INICIADA!");
        TextoFormatador.info("📍 Local: " + ambiente.getNome());
        TextoFormatador.linha();

        StatusExibidor.exibirStatusCacador(cacador);
        StatusExibidor.exibirStatusAnimal(animal, ambiente);
        esperar(800);

        double poderAnimalBase = calcularPoderAnimalComAmbiente(animal, ambiente);
        double vidaAnimal = 80 + animal.getNivel() * 15;

        TextoFormatador.info(String.format("🐾 Poder inicial do animal: %.1f | ❤️ Vida: %.0f", poderAnimalBase, vidaAnimal));
        TextoFormatador.info(String.format("🧍 Vida do caçador: %d/%d", cacador.getVidaAtual(), cacador.getVidaMaxima()));
        TextoFormatador.linha();

        // === Loop de combate ===
        while (cacador.getVidaAtual() > 0 && vidaAnimal > 0) {
            esperar(1000);
            TextoFormatador.info("⚡ Turno de combate...");

            int poderCacador = obterPoderCacador(cacador);

            // dano base
            double danoCacador = (poderCacador * (0.7 + Math.random() * 0.6));
            double danoAnimal = (poderAnimalBase * (0.5 + Math.random() * 0.8));

            // aplica danos
            vidaAnimal -= danoCacador;
            cacador.receberDano((int) danoAnimal);

            TextoFormatador.info(String.format("🧍 Causou %.0f de dano! 🐾 Sofreu %.0f de dano!", danoCacador, danoAnimal));
            TextoFormatador.info(String.format("❤️ Caçador: %d/%d | 🩸 Animal: %.0f",
                    cacador.getVidaAtual(), cacador.getVidaMaxima(), Math.max(vidaAnimal, 0)));

            TextoFormatador.linha();

            // condições
            if (vidaAnimal <= 0) {
                TextoFormatador.sucesso("🏆 Vitória! Você derrotou o " + animal.getNome() + "!");
                double xpGanho = Nivel.calcularXpGanho(cacador, animal, ambiente);
                cacador.ganharExperiencia(xpGanho);

                Item drop = animal.dropItem();
                if (drop != null) {
                    TextoFormatador.sucesso("🎁 Item obtido: " + drop.getNome());
                    cacador.adicionarItem(drop);
                } else {
                    TextoFormatador.info("🔎 Nenhum item deixado desta vez.");
                }
                break;
            }

            if (cacador.getVidaAtual() <= 0) {
                TextoFormatador.erro("💀 Você foi derrotado!");
                return tratarDerrota(cacador);
            }
        }

        TextoFormatador.linha();
        TextoFormatador.info("📊 Status final da batalha:");
        StatusExibidor.exibirStatusCacador(cacador);
        TextoFormatador.linha();
        return cacador.getVidaAtual() > 0;
    }

    // ===========================
    // ==== Métodos Auxiliares ===
    // ===========================

    private int obterPoderCacador(Cacador cacador) {
        try {
            return (int) (double) cacador.getClass().getMethod("getPoderTotal").invoke(cacador);
        } catch (Exception e) {
            return cacador.getNivel() * 10 + 30;
        }
    }

    private double calcularPoderAnimalComAmbiente(Animal animal, Ambiente ambiente) {
        double mediaAtributos = (animal.getForca() + animal.getAgilidade() + animal.getInteligencia()) / 3.0;
        return mediaAtributos * ambiente.getMultiplicadorXp();
    }

    private void esperar(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Quando o caçador perde a batalha, perde parte da vida total.
     * Se morrer completamente, oferece opção de reviver.
     */
    private boolean tratarDerrota(Cacador cacador) {
        TextoFormatador.alerta("⚠️ Você perdeu esta batalha!");
        int vidaRestante = cacador.getVidaAtual();

        if (vidaRestante <= 0) {
            TextoFormatador.erro("💀 Sua vida chegou a zero!");
            TextoFormatador.alerta("Deseja reviver com metade da vida? (s/n)");

            String resp = scanner.nextLine().trim().toLowerCase();
            if (resp.equals("s")) {
                cacador.reviver();
                TextoFormatador.sucesso("✨ Você reviveu com metade da vida! O jogo continuará de onde parou.");
                return true;
            } else {
                TextoFormatador.alerta("🏚️ Deseja encerrar e reiniciar o jogo? (s/n)");
                String resp2 = scanner.nextLine().trim().toLowerCase();
                if (resp2.equals("s")) {
                    TextoFormatador.alerta("🏚️ Fim da jornada. O jogo será reiniciado...");
                    cacador.resetarStatus();
                    return false;
                } else {
                    TextoFormatador.info("🔁 Voltando ao jogo sem reiniciar. Você pode continuar de onde parou.");
                    return true;
                }
            }
        }

        TextoFormatador.info("🩹 Você sobreviveu, mas está enfraquecido.");
        return true;
    }
}