import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

class SistemaAtendimento {
    private final Queue<Cliente> filaClientes = new LinkedList<>();
    private final Funcionario[] funcionarios;

    public SistemaAtendimento() {
        Random random = new Random();
        int numFuncionarios = random.nextInt(10) + 1; // Número aleatório de funcionários entre 1 e 10
        this.funcionarios = new Funcionario[numFuncionarios];
        for (int i = 0; i < numFuncionarios; i++) {
            this.funcionarios[i] = new Funcionario(i + 1);
        }
    }

    public void adicionarCliente(Cliente cliente) {
        filaClientes.add(cliente);
        System.out.println("Cliente " + cliente.getId() + " entrou na fila de espera.");
    }

    public void atenderClientes() {
        while (!filaClientes.isEmpty()) {
            Cliente cliente = filaClientes.poll();
            Funcionario funcionario = getFuncionarioDisponivel();
            if (funcionario != null) {
                System.out.println("Atendendo Cliente " + cliente.getId() + "...");
                atenderCliente(cliente, funcionario);
                // Marcar o funcionário como disponível novamente
                for (int i = 0; i < funcionarios.length; i++) {
                    if (funcionarios[i] == null) {
                        funcionarios[i] = funcionario;
                        break;
                    }
                }
            }
        }
        System.out.println("Todos os clientes foram atendidos.");
    }

    private void atenderCliente(Cliente cliente, Funcionario funcionario) {
        int tempoAtendimento = new Random().nextInt(5) + 1; // Tempo de atendimento aleatório entre 1 e 5 segundos
        System.out.println("Cliente " + cliente.getId() + " atendido pelo Funcionário " + funcionario.getId() + " com o tempo de " + tempoAtendimento + " segundos.");
        esperar(tempoAtendimento);
    }

    private Funcionario getFuncionarioDisponivel() {
        Random random = new Random();
        Funcionario funcionario = null;
        while (funcionario == null) {
            int index = random.nextInt(funcionarios.length);
            if (funcionarios[index] != null) {
                funcionario = funcionarios[index];
                funcionarios[index] = null; // Marcar o funcionário como ocupado
            }
        }
        return funcionario;
    }

    private void esperar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}