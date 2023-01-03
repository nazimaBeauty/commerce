package com.example.jaryia.fragmentPages;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jaryia.model.News;
import com.example.jaryia.R;
import com.example.jaryia.RecyclerAdapter;
import com.example.jaryia.model.PostInfo;
import com.example.jaryia.repo.JSONPlaceHolder;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Jobs#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Jobs extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private boolean checker = true;
    private final ArrayList<News> allInformation = new ArrayList<>();

    public Jobs() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Jobs.
     */
    // TODO: Rename and change types and number of parameters
    public static Jobs newInstance(String param1, String param2) {
        Jobs fragment = new Jobs();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SwipeRefreshLayout swipeRefreshLayout = view.findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            Toast.makeText(getContext(), "Жаныланды", Toast.LENGTH_SHORT).show();
            requireActivity().finish();
            startActivity(requireActivity().getIntent());
            swipeRefreshLayout.setRefreshing(false);
        });

        TextView jariya;
        jariya = requireActivity().findViewById(R.id.main_page_link);
        jariya.setMovementMethod(LinkMovementMethod.getInstance());

        init(view);
    }

    private void init(View view) {
        clear();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://karasuukg.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JSONPlaceHolder jsonPlaceHolder = retrofit.create(JSONPlaceHolder.class);
        Call<List<PostInfo>> call = jsonPlaceHolder.getPost();
        call.enqueue(new Callback<List<PostInfo>>() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onResponse(@NonNull Call<List<PostInfo>> call, @NonNull Response<List<PostInfo>> response) {
                if (!response.isSuccessful()) {
                    Toast.makeText(getContext(), "Data is empty", Toast.LENGTH_SHORT).show();
                } else {
                    List<PostInfo> postInfos = response.body();
                    if (postInfos != null) {
                        String[] photo = new String[postInfos.size()];
                        String[] des = new String[postInfos.size()];
                        String[] data = new String[postInfos.size()];
                        String[] social = new String[postInfos.size()];
                        StringBuilder temp = new StringBuilder();
                        StringBuilder photoTemp = new StringBuilder();
                        for (int i = 0; i < postInfos.size(); i++) {
                            if (postInfos.get(i).getItemchoice() == 5) {
                                des[i] = postInfos.get(i).getDescription();
                                data[i] = postInfos.get(i).getAdd_date();

                                for (int j = 0; j < postInfos.get(i).getIms().size(); j++)
                                    photoTemp.append("https://res.cloudinary.com/drw2jobkz/").append(postInfos.get(i).getIms().get(j).getNumber()).append(" ");

                                for (int j = 0; j < postInfos.get(i).getNumbers().size(); j++) {
                                    temp.append(postInfos.get(i).getNumbers().get(j).getNumber()).append("\n");
                                }

                                photo[i] = photoTemp.toString();
                                photoTemp = new StringBuilder();

                                social[i] = temp.toString();
                                temp = new StringBuilder();
                            }
                        }

                        for (int i = des.length - 1; i >= 0; i--) {
                            if (postInfos.get(i).getItemchoice() == 5) {
                                News news = new News(des[i], data[i], social[i], photo[i]);
                                allInformation.add(news);
                            }
                        }

                        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.setHasFixedSize(true);
                        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(getContext(), allInformation);
                        recyclerView.setAdapter(recyclerAdapter);
                        recyclerAdapter.notifyDataSetChanged();
                        checker = false;
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<PostInfo>> call, @NonNull Throwable t) {
                Toast.makeText(getContext(), "--------------------------" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void clear() {
        int size = allInformation.size();
        if (size > 0) {
            allInformation.subList(0, size).clear();
        }
    }
}