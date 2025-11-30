package com.example.uiprototype.data;

import com.example.uiprototype.model.Locker;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LockerRepository {
    private static LockerRepository INSTANCE;
    private final List<Locker> lockers = new ArrayList<>();

    private LockerRepository() {
        // Demo seed data
        lockers.add(new Locker(101, "Small",  "Level 1 – Aisle 1", true));
        lockers.add(new Locker(102, "Small",  "Level 1 – Aisle 1", false));
        lockers.add(new Locker(201, "Medium", "Level 2 – Aisle 3", true));
        lockers.add(new Locker(202, "Medium", "Level 2 – Aisle 4", true));
        lockers.add(new Locker(301, "Large",  "Basement – West",  false));
        lockers.add(new Locker(302, "Large",  "Basement – West",  true));
    }

    public static synchronized LockerRepository getInstance() {
        if (INSTANCE == null) INSTANCE = new LockerRepository();
        return INSTANCE;
    }

    public List<Locker> getAll() {
        return Collections.unmodifiableList(lockers);
    }

    public List<Locker> getAvailable() {
        List<Locker> available = new ArrayList<>();
        for (Locker l : lockers) {
            if (l.isAvailable()) {
                available.add(l);
            }
        }
        return available;
    }


    public List<Locker> getMyLockers(String email) {
        List<Locker> mine = new ArrayList<>();
        for (Locker l : lockers) {
            if (email.equals(l.getRentedBy())) {
                mine.add(l);
            }
        }
        return mine;
    }

    public Locker findLocker(int id){
        for(Locker l : lockers) {
            if (l.getId() == id) return l;
        }
        return null;
    }

    public boolean rentLocker(int lockerId, String email) {
        for (Locker l : lockers) {
            if (l.getId() == lockerId && l.isAvailable()) {
                l.setAvailable(false);
                l.setRentedBy(email);
                return true;
            }
        }
        return false;
    }


    public boolean returnLocker(int lockerId) {
        for (Locker l : lockers) {
            if (l.getId() == lockerId && !l.isAvailable()) {
                l.setAvailable(true);
                l.setRentedBy(null);
                return true;
            }
        }
        return false;
    }
}