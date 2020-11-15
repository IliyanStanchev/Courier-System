package tu_varna.project.courier_system.services;

import java.time.LocalDate;
import java.util.List;

import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Notification;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status.status;
import tu_varna.project.courier_system.entity.Type;
import tu_varna.project.courier_system.entity.User;

public interface UserService {

	boolean CreateClient(String username, String password, String name, String email, String phone, String country,
			String city, String street);

	boolean CreateCourier(String username, String password, String name, String email, String phone, String country,
			String city, String street, int bulstat);

	boolean CreateCourierFirm(int id, String companyName, String manager, String phone, String country, String city,
			String street);

	Company SearchCourierFirm(int bulstat);

	List<Object[]> getShipmentsList(int bulstat);

	void CreateShipment(Type.type type, LocalDate localDate, double shipmentPrice, int sender_id, User user2,
			int firm_id, int office_id);

	void CreateShipment(Type.type type, LocalDate localDate, double shipmentPrice, int sender_id, User user2,
			int firm_id);

	Shipment SearchShipment(int id, String phone);
	
	Company SearchCompany(int bulstat);

	User SearchUser(String name, String phone);

	void DeleteUser(User usr);

	List<String> getOfficesList(int bulstat);

	List<Object[]> getAllCouriers();

	List<Object[]> getAllCompanies();

	void DeleteCompany(int id);

	List<Object[]> getAllShipments();

	void deleteShipment(int number);

	User SearchUserByPhone(String phoneNmb);
	
	User SearchUserByUsername(String username); 

	int getBulstatByFirmName(String name);

	int getIdByOfficeName(String name);

	List<Object[]> getAllClients();

	List<Object[]> getAllOffices();

	void deleteOffice(int code);

	boolean CreateOffice(String company, String country, String city, String streetN, String agent, String phoneNmb,
			int firm_id);

	Shipment SearchShipmentByID(int shipment_id);

	Company getCompanyByID(int bulstat);

	String getUserName(int id);

	User getUserByID(int id);

	void ChangeShipmentStatus(Shipment shipment, status status);

	void ChangeUserAddress(User user1, String country, String city, String street);

	void ChangeUserPhone(User user1, String phone);

	void ChangeUserPassword(User user1, String password);

	void ChangeUserEmail(User user1, String email);

	int getBulstatByCourier(int id);

	void setShipmentCourier(Shipment shipment, int courier_id);

	void handleUserNotificationAnswer(boolean answer, Notification notification);

	Notification SearchNotificationByID(int notificationId);

	void DeleteNotification(Notification notification);

}