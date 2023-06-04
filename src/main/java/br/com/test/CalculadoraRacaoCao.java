package br.com.test;
public class CalculadoraRacaoCao {
    public static String calcularRacaoCao(char porte, double peso) {
        porte = Character.toUpperCase(porte);

        try {
            if (peso <= 0 || peso > 100) {
                if (peso <= 0) {
                    throw new IllegalArgumentException("Peso inválido para o porte " + porte + ". Peso não pode ser menor ou igual a 0.");
                } else {
                    throw new IllegalArgumentException("Peso inválido para o porte " + porte + ". Peso não pode ser maior que 100.");
                }
            }

            int quantidadeRacao;

            if (porte == 'P') {
                if (peso <= 10) {
                    quantidadeRacao = (int) (peso * 10);
                    return "A quantidade de ração necessária para o porte P é " + quantidadeRacao + " gramas.";
                } else {
                    throw new IllegalArgumentException("Peso inválido para o porte P.");
                }
            } else if (porte == 'M') {
                if (peso <= 30) {
                    quantidadeRacao = (int) (peso * 20);
                    return "A quantidade de ração necessária para o porte M é " + quantidadeRacao + " gramas.";
                } else {
                    throw new IllegalArgumentException("Peso inválido para o porte M.");
                }
            } else if (porte == 'G') {
                if (peso <= 100) {
                    quantidadeRacao = (int) (peso * 30);
                    return "A quantidade de ração necessária para o porte G é " + quantidadeRacao + " gramas.";
                } else {
                    throw new IllegalArgumentException("Peso inválido para o porte G.");
                }
            } else {
                throw new IllegalArgumentException("Porte inválido. Por favor, informe 'P', 'M' ou 'G'.");
            }
        } catch (IllegalArgumentException e) {
            return "Erro: " + e.getMessage();
        }
    }
}
