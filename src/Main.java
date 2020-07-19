import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        double startTime = System.nanoTime();

        // read the donations file
        HashMap<String, Double> donations = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Donations.csv"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String donor_id = values[2];
                Double amount = values[4].isEmpty() ? 0.0 : Double.parseDouble(values[4]);
                donations.put(donor_id, amount + donations.getOrDefault(donor_id, 0.0));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double parsingDonationsFile = System.nanoTime();
        // read the donors file.
        HashMap<String, List> donors = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader("Donors.csv"))) {
            String line = br.readLine();
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                String state = values[1];
                String id = values[0];
                if (!donors.containsKey(state)) donors.put(state, new ArrayList<String>());
                List donors_ids = donors.get(state);
                donors_ids.add(id);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        double parsingDonorsFile = System.nanoTime();

        StringBuilder ans = new StringBuilder();
        // do the join
        donors.keySet().forEach(state -> {
            List donors_ids = donors.get(state);
            Double sum_amount = 0.0;
            for (Object id : donors_ids) {
                sum_amount += donations.getOrDefault(id, 0.0);
            }
            ans.append(state + "," + sum_amount + "\n");
        });


        double joinFiles = System.nanoTime();
        double totalTime = System.nanoTime() - startTime;

        joinFiles -= parsingDonorsFile;
        parsingDonorsFile -= parsingDonationsFile;
        parsingDonationsFile -= startTime;

        System.out.println("result shown in seconds");
        System.out.println("totalTime: " + (totalTime / 1_000_000_000.0));
        System.out.println("joinFiles: " + (joinFiles / 1_000_000_000.0));
        System.out.println("parsingDonationsFile: " + (parsingDonationsFile / 1_000_000_000.0));
        System.out.println("parsingDonorsFile: " + (parsingDonorsFile / 1_000_000_000.0));
        System.out.println("--------- results ---------");
        System.out.println(ans.toString());


    }


}
