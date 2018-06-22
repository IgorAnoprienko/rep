package webview;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import javax.swing.table.TableModel;

import controller.JpaController;
import model.Book;
import model.Customer;
import model.IModel;
import model.Orders;
import model.Provider;
import model.Supply;

/**
 * Servlet implementation class ExecuteOperation
 */
@WebServlet("/ExecuteOperation")
public class ExecuteOperation extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ExecuteOperation() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType ("text/html; charset=UTF-8");
		String tableName = (String) session.getAttribute("tableName");
		JpaController controller = (JpaController) session.getAttribute("controller");
		String className = "model." + tableName;
		String operation = (String) session.getAttribute("operation");
		if (operation.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("ID"));
			controller.delete(id, tableName);
		} else {
			IModel obj = null;
			try {
				obj = (IModel) Class.forName(className).newInstance();
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (obj instanceof Book) {
				String name = request.getParameter("bookName");
				
				((Book) obj).setName(name);
				String author = request.getParameter("bookAuthor");
				((Book) obj).setAuthor(author);
				String pb = request.getParameter("bookPublishing");
				((Book) obj).setPublishing_house(pb);
				Float price = Float.parseFloat(request.getParameter("bookPrice"));
				((Book) obj).setPrice(price);
			} else if (obj instanceof Provider) {
				String name = request.getParameter("providerName");
				((Provider) obj).setName(name);
				String city = request.getParameter("providerCity");
				((Provider) obj).setCity(city);
				String address = request.getParameter("providerAddress");
				((Provider) obj).setAddress(address);
				String phone = request.getParameter("providerPhone");
				((Provider) obj).setPhone(phone);
			} else if (obj instanceof Customer) {
				String name = request.getParameter("customerName");
				((Customer) obj).setName(name);
				String address = request.getParameter("customerAddress");
				((Customer) obj).setAddress(address);
				String phone = request.getParameter("customerPhone");
				((Customer) obj).setPhone(phone);
			} else if (obj instanceof Orders) {
				Date date = Date.valueOf(request.getParameter("dateOrder"));
				((Orders) obj).setDate(date);
				
				if (request.getParameter("paidOrder")!= null) {
					((Orders) obj).setPaid(true);
				} else{
					((Orders) obj).setPaid(false);
				}
				
					int bookID = Integer.parseInt(request.getParameter("bookID"));
					Book book = (Book) (findById(bookID, controller, "Book"));
					((Orders) obj).setBook(book);
					int customerID = Integer.parseInt(request.getParameter("customerID"));
					Customer cr = (Customer) (findById(customerID, controller, "Customer"));
					((Orders) obj).setCustomer(cr);
			

			} else if (obj instanceof Supply) {
				int count = Integer.parseInt(request.getParameter("bookCount"));
				((Supply) obj).setCount(count);
				Date date = Date.valueOf(request.getParameter("dateSupply"));
				((Supply) obj).setDate(date);

				
					int bookID = Integer.parseInt(request.getParameter("bookID"));
					Book book = (Book) (findById(bookID, controller, "Book"));
					((Supply) obj).setBook(book);
					int providerID = Integer.parseInt(request.getParameter("providerID"));
					Provider pr = (Provider) (findById(providerID, controller, "Provider"));
					((Supply) obj).setProvider(pr);
				
			}
			switch (operation) {
			case "edit":
				if(request.getParameter("ID")!= null){
				int id = Integer.parseInt(request.getParameter("ID"));
				controller.edit(id, obj,className);
				}else return;
				break;
			case "add":
				controller.add(obj);
			}
		}

		TableModel tableModel = controller.getModel(tableName);
		session.setAttribute("tableModel", tableModel);
		request.getRequestDispatcher("showTable.jsp").forward(request, response);

	}

	private IModel findById(int id, JpaController controller, String className) {

		try {
			IModel obj = null;
			Class clazz = Class.forName("model." + className);
			for (Object x : controller.getObjectList(clazz)) {
				obj = (IModel) x;
				if (obj.getId() == id)
					return obj;
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
