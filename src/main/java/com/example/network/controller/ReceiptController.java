package com.example.network.controller;

import com.example.network.McaApi;
import com.example.network.response.Product;
import com.example.network.service.ProductService;
import org.apache.commons.lang3.StringUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;



public class ReceiptController {

    public void start() {

        ProductService productService = McaApi.createService(ProductService.class);

        Call<List<Product>> callAsync = productService.getProducts();

        callAsync.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                List<Product> products = response.body();
                assert products != null;
                printReceipt(products);
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable throwable) {
                System.out.println(throwable.getMessage());
            }
        });
    }

    private void printReceipt(List<Product> products) {

        Map<Boolean, List<Product>> productsGrouped = products.stream()           //group products by domestic value and
                .collect(Collectors.groupingBy(Product::isDomestic,                // sort each group alphabetically
                        Collectors.mapping(Function.identity(),                    // output {false: [Banana], true: [Apple, Tomato]}
                                Collectors.collectingAndThen(Collectors.toList(),
                                        e -> e.stream().sorted(Comparator.comparing(Product::getName))
                                                .collect(Collectors.toList())))));


        int domesticCount = 0, importedCount = 0;
        double domesticCost = 0.0, importedCost = 0.0;
        System.out.println(". Domestic");
        for (Product p: productsGrouped.get(true)) {    //iterate over domestic products
            p.setDescription(StringUtils.abbreviate(p.getDescription(), 30));  //truncate desc if longer than 30 characters
            System.out.println(p);
            domesticCost += p.getPrice();
            domesticCount += 1;
        }
        System.out.println(". Imported");
        for (Product p: productsGrouped.get(false)) {
            p.setDescription(StringUtils.abbreviate(p.getDescription(), 30));
            System.out.println(p);
            importedCost += p.getPrice();
            importedCount += 1;
        }
        System.out.println("Domestic cost: $" + domesticCost);
        System.out.println("Imported cost: $" + importedCost);
        System.out.println("Domestic count: " + domesticCount);
        System.out.println("Imported count: " + importedCount);
    }

}