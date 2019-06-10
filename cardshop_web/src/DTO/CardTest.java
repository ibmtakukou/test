package DTO;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class CardTest {
	@Before
	void pre_check() {

	}

	@Test
	void price_minus() {
		Card c = new Card();
		c.setPrice(100);
		if(c.getPrice()!= 100) {
			fail("error not 100");
		}
	}

}
