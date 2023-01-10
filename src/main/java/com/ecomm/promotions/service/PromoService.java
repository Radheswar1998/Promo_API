package com.ecomm.promotions.service;



import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecomm.promotions.model.Product;

@Service
public class PromoService {
	@Value("${application.getproducts.url}")
    private String url;
	
	public Product[] getAllProducts() {
		
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<Product[]> responseEntity = restTemplate.getForEntity(url, Product[].class); 
		System.err.println(responseEntity.getBody().length);
		return responseEntity.getBody();
	}
	
	public String createProductFile(Product[] productArray) {
		String fileName ="/Users/DURGA/Desktop/products/product.txt";
		 try {
		      FileWriter myWriter = new FileWriter(fileName);
		      for(Product product:productArray) {
		    	  myWriter.write(product.getProductName()+"\t"+product.getPrice()+"\n");
		      }
		      
		      myWriter.close();
		      System.out.println("Successfully wrote to the file.");
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }
		
		return fileName;
	}

}
