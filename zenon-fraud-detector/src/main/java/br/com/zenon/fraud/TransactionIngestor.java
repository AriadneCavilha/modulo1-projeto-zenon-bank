package br.com.zenon.fraud;

import java.io.BufferedReader;
import java.io.FileReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionIngestor {

    public List<Transaction> readFile(String fileName) throws Exception {
        List<Transaction> lista = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            br.readLine();
            for(int i = 0 ; i < 1000 ; i++) {
                    List<String> separadorRegistros = List.of(br.readLine().split(","));

                    int step = Integer.parseInt(separadorRegistros.get(0));
                    PaymentType payment = PaymentType.valueOf(separadorRegistros.get(1));
                    BigDecimal amount = new BigDecimal(separadorRegistros.get(2));
                    String nameOrigin = separadorRegistros.get(3);
                    BigDecimal oldBalanceOrigin = new BigDecimal(separadorRegistros.get(4));
                    BigDecimal newBalanceOrigin = new BigDecimal(separadorRegistros.get(5));
                    String nameDest = separadorRegistros.get(6);
                    BigDecimal oldBalanceDest = new BigDecimal(separadorRegistros.get(7));
                    BigDecimal newBalanceDest = new BigDecimal(separadorRegistros.get(8));
                    boolean isFraud = transformadorBooleano(Integer.parseInt(separadorRegistros.get(9)));
                    boolean isFlaggedFraud = transformadorBooleano(Integer.parseInt(separadorRegistros.get(10)));

                    Transaction transaction = new Transaction(step, payment, amount,
                            new TransactionCustomer(nameOrigin, oldBalanceOrigin, newBalanceOrigin),
                            new TransactionCustomer(nameDest, oldBalanceDest, newBalanceDest),
                            isFraud, isFlaggedFraud);

                    lista.add(transaction);
            }

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        return lista;
    }

    public boolean transformadorBooleano(int valor) {
        return valor == 0 ? false : true;
    }
}
