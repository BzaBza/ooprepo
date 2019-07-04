package checkout.offer.reward;

import checkout.Check;
import checkout.offer.Offer;
import checkout.offer.condition.Condition;

import java.time.LocalDate;

public class RewardOffer extends Offer {
    public RewardOffer(LocalDate expiration){
        super(expiration);
    }

//    public Offer getOffer(Condition condition) {
//        switch (condition) {
//            case TOTAL_COST:
//                break;
//            case BY_COST:
//                break;
//            case BY_TRADE:
//                break;
//        }
//    }

    @Override
    protected void setOffer(Check check) {

    }

    @Override
    public boolean isValid(Check check) {
        return true;
    }
}
