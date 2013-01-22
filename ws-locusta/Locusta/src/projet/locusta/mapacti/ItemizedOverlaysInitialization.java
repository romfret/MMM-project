package projet.locusta.mapacti;

import java.util.HashMap;
import java.util.Map;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;

public class ItemizedOverlaysInitialization {

	@SuppressLint("UseSparseArrays")
	private Map<Integer, MapItemizedOverlay> itemzedOverlays = new HashMap<Integer, MapItemizedOverlay>();
	
	/**
	 * Set the icons listS
	 * @param locustaMapActivityMain
	 * @return
	 */
	public Map<Integer, MapItemizedOverlay> init(LocustaMapActivityMain locustaMapActivityMain) {
		
		// Icone User
//		Drawable drawable88 = locustaMapActivityMain.getResources().getDrawable(R.drawable.img_88);
//	    itemzedOverlays.put(88, new MapItemizedOverlay(drawable88, locustaMapActivityMain));
		
		// Icones Bars
	    Drawable drawable37 = locustaMapActivityMain.getResources().getDrawable(R.drawable.img_37);
	    itemzedOverlays.put(37, new MapItemizedOverlay(drawable37, locustaMapActivityMain));
		
	    // Icones Restaurants
	    Drawable drawable39 = locustaMapActivityMain.getResources().getDrawable(R.drawable.img_39);
	    itemzedOverlays.put(39, new MapItemizedOverlay(drawable39, locustaMapActivityMain));
	    
	    
		
		return itemzedOverlays;
	}
}
