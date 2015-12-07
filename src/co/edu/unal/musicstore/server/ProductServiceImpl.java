package co.edu.unal.musicstore.server;


import static com.googlecode.objectify.ObjectifyService.ofy;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import co.edu.unal.musicstore.client.ProductService;
import co.edu.unal.musicstore.many2many.ofy.Product;






import co.edu.unal.musicstore.shared.FieldVerifier;
import co.edu.unal.musicstore.shared.LoginInfo;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadResult;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

public class ProductServiceImpl extends RemoteServiceServlet implements
ProductService, Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = 7997681442163323238L;
	

	protected Product loadProduct( String id )
	{
		LoadResult<Product> r = ofy.load().type( Product.class ).id( id );
		
		return r.now();
	}
	
		
	@Override
	public void init( ServletConfig sc )
	{
		try
		{
			super.init( sc );
			ObjectifyService.register( Product.class );
			
		}
		catch ( ServletException e )
		{
			e.printStackTrace();
		}
	
	}
	public void registerProducts() {
		//inventario
		storeProduct("920","Flying V","Guitarra","Disponible",5,2000000);
		storeProduct("921","BC-Rich 676", "Guitarra","Disponible",10,1600000);
		storeProduct("922","Dean epic custom", "Guitarra","Disponible",9,1500000);
		storeProduct("923","Fender Stratocaster", "Guitarra","Disponible",3,700000);
		storeProduct("924","Ibanez 789", "Guitarra","Disponible",65,900000);
		storeProduct("925","Jhon Petrucci's custom signature", "Guitarra","Disponible",25,7000000);
		storeProduct("926","Kramer 666","Guitarra","Disponible",40,1200000);
		storeProduct("927","Yamaha 15", "Guitarra","Disponible",79,500000);
		storeProduct("928","Washburn 156", "Guitarra","Disponible",33,1000000);
		storeProduct("929","Epiphone 799", "Guitarra","Disponible",43,2300000);
		storeProduct("930","Fender Squier", "Guitarra","Disponible",56,300000);
		storeProduct("931","Acoustic A1000 2x50W Stereo Acoustic Guitar Combo Amp", "Amplificador","Disponible",76,300000);
		storeProduct("932","Hughes & Kettner TubeMeister TM18H 18W Tube Guitar Amp Head", "Amplificador","Disponible",28,500000);
		storeProduct("933","Laney Lionheart 5w Tube Guitar Combo Amp", "Amplificador","Disponible",34,297800);
		storeProduct("934","Jetcity amp", "Amplificador","Disponible",21,150000);
		storeProduct("935","Peavey rage 158", "Amplificador","Disponible",34,200000);
		storeProduct("936","Marshall classic", "Amplificador","Disponible",99,190000);
		storeProduct("937","Sunn", "Amplificador","Disponible",56,4000000);
		storeProduct("938","Laney LA20C 20W", "Amplificador","Disponible",34,140000);
		storeProduct("939","Egnater Renegade 65W", "Amplificador","Disponible",56,290000);
		storeProduct("940","Friedman Vintage", "Amplificador","Disponible",78,450000);
		storeProduct("941","PGA57", "Microfono","Disponible",43,90000);
		storeProduct("942","PGA46", "Microfono","Disponible",22,90000);
		storeProduct("943","PGA31", "Microfono","Disponible",66,90000);
		storeProduct("944","PGA90", "Microfono","Disponible",22,90000);
		storeProduct("945","PGA87", "Microfono","Disponible",56,90000);
		storeProduct("946","Fender Jaguar Bass", "Bajo","Disponible",78,1400000);
		storeProduct("947","Gibson Thunderbird ", "Bajo","Disponible",45,2450000);
		storeProduct("946","Dean Rhapsody Bass", "Bajo","Disponible",32,200000);
		storeProduct("946","BC-Rich Warbeast Bass", "Bajo","Disponible",23,2500000);
		storeProduct("946","Gibson Flying v ", "Bajo","Disponible",42,3000000);
				
	}
	protected Key<Product> storeProduct(String id,String name,String type,String status, int amount, int price )
	{
		final Product m = createProduct( id, name, type, status, amount, price );
		ObjectifyService.run(new VoidWork() {
			public void vrun() {
				f = ofy().save().entity( m ).now();
			}
		});
		return f;
	}
	protected static Product createProduct( String id, String name, String tipo, String status, int amount, int price )
	{
		Product m = new Product(id, name, tipo, status, amount, price );
		return m;
	}

	@Override
	public ArrayList<Product> loadEverything( )
	{
		
		ObjectifyService.run(new VoidWork() {
			public void vrun() {
				q = ofy().load().type(Product.class).list();
		    }
		});
		ArrayList<Product> toReturn = new ArrayList<Product>();
		for(Product item : q){
			toReturn.add(item);
		}
		return toReturn;
	}
	@Override
	public void changeState(Product anyProduct) {
		
		if (anyProduct.getStatus().toLowerCase().equals( "disponible")){
			anyProduct.setStatus("Agotado");
		}else{
			anyProduct.setStatus("Disponible");
		}
	}

	@Override
	public String getUserEmail(final String token) {
		final UserService userService = UserServiceFactory.getUserService();
		final User user = userService.getCurrentUser();
		if (null != user) {
			return user.getEmail();	
		} else {
			return "noreply@sample.com";
		}
	}

	@Override
	public LoginInfo login(final String requestUri) {
		final UserService userService = UserServiceFactory.getUserService();
		final User user = userService.getCurrentUser();
		final LoginInfo loginInfo = new LoginInfo();
		if (user != null) {
			loginInfo.setLoggedIn(true);
			loginInfo.setName(user.getEmail());
			loginInfo.setLogoutUrl(userService.createLogoutURL(requestUri));
		} else {
			loginInfo.setLoggedIn(false);
			loginInfo.setLoginUrl(userService.createLoginURL(requestUri));
		}
		return loginInfo;
	}

	@Override
	public void loginDetails(final String token) {
		String url = "https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=" + token;

		final StringBuffer r = new StringBuffer();
		try {
			final URL u = new URL(url);
			final URLConnection uc = u.openConnection();
			final int end = 1000;
			InputStreamReader isr = null;
			BufferedReader br = null;
			try {
				isr = new InputStreamReader(uc.getInputStream());
				br = new BufferedReader(isr);
				final int chk = 0;
				while ((url = br.readLine()) != null) {
					if ((chk >= 0) && ((chk < end))) {
						r.append(url).append('\n');
					}
				}
			} catch (final java.net.ConnectException cex) {
				r.append(cex.getMessage());
			} catch (final Exception ex) {
				log.log(Level.SEVERE, ex.getMessage());
			} finally {
				try {
					br.close();
				} catch (final Exception ex) {
					log.log(Level.SEVERE, ex.getMessage());
				}
			}
		} catch (final Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
	private List<Product> q;
	private static Logger log = Logger.getLogger(ProductServiceImpl.class.getCanonicalName());
	private Objectify ofy;
	public Key<Product> f;
}
