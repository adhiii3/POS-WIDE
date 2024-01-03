package com.wide.pos.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wide.pos.web.connection.DataSource;
import com.wide.pos.web.domain.CashPayment;
import com.wide.pos.web.domain.Cashier;
import com.wide.pos.web.domain.Item;
import com.wide.pos.web.domain.QrisPayment;
import com.wide.pos.web.domain.Sale;
import com.wide.pos.web.domain.SaleItem;
import com.wide.pos.web.exception.RepositoryException;
import com.wide.pos.web.exception.UseCaseSaleException;
import com.wide.pos.web.repository.impl.ItemDummyMysqlRepository;
import com.wide.pos.web.service.ItemCartService;
import com.wide.pos.web.service.ItemService;
import com.wide.pos.web.service.SaleService;
import com.wide.pos.web.usecase.ProsesUseCaseSale;

/**
 * Servlet implementation class ServletPOS
 */
@WebServlet("/pos.do")
public class ServletPOS extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private final SaleService saleService = new SaleService();
	private final ItemService itemService = new ItemService();
	private final ItemCartService itemCartService = new ItemCartService();
	private final String basePage = "WEB-INF/jsp/dashboard.jsp";
	Cashier cashier = null;
	ProsesUseCaseSale saleUseCase = null;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletPOS() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			response.setContentType("text/html");
			String action = request.getParameter("action");
			String pageName = null;

			if ("create-order-proses".equals(action)) {
				
				if (saleUseCase == null) {
					cashier = new Cashier("C-123", "Ira");
					saleUseCase = new ProsesUseCaseSale();
					saleUseCase.createNewSale(cashier);
				}
				
				pageName = "list-item";
				request.setAttribute("items", itemService.findAll());
				
			} else if ("pembayaran".equals(action)) {
				pageName = "pembayaran";
			} else if ("selesaiPembayaran".equals(action)) {
				pageName = "selesai-pembayaran";
			} else if ("historyTransaksi".equals(action)) {
				pageName = "history-transaksi";
				request.setAttribute("historyTransaksi", saleService.findAllSales());
			} else if ("detail-history-transaksi".equals(action)) {
				String saleId = request.getParameter("id");
				pageName = "detail-history-transaksi";
				request.setAttribute("sales", saleService.findSaleBySaleNumber(saleId));
			}else if ("items".equals(action)) {
				
				pageName = "item-crud";
				request.setAttribute("items", itemService.findAll());
				
			}else if ("form-new-item".equals(action)) {
				pageName = "form-new-item";
			}else if ("form-update".equals(action)) {
				String id = request.getParameter("id");
				pageName = "form-update";
				request.setAttribute("item", itemService.findItemByCode(id));
			}else if("delete_item".equals(action)) {
				try {
					String itemCode = request.getParameter("id");
					itemService.deleteItem(itemCode);
					System.out.print("Oke");
					response.sendRedirect("http://localhost:8080/WidePos/pos.do?action=items"); 
					return;
				} catch (UseCaseSaleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			else {
				if (saleUseCase == null) {
					pageName = "create-order";
				} else {
					pageName = "list-item";
					request.setAttribute("items", itemService.findAll());
				}
			}
			
			request.setAttribute("page", pageName);
			request.getRequestDispatcher(basePage).forward(request, response);
		} catch (UseCaseSaleException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		if ("addToCart".equals(action)) {
			String id = request.getParameter("id");
			Item item = null;
			try {
				item = itemService.findItemByCode(id);
				itemCartService.addCartItemDetail(item);
				HttpSession session = request.getSession();
				session.setAttribute("itemCartDetail", itemCartService.getItemCartDetail());
			} catch (UseCaseSaleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if ("addSaleItem".equals(action)) {
			Item item = null;
			try {
				String itemCode = request.getParameter("itemCode");
				String quantity = request.getParameter("quantity");
				saleUseCase.addSaleItem(itemCode, Integer.parseInt(quantity));
				itemCartService.removeItemDetailById(itemCode);

				HttpSession session = request.getSession();
				session.setAttribute("sales", saleUseCase.getSale());

			} catch (UseCaseSaleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if ("deleteCartPerItem".equals(action)) {
			String id = request.getParameter("id");
			itemCartService.removeItemById(id);
		} else if ("deleteItemCart".equals(action)) {
			String itemCode = request.getParameter("id");
			saleUseCase.deleteSaleItem(itemCode);
		} else if ("prosesPembayaranItems".equals(action)) {
			String jenisPembayaran = request.getParameter("jenis_pembayaran");
			String uangPembayaran = request.getParameter("input-cash");
			String pageName = null;

			try {
				if ("cash".equalsIgnoreCase(jenisPembayaran)) {
					Integer uangBayar = 0;
					if (uangPembayaran == null || uangPembayaran.isEmpty() || uangPembayaran.isBlank()) {
						pageName = "pembayaran";
						request.setAttribute("page", pageName);
						request.getRequestDispatcher(basePage).forward(request, response);
						return;
					}

					uangBayar = Integer.parseInt(uangPembayaran);

					if (!(uangBayar >= saleUseCase.getSale().getTotalPricePlusTotalTax())) {
						pageName = "pembayaran";
						request.setAttribute("page", pageName);
						request.getRequestDispatcher(basePage).forward(request, response);
						return;
					}

					CashPayment cashPayment = new CashPayment(uangBayar,
							saleUseCase.getSale().getTotalPricePlusTotalTax());

					saleUseCase.makePayment(cashPayment);

				} else {
					QrisPayment qrisPayment = new QrisPayment(saleUseCase.getSale().getTotalPricePlusTotalTax());
					saleUseCase.makePayment(qrisPayment);
				}

				String saleNumber = saleUseCase.getSale().getSalesNumber();
				saleUseCase.finishSale();
				saleUseCase = null;
				removeSessionSale(request);

				response.sendRedirect("http://localhost:8080/WidePos/pos.do?action=detail-history-transaksi&id="+saleNumber); 
			} catch (UseCaseSaleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}else if("save_item".equals(action)) {
			String pageName = "item-crud";
			try {
				String itemCode = request.getParameter("itemCode");
				Integer price = Integer.parseInt(request.getParameter("price"));
				String description = request.getParameter("description");
				String type = request.getParameter("type");
				
				boolean isTax = Boolean.parseBoolean(request.getParameter("isTax"));
				Item item = new Item(itemCode, price, description, type, isTax);
				itemService.saveItem(item);
				
				response.sendRedirect("http://localhost:8080/WidePos/pos.do?action=items"); 
			} catch (UseCaseSaleException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else if("update_item".equals(action)) {
				try {
					String itemCode = request.getParameter("itemCode");
					Integer price = Integer.parseInt(request.getParameter("price"));
					String description = request.getParameter("description");
					String type = request.getParameter("type");
					
					boolean isTax = Boolean.parseBoolean(request.getParameter("isTax"));
					Item item = new Item(itemCode, price, description, type, isTax);
					itemService.updateItem(itemCode, item);
				} catch (UseCaseSaleException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}

	private void removeSessionSale(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("sales");
		session.removeAttribute("itemCartDetail");
		session.removeAttribute("itemCart");
	}
}
