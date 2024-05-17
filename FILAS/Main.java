public class Main {
    public static void main(String[] args) {
        SistemaAtendimento sistema = new SistemaAtendimento();

        for (int i = 1; i <= 10; i++) {
            sistema.adicionarCliente(new Cliente(i));
        }

        sistema.atenderClientes();
    }
}