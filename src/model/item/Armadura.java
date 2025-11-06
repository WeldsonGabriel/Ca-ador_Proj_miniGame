package model.item;

public class Armadura extends Item {

    public Armadura(String nome, int nivelItem, boolean raro) {
        super(nome, "Armadura",
                (int) (Math.random() * 2 + nivelItem),
                (int) (Math.random() * 2 + nivelItem),
                (int) (Math.random() * 3 + nivelItem),
                nivelItem,
                raro);
    }
}
