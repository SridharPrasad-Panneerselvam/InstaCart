package com.instacart.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.instacart.model.Cart;
import com.instacart.model.User;
import com.lowagie.text.Chunk;
import com.lowagie.text.Document;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

public class PdfReport extends AbstractPdfView {

	
	@Override
	protected void buildPdfDocument(Map<String, Object> cart, Document document, PdfWriter pdfwriter,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		document.add(new Paragraph("Order Receipt", FontFactory.getFont(FontFactory.COURIER_BOLD, 25)));
		document.add(Chunk.NEWLINE);
		User user = (User) cart.get("user");
		String userAddress =(String)cart.get("userAddress");
		String userMobile =(String)cart.get("userMobile");
		List<Cart> cartList = (List<Cart>)cart.get("cartList");
		
		
		document.add(new Paragraph("Customer Details", FontFactory.getFont(FontFactory.COURIER_BOLD, 20)));
		document.add(Chunk.NEWLINE);
		document.add(new Paragraph("Customer Name: " + user.getFirstName() + " " + user.getLastName(), FontFactory.getFont(FontFactory.COURIER_BOLD, 18)));
		document.add(new Paragraph("Delivery Address: "+ userAddress, FontFactory.getFont(FontFactory.COURIER_BOLD, 18)));
		
		document.add(new Paragraph("Mobile Number: "+ userMobile, FontFactory.getFont(FontFactory.COURIER_BOLD, 18)));
		document.add(new Paragraph("Email: "+ user.getUserName(), FontFactory.getFont(FontFactory.COURIER_BOLD, 18)));
		
		document.add(Chunk.NEWLINE);
		
		document.add(new Paragraph("Products Ordered", FontFactory.getFont(FontFactory.COURIER_BOLD, 16)));
		
		document.add(Chunk.NEWLINE);
		PdfPTable table = new PdfPTable(4);
		
		table.addCell("Name");
		table.addCell("Quantity");
		table.addCell("Price in Rs.");
		table.addCell("Total in Rs.");
		
		for(Cart c : cartList)
		{
			table.addCell(c.getProduct().getProductName());
			table.addCell(String.valueOf(c.getCartQuantity()));
			table.addCell(String.valueOf(c.getProduct().getProductPrice()));
			table.addCell(String.valueOf(c.getCartQuantity()*c.getProduct().getProductPrice()));
		}
		
		document.add(table);
	}

	
}
