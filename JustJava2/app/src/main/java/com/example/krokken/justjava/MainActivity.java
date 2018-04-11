package com.example.krokken.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;


/**
 * This app displays an order form to order coffee.
 */

public class MainActivity extends AppCompatActivity {


    //format for all the currency outputs
    DecimalFormat currencyFormat = new DecimalFormat("###,##0.00");

    //add in charges
    double wCreamCharge = .65;
    double cinnamonCharge = .25;
    double espressoCharge = 2.30;
    double dairyFreeCreamerCharge = 1.50;
    double liquidCourageCharge = 5;
    double addInsCharge = 0;

    //coffee related variables
    int numberOfCoffees = 0;
    double pricePerCup = 5;
    int coffeeType = -1;
    int numberOfLatte, numberOfMocha, numberOfFrappuccino, numberOfRegular, numberOfAmericano, numberOfCappuccino, numberOfIcedCoffee, numberOfMacchiato;

    //Toasts
    Toast needsToOrder;
    Toast tryingToOrder;

    //checkbox booleans
    boolean wCreamBox, cinnamonBox, espressoBox, dairyFreeCreamerBox, liquidCourageBox;

    String chargeT = "|  $";
    String wCreamChargeT = chargeT + currencyFormat.format(wCreamCharge);
    String cinnamonChargeT = chargeT + currencyFormat.format(cinnamonCharge);
    String espressoChargeT = chargeT + currencyFormat.format(espressoCharge);
    String dairyFreeCreamerChargeT = chargeT + currencyFormat.format(dairyFreeCreamerCharge);
    String liquidCourageT = chargeT + currencyFormat.format(liquidCourageCharge);
    String addIns = "";
    String orderText;
    String orderName = "";
    String addresses = "crybarczyk05@gmail.com";
    String orderSummary = "Order Summary for ";

    Button mochaButtonFocus, frappuccinoButtonFocus, regularCoffeeButtonFocus,
            americanoButtonFocus, cappuccinoButtonFocus, icedCoffeeButtonFocus,
            latteButtonFocus, macchiatoButtonFocus;

    //TextView inputs
    private EditText nameForOrder;

    @Override
    protected void onCreate(Bundle saveOrderText) {
        super.onCreate(saveOrderText);
        setContentView(R.layout.activity_main);

        nameForOrder = findViewById(R.id.name_input);
        latteButtonFocus = findViewById(R.id.latte_button);
        mochaButtonFocus = findViewById(R.id.mocha_button);
        frappuccinoButtonFocus = findViewById(R.id.frappuccino_button);
        regularCoffeeButtonFocus = findViewById(R.id.regular_coffee_button);
        americanoButtonFocus = findViewById(R.id.americano_button);
        cappuccinoButtonFocus = findViewById(R.id.cappuccino_button);
        icedCoffeeButtonFocus = findViewById(R.id.iced_coffee_button);
        macchiatoButtonFocus = findViewById(R.id.macchiato_button);

        coffeeCounter();
        focusSet();
        displayWhippedCreamCheckBox();
        displayCinnamonCheckBox();
        displayEspressoCheckBox();
        displayDairyFreeCreamerCheckBox();
        displayLiquidCourageBox();
        displayAddInCharges(addInsCalculate(addInsCharge));

    }

    public void increaseCoffee(View view) {
        addChargesCalculate();
        coffeeTypeIncreaseCalculate();
        displayAddInCharges(addInsCalculate(addInsCharge));
        displayBeverageCharge(pricePerCup * numberOfOrderedCoffees());
        displayQuantity(numberOfOrderedCoffees());
        displayMessage(coffeeCount());
        coffeeCounter();
    }


    public void decreaseCoffee(View view) {
        addChargesCalculate();
        coffeeTypeDecreaseCalculate();
        displayAddInCharges(addInsCalculate(addInsCharge));
        displayBeverageCharge(pricePerCup * numberOfOrderedCoffees());
        displayQuantity(numberOfOrderedCoffees());
        displayMessage(coffeeCount());
        coffeeCounter();
    }
// Methods to follow

    //This submits the entire order and displays their order text
    public void submitOrder(View view) {
        if ( numberOfOrderedCoffees() == 0) {
            needsToOrder.makeText(this, "You must choose at least one drink before submitting your order.", Toast.LENGTH_SHORT).show();
            return;
        }
        orderName = nameForOrder.getText().toString();
        setTextSize();
        addChargesCalculate();
        String orderSummaryText = createOrderSummary(priceCalculate(numberOfOrderedCoffees(), addInsCharge)) + createExtraSummary();

        Intent orderIntentEmail = new Intent(Intent.ACTION_SENDTO);
        orderIntentEmail.setData(Uri.parse("mailto:"));
        orderIntentEmail.putExtra(Intent.EXTRA_EMAIL, addresses);
        orderIntentEmail.putExtra(Intent.EXTRA_SUBJECT, orderSummary + orderName);
        orderIntentEmail.putExtra(Intent.EXTRA_TEXT, orderSummaryText);
        if (orderIntentEmail.resolveActivity(getPackageManager()) != null) {
            startActivity(orderIntentEmail);
        }
    }

    //Activated by whipped cream check box, updates topping charges
    public void whippedCreamCheckBox(View view) {
        CheckBox whippedCream = findViewById(R.id.whipped_cream_checkbox);
        wCreamBox = whippedCream.isChecked();
        addChargesCalculate();
        displayAddInCharges(addInsCalculate(addInsCharge));
    }

    //Activated by cinnamon check box, updates topping charges
    public void cinnamonCheckBox(View view) {
        CheckBox cinnamon = findViewById(R.id.cinnamon_checkbox);
        cinnamonBox = cinnamon.isChecked();
        addChargesCalculate();
        displayAddInCharges(addInsCalculate(addInsCharge));
    }

    //Activated by espresso shot check box, updates topping charges
    public void espressoShotCheckBox(View view) {
        CheckBox espressoShot = findViewById(R.id.espresso_shot_checkbox);
        espressoBox = espressoShot.isChecked();
        addChargesCalculate();
        displayAddInCharges(addInsCalculate(addInsCharge));
    }

    //Activated by dairy free creamer check box, updates topping charges
    public void dairyFreeCreamerCheckBox(View view) {
        CheckBox cinnamon = findViewById(R.id.dairy_free_creamer_checkbox);
        dairyFreeCreamerBox = cinnamon.isChecked();
        addChargesCalculate();
        displayAddInCharges(addInsCalculate(addInsCharge));
    }

    //Activated by liquid courage check box, updates topping charges
    public void liquidCourageCheckBox(View view) {
        CheckBox liquidCourage = findViewById(R.id.liquid_courage_checkbox);
        liquidCourageBox = liquidCourage.isChecked();
        addChargesCalculate();
        displayAddInCharges(addInsCalculate(addInsCharge));
    }


    //This displays a comment or question on the amount of coffee they ordered
    private void displayMessage(String orderText) {
        TextView quantityTextView = findViewById(R.id.order_text_view);
        quantityTextView.setText(orderText);
    }

    //This changes the size of the orderText once ordered
    private void setTextSize() {
        TextView quantityTextView = findViewById(R.id.order_text_view);
        quantityTextView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 14);
        orderText = "Thank you for your order!";
        orderText += "\nWe look forward to seeing you soon!";
        displayMessage(orderText);
    }

    public void latteButton(View view) {
        focusSet();
        latteButtonFocus.setFocusable(true);
        latteButtonFocus.setFocusableInTouchMode(true);
        latteButtonFocus.requestFocus();

        coffeeType = 0;

    }

    public void mochaButton(View view) {
        focusSet();
        mochaButtonFocus.setFocusable(true);
        mochaButtonFocus.setFocusableInTouchMode(true);
        mochaButtonFocus.requestFocus();

        coffeeType = 1;
    }

    public void frappuccinoButton(View view) {
        focusSet();
        frappuccinoButtonFocus.setFocusable(true);
        frappuccinoButtonFocus.setFocusableInTouchMode(true);
        frappuccinoButtonFocus.requestFocus();

        coffeeType = 2;

    }

    public void regularCoffeeButton(View view) {
        focusSet();
        regularCoffeeButtonFocus.setFocusable(true);
        regularCoffeeButtonFocus.setFocusableInTouchMode(true);
        regularCoffeeButtonFocus.requestFocus();

        coffeeType = 3;

    }

    public void americanoButton(View view) {
        focusSet();
        americanoButtonFocus.setFocusable(true);
        americanoButtonFocus.setFocusableInTouchMode(true);
        americanoButtonFocus.requestFocus();

        coffeeType = 4;

    }

    public void cappuccinoButton(View view) {
        focusSet();
        cappuccinoButtonFocus.setFocusable(true);
        cappuccinoButtonFocus.setFocusableInTouchMode(true);
        cappuccinoButtonFocus.requestFocus();

        coffeeType = 5;

    }

    public void icedCoffeeButton(View view) {
        focusSet();
        icedCoffeeButtonFocus.setFocusable(true);
        icedCoffeeButtonFocus.setFocusableInTouchMode(true);
        icedCoffeeButtonFocus.requestFocus();

        coffeeType = 6;

    }

    public void macchiatoButton(View view) {
        focusSet();
        macchiatoButtonFocus.setFocusable(true);
        macchiatoButtonFocus.setFocusableInTouchMode(true);
        macchiatoButtonFocus.requestFocus();

        coffeeType = 7;

    }

    public void focusSet() {
        frappuccinoButtonFocus.setFocusable(false);
        frappuccinoButtonFocus.setFocusableInTouchMode(false);
        mochaButtonFocus.setFocusable(false);
        mochaButtonFocus.setFocusableInTouchMode(false);
        latteButtonFocus.setFocusable(false);
        latteButtonFocus.setFocusableInTouchMode(false);
        regularCoffeeButtonFocus.setFocusable(false);
        regularCoffeeButtonFocus.setFocusableInTouchMode(false);
        americanoButtonFocus.setFocusable(false);
        americanoButtonFocus.setFocusableInTouchMode(false);
        cappuccinoButtonFocus.setFocusable(false);
        cappuccinoButtonFocus.setFocusableInTouchMode(false);
        icedCoffeeButtonFocus.setFocusable(false);
        icedCoffeeButtonFocus.setFocusableInTouchMode(false);
        macchiatoButtonFocus.setFocusable(false);
        macchiatoButtonFocus.setFocusableInTouchMode(false);
    }

    public void coffeeCounter() {
        mochaButtonFocus.setText("Mocha (" + numberOfMocha + ")");
        macchiatoButtonFocus.setText("Macchiato (" + numberOfMacchiato + ")");
        icedCoffeeButtonFocus.setText("Iced Coffee (" + numberOfIcedCoffee + ")");
        cappuccinoButtonFocus.setText("Cappuccino (" + numberOfCappuccino + ")");
        americanoButtonFocus.setText("Americano (" + numberOfAmericano + ")");
        regularCoffeeButtonFocus.setText("Regular (" + numberOfRegular + ")");
        frappuccinoButtonFocus.setText("Frapppuccino (" + numberOfFrappuccino + ")");
        latteButtonFocus.setText("Latte (" + numberOfLatte + ")");

    }

    //This displays the number of coffees being ordered
    private void displayQuantity(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        String numberText = "" + number;
        quantityTextView.setText(numberText);
    }

    //This displays the order summary at the bottom
//    private void displayPrice(String orderSummary) {
//        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view1);
//        orderSummaryTextView.setText(orderSummary);
//    }

    //This displays the toppings order summary at the bottom
//    private void displayExtras(String orderSummary) {
//        TextView orderSummaryTextView = findViewById(R.id.order_summary_text_view2);
//        orderSummaryTextView.setText(orderSummary);
//    }

    //This shows whether they wanted whipped cream or not
    private void displayWhippedCreamCheckBox() {
        TextView wCreamChargeText = findViewById(R.id.whipped_cream_charge);
        wCreamChargeText.setText(wCreamChargeT);
    }

    //This shows whether they wanted cinnamon or not
    private void displayCinnamonCheckBox() {
        TextView cinnamonChargeText = findViewById(R.id.cinnamon_charge);
        cinnamonChargeText.setText(cinnamonChargeT);
    }

    //This shows whether they wanted an espresso shot or not
    private void displayEspressoCheckBox() {
        TextView espressoChargeText = findViewById(R.id.espresso_shot_charge);
        espressoChargeText.setText(espressoChargeT);
    }

    //This shows whether they wanted dairy free creamer or not
    private void displayDairyFreeCreamerCheckBox() {
        TextView dairyFreeCreamerChargeText = findViewById(R.id.dairy_free_creamer_charge);
        dairyFreeCreamerChargeText.setText(dairyFreeCreamerChargeT);
    }

    //This shows whether they wanted liquid courage or not
    private void displayLiquidCourageBox() {
        TextView liquidCourageChargeText = findViewById(R.id.liquid_courage_charge);
        liquidCourageChargeText.setText(liquidCourageT);
    }

    private int numberOfOrderedCoffees() {
        int numberOfCof = 0;
        numberOfCof += numberOfMacchiato;
        numberOfCof += numberOfRegular;
        numberOfCof += numberOfLatte;
        numberOfCof += numberOfMocha;
        numberOfCof += numberOfIcedCoffee;
        numberOfCof += numberOfFrappuccino;
        numberOfCof += numberOfCappuccino;
        numberOfCof += numberOfAmericano;
        return numberOfCof;
    }

    //This calculates their order total
    private double priceCalculate(int numberOfCof, double addCharges) {
        double totalCoffeePrice;
        totalCoffeePrice = numberOfCof * pricePerCup;
        addCharges *= numberOfCof;
        totalCoffeePrice += addCharges;
        return totalCoffeePrice;
    }

    private void addChargesCalculate() {
        addInsCharge = 0;
        //This calculates whipped cream charges
        if (wCreamBox) {
            addInsCharge += wCreamCharge;
            addIns += "Whipped Cream\n";
        }
        //This calculates cinnamon charges
        if (cinnamonBox) {
            addInsCharge += cinnamonCharge;
            addIns += "Cinnamon\n";
        }
        //This calculates espresso shot charges
        if (espressoBox) {
            addInsCharge += espressoCharge;
            addIns += "Espresso Shot\n";
        }
        //This calculates dairy free creamer charges
        if (dairyFreeCreamerBox) {
            addInsCharge += dairyFreeCreamerCharge;
            addIns += "Dairy Free Creamer\n";
        }
        //This calculates liquid course charges
        if (liquidCourageBox) {
            addInsCharge += liquidCourageCharge;
            addIns += "Liquid Courage";
        }
    }

    //This calculates all of the toppings for a grand total charge of toppings
    public double addInsCalculate(double addCharges) {
        addCharges *= numberOfOrderedCoffees();

        return addCharges;
    }

    //This displays the total for the toppings charge
    private void displayAddInCharges(double addInsChargeText) {
        TextView addInsChargeT = findViewById(R.id.add_ins_charge);
        String currentChargeText = getString(R.string.current_charge) + currencyFormat.format(addInsChargeText);
        addInsChargeT.setText(currentChargeText);
    }

    //This displays the beverage charges
    private void displayBeverageCharge(double beverageTotalCharge) {
        TextView beverageChargeT = findViewById(R.id.beverage_charge);
        String beverageChargeText = "$" + currencyFormat.format(beverageTotalCharge);
        beverageChargeT.setText(beverageChargeText);
    }

    //This determines which coffee they're wanting to order
    private void coffeeTypeIncreaseCalculate() {
        if (coffeeType == 0) {
            numberOfLatte++;
            numberOfCoffees = numberOfLatte;
        } else if (coffeeType == 1) {
            numberOfMocha++;
            numberOfCoffees = numberOfMocha;
        } else if (coffeeType == 2) {
            numberOfFrappuccino++;
            numberOfCoffees = numberOfFrappuccino;
        } else if (coffeeType == 3) {
            numberOfRegular++;
            numberOfCoffees = numberOfRegular;
        } else if (coffeeType == 4) {
            numberOfAmericano++;
            numberOfCoffees = numberOfAmericano;
        } else if (coffeeType == 5) {
            numberOfCappuccino++;
            numberOfCoffees = numberOfCappuccino;
        } else if (coffeeType == 6) {
            numberOfIcedCoffee++;
            numberOfCoffees = numberOfIcedCoffee;
        } else if (coffeeType == 7) {
            numberOfMacchiato++;
            numberOfCoffees = numberOfMacchiato;
        } else if (coffeeType == -1) {
            tryingToOrder.makeText(this, "You must choose what type of drink you would like.", Toast.LENGTH_LONG).show();
        }
    }

    //This determines which coffee they're wanting to order
    private void coffeeTypeDecreaseCalculate() {
        if (coffeeType == 0) {
            numberOfLatte--;
            numberOfLatte=noLessThanZero(numberOfLatte);
        } else if (coffeeType == 1) {
            numberOfMocha--;
            numberOfMocha=noLessThanZero(numberOfMocha);
        } else if (coffeeType == 2) {
            numberOfFrappuccino--;
            numberOfFrappuccino=noLessThanZero(numberOfFrappuccino);
        } else if (coffeeType == 3) {
            numberOfRegular--;
            numberOfRegular=noLessThanZero(numberOfRegular);
        } else if (coffeeType == 4) {
            numberOfAmericano--;
            numberOfAmericano=noLessThanZero(numberOfAmericano);
        } else if (coffeeType == 5) {
            numberOfCappuccino--;
            numberOfCappuccino=noLessThanZero(numberOfCappuccino);
        } else if (coffeeType == 6) {
            numberOfIcedCoffee--;
            numberOfIcedCoffee=noLessThanZero(numberOfIcedCoffee);
        } else if (coffeeType == 7) {
            numberOfMacchiato--;
            numberOfMacchiato=noLessThanZero(numberOfMacchiato);
        }
        }

        private int noLessThanZero(int coffeeNumber){
            if (coffeeNumber <= 0) {
                coffeeNumber = 0;
            }
            return coffeeNumber;
        }


    //This tries to get Goldilocks her coffee fix
    private String coffeeCount() {
        if (numberOfOrderedCoffees() < 1) {
            orderText = getResources().getString(R.string.order_Text);
            return orderText;
        } else if (numberOfOrderedCoffees() <= 3) {
            orderText = "Are you sure you wouldn't like some more coffee?";
        } else if (numberOfOrderedCoffees() >= 6) {
            orderText = "That's a lot of coffee to enjoy!";
        } else {
            orderText = "That's a perfect amount of coffee";
        }
        return orderText;
    }

    private String createExtraSummary() {
        String extraSummary = "Extras for each:\n";

        if (addInsCharge == 0) {
            extraSummary += "None";
        } else {
            if (wCreamBox) {
                extraSummary += "Whipped Cream\n";
            }
            if (cinnamonBox) {
                extraSummary += "Cinnamon\n";
            }
            if (espressoBox) {
                extraSummary += "Espresso Shot\n";
            }
            if (dairyFreeCreamerBox) {
                extraSummary += "Dairy Free Creamer\n";
            }
            if (liquidCourageBox) {
                extraSummary += "Liquid Courage";
            }
        }
        return extraSummary;
    }

    //This calculates their entire order text into a string
    private String createOrderSummary(double price) {
        String orderSummary = "Name: " + orderName;

        if (numberOfLatte > 0) {
            orderSummary += "\nQuantity of Latte: " + numberOfLatte;
        }
        if (numberOfMocha > 0) {
            orderSummary += "\nQuantity of Mocha: " + numberOfMocha;

        }
        if (numberOfFrappuccino > 0) {
            orderSummary += "\nQuantity of Frappuccinos: " + numberOfFrappuccino;
        }
        if (numberOfAmericano > 0) {
            orderSummary += "\nQuantity of Americanos: " + numberOfAmericano;

        }
        if (numberOfCappuccino > 0) {
            orderSummary += "\nQuantity of Cappuccinos: " + numberOfCappuccino;

        }
        if (numberOfIcedCoffee > 0) {
            orderSummary += "\nQuantity of Iced Coffees: " + numberOfIcedCoffee;

        }
        if (numberOfMacchiato > 0) {
            orderSummary += "\nQuantity of Macchiatos: " + numberOfMacchiato;

        }
        if (numberOfRegular > 0) {
            orderSummary += "\nQuantity of Regular Coffee: " + numberOfRegular;

        }

        orderSummary += "\nPrice: $" + currencyFormat.format(price) + "\n";
        return orderSummary;
    }

}
