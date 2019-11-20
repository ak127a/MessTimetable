package com.akshatk.messtimetable;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Homepagefragment extends Fragment {

    FragmentStatePagerAdapter adapterViewPager;
    Calendar calendar= Calendar.getInstance(Locale.US);
    Calendar referenceCalendar = Calendar.getInstance(Locale.US);
    Date referenceDate;
    ViewPager vpPager;
    static String[][][] mealArray=new String[5][8][5];
    static int currentHour,currentWeekIndex,currentDayIndex,currentMealIndex,referenceWeekIndex;
    static String currentDay,currentWeek,currentMeal;
    static TextView itemsTextView,currentWeekTextView,currentMealHasTextView,dateAndDayTextView,currentMealTypeTextView;

    public static Homepagefragment newInstance() {
        Homepagefragment fragment = new Homepagefragment();
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.homepagefragment, container, false);
        vpPager = (ViewPager) view.findViewById(R.id.vpPager);
        adapterViewPager = new MyPagerAdapter(getChildFragmentManager());
        vpPager.setAdapter(adapterViewPager);

        referenceCalendar.set(Calendar.MILLISECOND, 0);
        referenceCalendar.set(Calendar.YEAR , 2019);
        referenceCalendar.set(Calendar.MONTH , 02);
        referenceCalendar.set(Calendar.DAY_OF_MONTH , 31);
        referenceDate = referenceCalendar.getTime();
        referenceWeekIndex = 2;

        currentMealHasTextView=(TextView)view.findViewById(R.id.displayItems);
        itemsTextView=(TextView)view.findViewById(R.id.displayItems);
        dateAndDayTextView = (TextView)view.findViewById(R.id.dayAndDate);
        currentWeekTextView= (TextView)view.findViewById(R.id.currentWeek);

        PagerTabStrip pagerTabStrip = (PagerTabStrip) view.findViewById(R.id.pager_header);
        dateAndDayTextView = (TextView)view.findViewById(R.id.dayAndDate);

        // Setting the tab font
        for (int i = 0; i < pagerTabStrip.getChildCount(); ++i) {
            View nextChild = pagerTabStrip.getChildAt(i);
            if (nextChild instanceof TextView) {
                TextView textViewToConvert = (TextView) nextChild;
                textViewToConvert.setTypeface(Typeface.createFromAsset(getContext().getAssets(),
                        "OpenSans-Regular.ttf"));
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE d MMMM");
        dateAndDayTextView.setText(sdf.format(calendar.getTime()));

        setMealArray();
        setCurrentItems();
        return view;
    }

    public void setMealArray()
    {
        mealArray[1][1][1]="Masala Dosa, Chutney, Tea, Coffee, Milk";
        mealArray[1][1][2]="Green Peas Pulav, Raita, Chapati, Rice, Rasam, Black Chana South curry";
        mealArray[1][1][3]="Noodles, Tea/Coffee";
        mealArray[1][1][4]="Rice, Rasam, Raita, Veg Biryani, Chicken Biryani, Burger, Paneer Butter Masala, Laccha Paratha, Fruit Salad, Cold Drink, Ice Cream";

        mealArray[1][2][1]="Idly-Vada, Sambhar, Chutney, Tea, Coffee, Milk";
        mealArray[1][2][2]="Rice, Soppu-Saru, Puliogare, Chapati, Aloo Bhindi, Butter milk, Papad";
        mealArray[1][2][3]="Samosa, Tea/Coffee";
        mealArray[1][2][4]="Rice, Rasam, Dal Tadka, Schezwan Fried Rice, Fulka, Gobhi Manchurian, Curd";

        mealArray[1][3][1]="Tomato Rice, Puri Sagu, Tea, Coffee, Milk";
        mealArray[1][3][2]="Rice, Rasam, Yellow Dal, Coconut Rice, Chapati, Veg Sabji, Papad";
        mealArray[1][3][3]="Bhel puri, Tea/Coffee";
        mealArray[1][3][4]="Rice, Sambhar, Bombay Dal, Green Salad, Fulka, Kadi Pakoda, Puliogare";

        mealArray[1][4][1]="Aloo Paratha, Curd, Pickle, Tea, Coffee, Milk";
        mealArray[1][4][2]="Rice, Green Sambhar, Veg pulao, Raita, Chapati, Chana Masala, Papad";
        mealArray[1][4][3]="Aalo Pakoda, Tea/Coffee";
        mealArray[1][4][4]="Rice, Rasam, Laccha Paratha, Egg curry, Paneer curry";

        mealArray[1][5][1]="Lemon Rice, Chutney, Veg Roll, Tea, Coffee, Milk";
        mealArray[1][5][2]="Rice, Dal, Rasam, Vangi Bhath, Chapati, Aloo capsicum, Papad";
        mealArray[1][5][3]="Mixture, Tea/Coffee";
        mealArray[1][5][4]="Rice, Sambhar, Chole Bhature, Sweet Lassi, Salad";

        mealArray[1][6][1]="Pav Bhaji, Rice Bhath, Chutney, Tea, Coffee, Milk";
        mealArray[1][6][2]="Rice, Sambhar, Lemon Rice, Chapati, Rajma Masala, Butter Milk";
        mealArray[1][6][3]="Pasta, Tea/Coffee";
        mealArray[1][6][4]="Rice,Dal Fry, Rasam, Fulka, Palak Paneer, Jeera Rice, Curd";


        mealArray[1][7][1]="Onion Dosa, Sagu, Chutney, Tea, Coffee, Milk";
        mealArray[1][7][2]="Rice, Sambhar, Pudhina Rice, Puri, Veg Kurma, Papad";
        mealArray[1][7][3]="Masala Vada, Tea/Coffee";
        mealArray[1][7][4]="Rice, Dal, Rasam, Aloo Paratha, Curd, Sweet, Banana";



        mealArray[2][1][1]="Idly-Vada, Sambhar, Chutney, Tea, Coffee, Milk";
        mealArray[2][1][2]="Aloo Parval, Fulka, Dum Jeera Rice, Ghiya Dal, Rice, Sambhar, Papad, Butter milk";
        mealArray[2][1][3]="Pakoda, Tea/Coffee";
        mealArray[2][1][4]="Chicken Curry, Kadhai Paneer, Chapati, Rice, Rasam";

        mealArray[2][2][1]="Puri Sagu, Tea, Coffee, Milk";
        mealArray[2][2][2]="Palak Rice, Chapati, Dal, Mix veg curry, Rice, Rasam";
        mealArray[2][2][3]="Semiya, Tea/Coffee";
        mealArray[2][2][4]="Fulka, Kofta curry, Veg pulao, Raita, Rice, Sambhar, Bombay Dal";

        mealArray[2][3][1]="Plain Dosa, Sambhar, Chutney, Tea, Coffee, Milk";
        mealArray[2][3][2]="Makai Nisha, Chapati, Dal Kolhapuri, Green Sambhar, Ghee rice, Butter milk";
        mealArray[2][3][3]="Kachori, Tea/Coffee";
        mealArray[2][3][4]="Fulka, Gobhi Manchurian, Veg Fried Rice, Rice, Rasam, Curd, Papad";

        mealArray[2][4][1]="Rava Idli, Chutney, Sagu, Tea, Coffee, Milk";
        mealArray[2][4][2]="Rajma Masala, Chapati, Veg Biryani, Raita, Rice, Rasam";
        mealArray[2][4][3]="Mixture. Tea/Coffee";
        mealArray[2][4][4]="Rice, Rasam, Fulka, Egg curry, Paneer curry";

        mealArray[2][5][1]="Pav Bhaji, Rice Bhath, Chutney, Tea, Coffee, Milk";
        mealArray[2][5][2]="Pudhina rice, Aloo gobhi Capsicum, Chapati, Rice, Sambhar, Dal, Butter Milk";
        mealArray[2][5][3]="Bread Jam, Tea/Coffee";
        mealArray[2][5][4]="Chole Bhature, Jeera rice, Rice, Rasam, Juice";

        mealArray[2][6][1]="Bread omelette, Tomato Rice, Chutney, Tea, Coffee, Milk";
        mealArray[2][6][2]="Bhindi Masala, Dal, Chapati, Methi Rice, Sambhar, Rice, Papad";
        mealArray[2][6][3]="Pani Puri, Tea/Coffee";
        mealArray[2][6][4]="Laccha Paratha, Kadi Pakoda, Lemon rice, Dal, Rice, Rasam, Papad";

        mealArray[2][7][1]="Idly-Vada, Sambhar, Chutney, Tea, Coffee, Milk";
        mealArray[2][7][2]="Bread Rice, Dal, Chapati, Green Sambhar, Veg Palya, Rice, Butter Milk";
        mealArray[2][7][3]="Rusk, Tea/Coffee";
        mealArray[2][7][4]="Palak Puri, Chana Masala, Jeera rice, Rasam, Rice, Curd, salad, Sweet";



        mealArray[3][1][1]="Masala Dosa, Chutney, Tea, Coffee, Milk";
        mealArray[3][1][2]="Rice, Green Dal, Rasam, Puliogare, Fulka, Rajma Masala, Butter Milk, Papad";
        mealArray[3][1][3]="Poha, Tea/Coffee";
        mealArray[3][1][4]="Veg Hyderabadi Biryani, Raita, Rice, Rasam, Chicken Kabab, Chole Bhature/Kerela Paratha, Pani Puri/Masala Vada, Chilly Panner, Fruit Salad, Cold Drink";

        mealArray[3][2][1]="Puri Sagu, Pudhina Rice, Tea, Coffee, Milk";
        mealArray[3][2][2]="Rice, Sambhar, Kashmiri Dum Rice, Chapati, Papad, Mushroom Green Peas";
        mealArray[3][2][3]="Bonda, Tea/Coffee";
        mealArray[3][2][4]="Butter Fulka, Veg Kadhai, Dal Tadka, Rice, Rasam, Methi Rice, Curd";

        mealArray[3][3][1]="Idly-Vada, Sambhar, Chutney, Tea, Coffee, Milk";
        mealArray[3][3][2]="Ghee Rice, Dal Fry, Chapati, Aloo Baingan, Rice, Green Sambhar, Butter Milk";
        mealArray[3][3][3]="Chips, Tea/Coffee";
        mealArray[3][3][4]="Dal Makhani, Plain Paratha, Soya Chilli, Veg Fried Rice, Rice, Rasam, Curd";

        mealArray[3][4][1]="Dal Palak Paratha, Sagu, Tea, Coffee, Milk";
        mealArray[3][4][2]="Mix Dal, Lemon Rice, Puri, Veg Kurma, Butter Milk, Rice, Rasam";
        mealArray[3][4][3]="Banana Bhajji, Tea/Coffee";
        mealArray[3][4][4]="Rice, Rasam, Laccha Paratha, Egg curry, Paneer curry";

        mealArray[3][5][1]="Chow Chow Bhath, lemon Rice, Tea, Coffee, Milk";
        mealArray[3][5][2]="Chana Masala, Chapati, Vangi Bhath, Sambhar, Rice, Butter Milk, Papad";
        mealArray[3][5][3]="Mixture, Tea/Coffee";
        mealArray[3][5][4]="Veg Hyderabadi Biryani, Raita, Plain Paratha, Palak Dal Sabji, Rice, Rasam, Papad";

        mealArray[3][6][1]="Chole Bhature, Tea, Coffee, Milk";
        mealArray[3][6][2]="Rajma Masala, Dum Ghee Rice, Chapati, Rice, Sambhar, Dal, Papad";
        mealArray[3][6][3]="Samosa, Tea/Coffee";
        mealArray[3][6][4]="Pudhina Rice, Fulka, Mattar Paneer, Curd, Rice, Rasam";

        mealArray[3][7][1]="Set Dosa, Sagu, Chutney, Tea, Coffee, Milk";
        mealArray[3][7][2]="Puliogare, Aloo Bhindi Dry, Fulka, Dal Mughlai, Rice, Sambhar, Papad";
        mealArray[3][7][3]="Biscuits, Tea/Coffee";
        mealArray[3][7][4]="Rice, Sambhar, Aloo Paratha, Curd, Dum Jeera Rice, Sweet";





        mealArray[4][1][1]="Idly-Vada, Sambhar, Chutney, Tea, Coffee, Milk";
        mealArray[4][1][2]="Chana Masala, Fulka, Rice, Green Sambhar, Ghee Rice, Butter Milk, Papad";
        mealArray[4][1][3]="Pasta, Tea/Coffee";
        mealArray[4][1][4]="Butter chicken, Shahi Paneer, Paratha, Dal Tadka, Salad";

        mealArray[4][2][1]="Puri sagu, Tea, Coffee, Milk";
        mealArray[4][2][2]="Kashmiri Dum Rice, Makai Nisha, Chapati, Dal Fry, Rice, Rasam";
        mealArray[4][2][3]="Bread Pakoda, Tea/Coffee";
        mealArray[4][2][4]="Chinese Pakoda, Fulka, Seasonal Fried Dry, Chana Dal, Rasam, Rice, Juice";

        mealArray[4][3][1]="Plain Dosa, Sambhar, Chutney, Tea, Coffee, Milk";
        mealArray[4][3][2]="Veg Pulao, Raita, Rajma Masala, Chapati, Sambhar, Rice, Butter milk";
        mealArray[4][3][3]="Kachori, Tea/Coffee";
        mealArray[4][3][4]="Rice, Fulka, Aloo Methi Dry, Bombay Dal, Curd Rice, Sambhar, Papad";

        mealArray[4][4][1]="Aloo Paratha, Curd, Tea, Coffee, Milk";
        mealArray[4][4][2]="Mix veg curry, Chapati, Tomato Rice, Dal, Sambhar, Butter milk";
        mealArray[4][4][3]="Semiya, Tea/Coffee";
        mealArray[4][4][4]="Rice, Rasam, Paratha, Egg curry, Paneer curry";

        mealArray[4][5][1]="Pav Bhaji, Puliogare, Tea, Coffee, Milk";
        mealArray[4][5][2]="Veg Palya, Fulka, Dal Kolahpuri, Bisibelabath, Green Sambhar, Rice, Butter milk";
        mealArray[4][5][3]="Mirchi Bhajji, Tea/Coffee";
        mealArray[4][5][4]="Rajma Masala, Plain Paratha, Kaju Pulao, Dal Tadka, Rice, Sambhar, Curd";

        mealArray[4][6][1]="Bread Omelette, Bread Jam, Rice Bhath, Chutney, Tea, Coffee, Milk";
        mealArray[4][6][2]="Chana Masala, Chapati, Palak Rice, Moong Dal, Sambhar, Rice, Butter milk";
        mealArray[4][6][3]="Masala Vada, Tea/Coffee";
        mealArray[4][6][4]="Veg Kurma, Puri, Curd, Jeera Rice, Rice, Rasam";

        mealArray[4][7][1]="Veg Roll, Lemon Rice, Tea, Coffee, Milk";
        mealArray[4][7][2]="Matar Paneer, Fulka, Veg Biryani, Raita, Rice, Rasam";
        mealArray[4][7][3]="Biscuits, Tea/Coffee";
        mealArray[4][7][4]="Babycorn Pulao, Raita, Greenpeas Masala, Fulka, Rice, Dal, Sambhar";
    }

    public void setCurrentItems()
    {

        Calendar currentCalendar = Calendar.getInstance(Locale.US);
        Date currentDate = currentCalendar.getTime();
        int daysBetween=(int)((currentDate.getTime()-referenceDate.getTime())/(3600*24*1000));
        currentWeekIndex = (3+referenceWeekIndex + daysBetween/7)%4 +1;

        currentHour=calendar.get(Calendar.HOUR_OF_DAY);
        if(currentHour>=0 && currentHour<=9) {
            currentMealIndex=1;
            currentMeal="Breakfast";
        }
        else if(currentHour>9 && currentHour<=14) {
            currentMealIndex=2;
            currentMeal="Lunch";
        }
        else if(currentHour>14 && currentHour<18){
            currentMealIndex=3;
            currentMeal="Snacks";
        }
        else{
            currentMealIndex=4;
            currentMeal="Dinner";
        }

        //currentMealTypeTextView.setText(currentMeal);

        switch (calendar.get(Calendar.DAY_OF_WEEK))
        {
            case 1 : currentDay="Sunday"; currentDayIndex=1;  break;
            case 2 : currentDay="Monday"; currentDayIndex=2; break;
            case 3 : currentDay="Tuesday"; currentDayIndex=3;break;
            case 4 : currentDay="Wednesday"; currentDayIndex=4; break;
            case 5 : currentDay="Thursday"; currentDayIndex=5; break;
            case 6 : currentDay="Friday"; currentDayIndex=6; break;
            case 7 : currentDay="Saturday"; currentDayIndex=7; break;
        }

        switch (currentWeekIndex)
        {
            case 1 : currentWeek="Week 1"; break;
            case 2 : currentWeek="Week 2"; break;
            case 3 : currentWeek="Week 3"; break;
            case 4 : currentWeek="Week 4"; break;
        }

        currentWeekTextView.setText(currentWeek);
        vpPager.setCurrentItem(currentMealIndex-1);

        //currentMealHasTextView.setText(mealArray[currentWeekIndex][currentDayIndex][currentMealIndex]);
    }

    public static class MyPagerAdapter extends FragmentStatePagerAdapter {
        private static int NUM_ITEMS = 4;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages.
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for a particular page.
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return TextFragment.newInstance(mealArray[currentWeekIndex][currentDayIndex][1]);
                case 1:
                    return TextFragment.newInstance(mealArray[currentWeekIndex][currentDayIndex][2]);
                case 2:
                    return TextFragment.newInstance(mealArray[currentWeekIndex][currentDayIndex][3]);
                case 3:
                    return TextFragment.newInstance(mealArray[currentWeekIndex][currentDayIndex][4]);
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Breakfast";
                case 1:
                    return "Lunch";
                case 2:
                    return "Snacks";
                case 3:
                    return "Dinner";
                default:
                    return null;
            }
        }

    }
}
