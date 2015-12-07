package co.edu.unal.musicstore.server;

import static com.googlecode.objectify.ObjectifyService.ofy;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;

import co.edu.unal.musicstore.client.GreetingService;
import co.edu.unal.musicstore.many2many.ofy.Product;
import co.edu.unal.musicstore.shared.FieldVerifier;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.LoadResult;
import com.googlecode.objectify.Objectify;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.VoidWork;

/**
 * The server-side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements
		GreetingService {
	@Override
	public void init(ServletConfig sc){
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

	public Key<Product> f;
}
