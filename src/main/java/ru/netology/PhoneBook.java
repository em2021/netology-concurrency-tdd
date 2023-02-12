package ru.netology;

import java.util.HashMap;
import java.util.Map;

public class PhoneBook {

    private final Map<String, String> phoneBook = new HashMap<>();

    public int add(String name, String phoneNumber) {
        if (phoneBook.containsKey(name)) {
            return phoneBook.size();
        }
        phoneBook.put(name, phoneNumber);
        return phoneBook.size();
    }

}