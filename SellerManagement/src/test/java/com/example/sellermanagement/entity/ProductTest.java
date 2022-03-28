package com.example.sellermanagement.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductTest {

	@Test
	void ProductTestCase() {
		Product product= new Product();
		product.setUserName("test123@username");
		product.setId(1);
		product.setQuantity(10);
		product.setCategory("Jeans");
		product.setPrice(10000);
		product.setProductImage("image");
		product.setProductName("levis");
		product.setDescription("world's largest maker of pants");
		
		assertEquals("test123@username", product.getUserName(),"product username testcase");
		assertEquals(1, product.getId(),"product id testcase");
		assertEquals(10,product.getQuantity(),"product quantity testcase" );
		assertEquals("Jeans", product.getCategory(),"product category testcase");
		assertEquals(10000,product.getPrice(),"product price testcase" );
		assertEquals("image", product.getProductImage(),"product image testcase");
		assertEquals("levis",product.getProductName(),"product name testcase");
		assertEquals("world's largest maker of pants", product.getDescription(),"product description testcase");
	
	}

}
