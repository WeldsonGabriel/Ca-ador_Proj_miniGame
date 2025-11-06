package util;

import model.personagem.Cacador;
import model.animal.Animal;
import model.ambiente.Ambiente;

public class StatusExibidor {

    public static void mostrarCabecalho(String titulo) {
        TextoFormatador.linha();
        System.out.println("⚔️  " + titulo.toUpperCase());
        TextoFormatador.linha();
    }

    // ==============================
    // Exibição do Caçador
    // ==============================
    public static void exibirStatusCacador(Cacador c) {
        TextoFormatador.info("🎯 STATUS DO CAÇADOR:");

        try {
            System.out.printf("  🧍 Nome: %s%n", c.getNome());
        } catch (Exception e) {
            System.out.println("  🧍 Nome: Desconhecido");
        }

        try {
            System.out.printf("  💪 Força: %d | ⚡ Agilidade: %d | 🧠 Inteligência: %d%n",
                    c.getForca(), c.getAgilidade(), c.getInteligencia());
        } catch (Exception e) {
            TextoFormatador.alerta("⚠️ Atributos do caçador não encontrados.");
        }

        try {
            System.out.printf("  ❤️ Vida: %d/%d%n", c.getVidaAtual(), c.getVidaMaxima());
        } catch (Exception ignored) {}

        try {
            System.out.printf("  ⭐ Nível: %d | 🔸 XP: %.0f / %.0f%n",
                    c.getNivel(), c.getExperiencia(), c.getExperienciaNecessaria());
        } catch (Exception ignored) {}

        // 🔹 Exibição de itens (verificação segura)
        try {
            Object itensObj = c.getItens();
            if (itensObj instanceof java.util.Collection<?> itens && !itens.isEmpty()) {
                System.out.println("  🎒 Itens:");
                for (Object o : itens) {
                    if (o instanceof model.item.Item item) {
                        System.out.println("     - " + item.getNome() + (item.isRaro() ? " ✨" : ""));
                    } else {
                        System.out.println("     - " + o);
                    }
                }
            } else {
                System.out.println("  🎒 Itens: Nenhum");
            }
        } catch (Exception e) {
            System.out.println("  🎒 Itens: Indisponível");
        }

        TextoFormatador.linha();
    }

    // ==============================
    // Exibição do Animal
    // ==============================
    public static void exibirStatusAnimal(Animal a, Ambiente ambiente) {
        TextoFormatador.info("🐾 ANIMAL ENCONTRADO:");

        try {
            System.out.printf("  🐅 Nome: %s | Idade: %s | Nível: %d%n",
                    a.getNome(), a.getIdade(), a.getNivel());
        } catch (Exception e) {
            TextoFormatador.alerta("⚠️ Dados do animal incompletos.");
        }

        try {
            System.out.printf("  💪 Força: %d | ⚡ Agilidade: %d | 🧠 Inteligência: %d%n",
                    a.getForca(), a.getAgilidade(), a.getInteligencia());
        } catch (Exception e) {
            TextoFormatador.alerta("⚠️ Atributos do animal não encontrados.");
        }

        try {
            String tipoAmbiente = null;
            try { tipoAmbiente = ambiente.getTipo(); } catch (Exception ignored) {}
            if (tipoAmbiente != null) {
                TextoFormatador.info(String.format("  🌍 Ambiente: %s (%s)", ambiente.getNome(), tipoAmbiente));
            } else {
                TextoFormatador.info(String.format("  🌍 Ambiente: %s (XPx%.2f)", ambiente.getNome(), ambiente.getMultiplicadorXp()));
            }
        } catch (Exception e) {
            TextoFormatador.info("  🌍 Ambiente: " + (ambiente != null ? ambiente.getNome() : "Desconhecido"));
        }

        TextoFormatador.linha();
    }

    // ==============================
    // Outros métodos de exibição
    // ==============================
    public static void mostrarResultadoBatalha(String vencedor, String detalhe) {
        TextoFormatador.sucesso("🏆 " + vencedor + " venceu a batalha!");
        TextoFormatador.alerta("📜 " + detalhe);
        TextoFormatador.linha();
    }

    public static void mostrarAmbiente(Ambiente a) {
        TextoFormatador.info("🌍 Ambiente Atual:");
        try {
            System.out.printf("  %s (%s)%n", a.getNome(), (a.getTipo() != null ? a.getTipo() : "—"));
        } catch (Exception e) {
            System.out.printf("  %s%n", a != null ? a.getNome() : "Desconhecido");
        }
        TextoFormatador.linha();
    }

    // ==============================
    // Aliases para compatibilidade
    // ==============================
    public static void mostrarStatusCacador(Cacador c) {
        exibirStatusCacador(c);
    }

    public static void mostrarAnimal(Animal a) {
        if (a != null) {
            try {
                TextoFormatador.info("🐾 Animal:");
                System.out.printf("  %s (Nível %d)%n", a.getNome(), a.getNivel());
                TextoFormatador.linha();
            } catch (Exception ex) {
                exibirStatusAnimal(a, null);
            }
        }
    }
}
