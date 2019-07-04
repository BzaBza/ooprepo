import checkout.*;
import checkout.offer.Offer;
import checkout.offer.condition.NameCondition;
import checkout.offer.condition.TrademarkCondition;
import checkout.offer.discount.DiscountOffer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class CheckoutServiceTest {

    private Product milk_7;
    private CheckoutService checkoutService;
    private Product bred_3;
    private LocalDate today;
    private LocalDate specificDateBeforeToday;
    private LocalDate specificDateAfterToday;
    private Product productWithDiscount;

    @BeforeEach
    void setUp() {
        checkoutService = new CheckoutService();
        checkoutService.openCheck();

        milk_7 = new Product(7, "Milk", Category.MILK, "borjomi");
        bred_3 = new Product(3, "Bred", Category.BRED,"lacalut");
        today = LocalDate.now();
        specificDateBeforeToday = LocalDate.of(2017, Month.NOVEMBER, 30);
        specificDateAfterToday = today.plusYears(100);
    }

    @Test
    void closeCheck__withOneProduct() {
        checkoutService.addProduct(milk_7);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(7));
    }

    @Test
    void closeCheck__withTwoProducts() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(10));
    }

    @Test
    void addProduct__whenCheckIsClosed__opensNewCheck() {
        checkoutService.addProduct(milk_7);
        Check milkCheck = checkoutService.closeCheck();
        assertThat(milkCheck.getTotalCost(), is(7));

        checkoutService.addProduct(bred_3);
        Check bredCheck = checkoutService.closeCheck();
        assertThat(bredCheck.getTotalCost(), is(3));
    }

    @Test
    void closeCheck__calcTotalPoints() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);
        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalPoints(), is(10));
    }
//
//    @Test
//    void useOffer__addOfferPoints() {
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(bred_3);
//
//        checkoutService.useOffer(new AnyGoodsOffer(6, 2, today));
//        Check check = checkoutService.closeCheck();
//
//        assertThat(check.getTotalPoints(), is(12));
//    }
//
//    @Test
//    void useOffer__whenCostLessThanRequired__doNothing() {
//        checkoutService.addProduct(bred_3);
//
//        checkoutService.useOffer(new AnyGoodsOffer(6, 2, today));
//        Check check = checkoutService.closeCheck();
//
//        assertThat(check.getTotalPoints(), is(3));
//    }
//
//    @Test
//    void useOffer__factorByCategory() {
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(bred_3);
//
//        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, today));
//        Check check = checkoutService.closeCheck();
//
//        assertThat(check.getTotalPoints(), is(31));
//    }
//
//    @Test
//    void useOffer__beforeCloseCheck() {
//        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, today));
//        checkoutService.useOffer(new AnyGoodsOffer(9, 2, today));
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(bred_3);
//
//        Check check = checkoutService.closeCheck();
//
//        assertThat(check.getTotalPoints(), is(33));
//    }
//
//    @Test
//    void useOffer__beforeCloseCheck__withTwoProducts() {
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(milk_7);
//        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, today));
//        checkoutService.useOffer(new AnyGoodsOffer(9, 2, today));
//        checkoutService.addProduct(bred_3);
//
//        Check check = checkoutService.closeCheck();
//
//        assertThat(check.getTotalPoints(), is(33));
//    }
//
//    @Test
//    void offerExpired() {
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(milk_7);
//        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, specificDateBeforeToday));
//        checkoutService.useOffer(new AnyGoodsOffer(9, 2, specificDateBeforeToday));
//
//        checkoutService.addProduct(bred_3);
//
//        Check check = checkoutService.closeCheck();
//
//        assertThat(check.getTotalPoints(), is(17));
//    }
//
//    @Test
//    void offer__date__today() {
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(milk_7);
//        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, today));
//        checkoutService.useOffer(new AnyGoodsOffer(9, 2, today));
//
//        checkoutService.addProduct(bred_3);
//
//        Check check = checkoutService.closeCheck();
//
//        assertThat(check.getTotalPoints(), is(33));
//    }
//
//    @Test
//    void offer__date__isNot__Expired() {
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(milk_7);
//        checkoutService.useOffer(new FactorByCategoryOffer(Category.MILK, 2, specificDateAfterToday));
//        checkoutService.useOffer(new AnyGoodsOffer(9, 2, specificDateAfterToday));
//
//        checkoutService.addProduct(bred_3);
//
//        Check check = checkoutService.closeCheck();
//
//        assertThat(check.getTotalPoints(), is(33));
//    }
//    @Test
//    void addPoints__by__ProductName() {
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(milk_7);
//
//        checkoutService.addProduct(bred_3);
//        checkoutService.useOffer(new PointsEditor("Milk", specificDateAfterToday));
//        Check check = checkoutService.closeCheck();
//
//        assertThat(check.getTotalPoints(), is(31));
//    }
//    @Test
//    void addPoints__by__trademark() {
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(milk_7);
//
//        checkoutService.addProduct(bred_3);
//        checkoutService.useOffer(new PointsEditor("KvasTaras", specificDateAfterToday));
//        Check check = checkoutService.closeCheck();
//
//        assertThat(check.getTotalPoints(), is(31));
//    }
//
//    @Test
//    void discountOffer__with__correct__productName() {
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(milk_7);
//        checkoutService.useOffer(new DiscountOffer("Milk",50, specificDateAfterToday));
//
//        Check check = checkoutService.closeCheck();
//
//        assertThat(check.getTotalCost(), is(7));
//    }
//
//    @Test
//    void discountOffer__incorrect__productName() {
//        checkoutService.addProduct(milk_7);
//        checkoutService.addProduct(milk_7);
//        checkoutService.useOffer(new DiscountOffer("Bred",50, specificDateAfterToday));
//
//        Check check = checkoutService.closeCheck();
//
//        assertThat(check.getTotalCost(), is(14));
//    }


    @Test
    void discountOffer__incorrect__productName() {
        checkoutService.addProduct(milk_7);
        checkoutService.addProduct(bred_3);

        Offer z = new DiscountOffer(specificDateAfterToday, new NameCondition("Milk"), 50);
//        Offer f = new DiscountOffer(specificDateAfterToday, new TrademarkCondition("xbox"), 100);

        checkoutService.useOffer(z);
//        checkoutService.useOffer(f);

        Check check = checkoutService.closeCheck();

        assertThat(check.getTotalCost(), is(7));
    }
}
