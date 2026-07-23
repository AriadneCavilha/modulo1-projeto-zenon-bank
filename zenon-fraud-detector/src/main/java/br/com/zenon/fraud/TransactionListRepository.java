package br.com.zenon.fraud;

import com.sun.java.accessibility.util.Translator;

import java.util.List;
import java.util.Optional;

public class TransactionListRepository implements TransactionRepository {
    private List<Transaction> lista;

    TransactionListRepository(List<Transaction> lista) {
        this.lista = lista;
    }

    @Override
    public Optional<Transaction> searchTransaction(String clientNameOrigin) {
        return lista.stream().filter(t -> t.origin().name().equalsIgnoreCase(clientNameOrigin)).findAny();
    }
}
