package br.com.zenon.fraud;

import java.math.BigDecimal;

public class Main {
    static void main() {
        Transaction transactionOne = new Transaction(1, PaymentType.PAYMENT,
                                                    new BigDecimal("9839.64"),
                                                    new TransactionCustomer("C1231006815", new BigDecimal("170136.0"), new BigDecimal("160296.36")),
                                                    new TransactionCustomer("M1979787155", new BigDecimal("0.0"), new BigDecimal("0.0")),
                                             false,
                                       false);


        Transaction transactionTwo = new Transaction(743, PaymentType.CASHOUT,
                new BigDecimal("850002.52"),
                new TransactionCustomer("C1280323807", new BigDecimal("850002.52"), new BigDecimal("0.0")),
                new TransactionCustomer("C873221189", new BigDecimal("6510099.11"), new BigDecimal("7360101.63")),
                true,
                false);

        System.out.println(transactionOne);
        IO.println(transactionOne);
    }
}
