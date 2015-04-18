package com.example.dewanshusharma.materialdes;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationDrawerFragment extends Fragment {

    private RecyclerView recyclerView;
    public static final String PrefFile="test";
    public static final String Key_drawer_used="drawer_used";
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mDrawerToggle;
    private boolean mUsedDrawer;
    private boolean msavedInstance;
    private View cView;
    private DataAdapter adapter;



    public NavigationDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
mUsedDrawer=Boolean.valueOf(readFromPref(getActivity(),Key_drawer_used,"false")) ;

        if(savedInstanceState!=null){
            msavedInstance=true;
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View layout=inflater.inflate(R.layout.fragment_navigation_drawer, container, true);
        recyclerView=(RecyclerView)layout.findViewById(R.id.drawerlist1);
        adapter=new DataAdapter(getActivity(),getData());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        return layout;
    }

    public List<Dataitem> getData(){
        List<Dataitem> data=new ArrayList<Dataitem>();
        int imgarr[]={R.drawable.im1,R.drawable.im2,R.drawable.im3,R.drawable.im4,R.drawable.im5,R.drawable.im6};
    String titles[]={"twitter","instagram","messages","facebook","Youtube","Gmail"};
        for(int i=0;i<25;i++){
            Dataitem putinfo=new Dataitem();
            putinfo.img=imgarr[i%imgarr.length];
            putinfo.title=titles[i%titles.length];
            data.add(putinfo);
        }
        return data;
    }

    public void setUp(DrawerLayout drawerLayout, final Toolbar toolbar,int frag_ID){


        mDrawerLayout=drawerLayout;
        cView=getActivity().findViewById(frag_ID);
        mDrawerToggle=new ActionBarDrawerToggle(getActivity(),mDrawerLayout,toolbar,R.string.drawer_open,R.string.drawer_close){


            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if(!mUsedDrawer){
                    mUsedDrawer=true;
                    saveToPref(getActivity(),Key_drawer_used,mUsedDrawer+" ");

                }
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                if(slideOffset<0.6){
                    toolbar.setAlpha(1-slideOffset);
                }
            }
        };
        //if(!mUsedDrawer && !msavedInstance){
          //  mDrawerLayout.openDrawer(cView);
       // }
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
           mDrawerToggle.syncState();
            }
        });



    }

    public static void saveToPref(Context context,String prefName,String prefValue){
        SharedPreferences sp=context.getSharedPreferences(PrefFile,context.MODE_PRIVATE);
        SharedPreferences.Editor edit=sp.edit();
        edit.putString(prefName,prefValue);
        edit.apply();

    }


    public static String readFromPref(Context context,String prefName,String defValue){
        SharedPreferences sp=context.getSharedPreferences(PrefFile,context.MODE_PRIVATE);
        return sp.getString(prefName,defValue);
    }
}
