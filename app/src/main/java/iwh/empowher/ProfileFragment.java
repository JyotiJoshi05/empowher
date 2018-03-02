package iwh.empowher;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.AppCompatButton;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by Jyoti Joshi on 18-02-2018.
 */

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private TextView tv_name,tv_email,tv_message;
    private SharedPreferences pref;
    private AppCompatButton btn_change_password,btn_logout;
    private EditText et_old_password,et_new_password;
    private AlertDialog dialog;
    private ProgressBar progress;
    private Button sevent, bevent, levent,bstory;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile,container,false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        pref = getActivity().getPreferences(0);
        String username = pref.getString(Constants.NAME,"");
        //Toast.makeText(getActivity().getApplicationContext(), "Welcome "+username, Toast.LENGTH_SHORT).show();
        //tv_name.setText("Username: "+pref.getString(Constants.NAME,""));
        sevent.setOnClickListener(this);
        bevent.setOnClickListener(this);
        levent.setOnClickListener(this);
        bstory.setOnClickListener(this);


    }

    private void initViews(View view){

        //tv_name = (TextView)view.findViewById(R.id.tv_name);
        sevent = (Button)view.findViewById(R.id.btn_graduate);
        bevent = (Button)view.findViewById(R.id.btn_bwoman);
        levent = (Button)view.findViewById(R.id.btn_lady);
        bstory = (Button)view.findViewById(R.id.btn_story);


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_graduate:
                Intent gintent = new Intent(getActivity(),EmpowHer_Sevents.class);
                startActivity(gintent);
                break;
            case R.id.btn_bwoman:
                Intent bintent = new Intent(getActivity(),EmpowHer_Bevents.class);
                startActivity(bintent);
                break;
            case R.id.btn_lady:
                Intent lintent = new Intent(getActivity(),EmpowHer_Levents.class);
                startActivity(lintent);
                break;
            case R.id.btn_story:
                Intent sintent = new Intent(getActivity(),EmpowHer_Story.class);
                startActivity(sintent);
                break;
        }
    }

    private void logout() {
    }

    private void goToLogin(){

    }

    private void changePasswordProcess(String email,String old_password,String new_password){


    }
}