package GIS;
import java.util.Set;
/**
 * This interface represents a gis layer that contains multiple elements
 * @author Alex,Ilya,Nour
 *
 */
public interface GIS_layer extends Set<GIS_element> {
	
	public Meta_data get_Meta_data(); 
	
}
