package Serlvets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EstacionamentoDAO;
import Model.Estaciona;

/**
 * Servlet implementation class UpdateServlet
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EstacionamentoDAO estacionamentoDAO = new EstacionamentoDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UpdateServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		String id = request.getParameter("id");

		if (id != null) {
			try {
				Integer code = Integer.parseInt(id);
				Estaciona estaciona = estacionamentoDAO.getEstacionaById(code);
				EstacionamentoDAO.confirmOut(estaciona);
				
				request.setAttribute("success", "Saida confirmada!");
				request.setAttribute("estacionamentos", estacionamentoDAO.getEstaciona(false));
				RequestDispatcher dispatcher = request.getRequestDispatcher("saida.jsp");
				dispatcher.forward(request, response);
			
			} catch (Exception e) {
				// TODO: handle exception
			}
		} else {
			request.setAttribute("error", "Erro!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("saida.jsp");
			dispatcher.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		doGet(request, response);

		String preco = request.getParameter("preco");
		String id = request.getParameter("id");

		if (preco.isBlank()) {
			request.setAttribute("error", "Selecione uma placa!!!");
			try {
				request.setAttribute("estacionamentos", estacionamentoDAO.getEstaciona(false));
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = request.getRequestDispatcher("pagamento.jsp");
			dispatcher.forward(request, response);
			return;
		}

		Integer pagamento = Integer.parseInt(preco);
		Integer code = Integer.parseInt(id);

		Estaciona estaciona = new Estaciona(code, pagamento);

		try {
			EstacionamentoDAO.updatePrice(estaciona);
			request.setAttribute("success", "Pagamento efetuado com sucesso!");
			request.setAttribute("estacionamentos", estacionamentoDAO.getEstaciona(false));
			RequestDispatcher dispatcher = request.getRequestDispatcher("pagamento.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException | ClassNotFoundException e) {
			request.setAttribute("error", "Erro ao efetuar pagamento!!!\n" + e.toString());
			RequestDispatcher dispatcher = request.getRequestDispatcher("pagamento.jsp");
			dispatcher.forward(request, response);
		}
	}

}
