package vn.devpro.management.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import vn.devpro.management.database.ReaderDB;
import vn.devpro.management.model.Reader;

public class ReaderController {
    static Scanner sc = new Scanner(System.in);

    public static void Menu() {
        do {
            System.out.println("\n\t\tQUAN LY DANH SACH DOC GIA");
            System.out.println("Chon mot chuc nang quan ly");
            System.out.println("\t1. Xem danh sach doc gia");
            System.out.println("\t2. Them doc gia moi");
            System.out.println("\t3. Sua thong tin doc gia");
            System.out.println("\t4. Xoa doc gia");
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
        } while (true);
    }

    // Hiển thị danh sách
    private static void display() {
        System.out.println("\n\t\tDANH SACH DOC GIA");
        System.out.printf("%-3s %-10s %-10s %-10s %-12s %-6s %-8s%n",
                "ID", "Code", "FirstName", "LastName", "Birth", "Gender", "Student");
        for (Reader reader : ReaderDB.getReaders()) {
            reader.display();
        }
    }

    // Thêm mới
    private static void create() {
        System.out.println("\n\t\tTHEM MOI DOC GIA");
        Reader reader = new Reader();
        reader.input();
        ReaderDB.getReaders().add(reader);
        ReaderDB.setAutoId(ReaderDB.getAutoId() + 1);
        System.out.println("\tThem moi doc gia thanh cong!");
    }

    // Sửa thông tin
    private static void update() {
        System.out.println("\n\t\tSUA THONG TIN DOC GIA");
        System.out.print("\tNhap id doc gia: ");
        int id = Integer.parseInt(sc.nextLine());
        Reader reader = ReaderDB.findByID(id);
        if (reader == null) {
            System.out.println("\tDoc gia khong ton tai!");
            return;
        }
        reader.edit();
    }

    // Xoá
    private static void remove() {
        System.out.println("\n\t\tXOA DOC GIA");
        System.out.printf("\tNhap ma doc gia: ");
        String code = sc.nextLine();
        int index = ReaderDB.findByCode(code);
        if (index == -1) {
            System.out.println("\tDoc gia khong ton tai");
            return;
        }
        ReaderDB.getReaders().remove(index);
        System.out.println("\tXoa doc gia thanh cong");
    }

    //Sắp xếp theo tên
    private static void sort() {
        Collections.sort(ReaderDB.getReaders(),new Comparator<Reader>() {
            @Override
            public int compare(Reader o1, Reader o2) {
                // TODO Auto-generated method stub
                return o1.getLastName().compareToIgnoreCase(o2.getLastName());
            }
        });
    }
}
