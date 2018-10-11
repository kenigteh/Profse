package com.ivlup.profse.tools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.ivlup.profse.FragmentClientCard;
import com.ivlup.profse.MainActivity;
import com.ivlup.profse.R;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;


public class ClientListFragment extends Fragment {

    private static RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private static RecyclerView.Adapter adapter;

    public void onResume(){
        super.onResume();

        ((MainActivity) getActivity())
                .setActionBarTitle(MainActivity.chosenClient);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.client_list, container, false);

    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        // Setup any handles to view objects here
        // EditText etFoo = (EditText) view.findViewById(R.id.etFoo);
        final ListView listView = view.findViewById(R.id.clients_recycler);
        //GroupAdapter adapter = new GroupAdapter();
        //listView.setAdapter(adapter);
        //listView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        //adapter.clear();


        ArrayList<String> names = new ArrayList<>();

       // for (Client st : MainActivity.clients) {
        //    if (st.getCategory().equals(MainActivity.chosenClient))
        //        items.add(new ClientItem(st) );
       // }

       for (int i = 0; i < MainActivity.clients.size(); i++) {
           if (MainActivity.clients.get(i).getCategory().equals(MainActivity.chosenClient)) {
               names.add(MainActivity.clients.get(i).getName());
           }
       }
        Set<String> hs = new HashSet<>();
        hs.addAll(names);
        names.clear();
        names.addAll(hs);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (view.getContext(),
                        android.R.layout.simple_list_item_1,
                        names);
        listView.setAdapter(adapter);


        if (names.isEmpty()) Toast.makeText(getContext(), "По вашему запросу ничего не найдено", Toast.LENGTH_LONG).show();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MainActivity.chosenName = String.valueOf(listView.getItemAtPosition(position));

                MainActivity.fm = getFragmentManager();
                MainActivity.ft = MainActivity.fm.beginTransaction();
                MainActivity.ft.replace(R.id.fragment_container, new FragmentClientCard());
                MainActivity.ft.commit();
                MainActivity.ft.addToBackStack(null);
            }
        });
        //adapter.addAll(items);

        //Toast.makeText(getContext(), MainActivity.chosen, Toast.LENGTH_SHORT).show();
        //MainActivity.chosen=


    }
}
//class ClientListFragment extends RecyclerView.ViewHolder implements View.OnclickListener, View.OnLongClickListener