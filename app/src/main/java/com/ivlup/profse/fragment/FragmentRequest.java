package com.ivlup.profse.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ivlup.profse.MainActivity;
import com.ivlup.profse.R;

import java.util.Arrays;
import java.util.List;

public class FragmentRequest extends Fragment {

    private List <String> shipping;
    private TextView requestSecondTxt;
    private LinearLayout goneThird;
    private LinearLayout goneFourth;

    private EditText first;
    private EditText second;
    private EditText third;
    private EditText fourth;
    private EditText fiveth;

    private String type;
    private String urgency;
    private String edition;
    private String color;
    private String phone;

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle(MainActivity.chosenClient);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.request_card, container, false);


    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
       shipping = Arrays.asList(getResources().getStringArray(R.array.shipping));

        requestSecondTxt = (TextView) view.findViewById(R.id.request_second);

        goneThird = (LinearLayout) view.findViewById(R.id.gone_third_request);
        goneFourth = (LinearLayout) view.findViewById(R.id.gone_fourth_request);

        first   = (EditText) view.findViewById(R.id.request_type);
        second  = (EditText) view.findViewById(R.id.request_urgent);
        third   = (EditText) view.findViewById(R.id.request_color);
        fourth  = (EditText) view.findViewById(R.id.request_edition);
        fiveth  = (EditText) view.findViewById(R.id.request_phone);

        first.setText(MainActivity.chosenClient);

        for (int i = 0; i < shipping.size(); i++) {
            String s = shipping.get(i);
            if (MainActivity.chosenClient.equals(s)) {
                setAsShipping();
                break;
            }
            else if (i == shipping.size()-1) {
                setAsNotShipping();
            }
            Button sendRequest = (Button) view.findViewById(R.id.send_request);


            sendRequest.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                     type    = String.valueOf(first.getText());
                     urgency = String.valueOf(second.getText());
                     edition = " ";
                     color   = " ";

                    if (goneThird.getVisibility() != View.GONE && goneFourth.getVisibility() != View.GONE) {
                        edition = String.valueOf(third.getText());
                        color   = String.valueOf(fourth.getText());
                    }

                     phone = String.valueOf(fiveth.getText());



                    if (          type.length() == 0
                            || urgency.length() == 0
                            || edition.length() == 0
                            ||   color.length() == 0
                            ||   phone.length() == 0) {
                        Toast.makeText(getContext(), "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        sendEmail();
                    }
                }
            });
        }

    }

    public void setAsNotShipping() {

    }

    public void setAsShipping() {
        goneThird.setVisibility(View.GONE);
        goneFourth.setVisibility(View.GONE);
    }

    public void sendEmail() {
        Toast.makeText(getContext(), "Пожалуйства, в предложенном варианте выберите e-mail и затем нажмите \"отправить\" ",Toast.LENGTH_LONG).show();
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"info_profse@mail.ru"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Profse заявка: " + type);
        i.putExtra(Intent.EXTRA_TEXT   ,
                "Тип заявки: "+type+"\n"
                        +"Срочность: "+urgency+"\n"
                        +"Контактный телефон: "+phone+"\n"
                        +edition+"\n"
                        +color);
        try {
            Toast.makeText(getContext(), "Нажмите \"отправить\"", Toast.LENGTH_LONG).show();
            getActivity().startActivity(Intent.createChooser(i, "Отправить заявку..."));

        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText( getActivity(), "Нет допустимого e-mail клиента", Toast.LENGTH_SHORT).show();
        }
    }


}
