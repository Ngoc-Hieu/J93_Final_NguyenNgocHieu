package vn.devpro.management.database;

import java.util.ArrayList;
import java.util.List;
import vn.devpro.management.model.Author;

public class AuthorDB {
    private static int autoId = 1;

    // Danh sách tác giả khởi tạo sẵn
    private static List<Author> authors = new ArrayList<>() {
        {
            add(new Author(autoId++, "Author-101", "Nguyen", "Ngoc Hieu"));
            add(new Author(autoId++, "Author-102", "Tran", "Van Nam"));
            add(new Author(autoId++, "Author-103", "Le", "Thi Hoa"));
            add(new Author(autoId++, "Author-104", "Pham", "Quang Minh"));
        }
    };
    

    public static List<Author> getAuthors() {
        return authors;
    }

    public static void setAuthors(List<Author> authors) {
        AuthorDB.authors = authors;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        AuthorDB.autoId = autoId;
    }


    public static Author findByID(int id) {
        for (Author a : authors) {
            if (a.getId() == id) {
                return a;
            }
        }
        return null;
    }
    public static int findByCode(String code) {
        for (int i = 0; i < authors.size(); i++) {
            if (authors.get(i).getCode().trim().equalsIgnoreCase(code.trim())) {
                return i;
            }
        }
        return -1;
    }
}
