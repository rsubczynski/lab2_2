package lab2_2;

import org.hamcrest.Matchers;
import org.junit.Test;
import pl.com.bottega.ecommerce.sales.domain.invoicing.StandardTax;
import pl.com.bottega.ecommerce.sales.domain.invoicing.Tax;
import pl.com.bottega.ecommerce.sales.domain.invoicing.TaxPolicy;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

import java.math.BigDecimal;
import java.util.Currency;

import static org.junit.Assert.assertThat;

public class Testy_2_2 {

    @Test public void testShouldGiveAnswerMultiplyByTwo() {
        Money money = new Money(100, "PLN");
        assertThat(money.multiplyBy(2), Matchers.is(new Money(200, "PLN")));
    }

    @Test public void testShouldGiveMoneyDivideByTwoSecondTwoParametersConstructor() {
        Money money = new Money(120);
        assertThat(money.multiplyBy(0.5), Matchers.is(new Money(60)));
    }

    @Test public void testShouldGiveSumMoneyTwoArguments() {
        Money money1arg = new Money(60, "PLN");
        Money money2arg = new Money(200, "PLN");
        assertThat(money1arg.add(money2arg), Matchers.is(new Money(260, "PLN")));
    }

    @Test public void testShouldGiveSubtractMoneyTwoArgumentsAndOneCurrency() {
        Money money1arg = new Money(60, "EUR");
        Money money2arg = new Money(20);
        assertThat(money1arg.subtract(money2arg), Matchers.is(new Money(40, "EUR")));
    }

    @Test public void testShouldGiveSubtractMoneyTwoArgumentsAndTwoCurrency() {
        Money money1arg = new Money(60, "PLN");
        Money money2arg = new Money(20, "PLN");
        assertThat(money1arg.subtract(money2arg), Matchers.is(new Money(40, "PLN")));
    }

    @Test public void testShouldGiveSumMoneyWithCurrencyCodeEur() {
        Money money1arg = new Money(60);
        Money money2arg = new Money(20);
        assertThat(money1arg.add(money2arg), Matchers.is(new Money(80, "EUR")));
    }

    @Test public void testShouldExceptionThrownByAddBecauseOfDifferentCurrencies() {
        Money money1arg = new Money(1223, "EUR");
        Money money2arg = new Money(20, "PLN");
        boolean result = true;
        try {
            money1arg.add(money2arg);
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertThat(result, Matchers.is(false));
    }

    @Test public void testShouldExceptionThrownBySubtractionBecauseOfDifferentCurrencies() {
        Money money1arg = new Money(56418, "EUR");
        Money money2arg = new Money(20, "PLN");
        boolean result = true;
        try {
            money1arg.subtract(money2arg);
        } catch (IllegalArgumentException e) {
            result = false;
        }
        assertThat(result, Matchers.is(false));
    }

    @Test(expected = IllegalArgumentException.class)
    public void TestShouldAddingTwoDifferentCurrenciesThrowsException() {
        Money moneyEUR = new Money(10, "EUR");
        Money moneyPLN = new Money(20, "PLN");
        moneyEUR.add(moneyPLN);
    }

    @Test public void testShouldDrugTaxPolicyTestPositive() {
        TaxPolicy tax = new StandardTax();
        Currency currency = Currency.getInstance("USD");
        Tax actualTax = tax.calculateTax(ProductType.FOOD, new Money(new BigDecimal(100), currency));
        Tax expectedTax = new Tax(new Money(new BigDecimal(7), currency), "5% (D)");

        assertThat(actualTax.getAmount(), Matchers.is(expectedTax.getAmount()));
    }
}
