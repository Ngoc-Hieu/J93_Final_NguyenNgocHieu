package vn.devpro.management;

import java.util.Scanner;

import vn.devpro.management.controller.AuthorController;
import vn.devpro.management.controller.BookController;
import vn.devpro.management.controller.CategoryController;
import vn.devpro.management.controller.ReaderController;
import vn.devpro.management.dto.BorrowController;

public class LibraryManagement {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n\t\tCHUONG TRINH QUAN LY THU VIEN");
            System.out.println("Chon mot chuc nang:");
            System.out.println("\t1. Cap nhat thong tin he thong");
            System.out.println("\t2. Quan ly giao dich muon sach");
            System.out.println("\t3. Thong ke don hang va doanh thu");
            System.out.println("\t0. Thoat");

            System.out.print("Lua chon cua ban: ");
            int choose = Integer.parseInt(sc.nextLine());

            switch (choose) {
                case 1 -> systemUpdateMenu();
                case 2 -> BorrowController.menu();
                case 3 -> statisticsMenu();
                case 0 -> {
                    System.out.println("Da thoat chuong trinh.");
                    return;
                }
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
    }

    // Menu cập nhật thông tin hệ thống
    private static void systemUpdateMenu() {
        while (true) {
            System.out.println("\n\t\tCAP NHAT THONG TIN HE THONG");
            System.out.println("Chon mot chuc nang:");
            System.out.println("\t1. Quan ly danh muc");
            System.out.println("\t2. Quan ly tac gia");
            System.out.println("\t3. Quan ly sach");
            System.out.println("\t4. Quan ly doc gia");
            System.out.println("\t0. Quay lai");

            System.out.print("Lua chon cua ban: ");
            int choose = Integer.parseInt(sc.nextLine());

            switch (choose) {
                case 1 -> CategoryController.Menu();
                case 2 -> AuthorController.Menu();
                case 3 -> BookController.Menu();
                case 4 -> ReaderController.Menu();
                case 0 -> { return; }
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
    }

    // Menu thống kê đơn hàng và doanh thu
    private static void statisticsMenu() {
        while (true) {
            System.out.println("\n\t\tTHONG KE DON HANG VA DOANH THU");
            System.out.println("Chon mot chuc nang thong ke:");
            System.out.println("\t1. Thong ke sach theo chuong loai");
            System.out.println("\t2. Thong ke sach theo tac gia");
            System.out.println("\t3. Thong ke cac sach dang duoc muon");
            System.out.println("\t4. Thong ke cac sach con lai co the muon");
            System.out.println("\t5. Thong ke sach da muon theo tung doc gia");
            System.out.println("\t0. Quay lai");

            System.out.print("Lua chon cua ban: ");
            int choose = Integer.parseInt(sc.nextLine());

            switch (choose) {
                case 1 -> BorrowController.statsByCategory();
                case 2 -> BorrowController.statsByAuthor();
                case 3 -> BorrowController.statsBorrowedBooks();
                case 4 -> BorrowController.statsAvailableBooks();
                case 5 -> BorrowController.statsByReader();
                case 0 -> { return; }
                default -> System.out.println("Lua chon khong hop le!");
            }
        }
    }
}
