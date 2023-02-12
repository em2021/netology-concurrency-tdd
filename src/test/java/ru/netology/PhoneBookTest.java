package ru.netology;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.*;
import java.util.stream.Stream;

public class PhoneBookTest {

    private PhoneBook phoneBook;
    private final PrintStream standardOut = System.out;

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
        phoneBook.add(name, phoneNumber);
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

    @ParameterizedTest
    @MethodSource("sourceForFindByNumber")
    void testFindByNumberExistingNumber_shouldReturn(String numberForSearch, String expected) {
        //given:
        String name = "Tom";
        String phoneNumber = "192-93-92";
        phoneBook.add(name, phoneNumber);
        String name2 = "Fred";
        String phoneNumber2 = "180-33-28";
        phoneBook.add(name2, phoneNumber2);
        //when:
        String actual = phoneBook.findByNumber(numberForSearch);
        //then:
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFindByNonExistingNumber_shouldReturnNull() {
        //given:
        String name = "Paul";
        String phoneNumber = "192-93-92";
        phoneBook.add(name, phoneNumber);
        String nonExistingNumber = "122-00-11";
        String expected = null;
        //when:
        String actual = phoneBook.findByNumber(nonExistingNumber);
        //then:
        Assertions.assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("sourceForFindByName")
    void testFindByNameExistingName_shouldReturn(String nameForSearch, String expected) {
        //given:
        String name = "George";
        String phoneNumber = "141-99-23";
        phoneBook.add(name, phoneNumber);
        String name2 = "Maria";
        String phoneNumber2 = "180-11-00";
        phoneBook.add(name2, phoneNumber2);
        //when:
        String actual = phoneBook.findByName(nameForSearch);
        //then:
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testFindByNonExistingName_shouldReturnNull() {
        //given:
        String name = "Nick";
        String phoneNumber = "732-92-08";
        phoneBook.add(name, phoneNumber);
        String nonExistingName = "Alice";
        String expected = null;
        //when:
        String actual = phoneBook.findByName(nonExistingName);
        //then:
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPrintAllNames_shouldPrint() {
        //given:
        String name = "Zoe";
        String phoneNumber = "140-09-01";
        phoneBook.add(name, phoneNumber);
        String name2 = "Alex";
        String phoneNumber2 = "308-19-94";
        phoneBook.add(name2, phoneNumber2);
        String name3 = "Peter";
        String phoneNumber3 = "930-21-10";
        phoneBook.add(name3, phoneNumber3);
        String expected = "Alex\nPeter\nZoe";
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        //when:
        phoneBook.printAllNames();
        System.setOut(standardOut);
        //then:
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    void testPrintAllNamesEmptyPhoneBook_shouldPrintMessage() {
        //given:
        String expected = "Phonebook is empty";
        final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStreamCaptor));
        //when:
        phoneBook.printAllNames();
        System.setOut(standardOut);
        //then:
        Assertions.assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    private static Stream<Arguments> sourceForFindByNumber() {
        return Stream.of(Arguments.of("192-93-92", "Tom"),
                Arguments.of("180-33-28", "Fred"));
    }

    private static Stream<Arguments> sourceForFindByName() {
        return Stream.of(Arguments.of("George", "141-99-23"),
                Arguments.of("Maria", "180-11-00"));
    }
}
