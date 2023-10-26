package synowiec.webshop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class ProductService {

    @Value("${page-info.tax}")
    private BigDecimal tax;

    @Value("${page-info.discount}")
    private BigDecimal discount;
    private final List<Product> productList;
    private BigDecimal sumNet;
    private BigDecimal sumGross;

    public ProductService() {
        BigDecimal min = new BigDecimal(50);
        BigDecimal max = new BigDecimal(300);
        Product product1 = new Product("Spodnie", generateRandomBigDecimalFromRange(min, max));
        Product product2 = new Product("Koszulka", generateRandomBigDecimalFromRange(min, max));
        Product product3 = new Product("Bluza", generateRandomBigDecimalFromRange(min, max));
        Product product4 = new Product("Buty", generateRandomBigDecimalFromRange(min, max));
        Product product5 = new Product("Czapka", generateRandomBigDecimalFromRange(min, max));
        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
        productList.add(product3);
        productList.add(product4);
        productList.add(product5);
    }

    public void addProduct(){
        Scanner scan = new Scanner(System.in);
        System.out.println("Dodawanie produktu. Wprowadź nazwę: ");
        String name = scan.nextLine();
        System.out.println("Wprowadź cenę: ");
        BigDecimal price = scan.nextBigDecimal();
        Product newProduct = new Product(name, price);
        productList.add(newProduct);
    }

    public void sumPrice(){
        sumNet = BigDecimal.valueOf(0.00);
        for(int i =0; i< productList.size(); i++){
            sumNet= sumNet.add(productList.get(i).getPrice());}
        System.out.println("Łączna wartość koszyka to: " + sumNet + " zł netto");
    }

    public void showProducts(){
        System.out.println("Zawartość koszyka:");
        for(int i =0; i< productList.size(); i++){
            System.out.println(productList.get(i).getName()+" - cena: " + productList.get(i).getPrice() + " zł");
        }
    }

    public void addTax(){
        sumGross = sumNet.multiply(tax).setScale(2, RoundingMode.CEILING);
        System.out.println("Wartość koszyka z VAT to: " + sumGross + " zł");
    }
    public void addDiscount(){
        sumNet = sumNet.multiply(discount).setScale(2, RoundingMode.HALF_UP);
        System.out.println("Cena po rabacie to: " + sumNet + " zł netto");
    }

    public BigDecimal generateRandomBigDecimalFromRange(BigDecimal min, BigDecimal max) {
        BigDecimal randomBigDecimal = min.add(new BigDecimal(Math.random()).multiply(max.subtract(min)));
        return randomBigDecimal.setScale(2,BigDecimal.ROUND_HALF_UP);
    }
}
