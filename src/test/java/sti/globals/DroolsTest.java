package sti.globals;

import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sti.globals.product.Product;
import sti.globals.product.ProductUnit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DroolsTest {

    static final Logger LOGGER = LoggerFactory.getLogger(DroolsTest.class);

    @Test
    void discountTest() {
        long start = System.currentTimeMillis();
        ProductUnit productUnit = new ProductUnit();

        LOGGER.info("Creating RuleUnit");
        try (RuleUnitInstance<ProductUnit> instance = RuleUnitProvider.get().createRuleUnitInstance(productUnit)) {
            LOGGER.info("Insert data");
            productUnit.getProducts().add(new Product(1, "Standard", 100));
            productUnit.getProducts().add(new Product(2, "Premium", 200));
            productUnit.getProducts().add(new Product(3, "Normal", 50));

            LOGGER.info("Run query. Rules are also fired");
            List<Product> queryResult = instance.executeQuery("FindAllProducts").toList("$p");
            queryResult.forEach(product -> LOGGER.info(product.toString()));
            assertEquals(3, queryResult.size());
        }
        LOGGER.info("Time taken: {} ms", System.currentTimeMillis() - start);
    }

}
