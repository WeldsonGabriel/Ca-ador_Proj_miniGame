package util;

/**
 * Classe utilitária para formatação de mensagens e saídas no terminal.
 * Responsável por imprimir linhas, títulos e mensagens com "efeito visual".
 */
public class TextoFormatador {

    // Cores ANSI simples para terminal (funciona bem no IntelliJ)
    private static final String RESET = "\u001B[0m";
    private static final String VERMELHO = "\u001B[31m";
    private static final String VERDE = "\u001B[32m";
    private static final String AMARELO = "\u001B[33m";
    private static final String AZUL = "\u001B[34m";
    private static final String CIANO = "\u001B[36m";

    /** Linha separadora padrão */
    public static void linha() {
        System.out.println("----------------------------------------------------");
    }

    /** Linha curta separadora */
    public static void linhaSeparadora() {
        System.out.println("────────────────────────────────────────────────────");
    }

    /** Cabeçalho centralizado com destaque */
    public static void cabecalho(String titulo) {
        linha();
        System.out.println(AZUL + "🔹 " + titulo.toUpperCase() + RESET);
        linha();
    }

    // Mensagens formatadas
    public static void info(String msg) {
        System.out.println(CIANO + msg + RESET);
    }

    public static void sucesso(String msg) {
        System.out.println(VERDE + msg + RESET);
    }

    public static void erro(String msg) {
        System.out.println(VERMELHO + msg + RESET);
    }

    public static void alerta(String msg) {
        System.out.println(AMARELO + msg + RESET);
    }

    /** Pequena animação de texto (opcional) */
    public static void digitar(String msg, int delayMs) {
        for (char c : msg.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delayMs);
            } catch (InterruptedException ignored) {}
        }
        System.out.println();
    }

    public static boolean azul(String s) {
        return false;
    }

    public static boolean cabecalhoFormat(String titulo) {
        return false;
    }
}
