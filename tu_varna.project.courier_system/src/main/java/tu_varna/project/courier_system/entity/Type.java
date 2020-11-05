package tu_varna.project.courier_system.entity;

public class Type {
 
	public enum type{
		bag(5.50),
		packet(7.50),
		parcel(10.50),
		cargo(12.50),
		document(2.50);
		
		double price;
		  type(double p) {
		      price = p;
		   }
		   public double showPrice() {
		      return price;
		   }
	}
}
