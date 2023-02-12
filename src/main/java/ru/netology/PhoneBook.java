package ru.netology;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

    public String findByName(String name) {
        if (phoneBook.containsKey(name)) {
            return phoneBook.get(name);
        }
        return null;
    }

    public void printAllNames() {
        if (phoneBook.size() < 1) {
            System.out.println("Phonebook is empty");
        }
        Set<String> ascendingNames = new TreeSet<>(phoneBook.keySet());
        ascendingNames.forEach(System.out::println);
    }
}