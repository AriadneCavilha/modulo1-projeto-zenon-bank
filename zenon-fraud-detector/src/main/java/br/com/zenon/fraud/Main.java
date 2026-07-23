package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    static void main() throws Exception {
        Transaction transactionOne = new Transaction(1, PaymentType.PAYMENT,
                                                    new BigDecimal("9839.64"),
                                                    new TransactionCustomer("C1231006815", new BigDecimal("170136.0"), new BigDecimal("160296.36")),
                                                    new TransactionCustomer("M1979787155", new BigDecimal("0.0"), new BigDecimal("0.0")),
                                             false,
                                       false);


        Transaction transactionTwo = new Transaction(743, PaymentType.CASH_OUT,
                                    new BigDecimal("850002.52"),
                                    new TransactionCustomer("C1280323807", new BigDecimal("850002.52"), new BigDecimal("0.0")),
                                    new TransactionCustomer("C873221189", new BigDecimal("6510099.11"), new BigDecimal("7360101.63")),
                                    true,
                                    false);

        System.out.println(transactionOne);
        IO.println(transactionOne);

        TransactionIngestor ingestor = new TransactionIngestor();

        System.out.println("------------TRAINING-BENCHMARK--------------");
        List<Transaction> list = ingestor.readFile("data/payment_data.csv", 100000);
        FraudAnalyzer fraudAnalyzer = new FraudAnalyzer(list);
        TransactionListRepository listRepository = new TransactionListRepository(list);

        System.out.println(listRepository.searchTransaction("C12345"));
        long startTime = System.nanoTime();
        listRepository.searchTransaction("C1231006815");
        long endTime = System.nanoTime();

        System.out.println("Tempo total: " + (endTime - startTime));

        TransactionMapRepository mapRepository = new TransactionMapRepository(list);


        System.out.println(mapRepository.searchTransaction("C12345"));
        long startTimeMap = System.nanoTime();
        mapRepository.searchTransaction("C1231006815");
        long endTimeMap = System.nanoTime();

        System.out.println("Tempo total: " + (endTimeMap - startTimeMap));

    }
}
