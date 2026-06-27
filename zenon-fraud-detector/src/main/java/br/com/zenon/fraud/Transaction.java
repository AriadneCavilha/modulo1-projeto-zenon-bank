package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Objects;

/*
Record veio no Java 16 ou superior
É utilizado para facilitar a criação de uma classe imutável
Ou seja, só setamos os atributos na hora de instanciar o objeto e após isso não conseguimos mais fazer essa
Mudança

Ele vai ter um construtor, um toString, getters porém não vai ter os setters
 */
public record Transaction(int step,
                          PaymentType type,
                          BigDecimal amount,
                          TransactionCustomer origin,
                          TransactionCustomer destin,
                          boolean isFraud,
                          boolean isFlaggedFraud) {

    public Transaction {
        Objects.requireNonNull(type);
        Objects.requireNonNull(origin);
        Objects.requireNonNull(destin);
        Objects.requireNonNull(amount);

        if(step <= 0 ) {
            throw new IllegalArgumentException("O valor de step deve ser positivo: " + step);
        }

        //transforma o bigdecimal levando em consideração o seu sinal
        if(amount.signum() < 0) {
            throw new IllegalArgumentException("O valor de amount deve ser positivo: " + amount);
        }
    }
}
