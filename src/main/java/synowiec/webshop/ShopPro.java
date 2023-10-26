package synowiec.webshop;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Scanner;


@Component
@Profile("Pro")
public class ShopPro {

    private final Scanner scanner = new Scanner(System.in);
    private final ProductService productService;

    public ShopPro(ProductService productService) {
        this.productService = productService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void pro(){
        int option;
        productService.showProducts();
        productService.sumPrice();

        do{
            System.out.println("Wybierz opcje z menu:");
            System.out.println("1. Dodaj produkt.");
            System.out.println("2. Dodaj Vat do cen z koszyka. ");
            System.out.println("3. Dodaj Rabat do cen z koszyka. ");
            System.out.println("4. Zakończ. ");
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
                case 3:
                    productService.addDiscount();
                    break;
                default:
                    break;
            }
        }while (option != 4);
    }




}
