import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Person {
    private String name;
    private double money;

    public Person(String name, double money){
        this.name = name;
        this.money = Math.round(money * 100.0) / 100.0;;
    }

    public String getName() {
        return name;
    }

    public double getMoney() {
        return money;
    }

    public void save() {
        try {
            File f = new File("src/StockExchanges.data");
            f.createNewFile();
            FileWriter fw = new FileWriter("src/StockExchanges.data");
            fw.write(name + "\n");
            fw.write("$" + money);
            fw.close();
        }
        catch (IOException e) {
            System.out.println("Unable to create file");
            e.printStackTrace();
        }
    }
}
