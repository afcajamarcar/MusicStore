package co.edu.unal.musicstore.client;

import java.util.ArrayList;

import co.edu.unal.musicstore.many2many.ofy.Product;
import co.edu.unal.musicstore.shared.LoginInfo;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("productservice")
public interface ProductService extends RemoteService {
	
	ArrayList<Product> loadEverything();
	void changeState(Product anyProduct);
	void registerProducts( );
	
	//Login methods
	String getUserEmail(String token);	

	LoginInfo login(String requestUri);	

	void loginDetails(String token);

}
