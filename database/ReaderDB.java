package vn.devpro.management.database;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import vn.devpro.management.model.Book;
import vn.devpro.management.model.Reader;

public class ReaderDB {
    private static int autoId = 1;

    private static List<Reader> readers = new ArrayList<>() {
        {
            add(new Reader(autoId++, "Reader-101", "Nguyen", "Van An", LocalDate.of(2000, 5, 12), "Male", true));
            add(new Reader(autoId++, "Reader-102", "Tran", "Thi Binh", LocalDate.of(1999, 8, 20), "Female", false));
            add(new Reader(autoId++, "Reader-103", "Le", "Van Cong", LocalDate.of(2001, 1, 15), "Male", true));
            add(new Reader(autoId++, "Reader-104", "Pham", "Thi Dat", LocalDate.of(1998, 11, 30), "Female", false));
        }
    };

    public static List<Reader> getReaders() {
        return readers;
    }

    public static void setReaders(List<Reader> readers) {
        ReaderDB.readers = readers;
    }

    public static int getAutoId() {
        return autoId;
    }

    public static void setAutoId(int autoId) {
        ReaderDB.autoId = autoId;
    }

    public static Reader findByID(int id) {
        for (Reader r : readers) {
            if (r.getId() == id) {
                return r;
            }
        }
        return null;
    }
   public static int findByCode(String code) {
        for (int i = 0; i < readers.size(); i++) {
            if (readers.get(i).getCode().trim().equalsIgnoreCase(code.trim())) {
                return i;
            }
        }
        return -1;
    }
}
