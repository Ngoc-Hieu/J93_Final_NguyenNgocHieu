package vn.devpro.management.model;

import java.util.Scanner;

import vn.devpro.management.database.AuthorDB;
import vn.devpro.management.database.BookDB;
import vn.devpro.management.database.CategoryDB;

public class Book {
    private int id;
    private String code;
    private int author_ID;
    private int category_ID;
    private String name;
    private int numberOfPages;
    private int quantity;

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

    public int getAuthor_ID() {
        return author_ID;
    }

    public void setAuthor_ID(int author_ID) {
        this.author_ID = author_ID;
    }

    public int getCategory_ID() {
        return category_ID;
    }

    public void setCategory_ID(int category_ID) {
        this.category_ID = category_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Book(int id, String code, int author_ID, int category_ID, String name, int numberOfPages, int quantity) {
        this.id = id;
        this.code = code;
        this.author_ID = author_ID;
        this.category_ID = category_ID;
        this.name = name;
        this.numberOfPages = numberOfPages;
        this.quantity = quantity;
    }

    public Book() {
    }

    public void display() {
        System.out.printf("%5d %10s %-25s %-15s %-20s %-20s %5d %5d%n",
                id,
                code,
                AuthorDB.findByID(author_ID).getFirstName(),
                AuthorDB.findByID(author_ID).getLastName(),
                CategoryDB.findByID(category_ID).getName(),
                name,
                numberOfPages,
                quantity);
    }

    public void input() {
        Scanner sc = new Scanner(System.in);

        this.id = BookDB.getAutoId();
        this.code = "Book-"+(100+id);

        System.out.print("Nhap author code");
        String codeAuthor;
        do{
            codeAuthor = sc.nextLine();
            if(AuthorDB.findByCode(codeAuthor) == -1) 
                System.out.println("Tac gia khong ton tai, vui long nhap lai");
        }while(AuthorDB.findByCode(codeAuthor) == -1);
        author_ID = AuthorDB.getAuthors().get(AuthorDB.findByCode(codeAuthor)).getId();
        System.out.print("Nhap category code: ");
        String codeCategory;
        do{
            codeCategory = sc.nextLine();
            if(CategoryDB.findByCode(codeCategory) == -1) 
                System.out.println("Danh muc khong ton tai, vui long nhap lai");
        }while(CategoryDB.findByCode(codeCategory) == -1);
        category_ID = CategoryDB.getCategories().get(CategoryDB.findByCode(codeCategory)).getId();
        
        System.out.print("Nhap ten sach: ");
        this.name = sc.nextLine();

        System.out.print("Nhap so trang: ");
        this.numberOfPages = Integer.parseInt(sc.nextLine());

        System.out.print("Nhap so luong: ");
        this.quantity = Integer.parseInt(sc.nextLine());
    }

    public void edit() {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n\t\tSUA THONG TIN SACH");

        System.out.print("Nhap ten moi: ");
        String newName = sc.nextLine();
        if (newName != null && !newName.isBlank()) {
            this.name = newName;
        }

        System.out.print("Nhap so trang moi: ");
        String strPages = sc.nextLine();
        if (!strPages.isBlank()) {
            this.numberOfPages = Integer.parseInt(strPages);
        }

        System.out.print("Nhap so luong moi: ");
        String strQuantity = sc.nextLine();
        if (!strQuantity.isBlank()) {
            this.quantity = Integer.parseInt(strQuantity);
        }

        System.out.print("Nhap author_ID moi: ");
        String strAuthor = sc.nextLine();
        if (!strAuthor.isBlank()) {
            this.author_ID = Integer.parseInt(strAuthor);
        }

        System.out.print("Nhap category_ID moi: ");
        String strCategory = sc.nextLine();
        if (!strCategory.isBlank()) {
            this.category_ID = Integer.parseInt(strCategory);
        }

        System.out.println("\tSua thong tin sach thanh cong!");
    }

}
