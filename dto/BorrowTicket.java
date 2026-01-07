package vn.devpro.management.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import vn.devpro.management.database.BookDB;
import vn.devpro.management.database.ReaderDB;
import vn.devpro.management.model.Book;
import vn.devpro.management.model.Reader;

public class BorrowTicket {
    private int id;
    private int reader_ID;
    private LocalDate borrowDate;
    private List<Integer> books_ID;

    

    

    public BorrowTicket() {
    }

    public BorrowTicket(int id, int reader_ID, LocalDate borrowDate, List<Integer> books_ID) {
        this.id = id;
        this.reader_ID = reader_ID;
        this.borrowDate = borrowDate;
        this.books_ID = books_ID;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReader_ID() {
        return reader_ID;
    }

    public void setReader_ID(int reader_ID) {
        this.reader_ID = reader_ID;
    }

    public LocalDate getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }

    public List<Integer> getBooks_ID() {
        return books_ID;
    }

    public void setBooks_ID(List<Integer> books_ID) {
        this.books_ID = books_ID;
    }

    public void addBook(int bookID){
        books_ID.add(bookID);
    }
    public void removeBook(int bookID){
        books_ID.remove(bookID);
    }

    public void printReceipt() {
        Reader reader = ReaderDB.findByID(reader_ID);
        System.out.println("\nTRUNG TAM TT THU VIEN");
        System.out.println("TRUONG DH DEVPRO");
        System.out.println("PHIEU MUON SACH\n");

        System.out.println("Ma phieu: " + id);
        System.out.println("Ma doc gia: " + reader.getId());
        System.out.println("Ho va ten doc gia: " + reader.getFirstName() + " " + reader.getLastName());
        System.out.println("Ngay muon: " + borrowDate.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.println("\nDanh sach cac sach cho muon:");

        System.out.printf("%-5s %-30s %-25s %-20s %-15s%n", "STT", "Ten sach", "Ten tac gia", "Loai sach", "Ngay hen tra");
        List<Book> books = new ArrayList<>();
        for(int x : books_ID){
            if(BookDB.findByID(x) != null){
                books.add(BookDB.findByID(x));
            }
        }
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            String authorName = vn.devpro.management.database.AuthorDB.findByID(b.getAuthor_ID()).getFirstName()
                    + " " + vn.devpro.management.database.AuthorDB.findByID(b.getAuthor_ID()).getLastName();
            String categoryName = vn.devpro.management.database.CategoryDB.findByID(b.getCategory_ID()).getName();
            String returnDate = borrowDate.plusDays(5).format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));

            System.out.printf("%-5d %-30s %-25s %-20s %-15s%n", i + 1, b.getName(), authorName, categoryName, returnDate);
        }

        System.out.println("\nHa Noi, ngay " + java.time.LocalDate.now().format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy")));
        System.out.println("Thu thu");
        System.out.println("Nguyen Tran Mai Huong");
    }
}
