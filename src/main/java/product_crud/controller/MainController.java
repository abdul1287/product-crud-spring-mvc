package product_crud.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.view.RedirectView;

import product_crud.dao.ProductDao;
import product_crud.model.Product;

@Controller
public class MainController {

	@Autowired
	private ProductDao productDao;

	@RequestMapping("/")
	public String home(Model m) {

		List<Product> products = productDao.getAll();
		m.addAttribute("product", products);

		return "index";
	}

	// Show add-product form
	@RequestMapping("/add-product")
	public String addProduct(Model m) {
		m.addAttribute("title", "Add Product");
		return "add_product_form";
	}

	// Handle product form
	@RequestMapping(value = "/handle-product", method = RequestMethod.POST)
	public RedirectView handleProduct(@ModelAttribute Product prod, HttpServletRequest request) {
		productDao.save(prod);
		RedirectView rv = new RedirectView();
		rv.setUrl(request.getContextPath() + "/");
		return rv;
	}

	// delete product handling
	@RequestMapping("/delete/{productId}")
	public RedirectView deleteProduct(@PathVariable("productId") int productId, HttpServletRequest request) {
		this.productDao.delete(productId);
		RedirectView rv = new RedirectView();
		rv.setUrl(request.getContextPath() + "/");
		return rv;
	}
	
	@RequestMapping("/update/{productId}")
	public String updateForm(@PathVariable("productId") int pid, Model m) {
		Product prod = this.productDao.getById(pid);
		m.addAttribute("product", prod);
		return "update_form";
	}
}