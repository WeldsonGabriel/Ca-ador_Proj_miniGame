package model.sistema;

import model.animal.Animal;
import model.ambiente.Ambiente;
import model.personagem.Cacador;
import model.item.Item;
import util.TextoFormatador;

/**
 * Responsabilidade: executar uma batalha entre um Cacador e um Animal,
 * aplicando modificadores do Ambiente, calculando XP e possíveis drops.
 */
public class Batalha {

    /**
     * Inicia a batalha.
     * Retorna true se o caçador venceu, false se foi derrotado.
     */
    public boolean iniciar(Cacador cacador, Animal animal, Ambiente ambiente) {
        TextoFormatador.info("\n⚔️  Iniciando confronto com " + animal.getNome() + " no " + ambiente.getNome());

        // Calcula poder do animal (média dos atributos) e aplica modificadores do ambiente
        double poderAnimal = calcularPoderAnimalComAmbiente(animal, ambiente);

        // Obtem poder do caçador (usa método que a classe Cacador deve expor)
        int poderCacador;
        try {
            // tenta método getPoderTotal() — comum nas versões anteriores do Cacador
            poderCacador = (int) (double) ( (Object) cacador ).getClass()
                    .getMethod("getPoderTotal").invoke(cacador);
        } catch (Exception e) {
            // fallback: soma simples de atributos via métodos padrão (se existirem)
            try {
                int f = (int) cacador.getClass().getMethod("getForca").invoke(cacador);
                int v = (int) cacador.getClass().getMethod("getAgilidade").invoke(cacador);
                int i = (int) cacador.getClass().getMethod("getInteligencia").invoke(cacador);
                poderCacador = f + v + i;
            } catch (Exception ex) {
                // se não há getters, assume um valor neutro (evita crash)
                poderCacador = cacador.getNivel() * 10 + 30;
            }
        }

        TextoFormatador.info(String.format("🔎 Poder do Caçador: %d | Poder do Animal: %.1f", poderCacador, poderAnimal));

        // Probabilidade de vitória baseada na razão de poder (com leve ruído aleatório)
        double chanceBase = poderCacador / (poderCacador + poderAnimal);
        double aleatorio = Math.random();
        boolean venceu = aleatorio < chanceBase;

        if (venceu) {
            TextoFormatador.sucesso("🏆 Vitória! Você superou o " + animal.getNome());
            // calcula XP usando a classe Nivel
            double xpGanho = Nivel.calcularXpGanho(cacador, animal, ambiente);
            cacador.ganharExperiencia(xpGanho);

            // drop de item
            Item drop = animal.dropItem();
            if (drop != null) {
                TextoFormatador.sucesso("🎁 O animal deixou cair: " + drop.getNome());
                cacador.adicionarItem(drop);
            } else {
                TextoFormatador.info("🔎 Nenhum item encontrado desta vez.");
            }
        } else {
            TextoFormatador.erro("💀 Derrota! O " + animal.getNome() + " te superou.");
            // dano simples / penalidade — tenta reduzir vida se o método existir
            try {
                // chama cacador.atacar(nome,poder) fallback? preferimos invocar um método existente
                cacador.getClass().getMethod("receberDanoPorPerda", double.class).invoke(cacador, poderAnimal * 0.5);
            } catch (NoSuchMethodException nsme) {
                // método não existe: apenas logamos
                TextoFormatador.alerta("Você sofreu uma derrota, recupere-se antes da próxima caçada.");
            } catch (Exception ex) {
                // ignora falha no fallback
            }
        }

        return venceu;
    }

    private double calcularPoderAnimalComAmbiente(Animal animal, Ambiente ambiente) {
        double mediaAtributos = (animal.getForca() + animal.getAgilidade() + animal.getInteligencia()) / 3.0;
        // ambiente pode ter penalidades ou bônus — usa multiplicador xp como proxy de dificuldade
        double ambienteFactor = 1.0 + (ambiente.getMultiplicadorXp() - 1.0); // ex: xp 1.2 => +0.2
        // considera idade (se Animal expor getNivel/idade, já embutido nos atributos)
        return mediaAtributos * ambienteFactor;
    }
}
