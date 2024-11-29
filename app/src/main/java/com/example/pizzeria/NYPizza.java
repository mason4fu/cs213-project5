package com.example.pizzeria;

/**
 * Represents NY style pizzas
 *
 * @author YU FU, JOHNATHAN CHAN
 */
public class NYPizza implements PizzaFactory{

    /**
     * Creates a deluxe NY pizza
     *
     * @return pizza of the deluxe type and a Brooklyn crust, which makes it NY style
     */
    @Override
    public Pizza createDeluxe() {
        Pizza deluxe = new Deluxe();
        deluxe.setCrust(Crust.BROOKLYN);
        return deluxe;
    }


    /**
     * Creates a meatzza NY pizza
     *
     * @return pizza of the meatzza type and a hand tossed crust, which makes it NY style
     */
    @Override
    public Pizza createMeatzza() {
        Pizza meatzza = new Meatzza();
        meatzza.setCrust(Crust.HANDTOSSED);
        return meatzza;
    }


    /**
     * Creates a BBQ chicken NY pizza
     *
     * @return pizza of the BBQ chicken type and a thin crust, which makes it NY style
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza BBQChicken = new BBQChicken();
        BBQChicken.setCrust(Crust.THIN);
        return BBQChicken;
    }


    /**
     * Creates a custom NY pizza
     *
     * @return custom pizza with a hand tossed crust, which makes it NY style
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza custom = new BuildYourOwn();
        custom.setCrust(Crust.HANDTOSSED);
        return custom;
    }
}
