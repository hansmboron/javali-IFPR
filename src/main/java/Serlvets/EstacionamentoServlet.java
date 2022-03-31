package Serlvets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.EstacionamentoDAO;
import Model.Estaciona;

import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class EstacionamentoServlet
 */
@WebServlet("/EstacionamentoServlet")
public class EstacionamentoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private EstacionamentoDAO estacionamentoDAO = new EstacionamentoDAO();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EstacionamentoServlet() {
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

		String param = request.getParameter("pagina");
		String id = request.getParameter("id");

		if (id != null) {
			try {
				Integer code = Integer.parseInt(id);
				Estaciona estaciona = EstacionamentoDAO.getEstacionaById(code);
				request.setAttribute("estaciona", estaciona);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}

		try {
			if (param.equals("list")) {
				request.setAttribute("estacionamentos", estacionamentoDAO.getEstaciona(true));
			} else {
				request.setAttribute("estacionamentos", estacionamentoDAO.getEstaciona(false));
			}

		} catch (ClassNotFoundException | SQLException e) {
			request.setAttribute("error", "Erro Driver | Banco de Dados");
		}

		if (param.equals("list")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("listar.jsp");
			dispatcher.forward(request, response);
		} else if (param.equals("pag")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("pagamento.jsp");
			dispatcher.forward(request, response);
		} else if (param.equals("saida")) {
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

		String placa = request.getParameter("placa");

		if (placa.isBlank() || placa.length() < 8) {
			request.setAttribute("error", "Placa com formato incorreto!!!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
			return;
		}

		Estaciona estaciona = new Estaciona(placa.toUpperCase());

		try {
			EstacionamentoDAO.salvar(estaciona);
			request.setAttribute("success", "Veículo salvo com sucesso!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException | ClassNotFoundException e) {
			request.setAttribute("error", "Erro ao salvar veículo!!!\n" + e.toString());
			RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
			dispatcher.forward(request, response);
		}
	}
}
