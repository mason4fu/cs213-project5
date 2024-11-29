package com.example.pizzeria;

/**
 * Represents Chicago style pizzas
 *
 * @author YU FU, JOHNATHAN CHAN
 */
public class ChicagoPizza implements PizzaFactory{

    /**
     * Creates a deluxe chicago pizza
     *
     * @return pizza of the deluxe type and a deep dish crust, which makes it chicago style
     */
    @Override
    public Pizza createDeluxe() {
        Pizza deluxe = new Deluxe();
        deluxe.setCrust(Crust.DEEPDISH);
        return deluxe;
    }

    /**
     * Creates a meatzza chicago pizza
     *
     * @return pizza of the meatzza type and a stuffed crust, which makes it chicago style
     */
    @Override
    public Pizza createMeatzza() {
        Pizza meatzza = new Meatzza();
        meatzza.setCrust(Crust.STUFFED);
        return meatzza;
    }

    /**
     * Creates a BBQ chicken chicago pizza
     *
     * @return pizza of the BBQ chicken type and a pan crust, which makes it chicago style
     */
    @Override
    public Pizza createBBQChicken() {
        Pizza BBQChicken = new BBQChicken();
        BBQChicken.setCrust(Crust.PAN);
        return BBQChicken;
    }

    /**
     * Creates a custom chicago pizza
     *
     * @return custom chicago pizza with a pan crust, which makes it chicago style
     */
    @Override
    public Pizza createBuildYourOwn() {
        Pizza custom = new BuildYourOwn();
        custom.setCrust(Crust.PAN);
        return custom;
    }
}
