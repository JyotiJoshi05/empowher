package iwh.empowher;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Jyoti Joshi on 22-02-2018.
 */

public class ViewSRecords extends AppCompatActivity {
    TextView event,view_title,view_description,view_link,view_org,view_date,view_eligible,view_cat;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewallrecords);
        Bundle extras = getIntent().getExtras();
        if (extras == null) throw new AssertionError();
        String title = extras.getString("clickedTitle");
        String desc = extras.getString("clickedDesc");
        String date = extras.getString("clickedDeadline");
        String org = extras.getString("clickedOrg");
        String cat = extras.getString("clickedCat");
        String eligible = extras.getString("clickedEligible");
        final String link = extras.getString("clickedLink");
        //view_link = (TextView)findViewById(R.id.slink);
        view_title = (TextView)findViewById(R.id.stitle);
        view_description =(TextView)findViewById(R.id.description);
        //view_date = (TextView)findViewById(R.id.deadline);
        view_eligible = (TextView)findViewById(R.id.eligible);
        view_org = (TextView)findViewById(R.id.sorg);
        view_cat = (TextView)findViewById(R.id.scat);
        //event.setText(data);
        view_title.setText(title);
        view_description.setText(desc);
        //view_date.setText(date);
        view_org.setText(org);
        view_eligible.setText(eligible);
        view_cat.setText(cat);
        Button Button = (Button)findViewById(R.id.slink);

        Button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory(Intent.CATEGORY_BROWSABLE);
                intent.setData(Uri.parse(link));
                startActivity(intent);
            }
        });
    }

}
