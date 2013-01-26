package locusta.project.item;

import java.util.HashMap;
import java.util.Map;

import locusta.project.mapacti.LocustaMapActivityMain;
import locusta.project.mapacti.R;
import android.graphics.drawable.Drawable;

/*
 * This class create all the marker item
 */
public class ItemizedOverlaysInitialization {


	private Map<Integer, MapItemizedOverlay> itemzedOverlays = new HashMap<Integer, MapItemizedOverlay>();
	
	/**
	 * Set the icons map
	 * @param locustaMapActivityMain
	 * @return
	 */
	public Map<Integer, MapItemizedOverlay> init(LocustaMapActivityMain locustaMapActivityMain) {

		// TODO faire une boucle for pour ajouter toutes les icones
		
		
		 // Icone User
 		Drawable drawable88 = locustaMapActivityMain.getResources().getDrawable(R.drawable.img_88);
 	    itemzedOverlays.put(88, new MapItemizedOverlay(drawable88, locustaMapActivityMain));
		
	    
		// Icones Bars
	    Drawable drawable37 = locustaMapActivityMain.getResources().getDrawable(R.drawable.img_37);
	    itemzedOverlays.put(37, new MapItemizedOverlay(drawable37, locustaMapActivityMain));
		
	    
	    // Icones Restaurants
	    Drawable drawable39 = locustaMapActivityMain.getResources().getDrawable(R.drawable.img_39);
	    itemzedOverlays.put(39, new MapItemizedOverlay(drawable39, locustaMapActivityMain));
	
	    // Icones Cinemas
	    Drawable drawable67 = locustaMapActivityMain.getResources().getDrawable(R.drawable.img_67);
	    itemzedOverlays.put(67, new MapItemizedOverlay(drawable67, locustaMapActivityMain));
		
		return itemzedOverlays;
	}
}
