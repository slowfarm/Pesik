package eva.android.com.pesik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String json = "";
        try {
            json = new GoodsGetTask().execute().get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        List<Good> goods = new ArrayList<>();
        try {
            JSONArray label = new JSONArray(json);
            for (int i = 0; i < label.length(); i++ ){
                Good good = new Good();
                JSONObject lab = label.getJSONObject(i);
                good.setLabel(lab.getString("label"));
                goods.add(good);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        for(Good good: goods)
            Log.d("mylog", "label" + good.getLabel());
    }
}
