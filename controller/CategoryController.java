package vn.devpro.management.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import vn.devpro.management.database.CategoryDB;
import vn.devpro.management.model.Category;

public class CategoryController {
    static Scanner sc = new Scanner(System.in);

    public static void Menu() {
        while (true) {
            System.out.println("\n\t\tQUAN LY DANH MUC");
            System.out.println("Chon mot chuc nang quan ly:");
            System.out.println("\t1. Xem danh sach danh muc");
            System.out.println("\t2. Them danh muc moi");
            System.out.println("\t3. Sua thong tin danh muc");
            System.out.println("\t4. Xoa danh muc");
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
        System.out.println("\n\t\tDANH SACH DANH MUC");
        System.out.printf("%-5s %-10s %-20s%n", "ID", "Code", "Name");
        for (Category category : CategoryDB.getCategories()) {
            category.display();
        }
    }

    // Thêm mới
    private static void create() {
        System.out.println("\n\t\tTHEM MOI DANH MUC");
        Category category = new Category();
        category.input();
        CategoryDB.getCategories().add(category);
        CategoryDB.setAutoId(CategoryDB.getAutoId() + 1);
        System.out.println("\tThem moi danh muc thanh cong!");
    }

    // Sửa thông tin
    private static void update() {
        System.out.println("\n\t\tSUA THONG TIN DANH MUC");
        System.out.print("\tNhap id danh muc: ");
        int id = Integer.parseInt(sc.nextLine());
        Category category = CategoryDB.findByID(id);
        if (category == null) {
            System.out.println("\tDanh muc khong ton tai!");
            return;
        }
        category.edit();
    }

    // Xoá
    private static void remove() {
        System.out.println("\n\t\tXOA DANH MUC");
        System.out.print("\tNhap ma danh muc: ");
        String code = sc.nextLine();
        int index = CategoryDB.findByCode(code);
        if (index == -1) {
            System.out.println("\tDanh muc khong ton tai");
            return;
        }
        CategoryDB.getCategories().remove(index);
        System.out.println("\tXoa danh muc thanh cong");
    }

    // Sắp xếp theo tên
    private static void sort() {
        Collections.sort(CategoryDB.getCategories(), new Comparator<Category>() {
            @Override
            public int compare(Category o1, Category o2) {
                return o1.getName().compareToIgnoreCase(o2.getName());
            }
        });
        System.out.println("\tSap xep danh sach danh muc thanh cong!");
    }
}
