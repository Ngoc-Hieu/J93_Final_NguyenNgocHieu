package vn.devpro.management.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import vn.devpro.management.database.BookDB;
import vn.devpro.management.model.Book;

public class BookController {
    static Scanner sc = new Scanner(System.in);

    public static void Menu() {
        while (true) {
            System.out.println("\n\t\tQUAN LY SACH");
            System.out.println("Chon mot chuc nang quan ly:");
            System.out.println("\t1. Xem danh sach sach");
            System.out.println("\t2. Them sach moi");
            System.out.println("\t3. Sua thong tin sach");
            System.out.println("\t4. Xoa sach");
            System.out.println("\t5. Sap xep danh sach theo ten sach");
            System.out.println("\t6. Tim sach theo danh muc");
            System.out.println("\t7. Tim sach theo tac gia");
            System.out.println("\t8. Tim sach theo ten");

            System.out.println("\t0. Quay lai");

            System.out.print("Lua chon cua ban: ");
            int choose;
            
            choose = Integer.parseInt(sc.nextLine());
            

            switch (choose) {
                case 1 -> display();
                case 2 -> create();
                case 3 -> update();
                case 4 -> remove();
                case 5 -> sort();
                case 6 -> searchByCategory();
                case 7 -> searchByAuthor();
                case 8 -> searchByName();

                case 0 -> {
                    return;
                }
                default -> System.out.println("Lua chon khong hop le, xin chon lai");
            }
        }
    }

    // Hiển thị danh sách
    private static void display() {
        System.out.println("\n\t\tDANH SACH SACH");
        System.out.printf("%-5s %-10s %-15s %-15s %-20s %-20s %-8s %-8s%n",
                "ID", "Code", "Author", "LastName", "Category", "Name", "Pages", "Quantity");
        for (Book book : BookDB.getBooks()) {
            book.display();
        }
    }

    // Thêm mới
    private static void create() {
        System.out.println("\n\t\tTHEM MOI SACH");
        Book book = new Book();
        book.input();
        BookDB.getBooks().add(book);
        BookDB.setAutoId(BookDB.getAutoId() + 1);
        System.out.println("\tThem moi sach thanh cong!");
    }

    // Sửa thông tin
    private static void update() {
        System.out.println("\n\t\tSUA THONG TIN SACH");
        System.out.print("\tNhap id sach: ");
        int id = Integer.parseInt(sc.nextLine());
        Book book = BookDB.findByID(id);
        if (book == null) {
            System.out.println("\tSach khong ton tai!");
            return;
        }
        book.edit();
    }

    // Xoá
    private static void remove() {
        System.out.println("\n\t\tXOA SACH");
        System.out.print("\tNhap ma sach: ");
        String code = sc.nextLine();
        int index = BookDB.findByCode(code);
        if (index == -1) {
            System.out.println("\tSach khong ton tai");
            return;
        }
        BookDB.getBooks().remove(index);
        System.out.println("\tXoa sach thanh cong");
    }

    // Sắp xếp theo tên sách
    private static void sort() {
        Collections.sort(BookDB.getBooks(), new Comparator<Book>() {
            @Override
            public int compare(Book o1, Book o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        System.out.println("\tSap xep danh sach sach thanh cong!");
    }

    private static void searchByCategory() {
        System.out.print("\nNhap ID danh muc: ");
        int categoryId = Integer.parseInt(sc.nextLine());
        System.out.println("\n\t\tKET QUA TIM KIEM THEO DANH MUC");
        for (Book book : BookDB.getBooks()) {
            if (book.getCategory_ID() == categoryId) {
                book.display();
            }
        }
    }

    private static void searchByAuthor() {
        System.out.print("\nNhap ID tac gia: ");
        int authorId = Integer.parseInt(sc.nextLine());
        System.out.println("\n\t\tKET QUA TIM KIEM THEO TAC GIA");
        for (Book book : BookDB.getBooks()) {
            if (book.getAuthor_ID() == authorId) {
                book.display();
            }
        }
    }

    private static void searchByName() {
        System.out.print("\nNhap ten sach: ");
        String keyword = sc.nextLine().toLowerCase();
        System.out.println("\n\t\tKET QUA TIM KIEM THEO TEN SACH");
        for (Book book : BookDB.getBooks()) {
            if (book.getName().toLowerCase().contains(keyword)) {
                book.display();
            }
        }
    }

}
