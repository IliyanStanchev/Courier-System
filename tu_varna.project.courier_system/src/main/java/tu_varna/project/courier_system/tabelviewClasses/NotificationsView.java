package tu_varna.project.courier_system.tabelviewClasses;

public class NotificationsView
{

	private int notificationId;
	private String notificationText;

	public NotificationsView(int notificationId, String notificationText)
	{
		super();
		this.notificationId = notificationId;
		this.notificationText = notificationText;
	}

	public int getNotificationId()
	{
		return notificationId;
	}

	public String getNotificationText()
	{
		return notificationText;
	}

	public void setNotificationId(int notificationId)
	{
		this.notificationId = notificationId;
	}

	public void setNotificationText(String notificationText)
	{
		this.notificationText = notificationText;
	}

}
