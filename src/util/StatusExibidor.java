package util;

import model.personagem.Cacador;
import model.animal.Animal;
import model.ambiente.Ambiente;
import util.TextoFormatador;

public class StatusExibidor {

    public static void mostrarCabecalho(String titulo) {
        TextoFormatador.linha();
        System.out.println(TextoFormatador.azul("⚔️  " + titulo.toUpperCase()));
        TextoFormatador.linha();
    }

    public static void mostrarStatusCacador(Cacador c) {
        TextoFormatador.info("🎯 Status do Caçador:");
        System.out.printf("  🧍 Nome: %s%n", c.getNome());
        System.out.printf("  💪 Força: %d | ⚡ Velocidade: %d | 🧠 Inteligência: %d%n",
                c.getForca(), c.getVelocidade(), c.getInteligencia());
        System.out.printf("  ⭐ Nível: %d | 🔸 XP: %d/%d%n",
                c.getNivel(), c.getXpAtual(), c.getXpProximoNivel());
        System.out.println("  🎒 Itens: " + (c.getItens().isEmpty() ? "Nenhum" : c.getItens()));
        TextoFormatador.linha();
    }

    public static void mostrarAmbiente(Ambiente a) {
        TextoFormatador.info("🌍 Ambiente Atual:");
        System.out.printf("  %s (%s)%n", a.getNome(), a.getEfeito());
        TextoFormatador.linha();
    }

    public static void mostrarAnimal(Animal a) {
        TextoFormatador.info("🐾 Animal Encontrado:");
        System.out.printf("  🐅 %s (%s, Nível %d)%n", a.getEspecie(), a.getIdade(), a.getNivel());
        System.out.printf("  💪 Força: %d | ⚡ Velocidade: %d | 🧠 Inteligência: %d%n",
                a.getForca(), a.getVelocidade(), a.getInteligencia());
        TextoFormatador.linha();
    }

    public static void mostrarResultadoBatalha(String vencedor, String detalhe) {
        TextoFormatador.sucesso("🏆 " + vencedor + " venceu a batalha!");
        TextoFormatador.alerta("📜 " + detalhe);
        TextoFormatador.linha();
    }

    public static void exibirStatusAnimal(Animal animal, Ambiente ambiente) {
    }

    public static void exibirStatusCacador(Cacador cacador) {
    }
}
