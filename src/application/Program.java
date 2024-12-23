package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import model.entities.Reservation;
import model.exceptions.DomainException;

public class Program {

	public static void main(String[] args) {

		SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");

		Scanner sc = new Scanner(System.in);

		try {

			System.out.print("Room number: ");
			int roomNumber = sc.nextInt();
			System.out.print("Check-in date (dd/MM/yyyy): ");
			Date checkin = sdf1.parse(sc.next());
			System.out.print("Check-out date (dd/MM/yyyy): ");
			Date checkout = sdf1.parse(sc.next());

			System.out.println("Error in reservation: Check-out must be after check-in date");

			Reservation reservation = new Reservation(roomNumber, checkin, checkout);
			System.out.println(reservation.toString());

			System.out.println();
			System.out.println("Enter data to update the reservation: ");
			System.out.print("Check-in date (dd/MM/yyyy): ");
			checkin = sdf1.parse(sc.next());
			System.out.print("Check-out date (dd/MM/yyyy): ");
			checkout = sdf1.parse(sc.next());
			reservation.uptadeDates(checkin, checkout);
			System.out.println(reservation.toString());

			sc.close();
		} catch (ParseException e) {
			System.out.println("Invalid date format");
		} catch (DomainException e) {
			System.out.println("Error in reservation: " + e.getMessage());
		} 
	}
}