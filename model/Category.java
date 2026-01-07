package vn.devpro.management.model;

import java.util.Scanner;

import vn.devpro.management.database.CategoryDB;

public class Category {
    private int id;
    private String code;
    private String name;

    private static final Scanner sc = new Scanner(System.in);

    public Category() {
    }

    public Category(int id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void display() {
        System.out.printf("%5d %10s %-20s%n", id, code, name);
    }

    public void input() {
        this.id = CategoryDB.getAutoId();
        this.code = "Category-"+(100+id);
        System.out.print("Nhap ten danh muc: ");
        this.name = sc.nextLine();
       
    }

    public void edit() {
        System.out.println("\n\t\tSUA THONG TIN DANH MUC");
        System.out.print("\tNhap ten moi: ");
        String newName = sc.nextLine();
        if (newName == null || newName.isBlank()) {
            System.out.println("\tTen khong duoc bo trong!");
        } else {
            this.setName(newName);
        }
        System.out.println("\tSua thong tin danh muc thanh cong!");
    }
}
