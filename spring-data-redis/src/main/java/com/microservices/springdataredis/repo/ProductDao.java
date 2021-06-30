package com.microservices.springdataredis.repo;

import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.microservices.springdataredis.entity.Product;

@Repository
public class ProductDao {
	
	public static final String HASH_KEY = "Product";
	
	@Autowired
	private RedisTemplate template;
	
	public Product save(Product product) {
		template.opsForHash().put(HASH_KEY, product.getId(), product);;
		return product;
	}
	
	public List<Product> findAll() {
		List<Product> productList = template.opsForHash().values(HASH_KEY);
		productList.sort(Comparator.comparing(Product::getId));
		return productList;
	}
	
	public Product findProductById(int id) {
		Product product = (Product) template.opsForHash().get(HASH_KEY, id);
		return product;
	}
	
	public String deleteProductById(int id) {
		template.opsForHash().delete(HASH_KEY, id);
		return "Product id "+id+" deleted.";
	}

}
