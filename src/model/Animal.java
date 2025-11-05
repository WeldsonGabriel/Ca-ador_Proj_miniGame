package model;

import java.util.Random;

public class Animal {
    private String especie;
    private String idade;
    private int forca;
    private int velocidade;
    private int inteligencia;

    private static final String[] ESPECIES = {"Lobo", "Urso", "Raposa", "Águia", "Cervo", "Leopardo"};
    private static final String[] IDADES = {"Filhote", "Adulto", "Velho"};

    public Animal(Ambiente ambiente) {
        Random random = new Random();
        this.especie = ESPECIES[random.nextInt(ESPECIES.length)];
        this.idade = IDADES[random.nextInt(IDADES.length)];
        gerarAtributosBase(random);
        aplicarModificadoresPorIdade();
        aplicarEfeitoAmbiente(ambiente);
    }

    private void gerarAtributosBase(Random random) {
        this.forca = random.nextInt(41) + 40;
        this.velocidade = random.nextInt(41) + 40;
        this.inteligencia = random.nextInt(41) + 40;
    }

    private void aplicarModificadoresPorIdade() {
        switch (idade) {
            case "Filhote" -> {
                forca *= 0.8;
                velocidade *= 1.1;
                inteligencia *= 0.9;
            }
            case "Velho" -> {
                forca *= 0.9;
                velocidade *= 0.85;
                inteligencia *= 1.2;
            }
        }
    }

    private void aplicarEfeitoAmbiente(Ambiente ambiente) {
        this.forca += (int) (forca * ambiente.getBonusForca());
        this.velocidade += (int) (velocidade * ambiente.getBonusVelocidade());
        this.inteligencia += (int) (inteligencia * ambiente.getBonusInteligencia());
    }

    public void mostrarStatus() {
        System.out.printf("""
                \n🐾 Animal Encontrado:
                Espécie: %s (%s)
                Força: %d
                Velocidade: %d
                Inteligência: %d
                """, especie, idade, forca, velocidade, inteligencia);
    }

    public int getForca() { return forca; }
    public int getVelocidade() { return velocidade; }
    public int getInteligencia() { return inteligencia; }
    public String getEspecie() { return especie; }
}
