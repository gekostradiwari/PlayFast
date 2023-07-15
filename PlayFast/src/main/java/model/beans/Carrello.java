package model.beans;

import java.sql.SQLException;
import java.util.ArrayList;

import model.DAOS.ProductModelDM;

public class Carrello {
	
	private ArrayList<ProductBean> products;
	
	public Carrello() {
		products = new ArrayList<ProductBean>();
	}
	
	public ArrayList<ProductBean> getProducts() {
		return products;
	}

	public synchronized void addProduct(int codice) throws SQLException {
		int i = 0;
		boolean flag = false;
		for(ProductBean product : products) {
			if(codice == product.getId()) {
				flag = true;
				products.set(i, product);
			}
			i++;
		}
		if(!flag) {
			ProductModelDM prodottoDAO = new ProductModelDM();
			ProductBean product = prodottoDAO.doRetrieveByKey(codice);
			products.add(product);
		}
	}
	
	public synchronized void removeProduct(int codice) {
		for(int i = 0; i < products.size(); i++) {
			ProductBean product = products.get(i);
			if(codice == product.getId()) {
					products.remove(i);
			}
		}
	}
	
	
	public synchronized double getTotale() {
		double totale = 0;
		for(ProductBean product : products) {
			totale = totale + (product.getPrezzo());
		}
		return totale;
	}
	
}