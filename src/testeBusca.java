import java.util.ArrayList;

public class testeBusca {
    public static void main(String[] args) {
        ArrayList<funcionario> vetor = new ArrayList<funcionario>();

        funcionario f1 = new funcionario();
        funcionario f2 = new funcionario();
        funcionario f3 = new funcionario();
        funcionario f4 = new funcionario();
        funcionario f5 = new funcionario();

        f1.nome = "joao";
        f2.nome = "pedro";
        f3.nome = "rafael";
        f4.nome = "iale";
        f5.nome = "paty";

        f1.matricula = 1;
        f2.matricula = 2;
        f3.matricula = 3;
        f4.matricula = 4;
        f5.matricula = 5;

        vetor.add(f1);
        vetor.add(f2);
        vetor.add(f3);
        vetor.add(f4);
        vetor.add(f5);

        System.out.println(pesquisaRecursivaNome(vetor, 0, vetor.size() -1, "pedro"));
        System.out.println(pesquisaRecursivaMatricula(vetor, 0, vetor.size() -1, 4));
    }
        // Busca sequencial binária recursiva:
        // passamos como parâmetro o array = vetor e as variáveis menor, maior e nome
        public static int pesquisaRecursivaNome( ArrayList<funcionario> vetor, int menor,  int maior, String nome) {
            // Adicionamos um if para início da função e passamos como parâmetro a variável menor sendo menor ou igual a variável maior
            if (menor <= maior) {
                // a varável PosicaoMeio recebe a soma e a divisão das variáveis maior e menor
                int PosicaoMeio = (menor + maior) / 2;

                // verifica se a posicao do meio é igual a posicao procurada
                if (vetor.get(PosicaoMeio).nome.equals(nome)) {
                    return PosicaoMeio;
                }

                // verifica se a posição é maior ou menor a posição procurada
                if (vetor.get(PosicaoMeio).nome.compareTo(nome) > 0) {
                    // Se for verdadeiro, chama recursivamente a função para a metade à esquerda
                    return pesquisaRecursivaNome(vetor,menor, PosicaoMeio - 1,  nome); // esquerda
                } else {
                    // Se for falso, chama recursivamente a função para a metade à direita
                    return pesquisaRecursivaNome(vetor, PosicaoMeio + 1, maior, nome); // direita
                }
            }
            // retorno se o nome buscado não for encontrado
            return -1; // Element not found
        }

        // passamos como parâmetro o array = vetor e as variáveis menor, maior e matricula
        public static int pesquisaRecursivaMatricula(ArrayList<funcionario> vetor, int menor, int maior, int matricula) {
            // Adicionamos um if para início da função e passamos como parâmetro a variável menor sendo menor ou igual a variável maior
            if (menor <= maior) {
                // a varável PosicaoMeio recebe a soma e a divisão das variáveis maior e menor
                int posicaoMeio = (menor + maior) / 2;

                // verifica se a posicao do meio é igual a posicao procurada
                if (vetor.get(posicaoMeio).matricula == matricula) {
                    return posicaoMeio;
                }

                // verifica se a posição é maior ou menor a posição procurada
                if (vetor.get(posicaoMeio).matricula > matricula) {
                    // Se for verdadeiro, chama recursivamente a função para a metade à esquerda
                    return pesquisaRecursivaMatricula(vetor, menor, posicaoMeio - 1, matricula);
                } else {
                    // Se for falso, chama recursivamente a função para a metade à direita
                    return pesquisaRecursivaMatricula(vetor, posicaoMeio + 1, maior, matricula);
                }
            }
            // retorno se o nome buscado não for encontrado
            return -1;
        }

}