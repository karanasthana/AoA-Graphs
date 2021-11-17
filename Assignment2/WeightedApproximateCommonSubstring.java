import java.io.IOException;
import java.util.*;
import java.io.FileWriter;

class WeightedApproximateCommonSubstring {
    
    public static void main(String ... args) {
        HashMap<Character, Integer> weightedAlphabets = getWeightedAlphabets();

        int maxWeight = 21912;
        int minWeight = 128;

        if (!runTestCases()) {
            return;
        }
        for (int i=0; i<10000; i++) {
            doRandomTesting(weightedAlphabets, maxWeight, minWeight);
        }
    }

    public static HashMap<Character, Integer> getRandomWeightedAlphabets() {
        HashMap<Character, Integer> weightedAlphabets = new HashMap<>();
        
        for (int i=0; i<26; i++) {
            weightedAlphabets.put((char)('a'+i), (int)(Math.random()*26));
        }

        return weightedAlphabets;
    }

    public static HashMap<Character, Integer> getWeightedAlphabets() {
        HashMap<Character, Integer> weightedAlphabets = new HashMap<>();

        weightedAlphabets.put('E',21912);
        weightedAlphabets.put('T',16587);
        weightedAlphabets.put('A',14810);
        weightedAlphabets.put('O',14003);
        weightedAlphabets.put('I',13318);
        weightedAlphabets.put('N',12666);
        weightedAlphabets.put('S',11450);
        weightedAlphabets.put('R',10977);
        weightedAlphabets.put('H',10795);
        weightedAlphabets.put('D',7874);
        weightedAlphabets.put('L',7253);
        weightedAlphabets.put('U',5246);
        weightedAlphabets.put('C',4943);
        weightedAlphabets.put('M',4761);
        weightedAlphabets.put('F',4200);
        weightedAlphabets.put('Y',3853);
        weightedAlphabets.put('W',3819);
        weightedAlphabets.put('G',3693);
        weightedAlphabets.put('P',3316);
        weightedAlphabets.put('B',2715);
        weightedAlphabets.put('V',2019);
        weightedAlphabets.put('K',1257);
        weightedAlphabets.put('X',315);
        weightedAlphabets.put('Q',205);
        weightedAlphabets.put('J',188);
        weightedAlphabets.put('Z',128);

        return weightedAlphabets;
    }

    public static void doRandomTesting(HashMap<Character, Integer> weightedAlphabets , int maxWeight, int minWeight) {
        int penalty = (minWeight) + (int)((maxWeight - minWeight) * (Math.random()));
        int s1Length = ((int)(Math.random() * 1000));
        int s2Length = (int)(s1Length/4);

        String s1 = generateRandomString(s1Length);
        String s2 = generateRandomString(s2Length);

        long startTime = System.nanoTime();
        findBestSubstr(s1, s2, penalty, weightedAlphabets);
        long elapsedTime = System.nanoTime() - startTime;


        writeToFile(s1Length, s2Length, elapsedTime, "100%");
        return;
    }

    public static String generateRandomString(int length) {
        int left = 65; // 'a'
        int right = 90; // 'z'
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int randomCharValueInt = left + (int)(random.nextFloat() * (right - left + 1));
            buffer.append((char) randomCharValueInt);
        }
        return buffer.toString();
    }

    public static String findBestSubstr(String s, String subs, int penalty, HashMap<Character, Integer> weightedAlphabets) {
        int sLength = s.length();
        int subsLength = subs.length();

        int result[][] = new int[sLength+1][subsLength+1];
        int max_till_now = 0;
        int max_i = 0, max_j = 0;

        for(int i=1; i<=sLength; i++) {
            char a = s.charAt(i-1);
            for(int j=1; j<=subsLength; j++) {
                char a2 = subs.charAt(j-1);

                if (a == a2) {
                    result[i][j] = result[i-1][j-1] + weightedAlphabets.get(a);
                } else {
                    result[i][j] = Math.max(0, (result[i-1][j-1] - penalty));
                }
                if (result[i][j] > max_till_now) {
                    max_till_now = result[i][j];
                    max_i = i;
                    max_j = j;
                }
            }
        }

        int score = max_till_now;
        String str = "";
        while (score > 0) {
            str = "" + subs.charAt(max_j-1) + str;
            max_i = max_i - 1;
            max_j = max_j - 1;

            score = result[max_i][max_j];
        }

        return str;
    }

    public static void writeToFile(int s1Length, int s2Length, long elapsedTime, String type) {
    	try {
    		FileWriter pw = new FileWriter("/Users/karanasthana/Documents/UFL/AOA/Assigments/Assignment2/data.csv", true);
            long totalStringLength = s1Length;
    		pw.append(totalStringLength + "," + elapsedTime + "," + type);
    		pw.append("\n");
    		pw.flush();
    		pw.close();    		
    	} catch (IOException e) {
    		System.out.println("Exception occurred e: " + e.getMessage());
    		e.printStackTrace();
    	}
    }

    public static boolean runTestCases() {
        String s = "ABCDELMN";
        String subs = "CDXLYN";
        int penalty = (int)(100 + (0 * (2600 - 100) / 10)); // 100
        String expectedOutput = "CDXLYN";

        System.out.println("String s1 - " + s + ". s2 - " + subs);

        HashMap<Character, Integer> weightedAlphabets = new HashMap<>();

        weightedAlphabets.put('A', 100);
        weightedAlphabets.put('B', 200);
        weightedAlphabets.put('C', 300);
        weightedAlphabets.put('D', 400);
        weightedAlphabets.put('E', 500);
        weightedAlphabets.put('F', 600);
        weightedAlphabets.put('G', 700);
        weightedAlphabets.put('H', 800);
        weightedAlphabets.put('I', 900);
        weightedAlphabets.put('J', 1000);
        weightedAlphabets.put('K', 1100);
        weightedAlphabets.put('L', 1200);
        weightedAlphabets.put('M', 1300);
        weightedAlphabets.put('N', 1400);
        weightedAlphabets.put('O', 1500);
        weightedAlphabets.put('P', 1600);
        weightedAlphabets.put('Q', 1700);
        weightedAlphabets.put('R', 1800);
        weightedAlphabets.put('S', 1900);
        weightedAlphabets.put('T', 2000);
        weightedAlphabets.put('U', 2100);
        weightedAlphabets.put('V', 2200);
        weightedAlphabets.put('W', 2300);
        weightedAlphabets.put('X', 2400);
        weightedAlphabets.put('Y', 2500);
        weightedAlphabets.put('Z', 2600);

        String output = findBestSubstr(s, subs, penalty, weightedAlphabets);
        System.out.println("-----Test Case 0 ----- with penalty as " + penalty);
        if (!output.equals(expectedOutput)) {
            System.out.println("Test Case failed for " + s + " and " + subs);
            return false;
        }
        System.out.println("Test Case 0 Successfully Passed! with best substring as " + output);
        System.out.println();

        penalty = (int)(100 + (1 * (2600 - 100) / 10)); // 350
        expectedOutput = "CDXLYN";
        output = findBestSubstr(s, subs, penalty, weightedAlphabets);
        System.out.println("-----Test Case 1 ----- with penalty as " + penalty);
        if (!output.equals(expectedOutput)) {
            System.out.println("Test Case failed for " + s + " and " + subs);
            return false;
        }
        System.out.println("Test Case 1 Successfully Passed! with best substring as " + output);
        System.out.println();

        penalty = (int)(100 + (2 * (2600 - 100) / 10)); // 600
        expectedOutput = "CDXLYN";
        output = findBestSubstr(s, subs, penalty, weightedAlphabets);
        System.out.println("-----Test Case 2 ----- with penalty as " + penalty);
        if (!output.equals(expectedOutput)) {
            System.out.println("Test Case failed for " + s + " and " + subs);
            return false;
        }
        System.out.println("Test Case 2 Successfully Passed! with best substring as " + output);
        System.out.println();

        penalty = (int)(100 + (3 * (2600 - 100) / 10)); // 850
        expectedOutput = "LYN";
        output = findBestSubstr(s, subs, penalty, weightedAlphabets);
        System.out.println("-----Test Case 3 ----- with penalty as " + penalty);
        if (!output.equals(expectedOutput)) {
            System.out.println("Test Case failed for " + s + " and " + subs);
            return false;
        }
        System.out.println("Test Case 3 Successfully Passed! with best substring as " + output);
        System.out.println();

        penalty = (int)(100 + (4 * (2600 - 100) / 10)); // 1100
        expectedOutput = "LYN";
        output = findBestSubstr(s, subs, penalty, weightedAlphabets);
        System.out.println("-----Test Case 4 ----- with penalty as " + penalty);
        if (!output.equals(expectedOutput)) {
            System.out.println("Test Case failed for " + s + " and " + subs);
            return false;
        }
        System.out.println("Test Case 4 Successfully Passed! with best substring as " + output);
        System.out.println();

        penalty = (int)(100 + (5 * (2600 - 100) / 10)); // 1350
        expectedOutput = "N";
        output = findBestSubstr(s, subs, penalty, weightedAlphabets);
        System.out.println("-----Test Case 5 ----- with penalty as " + penalty);
        if (!output.equals(expectedOutput)) {
            System.out.println("Test Case failed for " + s + " and " + subs);
            return false;
        }
        System.out.println("Test Case 5 Successfully Passed! with best substring as " + output);
        System.out.println();

        penalty = (int)(100 + (6 * (2600 - 100) / 10)); // 1600
        expectedOutput = "N";
        output = findBestSubstr(s, subs, penalty, weightedAlphabets);
        System.out.println("-----Test Case 6 ----- with penalty as " + penalty);
        if (!output.equals(expectedOutput)) {
            System.out.println("Test Case failed for " + s + " and " + subs);
            return false;
        }
        System.out.println("Test Case 6 Successfully Passed! with best substring as " + output);
        System.out.println();

        penalty = (int)(100 + (7 * (2600 - 100) / 10)); // 1850
        expectedOutput = "N";
        output = findBestSubstr(s, subs, penalty, weightedAlphabets);
        System.out.println("-----Test Case 7 ----- with penalty as " + penalty);
        if (!output.equals(expectedOutput)) {
            System.out.println("Test Case failed for " + s + " and " + subs);
            return false;
        }
        System.out.println("Test Case 7 Successfully Passed! with best substring as " + output);
        System.out.println();

        penalty = (int)(100 + (8 * (2600 - 100) / 10)); // 2100
        expectedOutput = "N";
        output = findBestSubstr(s, subs, penalty, weightedAlphabets);
        System.out.println("-----Test Case 8 ----- with penalty as " + penalty);
        if (!output.equals(expectedOutput)) {
            System.out.println("Test Case failed for " + s + " and " + subs);
            return false;
        }
        System.out.println("Test Case 8 Successfully Passed! with best substring as " + output);
        System.out.println();

        penalty = (int)(100 + (9 * (2600 - 100) / 10)); // 2350
        expectedOutput = "N";
        output = findBestSubstr(s, subs, penalty, weightedAlphabets);
        System.out.println("-----Test Case 9 ----- with penalty as " + penalty);
        if (!output.equals(expectedOutput)) {
            System.out.println("Test Case failed for " + s + " and " + subs);
            return false;
        }
        System.out.println("Test Case 9 Successfully Passed! with best substring as " + output);
        System.out.println();

        return true;
    }
}