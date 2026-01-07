package vn.devpro.management.model;

import java.net.Socket;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import vn.devpro.management.database.ReaderDB;

public class Reader {
    private int id;
    private String code;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private String gender;
    private boolean isStudent;

    public Reader() {
    }

    public Reader(int id, String code, String firstName, String lastName, LocalDate dateOfBirth, String gender,
            boolean isStudent) {
        this.id = id;
        this.code = code;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.isStudent = isStudent;
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

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean isStudent() {
        return isStudent;
    }

    public void setStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    
    public void display() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.printf("%-3d %-10s %-10s %-10s %-12s %-6s %-8s%n",
            id, code, firstName, lastName, dateOfBirth.format(formatter),
            gender, isStudent ? "Student" : "Not Student"
        );
    }
    public void input(){
        Scanner sc = new Scanner(System.in);
        id = ReaderDB.getAutoId();
        code = "Reader-" + (100+ReaderDB.getAutoId());
        System.out.printf("Nhap firstName: ");
        firstName = sc.nextLine();
        System.out.printf("Nhap lastName: ");
        lastName = sc.nextLine();
        System.out.printf("Nhap dateOfBirth (dd//MM/yyyy): ");
        String str = sc.nextLine();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateOfBirth = LocalDate.parse(str,dtf);
         System.out.print("Nhap gender: ");
        gender = sc.nextLine();
        System.out.print("Co phai sinh vien khong? (true/false): ");
        isStudent = Boolean.parseBoolean(sc.nextLine());
    }
    public void edit(){
         Scanner sc = new Scanner(System.in);
         System.out.println("\n\t\tSUA THONG TIN DOC GIA");
        System.out.printf("Nhap firstName moi: ");
        firstName = sc.nextLine();
        System.out.printf("Nhap lastName moi: ");
        lastName = sc.nextLine();
        System.out.printf("Nhap dateOfBirth moi (dd//MM/yyyy): ");
        String str = sc.nextLine();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        dateOfBirth = LocalDate.parse(str,dtf);
         System.out.print("Nhap gender moi: ");
        gender = sc.nextLine();
        System.out.print("Co phai sinh vien khong? (true/false): ");
        isStudent = Boolean.parseBoolean(sc.nextLine());
        System.out.println("Sua thong tin doc gia thanh cong");
    }

}
