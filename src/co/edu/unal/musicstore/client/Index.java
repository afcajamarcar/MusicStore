package co.edu.unal.musicstore.client;

import java.util.ArrayList;
import java.util.HashMap;

import co.edu.unal.musicstore.many2many.ofy.Product;
import co.edu.unal.musicstore.many2many.ofy.ShoppingCart;
import co.edu.unal.musicstore.shared.LoginInfo;

import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.view.client.DefaultSelectionEventManager;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import com.sun.corba.se.pept.transport.ContactInfo;


public class Index implements EntryPoint {
	
	// TODO #05: add constants for OAuth2 (don't forget to update GOOGLE_CLIENT_ID)
	private static final Auth AUTH = Auth.get();
	private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
	private static final String GOOGLE_CLIENT_ID = "650108443489-qml5g9kgkck1fpkr4ugbo1jbc3cgcj0d.apps.googleusercontent.com";
	private static final String PLUS_ME_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";
		// TODO #05:> end

		// TODO #06: define controls for login
	private final HorizontalPanel loginPanel = new HorizontalPanel();
	private final Anchor signInLink = new Anchor("");
		// TODO #06:> end

	private final ProductServiceAsync productService = GWT
			.create(ProductService.class);
	private CellTable<Product> cellTable;
	private AbsolutePanel absolutePanel;
	private ScrollPanel scrollPanel;
	private TextColumn<Product> textColumn_1;
	private TextColumn<Product> textColumn_2;
	private TextColumn<Product> textColumn_3;
	private Column<Product,Number> textColumn_4;
	private HashMap<String,String> urlsBajo;
	private HashMap<String,String> urlsGuitarras;
	private HashMap<String,String> urlsAmps;
	private HashMap<String,String> urlsMicro;
	private Image image;
	private VerticalPanel imagePanel;
	private Button show;
	private Button actualizar;
	private Button comprar;
	private HTML details;
	private ShoppingCart sp;
	private TextArea g;
	private VerticalPanel holdg;
	private Product selected;
	private Button verCarrito;
	@Override
	public void onModuleLoad() {
		productService.registerProducts(
				new AsyncCallback<Void>() {
					public void onFailure(Throwable caught) {
						// Show the RPC error message to the user
					}
					@Override
					public void onSuccess(Void result) {
						// TODO Auto-generated method stub
						
					}
				});
		//Imagenes bajos
		urlsBajo = new HashMap<>();
		urlsBajo.put("Fender Jaguar Bass","https://ugc.kn3.net/i/origin/http://4.bp.blogspot.com/_DQcFxEKQutw/S8Mvp8Z39vI/AAAAAAAAAM8/eO3qiU_rSWg/s1600/fender-jaguar-electric-bass.jpg");
		urlsBajo.put("Gibson Thunderbird","https://ugc.kn3.net/i/origin/http://www.long-mcquade.com/files/2036/lg_32601tbird4.jpg");
		urlsBajo.put("Dean Rhapsody Bass","https://ugc.kn3.net/i/origin/http://www.deanguitars.com/rhapsody/rhhbfltab-1600.jpg");
		urlsBajo.put("BC-Rich Warbeast Bass","https://ugc.kn3.net/i/origin/http://www.edroman.com/guitars/bcrich/images/bass_bst_nt4.jpg");
		urlsBajo.put("Gibson Flying v ","https://ugc.kn3.net/i/origin/http://farm3.static.flickr.com/2692/4352836495_2ca1103e70_z.jpg");
		
		//Imagenes Amps
		urlsAmps= new HashMap<>();
		urlsAmps.put("Acoustic A1000 2x50W Stereo Acoustic Guitar Combo Amp", "http://media.musiciansfriend.com/is/image/MMGS7/A1000-2x50W-Stereo-Acoustic-Guitar-Combo-Amp/J10059000000000-00-500x500.jpg");
		urlsAmps.put("Hughes & Kettner TubeMeister TM18H 18W Tube Guitar Amp Head", "http://media.musiciansfriend.com/is/image/MMGS7/TubeMeister-TM18H-18W-Tube-Guitar-Amp-Head/H79232000000000-00-500x500.jpg");
		urlsAmps.put("Laney Lionheart 5w Tube Guitar Combo Amp", "http://media.musiciansfriend.com/is/image/MMGS7/Lionheart-5w-Tube-Guitar-Combo-Amp-Blue-Tolex/481316000004000-00-500x500.jpg");
		urlsAmps.put("Jetcity amp", "http://www.premierguitar.com/ext/resources/archives/1de84ac1-9be4-4e96-ad88-4c7a4063418d.JPG?1371672915");
		urlsAmps.put("Peavey rage 158", "http://ecx.images-amazon.com/images/I/51I1kMRjRdL._SY355_.jpg");
		urlsAmps.put("Marshall classic", "http://www.prettygreen.com/media/images/productimage-picture-marshall-ms-2-microamp-classic-11609.jpg");
		urlsAmps.put("Sunn", "http://www.sunnamps.com/images/sunn_amp.jpg");
		urlsAmps.put("Laney LA20C 20W", "http://cdn.shopify.com/s/files/1/0657/6821/products/la20c_4_702df593-8ec9-432d-97e0-9eaa59dd913f.jpeg?v=1427979873");
		urlsAmps.put("Egnater Renegade 65W", "http://media.guitarcenter.com/is/image/MMGS7/Renegade-65W-Tube-Guitar-Amp-Head-Black-Beige/620076000283000-00-500x500.jpg");
		urlsAmps.put("Friedman Vintage", "http://mla-s2-p.mlstatic.com/friedman-white-tolex-vintage-4x12-guitar-speaker-cab-whitem-949011-MLA20450132246_102015-O.jpg");
		
		//Imagenes Micros
		urlsMicro = new HashMap<>();
		urlsMicro.put("PGA57", "http://cdn.shure.com/product/main_image/8905/pga81_main_1.jpg");
		urlsMicro.put("PGA46", "http://cdn.shure.com/product/main_image/9088/pga48_main_1.jpg");
		urlsMicro.put("PGA31", "http://cdn.shure.com/product/main_image/9091/pga27_main_1.jpg");
		urlsMicro.put("PGA90", "http://mla-s2-p.mlstatic.com/microfono-para-instrumentos-shure-pga-57lc-990011-MLA20461691949_102015-O.jpg");
		urlsMicro.put("PGA87", "http://www.shure.es/dms/shure/products/microphones/images/pga/pga48/pga48_8_cols/pga48_8_cols.jpg?1423820420");
		
		//Imagenes guitarras
		urlsGuitarras = new HashMap<>();
		urlsGuitarras.put("Flying V", "http://images.gibson.com/Files/46cd6b26-91a0-44aa-937b-994df482e8cb.jpg");
		urlsGuitarras.put("BC-Rich 676", "https://bkam-271900.c.cdn77.org/egypt/full/89449b4910160dfa676e7b3c01e64e3e2d73ca43.jpg");
		urlsGuitarras.put("Dean epic custom", "https://s-media-cache-ak0.pinimg.com/736x/37/61/32/376132e59a8361c63e5760dbc0d5e3e3.jpg");
		urlsGuitarras.put("Fender Stratocaster", "http://www.fmicassets.com/demandware/assets/electric-guitars/stratocaster/overview/0113000705_m1240_0000.jpg");
		urlsGuitarras.put("Ibanez 789", "http://www.edroman.com/detail_sheets/images/ibanez_789tpcu.jpg");
		urlsGuitarras.put("Jhon Petrucci's custom signature", "http://www.musicalisme.com/image/cache/data/mg_john-petrucci_ibanez-jpm100-p1_01-500x500.jpg");
		urlsGuitarras.put("Kramer 666", "http://www.meanstreetguitars.com/Mean_Street_HoloCrackle_1984_Kramer.jpg");
		urlsGuitarras.put("Yamaha 15", "http://guitar-auctions.co.uk/wp-content/uploads/2012/03/lot0186.jpg");
		urlsGuitarras.put("Washburn 156", "http://www.washburn.com/products/electric/images/PXS10FRDLXWB.jpg");
		urlsGuitarras.put("Epiphone 799", "http://musicrising.org/wp-content/uploads/2011/07/guitar_full.jpg");
		urlsGuitarras.put("Fender Squier", "http://assets.fender.com/frl/e0b0d53cc09a6cfde239257b3b11bb69/generated/90bce2e325d22193c22cc905bedef890.png");
		
		absolutePanel = new AbsolutePanel();
		absolutePanel.setSize("918px", "553px");
		
		scrollPanel = new ScrollPanel();
		absolutePanel.add(scrollPanel, 181, 90);
		scrollPanel.setSize("444px", "281px");
		
		cellTable = new CellTable<Product>();
		scrollPanel.setWidget(cellTable);
		scrollPanel.setStyleName("style");
		cellTable.setSize("100%", "100%");
		VerticalPanel verticalPanel = new VerticalPanel();
		verticalPanel.setSize("150px", "281px");
		
		
		Label lblNewLabel = new Label("Bienvenido a la aplicacion de la tienda de musica, nuevos productos y funcionalidades proximamente!");
		verticalPanel.add(lblNewLabel);
		absolutePanel.add(verticalPanel, 10, 90);
		
		//Carga los objetos del datastore
		textColumn_3 = new TextColumn<Product>() {
			@Override
			public String getValue(Product object) {
				return object.getName();
			}
		};
		cellTable.addColumn(textColumn_3, "Nombre");
		
		textColumn_2 = new TextColumn<Product>() {
			@Override
			public String getValue(Product object) {
				return object.getTipo();
			}
		};
		cellTable.addColumn(textColumn_2, "Tipo");
	
		
		textColumn_1 = new TextColumn<Product>() {
			@Override
			public String getValue(Product object) {
				return object.getStatus();
			}
		};
		cellTable.addColumn(textColumn_1, "Estado");
		
		textColumn_4 = new Column<Product,Number>(new NumberCell()){

			@Override
			public Number getValue(Product object) {
				return object.getAmount();
			}
			
		};
		cellTable.addColumn(textColumn_4, "Cantidad");
		
		updateTable();
		
		verCarrito = new Button("New button");
		verCarrito.setText("Ver carrito");
		verCarrito.setEnabled(false);
		absolutePanel.add(verCarrito, 760, 89);
		verCarrito.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
			  for(Product item : sp.getProducts()){
				  g.setText(item.getName());
			  }
			}
			
		});
		
		g = new TextArea();
		g.setReadOnly(true);
		holdg = new VerticalPanel();
		holdg.add(g);
		g.setHeight("144px");
		absolutePanel.add(holdg, 723, 142);
		holdg.setSize("151px", "151px");
		
		
		comprar = new Button();
		comprar.setText("Agregar al carro de compras");
		comprar.setEnabled(false);
		absolutePanel.add(comprar, 429, 391);
		comprar.setSize("193px", "30px");
        //Agregar al carrito
		comprar.addClickHandler(new ClickHandler(){

	  		@Override
	  		public void onClick(ClickEvent event) {
	  			verCarrito.setEnabled(true);
	  			sp.addProducts(selected);
	  			
	  		}
    	  
	  	});

		
		
		
		
        
		  	
		show = new Button();
		show.setText("Informacion");
		show.setEnabled(false);
		absolutePanel.add(show, 181, 391);
		show.setSize("106px", "30px");
		final SingleSelectionModel<Product> selectionModel 
	      = new SingleSelectionModel<Product>();
	      cellTable.setSelectionModel(selectionModel);
	      selectionModel.addSelectionChangeHandler(
	      new SelectionChangeEvent.Handler() {
	         public void onSelectionChange(SelectionChangeEvent event) {
	            selected = selectionModel.getSelectedObject();
	            image = new Image();
	            imagePanel = new VerticalPanel();
	            imagePanel.setSize("150px", "281px");
	            details = new HTML();
	            if (selected != null) {
	            	details.setText("Precio: "+String.valueOf(selected.getPrice()));
	            	show.setEnabled(true);
	            	comprar.setEnabled(true);
	            	
	               if(selected.getTipo().equalsIgnoreCase("bajo")){
	            	   image.setUrl(urlsBajo.get(selected.getName()));
	            	   
	               }if(selected.getTipo().equalsIgnoreCase("amplificador")){
	            	   
	            	   image.setUrl(urlsAmps.get(selected.getName()));
	            	   
	               }if(selected.getTipo().equalsIgnoreCase("guitarra")){
	            	   
	            	   image.setUrl(urlsGuitarras.get(selected.getName()));	            	  
	               
	               }if(selected.getTipo().equalsIgnoreCase("microfono")){
	            	   image.setUrl(urlsMicro.get(selected.getName()));	            	   	            	   	            	   
	               }
	               
	            }

	         }
	      });
	      show.addClickHandler(new ClickHandler() {
	          @Override
	          public void onClick(ClickEvent event) {
	             DialogBox temp = createDialogBox(image,details);
	             image.setSize("300px", "281px");
	             temp.setGlassEnabled(true);
	             temp.setAnimationEnabled(true);
	             temp.center();
	             temp.show();
	          }
	       });
	      actualizar = new Button();
		  actualizar.setText("Actualizar");
	      actualizar.setEnabled(true);
		  absolutePanel.add(actualizar, 306, 391);
		  actualizar.setSize("106px", "30px");
	      actualizar.addClickHandler(new ClickHandler(){

			@Override
			public void onClick(ClickEvent event) {
				updateTable();
			}
	    	  
	      });
	      
		
		//login 
		signInLink.getElement().setClassName("login-area");
		signInLink.setTitle("sign out");
		loginPanel.add(signInLink);
		RootPanel.get("loginPanelContainer").add(loginPanel);
		final StringBuilder userEmail = new StringBuilder();
		productService.login(GWT.getHostPageBaseURL(), new AsyncCallback<LoginInfo>() {
			@Override
			public void onFailure(final Throwable caught) {
				GWT.log("login -> onFailure");
			}

			@Override
			public void onSuccess(final LoginInfo result) {
				if (result.getName() != null && !result.getName().isEmpty()) {
					addGoogleAuthHelper();
					loadLogout(result);
				} else {
					loadLogin(result);
				}
				userEmail.append(result.getEmailAddress());
			}
		});
		// TODO #08:> end
		
		
		RootPanel.get("loginPanelContainer").add(loginPanel);
		RootPanel.get().add(absolutePanel, 10, 10);
		
		
	}
	  private DialogBox createDialogBox(Image image, HTML precio) {
		    // Create a dialog box and set the caption text
		  	
		    final DialogBox dialogBox = new DialogBox();
		    // Create a table to layout the content
		    VerticalPanel dialogContents = new VerticalPanel();
		    dialogContents.setSpacing(4);
		    dialogBox.setWidget(dialogContents);

		    // Add an image to the dialog
		    dialogContents.add(image);
		    dialogContents.setCellHorizontalAlignment(
		        image, HasHorizontalAlignment.ALIGN_CENTER);
		    dialogContents.add(precio);
		    dialogContents.setCellHorizontalAlignment(
			        precio, HasHorizontalAlignment.ALIGN_CENTER);

		    // Add a close button at the bottom of the dialog
		    Button closeButton = new Button("Close", new ClickHandler() {
		          public void onClick(ClickEvent event) {
		            dialogBox.hide();
		          }
		        });
		    dialogContents.add(closeButton);
		      dialogContents.setCellHorizontalAlignment(
		          closeButton, HasHorizontalAlignment.ALIGN_LEFT);

		    // Return the dialog box
		    return dialogBox;
		  }
	private void loadLogin(final LoginInfo loginInfo) {
		signInLink.setHref(loginInfo.getLoginUrl());
		signInLink.setText("Ingresar con su cuenta de Google");
		signInLink.setTitle("Sign in");
	}

	private void loadLogout(final LoginInfo loginInfo) {
		signInLink.setHref(loginInfo.getLogoutUrl());
		signInLink.setText(loginInfo.getName());
		signInLink.setTitle("Sign out");
	}

	private void addGoogleAuthHelper() {
		final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, GOOGLE_CLIENT_ID)
		.withScopes(PLUS_ME_SCOPE);
		AUTH.login(req, new Callback<String, Throwable>() {
			@Override
			public void onSuccess(final String token) {

				if (!token.isEmpty()) {
					productService.loginDetails(token, new AsyncCallback<Void>() {
						@Override
						public void onFailure(final Throwable caught) {
							GWT.log("loginDetails -> onFailure");
						}

						@Override
						public void onSuccess(Void result) 
						{
							signInLink.setText("Login correcto - Bienvenido");
							//nameField.setText(loginInfo.getName());
							signInLink.setStyleName("login-area");
						}
					});
				}
			}

			@Override
			public void onFailure(final Throwable caught) {
				GWT.log("Error -> loginDetails\n" + caught.getMessage());
			}
		});
	}
	public void updateTable(){
		productService.loadEverything(new AsyncCallback<ArrayList<Product>>(){
			
			@Override
			public void onFailure(Throwable caught) {
				final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
				simplePopup.add(new HTML("<b>Couldn't load any product</b>"));
			}

			@Override
			public void onSuccess(ArrayList<Product> result) {
				cellTable.setRowData(result);
			}
			
		});

	}
}
