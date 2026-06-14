package br.com.zenon.fraud;

import java.math.BigDecimal;

public record Transaction(int step,
                          PaymentType type,
                          BigDecimal amount,
                          TransactionCustomer origin,
                          TransactionCustomer destin,
                          boolean isFraud,
                          boolean isFlaggedFraud) {


}
