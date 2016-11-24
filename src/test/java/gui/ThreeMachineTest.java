package gui;

import asset.Job;
import org.junit.After;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * @author Freicher
 * @version 1.0
 */
public class ThreeMachineTest {
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void threeMachineCalculateTest() {
        List<Integer> firstValuesList = Arrays.asList(3, 4, 3, 2, 5);
        List<Integer> secondValuesList = Arrays.asList(2, 2, 1, 1, 2);
        List<Integer> threeValuesList = Arrays.asList(4, 2, 5, 4, 3);

        String result = threeMachineCalculate(firstValuesList, secondValuesList, threeValuesList);
        assertEquals("Z4 Z3 Z1 Z5 Z2 ", result);
    }

    private String threeMachineCalculate(List<Integer> firstValues, List<Integer> secondValues, List<Integer> threeValues) {
        List<Integer> firstValuesList = new ArrayList<>();
        List<Integer> secondValuesList = new ArrayList<>();

        List<Job> firstList = new ArrayList<>();
        List<Job> secondList = new ArrayList<>();

        for (int i = 0; i < firstValues.size(); i++) {

            int firstMachineValue = firstValues.get(i);
            int secondMachineValue = secondValues.get(i);
            int thirdMachineValue = threeValues.get(i);

            firstValuesList.add(firstMachineValue + secondMachineValue);
            secondValuesList.add(secondMachineValue + thirdMachineValue);
        }

        for (int i = 0; i < firstValuesList.size(); i++) {
            if (firstValuesList.get(i) <= secondValuesList.get(i)) {
                firstList.add(new Job("Z" + (i + 1), firstValuesList.get(i)));
            } else if (firstValuesList.get(i) > secondValuesList.get(i)) {
                secondList.add(new Job("Z" + (i + 1), secondValuesList.get(i)));
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