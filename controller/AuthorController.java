package vn.devpro.management.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import vn.devpro.management.database.AuthorDB;
import vn.devpro.management.model.Author;

public class AuthorController {
    static Scanner sc = new Scanner(System.in);

    public static void Menu() {
        while (true) {
            System.out.println("\n\t\tQUAN LY TAC GIA");
            System.out.println("Chon mot chuc nang quan ly:");
            System.out.println("\t1. Xem danh sach tac gia");
            System.out.println("\t2. Them tac gia moi");
            System.out.println("\t3. Sua thong tin tac gia");
            System.out.println("\t4. Xoa tac gia");
            System.out.println("\t5. Sap xep danh sach theo ten");
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
                case 0 -> { return; }
                default -> System.out.println("Lua chon khong hop le, xin chon lai");
            }
        }
    }

    // Hiển thị danh sách
    private static void display() {
        System.out.println("\n\t\tDANH SACH TAC GIA");
        System.out.printf("%-5s %-10s %-15s %-15s%n", "ID", "Code", "FirstName", "LastName");
        for (Author author : AuthorDB.getAuthors()) {
            author.display();
        }
    }

    // Thêm mới
    private static void create() {
        System.out.println("\n\t\tTHEM MOI TAC GIA");
        Author author = new Author();
        author.input();
        AuthorDB.getAuthors().add(author);
        AuthorDB.setAutoId(AuthorDB.getAutoId() + 1);
        System.out.println("\tThem moi tac gia thanh cong!");
    }

    // Sửa thông tin
    private static void update() {
        System.out.println("\n\t\tSUA THONG TIN TAC GIA");
        System.out.print("\tNhap id tac gia: ");
        int id = Integer.parseInt(sc.nextLine());
        Author author = AuthorDB.findByID(id);
        if (author == null) {
            System.out.println("\tTac gia khong ton tai!");
            return;
        }
        author.edit();
    }

    // Xoá
    private static void remove() {
        System.out.println("\n\t\tXOA TAC GIA");
        System.out.print("\tNhap ma tac gia: ");
        String code = sc.nextLine();
        int index = AuthorDB.findByCode(code);
        if (index == -1) {
            System.out.println("\tTac gia khong ton tai");
            return;
        }
        AuthorDB.getAuthors().remove(index);
        System.out.println("\tXoa tac gia thanh cong");
    }

    // Sắp xếp theo tên
    private static void sort() {
        Collections.sort(AuthorDB.getAuthors(), new Comparator<Author>() {
            @Override
            public int compare(Author o1, Author o2) {
                return o1.getLastName().compareToIgnoreCase(o2.getLastName());
            }
        });
        System.out.println("\tSap xep danh sach tac gia thanh cong!");
    }
}
