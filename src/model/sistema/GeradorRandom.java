package model.sistema;

import model.ambiente.Ambiente;
import model.animal.*;
import util.TextoFormatador;

import java.util.Random;

/**
 * Responsabilidade: gerar Ambientes e Animais aleatórios.
 * Usa lógica com switch/cases e for-each em listas internas para facilitar escalabilidade.
 */
public class GeradorRandom {
    private final Random rnd = new Random();

    private final String[] AMBIENTES = { "Floresta", "Montanha", "Deserto", "Pântano", "Ruínas" };
    private final String[] TIPOS_AMBIENTE = { "Florestal", "Montanhoso", "Árido", "Pantanal", "Antigo" };

    private final String[] ESPECIES = { "Lobo", "Urso", "Raposa", "Águia", "Cervo", "Leopardo", "Javali" };
    private final String[] IDADES = { "Filhote", "Adulto", "Velho" };

    public GeradorRandom() {}

    public Ambiente gerarAmbienteAleatorio() {
        // escolhe índice e monta multiplicadores base
        int idx = rnd.nextInt(AMBIENTES.length);
        String nome = AMBIENTES[idx];
        String tipo = TIPOS_AMBIENTE[idx];

        // regras simples por ambiente (faça expandível quando quiser)
        double multXp;
        double penalidade;
        switch (nome) {
            case "Floresta" -> { multXp = 1.0; penalidade = 0.0; }
            case "Montanha" -> { multXp = 0.9; penalidade = -0.05; } // menos xp, leve bônus para quem sobe
            case "Deserto" -> { multXp = 1.2; penalidade = 0.05; }
            case "Pântano" -> { multXp = 1.15; penalidade = -0.1; }
            case "Ruínas" -> { multXp = 1.3; penalidade = 0.1; }
            default -> { multXp = 1.0; penalidade = 0.0; }
        }

        Ambiente ambiente = new Ambiente(nome, tipo, multXp, penalidade);
        TextoFormatador.info("Ambiente gerado: " + ambiente.getNome() + " (XPx" + multXp + ")");
        return ambiente;
    }

    /**
     * Gera um animal adequado ao ambiente.
     * Estratégia:
     *  - escolhe espécie aleatória
     *  - escolhe idade (filhote/adulto/velho)
     *  - escolhe raridade por probabilidade e devolve AnimalNormal/Raro/Epico
     */
    public Animal gerarAnimalAleatorio(Ambiente ambiente) {
        String especie = ESPECIES[rnd.nextInt(ESPECIES.length)];
        String idade = IDADES[rnd.nextInt(IDADES.length)];

        // define nível do animal com base num range aleatório e com leve ajuste pelo multiplicador do ambiente
        int nivelBase = 1 + rnd.nextInt( Math.max(1, (int)(2 + ambiente.getMultiplicadorXp() * 2)) );

        // raridade por probabilidade: comum 65%, raro 25%, épico 10%
        double chance = rnd.nextDouble();
        Animal animal;
        if (chance <= 0.65) {
            animal = new AnimalNormal(especie, nivelBase, idade);
        } else if (chance <= 0.90) {
            animal = new AnimalRaro(especie, nivelBase, idade);
        } else {
            animal = new AnimalEpico(especie, nivelBase, idade);
        }

        TextoFormatador.info("Gerado animal: " + animal.getNome() + " (Tipo: " + animal.getClass().getSimpleName() + ")");
        return animal;
    }
}
