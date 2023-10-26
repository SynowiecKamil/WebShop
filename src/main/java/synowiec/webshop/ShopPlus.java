package synowiec.webshop;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.Scanner;


@Component
@Profile("Plus")
public class ShopPlus {

    private final Scanner scanner = new Scanner(System.in);
    private final ProductService productService;

    public ShopPlus(ProductService productService) {
        this.productService = productService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void plus(){
        int option;
        productService.showProducts();
        productService.sumPrice();

        do{
            System.out.println("Wybierz opcje z menu:");
            System.out.println("1. Dodaj produkt.");
            System.out.println("2. Dodaj Vat do cen z koszyka. ");
            System.out.println("3. Zakończ. ");
            option = scanner.nextInt();
            scanner.nextLine();
            switch (option) {
                case 1:
                    productService.addProduct();
                    productService.showProducts();
                    productService.sumPrice();
                    break;
                case 2:
                    productService.addTax();
                    break;
                default:
                    break;
            }
        }while (option != 3);
    }
}
