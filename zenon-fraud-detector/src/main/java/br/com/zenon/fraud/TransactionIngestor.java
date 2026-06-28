package br.com.zenon.fraud;


import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

public class TransactionIngestor {

    public List<Transaction> readFile(String fileName) {
        //aqui ele cria uma representação do caminho
        Path path = Path.of(fileName);
        try {
            // aqui ele faz a leitura do arquivo baseado no path
            List<String>lines = Files.readAllLines(path);

            /*
            Para que serve o Map? Ele vai transformar cada elemento da stream em outro valor
            Ele apenas diz:
            “Para cada item que passar por aqui, aplique essa função e devolva o resultado.”
             */
            List<Transaction> transactions = lines
                                            .stream()
                                            .skip(1)
                                            .map(this::parseLines)
                                            .limit(100)
                                            .filter(Optional::isPresent)
                                            .map(Optional::get)
                                            //dps disso como a gente tem certeza que sempre vai ter uma transação, a gente pega o valor do optional
                                            .toList();
            return transactions;
        } catch (Exception e) {
            throw  new RuntimeException(e.getMessage());
        }
    }

    /*
    Com o optional a gente deixa claro que pode ser que não tenha resultado
    dessa transação
     */
    private Optional<Transaction> parseLines(String line) {
        try {
            String[] parts = line.split(",");

            int step = Integer.parseInt(parts[0]);
            PaymentType paymentType = PaymentType.valueOf(parts[1]);

            BigDecimal amount = new BigDecimal(parts[2]);

            String nameOrigin = parts[3];
            BigDecimal oldBalanceOrigin = new BigDecimal(parts[4]);
            BigDecimal newBalanceOrigin = new BigDecimal(parts[5]);
            String nameDestination = parts[6];
            BigDecimal oldBalanceDestination = new BigDecimal(parts[7]);
            BigDecimal newBalanceDestination = new BigDecimal(parts[8]);
            boolean isFraud = transformBoolean(parts[9]);
            boolean isFlaggedFraud = transformBoolean(parts[10]);

            TransactionCustomer transactionCustomerOrigin = new TransactionCustomer(nameOrigin, oldBalanceOrigin, newBalanceOrigin);
            TransactionCustomer transactionCustomerDestination = new TransactionCustomer(nameDestination, oldBalanceDestination, newBalanceDestination);

            return Optional.of(new Transaction(step, paymentType,
                                amount,
                                transactionCustomerOrigin,
                                transactionCustomerDestination,
                                isFraud,
                                isFlaggedFraud));
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            return Optional.empty();
        }
    }

    private boolean transformBoolean(String value) {
        return value.equals("1");
    }
}

