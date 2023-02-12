package ru.netology;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

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

    public String findByNumber(String phoneNumber) {
        if (phoneBook.containsValue(phoneNumber)) {
            BiMap<String, String> phoneBookBiMap = HashBiMap.create(phoneBook);
            return phoneBookBiMap.inverse().get(phoneNumber);
        }
        return null;
    }

}