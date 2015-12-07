package co.edu.unal.musicstore.client;

import java.util.ArrayList;

import co.edu.unal.musicstore.many2many.ofy.Product;
import co.edu.unal.musicstore.shared.LoginInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.googlecode.objectify.Key;

public interface ProductServiceAsync {

	void loadEverything(AsyncCallback<ArrayList<Product>> callback);
	
	void changeState(Product anyProduct, AsyncCallback<Void> callback);
	
	void registerProducts(AsyncCallback<Void> callback);
	//loggin async methods

	void getUserEmail(String token, AsyncCallback<String> callback);

	void login(String requestUri, AsyncCallback<LoginInfo> callback);

	void loginDetails(String token, AsyncCallback<Void> callback);
	
}
