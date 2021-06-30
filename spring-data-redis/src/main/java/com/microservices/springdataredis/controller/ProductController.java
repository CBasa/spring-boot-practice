package com.microservices.springdataredis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.springdataredis.entity.Product;
import com.microservices.springdataredis.repo.ProductDao;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductDao dao;
	
	@PostMapping("/save")
	public Product save(@RequestBody Product product) {
		return dao.save(product);
	}
	
	@GetMapping("/getAll")
	public List<Product> getAll() {
		return dao.findAll();
	}
	
	@GetMapping("/getOne/{id}")
	public Product getOne(@PathVariable int id) {
		return dao.findProductById(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public String remove(@PathVariable int id) {
		return dao.deleteProductById(id);
	}

}
