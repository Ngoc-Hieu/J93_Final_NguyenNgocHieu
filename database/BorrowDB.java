package vn.devpro.management.database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import vn.devpro.management.dto.BorrowTicket;

public class BorrowDB {
    private static int autoId = 101; 

    private static List<BorrowTicket> tickets = new ArrayList<>() {
        {

            List<Integer> book1 = new ArrayList<>();
            book1.add(1);
            book1.add(2);
            book1.add(3);
            add(new BorrowTicket(autoId++, 1, LocalDate.of(2023, 5, 22), book1));

            List<Integer> book2 = new ArrayList<>();
            book2.add(2);
            book2.add(1);
            add(new BorrowTicket(autoId++, 2, LocalDate.of(2023, 6, 10), book2));
        }
    };

    public static List<BorrowTicket> getTickets() {
        return tickets;
    }

    public static void setTickets(List<BorrowTicket> tickets) {
        BorrowDB.tickets = tickets;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void incrementAutoId() {
        autoId++;
    }

    
}
