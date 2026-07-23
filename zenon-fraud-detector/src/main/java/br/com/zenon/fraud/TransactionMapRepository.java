package br.com.zenon.fraud;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class TransactionMapRepository implements TransactionRepository {

    private final Map<String, Transaction> transactionByOriginName;

    public TransactionMapRepository(List<Transaction> lista) {
        this.transactionByOriginName = lista.stream().collect(Collectors.toMap(transaction -> transaction.origin().name(), Function.identity()));
    }

    @Override
    public Optional<Transaction> searchTransaction(String clientNameOrigin) {
        return Optional.ofNullable(transactionByOriginName.get(clientNameOrigin));
    }
}
