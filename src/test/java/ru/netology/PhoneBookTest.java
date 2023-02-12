package ru.netology;

import org.junit.jupiter.api.*;

public class PhoneBookTest  {

    private PhoneBook phoneBook;

    @BeforeAll
    static void setup() {
        System.out.println("PhoneBook tests started");
    }

    @BeforeEach
    void init() {
        phoneBook = new PhoneBook();
        System.out.println("PhoneBook test started");
    }

    @AfterEach
    void completed() {
        System.out.println("PhoneBook test completed");
    }

    @AfterAll
    static void completedAll() {
        System.out.println("PhoneBook tests completed");
    }

    @Test
    void testAdd_shouldReturnNumberOfContactsInPhoneBook() {
        //given:
        String name = "John";
        String phoneNumber = "192-93-92";
        String name2 = "Dave";
        String phoneNumber2 = "204-84-90";
        int expected = 2;
        //when:
        phoneBook.add(name2, phoneNumber2);
        int actual = phoneBook.add(name2, phoneNumber2);
        //then:
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testAddExistingName_shouldNotAdd() {
        //given:
        String name = "John";
        String phoneNumber = "192-93-92";
        String name2 = "John";
        String phoneNumber2 = "180-33-28";
        int numberOfContacts;
        int expected = 1;
        //when:
        phoneBook.add(name, phoneNumber);
        int actual = phoneBook.add(name2, phoneNumber2);
        //then:
        Assertions.assertEquals(expected, actual);
    }
}
