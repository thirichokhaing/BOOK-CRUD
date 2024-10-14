package com.example.bookcrud.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.bookcrud.db.DatabaseConnection;
import com.example.bookcrud.model.bookDto.Book;
import com.example.bookcrud.model.bookDto.Order;
import com.example.bookcrud.model.orderDto.BookOrder;
import com.example.bookcrud.model.orderDto.OrderDetails;
import com.example.bookcrud.model.orderDto.OrderDetailsDto;
import com.example.bookcrud.servlet.order.OrderListServlet;

public class BookOrderDao {
	private static final String INSERT_ORDER = "insert into book_order (order_date) values (?)";
	private static final String INSERT_BOOK = "insert into order_details (order_id, book_id) values (?,?)";
	private static final String FIND_ALL_ORDER_DETAILS = "select * from order_details";
	private static final String FIND_ORDER_DETAILS_BY_ID = "select * from order_details where id = ?";
	private static final String FIND_ORDER_DETAILS_BY_ORDER_ID = "select book.title,author.name,book.price,book_order.order_date from book inner join author on author.id = book.author_id inner join order_details on order_details.book_id = book.id inner join book_order on book_order.id = order_details.order_id where order_details.order_id = ?";
	private static final String FIND_ALL_BOOK_ORDER = "select id,order_date from book_order where order_status = false";
	private static final String CONFIRM_ORDER_STATUS = "update book_order inner join order_details on order_details.order_id = book_order.id set order_status = true where order_details.order_id= ?";
	private static final String FIND_BOOK_BY_ORDER_ID = " select book.title from book inner join order_details on order_details.book_id = book.id where order_details.order_id = ?";
	
	private static final String FIND_ALL_ORDER = " select book_order.id,book_order.order_date,count(book.title) as count from book_order join order_details on order_details.order_id = book_order.id join book on order_details.book_id = book.id where book_order.order_status = false group by book_order.id,book_order.order_date";

	private Connection getConnection() throws SQLException {
		return DatabaseConnection.getInstance().getConnection();
	}
	
	public List<Order> findAllOrders()throws SQLException{
		List<Order> orderList = new ArrayList<>();
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_ALL_ORDER)){
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int orderId = rs.getInt("id");
				Date orderDate = rs.getDate("order_date");
				int count = rs.getInt("count");
				orderList.add(new Order(orderId, orderDate, count));
			}
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		return orderList;
	}
	public void insertOrder(BookOrder order) {
		try (Connection conn = getConnection();
				PreparedStatement stmt = conn.prepareStatement(INSERT_ORDER, Statement.RETURN_GENERATED_KEYS)) {
			stmt.setDate(1, new Date(order.getOrderDate().getTime()));
			stmt.executeUpdate();

			ResultSet rs = stmt.getGeneratedKeys();

			while (rs.next()) {
				order.setId(rs.getInt(1));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void insertBookToOrder(OrderDetails orderDetails) {
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT_BOOK)) {
			stmt.setInt(1, orderDetails.getOrderId());
			stmt.setInt(2, orderDetails.getBookId());

			stmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public List<OrderDetails> findAllOrderDetails() throws SQLException{
		List<OrderDetails> orderDetailsList = new ArrayList<>();
		try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_ALL_ORDER_DETAILS)) {
			
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				int id = rs.getInt("id");
				int orderId = rs.getInt("order_id");
				int bookId = rs.getInt("book_id");
				orderDetailsList.add(new OrderDetails(id, orderId, bookId));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderDetailsList;
	}
	
	public OrderDetails findOrderDetailsById(int id)throws SQLException{
	OrderDetails orderDetails = null;
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_ORDER_DETAILS_BY_ID)){
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				int orderDetailsId = rs.getInt("id");
				int orderId = rs.getInt("order_id");
				int bookId = rs.getInt("book_id");
				orderDetails = new OrderDetails(orderDetailsId, orderId, bookId);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return orderDetails;
	}
	
	public List<OrderDetailsDto> findOrderdetailsByOrderId(int id)throws SQLException {
		List<OrderDetailsDto> detailsList = new ArrayList<>();
		
		try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_ORDER_DETAILS_BY_ORDER_ID)) {
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				String title = rs.getString("title");
				String authorName = rs.getString("name");
				Double price = rs.getDouble("price");
				Date orderDate = rs.getDate("order_date");
				detailsList.add(new OrderDetailsDto(title, price, authorName, orderDate));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return detailsList;
	}
	
	public List<OrderDetailsDto> findAllBookOrders(){
		List<OrderDetailsDto> orderDetails = new ArrayList<>();
		
		try(Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_ALL_BOOK_ORDER)) {
		ResultSet rs = stmt.executeQuery();
		while(rs.next()) {
			
			int orderId = rs.getInt("id");
			Date orderDate = rs.getDate("order_date");
			orderDetails.add(new OrderDetailsDto(orderId, orderDate));
			
		}
			
		} catch (Exception e) {
		e.printStackTrace();
		}
		
		return orderDetails;
	}
	
	public void confirmOrderStatus(int id) {
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(CONFIRM_ORDER_STATUS)){
			stmt.setInt(1,id);
			stmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Book> findAllBookByOrderId(int orderId)throws SQLException {
		List<Book> books = new ArrayList<>();
		try (Connection conn = getConnection(); PreparedStatement stmt = conn.prepareStatement(FIND_BOOK_BY_ORDER_ID)){
			stmt.setInt(1, orderId);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
			String title = rs.getString("title");
			books.add(new Book(title));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return books;
	}
}
