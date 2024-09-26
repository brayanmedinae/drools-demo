package sti.globals.product;

import lombok.Getter;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

import java.util.HashSet;
import java.util.Set;

@Getter
public class ProductUnit implements RuleUnitData {

    private final DataStore<Product> products;
    private final Set<String> controlSet = new HashSet<>();

    public ProductUnit() {
        this(DataSource.createStore());
    }

    public ProductUnit(DataStore<Product> products) {
        this.products = products;
    }
}
