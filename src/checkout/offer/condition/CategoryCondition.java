package checkout.offer.condition;

import checkout.Product;

public class CategoryCondition implements Condition {
    @Override
    public boolean isSuitable(Product product) {
        return false;
    }
}
