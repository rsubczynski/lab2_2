package pl.com.bottega.ecommerce.sharedkernel;

import static org.junit.Assert.assertThat;

import org.hamcrest.Matchers;
import org.junit.Test;

public class MoneyTest {

	@Test
	public void multiplyingByTwoIsCorrect() {
		Money money = new Money(10, "PLN");
		assertThat(money.multiplyBy(2), Matchers.is(new Money(20, "PLN")));
	}

	@Test
	public void multiplyingByZeroIsCorrect() {
		Money money = new Money(10, "PLN");
		assertThat(money.multiplyBy(0), Matchers.is(Money.ZERO));
	}

	@Test
	public void addingTwentyEuroToTenEuroIsCorrect() {
		Money money = new Money(10, "EUR");
		Money otherMoney = new Money(20, "EUR");
		assertThat(money.add(otherMoney), Matchers.is(new Money(30, "EUR")));
	}

	@Test
	public void subtractingTenEuroFromTwentyEuroIsCorrect() {
		Money money = new Money(20, "EUR");
		Money otherMoney = new Money(10, "EUR");
		assertThat(money.subtract(otherMoney), Matchers.is(new Money(10, "EUR")));
	}

	@Test(expected = IllegalArgumentException.class)
	public void addingTwoDifferentCurrenciesThrowsException() {
		Money moneyEUR = new Money(10, "EUR");
		Money moneyPLN = new Money(20, "PLN");
		moneyEUR.add(moneyPLN);
	}

	@Test
	public void roundingMoneyIsCorrect() {
		Money money = new Money(0.005);
		assertThat(money, Matchers.is(new Money(0.01)));
	}
}