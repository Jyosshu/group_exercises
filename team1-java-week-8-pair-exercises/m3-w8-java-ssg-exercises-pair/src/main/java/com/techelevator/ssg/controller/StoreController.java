package com.techelevator.ssg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.techelevator.ssg.model.store.Product;
import com.techelevator.ssg.model.store.ProductDao;
import com.techelevator.ssg.model.store.ShoppingCart;

@Controller
@RequestMapping("/shoppingCart")
public class StoreController {

	@Autowired
	ProductDao productDao;
	
	@RequestMapping(path="/index", method=RequestMethod.GET)
		public String displayStore(HttpSession session) {
			List<Product> allProducts = productDao.getAllProducts();
			session.setAttribute("products", allProducts);
			
		return "storeIndex";
	}
	
	@RequestMapping(path="/productDetail/{id}", method=RequestMethod.GET)
		public String displayDetailProduct(@PathVariable Long id, ModelMap mapHolder) {
			Product newProduct = productDao.getProductById(id);
			mapHolder.put("product", newProduct);
		return "productDetail";
	}
	
	@RequestMapping(path="/addToCart", method=RequestMethod.POST)	
		public String addProductToCart(@RequestParam Long productId, @RequestParam Integer quantity, HttpSession session) {
			if(session.getAttribute("ShoppingCart") == null){
				session.setAttribute("ShoppingCart", new ShoppingCart());
			}
			
			ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
			sc.addProduct(productId, quantity);
			
		return "redirect:/shoppingCart/view";
	}
	
	@RequestMapping(path="/view", method=RequestMethod.GET)
		public String showShoppingCart(HttpSession session, ModelMap modelHolder){
			Map<Product, Integer> productList = new HashMap<>();
			ShoppingCart sc = (ShoppingCart) session.getAttribute("ShoppingCart");
			if(sc != null){
				Map<Long, Integer> shoppingCartProducts = sc.getAllProducts();
				for(Long productId : shoppingCartProducts.keySet()){
					Product currentProduct = productDao.getProductById(productId);
					productList.put(currentProduct, shoppingCartProducts.get(productId));
				}
			}
			modelHolder.put("productList", productList);
			return "cartView";
	}
	
}
