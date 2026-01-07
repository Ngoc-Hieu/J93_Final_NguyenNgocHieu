package vn.devpro.management.database;

import java.util.ArrayList;
import java.util.List;
import vn.devpro.management.model.Book;

public class BookDB {
    private static int autoId = 1;

    private static List<Book> books = new ArrayList<>() {
        {
            add(new Book(autoId++, "Book-101", 1, 2, "Bao Cong ky an", 250, 10));
            add(new Book(autoId++, "Book-102", 2, 3, "AI va con duong moi", 300, 5));
            add(new Book(autoId++, "Book-103", 3, 4, "Nguyen ly he dieu hanh", 280, 8));
            add(new Book(autoId++, "Book-104", 4, 1, "Mau ao thinh hanh", 320, 12));
        }
    };

    public static List<Book> getBooks() {
        return books;
    }

    public static void setBooks(List<Book> books) {
        BookDB.books = books;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        BookDB.autoId = autoId;
    }

    public static Book findByID(int id) {
        for (Book b : books) {
            if (b.getId() == id) {
                return b;
            }
        }
        return null;
    }
public static int findByCode(String code) {
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getCode().trim().equalsIgnoreCase(code.trim())) {
                return i;
            }
        }
        return -1;
    }
   
}
