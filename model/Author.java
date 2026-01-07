package vn.devpro.management.model;

import java.util.Scanner;

import vn.devpro.management.database.AuthorDB;

public class Author {
    private int id;
    private String code;
    private String firstName;
    private String lastName;

    private static final Scanner sc = new Scanner(System.in);

    public Author() {
    }

    public Author(int id, String code, String firstName, String lastName) {
        this.id = id;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
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

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

  
    public void display() {
        System.out.printf("%5d %10s %-15s %-15s%n", id, code, firstName, lastName);
    }

    public void input() {
        this.id = AuthorDB.getAutoId();
        this.code = "Author-"+(100+id);
        System.out.print("Nhap firstName: ");
        this.firstName = sc.nextLine();
        System.out.print("Nhap lastName: ");
        this.lastName = sc.nextLine();
    }

    public void edit() {
        System.out.println("\n\t\tSUA THONG TIN TAC GIA");
        System.out.print("\tNhap firstName moi: ");
        String newFirst = sc.nextLine();
        if (newFirst != null && !newFirst.isBlank()) {
            this.setFirstName(newFirst);
        }

        System.out.print("\tNhap lastName moi: ");
        String newLast = sc.nextLine();
        if (newLast != null && !newLast.isBlank()) {
            this.setLastName(newLast);
        }

        System.out.println("\tSua thong tin tac gia thanh cong!");
    }
}
