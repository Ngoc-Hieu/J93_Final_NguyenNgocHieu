package vn.devpro.management.database;

import java.util.ArrayList;
import java.util.List;

import vn.devpro.management.model.Category;

public class CategoryDB {
    private static int autoId = 1;

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        CategoryDB.autoId = autoId;
    }

    private static List<Category> categories = new ArrayList<>() {
        {
            add(new Category(autoId++, "Category-101", "Thoi trang"));
            add(new Category(autoId++, "Category-102", "Trinh tham"));
            add(new Category(autoId++, "Category-103", "Khoa hoc"));
            add(new Category(autoId++, "Category-104", "Giao trinh"));
        }
    };

    public static List<Category> getCategories() {
        return categories;
    }

    public static void setCategories(List<Category> categories) {
        CategoryDB.categories = categories;
    } 
     public static Category findByID(int id){
        for(Category x : categories){
            if(x.getId() == id){
                return x;
            }
        }
        return null;
    }
    public static int findByCode(String code) {
        for (int i = 0; i < categories.size(); i++) {
            if (categories.get(i).getCode().trim().equalsIgnoreCase(code.trim())) {
                return i;
            }
        }
        return -1;
    }
}
