package pl.freicher.gui;

import pl.freicher.asset.Job;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Freicher
 * @version 1.0
 */
public class TwoMachineTest {
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void twoMachineCalculateTest() {
        List<Integer> firstValuesList = Arrays.asList(4, 4, 8, 6, 2);
        List<Integer> secondValuesList = Arrays.asList(5, 1, 4, 8, 3);

        String result = twoMachineCalculate(firstValuesList, secondValuesList);
        assertEquals("Z5 Z1 Z4 Z3 Z2 ", result);
    }

    private String twoMachineCalculate(List<Integer> firstValuesList, List<Integer> secondValuesList) {
        List<Job> firstList = new ArrayList<>();
        List<Job> secondList = new ArrayList<>();

        for (int i = 0; i < firstValuesList.size(); i++) {

            int firstMachineValue = firstValuesList.get(i);
            int secondMachineValue = secondValuesList.get(i);

            if (firstMachineValue <= secondMachineValue) {
                firstList.add(new Job("Z" + (i + 1), firstMachineValue));
            } else if (firstMachineValue > secondMachineValue) {
                secondList.add(new Job("Z" + (i + 1), secondMachineValue));
            }
        }

        Collections.sort(firstList);
        Collections.sort(secondList, Collections.reverseOrder());
        firstList.addAll(secondList);

        StringBuilder sb = new StringBuilder();
        for (Job t : firstList) {
            sb.append(t.getName() + " ");
        }

        return sb.toString();
    }

}