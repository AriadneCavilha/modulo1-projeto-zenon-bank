package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Objects;

public record TransactionCustomer(String name, BigDecimal oldBalance, BigDecimal newBalance) {

    public TransactionCustomer {
        Objects.requireNonNull(name);
        Objects.requireNonNull(oldBalance);
        Objects.requireNonNull(newBalance);

        if(name.isEmpty()) throw new IllegalArgumentException("O nome de origem do cliente não pode ser vazio");
        if(oldBalance.signum() < 0) throw new IllegalArgumentException("O valor de oldBalance deve ser positivo: " + oldBalance);
        if(newBalance.signum() < 0) throw new IllegalArgumentException("O valor de newBalance deve ser positivo: " + newBalance);
    }

    @Override
    public String toString() {
        return "TransactionCustomer{" +
                "name='" + name + '\'' +
                ", oldBalance=" + oldBalance +
                ", newBalance=" + newBalance +
                '}';
    }
}
