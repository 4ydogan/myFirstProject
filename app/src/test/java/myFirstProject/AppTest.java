/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package myFirstProject;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

class AppTest {
    @Test void howManyPositiveElementsIsTrue() {
        App classUnderTest = new App();

        ArrayList<Integer> myList = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            myList.add(i);
        }

        assertTrue(App.howManyPositiveElements(myList, 99, Integer.class) == 0);
    }
}
