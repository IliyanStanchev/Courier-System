package tu_varna.project.courier_system.services;

import java.time.LocalDate;
import java.util.List;

import tu_varna.project.courier_system.dao.CourierFirmDaoImpl;
import tu_varna.project.courier_system.dao.NotificationDaoImpl;
import tu_varna.project.courier_system.dao.OfficeDaoImpl;
import tu_varna.project.courier_system.dao.ShipmentDaoImpl;
import tu_varna.project.courier_system.dao.UserDaoImpl;
import tu_varna.project.courier_system.entity.Client;
import tu_varna.project.courier_system.entity.Company;
import tu_varna.project.courier_system.entity.Courier;
import tu_varna.project.courier_system.entity.Notification;
import tu_varna.project.courier_system.entity.Office;
import tu_varna.project.courier_system.entity.Shipment;
import tu_varna.project.courier_system.entity.Status.status;
import tu_varna.project.courier_system.entity.Type;
import tu_varna.project.courier_system.entity.User;

public class UserServiceImpl implements UserService {

	private UserDaoImpl user = new UserDaoImpl();
	private CourierFirmDaoImpl firm = new CourierFirmDaoImpl();
	private ShipmentDaoImpl ship = new ShipmentDaoImpl();
	private OfficeDaoImpl office = new OfficeDaoImpl();
	private NotificationDaoImpl notif = new NotificationDaoImpl();

	@Override
	public boolean CreateClient(String username, String password, String name, String email, String phone,
			String country, String city, String street) {
		return user.save(new Client(username, password, name, email, phone, country, city, street));
	}

	@Override
	public boolean CreateCourier(String username, String password, String name, String email, String phone,
			String country, String city, String street, int bulstat) {
		return user.save(new Courier(username, password, name, email, phone, country, city, street, firm.get(bulstat)));
	}

	@Override
	public boolean CreateCourierFirm(int id, String companyName, String manager, String phone, String country,
			String city, String street) {
		return firm.save(new Company(id, companyName, manager, phone, country, city, street));
	}

	@Override
	public Company SearchCourierFirm(int bulstat) {
		return firm.get(bulstat);
	}

	@Override
	public List<Object[]> getShipmentsList(int bulstat) {
		return ship.getListShipmentsByFirm(bulstat);
	}

	@Override
	public void CreateShipment(Type.type type, LocalDate localDate, double shipmentPrice, int sender_id, User user2,
			int firm_id, int office_id) {

		ship.save(new Shipment(type, localDate, shipmentPrice, user.get(sender_id), user2, firm.get(firm_id),
				office.get(office_id)));

	}

	@Override
	public void CreateShipment(Type.type type, LocalDate localDate, double shipmentPrice, int sender_id, User user2,
			int firm_id) {

		ship.save(new Shipment(type, localDate, shipmentPrice, user.get(sender_id), user2, firm.get(firm_id)));

	}

	@Override
	public Shipment SearchShipment(int id, String phone) {
		return ship.getShipmentByNumAndPhone(id, phone);
	}

	@Override
	public User SearchUser(String name, String phone) {
		return user.getUserByNameAndPhone(name, phone);
	}

	@Override
	public void DeleteUser(User usr) {
		user.delete(usr);
	}

	@Override
	public List<String> getOfficesList(int bulstat) {
		return office.getOfficesByFirm(bulstat);
	}

	@Override
	public List<Object[]> getAllCouriers() {
		return user.getAllCouriers();
	}

	@Override
	public List<Object[]> getAllCompanies() {
		return firm.getAllCompanies();
	}

	@Override
	public void DeleteCompany(int id) {
		firm.delete(firm.get(id));
	}

	@Override
	public List<Object[]> getAllShipments() {
		return ship.getAllShipments();
	}

	@Override
	public void deleteShipment(int number) {
		ship.delete(ship.get(number));

	}

	@Override
	public User SearchUserByPhone(String phoneNmb) {

		return user.getUserByPhone(phoneNmb);

	}
	
	@Override
	public User SearchUserByUsername(String username) {

		return user.getUserByUsername(username);

	}

	@Override
	public int getBulstatByFirmName(String name) {
		return firm.getBulstatByFirmName(name);
	}

	@Override
	public int getIdByOfficeName(String name) {
		return office.getIdByOfficeName(name);
	}

	@Override
	public List<Object[]> getAllClients() {
		return user.getAllClients();
	}

	@Override
	public List<Object[]> getAllOffices() {
		return office.getAllOffices();

	}

	@Override
	public void deleteOffice(int code) {
		office.delete(office.get(code));

	}

	@Override
	public boolean CreateOffice(String company, String country, String city, String streetN, String agent,
			String phoneNmb, int firm_id) {
		return office
				.save(new Office(company + " " + streetN, country, city, streetN, agent, phoneNmb, firm.get(firm_id)));

	}

	@Override
	public Shipment SearchShipmentByID(int shipment_id) {
		return ship.get(shipment_id);

	}

	@Override
	public Company getCompanyByID(int bulstat) {
		return firm.get(bulstat);
	}

	@Override
	public String getUserName(int id) {
		return user.getUserName(id);
	}

	@Override
	public User getUserByID(int id) {
		return user.get(id);
	}

	@Override
	public void ChangeShipmentStatus(Shipment shipment, status status) {

		ship.ChangeShipmentStatus(shipment, status);

	}

	@Override
	public void ChangeUserAddress(User user1, String country, String city, String street) {

		user.ChangeUserAddress(user1, country, city, street);

	}

	@Override
	public void ChangeUserPhone(User user1, String phone) {
		user.ChangeUserPhone(user1, phone);

	}

	@Override
	public void ChangeUserPassword(User user1, String password) {
		user.ChangeUserPassword(user1, password);
	}

	@Override
	public void ChangeUserEmail(User user1, String email) {
		user.ChangeUserEmail(user1, email);

	}

	@Override
	public int getBulstatByCourier(int id) {
		return user.getBulstatByCourier(id);
	}

	@Override
	public void setShipmentCourier(Shipment shipment, int courier_id) {

		ship.setShipmentCourier(shipment, (Courier) user.get(courier_id));

	}

	@Override
	public void handleUserNotificationAnswer(boolean answer, Notification notification) {
		Shipment shipment = notification.getShipment();
		if (answer == true) {
			ship.ChangeShipmentStatus(shipment, status.accepted);

		} else if (answer == false) {
			ship.ChangeShipmentStatus(shipment, status.declined);
			Shipment new_shipment = new Shipment(status.in_proccess_of_return,shipment.getType(), LocalDate.now(), 0.00, shipment.getReceiver(),
					shipment.getSender(),shipment.getCourier(), shipment.getFirm());
			ship.save(new_shipment);
			new ShipmentDelivery(new_shipment);
			System.out.println("You declined the delivery. The return of the shipment will cost you:"
					+ shipment.getType().showPrice());
			notif.save(new Notification(shipment.getSender(),
					shipment.getReceiver().getName() + " declined your shipment! A courier will bring it back to you!",
					false));

		}
		notif.delete(notification);

	}

	@Override
	public Notification SearchNotificationByID(int notificationId) {
		return notif.get(notificationId);
	}

	@Override
	public void DeleteNotification(Notification notification) {
		notif.delete(notification);

	}

	@Override
	public Company SearchCompany(int bulstat) {
		return firm.get(bulstat);
		
	}
}
