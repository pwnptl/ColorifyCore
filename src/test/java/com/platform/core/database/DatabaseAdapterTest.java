package com.platform.core.database;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseAdapterTest {
    @Test
    public void testGetAll() {
        DatabaseAdapter yourClass = new DatabaseAdapter();  // Assuming YourClass is the class containing the getAll method

        // Call the method without assertions
        String result = yourClass.getAll();

        // Simply print the result without making assertions
        System.out.println("Result: " + result);
    }
}