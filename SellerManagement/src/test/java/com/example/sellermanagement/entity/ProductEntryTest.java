package com.example.sellermanagement.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ProductEntryTest {

	@Test
	void ProductEntryTestCase() {
		ProductEntry productEntry = new ProductEntry();
		productEntry.setQuantity(10);
		productEntry.setCategory("Jeans");
		productEntry.setPrice(10000);
		productEntry.setProductImage("image");
		productEntry.setProductName("levis");
		productEntry.setDescription("world's largest maker of pants");
		
		assertEquals(10,productEntry.getQuantity(),"product quantity testcase" );
		assertEquals("Jeans", productEntry.getCategory(),"product category testcase");
		assertEquals(10000,productEntry.getPrice(),"product price testcase" );
		assertEquals("image", productEntry.getProductImage(),"product image testcase");
		assertEquals("levis",productEntry.getProductName(),"product name testcase");
		assertEquals("world's largest maker of pants", productEntry.getDescription(),"product description testcase");
	}

}
