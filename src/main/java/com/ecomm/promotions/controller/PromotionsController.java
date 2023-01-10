package com.ecomm.promotions.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecomm.promotions.model.Product;
import com.ecomm.promotions.model.PromoResponse;
import com.ecomm.promotions.service.PromoService;

@RestController
@RequestMapping("/promo")
public class PromotionsController {
	
	@Autowired PromoService promoService;
	
	@GetMapping("/createProductsFile")
	public PromoResponse createProductsFile() {
		Product[] productArray = promoService.getAllProducts();
		PromoResponse promoResponse = new PromoResponse();
		try{
			String fileName = promoService.createProductFile(productArray);
			promoResponse.setFileName(fileName);
			promoResponse.setStatusId(200);
			promoResponse.setStatusMessage("Successful");
			
		}
		catch(Exception ex) {
			promoResponse.setStatusId(500);
			promoResponse.setStatusMessage("Failed");
		}
		
		return promoResponse;
	}

}
