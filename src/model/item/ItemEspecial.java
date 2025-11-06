package model.item;

public class ItemEspecial extends Item {
    private String efeito; // Ex: +10% XP, +chance de drop

    public ItemEspecial(String nome, String efeito, int nivelItem, boolean raro) {
        super(nome, "Item Especial",
                (int) (Math.random() * 2 + nivelItem),
                (int) (Math.random() * 2 + nivelItem),
                (int) (Math.random() * 2 + nivelItem),
                nivelItem,
                raro);
        this.efeito = efeito;
    }

    public String getEfeito() {
        return efeito;
    }

    @Override
    public String toString() {
        return super.toString() + "\n   ✨ Efeito: " + efeito;
    }
}
