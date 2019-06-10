package DTO;

import java.util.ArrayList;
import java.util.Scanner;

import DAO.CardsDAO;

public class God0604 {

	public static void main(String[] args) {
		System.out.println("ぱちもんカードショップ へようこそ");
		System.out.println("どうしますか？ 0:商品DB初期化 1:商品一覧を見る 2:特定の商品を見る 3:商品を追加 4:商品を削除 5:商品更新");

		int i = new Scanner(System.in).nextInt();
		if(i == 0) {
			resetDB();
		} else if(i == 1) {
			showAllCards();
		} else if(i == 2) {
			showCard();
		} else if(i == 3) {
			addCard();
		} else if(i == 4) {
			removeCard();
		} else if(i == 5) {
			changeCard();
		}
	}

	private static void resetDB() {
		System.out.print("＜DBリセット＞ ");
		// DAOに依頼してＤＢをリセットする
		CardsDAO cdao = new CardsDAO();
		cdao.initialize();
		System.out.print("リセット完了しました");
	}

	private static void addCard() {
		System.out.print("＜新規カード商品の追加＞ ");
		System.out.print("商品コードを入力： ");
		String newcode = new Scanner(System.in).nextLine();
		System.out.print("カード名を入力： ");
		String newname = new Scanner(System.in).nextLine();
		System.out.print("レアを入力（1,2,3）： ");
		int newrare = new Scanner(System.in).nextInt();
		System.out.print("価格を入力： ");
		int newprice = new Scanner(System.in).nextInt();
		CardsDAO cdao = new CardsDAO();
		cdao.add(newcode, newname, newrare, newprice);
		System.out.print("登録完了しました ");
	}

	private static void showCard() {
		System.out.print("＜特定カード情報の表示＞ ");
		System.out.println("＜検索方法1:コード　2:名前＞ ");
		String selectnum = new Scanner(System.in).nextLine();

		if (selectnum.equals("1")) {
			System.out.print("表示したい商品コードを入力： \n");
			String code = new Scanner(System.in).nextLine();
			CardsDAO cdao = new CardsDAO();
			ArrayList<Card> cards = cdao.findbycode(code);
			for(Card c:cards) {
				System.out.print(c.getCode()+"\t");
				System.out.print(c.getName()+"\t");
				System.out.print(c.getRare()+"\t");
				System.out.print(c.getPrice()+"\t");
			}
		}
		if(selectnum.equals("2")) {
			System.out.print("表示したい商品名前を入力： \n");
			String name = new Scanner(System.in).nextLine();
			CardsDAO cdao = new CardsDAO();
			ArrayList<Card> cards = cdao.findbyname(name);
			for(Card c:cards) {
				System.out.print(c.getCode()+"\t");
				System.out.print(c.getName()+"\t");
				System.out.print(c.getRare()+"\t");
				System.out.print(c.getPrice()+"\t");
			}
		}

		System.out.print("カード情報の表示完了");
	}

	private static void showAllCards() {
		System.out.print("＜カード一覧の表示＞ \n");
		CardsDAO cdao = new CardsDAO();
		cdao.findAll();
		System.out.print("カード一覧の表示完了");
	}

	private static void removeCard() {
		System.out.print("削除したい商品コードを入力： \n");
		String code = new Scanner(System.in).nextLine();
		CardsDAO cdao = new CardsDAO();
		cdao.remove(code);
		System.out.print("商品の削除完了");
	}

	private static void changeCard() {
		System.out.print("＜商品の更新＞ ");
		System.out.print("商品コードを入力： ");
		String searchcode = new Scanner(System.in).nextLine();
		System.out.print("商品の新コードを入力： ");
		String newcode = new Scanner(System.in).nextLine();
		System.out.print("カードの新名を入力： ");
		String newname = new Scanner(System.in).nextLine();
		System.out.print("新レアを入力（1,2,3）： ");
		int newrare = new Scanner(System.in).nextInt();
		System.out.print("新価格を入力： ");
		int newprice = new Scanner(System.in).nextInt();
		CardsDAO cdao = new CardsDAO();
		cdao.change(searchcode, newcode, newname, newrare, newprice);
		System.out.print("登録完了しました ");
	}
}
