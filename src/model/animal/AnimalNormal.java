package model.animal;

import model.item.Item;

public class AnimalNormal extends Animal {
    public AnimalNormal(String nome, int nivel, String idade) {
        super(nome, nivel, idade);
        this.raridadeMultiplicador = 1.0;
    }

    @Override
    public Item dropItem() {
        // Normais raramente dropam algo
        return Math.random() < 0.2 ? new Item("Pele comum", "Material", false) : null;
    }
}
