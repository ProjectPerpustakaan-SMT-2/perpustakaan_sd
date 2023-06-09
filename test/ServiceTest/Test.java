/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ServiceTest;

/**
 *
 * @author Hafidz Fadhillah
 */
public class Test {

    public static void main(String[] args) {
        String input = "123720230608";
        int dynamicPortionLength = getDynamicPortionLength(input);
        String dynamicPortion = input.substring(0, dynamicPortionLength);

        System.out.println(dynamicPortion); // Output: 12
    }

    private static int getDynamicPortionLength(String input) {
        int inputLength = input.length();
        int dynamicPortionLength = 1; // Default dynamic portion length

        // Adjust dynamic portion length based on input length
        if (inputLength >= 9) {
            dynamicPortionLength = inputLength - 8;
        }

        return dynamicPortionLength;
    }
}
