package DAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Card;

/**
 * CardsDAO - CARDSテーブルへのアクセスを引き受けるクラス
 *
 * このクラスは、CARDSテーブルへの様々なSQL送信を受け持ちます。
 * Mainクラス（Godクラス）などを作る人は、SQLの知識がなくとも、
 * このクラスのメソッドを呼び出すだけで、DBを操作できて便利です。
 *
 * CARDSテーブルの１行の情報は、DTO(Data Transfer Object)である
 * Cardクラスにいれてやりとりします。
 *
 */
public class CardsDAO {
	// DB接続に使う各種の情報
	String fqcn = "org.postgresql.Driver";
	String url = "jdbc:postgresql://localhost:5432/postgres";
	String user = "postgres";
	String password = null;

	// テーブルを初期化し、初期データを入れるメソッド
	public void initialize() {
		System.out.println("DBテーブルを初期化します");
		// STEP0-ドライバのロード
		try {
			Class.forName(fqcn);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		// DBに接続（try-with-resources使用）
		try(Connection con = DriverManager.getConnection(this.url, this.user, this.password)) {
			con.setAutoCommit(false);
			PreparedStatement pstmt =
				con.prepareStatement(
				" DROP TABLE IF EXISTS CARDS ");
			pstmt.executeUpdate();

			pstmt = con.prepareStatement(
				" CREATE TABLE CARDS ( " +
				"   CODE CHAR(4), " +
				"   NAME VARCHAR(32), " +
				"   RARE INTEGER, " +
				"   PRICE INTEGER " +
				" )");
			pstmt.executeUpdate();

			// INSERT文のテンプレをつくって...
			pstmt = con.prepareStatement(
					" INSERT INTO CARDS VALUES (?,?,?,?) ");

			// ?に値を流し込んで実行、を繰り返す
			pstmt.setString(1, "P001");
			pstmt.setString(2, "ぴかちう");
			pstmt.setInt(3, 2);
			pstmt.setInt(4,  1200);
			pstmt.executeUpdate();

			pstmt.setString(1, "Z021");
			pstmt.setString(2, "ぜにがめ");
			pstmt.setInt(3, 1);
			pstmt.setInt(4,  800);
			pstmt.executeUpdate();

			pstmt.setString(1, "M099");
			pstmt.setString(2, "みゅー");
			pstmt.setInt(3, 3);
			pstmt.setInt(4,  9200);
			pstmt.executeUpdate();

			con.commit();
			System.out.println("DBテーブルの初期化完了");
		} catch(SQLException e) {
			e.printStackTrace();
		}

		}

	public ArrayList<Card> findAll() {
		// connect to database;
		try {
			Class.forName(this.fqcn);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Card> cards = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(this.url, this.user, this.password)) {
			con.setAutoCommit(false);
			String sql="select * from cards";
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
		    int columnCount = rsmd.getColumnCount();
		    while (rs.next()){
		        Card c = new Card(rs.getString(1),rs.getString(2),Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4)));
		        cards.add(c);
		    }
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return cards;
	}

	public ArrayList<Card> findbycode(String code) {
		try {
			Class.forName(this.fqcn);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Card> cards = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(this.url, this.user, this.password)) {
			con.setAutoCommit(false);
			String sql="select * from cards where code=?";
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, code);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
		    int columnCount = rsmd.getColumnCount();
		    while (rs.next()){
		        Card c = new Card(rs.getString(1),rs.getString(2),Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4)));
		        cards.add(c);
		    }
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return cards;
	}

	public void add(String newcode, String newname, int newrare, int newprice) {
		Card card = new Card();
		card.setCode(newcode);
		card.setName(newname);
		card.setRare(newrare);
		card.setPrice(newprice);
		// connect to database;
		try {
			Class.forName(this.fqcn);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}

		try(Connection con = DriverManager.getConnection(this.url, this.user, this.password)) {
			con.setAutoCommit(false);
			String sql="insert into cards values(?,?,?,?)";
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, newcode);
			pstmt.setString(2, newname);
			pstmt.setInt(3, newrare);
			pstmt.setInt(4,  newprice);
			pstmt.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(String code) {
		try {
			Class.forName(this.fqcn);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection(this.url, this.user, this.password)) {
			con.setAutoCommit(false);
			String sql="delete from cards where code=?";
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, code);
			pstmt.executeUpdate();
			con.commit();

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public void change(String searchcode,String newcode, String newname, int newrare, int newprice) {
		try {
			Class.forName(this.fqcn);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection(this.url, this.user, this.password)) {
			con.setAutoCommit(false);
			String sql="update cards set code=?, name=?, rare=?, price=? where code = ?";

			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, newcode);
			pstmt.setString(2, newname);
			pstmt.setInt(3, newrare);
			pstmt.setInt(4,  newprice);
			pstmt.setString(5, searchcode);
			pstmt.executeUpdate();
			con.commit();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Card> findbyname(String name) {
		try {
			Class.forName(this.fqcn);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		ArrayList<Card> cards = new ArrayList<>();
		try(Connection con = DriverManager.getConnection(this.url, this.user, this.password)) {
			con.setAutoCommit(false);
			String sql="select * from cards where name=?";
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData rsmd = rs.getMetaData();
		    int columnCount = rsmd.getColumnCount();
		    while (rs.next()){
		        Card c = new Card(rs.getString(1),rs.getString(2),Integer.parseInt(rs.getString(3)),Integer.parseInt(rs.getString(4)));
		        cards.add(c);
		    }
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return cards;
	}

	public void resetDB() {
		try {
			Class.forName(this.fqcn);
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		try(Connection con = DriverManager.getConnection(this.url, this.user, this.password)) {
			con.setAutoCommit(false);
			String sql="delete from cards";
			PreparedStatement pstmt=(PreparedStatement) con.prepareStatement(sql);
			pstmt.executeUpdate();
			con.commit();

		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
