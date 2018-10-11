package com.ivlup.profse.tools;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.ivlup.profse.MainActivity;
import com.ivlup.profse.MyListFragment;
import com.ivlup.profse.R;

public class FragmentChoose extends Fragment {

    public void sendEmail(String chosenVar) {
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"info_profse@mail.ru"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Profse заявка: "+chosenVar);
        i.putExtra(Intent.EXTRA_TEXT   , "Напишите вашу заявку здесь: ");
        try {
            getActivity().startActivity(Intent.createChooser(i, "Отправить заявку..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText( getActivity(), "Нет допустимого e-mail клиента", Toast.LENGTH_SHORT).show();
        }
    }

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Выбрать вариант");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.choose_option, container, false);



    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        Button btsend = view.findViewById(R.id.send);
        btsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail(MainActivity.chosen);
            }
        });
        Button btchoose = view.findViewById(R.id.choose);
        btchoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (    MainActivity.chosenClient.equals("Издательства и типографии") ||
                        MainActivity.chosenClient.equals("Конференции, семинары, тренинги") ||
                        MainActivity.chosenClient.equals("Выставки") ||
                        MainActivity.chosenClient.equals("Спортивные мероприятия") ||
                        MainActivity.chosenClient.equals("Презенты для клиентов") ||
                        MainActivity.chosenClient.equals("От 30 до 100 руб") ||
                        MainActivity.chosenClient.equals("От 100 до 300 руб") ||
                        MainActivity.chosenClient.equals("От 300 до 500 руб") ||
                        MainActivity.chosenClient.equals("От 300 до 500 руб") ||
                        MainActivity.chosenClient.equals("От 500 руб") ||
                        MainActivity.chosenClient.equals("Вип-подарки") ||
                        MainActivity.chosenClient.equals("Имидж сотрудников") ||
                        MainActivity.chosenClient.equals("Подарки для детей") ||
                        MainActivity.chosenClient.equals("Ручная работа") ||
                        MainActivity.chosenClient.equals("Презентации")) {
                    MainActivity.fm = getFragmentManager();
                    MainActivity.ft = MainActivity.fm.beginTransaction();
                    MainActivity.ft.replace(R.id.fragment_container, new ClientListFragment());
                    MainActivity.ft.commit();
                    MainActivity.ft.addToBackStack(null);
                }
            }
        });

    }
}
