package example.soufiane.plateformepedagogique;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

public class BdList extends Activity {
    ListView list;
    String[] web = {
            "img1",
            "img2",
            "img3"

    } ;
    Integer[] imageId = {
            R.drawable.img1,
            R.drawable.img2,
            R.drawable.img3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bd_list);

        CustomList adapter = new
                CustomList(BdList.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(BdList.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
                Intent i2 = new Intent(getApplicationContext(), BD.class);
                Bundle bundle = ActivityOptions.makeCustomAnimation(getApplicationContext(), R.anim.move_right_in, R.anim.move_right_out).toBundle();
                startActivity(i2, bundle);
            }
        });

    }

}