package model.item;

public class Arma extends Item {

    public Arma(String nome, int nivelItem, boolean raro) {
        super(nome, "Arma",
                (int) (Math.random() * 4 + nivelItem * 2),
                (int) (Math.random() * 2 + nivelItem),
                0,
                nivelItem,
                raro);
    }
}
