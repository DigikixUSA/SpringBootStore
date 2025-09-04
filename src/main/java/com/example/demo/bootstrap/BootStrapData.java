package com.example.demo.bootstrap;

import com.example.demo.domain.OutsourcedPart;
import com.example.demo.domain.Part;
import com.example.demo.domain.Product;
import com.example.demo.repositories.OutsourcedPartRepository;
import com.example.demo.repositories.PartRepository;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.service.PartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 *
 *
 *
 */
@Component
public class BootStrapData implements CommandLineRunner {


    private final PartRepository partRepository;
    private final ProductRepository productRepository;

    private final OutsourcedPartRepository outsourcedPartRepository;

    public BootStrapData(PartRepository partRepository, ProductRepository productRepository, OutsourcedPartRepository outsourcedPartRepository) {
        this.partRepository = partRepository;
        this.productRepository = productRepository;
        this.outsourcedPartRepository=outsourcedPartRepository;
    }

    @Override
    public void run(String... args) throws Exception {

//only used for testing
// partRepository.deleteAll();
        //check if starting from scratch, get the current quantity of ALL parts, only add if already 0
        List<Part> InStockParts=(List<Part>) partRepository.findAll();
        if (InStockParts.size()==0) {
            OutsourcedPart o = new OutsourcedPart();
            o.setCompanyName("Summit Racing");//
            o.setName("Ignition Coil");//Ignition Coil
            o.setPrice(99.99);//50.0
            o.setMax(100);
            o.setMin(5);//0
            o.setInv(50);//100
            o.setId(100L);
            outsourcedPartRepository.save(o);

            o.setCompanyName("Summit Racing");//
            o.setName("Spark Plug");//Spark Plug
            o.setPrice(99.99);//5.0
            o.setMax(100);//200
            o.setMin(5);//0
            o.setInv(50);
            o.setId(101L);
            outsourcedPartRepository.save(o);

            o.setCompanyName("ZZP");//
            o.setName("Brake Rotors");//Brake Rotors
            o.setPrice(99.99);//250.0
            o.setMax(100);//50
            o.setMin(5);//0
            o.setInv(50);//30
            o.setId(103L);
            outsourcedPartRepository.save(o);

            o.setCompanyName("Rock Auto");//
            o.setName("Brake Pads");//Brake Pads
            o.setPrice(99.99);//50.0
            o.setMax(100);
            o.setMin(5);//0
            o.setInv(50);
            o.setId(104L);
            outsourcedPartRepository.save(o);

            o.setCompanyName("ZZP");//
            o.setName("Exhaust Manifold");//Exhaust Manifold
            o.setPrice(99.99);//250.0
            o.setMax(100);//20
            o.setMin(5);//0
            o.setInv(50);//5
            o.setId(102L);
            outsourcedPartRepository.save(o);

            List<OutsourcedPart> outsourcedParts = (List<OutsourcedPart>) outsourcedPartRepository.findAll();
            for (OutsourcedPart part : outsourcedParts) {
                System.out.println(part.getName() + " " + part.getCompanyName()+ " min="+part.getMin()+" max="+part.getMax()+ " inv="+part.getInv()+" price="+part.getPrice());
            }
        }
//only used for testing
// productRepository.deleteAll();
        //check if starting from scratch
        List<Product> InStockProducts=(List<Product>) productRepository.findAll();
        if (InStockProducts.size()==0) {

            Product paint = new Product("Paint", 200.0, 10);//16.0, 15, Paint
            Product brakeFluid = new Product("Brake Fluid", 200.0, 15);//12.0, 10, Brake Fluid
            Product tuneUpKit = new Product("Tune-Up Kit", 400.0, 25);//160.0, 5, Tune-Up Kit
            Product brakeServiceKit = new Product("Brake Service Kit", 99.99, 35);//250.0, 10, Brake Service Kit
            Product appearanceKit = new Product("Appearance Kit", 200.0, 45);//75.0, 8, Appearance Kit
            productRepository.save(paint);
            productRepository.save(brakeFluid);
            productRepository.save(tuneUpKit);
            productRepository.save(brakeServiceKit);
            productRepository.save(appearanceKit);
        }

        System.out.println("Started in Bootstrap");
        System.out.println("Number of Products"+productRepository.count());
        System.out.println(productRepository.findAll());
        System.out.println("Number of Parts"+partRepository.count());
        System.out.println(partRepository.findAll());

    }
}
