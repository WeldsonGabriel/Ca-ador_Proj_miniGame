package util;

/**
 * Utilitário simples para formatar textos no console.
 * - Usa códigos ANSI para cores (se o terminal suportar).
 * - Métodos estáticos fáceis de chamar a partir de qualquer classe.
 */
public final class TextoFormatador {

    // Cores ANSI (funciona na maioria dos terminais; se não suportar, fica texto "cru")
    private static final String RESET  = "\u001B[0m";
    private static final String RED    = "\u001B[31m";
    private static final String GREEN  = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE   = "\u001B[34m";
    private static final String CYAN   = "\u001B[36m";
    private static final String BOLD   = "\u001B[1m";

    private TextoFormatador() { /* utilitário — não instanciar */ }

    public static void linha() {
        System.out.println("────────────────────────────────────────────────────────");
    }

    public static void info(String msg) {
        System.out.println(CYAN + "[INFO] " + RESET + msg);
    }

    public static void sucesso(String msg) {
        System.out.println(GREEN + BOLD + "[SUCESSO] " + RESET + msg);
    }

    public static void erro(String msg) {
        System.out.println(RED + BOLD + "[ERRO] " + RESET + msg);
    }

    public static void alerta(String msg) {
        System.out.println(YELLOW + "[ALERTA] " + RESET + msg);
    }

    /**
     * Versão simples de print formatado — útil para logs rápidos.
     */
    public static void printf(String format, Object... args) {
        System.out.println(String.format(format, args));
    }
}
