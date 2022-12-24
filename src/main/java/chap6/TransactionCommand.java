package chap6;

import static java.util.stream.Collectors.groupingBy;

import chap5.Trader;
import chap5.Transaction;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Currency;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TransactionCommand {

    public static void main(String[] args) {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        List<Transaction> transactions = Arrays.asList(
            new Transaction(brian, 2011, 300, Currency.getInstance("USD")),
            new Transaction(raoul, 2012, 1000, Currency.getInstance("KRW")),
            new Transaction(raoul, 2011, 400, Currency.getInstance("EUR")),
            new Transaction(mario, 2012, 710, Currency.getInstance("USD")),
            new Transaction(mario, 2012, 700, Currency.getInstance("KRW")),
            new Transaction(alan, 2012, 950, Currency.getInstance("EUR")));
        Map<Currency, List<Transaction>> transactionsByCurrencies = transactions.stream()
                .collect(groupingBy(Transaction::getCurrency));
        System.out.println("transactionsByCurrencies = " + transactionsByCurrencies);
    }
}
