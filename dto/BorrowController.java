package vn.devpro.management.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import vn.devpro.management.database.AuthorDB;
import vn.devpro.management.database.BookDB;
import vn.devpro.management.database.ReaderDB;
import vn.devpro.management.database.BorrowDB;
import vn.devpro.management.database.CategoryDB;
import vn.devpro.management.model.Author;
import vn.devpro.management.model.Book;
import vn.devpro.management.model.Reader;

public class BorrowController {
    static Scanner sc = new Scanner(System.in);

    public static void menu() {
        while (true) {
            System.out.println("\n\t\tQUAN LY PHIEU MUON SACH");
            System.out.println("\t1. Tao phieu muon moi");
            System.out.println("\t2. Xem danh sach phieu muon");
            System.out.println("\t3. Xoa phieu muon");
            System.out.println("\t4. Xem danh sach sach trong phieu");
            System.out.println("\t5. Them sach vao phieu");
            System.out.println("\t6. Sua sach trong phieu");
            System.out.println("\t7. Xoa sach trong phieu");
            System.out.println("\t0. Quay lai");

            System.out.print("Lua chon: ");
            int choose;

            choose = Integer.parseInt(sc.nextLine());

            switch (choose) {
                case 1 -> createTicket();
                case 2 -> displayTickets();
                case 3 -> removeTicket();
                case 4 -> viewTicketBooks();
                case 5 -> addBookToTicket();
                case 6 -> editBookInTicket();
                case 7 -> removeBookFromTicket();
                case 0 -> {
                    return;
                }
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
    }

    // Tạo phiếu mượn mới
    private static void createTicket() {
        System.out.print("Nhap ID doc gia: ");
        int readerId = Integer.parseInt(sc.nextLine());
        Reader reader = ReaderDB.findByID(readerId);

        if (reader == null) {
            System.out.println("Doc gia khong ton tai!");
            return;
        }

        List<Integer> books_ID = new ArrayList<>();
        BorrowTicket ticket = new BorrowTicket(BorrowDB.getAutoId(), reader.getId(), LocalDate.now(), books_ID);

        while (true) {
            if (books_ID.size() >= 3) {
                System.out.println("Moi phieu chi duoc muon toi da 3 sach!");
                break;
            }

            System.out.print("Nhap ID sach muon: ");
            int bookId = Integer.parseInt(sc.nextLine());
            if (bookId == 0)
                break;

            if (!canAddBookToTicket(ticket, reader, bookId)) {
                continue;
            }

            ticket.addBook(bookId);
            Book book = BookDB.findByID(bookId);
            book.setQuantity(book.getQuantity() - 1);
            System.out.println("Da them sach vao phieu!");
        }

        BorrowDB.getTickets().add(ticket);
        BorrowDB.incrementAutoId();
        System.out.println("Tao phieu muon thanh cong!");
        ticket.printReceipt();
    }

    // Hiển thị danh sách phiếu mượn
    private static void displayTickets() {
        System.out.println("\n\t\tDANH SACH PHIEU MUON");
        for (BorrowTicket t : BorrowDB.getTickets()) {
            t.printReceipt();
        }
    }

    // Xóa phiếu mượn
    private static void removeTicket() {
        System.out.print("Nhap ID phieu muon can xoa: ");
        int id = Integer.parseInt(sc.nextLine());
        BorrowTicket ticket = BorrowDB.getTickets().stream()
                .filter(t -> t.getId() == id)
                .findFirst()
                .orElse(null);
        if (ticket != null) {
            // Hoàn trả số lượng sách khi xóa phiếu
            for (int bookId : ticket.getBooks_ID()) {
                Book b = BookDB.findByID(bookId);
                if (b != null)
                    b.setQuantity(b.getQuantity() + 1);
            }
            BorrowDB.getTickets().remove(ticket);
            System.out.println("Da xoa phieu muon!");
        } else {
            System.out.println("Khong tim thay phieu muon!");
        }
    }

    // Xem danh sách sách trong phiếu
    private static void viewTicketBooks() {
        BorrowTicket ticket = inputTicketById();
        if (ticket == null)
            return;
        ticket.printReceipt();
    }

    // Thêm sách vào phiếu
    private static void addBookToTicket() {
        BorrowTicket ticket = inputTicketById();
        if (ticket == null)
            return;

        Reader reader = ReaderDB.findByID(ticket.getReader_ID());
        if (reader == null) {
            System.out.println("Doc gia khong ton tai cho phieu nay!");
            return;
        }

        if (ticket.getBooks_ID().size() >= 3) {
            System.out.println("Moi phieu chi duoc muon toi da 3 sach!");
            return;
        }

        System.out.print("Nhap ID sach can them: ");
        int bookId = Integer.parseInt(sc.nextLine());

        if (!canAddBookToTicket(ticket, reader, bookId))
            return;

        ticket.addBook(bookId);
        Book b = BookDB.findByID(bookId);
        b.setQuantity(b.getQuantity() - 1);
        System.out.println("Da them sach vao phieu!");
    }

    // Sửa sách trong phiếu (thay thế một bookID bằng bookID khác)
    private static void editBookInTicket() {
        BorrowTicket ticket = inputTicketById();
        if (ticket == null)
            return;

        Reader reader = ReaderDB.findByID(ticket.getReader_ID());
        if (reader == null) {
            System.out.println("Doc gia khong ton tai cho phieu nay!");
            return;
        }

        if (ticket.getBooks_ID().isEmpty()) {
            System.out.println("Phieu chua co sach de sua!");
            return;
        }

        System.out.print("Nhap ID sach dang co trong phieu can thay: ");
        int oldBookId = Integer.parseInt(sc.nextLine());
        if (!ticket.getBooks_ID().contains(oldBookId)) {
            System.out.println("Sach nay khong co trong phieu!");
            return;
        }

        System.out.print("Nhap ID sach moi thay the: ");
        int newBookId = Integer.parseInt(sc.nextLine());

        if (!canAddBookToTicket(ticket, reader, newBookId))
            return;

        Book oldBook = BookDB.findByID(oldBookId);
        if (oldBook != null)
            oldBook.setQuantity(oldBook.getQuantity() + 1);

        Book newBook = BookDB.findByID(newBookId);
        newBook.setQuantity(newBook.getQuantity() - 1);

        ticket.getBooks_ID().remove(Integer.valueOf(oldBookId));
        ticket.addBook(newBookId);

        System.out.println("Da sua sach trong phieu!");
    }

    // Xóa sách trong phiếu
    private static void removeBookFromTicket() {
        BorrowTicket ticket = inputTicketById();
        if (ticket == null)
            return;

        if (ticket.getBooks_ID().isEmpty()) {
            System.out.println("Phieu chua co sach de xoa!");
            return;
        }

        System.out.print("Nhap ID sach can xoa khoi phieu: ");
        int bookId = Integer.parseInt(sc.nextLine());
        if (!ticket.getBooks_ID().contains(bookId)) {
            System.out.println("Sach nay khong co trong phieu!");
            return;
        }

        Book b = BookDB.findByID(bookId);
        if (b != null)
            b.setQuantity(b.getQuantity() + 1);

        ticket.removeBook(bookId);
        System.out.println("Da xoa sach khoi phieu!");
    }

    // Hàm nhập ID phiếu mượn và tìm phiếu đó trong danh sách
    private static BorrowTicket inputTicketById() {
        System.out.print("Nhap ID phieu muon: ");
        int id = Integer.parseInt(sc.nextLine());
        for (BorrowTicket ticket : BorrowDB.getTickets()) {
            if (ticket.getId() == id) {
                return ticket;
            }
        }

        // Nếu không tìm thấy thì báo và trả về null
        System.out.println("Khong tim thay phieu muon!");
        return null;
    }

    // kiểm tra ràng buộc trước khi thêm sách
    private static boolean canAddBookToTicket(BorrowTicket ticket, Reader reader, int bookId) {
        if (ticket.getBooks_ID().size() >= 3) {
            System.out.println("Moi phieu chi duoc muon toi da 3 sach!");
            return false;
        }

        Book book = BookDB.findByID(bookId);
        if (book == null) {
            System.out.println("Sach khong ton tai!");
            return false;
        }
        if (book.getQuantity() <= 0) {
            System.out.println("Sach da het hang!");
            return false;
        }

        if (ticket.getBooks_ID().contains(bookId)) {
            System.out.println("Moi sach chi duoc muon 1 cuon!");
            return false;
        }

        int maxAllowed = reader.isStudent() ? 8 : 5;
        if (ticket.getBooks_ID().size() >= maxAllowed) {
            System.out.println("Doc gia da dat gioi han muon!");
            return false;
        }

        return true;
    }

    public static void statsByCategory() {
        Map<String, Integer> countMap = new HashMap<>();
        for (BorrowTicket t : BorrowDB.getTickets()) {
            for (int bookId : t.getBooks_ID()) {
                Book b = BookDB.findByID(bookId);
                String category = CategoryDB.findByID(b.getCategory_ID()).getName();
                countMap.put(category, countMap.getOrDefault(category, 0) + 1);
            }
        }
        System.out.println("\nThong ke sach theo chuong loai:");
        countMap.forEach((k, v) -> System.out.println("- " + k + ": " + v + " cuon"));
    }

    public static void statsByAuthor() {
        Map<String, Integer> countMap = new HashMap<>();
        for (BorrowTicket t : BorrowDB.getTickets()) {
            for (int bookId : t.getBooks_ID()) {
                Book b = BookDB.findByID(bookId);
                Author a = AuthorDB.findByID(b.getAuthor_ID());
                String authorName = a.getFirstName() + " " + a.getLastName();
                countMap.put(authorName, countMap.getOrDefault(authorName, 0) + 1);
            }
        }
        System.out.println("\nThong ke sach theo tac gia:");
        countMap.forEach((k, v) -> System.out.println("- " + k + ": " + v + " cuon"));
    }

    public static void statsBorrowedBooks() {
        System.out.println("\nCac cuon sach dang duoc muon:");
        for (BorrowTicket t : BorrowDB.getTickets()) {
            Reader r = ReaderDB.findByID(t.getReader_ID());
            for (int bookId : t.getBooks_ID()) {
                Book b = BookDB.findByID(bookId);
                Author a = AuthorDB.findByID(b.getAuthor_ID());
                System.out.println("- " + b.getName() + " | " + a.getFirstName() + " " + a.getLastName() + " | "
                        + r.getFirstName() + " " + r.getLastName());
            }
        }
    }

    public static void statsAvailableBooks() {
        System.out.println("\nCac cuon sach con lai co the muon:");
        for (Book b : BookDB.getBooks()) {
            if (b.getQuantity() > 0) {
                Author a = AuthorDB.findByID(b.getAuthor_ID());
                System.out.println("- " + b.getName() + " | " + a.getFirstName() + " " + a.getLastName()
                        + " | So luong: " + b.getQuantity());
            }
        }
    }

    public static void statsByReader() {
        System.out.println("\nThong ke sach da muon theo tung doc gia:");
        for (Reader r : ReaderDB.getReaders()) {
            System.out.println("\nDoc gia: " + r.getFirstName() + " " + r.getLastName());
            for (BorrowTicket t : BorrowDB.getTickets()) {
                if (t.getReader_ID() == r.getId()) {
                    for (int bookId : t.getBooks_ID()) {
                        Book b = BookDB.findByID(bookId);
                        System.out.println("- " + b.getName());
                    }
                }
            }
        }
    }
}
