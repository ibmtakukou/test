package DTO;

/**
 * Card - 1枚のトレーディングカードを表すDTOです。
 *
 */
public class Card {
	private String code;
	private String name;
	private int rare;
	private int price;

	public Card() {
		;
	}
	public Card(String code, String name, int rare, int price) {
		this.setCode(code);
		this.setName(name);
		this.setRare(rare);
		this.setPrice(price);
	}

	public int getPrice() {
		return this.price;
	}

	public void setPrice(int newPrice) {
		if(newPrice < 0) {
			throw new IllegalArgumentException();
		}
		this.price = newPrice;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if(code.matches("[A-Z][0-9]{3}") == false) {
			throw new IllegalArgumentException();
		}
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name.length() < 3) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	public int getRare() {
		return rare;
	}

	public void setRare(int rare) {
		if(rare < 1 || rare > 3) {
			throw new IllegalArgumentException();
		}
		this.rare = rare;
	}
}
