package br.com.zenon.fraud;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class FraudAnalyzer {

    private List<Transaction> transactions;

    public FraudAnalyzer(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public int fraudTransactions() {
        return transactions.stream().filter(Transaction::isFraud).toList().size();
    }

    public List<BigDecimal> topTreeAmountFrauds() {
        return transactions.stream().filter(Transaction::isFraud)
                                    .sorted(Comparator.comparing(Transaction::amount).reversed())
                                    .limit(3)
                                    .map(Transaction::amount)
                                    .toList();
    }

    public List<String> findTopSuspiciousClients() {
        return transactions.stream().filter(Transaction::isFraud)
                                        .sorted(Comparator.comparing(Transaction::amount).reversed())
                                        .map(t -> t.origin().name())
                                        .distinct()
                                        .limit(5)
                                        .toList();
    }

    public BigDecimal totalAmountFrauds() {
        return transactions.stream().filter(Transaction::isFraud)
                                    .map(Transaction::amount)
                                    .reduce(BigDecimal.ZERO, BigDecimal::add);

        /*
        No reduce vamos ter o valor inicial, no qual começa a acumulação -> 0
        E dps temos o acumulador que vai recebendo a soma a cada passo
         */
    }

    public Map<PaymentType, Long> fraudsByTypeOfTransaction() {
        return transactions.stream().filter(Transaction::isFraud)
                                    .collect(Collectors.groupingBy(Transaction::type, Collectors.counting()));
        /**
         * O grouping by serve para separar os elementos em grupos com base em um chave, nesse caso é o transaction type
            Depois de agrupar ele diz o que fazer com cada grupo, então o segundo parametro vai ser para transformar vários itens
         em um valor final, então ou: soma, contagem, máximo ou mínimo por exemplo. Aqui nesse caso nós vamos somar a qtd de fraudes por grupo
         */
    }


}
