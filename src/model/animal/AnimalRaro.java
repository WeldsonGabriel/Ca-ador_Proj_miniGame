package model.animal;

import model.item.Item;

public class AnimalRaro extends Animal {
    public AnimalRaro(String nome, int nivel, String idade) {
        super(nome, nivel + 1, idade);
        this.raridadeMultiplicador = 1.5;
        this.forca += 5;
        this.agilidade += 5;
        this.inteligencia += 3;
    }

    @Override
    public Item dropItem() {
        // Chance de item raro
        return Math.random() < 0.5 ? new Item("Couro Raro", "Armadura", true) : null;
    }
}
