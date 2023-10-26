package synowiec.webshop;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.Scanner;


@Component
@Profile("Start")
public class ShopStart {

    private final ProductService productService;
    private final Scanner scanner = new Scanner(System.in);

    public ShopStart(ProductService productService) {
        this.productService = productService;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void start(){
        productService.showProducts();
        productService.sumPrice();
        String option = "";
        do{
            productService.addProduct();
            productService.showProducts();
            productService.sumPrice();
            System.out.println("Czy chcesz dodać kolejny produkt (T/N)?");
            option = scanner.nextLine();
        }while(option.equals("T"));
    }
}
