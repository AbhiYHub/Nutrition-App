package com.miniproject.nutritionapp.ProfileFrags;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.miniproject.nutritionapp.Keys;
import com.miniproject.nutritionapp.R;


public class FragmentGender extends Fragment {

    View view;

    RadioButton male,female;
    RadioGroup group;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_gender, container, false);

        group = view.findViewById(R.id.radio_frag);
        male = view.findViewById(R.id.male_frag);
        female = view.findViewById(R.id.female_frag);

        SharedPreferences sp = getActivity().getSharedPreferences(Keys.USER_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (sp.getString(Keys.USER_GENDER,Keys.USER_MALE).equals(Keys.USER_FEMALE)){
            group.check(female.getId());
        }else{
            group.check(male.getId());
            editor.putString(Keys.USER_GENDER,Keys.USER_MALE);
            editor.commit();
        }

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if(radioGroup.getCheckedRadioButtonId()==male.getId()){
                    editor.putString(Keys.USER_GENDER,Keys.USER_MALE);
                    editor.commit();
                }else if(radioGroup.getCheckedRadioButtonId()==female.getId()){
                    editor.putString(Keys.USER_GENDER,Keys.USER_FEMALE);
                    editor.commit();
                }
            }
        });

        return view;
    }
}