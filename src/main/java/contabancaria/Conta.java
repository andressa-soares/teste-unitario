package contabancaria;

import java.nio.channels.IllegalSelectorException;

public class Conta {

    private String titular;
    private double saldo;
    private boolean ativa;

    public String getTitular() {
        return titular;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public Conta(String titular, double saldoInicial) {
        if (titular == null || titular.isBlank())
            throw new IllegalArgumentException("O titular não pode ser nulo ou vazio.");
        if (saldoInicial < 0)
            throw new IllegalArgumentException("O saldo inicial não pode ser negativo.");

        this.titular = titular;
        this.saldo = saldoInicial;
        this.ativa = true;
    }

    public Conta(String titular) {
        this(titular, 0);
    }

    public void depositar(double valor) {
        if (!isAtiva())
            throw new IllegalStateException("A conta está inativa.");

        isSaldoValid(valor);

        this.saldo += valor;
    }

    public void sacar(double valor) {
        if (!isAtiva())
            throw new IllegalStateException("A conta está inativa.");

        isSaldoValid(valor);

        if (valor > this.saldo)
            throw new IllegalStateException("Saldo insuficiente para o saque.");

        this.saldo -= valor;
    }

    public void transferir(Conta destino, double valor) {
        if (!isAtiva())
            throw new IllegalStateException("A conta origem está inativa.");
        if (destino.ativa == false)
            throw new IllegalStateException("A conta destino está inativa.");

        isSaldoValid(valor);

        if (this.saldo < valor)
            throw new IllegalStateException("Saldo insuficiente para transferência.");

        this.saldo -= valor;
        destino.saldo += valor;
    }

    public void encerrar() {
        if (!isAtiva())
            throw new IllegalStateException("A conta já foi encerrada.");
        if (saldo > 0)
            throw new IllegalStateException("A conta deve estar com saldo zerado para encerramento.");

        this.ativa = false;
    }

    void isSaldoValid(double saldo) {
        if (saldo <= 0)
            throw new IllegalArgumentException("O valor deve ser maior que zero.");
    }
}
