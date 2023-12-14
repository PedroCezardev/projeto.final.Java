import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.*;

class Funcionario {
    int matricula;
    int codigoCargo;
    String nome;
    double salario;
    // Construtor, métodos getters e setters podem ser adicionados conforme necessário
}

public class Main {
    private static final String NOME_ARQUIVO =  "C:/Users/aluno/Downloads/trabalho java/funcionarios.txt";

    public static void main (String[] args) {
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        lerArquivo(funcionarios);
        exibirMenu(funcionarios);
        salvarArquivo(funcionarios);
    }

    private static void lerArquivo(ArrayList<Funcionario> funcionarios) {
        try (Scanner scanner = new Scanner(new File(NOME_ARQUIVO))) {
            int totalFuncionarios = Integer.parseInt(scanner.nextLine().trim());

            for (int i = 0; i < totalFuncionarios; i++) {
                String linha = scanner.nextLine();
                String[] dados = linha.split(" ");
                if (dados.length == 4) {
                    Funcionario funcionario = new Funcionario();
                    funcionario.matricula = Integer.parseInt(dados[0]);
                    funcionario.codigoCargo = Integer.parseInt(dados[1]);
                    funcionario.nome = dados[2].replace("-", " ");
                    funcionario.salario = Double.parseDouble(dados[3]);
                    funcionarios.add(funcionario);
                } else {
                    System.err.println("Formato inválido na linha: " + linha);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("Arquivo não encontrado. Criando novo arquivo...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void exibirMenu(ArrayList<Funcionario> funcionarios) {
        Scanner scanner = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\nMenu:");
            System.out.println("0. Sair"); 
            System.out.println("1.  Consultar funcionários");
            System.out.println("2.  Alterar dados de um funcionário");
            System.out.println("3.  Adicionar novo funcionário");
            System.out.println("4.  Exibir todos os funcionários");
            System.out.println("5.  Exibir ordenação por nome em Quicksort");
            System.out.println("6.  Exibir ordenação por código em Quicksort");
            System.out.println("7.  Exibir ordenação por nome em MergeSort");
            System.out.println("8.  Exibir ordenação por código em MergeSort");
            System.out.println("9.  Exibir ordenação por nome em ShellSort");
            System.out.println("10. Exibir ordenação por código em ShellSort");
            System.out.println("11. Exibir ordenação por nome em RadixSort");           /*ainda falta adicionar */
            System.out.println("12. Exibir ordenação por código em RadixSort");         /*ainda falta adicionar */
            System.out.println("13. Exibir ordenação por nome em CountingSort");        
            System.out.println("14. Exibir ordenação por código em CountingSort");      
            System.out.println("15. Pesquisar um funcionário usando nome ou código (Versão recursiva da busca binária)");   /*ainda falta adicionar a busca por nome */
            System.out.println("16. Pesquisar um funcionário usando nome ou código (Versão recursiva da busca sequencial)");
            System.out.print("Escolha a opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 0:
                    System.out.println("Saindo do programa...");
                    break;
                case 1:
                    consultarFuncionarios(funcionarios);
                    break;
                case 2:
                    alterarFuncionario(funcionarios);
                    break;
                case 3:
                    adicionarFuncionario(funcionarios);
                    break;
                case 4:
                    exibirTodosFuncionarios(funcionarios);
                    break;
                case 5:
                    Funcionario[] funcionariosArrayQ = funcionarios.toArray(new Funcionario[funcionarios.size()]);
                    ordenarPorNomeQuickSort(funcionariosArrayQ, opcao, opcao);
                    exibeFuncionario(funcionariosArrayQ);
                    break;
                case 6: 
                    Funcionario[] funcionariosArrayQ2 = funcionarios.toArray(new Funcionario[funcionarios.size()]);
                    ordenarPorMatriculaQuickSort(funcionariosArrayQ2, opcao, opcao);
                    exibeFuncionario(funcionariosArrayQ2);               
                    break;    
                case 7:
                    Funcionario[] funcionariosArrayN = funcionarios.toArray(new Funcionario[funcionarios.size()]);
                    mergeSort(funcionariosArrayN, 0, funcionariosArrayN.length - 1);
                    exibeFuncionario(funcionariosArrayN);
                    break;
                case 8: 
                    Funcionario[] funcionariosArrayID = funcionarios.toArray(new Funcionario[funcionarios.size()]);
                    mergeSortid(funcionariosArrayID, 0, funcionariosArrayID.length - 1);
                    exibeFuncionario(funcionariosArrayID);               
                    break;                                 
                case 9:
                    Funcionario[] funcionariosArray = funcionarios.toArray(new Funcionario[0]);
                    funcionariosArray = shellSortNome(funcionariosArray);
                    exibeFuncionario(funcionariosArray); 
                    break;
                case 10: 
                    Funcionario[] funcionariosArray1 = funcionarios.toArray(new Funcionario[0]);
                    funcionariosArray = shellSortCod(funcionariosArray1);
                    exibeFuncionario(funcionariosArray);                
                    break;              
                case 11:
                    Funcionario[] funcionariosArrayRD = funcionarios.toArray(new Funcionario[0]);
                    funcionariosArrayRD = ordenacaoRadixSortNome(funcionariosArrayRD);
                    exibeFuncionario(funcionariosArrayRD);
                    break;
                case 12:
                Funcionario[] funcionariosArrayRD2 = funcionarios.toArray(new Funcionario[0]);
                    funcionariosArrayRD2 = ordenacaoRadixSortMatricula(funcionariosArrayRD2);
                    exibeFuncionario(funcionariosArrayRD2);
                    break;
                case 13:
                Funcionario[] funcionariosArrayCS = funcionarios.toArray(new Funcionario[funcionarios.size()]);
                    ordenarPorNomeCountingSort(funcionariosArrayCS);
                    exibeFuncionario(funcionariosArrayCS);               
                    break;
                case 14:
                    Funcionario[] funcionariosArrayCS1 = funcionarios.toArray(new Funcionario[funcionarios.size()]);
                    ordenarPorMatriculaCountingSort(funcionariosArrayCS1);
                    exibeFuncionario(funcionariosArrayCS1);               
                    break;
                case 15:
                    //Pergunta ao usuário de qual maneira ele quer consultar,por nome ou por código.
                System.out.println("Você deseja pesquisar por Nome? (Digite 1) \n Pesquisar por código de funcionario? (Digite 2)  ");
                int escolhaBIN = scanner.nextInt();
                //Condição caso o usuário escolha 1 (pesquisar por nome) 
                if (escolhaBIN == 1) {
                    scanner.nextLine();
                    System.out.println("Qual nome do funcionário que você deseja encontrar?");
                    String nome = scanner.nextLine();


                    //Chamando função para pesquisar por nome
                    int retorno = pesquisaRecursivaNome(funcionarios,0, funcionarios.size() - 1, nome);
                    if (retorno >= 0) {
                    //exibe as infomações do funcuncionário encontrado
                    System.out.println("\nInformações do funcionário:");
                    System.out.println("Matrícula: " + funcionarios.get(retorno).matricula);
                    System.out.println("Código do Cargo: " + funcionarios.get(retorno).codigoCargo);
                    System.out.println("Nome: " + funcionarios.get(retorno).nome);
                    System.out.println("Salário: " + funcionarios.get(retorno).salario);
                    } else {
                    System.out.println("O funcionário não existe");
                    }
                    //condição caso o usuário escolha 2 (pesquisar por código)
                    } else if (escolhaBIN == 2) {
                    scanner.nextLine();
                    System.out.println("Qual número do funcionário que você deseja encontrar?");
                    Integer numeroBIN = scanner.nextInt();


                    //chamando função para pesquisar por código
                    int retorno = pesquisaRecursivaMatricula(funcionarios, 0, funcionarios.size() - 1, numeroBIN);
                    if (retorno >= 0) {
                    //exibe as infomações do funcuncionário encontrado 
                    System.out.println("\nInformações do funcionário:");
                    System.out.println("Matrícula: " + funcionarios.get(retorno).matricula);
                    System.out.println("Código do Cargo: " + funcionarios.get(retorno).codigoCargo);
                    System.out.println("Nome: " + funcionarios.get(retorno).nome);
                    System.out.println("Salário: " + funcionarios.get(retorno).salario);
                    } else {
                    System.out.println("O funcionário não existe");
                    }

                }
                    break;
                case 16:
                    //Pergunta ao usuário de qual maneira ele quer consultar,por nome ou por código.
                System.out.println("Você deseja pesquisar por Nome? (Digite 1) \n Pesquisar por código de funcionario? (Digite 2)  ");
                int escolha = scanner.nextInt();
                //Condição caso o usuário escolha 1 (pesquisar por nome) 
                if (escolha == 1) {
                    scanner.nextLine();
                    System.out.println("Qual nome do funcionário que você deseja encontrar?");
                    String nome = scanner.nextLine();
                    //Chamando função para pesquisar por nome
                    int retorno = pesquisarRNome(nome, funcionarios, funcionarios.size());
                    if (retorno >= 0) {
                    //exibe as infomações do funcuncionário encontrado    
                    System.out.println("\nInformações do funcionário:");
                    System.out.println("Matrícula: " + funcionarios.get(retorno).matricula);
                    System.out.println("Código do Cargo: " + funcionarios.get(retorno).codigoCargo);
                    System.out.println("Nome: " + funcionarios.get(retorno).nome);
                    System.out.println("Salário: " + funcionarios.get(retorno).salario);
                    } else {
                    System.out.println("O funcionário não existe");
                    }
                    //condição caso o usuário escolha 2 (pesquisar por código)
                    } else if (escolha == 2) {
                    scanner.nextLine();
                    System.out.println("Qual número do funcionário que você deseja encontrar?");
                    Integer numero = scanner.nextInt();
                    //chamando função para pesquisar por código
                    int retorno = pesquisarRNumero(numero, funcionarios, funcionarios.size());
                    if (retorno >= 0) {
                    //exibe as infomações do funcuncionário encontrado 
                    System.out.println("\nInformações do funcionário:");
                    System.out.println("Matrícula: " + funcionarios.get(retorno).matricula);
                    System.out.println("Código do Cargo: " + funcionarios.get(retorno).codigoCargo);
                    System.out.println("Nome: " + funcionarios.get(retorno).nome);
                    System.out.println("Salário: " + funcionarios.get(retorno).salario);
                    } else {
                    System.out.println("O funcionário não existe");
                      }

                }
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (opcao != 0);

        // Fecha o scanner para evitar vazamento de recursos
        scanner.close();
    }


    private static void consultarFuncionarios(ArrayList<Funcionario> funcionarios) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInforme a matrícula do funcionário a ser consultado: ");
        Integer matricula = scanner.nextInt();
   
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.matricula == (matricula)) {
                System.out.println("\nInformações do funcionário:");
                System.out.println("Matrícula: " + funcionario.matricula);
                System.out.println("Código do Cargo: " + funcionario.codigoCargo);
                System.out.println("Nome: " + funcionario.nome);
                System.out.println("Salário: " + funcionario.salario);
                return;
            }
        }
   
        System.out.println("Funcionário não encontrado com a matrícula fornecida.");
    }
   

    private static void alterarFuncionario(ArrayList<Funcionario> funcionarios) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("\nInforme a matrícula do funcionário a ser alterado: ");
        Integer matricula = scanner.nextInt();
   
        for (Funcionario funcionario : funcionarios) {
            if (funcionario.matricula == (matricula)) {
                System.out.print("Novo salário: ");
   
                while (!scanner.hasNextDouble()) {
                    System.out.println("Formato inválido. Digite um número para o salário.");
                    scanner.next(); // Limpa a entrada inválida do buffer
                }
   
                funcionario.salario = scanner.nextDouble();
                System.out.println("Dados do funcionário atualizados.");
                return;
            }
        }
   
        System.out.println("Funcionário não encontrado com a matrícula fornecida.");
    }

    // Método para adicionar um novo funcionário
    public static void adicionarFuncionario(ArrayList<Funcionario> funcionarios) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\nAdicionar novo funcionário:");
        Funcionario novoFuncionario = new Funcionario();

        System.out.print("Matrícula: ");
        novoFuncionario.matricula = scanner.nextInt();

        System.out.print("Código do Cargo: ");
        novoFuncionario.codigoCargo = scanner.nextInt();

        // Lê o nome usando nextLine() para permitir espaços no nome
        System.out.print("Nome: ");
        scanner.nextLine(); // Limpeza do buffer antes de ler a linha completa
        novoFuncionario.nome = scanner.nextLine();

        System.out.print("Salário: ");

        // Verifica se o valor digitado é um double válido
        while (!scanner.hasNextDouble()) {
            System.out.println("Formato inválido para o salário. Digite novamente: ");
            scanner.next(); // Limpa a entrada inválida do buffer
        }

        // Atualiza o salário do funcionário
        novoFuncionario.salario = scanner.nextDouble();

        // Adiciona o novo funcionário à lista
        funcionarios.add(novoFuncionario);

        System.out.println("Novo funcionário adicionado com sucesso!");
    }
   
    private static void exibirTodosFuncionarios(ArrayList<Funcionario> funcionarios) {
        System.out.println("\nLista de todos os funcionários:");
        for (Funcionario funcionario : funcionarios) {
            System.out.println("Matrícula: " + funcionario.matricula);
            System.out.println("Código do Cargo: " + funcionario.codigoCargo);
            System.out.println("Nome: " + funcionario.nome);
            System.out.println("Salário: " + funcionario.salario);
            System.out.println("-----");
        }
    }

    private static void salvarArquivo(ArrayList<Funcionario> funcionarios) {
        try (PrintWriter writer = new PrintWriter(new File(NOME_ARQUIVO))) {
            writer.println(funcionarios.size()); // Escreve o total de funcionários na primeira linha

            for (Funcionario funcionario : funcionarios) {
                String nomeFormatado = funcionario.nome.replace(" ", "-");
                writer.println(funcionario.matricula + " " + funcionario.codigoCargo + " " + nomeFormatado + " " + funcionario.salario);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    public static void exibeFuncionario(Funcionario[] f) {
        for (int i = 0; i < f.length; i++) {
            System.out.println("Matrícula: " + f[i].matricula);
            System.out.println("Código do cargo: " + f[i].codigoCargo);
            System.out.println("Nome: " + f[i].nome);
            System.out.println(" Salario: R$ " + f[i].salario);
        }
    }










    // Método Quicksort para ordenar funcionários por número de matrícula
    public static void ordenarPorMatriculaQuickSort(Funcionario[] funcionarios, int inicio, int fim) {
        if (inicio < fim) {
            int indexPivo = particionarPorMatricula(funcionarios, inicio, fim);
            ordenarPorMatriculaQuickSort(funcionarios, inicio, indexPivo - 1);
            ordenarPorMatriculaQuickSort(funcionarios, indexPivo + 1, fim);
        }
    }
    private static int particionarPorMatricula(Funcionario[] funcionarios, int inicio, int fim) {
        int pivo = funcionarios[fim].codigoCargo;
        int i = inicio - 1;
        for (int j = inicio; j < fim; j++) {
            if (funcionarios[j].codigoCargo < pivo) {
                i++;
                trocar(funcionarios, i, j);
            }
        }
        trocar(funcionarios, i + 1, fim);
        return i + 1;
    }
    public static void ordenarPorNomeQuickSort(Funcionario[] funcionarios, int inicio, int fim) {
        if (inicio < fim) {
            int indexPivo = particionarPorNome(funcionarios, inicio, fim);
            ordenarPorNomeQuickSort(funcionarios, inicio, indexPivo - 1);
            ordenarPorNomeQuickSort(funcionarios, indexPivo + 1, fim);
            }
        }
        private static int particionarPorNome(Funcionario[] funcionarios, int inicio, int fim) {
            String pivo = funcionarios[inicio].nome;
            int i = inicio;
            int j = fim;
            while(i < j) {
                while(funcionarios[i].nome.compareTo(pivo) < 0) {
                    i++;
                }
                while(funcionarios[j].nome.compareTo(pivo) > 0) {
                    j--;
                }
                if(i <= j) {
                    trocar(funcionarios, i, j);
                    i++;
                    j--;
                }
            }
            if(j > inicio) {
                particionarPorNome(funcionarios, inicio, j);
            }
            if(i < fim) {
                particionarPorNome(funcionarios, i, fim);
            }
            return i;
        }
        private static void trocar(Funcionario[] funcionarios, int i, int j) {
            Funcionario temp = funcionarios[i];
            funcionarios[i] = funcionarios[j];
            funcionarios[j] = temp;
        }
    






//MergeSort
    public static void mergeSort(Funcionario[] f, int inicio, int fim) {
        if (inicio < fim) {
            // Encontra o ponto médio do vetor
            int meio = (inicio + fim) / 2;
    
            // Chama recursivamente o merge sort para as duas metades
            mergeSort(f, inicio, meio);
            mergeSort(f, meio + 1, fim);
    
            // Combina as duas metades ordenadas
            merge(f, inicio, meio, fim);
        }
    }
    
    // Função para combinar duas metades ordenadas
        public static void merge(Funcionario[] f, int inicio, int meio, int fim) {
        int tamanho1 = meio - inicio + 1;
        int tamanho2 = fim - meio;
    
        // Cria arrays temporários para armazenar as duas metades
        Funcionario[] parte1 = new Funcionario[tamanho1];
        Funcionario[] parte2 = new Funcionario[tamanho2];
    
        // Copia os dados para os arrays temporários
        for (int i = 0; i < tamanho1; i++) {
            parte1[i] = f[inicio + i];
        }
        for (int j = 0; j < tamanho2; j++) {
            parte2[j] = f[meio + 1 + j];
        }
    
        // Índices iniciais dos subarrays
        int i = 0, j = 0;
    
        // Índice inicial do subarray combinado
        int k = inicio;
    
        // Combina as duas metades ordenadas de volta ao vetor original
        while (i < tamanho1 && j < tamanho2) {
            if (parte1[i].nome.compareTo(parte2[j].nome) <= 0) {
                f[k] = parte1[i];
                i++;
            } else {
                f[k] = parte2[j];
                j++;
            }
            k++;
        }
    
        // Copia os elementos restantes, se houver, de parte1
        while (i < tamanho1) {
            f[k] = parte1[i];
            i++;
            k++;
        }
    
        // Copia os elementos restantes, se houver, de parte2
        while (j < tamanho2) {
            f[k] = parte2[j];
            j++;
            k++;
            }
        }
    
        // Função principal do merge sort - ID
    public static void mergeSortid(Funcionario[] f, int inicio, int fim) {
        if (inicio < fim) {
            // Encontra o ponto médio do vetor
            int meio = (inicio + fim) / 2;
    
            // Chama recursivamente o merge sort para as duas metades
            mergeSortid(f, inicio, meio);
            mergeSortid(f, meio + 1, fim);
    
            // Combina as duas metades ordenadas
            mergeid(f, inicio, meio, fim);
        }
    }
    
    // Função para combinar duas metades ordenadas
    public static void mergeid(Funcionario[] f, int inicio, int meio, int fim) {
        int tamanho1 = meio - inicio + 1;
        int tamanho2 = fim - meio;
    
        // Cria arrays temporários para armazenar as duas metades
        Funcionario[] parte1 = new Funcionario[tamanho1];
        Funcionario[] parte2 = new Funcionario[tamanho2];
    
        // Copia os dados para os arrays temporários
        for (int i = 0; i < tamanho1; i++) {
            parte1[i] = f[inicio + i];
        }
        for (int j = 0; j < tamanho2; j++) {
            parte2[j] = f[meio + 1 + j];
        }
    
        // Índices iniciais dos subarrays
        int i = 0, j = 0;
    
        // Índice inicial do subarray combinado
        int k = inicio;
    
        // Combina as duas metades ordenadas de volta ao vetor original
        while (i < tamanho1 && j < tamanho2) {
            if (parte1[i].codigoCargo<(parte2[j].codigoCargo)) {
                f[k] = parte1[i];
                i++;
            } else {
                f[k] = parte2[j];
                j++;
            }
            k++;
        }
    
        // Copia os elementos restantes, se houver, de parte1
        while (i < tamanho1) {
            f[k] = parte1[i];
            i++;
            k++;
        }
    
        // Copia os elementos restantes, se houver, de parte2
        while (j < tamanho2) {
            f[k] = parte2[j];
            j++;
            k++;
        }
    }












// ShellSort
    public static Funcionario[] shellSortNome(Funcionario [] f) {
        int n = f.length; //tamanho do vetor de funcionario[]
        int j; 
        for(int intervalo = n/2; intervalo > 0; intervalo = intervalo/2){ //o intervalo sempre começa com n/2; o intervalo tem que ser maior que 0; o intervalo vai terminar no momento que n for dividido por um número que o deixa abaixo de 0
            for(int i = intervalo; i < n; i++){ //quando i for igual ao valor do index do intervalo; i tem que ser menor que n
              j = i; //atribui valor de i a j 
              Funcionario temp = f[j]; //declara e atribui a variavel temp
              if(f[j - intervalo].nome.compareTo(temp.nome) > 0){  //usa o temp pra comparar
                for(j=i; j>=intervalo && f[j - intervalo].nome.compareTo(temp.nome) > 0; j = j -intervalo){ //tem duas condições pra acontecer isso ae: j tem que ser maior ou igual ao intervalo, já que, não tem index antes do 0, e a segunda condição é qnd o valor da variável temp é maior que o elemento que está sendo comparado 
                    f[j] = f[j-intervalo]; 
                }
                f[j] = temp; 
              }
            }
        }
        return f; 
      }

      public static Funcionario[] shellSortCod(Funcionario [] f) {
        int n = f.length; //tamanho do vetor de funcionario[]
        int j; 
        for(int intervalo = n/2; intervalo > 0; intervalo = intervalo/2){ //o intervalo sempre começa com n/2; o intervalo tem que ser maior que 0; o intervalo vai terminar no momento que n for dividido por um número que o deixa abaixo de 0
            for(int i = intervalo; i < n; i++){ //quando i for igual ao valor do index do intervalo; i tem que ser menor que n
              j = i; //atribui valor de i a j 
              Funcionario temp = f[j]; //declara e atribui a variavel temp
              if(f[j-intervalo].codigoCargo > temp.codigoCargo){  //usa o temp pra comparar
                while (j >= intervalo && f[j-intervalo].codigoCargo > temp.codigoCargo){ //tem duas condições pra acontecer isso ae: j tem que ser maior ou igual ao intervalo, já que, não tem index antes do 0, e a segunda condição é qnd o valor da variável temp é maior que o elemento que está sendo comparado 
                    f[j] = f[j-intervalo]; 
                    j = j -intervalo; 
                }
                f[j] = temp; 
              }
            }
        }
        return f; 
      }








//RadixSort
// Constante que define o número máximo de caracteres
private static final int MAX_CHARS = 256;

// Função principal para classificar um array usando o Radix Sort
public static Funcionario[] ordenacaoRadixSortNome(Funcionario[] funcionarios) {
    // Encontrar o comprimento máximo do nome
    int maxNomeLength = getMaxNomeLength(funcionarios);

    // Loop principal do Radix Sort, iterando sobre cada caractere da direita para a esquerda
    for (int pos = maxNomeLength - 1; pos >= 0; pos--) {
        // Criação de filas para armazenar temporariamente os elementos durante a ordenação
        Queue<Funcionario>[] queues = createQueues();

        // Distribuição dos elementos nas filas com base no caractere atual
        for (Funcionario funcionario : funcionarios) {
            int q = getChar(funcionario.nome, pos);
            queues[q].add(funcionario);
        }

        // Restauração dos elementos no array original a partir das filas
        restore(queues, funcionarios);
    }

    return funcionarios;
}




// Função para restaurar os elementos do array original a partir das filas
private static void restore(Queue<Funcionario>[] qs, Funcionario[] funcionarios) {
    int contArray = 0;
    for (Queue<Funcionario> queue : qs) {
        while (!queue.isEmpty()) {
            funcionarios[contArray] = queue.poll();
            contArray++;
        }
    }
}

// Função para criar filas vazias
private static Queue<Funcionario>[] createQueues() {
    LinkedList<Funcionario>[] result = new LinkedList[MAX_CHARS];
    for (int i = 0; i < MAX_CHARS; i++) {
        result[i] = new LinkedList<>();
    }
    return result;
}

// Função para obter o caractere em uma posição específica de uma string
private static int getChar(String string, int pos) {
    if (pos >= string.length()) {
        return 0;
    }
    return string.charAt(pos);
}

// Função para obter o comprimento máximo do nome
private static int getMaxNomeLength(Funcionario[] funcionarios) {
    int maxNomeLength = 0;
    for (Funcionario funcionario : funcionarios) {
        maxNomeLength = Math.max(maxNomeLength, funcionario.nome.length());
    }
    return maxNomeLength;
}

// Constante que define o número máximo de dígitos
private static final int MAX_DIGITS = 10; // Assuming matricula is a 10-digit integer

// Função principal para classificar um array usando o Radix Sort
public static Funcionario[] ordenacaoRadixSortMatricula(Funcionario[] funcionarios) {
    // Encontrar o número máximo de dígitos na matricula
    int maxMatriculaDigits = getMaxMatriculaDigits(funcionarios);

    // Loop principal do Radix Sort, iterando sobre cada dígito da direita para a esquerda
    for (int pos = maxMatriculaDigits - 1; pos >= 0; pos--) {
        // Criação de filas para armazenar temporariamente os elementos durante a ordenação
        Queue<Funcionario>[] queues = createQueues();

        // Distribuição dos elementos nas filas com base no dígito atual
        for (Funcionario funcionario : funcionarios) {
            int q = getDigit(funcionario.matricula, pos);
            queues[q].add(funcionario);
        }

        // Restauração dos elementos no array original a partir das filas
        restore(queues, funcionarios);
    }

    return funcionarios;
}

// Função para obter o dígito em uma posição específica de um número
private static int getDigit(int number, int pos) {
    return (number / (int) Math.pow(10, pos)) % 10;
}

// Função para obter o número máximo de dígitos na matricula
private static int getMaxMatriculaDigits(Funcionario[] funcionarios) {
    int maxMatriculaDigits = 0;
    for (Funcionario funcionario : funcionarios) {
        int numDigits = (int) Math.log10(funcionario.matricula) + 1;
        maxMatriculaDigits = Math.max(maxMatriculaDigits, numDigits);
    }
    return maxMatriculaDigits;
}









// CountingSort

    public static Funcionario[] ordenarPorMatriculaCountingSort(Funcionario[] vet) {
        int n = vet.length;
        int max = getMax(vet);
    
        Funcionario[] output = new Funcionario[n];
        int[] count = new int[max + 1];
    
        for (int i = 0; i < n; i++) {
          count[(vet[i].matricula)]++;
        }
    
        for (int i = 1; i <= max; i++) {
          count[i] += count[i - 1];
        }
    
        for (int i = n - 1; i >= 0; i--) {
          output[count[vet[i].matricula] - 1] = vet[i];
          count[vet[i].matricula]--;
        }
    
        return output;
      }
    
      public static Funcionario[] ordenarPorNomeCountingSort(Funcionario[] vet) {
        int n = vet.length;
    
        int maxLength = 0;
        for (Funcionario fun : vet) {
          maxLength = Math.max(maxLength, fun.nome.length());
        }
    
        Funcionario[] output = new Funcionario[n];
    
        for (int i = maxLength - 1; i >= 0; i--) {
          int[] count = new int[256];
    
          for (Funcionario fun : vet) {
            int index = (i < fun.nome.length()) ? fun.nome.charAt(i) : 0;
            count[index]++;
          }
    
          for (int j = 1; j < count.length; j++) {
            count[j] += count[j - 1];
          }
    
          for (int j = n - 1; j >= 0; j--) {
            int index = (i < vet[j].nome.length()) ? vet[j].nome.charAt(i) : 0;
            output[count[index] - 1] = vet[j];
            count[index]--;
          }
    
          System.arraycopy(output, 0, vet, 0, n);
        }
    
        return vet;
      }
    
      private static int getMax(Funcionario[] vet) {
        int max = vet[0].matricula;
    
        for (int i = 1; i < vet.length; i++) {
          max = Math.max(max, vet[i].matricula);
        }
    
        return max;
      }














// Busca sequencial binária recursiva:
        // passamos como parâmetro o array = vetor e as variáveis menor, maior e nome
        public static int pesquisaRecursivaNome( ArrayList<Funcionario> vetor, int menor, int maior, String nome) {
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
        public static int pesquisaRecursivaMatricula(ArrayList<Funcionario> vetor, int menor, int maior, int matricula) {
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











      //Pesquisar um funcionário usando nome ou código (Versão recursiva da busca sequencial)
      private static int pesquisarRNome(String nome, ArrayList<Funcionario> vetor, int n) {
        if (n == 1) {
            if (vetor.get(0).nome.equals(nome)) {
                return 0;
            } else {
                return -1;
            }
        } else {
            int index = pesquisarRNome(nome, vetor, n - 1);

            if (index < 0) {

                if (vetor.get(n - 1).nome.equals(nome)) {
                    index = n - 1;

                }
            }

            return index;
        }
    }
    //função para consultar funcionário por código (numero) (Versão recursiva da busca sequencial)
    private static int pesquisarRNumero(int numero, ArrayList<Funcionario> vetor, int n) {
        if (n == 1) {
            if (vetor.get(0).matricula == (numero)) {
                return 0;
            } else {
                return -1;
            }
        } else {
            int index = pesquisarRNumero(numero, vetor, n - 1);

            if (index < 0) {
                if (vetor.get(n - 1).matricula == (numero)) {
                    index = n - 1;
                }
            }

            return index;
        }
    }

}


