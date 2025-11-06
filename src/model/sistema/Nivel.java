package model.sistema;

import model.animal.Animal;
import model.ambiente.Ambiente;
import model.personagem.Cacador;

/**
 * Classe utilitária para cálculos de XP e progressão.
 * Regras:
 *  - xpBase vem do animal (animal.getXpReward())
 *  - multiplicadores por ambiente e diferença de nível influenciam
 *  - quanto mais próximo o caçador estiver do animal em atributos, menor o ganho (evita farm fácil)
 */
public class Nivel {

    public static double xpParaProximoNivel(int nivelAtual) {
        // fórmula escalável (exponencial suave)
        return 100 * Math.pow(1.25, nivelAtual - 1);
    }

    public static double calcularXpGanho(Cacador cacador, Animal animal, Ambiente ambiente) {
        // base do animal (média de atributos * nivel) — usa método da classe Animal
        double xpBase = animal.getXpReward();

        // fator de nível entre animal e caçador
        double levelFactor = (animal.getNivel() + 1.0) / Math.max(1.0, cacador.getNivel());

        // média de atributos para cacador e animal (tenta invocar getters — espera que existam)
        double avgCacador = safeAvgCacador(cacador);
        double avgAnimal = (animal.getForca() + animal.getAgilidade() + animal.getInteligencia()) / 3.0;

        // ratio que diminui o xp se cacador for muito superior
        double attrRatio = Math.max(0.4, (avgAnimal / Math.max(1.0, avgCacador)));

        // ambiente aumenta ou reduz xp
        double envMult = ambiente.getMultiplicadorXp();

        double xp = xpBase * levelFactor * attrRatio * envMult;

        // garantia mínima
        return Math.max(1.0, Math.round(xp * 10.0) / 10.0);
    }

    private static double safeAvgCacador(Cacador cacador) {
        try {
            double f = (double) cacador.getClass().getMethod("getForca").invoke(cacador);
            double v = (double) cacador.getClass().getMethod("getAgilidade").invoke(cacador);
            double i = (double) cacador.getClass().getMethod("getInteligencia").invoke(cacador);
            return (f + v + i) / 3.0;
        } catch (Exception e) {
            // fallback razoável: usa nível como proxy
            return Math.max(1.0, cacador.getNivel() * 10.0);
        }
    }
}
