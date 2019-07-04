package checkout.offer.condition;

import checkout.Product;

public class NameCondition implements Condition {
    public NameCondition(String name) {
    }

    @Override
    public boolean isSuitable(Product product) {
        return true;
    }
}
