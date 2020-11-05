package tu_varna.project.courier_system.entity;

public class Status {
	public enum status
	{
		pending,
		delivered,
		not_delivered,
		declined, 
		taken_by_courier;
	}

}
