package unitTest;

import br.com.test.CalculadoraRacaoCao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testCalculadoraRacaoCao {

    @Test
    void testCalcularRacaoCaoPositivoPorteP() {
        // Caso de teste positivo com porte 'P' e peso válido minimo (1 kg)
        String resultado = CalculadoraRacaoCao.calcularRacaoCao('P', 1);
        assertEquals("A quantidade de ração necessária para o porte P é 10 gramas.", resultado);
    }

    @Test
    void testCalcularRacaoCaoPositivoPorteM() {
        // Caso de teste positivo com porte 'M' e peso válido Limite (29 kg)
        String resultado = CalculadoraRacaoCao.calcularRacaoCao('M', 29);
        assertEquals("A quantidade de ração necessária para o porte M é 580 gramas.", resultado);
    }
    @Test
    void testCalcularRacaoCaoPositivoPorteG() {
        // Caso de teste positivo com porte 'G' e peso válido Limite (99 kg)
        String resultado = CalculadoraRacaoCao.calcularRacaoCao('G', 99);
        assertEquals("A quantidade de ração necessária para o porte G é 2970 gramas.", resultado);
    }

    @Test
    void testCalcularRacaoCaoNegativoPorteG() {
        // Caso de teste megativo com porte 'G' e peso inválido  (101 kg)
        String resultado = CalculadoraRacaoCao.calcularRacaoCao('G', 101);
        assertEquals("Erro: Peso inválido para o porte G. Peso não pode ser maior que 100.", resultado);
    }

    @Test
    void testCalcularRacaoCaoPorteInvalido() {
        // Caso de teste negativo com porte inválido ('A') e peso válido (20 kg)
        String resultado = CalculadoraRacaoCao.calcularRacaoCao('A', 20);
        assertEquals("Erro: Porte inválido. Por favor, informe 'P', 'M' ou 'G'.", resultado);
    }

    @Test
    void testCalcularRacaoCaoPesoInvalido() {
        // Caso de teste negativo com porte válido ('G') e peso inválido (120 kg)
        String resultado = CalculadoraRacaoCao.calcularRacaoCao('G', 120);
        assertEquals("Erro: Peso inválido para o porte G. Peso não pode ser maior que 100.", resultado);
    }

    @ParameterizedTest
    @CsvSource({
            "'A', 5, 'Erro: Porte inválido. Por favor, informe ''P'', ''M'' ou ''G''.'",
            "'P', 0, 'Erro: Peso inválido para o porte P. Peso não pode ser menor ou igual a 0.'",
            "'M', 110, 'Erro: Peso inválido para o porte M. Peso não pode ser maior que 100.'",
            "'G', -10, 'Erro: Peso inválido para o porte G. Peso não pode ser menor ou igual a 0.'"
    })
    void testCalcularRacaoCaoNegativeLendoLista(char porte, double peso, String expected) {
        String resultado = CalculadoraRacaoCao.calcularRacaoCao(porte, peso);
        assertEquals(expected, resultado);
        }
    @ParameterizedTest
    @CsvFileSource(resources = "/csv/cachorros.csv", numLinesToSkip = 1)
    public void testCalcularRacaoCaoPositivoLendoArquivo(char porte, double peso, String quantidadeRacao) {
        String resultado = CalculadoraRacaoCao.calcularRacaoCao(porte, peso);
        assertEquals(quantidadeRacao, resultado);
    }
}



