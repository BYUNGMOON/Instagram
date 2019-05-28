package com.example.instagram;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfileName, edtProfileBio, edtProfileProfession, edtProfileHobbies, edtProfileFavSport;
    private Button btnUpdateInfo, btnLogout;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtProfileName = view.findViewById(R.id.edtProfileName);
        edtProfileBio = view.findViewById(R.id.edtProfileBio);
        edtProfileProfession = view.findViewById(R.id.edtProfileProfession);
        edtProfileHobbies = view.findViewById(R.id.edtProfileHobbies);
        edtProfileFavSport = view.findViewById(R.id.edtProfileFavoriteSport);

        btnUpdateInfo = view.findViewById(R.id.btnUpdateInfo);
        btnLogout = view.findViewById(R.id.btnLogout);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (parseUser.getCurrentUser() != null) {
                    parseUser.getCurrentUser().logOut();

                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

        String str;

        str = parseUser.get("profileName") + "";
        if( str.equals("null")) str = "";
        edtProfileName.setText(str);

        str = parseUser.get("profileBio") + "";
        if( str.equals("null")) str = "";
        edtProfileBio.setText(str);

        str = parseUser.get("profileProfession") + "";
        if( str.equals("null")) str = "";
        edtProfileProfession.setText(str);

        str = parseUser.get("profileHobbies") + "";
        if( str.equals("null")) str = "";
        edtProfileHobbies.setText(str);

        str = parseUser.get("ProfileFavSport") + "";
        if( str.equals("null")) str = "";
        edtProfileFavSport.setText(str);

        btnUpdateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ( edtProfileName.getText().toString().equals("") ||
                        edtProfileBio.getText().toString().equals("") ||
                        edtProfileProfession.getText().toString().equals("") ||
                        edtProfileHobbies.getText().toString().equals("") ||
                        edtProfileFavSport.getText().toString().equals("")) {

                    FancyToast.makeText(getContext(),
                            "Input Required",
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,
                            true).show();

                    return;
                }

                parseUser.put("profileName", edtProfileName.getText().toString());
                parseUser.put("profileBio", edtProfileBio.getText().toString());
                parseUser.put("profileProfession", edtProfileProfession.getText().toString());
                parseUser.put("profileHobbies", edtProfileHobbies.getText().toString());
                parseUser.put("ProfileFavSport", edtProfileFavSport.getText().toString());

                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {

                        if (e == null) {

                            FancyToast.makeText(getContext(),
                                    "Info Updated",
                                    FancyToast.LENGTH_LONG,
                                    FancyToast.SUCCESS,
                                    true).show();
                        } else {
                            FancyToast.makeText(getContext(),
                                    e.getMessage(),
                                    FancyToast.LENGTH_LONG,
                                    FancyToast.ERROR,
                                    true).show();
                        }
                    }
                });

            }
        });
        return view;
    }
}
