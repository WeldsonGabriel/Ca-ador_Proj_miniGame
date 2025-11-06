package model.animal;

import model.item.Item;

public class AnimalEpico extends Animal {
    public AnimalEpico(String nome, int nivel, String idade) {
        super(nome, nivel + 3, idade);
        this.raridadeMultiplicador = 2.5;
        this.forca += 10;
        this.agilidade += 8;
        this.inteligencia += 6;
    }

    @Override
    public Item dropItem() {
        // Sempre dropa algo épico
        return new Item("Essência Selvagem", "Item Especial", true);
    }
}
