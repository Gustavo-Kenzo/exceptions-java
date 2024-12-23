package model.entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import model.exceptions.DomainException;

public class Reservation {
	private Integer roomNumber;
	private Date checkin;
	private Date checkout;

	private static SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

	public Reservation(Integer roomNumber, Date checkin, Date checkout) throws DomainException {
		if(checkout.before(checkin)) {
			throw new DomainException("Check-out must be after check-in date");
		}
		this.roomNumber = roomNumber;
		this.checkin = checkin;
		this.checkout = checkout;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public Date getCheckin() {
		return checkin;
	}

	public Date getCheckout() {
		return checkout;
	}

	public long duration() {
		long diff = checkout.getTime() - checkin.getTime();
		return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	}

	public void uptadeDates(Date checkin, Date checkout) throws DomainException{
		Date now = new Date();
		if (checkin.before(now) || checkout.before(now)) {
			throw new DomainException("Reservation dates for update must be future dates");
		} 
		if (!checkout.after(checkin)) {
			throw new DomainException("Check-out date must be after check-in date");
		} 
		this.checkin = checkin;
		this.checkout = checkout;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Reservation: ");
		sb.append("Room: " + roomNumber + ", ");
		sb.append("check-in: " + sdf1.format(checkin) + ", ");
		sb.append("check-out: " + sdf1.format(checkout) + ", ");
		sb.append(duration() + " nights");
		return sb.toString();
	}
}
