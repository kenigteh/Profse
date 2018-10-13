package com.ivlup.profse.fragment.useful;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.ivlup.profse.R;
import com.ivlup.profse.activity.MainActivity;
import com.ivlup.profse.contractor.FragmentListContractors;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyListFragment extends Fragment {

private Map<String, List<String>> hashMap;

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle("Список");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_listview, container, false);



    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);

        Bundle bundle = getArguments();

        hashMap = new HashMap<String, List<String>>(50);

        fillArrayList();


        final ListView listView = view.findViewById(R.id.listView);

        ArrayAdapter<String> adapter = new ArrayAdapter< >
                (view.getContext(),
                        android.R.layout.simple_list_item_1,
                        hashMap.get(MainActivity.chosen));
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.chosenClient = String.valueOf(listView.getItemAtPosition(position));

                MainActivity.fm = getFragmentManager();
                MainActivity.ft = MainActivity.fm.beginTransaction();
                MainActivity.ft.replace(R.id.fragment_container, new FragmentListContractors());
                MainActivity.ft.commit();
                MainActivity.ft.addToBackStack(null);
            }
        });
    }


    private void fillArrayList() {
        hashMap.put("AD_JUR_OTHER", Arrays.asList(getResources().getStringArray(R.array.ad_jur_other)));
        hashMap.put("AD_JUR_ATELIER", Arrays.asList(getResources().getStringArray(R.array.ad_jur_atelier)));
        hashMap.put("AD_JUR_OUT", Arrays.asList(getResources().getStringArray(R.array.ad_jur_out)));
        hashMap.put("AD_JUR_NEWS", Arrays.asList(getResources().getStringArray(R.array.ad_jur_news)));
        hashMap.put("Издательства и тиографии", Arrays.asList(getResources().getStringArray(R.array.ad_jur_typography)));

        hashMap.put("AUTO_OTHER", Arrays.asList(getResources().getStringArray(R.array.auto_other)));
        hashMap.put("BEAU_SALOONS", Arrays.asList(getResources().getStringArray(R.array.beauty_saloons)));
        hashMap.put("BEAU_SPA", Arrays.asList(getResources().getStringArray(R.array.beauty_spa)));
        hashMap.put("BEAU_TATTOO", Arrays.asList(getResources().getStringArray(R.array.beauty_tattoo)));
        hashMap.put("BEAU_SOLARIUM", Arrays.asList(getResources().getStringArray(R.array.beauty_solarium)));
        hashMap.put("BEAU_MASSAGE", Arrays.asList(getResources().getStringArray(R.array.beauty_massage)));
        hashMap.put("BEAU_COSMETOLOGY", Arrays.asList(getResources().getStringArray(R.array.beauty_cosmetology)));
        hashMap.put("BEAU_NAILS", Arrays.asList(getResources().getStringArray(R.array.beauty_nails)));
        hashMap.put("BEAU_OTHER", Arrays.asList(getResources().getStringArray(R.array.beauty_other)));
        hashMap.put("CELEB_CHILD", Arrays.asList(getResources().getStringArray(R.array.celebration_child)));
        hashMap.put("CELEB_EVENTS", Arrays.asList(getResources().getStringArray(R.array.celebration_events)));
        hashMap.put("CELEB_FLOWERS", Arrays.asList(getResources().getStringArray(R.array.celebration_flowers)));
        hashMap.put("EDU_OLD", Arrays.asList(getResources().getStringArray(R.array.education_old)));
        hashMap.put("EDU_CHILD", Arrays.asList(getResources().getStringArray(R.array.education_child)));
        hashMap.put("HEALTH_STOMATOLOGY", Arrays.asList(getResources().getStringArray(R.array.health_stomatology)));
        hashMap.put("HEALTH_OTHER", Arrays.asList(getResources().getStringArray(R.array.health_other)));
        hashMap.put("ALL_OTHER_ATELIER", Arrays.asList(getResources().getStringArray(R.array.other_atelier)));
        hashMap.put("ALL_OTHER_CHEMISTRY", Arrays.asList(getResources().getStringArray(R.array.other_chemistry)));
        hashMap.put("ALL_OTHER_OUT", Arrays.asList(getResources().getStringArray(R.array.other_out)));
        hashMap.put("ALL_OTHER_DESIGN", Arrays.asList(getResources().getStringArray(R.array.other_design)));
        hashMap.put("ALL_OTHER_SOCIAL", Arrays.asList(getResources().getStringArray(R.array.other_social)));
        hashMap.put("ALL_OTHER_PETS", Arrays.asList(getResources().getStringArray(R.array.other_pets)));
        hashMap.put("REPAIR_SOCIAL", Arrays.asList(getResources().getStringArray(R.array.repair_social)));
        hashMap.put("REPAIR_FLAT", Arrays.asList(getResources().getStringArray(R.array.repair_flat)));
        hashMap.put("REPAIR_WINDOW", Arrays.asList(getResources().getStringArray(R.array.repair_windows)));
        hashMap.put("REPAIR_CARPENTRY", Arrays.asList(getResources().getStringArray(R.array.repair_carpentry)));
        hashMap.put("REPAIR_BYT", Arrays.asList(getResources().getStringArray(R.array.repair_byt)));
        hashMap.put("REPAIR_HOME_BYT", Arrays.asList(getResources().getStringArray(R.array.repair_home_byt)));
        hashMap.put("REPAIR_OTHER", Arrays.asList(getResources().getStringArray(R.array.repair_other)));
        hashMap.put("SPORT_SECTIONS", Arrays.asList(getResources().getStringArray(R.array.sport_sections)));
        hashMap.put("SPORT_FITNESS", Arrays.asList(getResources().getStringArray(R.array.sport_fitness_bodybuilding)));

        hashMap.put("REST_TO_LIVE", Arrays.asList(getResources().getStringArray(R.array.rest_to_live)));
        hashMap.put("REST_CHILD", Arrays.asList(getResources().getStringArray(R.array.rest_child)));
        hashMap.put("REST_PANSIONAT", Arrays.asList(getResources().getStringArray(R.array.rest_pansionat)));
        hashMap.put("REST_DOCUMENTS", Arrays.asList(getResources().getStringArray(R.array.rest_documents)));

        hashMap.put("REPAIR_COMMUNICATION", Arrays.asList(getResources().getStringArray(R.array.repair_communication)));
        hashMap.put("PRESENTS_OTHER", Arrays.asList(getResources().getStringArray(R.array.presents_other)));

        hashMap.put("JUR_OTHER", Arrays.asList(getResources().getStringArray(R.array.jur_other)));
        hashMap.put("JUR_TRANSPORT", Arrays.asList(getResources().getStringArray(R.array.jur_transport)));
        hashMap.put("JUR_SERVICE", Arrays.asList(getResources().getStringArray(R.array.jur_service)));
        hashMap.put("JUR_AUDIT", Arrays.asList(getResources().getStringArray(R.array.jur_audit)));
        hashMap.put("JUR_PC", Arrays.asList(getResources().getStringArray(R.array.jur_pc)));
        hashMap.put("JUR_AVIA", Arrays.asList(getResources().getStringArray(R.array.jur_avia)));
    }


}
